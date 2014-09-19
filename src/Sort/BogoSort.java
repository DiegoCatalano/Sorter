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

import java.util.Random;

/**
 * Bogo sort.
 * 
 * In computer science, bogosort(also stupid sort or slowsort) is a particularly ineffective sorting algorithm based
 * on the generate and test paradigm. It is not useful for sorting, but may be used for educational purposes,
 * to contrast it with other more realistic algorithms; it has also been used as an example in logic programming.
 * 
 * Best case: O(n).
 * Average case: O(n * n!).
 * Worst case: Unbouded.
 * 
 * References: http://en.wikipedia.org/wiki/Bogosort
 * 
 * @author Diego Catalano
 */
public class BogoSort implements ISort{
    
    private long nComp = 0;
    private long nAtt = 0;
    private long time = 0;
    private Random generator = new Random();

    /**
     * Initialize a new instance of the BogoSort class.
     */
    public BogoSort() {}

    @Override
    public void Sort(int[] v) {

        nComp = nAtt = 0;
        long start = System.currentTimeMillis();
        
        while (!isSorted(v)) {
            nComp++;
            for (int i = 0; i < v.length; i++, nComp++){  
                int randomPosition = generator.nextInt(v.length);
                nAtt++;
  
                int temp = v[i];  
                v[i] = v[randomPosition];  
                v[randomPosition] = temp;  
                nAtt += 3;
            }  
        }
        
        long end = System.currentTimeMillis();
        this.time = end - start;
    }
    
    private boolean isSorted(int[] data)  {  
        for (int i = 1; i < data.length; i++, nComp++){
            nComp++;
            if (data[i] < data[i - 1])  
                return false;
        }
  
        return true;  
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