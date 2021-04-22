/**
 *Clase: Main
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
package edu.cecar.vista;

import edu.cecar.controlador.ControladorArchivos;
import edu.cecar.controlador.ControladorHilo;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FileUtils;

/**
 *
 *
 */
public class Main extends javax.swing.JFrame {

    private ControladorArchivos ca = new ControladorArchivos();
    private ControladorHilo ch = new ControladorHilo();
    private List<String> paths = ca.getPaths();
    private File audios[];

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pathTxt = new javax.swing.JTextField();
        openFolderBtn = new javax.swing.JButton();
        processBtn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        searchTxt = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        searchBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        audiosJtb = new javax.swing.JTable();
        playBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        stopBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COTA");

        jLabel1.setText("Path: ");

        pathTxt.setEnabled(false);

        openFolderBtn.setText("Abrir carpeta");
        openFolderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFolderBtnActionPerformed(evt);
            }
        });

        processBtn.setText("Procesar");
        processBtn.setEnabled(false);
        processBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processBtnActionPerformed(evt);
            }
        });

        jButton3.setText("Ver audios");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar palabra: ");

        searchBtn.setText("Buscar");
        searchBtn.setEnabled(false);
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        audiosJtb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        audiosJtb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                audiosJtbMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(audiosJtb);

        playBtn.setText("Play");
        playBtn.setEnabled(false);

        updateBtn.setText("Actualizar");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        stopBtn.setText("Detener");
        stopBtn.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(processBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pathTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(openFolderBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchBtn))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(updateBtn))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(playBtn)
                                            .addComponent(stopBtn))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pathTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openFolderBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(processBtn)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(searchBtn)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(updateBtn)
                        .addGap(55, 55, 55)
                        .addComponent(playBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopBtn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void llenarTabla() {
        int listLength = audios.length;

        String matriz[][] = new String[listLength][2];
        for (int i = 0; i < audios.length; i++) {
            matriz[i][0] = audios[i].getName();
        }
        audiosJtb.setModel(new DefaultTableModel(
                matriz, new String[]{
                    "Audios"
                }
        ));

    }

    private void openFolderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFolderBtnActionPerformed
        JFileChooser fc = new JFileChooser();
        File path = null;

        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int respuesta = fc.showOpenDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            File archivoElegido = fc.getSelectedFile();
            pathTxt.setText(archivoElegido.getAbsolutePath());
            path = archivoElegido;
        }

        ca.listFiles(path);

        if (!paths.isEmpty()) {
            processBtn.setEnabled(true);
        }
    }//GEN-LAST:event_openFolderBtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            Runtime.getRuntime().exec("explorer.exe /open, audios");
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void processBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processBtnActionPerformed
        // TODO add your handling code here:
        searchBtn.setEnabled(false);
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);

        for (String fileName : paths) {

            //System.out.println("path:"+fileName);
            String fn[] = fileName.split("\\.");
            String extension = fn[1];

            ControladorHilo hilo = new ControladorHilo(1, fileName, extension);
            executor.execute(hilo);
        }
        executor.shutdown();
        searchBtn.setEnabled(true);
    }//GEN-LAST:event_processBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        try {
            // TODO add your handling code here:
            File eliminar = new File("resultados");
            FileUtils.deleteDirectory(eliminar);

            File crear = new File("resultados");
            if (crear.mkdirs()) {
                System.out.println("creada");
            }

            processBtn.setEnabled(false);
            String search = searchTxt.getText();
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
            for (String fileName : paths) {

                String fn[] = fileName.split("\\.");
                String extension = fn[1];
                ControladorHilo hilo = new ControladorHilo(2, search, fileName, extension);
                executor.execute(hilo);
            }
            executor.shutdown();
            processBtn.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Operación completada... Presione Actualizar");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_searchBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:        
        audios = new File("resultados").listFiles();
        if (audios != null || audios.length != 0) {
            llenarTabla();
        }
        if (audios == null || audios.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay resultados para mostrar");
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void audiosJtbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_audiosJtbMouseClicked
        // TODO add your handling code here:
        int seleccion = audiosJtb.rowAtPoint(evt.getPoint());
        playBtn.setEnabled(true);
        stopBtn.setEnabled(false);
        String name = String.valueOf(audiosJtb.getValueAt(seleccion, 0));
        String path = "";
        for (File audio : audios) {
            String audioName = audio.getName();
            if (audioName.equals(name)) {
                try {
                    path = audio.getCanonicalPath();
                    break;
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        File audio = new File(path);

        try {
            AudioInputStream input = AudioSystem.getAudioInputStream(audio);
            Clip clip = AudioSystem.getClip();
            clip.open(input);
            //long clipTimePosition = clip.getMicrosecondPosition();
            playBtn.addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    clip.start();
                    playBtn.setEnabled(false);                    
                    stopBtn.setEnabled(true);
                }

            });        
            stopBtn.addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    clip.stop();
                    stopBtn.setEnabled(false);
                    playBtn.setEnabled(true);
                }
            });
        } catch (UnsupportedAudioFileException ex) {

        } catch (IOException ex) {

        } catch (LineUnavailableException ex) {

        }


    }//GEN-LAST:event_audiosJtbMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable audiosJtb;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton openFolderBtn;
    private javax.swing.JTextField pathTxt;
    private javax.swing.JButton playBtn;
    private javax.swing.JButton processBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JButton stopBtn;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables

}
