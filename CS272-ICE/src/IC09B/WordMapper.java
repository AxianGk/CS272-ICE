package IC09B;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

/**
 * How many occurences of 10-letter words in Alice in Wonderland?
 * Complete the program to find out.
 * 
 * @author 
 * @version 
 *
 */
public class WordMapper
{
    public static void main(String[] args) throws FileNotFoundException
    {        
        Scanner in = new Scanner(new File("alice30.txt"));
        
        /* TODO: Get the appropriate data structure ready... */
        // We want to map word length to occurence count
        
        
        while (in.hasNext())
        {
            /* TODO: Read and process the next word... */
            // - Read the word and grab its length
            // - See if that length is already represented in the map by trying to get its count
            // - If this is the first entry, put it in with a count 1
            // - Otherwise, increment the existing count by 1 and put it back
            
        }
        in.close();

        System.out.println( /* TODO: Print the answer... */);
    }
}

