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
 * Shell sort.
 * 
 * Shellsort, also known as Shell sort or Shell's method, is an in-place comparison sort.
 * It generalizes an exchanging sort, such as insertion or bubble sort, by starting the comparison
 * and exchange of elements with elements that are far apart before finishing with neighboring elements.
 * Starting with far apart elements can move some out-of-place elements into position faster than a simple
 * nearest neighbor exchange. Donald Shell published the first version of this sort in 1959.
 * The running time of Shellsort is heavily dependent on the gap sequence it uses. For many practical variants,
 * determining their time complexity remains an open problem.
 * 
 * Best case: Depends on gap sequence.
 * Average case: Depends on gap sequence.
 * Worst case: Depends on gap sequence.
 * 
 * References: http://en.wikipedia.org/wiki/Shellsort
 * 
 * @author Diego Catalano
 */
public class ShellSort implements ISort{
    
    private long nComp = 0;
    private long nAtt = 0;
    private long time = 0;

    /**
     * Initialize a new instance of the ShellSort class.
     */
    public ShellSort() {}

    @Override
    public void Sort(int[] v) {
        
        nComp = nAtt = 0;
        long start = System.currentTimeMillis();
        
        int inc = v.length / 2;
        nAtt++;
        
        while (inc > 0) {
            nComp++;
            for (int i = 0; i < v.length; i++, nComp++) {
                int j = i;
                int temp = v[i];
                nAtt += 2;
                while (j >= inc && v[j-inc] > temp) {
                    nComp += 2;
                    v[j] = v[j - inc];
                    j -= inc;
                    nAtt += 2;
                }
                v[j] = temp;
                nAtt++;
            }
            
            nComp++;
            if (inc == 2)
                inc = 1;
            else 
                inc = inc * 5 / 11;
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