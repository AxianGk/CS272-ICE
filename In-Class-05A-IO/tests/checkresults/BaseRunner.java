package checkresults;

import java.util.Arrays;

/**
 * Contains common (static) method for running different kinds of tests.
 * 
 * @author Stephen Gilbert
 * @version Aug 8, 2009 3:03:47 PM
 *
 */
public class BaseRunner
{
    protected final static String underline;

    static
    {
        char[] lines = new char[80];
        Arrays.fill(lines, '-');
        underline = String.valueOf(lines);
    }

    private static String studentName;
    private static String assignmentName;

    public static void setStudentName(String name)
    {
        studentName = name;
    }

    public static String getStudentName()
    {
        return studentName;
    }

    public static void setAssignmentName(String name)
    {
        assignmentName = name;
    }

    public static String getAssignmentName()
    {
        return assignmentName;
    }

    /**
     * Uses reflection to look up a class name from a String.
     * 
     * @param name - the class to look up in the class-path.
     * @return the class or null. (Exceptions are suppressed.)
     */
    final public static Class<?> getClass(String name)
    {
        Class<?> result = null;
        try
        {
            result = Class.forName(name);
        }
        catch (ClassNotFoundException e)
        {
            // Suppress
        }

        return result;
    }

    /**
     * Creates an object from a class using the default constructor. Exceptions
     * are suppressed.
     * 
     * @param c the class to create an instance for.
     * @return a new instance or null.
     */
    final public static Object newInstance(Class<?> c)
    {
        Object result = null;
        try
        {
            result = c.newInstance();
        }
        catch (Exception e)
        {
            // Suppress
        }

        return result;
    }

    /**
     * Display the test header on the console.
     *
     * @param fname the name of the method under test.
     * @param cname the name of the class containing the method.
     */
    public static void startTest(String cname)
    {
        out(BaseRunner.getAssignmentName() + " - Testing " + cname + " for " + 
                BaseRunner.getStudentName());
        out(underline);
    }

    /**
     * Display the test header on the console.
     *
     * @param mname the name of the method under test.
     * @param cname the name of the class containing the method.
     */
    public static void startTest(String mname, String cname)
    {
        out("Testing method " + mname + " in student class " + cname);
        out(underline);
    }

    /**
     * Print the final statistics after the test is done.
     * 
     * @param passing number of tests that passed.
     * @param possible number of tests possible.
     */
    public static void endTest(double passing, double possible)
    {
        if (possible == 0)
        {
            out(underline);
            out("   No tests run: (0%)\n");
        }
        else
        {
            String results = String.format(
                    "   %.0f/%.0f tests passing (%.0f%%)", passing, possible,
                    passing / possible * 100);

            out(underline);
            out(results);
        }
    }

    /**
     * Print the final statistics after the test is done.
     * 
     * @param percent based on ScoreKeeper
     */
    public static void endTest(double percent)
    {
        String results = String.format("   %.0f%% tests passing",
                percent * 100);
        out(underline);
        out(results);
    }

    /**
     * Print the final statistics after the test is done.
     * 
     * @param percent based on ScoreKeeper
     */
    public static void endTest(double possible, double points, double percent)
    {
        String results =  String.format("   %.0f%% (%.1f/%.1f tests passing)", percent * 100,
                points, possible);
        out(underline);
        out(results);

    }

    /**
     * Show the points.
     * 
     * @param percent percentage.
     * @param points points.
     */
    public static void showPoints(double percent, double points)
    {
        String results = String.format("   %.0f of %.0f points%n",
                percent * points, points);
        out(results);
    }

    /**
     * Show the points.
     * 
     * @param percent percentage.
     * @param points points.
     */
    public static void showPoints2(double points, double possible)
    {
        String results = String.format("  %5.2f/%5.2f  ", points, possible);
        out(results);
    }

    /**
     * Print a message on standard output and flush it.
     * 
     * @param message the message to display.
     */
    public static void out(String message)
    {
        System.out.println(message);
    }
}
