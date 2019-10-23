package IC09B;

import java.util.Collection;
import java.util.ArrayList;
import java.util.TreeSet;

/**
   This program demonstrates classes from the Java collections framework.
*/
public class CollectionsDemo
{
   public static void main(String[] args)
   {
      System.out.println("Working with an ArrayList");
      workWith(new ArrayList<String>());
      System.out.println("Working with a TreeSet");
      workWith(new TreeSet<String>());
   }

   /**
      Shows how to work with a collection of strings.
      @param coll a collection from the Java collections framework
   */
   public static void workWith(Collection<String> coll)
   {
      coll.add("Harry");
      coll.add("Sally");
      coll.add("Fred");
      coll.add("Wilma");
      coll.add("Harry");
      System.out.println(coll);
      System.out.print("Removing Harry and Tom: ");
      System.out.print(coll.remove("Harry") + " ");
      System.out.println(coll.remove("Tom"));
      System.out.print("Looking for Harry and Sally: ");
      System.out.print(coll.contains("Harry") + " ");
      System.out.println(coll.contains("Sally"));
      for (String s : coll) 
      {
         System.out.println(s);
      }
   }
}
