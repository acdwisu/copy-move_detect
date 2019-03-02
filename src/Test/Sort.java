/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Array.ArrayComparator;
import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import pradwt.FeaturesBlock;

/**
 *
 * @author Dolores Pot Hol
 */
public class Sort {
    public static void main(String args[]) {
        new Sort().run4();
    }
    
    private void run4() {
        System.out.println(new Point(12212,1212).equals(new Point(12212,1212)));
//        LinkedList<Point> p = new LinkedList();
//        
//        p.add(new Point(1,2));
//        p.add(new Point(2,2));
//        p.add(new Point(1,2));
//        p.add(new Point(2,2));
//        
//        
//        Enumeration<Point> e = Collections.enumeration(p);
//        
//        while(e.hasMoreElements()) {
//            System.out.println(e.nextElement().toString());
//        }
    }
    
    private void run3() {
        FeaturesBlock[] fbs = new FeaturesBlock[] {
            new FeaturesBlock(new Double[]{1.0,2.0,3.0}, new Point(1,2)),
            new FeaturesBlock(new Double[]{3.0,2.0,3.0}, new Point(2,2)),
            new FeaturesBlock(new Double[]{2.0,2.0,3.0}, new Point(2,1)),
        };
        
        Arrays.sort(fbs);
        
        System.out.println(fbs[0].toString());
        System.out.println(fbs[1].toString());
        System.out.println(fbs[2].toString());
    }
    
    private void run0() {
        Double[] d = new Double[]{1.0,2.0,3.0};
        Double[] e = new Double[]{1.0,2.0,3.0};
        Double[] f = new Double[]{1.0,2.0,1.0};
        Double[] g = new Double[]{3.0,2.0,1.0};
        
        System.out.println(Arrays.toString(d).compareTo(Arrays.toString(g)));
    }
    
    private void run() {
        Double[][] arr = new Double[][]{
            {1.3, 1.2, 1.6, 1.3, 1.2},
            {1.1, 1.2, 1.3, 1.3, 1.3},
            {1.1, 1.2, 1.1, 1.1, 1.1},            
            {1.3, 1.1, 1.6, 1.3, 1.1}
        };
        
        Arrays.sort(arr, new ArrayComparator<>());
        
        print(arr);
    }
    
    private void run2() {
        Double[][][] arr = new Double[][][]{
            {
                {1.1, 1.2, 1.3, 1.3, 1.3},
                {1.1, 1.2, 1.1, 1.1, 1.1},
                {1.3, 1.1, 1.6, 1.3, 1.2},
                {1.3, 1.1, 1.6, 1.3, 1.1}
            }, {
                {1.3, 1.1, 1.6, 1.3, 1.2},
                {1.3, 1.1, 1.6, 1.3, 1.1},
                {1.1, 1.2, 1.3, 1.3, 1.3},
                {1.1, 1.2, 1.1, 1.1, 1.1}                
            }, {
                {1.1, 1.1, 1.6, 1.3, 1.1},
                {1.1, 1.2, 1.3, 1.3, 1.3},                
                {1.3, 1.1, 1.6, 1.3, 1.2},
                {1.1, 1.2, 1.1, 1.1, 1.1}
            }, {
                {1.3, 1.1, 1.6, 1.3, 1.2},
                {1.3, 1.1, 1.6, 1.3, 1.1},
                {1.1, 1.2, 1.3, 1.3, 1.6},
                {1.1, 1.2, 1.1, 1.1, 1.1}                
            }, {
                {1.3, 1.1, 1.6, 1.3, 1.1},
                {1.1, 1.2, 1.3, 1.3, 1.3},                
                {1.3, 1.1, 1.6, 1.3, 1.4},
                {1.1, 1.2, 1.1, 1.1, 1.1}
            }, {
                {1.3, 1.1, 1.6, 1.3, 1.2},
                {1.3, 1.1, 1.6, 1.3, 1.1},
                {1.1, 1.2, 1.3, 1.3, 1.3},
                {1.1, 1.2, 1.1, 1.1, 1.1}                
            }, {
                {1.3, 1.1, 1.6, 1.3, 1.1},
                {1.1, 1.2, 1.3, 1.3, 1.3},                
                {1.3, 1.1, 1.6, 1.3, 1.1},
                {1.1, 1.2, 1.1, 1.1, 1.4}
            }, {
                {1.3, 1.1, 1.6, 1.3, 1.1},
                {1.1, 1.2, 1.3, 1.3, 1.3},                
                {1.3, 1.1, 1.6, 1.3, 1.1},
                {1.1, 1.2, 1.1, 1.1, 1.3}
            }, {
                {1.3, 1.1, 1.6, 1.3, 1.1},
                {1.1, 1.2, 1.3, 1.3, 1.3},                
                {1.3, 1.1, 1.6, 1.3, 1.1},
                {1.1, 1.2, 1.1, 1.1, 1.2}
            }            , {
                {1.3, 1.1, 1.6, 1.3, 1.1},
                {1.1, 1.2, 1.3, 1.3, 1.3},                
                {1.3, 1.1, 1.6, 1.3, 1.1},
                {1.1, 1.2, 1.1, 1.1, 1.1}
            }                                    
        };
        
        Double[][] arrMod = to1DArray(arr);
        
        Arrays.sort(arrMod, new ArrayComparator());
        
        print(arrMod);
    }
    
    private Double[][] to1DArray(Double[][][] arr) {
        Double[][] result = new Double[arr.length][arr[0].length*arr[0][0].length];
        
        for(int n=0; n<arr.length; n++) {
            for(int i=0, a=0; i<arr[0].length; i++) {
                for(int j=0; j<arr[0][0].length; j++, a++) {
                    result[n][a] = arr[n][i][j];
                }
            }
        }
        
        return result;
    }
    
    private void print(Double[][] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                System.out.print(arr[i][j]+ "  ");
            }
            System.out.println("");
        }
    }
}
