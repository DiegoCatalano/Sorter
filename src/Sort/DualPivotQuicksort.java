// Sorter
// Comparasion among sort
//
// Copyright © Diego Catalano, 2017
// diego.catalano at live.com
//
// Copyright © Vladimir Yaroslavskiy
// iaroslavski at mail.ru
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
 * Dual Pivot Quicksort.
 * 
 * Quicksort with dual pivot.
 * Follow the original implementation from Vladimir.
 * References: http://codeblab.com/wp-content/uploads/2009/09/DualPivotQuicksort.pdf
 * 
 * @author Diego Catalano
 */
public class DualPivotQuicksort implements ISort{
    
    private long nComp;
    private long nAtt;
    private long time;

    public DualPivotQuicksort() {}

    @Override
    public void Sort(int[] v) {
        nComp = nAtt = time = 0;
        long start = System.currentTimeMillis();
        DualPivotQuicksort(v, 0, v.length-1, v.length/2);
        long end = System.currentTimeMillis();
        
        this.time = end - start;
    }
    
    private void DualPivotQuicksort(int[] v, int left, int right, int div){
        int len = right - left;
        
        int third = len / div;
        // "medians"
        int m1 = left + third;
        int m2 = right - third;
        if (m1 <= left) {
            m1 = left + 1;
        }
        if (m2 >= right) {
            m2 = right - 1;
        }
        if (v[m1] < v[m2]) {
            swap(v, m1, left);
            swap(v, m2, right);
        }
        else {
            swap(v, m1, right);
            swap(v, m2, left);
        }
        
        // pivots
        int pivot1 = v[left];
        int pivot2 = v[right];
        
        // pointers
        int less = left + 1;
        int great = right - 1;
        
        // sorting
        for (int k = less; k <= great; k++) {
            if (v[k] < pivot1)
                swap(v, k, less++);
        
            else if (v[k] > pivot2) {
                while (k < great && v[great] > pivot2) {
                    great--;
                }
                swap(v, k, great--);
                if (v[k] < pivot1) {
                    swap(v, k, less++);
                }
            }
        }
        
        // swaps
        int dist = great - less;
        if (dist < 13) {
            div++;
        }
        swap(v, less - 1, left);
        swap(v, great + 1, right);
        
        // subarrays
        DualPivotQuicksort(v, left, less - 2, div);
        DualPivotQuicksort(v, great + 2, right, div);

        // equal elements
        if (dist > len - 13 && pivot1 != pivot2) {
            for (int k = less; k <= great; k++) {
                if (v[k] == pivot1) {
                    swap(v, k, less++);
                }
                else if (v[k] == pivot2) {
                    swap(v, k, great--);
                if (v[k] == pivot1) {
                    swap(v, k, less++);
                }
                }
            }
        }
        
        // subarray
        if (pivot1 < pivot2) {
            DualPivotQuicksort(v, less, great, div);
        }
    }
    
    private void swap(int[] a, int i, int j) {
     int temp = a[i];
     a[i] = a[j];
     a[j] = temp;
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
