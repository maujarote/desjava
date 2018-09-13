/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anafile;

//import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author mjromero
 */
public class Ruta {
    public void seleccion(javafx.scene.control.Label txtRuta){
        // TODO add your handling code here:
        // Inicializamos el dialogo
        JFileChooser fileSelector = new JFileChooser();
        // Establecemos una ruta inicial al abrir el dialogo 
        fileSelector.setCurrentDirectory(new java.io.File("."));
        // Establecemos el titulo del dialogo
        fileSelector.setDialogTitle("Seleccione la ruta en la que hay que buscar...");
        // Indicamos que solo eliga directorios
        fileSelector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // Desactivamos la opcion de todos los archivos
        fileSelector.setAcceptAllFileFilterUsed(false);
        //
        if(fileSelector.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("Directory: "+ fileSelector.getCurrentDirectory());
            System.out.println("FileSelected: "+ fileSelector.getSelectedFile());
            txtRuta.setText(fileSelector.getSelectedFile().getPath());
        } else {
            System.out.println("No seleciono nada...");
        }
    }
    
    public List<ResultadoArchivos> BuscaArchivos(String ruta) throws java.io.IOException {
        List<ResultadoArchivos> resultado = new ArrayList<>();
        ResultadoArchivos resOne;
        String sDirectorio = ruta; //"C:\\Monex\\Fiscal\\Reportes";
        File f = new File(sDirectorio);

        if (f.exists()) {   // Directorio existe 
            for (File fichero : f.listFiles()) {
                // No leemos los folders
                if(fichero.isFile()){
                //    System.out.println(fichero.getName());
                    if (fichero.getName().toUpperCase().contains(".MP3")) {
                    //    System.out.println("Si es MP3");
                        resOne = new ResultadoArchivos();
                        resOne.setNomArchivo(fichero.getName());
                        resOne.setRutaArchivo(fichero.getPath());
                        if (fichero.canWrite()){
                            Metadata metadata = fileProperty(fichero.getAbsolutePath());
                            resOne.setExtArchivo(metadata.get("xmpDM:audioCompressor"));
                            resOne.setTitulo(metadata.get("title"));
                            resOne.setArtista(metadata.get("xmpDM:artist"));
                            resOne.setCompositor(metadata.get("xmpDM:composer"));
                            resOne.setGenero(metadata.get("xmpDM:genre"));
                            resOne.setAlbum(metadata.get("xmpDM:album"));
//                          resOne.setDuracion(Float.parseFloat(metadata.get("xmpDM:duration")));
//                          resOne.setDuracion(Double.parseDouble(metadata.get("xmpDM:duration")));
                            resOne.setDuracion(metadata.get("xmpDM:duration"));
                            resOne.setAnio(metadata.get("xmpDM:releaseDate"));
                            resOne.setTrack(metadata.get("xmpDM:trackNumber"));
                            resOne.setBloqueo("F");
                        }
                        else {
                            resOne.setBloqueo("V");
                        }
                        resultado.add(resOne);
                    }
                } else {
                    if(fichero.isDirectory()){
//                        System.out.println("-->" +fichero.getPath());
                        resultado.addAll(BuscaArchivos(fichero.getPath()));
                    }
                }
            }
        }

        return resultado;
    }
    
    private Metadata fileProperty(String fileLocation) throws java.io.IOException {
        //String fileLocation = "C:\\Users\\mauri\\Downloads\\Cucurrucucu Paloma - Lola Beltran.mp3";

        try {
            InputStream input = new FileInputStream(new File(fileLocation));
            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, handler, metadata, parseCtx);
            input.close();
            // Obtiene informacion directa
/*            System.out.println("----------------------------------------------");
            System.out.println("Titulo: " + metadata.get("title"));
            System.out.println("Artista: " + metadata.get("xmpDM:artist"));
            System.out.println("Compositor : " + metadata.get("xmpDM:composer"));
            System.out.println("Genero : " + metadata.get("xmpDM:genre"));
            System.out.println("Album : " + metadata.get("xmpDM:album"));
            System.out.println("Año: " + metadata.get("xmpDM:releaseDate"));
            System.out.println("Pista : " + metadata.get("xmpDM:trackNumber"));
            System.out.println("Duración : " + metadata.get("xmpDM:duration"));
  */          return metadata;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (SAXException e) {
            e.printStackTrace();
            return null;
        } catch (TikaException e) {
            return null;
        }
 //       return null;
    }
}
