
import java.util.Scanner;

class Interpreter {

    Interpreter(){

    }

    public void interpretString(String str){
        String opcode = "";
        for(int i = 0; i < 6; i++){
            opcode = opcode + (Character.toString( str.charAt(i) ));
        }

        System.out.println("Char code it : " + opcode);
        System.out.println("Opcode value : " + convertBinary(opcode));

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
        String instructionFormat = "R";
        String operation = "";
        int register1;
        int register2;
        int registerDestination;
        int shiftAmount;
        int constant;

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

        

    }

    public void iFormatInstructions(String str){

    }

    public void jFormatInstructions(String str){

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
                    System.out.println(binaryString);
                    interpreter.interpretString(binaryString);
                }
                else {
                    System.out.println("String is not the correct length");
                }
                
            }

            

            System.out.println("Do you want to interpret another binary string (Y/N)");
            String continueUserInput = myObj.nextLine();

            if(continueUserInput != "Y" || continueUserInput != "y"){
                keepRunning = false;
            }

            
        }
    }
}