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

package Tools;

import Sort.ISort;

/**
 * Performs tests between sort algorithm.
 * @author Diego Catalano
 */
public class BenchmarkComparator {
    
    private ISort sort1;
    private ISort sort2;
    private int[] v;

    public int[] getGeneratedVector() {
        return v;
    }
    
    public long getDifferenceOfComparasion(){
        if(sort1.getTotalOfComparasion() > sort2.getTotalOfComparasion())
            return sort1.getTotalOfComparasion() - sort2.getTotalOfComparasion();
        return sort2.getTotalOfComparasion() - sort1.getTotalOfComparasion();
    }
    
    public long getDifferenceOfAttribution(){
        if(sort1.getTotalOfAttribution()> sort2.getTotalOfAttribution())
            return sort1.getTotalOfAttribution() - sort2.getTotalOfAttribution();
        return sort2.getTotalOfAttribution() - sort1.getTotalOfAttribution();
    }
    
    public long getDifferenceOfElapsedTime(){
        if(sort1.getElapsedTime()> sort2.getElapsedTime())
            return sort1.getElapsedTime() - sort2.getElapsedTime();
        return sort2.getElapsedTime() - sort1.getElapsedTime();
    }

    /**
     * Perform tests.
     * @param sort1 First sort algorithm.
     * @param sort2 Second sort algorithm.
     */
    public BenchmarkComparator(ISort sort1, ISort sort2) {
        this.sort1 = sort1;
        this.sort2 = sort2;
    }
    
    /**
     * Perform test with a custom vector.
     * @param array Array.
     * @return The winner between first or second sort.
     */
    public int CustomVector(int[] array){
        this.v = array;
        return Run();
    }
    
    /**
     * Perform test with a random vector.
     * @param size Size of vector.
     * @return The winner between first or second sort.
     */
    public int RandomVector(int size){
        return RandomVector(size, true);
    }
    
    /**
     * Perform test with a random vector.
     * @param size Size of vector.
     * @param canRepeat True if can repeat element, otherwise not.
     * @return The winner between first or second sort.
     */
    public int RandomVector(int size, boolean canRepeat){
        this.v = VectorUtil.GenerateRandomVector(size, canRepeat);
        return Run();
    }
    
    /**
     * Perform test with a sorted vector.
     * @param size Size of vector.
     * @return The winner between first or second sort.
     */
    public int SortedVector(int size){
        return SortedVector(size, false);
    }
    
    /**
     * Perform test with a sorted vector.
     * @param size Size of vector.
     * @param invert True if needs to invert the vector, otherwise keep it.
     * @return The winner between first or second sort.
     */
    public int SortedVector(int size, boolean invert){
        this.v = VectorUtil.GenerateSortedVector(size, invert);
        return Run();
    }
    
    /**
     * Perform test with partial sorted vector.
     * @param size Size of vector.
     * @return The winner between first or second sort.
     */
    public int PartialSortedVector(int size){
        this.v = VectorUtil.GeneratePartialSortedVector(size);
        return Run();
    }
    
    /**
     * Perform test with partial sorted vector.
     * @param size Size of vector.
     * @param sizeSorted Set number of elements that are sorted.
     * @return The winner between first or second sort.
     */
    public int PartialSortedVector(int size, int sizeSorted){
        this.v = VectorUtil.GeneratePartialSortedVector(size, sizeSorted);
        return Run();
    }
    
    /**
     * Perform sort in both algorithms.
     * @return The winner between first or second sort.
     */
    private int Run(){
        int winner = 0;
        int[] v2 = v.clone();
        
        sort1.Sort(v);
        sort2.Sort(v2);
        
        if (sort1.getTotalOfComparasion() > sort2.getTotalOfComparasion())
            winner = 1;
        
        return winner;
    }
}