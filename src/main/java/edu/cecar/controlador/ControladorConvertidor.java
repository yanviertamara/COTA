/**
 *Clase: ControladorConvertidor
 *
 *@version: 0.1
 *
 *Fecha de Creación: 1/04/2021
 *
 *Fecha de Modificación: 
 *
 *@autor: Yanvier
 *
 *Copyright: CECAR
 *
*/
package edu.cecar.controlador;

import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;

import javax.sound.sampled.AudioFileFormat;

/**
 *
 * 
 */
public class ControladorConvertidor {
    public synchronized void convertidorAudio(String fileName, String text){
         System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        // listAllVoices();
        FreeTTS freetts;
        AudioPlayer audioPlayer = null;
        String voiceName = "kevin16";

        /* The VoiceManager manages all the voices for FreeTTS.
         */
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice helloVoice = voiceManager.getVoice(voiceName);

        if (helloVoice == null) {
            System.err.println(
                    "Cannot find a voice named "
                    + voiceName + ".  Please specify a different voice.");
            System.exit(1);
        }

        /* Allocates the resources for the voice.
         */
        helloVoice.allocate();

        /* Synthesize speech.
         */
//create a audioplayer to dump the output file
        audioPlayer = new SingleFileAudioPlayer("audios\\"+fileName, AudioFileFormat.Type.WAVE);
        //attach the audioplayer 
        helloVoice.setAudioPlayer(audioPlayer);

        helloVoice.speak(text);

        /* Clean up and leave.
         */
        helloVoice.deallocate();
//don't forget to close the audioplayer otherwise file will not be saved
        audioPlayer.close();
    }
}
