
import java.util.Scanner;

class Interpreter {

    Interpreter(){

    }

    public void interpretString(String str){
        String opcode = "";
        for(int i = 0; i < 6; i++){
            opcode = opcode + (Character.toString( str.charAt(i) ));
        }

        if(convertBinary(opcode) == 0){
            rFormatInstruction(str);
        }
        else if(convertBinary(opcode) == 2 || convertBinary(opcode) == 3){
            jFormatInstructions(str);
        } 
        else if(
            convertBinary(opcode) == 4 || convertBinary(opcode) == 5 || convertBinary(opcode) == 8
            || convertBinary(opcode) == 9 || convertBinary(opcode) == 12 || convertBinary(opcode) == 13 || 
            convertBinary(opcode) == 10 || convertBinary(opcode) == 11 || convertBinary(opcode) == 15 || convertBinary(opcode) == 35 ||
            convertBinary(opcode) == 43 )
        {
            iFormatInstructions(str);
        }
        
    }

    public void rFormatInstruction(String str){
        String operation = "";
        int register1;
        int register2;
        int registerDestination;
        int shiftAmount;
        int function;

        String temp1 = "";
        for(int i = 6; i < 11; i++){
            temp1 = temp1 + Character.toString(str.charAt(i));
        }
        register1 = convertBinary(temp1);
        
        
        String temp2 = "";
        for(int i = 11; i < 16; i++){
            temp2 = temp2 + Character.toString(str.charAt(i));
        }
        register2 = convertBinary(temp2);

        String temp3 = "";
        for(int i = 16; i < 21; i++){
            temp3 = temp3 + Character.toString(str.charAt(i));
        }
        registerDestination = convertBinary(temp3);

        String temp4 = "";
        for(int i = 21; i < 26; i++){
            temp4 = temp4 + Character.toString(str.charAt(i));
        }
        shiftAmount = convertBinary(temp4);

        String temp5 = "";
        for(int i = 26; i < 32; i++){
            temp5 = temp5 + Character.toString(str.charAt(i));
        }
        function = convertBinary(temp5);

        if(function == 32){
            operation = "add";
        }
        else if(function == 33){
            operation = "addu";
        }
        else if(function == 34){
            operation = "sub";
        }
        else if(function == 35){
            operation = "subu";
        }
        else if(function == 36){
            operation = "and";
        }
        else if(function == 37){
            operation = "or";
        }
        else if(function == 39){
            operation = "nor";
        }
        else if(function == 42){
            operation = "slt";
        }
        else if(function == 43){
            operation = "sltu";
        }
        else if(function == 0){
            operation = "sll";
        }
        else if(function == 2){
            operation = "srl";
        }
        else if(function == 8){
            operation = "jr";
        }

        System.out.println("\nInstruction Format: R");
        System.out.println("Operation: " + operation);
        System.out.println("Source Registers: " + register1 + ", " + register2);
        System.out.println("Destination Register: " + registerDestination);
        System.out.println("Shift amount: " + shiftAmount);
        System.out.println("Constant/Offset: none");
        
    }

    public void iFormatInstructions(String str){
        int opCode;
        int baseRegister;
        int destinationRegister;
        int immediate;

        String opCodeStr = "";

        String temp1 = "";
        for(int i = 0; i < 6; i++){
            temp1 = temp1 + Character.toString(str.charAt(i));
        }
        opCode = convertBinary(temp1);

        String temp2 = "";
        for(int i = 6; i < 11; i++){
            temp2 = temp2 + Character.toString(str.charAt(i));
        }
        baseRegister = convertBinary(temp2);

        String temp3 = "";
        for(int i = 11; i < 16; i++){
            temp3 = temp3 + Character.toString(str.charAt(i));
        }
        destinationRegister = convertBinary(temp3);

        String temp4 = "";
        for(int i = 16; i < 32; i++){
            temp4 = temp4 + Character.toString(str.charAt(i));
        }
        immediate = convertBinary(temp4);

        if(opCode == 4){
            opCodeStr = "beq";
        }
        else if(opCode == 5){
            opCodeStr = "bne";
        }
        else if(opCode == 8){
            opCodeStr = "addi";
        }
        else if(opCode == 9){
            opCodeStr = "addiu";
        }
        else if(opCode == 12){
            opCodeStr = "andi";
        }
        else if(opCode == 13){
            opCodeStr = "ori";
        }
        else if(opCode == 10){
            opCodeStr = "slti";
        }
        else if(opCode == 11){
            opCodeStr = "sltiu";
        }
        else if(opCode == 15){
            opCodeStr = "lui";
        }
        else if(opCode == 35){
            opCodeStr = "lw";
        }
        else if(opCode == 43){
            opCodeStr = "sw";
        }

        System.out.println("\nInstruction Format: I");
        System.out.println("Operation: " + opCodeStr);
        System.out.println("Source Registers: " + baseRegister);
        System.out.println("Destination Registers: " + destinationRegister);
        System.out.println("Shift amount: none");
        System.out.println("Constant/Offset: " + immediate);
    }

    public void jFormatInstructions(String str){
        int opCode;
        int address;

        String opCodeStr = "";

        String temp1 = "";
        for(int i = 0; i < 6; i++){
            temp1 = temp1 + Character.toString(str.charAt(i));
        }
        opCode = convertBinary(temp1);

        String temp2 = "";
        for(int i = 6; i < 32; i++){
            temp2 = temp2 + Character.toString(str.charAt(i));
        }
        address = convertBinary(temp2);

        if(opCode == 2){
            opCodeStr = "j";
        }
        else if(opCode == 3){
            opCodeStr = "jal";
        }

        System.out.println("\nInstruction Format: J");
        System.out.println("Operation: " + opCodeStr);
        System.out.println("Source Registers: none");
        System.out.println("Destination Registers: none");
        System.out.println("Shift amount: none");
        System.out.println("Constant/Offset: " + address);

    }
    
    int convertBinary(String binaryString){
        int decimal = Integer.parseInt(binaryString,2);  

        return decimal;
    }

}

class MIPS_Instruction{
    public static void main(String[] args){
        Scanner myObj = new Scanner(System.in);
        Interpreter interpreter = new Interpreter();

        boolean keepRunning = true;
        boolean inputNotValidated = true;

        String binaryString = "";

        while(keepRunning){
            System.out.println("Enter a 32 bit instruction to interpret");

            while(inputNotValidated){
                binaryString = myObj.nextLine();

                if(binaryString.length() == 32){
                    inputNotValidated = false;
                    interpreter.interpretString(binaryString);
                }
                else {
                    System.out.println("String is not the correct length");
                }
                
            }

            

            System.out.println("\nDo you want to interpret another binary string (Y/N)");
            String continueUserInput = myObj.nextLine();

            if(continueUserInput != "Y" || continueUserInput != "y"){
                keepRunning = false;
            }

            
        }
    }
}