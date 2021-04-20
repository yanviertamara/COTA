/**
 *Clase: prueba
 *
 *@version: 0.1
 *
 *Fecha de Creación: 31/03/2021
 *
 *Fecha de Modificación:
 *
 *@autor: Yanvier
 *
 *Copyright: CECAR
 *
 */
package edu.cecar.cota;

import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import edu.cecar.controlador.ControladorArchivos;
import edu.cecar.controlador.ControladorConvertidor;
import javax.sound.sampled.AudioFileFormat.Type;

/**
 *
 *
 */
public class prueba {

    /**
     * Simple program to demonstrate the use of the FreeTTS speech synthesizer.
     * This simple program shows how to use FreeTTS without requiring the Java
     * Speech API (JSAPI).
     */
    /**
     * Example of how to list all the known voices.
     */
    public static void main(String[] args) {
       ControladorArchivos ca = new ControladorArchivos();
        ControladorConvertidor cc = new ControladorConvertidor();
        cc.convertidorAudio("salida1","programita");
    }

}
