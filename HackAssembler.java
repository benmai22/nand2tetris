import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class HackAssembler {

    public static void main(String[] args) throws Exception {
        
         //String inputfile = args[0];

        try {
           
            AssembleLogic assemble = new AssembleLogic("/Users/benmai/Documents/06/add/add.asm");
            assemble.firstPass();
            assemble.secondPass();
            
        } catch (FileNotFoundException ex) {
            System.err.println("Input file not found");
        } catch (IOException ex) {
            System.err.println("I/O error");
        }
        
  
    }
}