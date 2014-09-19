// Sorter
// Comparasion among sort
//
// Copyright © Diego Catalano, 2013
// diego.catalano at live.com
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

package Sort;

/**
 * Insertion sort.
 * 
 * Insertion sort is a simple sorting algorithm that builds the final sorted array (or list) one item at a time.
 * It is much less efficient on large lists than more advanced algorithms such as quicksort, heapsort, or merge sort.
 * 
 * Best case: O(n).
 * Average case: O(n^2).
 * Worst case: O(n^2).
 * 
 * References: http://en.wikipedia.org/wiki/Insertion_sort
 * 
 * @author Diego Catalano
 */
public class InsertionSort implements ISort{

    private long nComp = 0;
    private long nAtt = 0;
    private long time = 0;
    
    /**
     * Initialize a new instance of the InsertionSort class.
     */
    public InsertionSort() {}

    @Override
    public void Sort(int[] v) {
        
        nComp = nAtt = 0;
        long start = System.currentTimeMillis();
        
        for (int i = 1; i < v.length; i++, nComp++) {  
          int a = v[i];
          nAtt++;
          int j; 
          for (j = i - 1; j >=0 && v[j] > a; j--, nComp+=2){  
            v[j + 1] = v[j];
            nAtt++;
          }  
          v[j + 1] = a;
          nAtt++;
        }
        
        long end = System.currentTimeMillis();
        this.time = end - start;
    }

    @Override
    public long getTotalOfComparasion() {
        return nComp;
    }

    @Override
    public long getTotalOfAttribution() {
        return nAtt;
    }

    @Override
    public long getElapsedTime() {
        return time;
    }
}