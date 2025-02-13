// This is our CPU file

public class CPU{

    public int[] registers = new int[16];

    public String[] cache;

    public int temporary_reg_1;
    public int temporary_reg_2;
    public int temporary_Dst_reg;
    public int temporary_breg;
    public int temporary_sreg1;
    public int temporary_sreg2;
    public int temporary_address;

    private int cpuNumber;
    public int instant;
    public int operationCode;

    public CPU(int cpuNumber)
    {
        this.cpuNumber = cpuNumber;
        registers[1]=0;
    }

    public String fetch(int programCounter){
        String instruction = cache[programCounter];
        return instruction;
    }

    public static String hex_to_binary(String s)
    {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }

        String val2 = Integer.toBinaryString(val);
        return val2;
    }

    public static int hex_to_decimal(String s)
    {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

    public int binary_to_Integer(String binary) {
        char[] numbers = binary.toCharArray();
        int result = 0;
        for(int i=numbers.length - 1; i>=0; i--)
            if(numbers[i]=='1')
                result += Math.pow(2, (numbers.length-i - 1));
        return result;
    }

    public int decode(String instruction) {
        String binary_instructions = hex_to_binary(instruction.substring(2));
        String temporary_instructions = binary_instructions;
        instant = Integer.parseInt(temporary_instructions.substring(0, 2));
        operationCode = hex_to_decimal(instruction.substring(2, 8));

        switch(instant)
        {
            case 00: {
                temporary_sreg1 = binary_to_Integer(temporary_instructions.substring(8,12));
                temporary_sreg2 = binary_to_Integer(temporary_instructions.substring(12, 16));
                temporary_Dst_reg = binary_to_Integer(temporary_instructions.substring(16, 20));
                break;
            }

            case 01:{
                temporary_breg = binary_to_Integer(temporary_instructions.substring(8, 12));
                temporary_Dst_reg = binary_to_Integer(temporary_instructions.substring(12, 16));
                temporary_address = binary_to_Integer(temporary_instructions.substring(16));
                break;
            }

            case 10:{
                temporary_address = binary_to_Integer(temporary_instructions.substring(8));
                break;
            }

            case 11: {
                //IO operation
                temporary_reg_1 = binary_to_Integer(temporary_instructions.substring(8, 12));
                temporary_reg_2 = binary_to_Integer(temporary_instructions.substring(12, 16));
                temporary_address = binary_to_Integer(temporary_instructions.substring(16));
                break;
            }

            default: {
                System.out.println("EXCEPTION: Invalid instruction type");
            }
    }

    public void execute(){

    }

}