/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pex3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


/**
 *
 * @author Kyle M. Medeiros
 */
public class FXMLDocumentController implements Initializable {

    private String docToProcess;
    private String encryptedString;
    
    @FXML 
    private String mode = "TEXT";
    @FXML
    private ComboBox<String> innerRotor;

    @FXML
    private ComboBox<String> middleRotor;

    @FXML
    private ComboBox<String> outerRotor;

    @FXML
    private Button encrypt;

    @FXML
    private TextArea plugs;

    @FXML
    private TextArea reflectors;

    @FXML
    private TextField fileNameBox;

    
    @FXML
    private Button encryptText;

    @FXML
    private Button encryptFile;
    
    @FXML
    private Button fileOpenButton;

    
    @FXML
    private TextArea inText;

    @FXML
    private TextArea outText;
    
    @FXML
    private TextField innerStartPos;

    @FXML
    private TextField middleStartPos;

    @FXML
    private TextField outerStartPos;
    
    @FXML
    private TextField modeIndicator;
    
    
    @FXML
    private TextField outFileNameBox;
    
    @FXML
    private TextArea statusBar;
    
    @FXML
    private Button openFileButton;
    
    @FXML
    private void exitEnigma(){
        System.exit(1);
    }
    
    @FXML
    void process(ActionEvent event) {
        
        switch (mode){
            
            case "TEXT":
                encryptFromText();
                break;
                
            case "FILE":
                encryptFromFile();
                break;
        }
        
        FileHandler filehandler = new FileHandler();
        
        statusBar.setText(statusBar.getText()+"Writing file to "+outFileNameBox.getText()+"\n");
        filehandler.writeToFile(encryptedString, outFileNameBox.getText());
         
        statusBar.setText(statusBar.getText()+"All done!\n");

    }
    
    @FXML
    void setModeToText(ActionEvent event){
        
        modeIndicator.setText("Textbox");
        mode = "TEXT";
        
    }
    @FXML
    void setModeToFile(ActionEvent event){
        
        modeIndicator.setText("File");
        mode = "FILE";
    }
    
    @FXML
    void encryptFromText(){
        
        statusBar.setText(statusBar.getText()+"Encrypting from a text box...\n");
        int outerStart = Integer.parseInt(outerStartPos.getText());
        Rotor outer = new Rotor(outerRotor.getValue(), outerStart);
        int middleStart = Integer.parseInt(middleStartPos.getText());
        Rotor middle = new Rotor(middleRotor.getValue(), middleStart);        
        int innerStart = Integer.parseInt(innerStartPos.getText());
        Rotor inner = new Rotor(innerRotor.getValue(), innerStart);  
        
        statusBar.setText(statusBar.getText()+"Successfully made all rotors.\n");

            docToProcess = inText.getText();
        
           docToProcess = docToProcess.replace("1","ONE");
           docToProcess = docToProcess.replace("2","TWO");   
           docToProcess = docToProcess.replace("3","THREE");
           docToProcess = docToProcess.replace("4","FOUR");
           docToProcess = docToProcess.replace("5","FIVE");
           docToProcess = docToProcess.replace("6","SIX");
           docToProcess = docToProcess.replace("7","SEVEN");
           docToProcess = docToProcess.replace("8","EIGHT");
           docToProcess = docToProcess.replace("9","NINE");
           docToProcess = docToProcess.replace("0","ZERO");

        Enigma enigma = new Enigma(outer, middle, inner, plugs.getText(), reflectors.getText(), docToProcess);
        
        statusBar.setText(statusBar.getText()+"Successfully initialized Enigma class...\n");
        enigma.setRotors();
        
        statusBar.setText(statusBar.getText()+"Encrypting.....\n");
        encryptedString = enigma.encryptString();
        outText.setText(encryptedString);
        
        statusBar.setText(statusBar.getText()+"Successfully encrypted text.\n");
        
        
        
        
        
    }
    
    @FXML
    void encryptFromFile(){
        statusBar.setText(statusBar.getText()+"Encrypting from a file...\n");
        int outerStart = Integer.parseInt(outerStartPos.getText());
        Rotor outer = new Rotor(outerRotor.getValue(), outerStart);
        int middleStart = Integer.parseInt(middleStartPos.getText());
        Rotor middle = new Rotor(middleRotor.getValue(), middleStart);        
        int innerStart = Integer.parseInt(innerStartPos.getText());
        Rotor inner = new Rotor(innerRotor.getValue(), innerStart);  
        
        statusBar.setText(statusBar.getText()+"Successfully made all rotors.\n");

        Enigma enigma = new Enigma(outer, middle, inner, plugs.getText(), reflectors.getText(), docToProcess);
        
        statusBar.setText(statusBar.getText()+"Successfully initialized Enigma class...\n");
        enigma.setRotors();
        
        statusBar.setText(statusBar.getText()+"Encrypting.....\n");
        encryptedString = enigma.encryptString();
        outText.setText(encryptedString);
        statusBar.setText(statusBar.getText()+"Successfully encrypted file.\n");
    
    }
    
    
    @FXML
    private void handleOpen(ActionEvent event) throws FileNotFoundException{
        FileChooser fc = new FileChooser();
        fc.setTitle("View the files");
        fc.getExtensionFilters().addAll( new ExtensionFilter("Text Files","*.txt"),new ExtensionFilter("All Files",","));
        fc.setInitialDirectory( new File(System.getProperty("user.home")));

        File file = fc.showOpenDialog(new Stage());
        
        
        if (file != null){
            try (Scanner s = new Scanner(file).useDelimiter("\\Z")) {
                docToProcess = s.next();
                docToProcess = docToProcess.replace("1","ONE");
                docToProcess = docToProcess.replace("2","TWO");   
                docToProcess = docToProcess.replace("3","THREE");
                docToProcess = docToProcess.replace("4","FOUR");
                docToProcess = docToProcess.replace("5","FIVE");
                docToProcess = docToProcess.replace("6","SIX");
                docToProcess = docToProcess.replace("7","SEVEN");
                docToProcess = docToProcess.replace("8","EIGHT");
                docToProcess = docToProcess.replace("9","NINE");
                docToProcess = docToProcess.replace("0","ZERO");
                
                
                inText.setText(docToProcess);
                
  }
                   
 
            catch (IOException e){
                e.printStackTrace();
            }
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        String musicPath = "src/resources/media/LCD Soundsystem - Get Innocuous (Soulwax Remix).mp3";
        Media media = new Media(new File(musicPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        innerRotor.getItems().addAll("I","II","III","IV","V");
        middleRotor.getItems().addAll("I","II","III","IV","V");
        outerRotor.getItems().addAll("I","II","III","IV","V");
        
        innerRotor.getSelectionModel().select("I");
        middleRotor.getSelectionModel().select("V");
        outerRotor.getSelectionModel().select("III");
        
        statusBar.setText("Welcome to the Enigma Machine! This is the status box. \n The default setting is Day 31. \n HOW TO USE: Select either \"Textbox\" or \"File\", put the text into the textbox or load file (if applicable), type the name of the output file (It will be saved in the resources/outputs folder), and then press GO! \n");
        
        

        
        
        // TODO
    }    
    
}
