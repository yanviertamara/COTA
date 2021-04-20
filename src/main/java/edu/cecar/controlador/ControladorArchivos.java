/**
 *Clase: ControladorArchivos
 *
 *@version: 0.1
 *
 *Fecha de Creación: 30/03/2021
 *
 *Fecha de Modificación:
 *
 *@autor: Yanvier
 *
 *Copyright: CECAR
 *
 */
package edu.cecar.controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


/**
 *
 *
 */
public class ControladorArchivos {

    private List<String> paths = new ArrayList();

    public void listFiles(File path) {
        try {
            File[] files = path.listFiles();
            for (File file : files) {
                String nameFile = file.getCanonicalPath();
                if (file.isDirectory()) {
                    System.out.println("directorio:" + nameFile);
                    listFiles(file);
                } else if (nameFile.contains(".txt") || nameFile.contains(".dat") || nameFile.contains(".doc") || nameFile.contains(".docx")) {
                    paths.add(nameFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void convertTextVoice() {   
        ControladorConvertidor cc = new ControladorConvertidor();
        
        for(String nameFile : paths) {
            System.out.println("     archivo:" + nameFile);

            File file = new File(nameFile);
            if (nameFile.contains(".doc")) {
                
                try {
                    InputStream doc = new FileInputStream(file);
                    WordExtractor we = new WordExtractor(doc);                    
                    String text = we.getText();
                    String filename = getFilename(nameFile);
                    cc.convertidorAudio(filename, text);
                } catch (FileNotFoundException ex) {
                    System.out.println("Archivo no encontrado");
                } catch (IOException ex) {
                    System.out.println("Hubo un error al leer el archivo");
                }

            }
            
            if (nameFile.contains(".docx")) {
                try {
                    InputStream docx = new FileInputStream(file);
                    XWPFWordExtractor xwpf_we = new XWPFWordExtractor(new XWPFDocument(docx));                    
                    String text = xwpf_we.getText();
                    String filename = getFilename(nameFile);
                    cc.convertidorAudio(filename, text);
                } catch (FileNotFoundException ex) {
                    System.out.println("Archivo no encontrado");
                } catch (IOException ex) {
                    System.out.println("Hubo un error al leer el archivo");
                }
            }
            
            if (nameFile.contains(".dat")||nameFile.contains(".txt")) {
                String fileContent = "";

                try {
                    //make filereader object to read the file
                    FileReader fileR = new FileReader(nameFile);
                    //create bufferreader to wrap the file 
                    BufferedReader fileStream = new BufferedReader(fileR);

                    String temp = fileStream.readLine();
                    while (temp != null) {
                        fileContent = fileContent + " " + temp;
                        temp = fileStream.readLine();

                    }
                    fileStream.close();
                    String text = temp;
                    String filename = getFilename(nameFile);
                    cc.convertidorAudio(filename, text);
                } catch (FileNotFoundException e) {

                    System.out.println("Archivo no encontrado");
                } catch (IOException e) {
                    System.out.println("Hubo un error al leer el archivo");
                }
            }
            /*if(nameFile.contains(".txt")){
                
            }*/
        }
    }
    
    public synchronized List<File> searchTextVoice(String search) {
        List<File> audios = new ArrayList();
        for(String nameFile : paths) {

            System.out.println("     archivo:" + nameFile);

            File file = new File(nameFile);
            if (nameFile.contains(".doc")) {
                
                try {
                    InputStream doc = new FileInputStream(file);
                    WordExtractor we = new WordExtractor(doc);                    
                    String text = we.getText();
                    if(text.contains(search)){
                        audios.add(searchAudio(nameFile));
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("Archivo no encontrado");
                } catch (IOException ex) {
                    System.out.println("Hubo un error al leer el archivo");
                }

            }
            
            if (nameFile.contains(".docx")) {
                try {
                    InputStream docx = new FileInputStream(file);
                    XWPFWordExtractor xwpf_we = new XWPFWordExtractor(new XWPFDocument(docx));                    
                    String text = xwpf_we.getText();
                    if(text.contains(search)){
                        audios.add(searchAudio(nameFile));
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("Archivo no encontrado");
                } catch (IOException ex) {
                   System.out.println("Hubo un error al leer el archivo");
                }
            }
            
            if (nameFile.contains(".dat")||nameFile.contains(".txt")) {
                String fileContent = "";

                try {
                    //make filereader object to read the file
                    FileReader fileR = new FileReader(nameFile);
                    //create bufferreader to wrap the file 
                    BufferedReader fileStream = new BufferedReader(fileR);

                    String temp = fileStream.readLine();
                    while (temp != null) {
                        fileContent = fileContent + " " + temp;
                        temp = fileStream.readLine();

                    }
                    fileStream.close();
                    String text = temp;
                    if(text.contains(search)){
                        audios.add(searchAudio(nameFile));
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Archivo no encontrado");
                } catch (IOException e) {
                    System.out.println("Hubo un error al leer el archivo");
                }
            }
            /*if(nameFile.contains(".txt")){
                
            }*/
        }
        return audios;
    }
    
    public String getFilename(String path){
		int i = path.lastIndexOf('\\');
		String fe = path.substring(i+1);                
                String fn[] = fe.split("\\.");
                String name = fn[0];
                return name;
    }
    
    public File searchAudio(String audioName){
        File audios[] = new File("audios").listFiles();
        for (File audio : audios) {
            String name = audio.getName();
            if(name.contains(audioName)){
                return audio;
            }
        }
        return null;
    }
}
