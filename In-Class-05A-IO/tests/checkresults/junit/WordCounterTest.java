package checkresults.junit;

import static checkresults.ReflectionSupport.create;
import static checkresults.ReflectionSupport.invoke;
import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import checkresults.AbstractBasicTest;
import checkresults.ScoringWeight;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WordCounterTest extends AbstractBasicTest
{
    private StringWriter results;
    // May need to modify to add parameters for object construction.
    @Before public void setup() 
    { 
        obj = create(thisClass);
        results = new StringWriter();
    }

    @BeforeClass
    public static void initializeTests() throws Exception
    {
        className = "occ.cs272.ic05.WordCounter";
        init();
    }
    
    @Test public void test01() {testJackJill(0);}
    @Test public void test02() {testJackJill(1);}
    @Test public void test03() {testJackJill(2);}
    @Test public void test04() {testJackJill(3);}
    @Test public void test05() {testJackJill(4);}
    @Test public void test06() {testJackJill(5);}
    @Test public void test07() {testJackJill(6);}
    @Test public void test08() {testJackJill(7);}
    @Test public void test09() {testJackJill(8);}
    @Test public void test10() {testMary(0);}
    @Test public void test11() {testMary(1);}
    @Test public void test12() {testMary(2);}
    @Test public void test13() {testMary(3);}
    @Test public void test14() {testPeter(0);}
    @Test public void test15() {testPeter(1);}
    @Test public void test16() {testPeter(2);}
    @Test public void test17() {testPeter(3);}
    @Test @ScoringWeight(5) 
    public void test18() {
        StringReader in = new StringReader("one\ntwo two\nthree three three\n");
        StringWriter out = new StringWriter();
        invoke(obj, "countWords", in, out);
        actual = "1   one\n2   two two\n3   three three three\n".split("\n");
        expected = out.toString().split("\n|\r\n");
        assertEquals("String input #1", expected[0], actual[0]);
        assertEquals("String input #2", expected[1], actual[1]);
        assertEquals("String input #3", expected[2], actual[2]);
        out("Reading string input->OK");
    }
    private static String[] actual = null;
    private static String[] expected = null;
    
    private void testJackJill(int line)
    {
        try {
            if (actual == null)
            {
                setUpResults("JackJill.txt");
                expected = new String[] {
                        "7   Jack and Jill went up the hill,",
                        "6   To fetch a pail of water.",
                        "7   Jack fell down and broke his crown,",
                        "5   And Jill came tumbling after.",
                        "0   ",
                        "7   Up Jack got and home he ran,",
                        "6   As fast as he could caper.",
                        "6   There his mother bound his head,",
                        "5   With vinegar and brown paper."
                    };
            }
            String msg = "JackJill line #" + (line + 1) + "->";
            assertEquals(msg, expected[line], actual[line]);
            out(msg + expected[line]);
            if (line == expected.length - 1)
            {
                actual = expected = null;
            }
        } catch (Exception e) { throw new RuntimeException(e); }
    }
    
    private void testMary(int line)
    {
        try {
            if (actual == null)
            {
                setUpResults("Mary.txt");
                expected = new String[] {
                        "5   Mary had a little lamb",
                        "6   Whose fleece was white as snow.", 
                        "5   And everywhere that Mary went,", 
                        "6   The lamb was sure to go!"
                    };
            }
            String msg = "Mary line #" + (line + 1) + "->";
            assertEquals(msg, expected[line], actual[line]);
            out(msg + expected[line]);
            if (line == expected.length - 1)
            {
                actual = expected = null;
            }
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    private void testPeter(int line)
    {
        try {
            if (actual == null)
            {
                setUpResults("Peter.txt");
                expected = new String[] {
                        "8   Peter Piper picked a peck of pickled peppers." , 
                        "8   A peck of pickled peppers, Peter Piper picked." , 
                        "8   If Peter picked a peck of pickled peppers," , 
                        "9   Where's the peck of pickled peppers Peter Piper picked?" , 
                    };
            }
            String msg = "Peter line #" + (line + 1) + "->";
            assertEquals(msg, expected[line], actual[line]);
            out(msg + expected[line]);
            if (line == expected.length - 1)
            {
                actual = expected = null;
            }
        } catch (Exception e) { throw new RuntimeException(e); }
    }
    
    private void setUpResults(String fname)
    {
        try {
            if (actual == null)
            {
                Object[] args = {new FileReader(fname), results};
                invoke(obj, "countWords", args);
                actual = results.toString().split("\n|\r\n");
                expected = null;
            }
        } catch (Exception e) { throw new RuntimeException(e); }
    }
    
    
}
