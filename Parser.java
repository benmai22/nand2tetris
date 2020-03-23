import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Parser {
    
    public enum Command {
        A_COMMAND, C_COMMAND, L_COMMAND;
    }
    
    private Scanner asmfile;
    private String line;
    
    public Parser(String inputfile) throws FileNotFoundException, IOException {
         asmfile = new Scanner(new File("/Users/benmai/Documents/06/add/add.asm"));
        line = asmfile.nextLine().replaceAll("//.*$","").trim();
        
        if (line.isEmpty()) {
            advance();
        }
    }
    
    public boolean hasMoreCommands() {
        if (asmfile.hasNextLine()) {
            return true;
        } else {
            return false;
        }
    }
    
    public void advance() throws IOException {
        if (hasMoreCommands()) {
            line = asmfile.nextLine().replaceAll("//.*$","").trim();
            
            if (line.equals("") || (line.startsWith("//"))) {
                advance();
            }
        }
    }
    
    public Command commandType() {
        if (line.startsWith("@")) {
            return Command.A_COMMAND;
        } else if (line.startsWith("(")) {
            return Command.L_COMMAND;
        } else {
            return Command.C_COMMAND;
        }
    }
    
    public String symbol() {
        if (commandType() == Command.A_COMMAND) {
            return line.substring(1);
        } else if (commandType() ==  Command.L_COMMAND) {
            return line.substring(1, line.length() - 1);
        }
        return "";
    }
    
    public String dest() {
        if (commandType() == Command.C_COMMAND) {
            
            if (line.contains("=")) {
                int index = line.indexOf("=");
                return line.substring(0, index);
            }   
        }
        return "";
    }
    
    public String comp() {
        if (commandType() == Command.C_COMMAND) {
            
            int index;
            
            if (line.contains("=")) {
                index = line.indexOf("=");
                return line.substring(index + 1);
            }
            
            if (line.contains(";")) {
                index = line.indexOf(";");
                return line.substring(0, index);
            }
                 
        }        
        return "";
    }
    
    public String jump() {
        if (commandType() == Command.C_COMMAND && line.contains(";")) {
            int index = line.indexOf(";");
            return line.substring(index + 1);
        }
        return "";
    }
    
    public void close() {
        asmfile.close();
    }
}