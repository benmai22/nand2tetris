
public class Code {
    
    public static String dest(String mnem) {
        
        String d1 = "0";
        String d2 = "0";
        String d3 = "0";
        
        if (mnem.contains("A")) {
            d1 = "1";
        }
        
        if (mnem.contains("D")) {
            d2 = "1";
        }
        
        if (mnem.contains("M")) {
            d3 = "1";
        }
        
        return d1 + d2 + d3;
    }
    
    public static String comp(String mnem) {
        
        String cbits = "";
        
        if (mnem.equals("0")) {
            cbits = "101010";
        } else if (mnem.equals("1")) {
            cbits = "111111";
        } else if (mnem.equals("-1")) {
            cbits = "111010";
        } else if (mnem.equals("D")) {
            cbits = "001100";
        } else if (mnem.equals("A")||mnem.equals("M")) {
            cbits = "110000";
        } else if (mnem.equals("!D")) {
            cbits = "001101";
        } else if (mnem.equals("!A")||mnem.equals("!M")) {
            cbits = "110001";
        } else if (mnem.equals("-D")) {
            cbits = "001111";
        } else if (mnem.equals("-A")||mnem.equals("-M")) {
            cbits = "110011";
        } else if (mnem.equals("D+1")) {
            cbits = "011111";
        } else if (mnem.equals("A+1")||mnem.equals("M+1")) {
            cbits = "110111";
        } else if (mnem.equals("D-1")) {
            cbits = "001110";
        } else if (mnem.equals("A-1")||mnem.equals("M-1")) {
            cbits = "110010";
        } else if (mnem.equals("D+A")||mnem.equals("D+M")) {
            cbits = "000010";
        } else if (mnem.equals("D-A")||mnem.equals("D-M")) {
            cbits = "010011";
        } else if (mnem.equals("A-D")||mnem.equals("M-D")) {
            cbits = "000111";
        } else if (mnem.equals("D&A")||mnem.equals("D&M")) {
            cbits = "000000";
        } else if (mnem.equals("D|A")||mnem.equals("D|M")) {
            cbits = "010101";
        }
        
        if (mnem.contains("M")) {
            cbits = "1" + cbits;
        } else {
            cbits = "0" + cbits;
        }
        
        return cbits;
    }
    
    public static String jump(String mnem) {
        
        if (mnem.equals("JGT")) {
            return "001";
        } else if (mnem.equals("JEQ")) {
            return "010";
        } else if (mnem.equals("JGE")) {
            return "011";
        } else if (mnem.equals("JLT")) {
            return "100";
        } else if (mnem.equals("JNE")) {
            return "101";
        } else if (mnem.equals("JLE")) {
            return "110";
        } else if (mnem.equals("JMP")) {
            return "111";
        } else {
            return "000";
        }
    }
    
    public static String convertBinary(String symbol) {
        
        int val = Integer.valueOf(symbol);
        String binary = Integer.toBinaryString(val);
        
        return String.format("%1$15s", binary).replace(" ", "0");
    }
}
