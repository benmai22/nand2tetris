import java.util.HashMap;

public class SymbolTable {
    
    HashMap<String, Integer> table;
    
    public SymbolTable() {
        table = new HashMap<String, Integer>();
        table.put("SP", 0);
        table.put("LCL", 1);
        table.put("ARG", 2);
        table.put("THIS", 3);
        table.put("THAT", 4);
        table.put("SCREEN", 16384);
        table.put("KBD", 24576);
        
        for (int i = 0; i < 16; i++) {
            table.put("R" + i, i);
        } 
    }
    
    public void addEntry(String symbol, int addr) {
        table.put(symbol, addr);
    }
    
    public boolean contains(String symbol) {
        return table.containsKey(symbol);
    }
    
    public int getAddress(String symbol) {
        return table.get(symbol);
    }
    
}
