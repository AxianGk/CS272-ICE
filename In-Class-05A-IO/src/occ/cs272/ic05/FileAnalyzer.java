package occ.cs272.ic05;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * 
 * FileAnalyzer
 * 
 * @author add your name
 * @version Fall 2019
 * 
 *  Open a file, read all words in the file, and 
 *  find the longest word in the file. If there are 
 *  multiple words with the same length, return the 
 *  first of the maximum length words.
 */
public class FileAnalyzer
{
    // TODO: You must fill these in correctly to get credit
    public static final String STUDENT = "xzhao4";
    public static final String ASSIGNMENT = "IC05-A";
    
   /**
    * @param inputFileName the name of the input file
    * @throws FileNotFoundException 
   */
   public String longestWord(String inputFileName) throws FileNotFoundException
   {
       // Store the largest word in this variable
       String result = "";
       // 1. Construct a FileReader and a Scanner
       // TODO: your work here
       FileReader fr = new FileReader(inputFileName);
       Scanner in = new Scanner(fr);
       
       // 2. Use the Scanner to read all of the words
       //   Keep track of which is largest
       // TODO: your work here
       while (in.hasNext())
       {
           String word = in.next();
           if(word.length() > result.length())
               result = word;
       }
       
       // 3. Close the files
       // TODO: your work here 
       in.close();
       
       // Return the result
       return result;
   }
}