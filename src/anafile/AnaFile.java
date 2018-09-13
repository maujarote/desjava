/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anafile;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author mjromero
 */
public class AnaFile extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Etiqueta de Ruta
        Label txtRuta = new Label();
        txtRuta.setText("Hola");
        Ruta rut = new Ruta();
        // Total de Archivos
        Label txtTotalFiles = new Label();
        // Total de Archivos de Música
        Label txtTotalFilesMp3 = new Label();
        // Longitud máxima del nombre de archivo
        Label txtLongMax = new Label();
        // Botón de Ruta
        Button btnRuta = new Button();
        btnRuta.setText("Ruta");
        btnRuta.setOnAction((ActionEvent event) -> {
            rut.seleccion(txtRuta);
            txtTotalFiles.setText(String.valueOf(getTotFiles(txtRuta.getText(), 0)));
            txtTotalFilesMp3.setText(String.valueOf(getTotFilesMp3(txtRuta.getText(), 0)));
            txtLongMax.setText(String.valueOf(getLongMax(txtRuta.getText(), 0)));
        });
        // Boton de Salida
        Button btnSalir = new Button();
        btnSalir.setText("Salir");
        btnSalir.setOnAction((ActionEvent event) -> {
            // TODO add your handling code here:
            int resputasPermitidas = JOptionPane.YES_NO_OPTION;
            int respuesta = JOptionPane.showConfirmDialog(null,
                    "Desea salir del sistema?",
                    "Salir", resputasPermitidas, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Botón de recorrido de Árbol
        Button btnAnaDir = new Button();
        btnAnaDir.setText("Recorrer Directorio");
        btnAnaDir.setOnAction((ActionEvent event) -> {
            System.out.println("Analiza Directorio");
            //Inicio
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            try {
                if (validaInput(txtRuta.getText())) {
                    // Conexion a la Base de Datos
                    // Avisa que inicia proceso
                    JOptionPane.showMessageDialog(null, "Espere que el proceso termine...", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Inicio: " + format.format(new Date()) + "\n");
                    // Obtiene la informacion
                    List<ResultadoArchivos> res = rut.BuscaArchivos(txtRuta.getText());
                    // Muestra la informacion
                    int num = 1;
                    for (ResultadoArchivos r : res) {
                        System.out.println("Archivo: " + r.getNomArchivo());
                        System.out.println("Extensión: " + r.getExtArchivo());
                        System.out.println("Ruta Archivo: " + r.getRutaArchivo());
                        System.out.println("Genero: " + r.getGenero());
                        System.out.println("Título de Canción: " + r.getTitulo());
                        
                        num++;
                    }
                    JOptionPane.showMessageDialog(null, "El proceso de busqueda ha terminado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (HeadlessException e) { 
                JOptionPane.showInternalMessageDialog(null, "Error al buscar los archivos.. " + e.getMessage(), "Fatal", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(AnaFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Botón de Muestra
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
            mensaje();
            //              rut.seleccion();
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(btn, 0, 1);
        grid.add(btnRuta, 0, 2);
        grid.add(txtRuta, 1, 2);
        grid.add(txtTotalFiles, 1, 3);
        grid.add(txtTotalFilesMp3, 2, 3);
        grid.add(txtLongMax, 3, 3);
        grid.add(btnSalir, 3, 5);
        grid.add(btnAnaDir, 0, 3);

        Scene scene = new Scene(grid, 500, 450);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void mensaje() {
        System.out.println("Mensaje");
    }

    private boolean validaInput(String ruta) {

        if (ruta.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La ruta es obligatoria.", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    // Obtiene el total de archivos a recorrer
    static int getTotFiles(String ruta, int nTF) {
        String sDirectorio = ruta; //"C:\\Monex\\Fiscal\\Reportes";
        File f = new File(sDirectorio);
        int regreso = nTF;
//        System.out.println(ruta);
        if (f.exists()) {   // Directorio existe 
            for (File fichero : f.listFiles()) {
                // No leemos los folders
                if (fichero.isFile()) {
                    regreso++;
                } else {
                    if (fichero.isDirectory()) {
                        regreso = getTotFiles(fichero.getPath(), regreso);
                    }
                }
            }
        }
//        System.out.println("Total de Archivos: " + nTF);
        return regreso;
    }

    static int getTotFilesMp3(String ruta, int nTF) {
        String sDirectorio = ruta; //"C:\\Monex\\Fiscal\\Reportes";
        File f = new File(sDirectorio);
        int regreso = nTF;
        if (f.exists()) {   // Directorio existe 
            for (File fichero : f.listFiles()) {
                // Es MP3
                if (fichero.getName().toUpperCase().contains(".MP3")) {
                    regreso++;
                } else {
                    if (fichero.isDirectory()) {
                        regreso = getTotFilesMp3(fichero.getPath(), regreso);
                    }
                }
            }
        }
        return regreso;
    }

    static int getLongMax(String ruta, int nLM) {
        String sDirectorio = ruta; //"C:\\Monex\\Fiscal\\Reportes";
        File f = new File(sDirectorio);
        int regreso = nLM;
        if (f.exists()) {   // Directorio existe 
            for (File fichero : f.listFiles()) {
                // Es MP3
                if (fichero.getName().toUpperCase().contains(".MP3")) {
                    if (regreso < fichero.getName().length()){
                        regreso = fichero.getName().length();
                    }
                } else {
                    if (fichero.isDirectory()) {
                        regreso = getTotFilesMp3(fichero.getPath(), regreso);
                    }
                }
            }
        }
        return regreso;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

