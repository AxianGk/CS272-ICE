package IC09B;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * How many unique words in Alice in Wonderland?
 * Complete the program to find out.
 * 
 * @author 
 * @version 
 *
 */
public class UniqueWords
{
    public static void main(String[] args) throws FileNotFoundException
    {        
        Scanner in = new Scanner(new File("alice30.txt"));
        
        /* TODO: Get the appropriate data structure ready... */
        Set<String> allwords = new HashSet<String>();
        
        while (in.hasNext())
        {
            /* TODO: Read and process the next word... */
            allwords.add(in.next());
        }
        in.close();

        System.out.println( "The number of unique words in Alice in Wonderland: " );
        System.out.println(allwords.size());
        
    }
}

