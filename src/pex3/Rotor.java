package pex3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kyle M. Medeiros
 */
public class Rotor {

    String I   = "AUNGHOVBIPWCJQXDKRY ELSZFMT.";
    String II  = "O J.ETYCHMRWAFKPUZDINSXBGLQV";
    String III = "FBDHJLNPRTVXZ.ACEGI KMOQSUWY";
    String IV  = ".HKPDEAC WTVQMYNLXSURZOJFBGI";
    String V   = "YDNGLCIQVEZRPTAOXWBMJSUH.K F";
    
    private char[] cypher;
    private char[] dCypher;
    private int startPos;
    private int totalTurns;
    
    public Rotor() {
                
    }
    
    public Rotor (String str, int start){
        
        switch(str){
            
            case "I":
                cypher = I.toCharArray();
               
                
                break;
            case "II":
                cypher = II.toCharArray();
                
                
                break;
            case "III":
                cypher = III.toCharArray();
                
                
                break;
            case "IV":
                cypher = IV.toCharArray();
                
                
                break;
            case "V":
                cypher = V.toCharArray();
                
            
                break;
                             
                
        }
        
        startPos = start;
    }
    
    public int getIndexOf(char character){
        
        
        for (int i = 0; i<28;i++){

            if (cypher[i] == character){
                return i;
            }
        }
        return 0;
    }
    
    public char getCharOf(int index){
        return cypher[index];
    }
    
    
    public void spinRotor(int numberOfSpins, String direction){
        
        
        char temp;
        
        switch (direction){
            
            case "CW":

                temp = cypher[27];
                System.arraycopy(cypher, 0, cypher, 1, 27);
                cypher[0] = temp;
                
                System.out.printf("New rotor:");
                for (char c : cypher){
                System.out.printf("%c,",c);
                }
                System.out.printf("\n");
                
                totalTurns++;
            break;
            
            
            case "CCW":
            System.out.println(cypher);
            for(int j = 0; j<numberOfSpins-1; j++){

                    temp = cypher[0];
                    System.arraycopy(cypher, 1, cypher, 0, 27);
                    cypher[27]=temp;
                
            }
                System.out.printf("Starting rotor:");
                for (char c : cypher){
                System.out.printf("%c,",c);
                }
                System.out.printf("\n");
                
            
            break;
            
            default:
                
                System.out.println("Incorrect direction argument.");
                
            break;
        }
        


       
    }
    
    public int getStartPos(){
        return startPos;
    }
    

    public boolean hasFullyRotated(){
        
        System.out.printf("Total turns for the rotor: %d\n",totalTurns);
        
        if (totalTurns == cypher.length){
            totalTurns = 0;
            
            return true;
        }
        
        return false;
    }
    
    public boolean isInTheRotor(char c){
        
        for (int i = 0; i<28; i++){
            if (cypher[i] == c){
                return true;
            }
           
        }
        
        return false;
    }
}
