import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class AssembleLogic {
 
    String inputfile;
    PrintWriter outputfile;
    SymbolTable table = new SymbolTable();
    
    public AssembleLogic(String input) throws IOException {
        
        inputfile = input;
        String output = inputfile.replaceAll("\\..*", "") + ".hack";
        outputfile = new PrintWriter(new FileWriter(output));  
    }
    
    
    public void firstPass() throws IOException, FileNotFoundException {
        
            int romAddr = 0;
            Parser parse = new Parser(inputfile);
            String symbol;
            
            while (parse.hasMoreCommands()) {
                
                if (parse.commandType() == Parser.Command.L_COMMAND) {
                    
                    symbol = parse.symbol();
                    
                    if (!table.contains(symbol)) {
                        table.addEntry(parse.symbol(), romAddr);
                    }
                    
                } else {
                    romAddr++;
                    
                    if (romAddr > 32768) {
                        System.err.println("Error: All ROM memory is currently in use");
                    }
                }
                parse.advance();
            }
            
            parse.close();
            
    }        
            
    public void secondPass() throws IOException, FileNotFoundException {
        Parser parse = new Parser(inputfile);
        int ramAddr = 16;
        String dest, comp, jump, symbol, value, command;
            
        while(parse.hasMoreCommands()) {
                
            if (parse.commandType() == Parser.Command.C_COMMAND) {
                dest = parse.dest();
                comp = parse.comp();
                jump = parse.jump();
                
                outputfile.println("111" + Code.comp(comp) + Code.dest(dest) + Code.jump(jump));
                
            } else if (parse.commandType() == Parser.Command.A_COMMAND) {
                    
                symbol = parse.symbol();
                    
                if (Character.isDigit(symbol.charAt(0))) {
                    value = Code.convertBinary(symbol);
                    
                } else if (table.contains(symbol)) {
                    String temp = Integer.toString(table.getAddress(symbol));
                    value = Code.convertBinary(temp);
                    
                } else {
                        
                    if (ramAddr > 16383) {
                        System.err.println("Warning: RAM Address is in I/O memory");
                    }
                        
                    if (ramAddr > 24576) {
                        System.err.println("Error: No RAM left");
                    }
                        
                    table.addEntry(symbol, ramAddr);
                    String temp = Integer.toString(ramAddr);
                    value = Code.convertBinary(temp);
                    ramAddr++;
                }
                    
                outputfile.println("0" + value);
                    
            }
                
                parse.advance();     
        }
        
        //Last line
        if (parse.commandType() == Parser.Command.C_COMMAND) {
            dest = parse.dest();
            comp = parse.comp();
            jump = parse.jump();
                    
            outputfile.println("111" + Code.comp(comp) + Code.dest(dest) + Code.jump(jump));
        } else if (parse.commandType() == Parser.Command.A_COMMAND) {
                    
            symbol = parse.symbol();
                    
            if (Character.isDigit(symbol.charAt(0))) {
                value = Code.convertBinary(symbol);
            } else if (table.contains(symbol)) {
                String temp = Integer.toString(table.getAddress(symbol));
                value = Code.convertBinary(temp);
            } else {
                        
                if (ramAddr > 16383) {
                    System.err.println("Warning: RAM Address is in I/O memory");
                }
                        
                if (ramAddr > 24576) {
                    System.err.println("Error: No RAM left");
                }
                        
                table.addEntry(symbol, ramAddr);
                String temp = Integer.toString(ramAddr);
                value = Code.convertBinary(temp);
                ramAddr++;
            }
                    
            outputfile.println("0" + value);
                    
        }
        
        outputfile.close();
    }
 
}