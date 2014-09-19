// Sorter
// Comparasion among sort
//
// Copyright © Diego Catalano, 2013
// diego.catalano at live.com
//
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
import java.io.FileWriter;
import java.io.IOException;

/**
 * Performs intensive tests.
 * @author Diego Catalano
 */
public class Benchmark {
    
    public enum Method {Sorted, InverseSorted, PartialSorted, PartialSortedShuffle, Random, RandomNoRepetition};
    private Method method;
    private ISort algorithm;
    private int times = 6;
    private long[] nComp;
    private long[] nAtt;
    private long[] elapsedTime;

    /**
     * Perform tests.
     * Default value = 6.
     * Start with vector size of 10 and grow multiply by 10 for each time.
     * @param algorithm Sort algorithm.
     * @param method Method.
     */
    public Benchmark(ISort algorithm, Method method) {
        this(algorithm, method, 6);
    }
    
    /**
     * Perform tests.
     * @param algorithm Sort algorithm.
     * @param method Method.
     * @param n Times.
     */
    public Benchmark(ISort algorithm, Method method, int n) {
        this.algorithm = algorithm;
        this.method = method;
        this.times = Math.max(1, Math.min(n, 12));
        nComp = new long[times];
        nAtt = new long[times];
        elapsedTime = new long[times];
    }
    
    /**
     * Process the test.
     */
    public void Process(){
        int x = 10;
        switch (method){
            case Sorted:
                for (int i = 0; i < times; i++) {
                    int[] v = VectorUtil.GenerateSortedVector(x);
                    algorithm.Sort(v);
                    nComp[i] = algorithm.getTotalOfComparasion();
                    nAtt[i] = algorithm.getTotalOfAttribution();
                    elapsedTime[i] = algorithm.getElapsedTime();
                    x *= 10;
                }
            break;
            case InverseSorted:
                for (int i = 0; i < times; i++) {
                    int[] v = VectorUtil.GenerateSortedVector(x, true);
                    algorithm.Sort(v);
                    nComp[i] = algorithm.getTotalOfComparasion();
                    nAtt[i] = algorithm.getTotalOfAttribution();
                    elapsedTime[i] = algorithm.getElapsedTime();
                    x *= 10;
                }
            break;
            case PartialSorted:
                for (int i = 0; i < times; i++) {
                    int[] v = VectorUtil.GeneratePartialSortedVector(x);
                    algorithm.Sort(v);
                    nComp[i] = algorithm.getTotalOfComparasion();
                    nAtt[i] = algorithm.getTotalOfAttribution();
                    elapsedTime[i] = algorithm.getElapsedTime();
                    x *= 10;
                }
            break;
            case PartialSortedShuffle:
                for (int i = 0; i < times; i++) {
                    int[] v = VectorUtil.GeneratePartialSortedVectorShuffle(x,1);
                    algorithm.Sort(v);
                    nComp[i] = algorithm.getTotalOfComparasion();
                    nAtt[i] = algorithm.getTotalOfAttribution();
                    elapsedTime[i] = algorithm.getElapsedTime();
                    x *= 10;
                }
            break;
            case Random:
                for (int i = 0; i < times; i++) {
                    int[] v = VectorUtil.GenerateRandomVector(x);
                    algorithm.Sort(v);
                    nComp[i] = algorithm.getTotalOfComparasion();
                    nAtt[i] = algorithm.getTotalOfAttribution();
                    elapsedTime[i] = algorithm.getElapsedTime();
                    x *= 10;
                }
            break;
            case RandomNoRepetition:
                for (int i = 0; i < times; i++) {
                    int[] v = VectorUtil.GenerateRandomVector(x, false);
                    algorithm.Sort(v);
                    nComp[i] = algorithm.getTotalOfComparasion();
                    nAtt[i] = algorithm.getTotalOfAttribution();
                    elapsedTime[i] = algorithm.getElapsedTime();
                    x *= 10;
                }
            break;
        }
        
        
    }
    
    /*
     * Export results as CSV format.
     * @param filename Pathname.
     */
    public void ExportAsCSV(String filename){
	try {
	    FileWriter writer = new FileWriter(filename);
 
	    writer.append("Algorithm: " + algorithm.toString());
	    writer.append('\n');
 
	    writer.append("Method: " + method.name());
            writer.append('\n');
 
	    writer.append("Size");
	    writer.append(';');
	    writer.append("Total of Comparasion");
            writer.append(';');
            writer.append("Total of Attribution");
            writer.append(';');
            writer.append("Elapsed Time (ms)");
	    writer.append('\n');
            
            int x = 10;
            for (int i = 0; i < times; i++) {
                writer.append(String.valueOf(x));
                writer.append(';');
                writer.append(String.valueOf(nComp[i]));
                writer.append(';');
                writer.append(String.valueOf(nAtt[i]));
                writer.append(';');
                writer.append(String.valueOf(elapsedTime[i]));
                writer.append('\n');
                x *= 10;
            }
 
	    //generate whatever data you want
	    writer.flush();
	    writer.close();
	}
	catch(IOException e)
	{
	     e.printStackTrace();
	}
    }
    
    /**
     * Export benchmark log.
     * @param filename Pathname.
     */
    public void ExportAsLOG(String filename){
	try {
	    FileWriter writer = new FileWriter(filename);
 
	    writer.append("Algorithm: " + algorithm.toString());
            writer.append('\r');
	    writer.append('\n');
 
	    writer.append("Method: " + method.name());
            writer.append('\r');
            writer.append('\n');
            writer.append('\r');
            writer.append('\n');
            
            int x = 10;
            for (int i = 0; i < times; i++) {
                writer.append("Size: ");
                writer.append(String.valueOf(x));
                writer.append('\r');
                writer.append('\n');
                writer.append("Total of Comparasion: ");
                writer.append(String.valueOf(nComp[i]));
                writer.append('\r');
                writer.append('\n');
                writer.append("Total of Attribution: ");
                writer.append(String.valueOf(nAtt[i]));
                writer.append('\r');
                writer.append('\n');
                writer.append("Elapsed time (ms): ");
                writer.append(String.valueOf(elapsedTime[i]));
                writer.append('\r');
                writer.append('\n');
                writer.append('\r');
                writer.append('\n');
                x *= 10;
            }
            
	    //generate whatever data you want
	    writer.flush();
	    writer.close();
	}
	catch(IOException e)
	{
	     e.printStackTrace();
	}
    }
}