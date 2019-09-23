package checkresults;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.*;

import java.lang.annotation.*;
import java.util.*;
import java.io.*;

import javax.swing.JOptionPane;

/**
 * Keeps track of scores based on ScoreWeight annotations
 *
 * @author Stephen Gilbert
 * @version Sep 19, 2009 August 17, 2012 - Turned off the System.err except when
 *          reporting a failure. This is because the "Lift abbot GUI framework"
 *          throws an exception when run in DrJava that is confusing for
 *          students.
 *
 */
public class ScoreKeeper extends RunListener
{
    // Turn the error stream on only during a failure.
    private static PrintStream oldErr = System.err;
    private static PrintStream noStream = new PrintStream(
            new ByteArrayOutputStream());

    private double score = 0.0;
    private double possible = 0.0;
    private double points = 0.0;
    private double tpossible = 0.0;

    private String failMessage;
    private boolean testIgnored;

    public double getPossible()
    {
        return possible;
    }

    public double getScore()
    {
        return score;
    }

    @Override
    public void testAssumptionFailure(Failure failure)
    {
        // Called when Assume fails
        // Don't want to count these points in total tests.
        testIgnored = true;
        possible -= tpossible;
    }

    @Override
    public void testFailure(Failure failure) throws Exception
    {
        dbg("testFailure->" + failure);

        points = 0;
        if (failure != null)
        {
            String msg;
            if (failure.getMessage() != null)
                msg = failure.getMessage();
            else
                msg = failure.getDescription().toString();

            if (msg.startsWith("test timed out"))
                msg = "Could not complete. (Endless Loop?)";

            if (msg.trim().length() == 0)
                failMessage = String.format(" X TEST FAILED");
            else
            {
                if (msg.startsWith("java.lang.AssertionError: "))
                    msg = msg.replace("java.lang.AssertionError: ", "");
                else if (msg.startsWith("org.junit.ComparisonFailure: "))
                    msg = msg.replace("org.junit.ComparisonFailure: ", "");
                else if (msg
                        .startsWith("junit.framework.AssertionFailedError: "))
                    msg = msg.replace(
                            "junit.framework.AssertionFailedError: ", "");
                failMessage = String.format(" X %s", msg);
                if (failMessage.length() > 80)
                {
                    int pos = failMessage.substring(0, 80).lastIndexOf(' ');
                    failMessage = failMessage.substring(0, pos) + "\n  "
                            + failMessage.substring(pos);
                }
            }
        }
        super.testFailure(failure);
    }

    @Override
    public void testFinished(Description description) throws Exception
    {
        dbg("testFinished->" + description);

        System.setErr(oldErr);
        try
        {
            // If failure - print the error message, otherwise finish regular
            if (failMessage.length() > 0)
                System.err.println(failMessage + " (" + tpossible + ")");
            else if (!testIgnored)
            {
                System.out.println(" (" + tpossible + ")");
                score += points;
                points = 0;
                System.out.flush();
                System.err.flush();
            }
            super.testFinished(description);
        }
        catch (Exception e)
        {
        }
        System.setErr(noStream);
    }

    // Remove HINT (see earlier semesters for HINT processing.)
    @Override
    public void testStarted(Description description) throws Exception
    {
        dbg("testStarted->" + description);
        try
        {
            failMessage = "";
            testIgnored = false;
            Collection<Annotation> annotations = description.getAnnotations();
            // System.out.println("There are " + annotations.size() + "
            // annotations.");
            boolean hasWeight = false;
            for (Annotation a : annotations)
            {
                // System.out.println(a.toString());
                if (a.annotationType() == ScoringWeight.class)
                {
                    ScoringWeight weight = (ScoringWeight) a;
                    // System.out.println("Weight: " + weight.value());
                    possible += weight.value();
                    points = weight.value();
                    hasWeight = true;
                }
            }
            if (!hasWeight)
            {
                points = 1.0;
                possible += 1.0;
            }
            tpossible = points;
        }
        catch (Exception e)
        {
        }

    }

    @Override
    public void testRunStarted(Description d)
    {
        dbg("testRunStarted->" + d);
        System.setErr(noStream);
    }

    @Override
    public void testRunFinished(Result result) throws Exception
    {
        dbg("testRunFinished->" + result);
        System.setErr(oldErr);
    }

    /**
     * Return percentage.
     * 
     * @return the percentage.
     */
    public double getPercent()
    {
        // System.err.println("possible = " + possible + ", score = " + score);
        return possible > 0 ? score / possible : 0.0;
    }

    private static final boolean DEBUG = false;

    private static void dbg(String msg)
    {
        if (DEBUG)
        {
            JOptionPane.showMessageDialog(null, msg, 
                    "dbg in ScoreKeeper",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
