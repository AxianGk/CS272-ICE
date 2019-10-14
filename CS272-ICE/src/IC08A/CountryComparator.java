package IC08A;

import java.util.Comparator;

public class CountryComparator implements Comparator<Country>
{
    public int compare(Country a, Country b)
    {
        return a.getName().compareTo(b.getName());
    }
}
