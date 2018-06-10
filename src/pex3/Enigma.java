/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pex3;

/**
 *
 * @author Kyle M. Medeiros
 */
public class Enigma {
    
        
    
        Rotor outer;
        Rotor middle;
        Rotor inner;
        Plugboard plugboard;
        Reflector reflector;
        
        
        private String path;
        private String megaString;
        
        
        
        public Enigma(Rotor rO, Rotor rM, Rotor rI, String plugSetting, String reflectSetting, String stringToEncrypt){
            
        outer = rO;
        middle = rM;
        inner = rI;
        plugboard = new Plugboard(plugSetting);
        reflector = new Reflector(reflectSetting);
        megaString = stringToEncrypt;
        
        }
         

        

        


    public char encryptChar(char input){
        
         boolean wasLowerCase = false;
         boolean rotorUsedFlag;
         boolean inputNotALetter = false;
         
         //Lowercase to uppercase checker/flag
        if (Character.isLowerCase(input)){
            input = Character.toUpperCase(input);
            wasLowerCase = true;
        }
        else if (Character.isLetter(input) == false){
            inputNotALetter = true;
        }
        //Step 1: Plugboard
        System.out.printf("Character before first plugboard: %c\n",input);
        input = plugboard.plugboardSwap(input);
        System.out.printf("Character after first plugboard and before rotors: %c\n",input);
        
        //Step 2: Rotors
        int nextR = 0;
        
            //Inner to Outer rotor
     
        if (inner.isInTheRotor(input)){
            nextR = inner.getIndexOf(input);
            input = outer.getCharOf(nextR);

           System.out.printf("Inner to Outer Rotor: %c\n",input);

                //Middle to Outer rotor
            nextR = middle.getIndexOf(input);
            input = outer.getCharOf(nextR);


            System.out.printf("Middle to Outer Rotor: %c\n",input); 
            
            rotorUsedFlag = true;
        }
        else{
            System.out.printf("%c was not in the rotor. Skipping to reflector.\n",input);
            rotorUsedFlag = false;
        }
        //Step 3: Reflector
        
        input = reflector.reflectorSwap(input);
        
        System.out.printf("After reflector: %c\n",input);
        //Step 4: Rotors in Reverse
        
          if (inner.isInTheRotor(input)){
            //Outer to Middle rotor
        nextR = outer.getIndexOf(input);
        input = middle.getCharOf(nextR);
        
        System.out.printf("Outer to Middle rotor: %c\n",input);
        
            //Outer to Inner rotor
        nextR = outer.getIndexOf(input);
        input = inner.getCharOf(nextR);
        
        System.out.printf("Outer to Inner Rotor: %c\n",input);     
          }
          
       else{
           System.out.printf("%c was not in the rotor. Skipping to plugboard.\n",input);
        }
          
        //Step 5: Plugboard again
        input = plugboard.plugboardSwap(input);
        
       System.out.printf("Character after last plugboard: %c\n",input);
        
        //Step 6: Spin the rotors
        
        
        
        if (rotorUsedFlag == true){
        System.out.println("The rotors are spinning");
        inner.spinRotor(1,"CW");
            if (inner.hasFullyRotated() == true){
                System.out.println("Inner rotor fully rotated. Rotating middle rotor.");
                middle.spinRotor(1, "CW");
                
                if (middle.hasFullyRotated() == true){
                    System.out.println("Middle rotor fully rotated. Rotating outer rotor.");
                    outer.spinRotor(1, "CW");
                }
            }
        }
            
        if (wasLowerCase /*|| (inputNotALetter && Character.isUpperCase(input))*/){
            input = Character.toLowerCase(input);
        }
        
        return input;
    }    
    
    public void setRotors(){
        inner.spinRotor(inner.getStartPos(), "CCW");
        middle.spinRotor(middle.getStartPos(),"CCW");
        outer.spinRotor(outer.getStartPos(), "CCW");
    }
    
    public String encryptString(){
        String result = "";
        
        
       char[] strToArray = megaString.toCharArray();
       
       for (Character c : strToArray){
           
           c = encryptChar(c);
           
           System.out.println(c);
           
           result += c.toString();
       }
           
        
           
         System.out.println(result);
         return result;
       
    }
}
