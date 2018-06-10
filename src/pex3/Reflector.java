/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pex3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kyle M. Medeiros
 */
public class Reflector {
    List<String> swaps;
    List<Character> inputs = new ArrayList<>();
    List<Character> outputs = new ArrayList<>();
    
    public Reflector(String str) {
        
        swaps = Arrays.asList(str.split(","));
        
        for (String s : swaps){
            char[] temp = s.toCharArray();
            
            inputs.add(temp[0]);
            outputs.add(temp[temp.length-1]);
            
        }
        
        System.out.printf("The Reflector settings are as follows: \n");
        System.out.printf("Inputs:  %s\n",inputs);
        System.out.printf("Outputs: %s\n",outputs);
    }
    
    public char reflectorSwap(char input){
        
        if (inputs.contains(input)){
            
            int sIndex = inputs.indexOf(input);
            System.out.printf("Reflector is returning a successful swap to %c.\n",outputs.get(sIndex));
            return outputs.get(sIndex);
            
        }
        else if (outputs.contains(input)){
            
            int sIndex = outputs.indexOf(input);
            System.out.printf("Reflector is returning a successful swap to %c.\n",inputs.get(sIndex));
            return inputs.get(sIndex);
        }
        else{
            System.out.println("No match in the reflector was found. returning default input.");
            return input;
        }
    }
    
    public List getReflectorInputs(){
        return inputs;
    }
    
    public List getReflectorOutputs(){
        return outputs;
    }
    

    
}
