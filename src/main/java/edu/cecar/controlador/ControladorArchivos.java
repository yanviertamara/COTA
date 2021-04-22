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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 *
 */
public class ControladorArchivos {

    private List<String> paths = new ArrayList();


    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }
 
    

    public void listFiles(File path) {
        try {
            File[] files = path.listFiles();
            for (File file : files) {
                String nameFile = file.getCanonicalPath();
                if (file.isDirectory()) {
                    System.out.println("directorio:" + nameFile);
                    listFiles(file);
                } else if (nameFile.contains(".txt") || nameFile.contains(".dat") || nameFile.contains(".doc") || nameFile.contains(".docx")) {
                    System.out.println("     archivo:" + nameFile);
                    paths.add(nameFile);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado un directorio valido");
        }
    }

    public synchronized void convertTextVoice(String nameFile, String extension) {

        ControladorConvertidor cc = new ControladorConvertidor();

        File file = new File(nameFile);

        String filename = getFilename(nameFile);
        String text = "";
        if (extension.equals("docx")) {
            System.out.println("     leyendo:" + nameFile + " Extension:" + extension);
            try {
                InputStream docx = new FileInputStream(file);
                XWPFWordExtractor xwpf_we = new XWPFWordExtractor(new XWPFDocument(docx));
                text = xwpf_we.getText();
                System.out.println("     leido:" + nameFile + " Extension:" + extension);
            } catch (FileNotFoundException ex) {
                System.out.println("Archivo no encontrado");
            } catch (IOException ex) {
                System.out.println("Hubo un error al leer el archivo");
            }
        }

        if (extension.equals("doc")) {
            System.out.println("     leyendo:" + nameFile + " Extension:" + extension);
            try {
                InputStream doc = new FileInputStream(file);
                WordExtractor we = new WordExtractor(doc);
                text = we.getText();
                filename = getFilename(nameFile);
                System.out.println("     leido:" + nameFile + " Extension:" + extension);
            } catch (FileNotFoundException ex) {
                System.out.println("Archivo no encontrado");
            } catch (IOException ex) {
                System.out.println("Hubo un error al leer el archivo");
            }
        }

        if (extension.equals("txt")) {
            System.out.println("     leyendo:" + nameFile + " Extension:" + extension);
            Scanner scanner;
            try {
                //se pasa el flujo al objeto scanner
                scanner = new Scanner(file);
                String content = "";
                while (scanner.hasNextLine()) {
                    // el objeto scanner lee linea a linea desde el archivo
                    String linea = scanner.nextLine();
                    content = content + " " + linea;

                }
                //se cierra el ojeto scanner
                scanner.close();
                filename = getFilename(nameFile);
                text = content;
                System.out.println("     leido:" + nameFile + " Extension:" + extension);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (extension.equals("dat")) {
            System.out.println("     leyendo:" + nameFile + " Extension:" + extension);
            FileReader f = null;
            try {
                String content = "";
                String cadena;
                f = new FileReader(nameFile);
                BufferedReader b = new BufferedReader(f);
                while ((cadena = b.readLine()) != null) {
                    System.out.println(cadena);
                    content = content + " " + cadena;

                }
                b.close();

                filename = getFilename(nameFile);
                text = content;
                System.out.println("     leido:" + nameFile + " Extension:" + extension);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorArchivos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ControladorArchivos.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    f.close();
                } catch (IOException ex) {
                    Logger.getLogger(ControladorArchivos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        cc.convertidorAudio(filename, text);
    }

    public synchronized void searchTextVoice(String search, String nameFile, String extension) {

        System.out.println("     archivo:" + nameFile);
        String filename = getFilename(nameFile);
        File file = new File(nameFile);
        if (extension.equals("doc")) {

            try {
                InputStream doc = new FileInputStream(file);
                WordExtractor we = new WordExtractor(doc);
                String text = we.getText();
                if (text.contains(search)) {
                    System.out.println("Encontrado archivo: "+ nameFile);
                    searchAudio(nameFile,filename);
                    
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Archivo no encontrado");
            } catch (IOException ex) {
                System.out.println("Hubo un error al leer el archivo");
            }

        }

        if (extension.equals("docx")) {
            try {
                InputStream docx = new FileInputStream(file);
                XWPFWordExtractor xwpf_we = new XWPFWordExtractor(new XWPFDocument(docx));
                String text = xwpf_we.getText();
                if (text.contains(search)) {
                    System.out.println("Encontrado archivo: "+ nameFile);
                    searchAudio(nameFile,filename);
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Archivo no encontrado");
            } catch (IOException ex) {
                System.out.println("Hubo un error al leer el archivo");
            }
        }

        if (extension.equals("txt")) {
            Scanner scanner;
            try {
                //se pasa el flujo al objeto scanner
                scanner = new Scanner(file);
                String content = "";
                while (scanner.hasNextLine()) {
                    // el objeto scanner lee linea a linea desde el archivo
                    String linea = scanner.nextLine();
                    content = content + " " + linea;

                }
                //se cierra el ojeto scanner
                scanner.close();
                String text = content;
                if (text.contains(search)) {
                    System.out.println("Encontrado archivo: "+ nameFile);
                    searchAudio(nameFile,filename);
                }                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (extension.equals("dat")) {
            FileReader f = null;
            try {
                String content = "";
                String cadena;
                f = new FileReader(nameFile);
                BufferedReader b = new BufferedReader(f);
                while ((cadena = b.readLine()) != null) {
                    System.out.println(cadena);
                    content = content + " " + cadena;

                }
                b.close();

                String text = content;
                if (text.contains(search)) {
                    System.out.println("Encontrado archivo: "+ nameFile);
                    searchAudio(nameFile,filename);
                }                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorArchivos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ControladorArchivos.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    f.close();
                } catch (IOException ex) {
                    Logger.getLogger(ControladorArchivos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String getFilename(String path) {
        int i = path.lastIndexOf('\\');
        String fe = path.substring(i + 1);
        String fn[] = fe.split("\\.");
        String name = fn[0];
        return name;
    }

    public void searchAudio(String path, String filename) {
        File dir[] = new File("audios").listFiles();
        for (File audio : dir) {
            String name = getFilename(audio.getName());
            if (name.contains(filename)) {                
                try {
                    copiarArchivo(audio.getCanonicalPath(),audio.getName());
                } catch (IOException ex) {
                    Logger.getLogger(ControladorArchivos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }
    
     public void copiarArchivo(String origenArchivo, String fileName) {
                File origen = new File(origenArchivo);
                File destino = new File("resultados\\"+fileName);

                try {
                        InputStream in = new FileInputStream(origen);
                        OutputStream out = new FileOutputStream(destino);

                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = in.read(buf)) > 0) {
                                out.write(buf, 0, len);
                        }

                        in.close();
                        out.close();
                } catch (IOException ioe){
                        ioe.printStackTrace();
                }

        }
}
