// Sorter
// Comparasion among sort
//
// Copyright © Diego Catalano, 2013
// diego.catalano at live.com
//
// Original code: http://www.codecodex.com/wiki/Merge_sort#Java
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
 * Merge sort.
 * 
 * <para>In computer science, a merge sort (also commonly spelled mergesort) is an O(n log n) comparison-based sorting algorithm.
 * Most implementations produce a stable sort, which means that the implementation preserves the input order of equal elements
 * in the sorted output. Merge sort is a divide and conquer algorithm that was invented by John von Neumann in 1945.</para>
 * 
 * Best case: O(n log n).
 * Average case: O(n log n).
 * Worst case: O(n log n).
 * 
 * References: http://en.wikipedia.org/wiki/Merge_sort
 * 
 * @author Diego Catalano
 */
public final class MergeSort implements ISort{
    
    private long nComp = 0;
    private long nAtt = 0;
    private long time = 0;

    /**
     * Initialize a new instance of the MergeSort class.
     */
    public MergeSort() {}
    
    @Override
    public long getTotalOfComparasion() {
        return nComp;
    }
    
    @Override
    public long getTotalOfAttribution(){
        return nAtt;
    }
    
    @Override
    public long getElapsedTime(){
        return time;
    }
    
    @Override
    public void Sort(int array[])  {
        nComp = nAtt = 0;
        long start = System.currentTimeMillis();
        
        nComp++;
        if(array.length > 1){
            int elementsInA1 = array.length/2;  
            int elementsInA2 = array.length - elementsInA1; 
            nAtt += 2;
            
            int arr1[] = new int[elementsInA1];  
            int arr2[] = new int[elementsInA2];  
              
            for(int i = 0; i < elementsInA1; i++, nComp++, nAtt+=2)
                arr1[i] = array[i];  
      
            for(int i = elementsInA1; i < elementsInA1 + elementsInA2; i++, nComp++, nAtt+=2)  
                arr2[i - elementsInA1] = array[i];  
      
            Sort(arr1);  
            Sort(arr2);  
            
            nAtt += 3;
            int i = 0, j = 0, k = 0;  
      
            
            while(arr1.length != j && arr2.length != k) {  
                nComp+=4;
                if(arr1[j] <= arr2[k]) {  
                    array[i] = arr1[j];  
                    i++;  
                    j++;
                } else {  
                    array[i] = arr2[k];  
                    i++;  
                    k++;
                }
                nAtt += 3;
            }  
      
            while(arr1.length != j) {  
                array[i] = arr1[j];  
                i++;  
                j++;  
            }
            
            while(arr2.length != k) {
                array[i] = arr2[k];  
                i++;  
                k++;
                nComp++;
                nAtt++;
            }  
        }
        long end = System.currentTimeMillis();
        this.time = end - start;
    }
    
    @Override
    public String toString(){
        return "Merge Sort";
    }
}