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
 * Common interface for sorting algorithms.
 * @author Diego Catalano
 */
public interface ISort {
    
    /**
     * Perform sort in an array.
     * @param v Array.
     */
    public void Sort(int[] v);
    
    /**
     * Get Total of Comparasion performed by sort algorithm.
     * @return Total of comparasion.
     */
    public long getTotalOfComparasion();
    
    /**
     * Get Total of Attribution performed by sort algorithm.
     * @return Total of instructions.
     */
    public long getTotalOfAttribution();
    
    /**
     * Get elapsed time by sort algorithm.
     * @return Elapsed time.
     */
    public long getElapsedTime();
}