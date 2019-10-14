package IC07A;

import java.util.Scanner;

/**
   This program measures how long it takes to sort an
   array of a user-specified size with the selection
   sort algorithm.
 */
public class SelectionSortTimings
{  
    public static void main(String[] args)
    {  
        Scanner in = new Scanner(System.in);
        //System.out.print("Enter array size: ");
        //int n = in.nextInt();
        
        for(int size = 1; size <= 6; ++size)
        {

            // Construct random array
            
            int[] a = ArrayUtil.randomIntArray(size*10000, size*10000);

            // Use stopwatch to time selection sort

            StopWatch timer = new StopWatch();

            timer.start();
            SelectionSorter.sort(a);
            timer.stop();

            System.out.println(a.length + "Elapsed time: " 
                    + timer.getElapsedTime() + " milliseconds");
        }
    }
}


