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
 * Bubble sort.
 * 
 * Bubble sort, sometimes incorrectly referred to as sinking sort, is a simple sorting algorithm that works
 * by repeatedly stepping through the list to be sorted, comparing each pair of adjacent items and swapping them
 * if they are in the wrong order. The pass through the list is repeated until no swaps are needed, which indicates
 * that the list is sorted. The algorithm gets its name from the way smaller elements "bubble" to the top of the list.
 * Because it only uses comparisons to operate on elements, it is a comparison sort. Although the algorithm is simple,
 * most of the other sorting algorithms are more efficient for large lists.
 * 
 * Best case: O(n).
 * Average case: O(n^2).
 * Worst case: O(n^2).
 * 
 * References: http://en.wikipedia.org/wiki/Bubble_sort
 * 
 * @author Diego Catalano
 */
public class BubbleSort implements ISort{
    
    private long nComp = 0;
    private long nAtt = 0;
    private long time = 0;

    /**
     * Initialize a new instance of the BubbleSort class.
     */
    public BubbleSort() {}

    @Override
    public void Sort(int[] v) {
        
        nComp = nAtt = 0;
        long start = System.currentTimeMillis();
        
        for (int k = 0; k < v.length - 1; k++, nComp++)  {  
           boolean isSorted = true;

           for (int i = 1; i < v.length - k; i++, nComp++)  {
               nComp++;
               if (v[i] < v[i - 1]){
                  int tempVariable = v[i];
                  v[i] = v[i - 1];
                  v[i - 1] = tempVariable;

                  isSorted = false;
                  nAtt += 3;
               }
           }

           nComp++;
           if (isSorted)
              break;
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