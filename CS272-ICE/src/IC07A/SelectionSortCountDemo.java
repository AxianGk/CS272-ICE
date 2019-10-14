package IC07A;

import java.util.Scanner;

/**
   This program measures how long it takes to sort an
   array of a user-specified size with the selection
   sort algorithm.
 */
public class SelectionSortCountDemo
{  
    public static void main(String[] args)
    {  
        Scanner in = new Scanner(System.in);
        //System.out.print("Enter array size: ");
        //int n = in.nextInt();
        
        for(int size = 1; size <= 9; ++size)
        {

            // Construct random array
            
            int[] a = ArrayUtil.randomIntArray(size*10000, size*10000);

            // Use stopwatch to time selection sort

            SelectionSorter.resetcCount();
            SelectionSorter.sort(a);

            
            System.out.println(a.length + "Elapsed. Comparisons make: " 
                    + SelectionSorter.getcCount());
        }
    }
}


