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
 * Bucket Sort.
 * 
 * Is a sorting algorithm that works by partitioning an array into a number of buckets.
 * Each bucket is then sorted individually, either using a different sorting algorithm,
 * or by recursively applying the bucket sorting algorithm.
 * 
 * Best case: O(n).
 * Average case: O(n + k).
 * Worst case: O(n^2).
 * 
 * Reference: http://en.wikipedia.org/wiki/Bucket_sort
 * 
 * @author Diego Catalano
 */
public class BucketSort implements ISort{
    
    private long nComp = 0;
    private long nAtt = 0;
    private long time = 0;
    private boolean findBuckets = false;
    
    private int numBuckets;

    /**
     * Get number of buckets.
     * @return Number of buckets.
     */
    public int getNumBuckets() {
        return numBuckets;
    }

    /**
     * Set number of buckets.
     * @param numBuckets Number of buckets.
     */
    public void setNumBuckets(int numBuckets) {
        this.numBuckets = numBuckets;
    }
    
    /**
     * Initialize a new instance of the BucketSort class.
     */
    public BucketSort() {
        this.findBuckets = true;
    }

    /**
     * Initialize a new instance of the BucketSort class.
     * @param numBuckets Number of buckets.
     */
    public BucketSort(int numBuckets) {
        this.numBuckets = numBuckets;
    }

    @Override
    public void Sort(int[] v) {
        
        if(findBuckets){
            int min = Integer.MAX_VALUE;
            int max = 0;
            for (int i = 0; i < v.length; i++) {
                if (v[i] > max) max = v[i];
                if (v[i] < min) min = v[i];
            }
            this.numBuckets = max - min;
        }
        
        nComp = nAtt = 0;
        long start = System.currentTimeMillis();
        
        int [] bucket = new int[numBuckets+1];

        for (int i = 0; i < bucket.length; i++) {
           bucket[i] = 0;
        }

        for (int i = 0; i < v.length; i++) {
           bucket[v[i]]++;
        }
        nComp += v.length;
        nAtt += v.length;

        int outPos = 0;
        for (int i = 0; i < bucket.length; i++) {
            nComp++;
           for (int j = 0; j < bucket[i]; j++) {
              v[outPos++] = i;
              nComp++;
              nAtt++;
           }
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