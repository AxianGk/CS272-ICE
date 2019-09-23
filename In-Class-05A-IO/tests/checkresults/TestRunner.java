package checkresults;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.junit.runner.JUnitCore;

/**
 * Runs a set of JUnit tests.
 *
 *  @author Stephen Gilbert
 *  @version Spring 2016
 *
 */
public class TestRunner extends BaseRunner
{
    /**
     * Run a set of tests and save the results in a file.
     * Show the file when done.
     * @param classes array containing the class names.
     */
    public static void runTests(String[] classes, double totalPoints)
    {
        Map<String, Double> scoreMap = new TreeMap<>();
        String hwPackage = System.getProperty("hwPackage");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        PrintStream oldErr = System.err;
        boolean studentError = false;
        
        try (PrintStream out = new PrintStream(baos))
        {
            System.setOut(out);
            System.setErr(out);
            
            for (String hwClass : classes)
            {
                Class<?> studentClass = getClass(hwPackage + "." + hwClass);
                Field student = studentClass.getField("STUDENT");
                String studentName = (String)(student.get(null));
                String prevStudent = BaseRunner.getStudentName();
                if (prevStudent != null && prevStudent.length() > 0 && 
                        ! prevStudent.equals(studentName))
                    studentError = true;
                Field assignment = studentClass.getField("ASSIGNMENT");
                String assignmentName = (String)(assignment.get(null));
                double points = runTestsFor(hwClass, studentName, assignmentName);
                scoreMap.put(assignmentName, points);
                System.out.println();
            }
        }
        catch (Exception e)
        {
            
        }
        
        System.setOut(oldOut);
        System.setErr(oldErr);

        String student = BaseRunner.getStudentName();
        String hwDesc = System.getProperty("hwDescription");
        System.out.println(student + " - " + hwDesc + " - " + new Date());
        if (studentError) 
            System.out.println("**** ERROR IN ONE STUDENT NAME ****");
        System.out.println(underline);
        double ptsPerPart = totalPoints / classes.length;
        double pts = 0.0;
        for (Map.Entry<String, Double> e : scoreMap.entrySet())
        {
            System.out.printf("  - Assignment %-25s%10.2f%%%n", 
                    e.getKey(), e.getValue() * 100);
            pts += ptsPerPart * e.getValue();
        }
        System.out.println(underline);
        System.out.printf(" Total Points: %10.2f/%.2f (%5.2f%%)%n", 
                pts, totalPoints, (pts / totalPoints * 100));
        
        System.out.println();
        System.out.println(baos.toString());
    }
    
    /**
     * runTests - run a test on a single class.
     * @return Score for that test.
     */
    public static double runTests()
    {
        try
        {
            String hwClass = System.getProperty("hwClass");
            String hwPackage = System.getProperty("hwPackage");
            Class<?> studentClass = getClass(hwPackage + "." + hwClass);
            Field student = studentClass.getField("STUDENT");
            String studentName = (String)(student.get(null));
            Field assignment = studentClass.getField("ASSIGNMENT");
            String assignmentName = (String)(assignment.get(null));
            return runTestsFor(hwClass, studentName, assignmentName);
            
        } catch (Exception e) {
//            e.printStackTrace(System.err);
            System.err.print("Missing student class, ASSIGNMENT or STUDENT");
            return 0.0;
        }
    }
    /**
     *  Runs a set of instructor JUnit tests.
     *
     *  @param className : name of the student's class with method
     *  @return percentage of tests passing as a double.
     */
    public static double runTestsFor(String className, String studentName, 
            String assignmentName)
    {
        BaseRunner.setStudentName(studentName);
        BaseRunner.setAssignmentName(assignmentName);
        
        // Look up a the student class and create an instance to test.
        Class<?> testClass = getClass("checkresults.junit." + className + "Test");

        // Start the test
        startTest(className);

        if (testClass == null)
        {
            out("No tests written for " + className);
            out("");
            return 0.0;
        }

        // Create and run the tests
        JUnitCore core = new JUnitCore();
        ScoreKeeper score = new ScoreKeeper();
        core.addListener(score);
        core.run(testClass);

        endTest(score.getPossible(), score.getScore(), score.getPercent());

        return score.getPercent();
    }
}
