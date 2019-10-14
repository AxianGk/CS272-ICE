package IC08A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
//import java.util.Comparator;

/**
   This program demonstrates Java library methods for sorting and
   searching.
*/
public class JavaLibraryDemo
{
   public static void main(String[] args)
   {
      int[] values = { 1, 4, 9, 1, 6, 2, 5, 3, 6, 4, 9, 6, 4, 8, 1 };
      Arrays.sort(values);
      System.out.println(Arrays.toString(values));
      int pos = Arrays.binarySearch(values, 4);
      System.out.println("Position of 4: " + pos);
      pos = Arrays.binarySearch(values, 7);
      System.out.println("Position for inserting 7: " + (-pos - 1));

      Country country1 = new Country("Belgium", 30510);
      Country country2 = new Country("Thailand", 514000);
      Country country3 = new Country("Uruguay", 176220);

      ArrayList<Country> countries = new ArrayList<Country>();
      countries.add(country1);
      countries.add(country2);
      countries.add(country3);

      Collections.sort(countries);
      System.out.println(countries);

      // Add some more countries
      countries.add(new Country("Switzerland", 41284));
      countries.add(new Country("Zimbabwe", 390757));
      countries.add(new Country("Nicaragua", 130373));
      countries.add(new Country("Israel", 20770));
      
      // TODO: Use a Comparator to sort by country name; then print the result
      // You'll need to write a class that implements Comparator<Country>
      Collections.sort(countries, new CountryComparator());
      System.out.println(countries);
      
      // TODO: Use lambda expressions to sort by length of country name; then print the result
      Collections.sort(countries, (u, v) -> u.getName().length() - v.getName().length());
      System.out.println(countries);
      
      // TODO: Use lambda expressions to sort by country name; then print the result
      Collections.sort(countries, (u, v) -> u.getName().compareTo(v.getName()));
      System.out.println(countries);
      
   }
}
