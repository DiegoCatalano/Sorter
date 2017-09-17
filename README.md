Sorter
=========

The Sorter is a library for to understand and release benchmarks.

The project is organized under the same architecture found in [Catalano Framework]. Some sort algorithms implemented:

  - Bubble Sort
  - Bogo Sort
  - Insertion Sort
  - Shell Sort
  - Bucket Sort
  - Merge Sort
  - Heap Sort
  - Quick Sort 
  - Dual Pivot Quicksort

Version
----

0.8


License
----

GNU-GPL

#1 Example: First use
----

Simple example of how to instantiate and use classes.

```java
        
//Initialize the vector util class.
VectorUtil vec = new VectorUtil();
        
//Generate random vector.
int[] v = vec.GenerateRandomVector(1000);
        
//Initialize a sort algorithm.
ISort sort = new DualPivotQuicksort();
sort.Sort(v);
        
//Get the metrics.
long time = sort.getElapsedTime();
long nCmp = sort.getTotalOfComparasion();
long nAtt = sort.getTotalOfAttribution();
```

[Catalano Framework]:https://github.com/DiegoCatalano/Catalano-Framework