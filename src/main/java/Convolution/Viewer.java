/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Convolution;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Roberto
 */
public class Viewer {

    ControlPanel controlPanel;

    public Viewer(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    //Imagen actual que se ha cargado
    public BufferedImage imageActual;
    public BufferedImage imagenOriginal;

    public BufferedImage getImageActual() {
        return imageActual;
    }

    public void setImageActual(BufferedImage imageActual) {
        this.imageActual = imageActual;
    }

    //Método que devuelve una imagen abierta desde archivo
    //Retorna un objeto BufferedImagen
    public BufferedImage abrirImagen() {
        BufferedImage Imagen;
        
        //Creamos la variable que será devuelta (la creamos como null)
        BufferedImage bmp = null;
        BufferedImage bmp2 = null;
        //Creamos un nuevo cuadro de diálogo para seleccionar imagen
        JFileChooser selector = new JFileChooser();
        //Le damos un título
        selector.setDialogTitle("Seleccione una imagen");
        //Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP & PNG", "jpg", "gif", "bmp","png");
        selector.setFileFilter(filtroImagen);
        //Abrimos el cuadro de diálog
        int flag = selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if (flag == JFileChooser.APPROVE_OPTION) {
            try {
                //Devuelve el fichero seleccionado
                File imagenSeleccionada = selector.getSelectedFile();
                File imagenSeleccionada2 = selector.getSelectedFile();
                //Asignamos a la variable bmp la imagen leida
                bmp = ImageIO.read(imagenSeleccionada);
                bmp2 = ImageIO.read(imagenSeleccionada2);
            } catch (Exception e) {
            }

        }

        //Asignamos la imagen cargada a la propiedad imageActual
        imageActual = bmp;
        imagenOriginal= bmp;

        //Retornamos el valor

        return bmp;
        
    }

    public static BufferedImage copyImage(BufferedImage imageActual) {
        BufferedImage b = new BufferedImage(imageActual.getWidth(), imageActual.getHeight(), imageActual.getType());
        Graphics g = b.getGraphics();
        g.drawImage(imageActual, 0, 0, null);
        g.dispose();
        return b;
    }

    public BufferedImage imagenOriginal() {
        BufferedImage bmp = imageActual;
        imagenOriginal = bmp;
        return bmp;
    }
}
