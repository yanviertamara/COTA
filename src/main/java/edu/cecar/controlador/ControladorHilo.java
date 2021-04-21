/**
 *Clase: ControladorHilo
 *
 *@version: 0.1
 *
 *Fecha de Creación: 21/04/2021
 *
 *Fecha de Modificación:
 *
 *@autor: Yanvier
 *
 *Copyright: CECAR
 *
 */
package edu.cecar.controlador;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 */
public class ControladorHilo implements Runnable {

    private int op;
    private String search;
    private String nameFile;
    private String extension;

    ControladorArchivos ca = new ControladorArchivos();

    public ControladorHilo(int option, String name, String ext) {
        op = option;
        nameFile = name;
        extension = ext;
    }

    public ControladorHilo(String word, int option, String name, String ext) {
        op = option;
        search = word;
        nameFile = name;
        extension = ext;
    }

    @Override
    public void run() {
        switch (op) {
            case 1:
                ca.convertTextVoice(nameFile, extension);
                break;
            case 2:
                ca.searchTextVoice(search, nameFile, extension);
                break;
        }
    }

}
