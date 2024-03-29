package IC09B;

/**
   A country with a name and area.
*/
public class Country
{
   private String name;
   private double area;

   /**
      Constructs a country.
      @param aName the name of the country
      @param anArea the area of the country
   */
   public Country(String aName, double anArea) 
   { 
      name = aName;
      area = anArea; 
   }

   /**
      Gets the country name.
      @return the name
   */
   public String getName() 
   {
      return name;
   }

   /**
      Gets the area of the country.
      @return the area
   */
   public double getArea() 
   {
      return area;
   }
   
   public int hashCode()
   {
      int h1 = name.hashCode();
      int h2 = new Double(area).hashCode();
      final int HASH_MULTIPLIER = 29;
      int h = HASH_MULTIPLIER * h1 + h2;
      return h;
   }

   public boolean equals(Object otherObject)
   {  
      Country other = (Country) otherObject;
      return name.equals(other.name) 
         && area == other.area; 
   }
   
   public String toString()
   {
      return getClass().getName() + "[name=" + name 
         + ",area=" + area + "]";
   }
}
