/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pradwt;

import Dekomposisi.DWT;
import Jama.Matrix;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author M.Hakim Amransyah
 */
public class PraDWT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        JFileChooser chooser = new JFileChooser();
        if(chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION){          
            File f = chooser.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(f);
                CitraWarna warna = new CitraWarna(img);
                CitraKeabuan citra = new Pengolahan().doGrayScale(warna);      
                DWT dwt = new DWT();
                CitraKeabuan citra_hasil = new CitraKeabuan(dwt.dekomposisi(citra.getP()));
                CitraKeabuan citra_LL = new CitraKeabuan(dwt.getLL());
                CitraKeabuan citra_LH = new CitraKeabuan(dwt.getLH());
                CitraKeabuan citra_HL = new CitraKeabuan(dwt.getHL());
                CitraKeabuan citra_HH = new CitraKeabuan(dwt.getHH());
                create(citra_hasil.getImg(),"Hasil");
                create(citra_LL.getImg(),"LL");
                create(citra_LH.getImg(),"LH");
                create(citra_HL.getImg(),"HL");
                create(citra_HH.getImg(),"HH");
                
                Matrix m = new Matrix(dwt.getHHDouble());
                Matrix U = m.svd().getU();
                Matrix S = m.svd().getS();
                Matrix V = m.svd().getV().transpose();
                
                
            } catch (IOException ex) {
                Logger.getLogger(PraDWT.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }else{
            
        }
    }
    
    public static void create(BufferedImage img,String nfile){
        try {    
            ImageIO.write(img, "jpg", new File("E:\\"+nfile+".jpg"));
        } catch (IOException ex) {
            Logger.getLogger(PraDWT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void cetakImage(double[][] img){
        for(int i=0;i<img.length;i++){
            for(int j=0;j<img[i].length;j++){
                System.out.print(img[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
}
