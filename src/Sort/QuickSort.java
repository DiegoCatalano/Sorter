// Sorter
// Comparasion among sort
//
// Copyright © Diego Catalano, 2013
// diego.catalano at live.com
//
// Copyright © 2009 - 2010 Lars Vogel
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
 * Quick sort.
 * 
 * Quicksort, or partition-exchange sort, is a sorting algorithm developed by Tony Hoare that, on average, makes
 * O(n log n) comparisons to sort n items. In the worst case, it makes O(n2) comparisons, though this behavior is rare.
 * Quicksort is often faster in practice than other O(n log n) algorithms.
 * Additionally, quicksort's sequential and localized memory references work well with a cache.
 * Quicksort is a comparison sort and, in efficient implementations, is not a stable sort. 
 * Quicksort can be implemented with an in-place partitioning algorithm, so the entire sort can be done with 
 * only O(log n) additional space used by the stack during the recursion.
 * 
 * Best case: O(n log n).
 * Average case: O(n log n).
 * Worst case: O(n^2).
 * 
 * References: http://en.wikipedia.org/wiki/Quicksort
 * 
 * @author Diego Catalano
 */
public final class QuickSort implements ISort{
    
    private int[] numbers;
    private int number;
    private long nComp = 0;
    private long nAtt = 0;
    private long time = 0;

    /**
     * Initialize a new instance of the QuickSort class.
     */
    public QuickSort() {}
    
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
        // Check for empty or null array
        if (v == null || v.length == 0){
          return;
        }
        this.numbers = v;
        number = v.length;
        quicksort(0, number - 1);
        long end = System.currentTimeMillis();
        
        this.time = end - start;
    }
 
    private void quicksort(int low, int high) {
      int i = low, j = high; nAtt+=2;
      // Get the pivot element from the middle of the list
      int pivot = numbers[low + (high-low)/2]; nAtt++;

      // Divide into two lists
      while (i <= j) {
        nComp++;
        // If the current value from the left list is smaller then the pivot
        // element then get the next element from the left list
        while (numbers[i] < pivot) {
          nComp++;
          nAtt++;
          i++;
        }
        // If the current value from the right list is larger then the pivot
        // element then get the next element from the right list
        while (numbers[j] > pivot) {
          nAtt++;
          nComp++;
          j--;
        }

        // If we have found a values in the left list which is larger then
        // the pivot element and if we have found a value in the right list
        // which is smaller then the pivot element then we exchange the
        // values.
        // As we are done we can increase i and j
        nComp++;
        if (i <= j) {
          exchange(i, j);
          i++;
          j--;
          nAtt += 5;
        }
      }
      
      // Recursion
      nComp++;
      if (low < j)
        quicksort(low, j);
      
      nComp++;
      if (i < high)
        quicksort(i, high);
    }

    private void exchange(int i, int j) {
      int temp = numbers[i];
      numbers[i] = numbers[j];
      numbers[j] = temp;
    }
    
    @Override
    public String toString(){
        return "Quick Sort";
    }
}