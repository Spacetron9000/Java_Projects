/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pex3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Kyle M. Medeiros
 */
public class FileHandler {
    
    private String megaString;
    private String path;

    private File file;
    
    public FileHandler() {
    }

    public FileHandler(String fileName) {
        
        path = ("C:/Users/671km/OneDrive/Documents/NetBeansProjects/PEX3/src/resources/files/"+fileName+".txt");
    }
    
    
    String processedDocument(){
        
        try{
           
           megaString = new String(Files.readAllBytes(Paths.get(path)));
            
           
           
           megaString = megaString.replace("1","ONE");
           megaString = megaString.replace("2","TWO");   
           megaString = megaString.replace("3","THREE");
           megaString = megaString.replace("4","FOUR");
           megaString = megaString.replace("5","FIVE");
           megaString = megaString.replace("6","SIX");
           megaString = megaString.replace("7","SEVEN");
           megaString = megaString.replace("8","EIGHT");
           megaString = megaString.replace("9","NINE");
           megaString = megaString.replace("0","ZERO");
           
           System.out.printf("Successfully initialized and parsed %s .\n", path);
           
        }
        catch(IOException|NullPointerException e){
            System.out.printf("File %s couldn't be found \n",path);
        }
    
        return megaString;
    }
    
    
    public void writeToFile(String input, String outf){
        
        outf = "src/resources/outputs/"+outf+".txt";
            File file = new File(outf);
        
        
        
        try{           
            if(!file.exists()){
                System.out.println("Creating new encrypted message file...\n");
                file.createNewFile();
                System.out.println("Overwriting existing file...\n");
            }
            
            else{
                file.delete();
                file.createNewFile();
                
            }
           
        }
        catch(IOException e){
            System.out.println("Attempted to make file. Something went wrong \n");
        }
        
        
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outf), StandardCharsets.UTF_8, StandardOpenOption.APPEND)){

            writer.append(input);
        }
        
        catch(IOException e){
            e.printStackTrace();
        }
        
    }
}
