/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Convolution;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Roberto
 */
public class Convolution {

    Viewer viewer;
    ControlPanel controlPanel;
    private float[][] kernel;
    private float kernelDiv = 0;

    public Convolution(Viewer viewer, ControlPanel controlPanel) {
        this.viewer = viewer;
        this.controlPanel = controlPanel;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public void pixelImage() {
        for (int i = 0; i < viewer.getImageActual().getHeight(); i++) {
            for (int j = 0; j < viewer.getImageActual().getHeight(); j++) {
                int pixel = viewer.getImageActual().getRGB(i, j);
                //Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                //Retrieving the R G B values
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
            }
        }
    }

//    public void pixelImage() {
//        int [] pixel;
//        for (int y = 0; y < viewer.getImageActual().getHeight(); y++) {
//            for (int x = 0; x < viewer.getImageActual().getHeight(); x++) {
//                pixel = viewer.getImageActual().getRaster().getPixel(x,y,new int[3]);
////                pixel = viewer.getImageActual().getRGB(x, y);
//                //Creating a Color object from pixel value
//                Color color = new Color(pixel, true);
//                //Retrieving the R G B values
//                int red = color.getRed();
//                int green = color.getGreen();
//                int blue = color.getBlue();
//            }
//        }
//    }
    public BufferedImage escalaGrises() {
        //Variables que almacenarán los píxeles
        int mediaPixel, colorSRGB;
        Color colorAux;

        //Recorremos la imagen píxel a píxel
        for (int i = 0; i < viewer.getImageActual().getHeight(); i++) {
            for (int j = 0; j < viewer.getImageActual().getHeight(); j++) {
                //Almacenamos el color del píxel
                colorAux = new Color(this.viewer.getImageActual().getRGB(i, j));
                //Calculamos la media de los tres canales (rojo, verde, azul)
                mediaPixel = (int) ((colorAux.getRed() + colorAux.getGreen() + colorAux.getBlue()) / 3);
                //Cambiamos a formato sRGB
                colorSRGB = (mediaPixel << 16) | (mediaPixel << 8) | mediaPixel;
                //Asignamos el nuevo valor al BufferedImage
                viewer.getImageActual().setRGB(i, j, colorSRGB);
            }
        }
        //Retornamos la imagen
        return viewer.getImageActual();
    }

    public void obtenerRGB() {

        //Retornamos el valor
        int alto = viewer.getImageActual().getHeight();
        int ancho = viewer.getImageActual().getWidth();
        System.out.println("Alto " + alto + "ancho" + ancho);

        for (int y = 0; y < viewer.getImageActual().getHeight(); y++) {
            for (int x = 0; x < viewer.getImageActual().getWidth(); x++) {
                int srcPixel = viewer.getImageActual().getRGB(x, y);

                Color c = new Color(srcPixel);

                int valR = c.getRed();
                int valG = c.getGreen();
                int valB = c.getBlue();

                System.out.println(" r " + valR + " g " + valG + " b " + valB);
            }
            System.out.println("");
        }
    }

    public void blur() {
        kernel = new float[][]{
            {2, 1, 2},
            {1, -1, 1},
            {2, 1, 2}
        };
        kernelDiv = 11;
    }

    public void sharpening() {
        kernel = new float[][]{
            {0, -1, 0},
            {-1, 5, -1},
            {0, -1, 0}
        };
        kernelDiv = 1;
    }

    public void smoothing() {
        kernel = new float[][]{
            {1, 2, 1},
            {2, 4, 2},
            {1, 2, 1}
        };
        kernelDiv = 16;
    }

    public void raised() {
        kernel = new float[][]{
            {0, 0, -2},
            {0, 2, 0},
            {1, 2, 0}
        };
        kernelDiv = 1;
    }

    public void outline() {
        kernel = new float[][]{
            {-1, -1, -1},
            {-1, 8, -1},
            {-1, -1, -1}
        };
        kernelDiv = 1;
    }

    public void emboss() {
        kernel = new float[][]{
            {-2, -1, 0},
            {-1, 1, 1},
            {0, 1, 2}
        };
        kernelDiv = 1;
    }

}
