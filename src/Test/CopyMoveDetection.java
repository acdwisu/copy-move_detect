/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Array.ArrayComparator;
import Array.ArrayHandle;
import Dekomposisi.DWT;
import Jama.Matrix;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import pradwt.CitraKeabuan;
import pradwt.CitraWarna;
import pradwt.FeaturesBlock;
import pradwt.PraDWT;
import pradwt.Pengolahan;

/**
 *
 * @author Dolores Pot Hol
 */
public class CopyMoveDetection {
      /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        new CopyMoveDetection().run1();
    }
    
    private void run1() {
        int Nn = 5,
            Nf = 50,
            widthOverlapBlock = 8,
            heightOverlapBlock = 8;
                
        try {
            BufferedImage img = ImageIO.read(new File("Dataset/im12_t.bmp"));

            CitraWarna warna = new CitraWarna(img);
            CitraKeabuan citra = new Pengolahan().doGrayScale(warna);      

            DWT dwt = new DWT();
            dwt.dekomposisi(citra.getP());
                        
            Pengolahan p = new Pengolahan();
            ArrayHandle a = new ArrayHandle();
            
            double[][] matrixLL = dwt.getLLDouble();

            // Detection using DWT
//            double[][][] dwtFeatures2D = p.getOverlappedFeature(matrixLL, heightOverlapBlock, widthOverlapBlock);
//            
//            double[][] dwtFeatures1D = a.arr1DArraysTo1DArray(dwtFeatures2D);
//            
//            Double[][] dwtFeatures = a.toDouble(dwtFeatures1D);
//            
//            Arrays.sort(dwtFeatures, new ArrayComparator());
            
            FeaturesBlock[] overlappedBlockFeatures = p.getOverlappedFeatureBlock(matrixLL, heightOverlapBlock, widthOverlapBlock);
            
            LinkedList<Point[]> coupled = new LinkedList();
            LinkedList<Point> distances = new LinkedList();
            
            Arrays.sort(overlappedBlockFeatures);
            
            System.out.println("loop 1");
//            System.out.println("max "+overlappedBlockFeatures.length);
            for(int i=0; i<overlappedBlockFeatures.length-1; i++) {
//                System.out.println("overlappedBlockFeatures "+i);
                for(int j=i+1; j<i+Nn; j++) {
                    if(j==overlappedBlockFeatures.length-1) break;
                    
                    if(overlappedBlockFeatures[i].compareTo(overlappedBlockFeatures[j]) == 0) {
                        coupled.add(new Point[]{
                            overlappedBlockFeatures[i].getStartPoint(),overlappedBlockFeatures[j].getStartPoint()
                        });
                     
                        int xi = overlappedBlockFeatures[i].getStartPoint().x,
                            xj = overlappedBlockFeatures[j].getStartPoint().x;
                        int yi = overlappedBlockFeatures[i].getStartPoint().y,
                            yj = overlappedBlockFeatures[j].getStartPoint().y;
                    
                        distances.add(new Point(Math.abs(xi-xj), Math.abs(yi-yj)));
                    }                                        
                }
            }
            
            
            
            HashMap<String,Integer> frequent = new HashMap();
            
            System.out.println("loop 2");
            
            for(Point distance : distances) {
                String key = distance.x+","+distance.y;
                
                if(frequent.containsKey(key)) {
                    frequent.replace(key, frequent.get(key)+1);
                } else {
                    frequent.put(key, 0);
                }
            }
            
            LinkedList<String> toDelete = new LinkedList();
            
            System.out.println("loop 3");
            
            for(String key : frequent.keySet()) {
                if(frequent.get(key) < Nf) {
                    toDelete.add(key);
                }
            }
            
            System.out.println("loop 4");
            
            for(String td : toDelete) {
                frequent.remove(td);
            }
            
            System.out.println("loop 5");
            
            for(int i=distances.size()-1; i>=0; i--) {
                boolean delete = true;
                
                for(String key : frequent.keySet()) {
                    if((distances.get(i).x+","+distances.get(i).y).equals(key))
                        delete = false;
                }
                
                if(delete) {
                    distances.remove(i);
                    coupled.remove(i);
                }
                
//                System.out.println(i+ " " +delete);
            }
            
            System.out.println("fin");
            
            BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

            Graphics2D g = result.createGraphics();
            
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, result.getWidth(), result.getHeight());                       
            
            for(Point[] couple : coupled) {
                result.setRGB(couple[0].x, couple[0].y, Color.WHITE.getRGB());
                result.setRGB(couple[1].x, couple[1].y, Color.WHITE.getRGB());
            }
            
            ImageIO.write(result, "bmp", new File("Dataset/hasil/aaa9.bmp"));
            
            // Detection using DWT + SVD


        } catch (IOException ex) {
            Logger.getLogger(PraDWT.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }
    
    private void testOverlappedBlock() {
        double[][] arr = new double[][]{
            {1.3, 1.2, 1.6, 1.3, 1.2},
            {1.1, 1.2, 1.3, 1.3, 1.3},
            {1.1, 1.2, 1.1, 1.1, 1.1},            
            {1.3, 1.1, 1.6, 1.3, 1.1}
        };
        
        Pengolahan p = new Pengolahan();
        
        double[][][] arrOverlapped = p.getOverlappedFeature(arr, 2, 2);
        
        for(int arrN=0; arrN<arrOverlapped.length; arrN++) {
            for(int i=0; i<arrOverlapped[0].length; i++) {
                for(int j=0; j<arrOverlapped[0][0].length; j++) {
                    System.out.print(arrOverlapped[arrN][i][j] + " ");
                }
                System.out.println("");
            }
            System.out.println("--------");
        }
    }
    
    private void create(BufferedImage img,String nfile){
        try {    
            ImageIO.write(img, "jpg", new File("E:\\"+nfile+".jpg"));
        } catch (IOException ex) {
            Logger.getLogger(PraDWT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cetakImage(double[][] img){
        for(int i=0;i<img.length;i++){
            for(int j=0;j<img[i].length;j++){
                System.out.print(img[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
}
