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
 * Heap sort.
 * 
 * Heapsort is a comparison-based sorting algorithm to create a sorted array (or list), and is part of the selection sort family.
 * Although somewhat slower in practice on most machines than a well-implemented quicksort, it has the advantage
 * of a more favorable worst-case O(n log n) runtime. Heapsort is an in-place algorithm, but it is not a stable sort.
 * 
 * Best case: O(n log n).
 * Average case: O(n log n).
 * Worst case: O(n log n).
 * 
 * References: http://en.wikipedia.org/wiki/Heapsort
 * 
 * @author Diego Catalano
 */
public class HeapSort implements ISort{
    
    private long nComp = 0;
    private long nAtt = 0;
    private long time = 0;

    /**
     * Initialize a new instance of the HeapSort class.
     */
    public HeapSort() {}
    
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
    public void Sort(int[] v){  
        long start = System.currentTimeMillis();
        buildMaxHeap(v);
        int n = v.length;

        for (int i = v.length - 1; i > 0; i--, nComp++, nAtt+=2) 
        { 
           swap(v, i , 0);
           maxHeapify(v, 0, --n);
        }
        long end = System.currentTimeMillis();
        this.time = end - start;
    }

    private void buildMaxHeap(int[] v){ 
       for (int i = v.length/2 - 1; i >= 0; i--, nComp++, nAtt+=2)
          maxHeapify(v, i , v. length );
    }
    
    private void maxHeapify(int[] v, int pos, int n) { 
       int maxi; 
       int l = 2 * pos + 1;
       int right = 2 * pos + 2;
       nAtt += 2;
       nComp += 2;
       if ( (l < n) && (v[l] > v[pos]) )
       {
          maxi = l;
       }
       else
       {
          maxi = pos;
       }
       
       nAtt++;
       nComp += 2;
       if (right < n && v[right] > v[maxi]) 
       { 
          maxi = right;
          nAtt++;
       }
       
       nComp++;
       if (maxi != pos) 
       {
          swap(v, pos, maxi);
          maxHeapify(v, maxi, n);
       }
    }
 
    private void swap ( int[ ] v, int j, int aposJ ){
       int aux = v [ j ];
       v [ j ] = v [ aposJ ];
       v [ aposJ ] = aux;
       nAtt += 3;
    }
    
    @Override
    public String toString(){
        return "Heap Sort";
    }
}