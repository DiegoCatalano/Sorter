// Sorter
// Comparasion among sort
//
// Copyright © Diego Catalano, 2013
// diego.catalano at live.com
//
//
//    This program is free software; you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation; either version 2 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program; if not, write to the Free Software
//    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//

package Tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tool for creation automaically of vectors.
 * Allow to save the vector using serialization.
 * @author Diego Catalano
 */
public final class VectorUtil implements Serializable{
    
    private static Random r = new Random();

    /**
     * Initializes a new instance of the VectorUtil class.
     */
    public VectorUtil() {}
    
    /**
     * Initializes a new instance of the VectorUtil class.
     * @param seed Random seed generator.
     */
    public VectorUtil(long seed){
        setSeed(seed);
    }
    
    public void setSeed(long seed){
        r.setSeed(seed);
    }
    
    /**
     * Generate random vector.
     * Eg. with size of 10. {5,7,6,6,2,0,2,1,4,9}.
     * @param size Size of array.
     * @return Array.
     */
    public static int[] GenerateRandomVector(int size){
        return GenerateRandomVector(size, true);
    }
    
    /**
     * Generate random vector.
     * Eg. with size of 10 and canRepeat = false. {5,7,6,8,2,0,3,1,4,9}.
     * @param size Size of array.
     * @return Array.
     */
    public static int[] GenerateRandomVector(int size, boolean canRepeat){
        
        int[] v = new int[size];
        if(canRepeat){
            for (int i = 0; i < size; i++) {
                v[i] = r.nextInt(size);
            }
        }
        else{
            v = GenerateSortedVector(size);
            Shuffle(v);
        }
        
        return v;
        
    }
    
    /**
     * Generate sorted vector.
     * Eg. with size 10 {0,1,2,3,4,5,6,7,8,9}
     * @param size Size of array.
     * @return Array.
     */
    public static int[] GenerateSortedVector(int size){
        return GenerateSortedVector(size, false);
    }
    
    /**
     * Generate sorted vector.
     * Eg. with size 10 and invert = true. {9,8,7,6,5,4,3,2,1,0}
     * @param size Size of array.
     * @param invert Invert array.
     * @return Array.
     */
    public static int[] GenerateSortedVector(int size, boolean invert){
        
        int[] v = new int[size];
        for (int i = 0; i < size; i++) {
            v[i] = i;
        }
        
        if (invert) return InvertVector(v);
        
        return v;
    }
    
    /**
     * Generate partial sorted vector.
     * Size sorted is definied as size / 2.
     * Eg. {1,2,3,4,5,9,6,7,0,8}
     * @param size Size of array.
     * @return Array.
     */
    public static int[] GeneratePartialSortedVector(int size){
        return GeneratePartialSortedVector(size, size / 2);
    }
    
    /**
     * Generate partial sorted vector with no respect the order of sort.
     * 
     * Eg. {0,2,1,3,4,5,6,7,8,9} with times equal one.
     * 
     * @param size Size of array.
     * @param times Times to perform shuffle.
     * @return Array.
     */
    public static int[] GeneratePartialSortedVectorShuffle(int size, int times){
        
        int[] v = GenerateSortedVector(size);
        Shuffle(v, times);
        return v;
        
    }
    
    /**
     * Generate partial sorted vector.
     * Eg.  {1,2,3,4,5,9,6,7,0,8}
     * @param size Size of array.
     * @return Array.
     */
    public static int[] GeneratePartialSortedVector(int size, int sizeSorted){
        
        if (sizeSorted > size)
            throw new IllegalArgumentException("size must be higher than sizeSorted");
        
        int[] v = new int[size];
        
        for (int i = 0; i < sizeSorted; i++) {
            v[i] = i;
        }
        
        for (int i = sizeSorted; i < size; i++) {
            v[i] = r.nextInt(size);
        }
        
        return v;

    }
    
    private static int[] InvertVector(int v[]){
        
        int[] a = new int[v.length];
        
        for (int i = 0; i < v.length; i++) {
            a[i] = v[v.length - 1 - i];
        }
        
        return a;
        
    }
    
    /**
     * Verify if the vector is sorted.
     * @param v Vector.
     * @return True if is sorted, otherwise return false.
     */
    public static boolean isSorted(int[] v){
        for (int i = 1; i < v.length; i++) {
            if (v[i] < v[i - 1]) return false;
        }
        return true;
    }
    
    /**
     * Shuffle an array.
     * @param v Array.
     */
    public static void Shuffle(int[] v) {
        Shuffle(v, v.length);
    }
    
    /**
     * Shuffle an array.
     * @param v Array.
     */
    public static void Shuffle(int[] v, int times) {
      int n = Math.max(1, Math.min(v.length, times));
      Random random = new Random();
      random.nextInt();
      for (int i = 0; i < n; i++) {
        int change = i + random.nextInt(v.length - i);
        swap(v, i, change);
      }
    }

    private static void swap(int[] v, int i, int change) {
      int helper = v[i];
      v[i] = v[change];
      v[change] = helper;
    }
    
    /**
     * Read a file and loads the vector.
     * @param filename Path contains a file.
     * @return Vector.
     */
    public static int[] ReadFile(String filename){
        int[] v;
        
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            v = (int[])in.readObject();
            return v;
        }
        catch (IOException ex) {
            Logger.getLogger(VectorUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(ClassNotFoundException cl){
            Logger.getLogger(VectorUtil.class.getName()).log(Level.SEVERE, null, cl);
        }
        
        return null;
    }
    
    /**
     * Save the vector as a file.
     * @param filename Path to save ur vector.
     * @param v Vector.
     */
    public static void WriteFile(String filename, int[] v){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(v);
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(VectorUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException io){
            Logger.getLogger(VectorUtil.class.getName()).log(Level.SEVERE, null, io);
        }
    }
}