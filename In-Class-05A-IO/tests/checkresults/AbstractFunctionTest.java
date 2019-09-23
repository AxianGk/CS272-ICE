package checkresults;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AbstractFunctionTest
{
    protected static String[] oddWords3orLonger = {
        "judicious", "biographies", "rioting", "nourishes", 
        "telegraphic", "banally", "confederate", "bytes",
        "outdoor", "baleful", "importers", "financing", 
        "politically", "tutored", "gunplay", "provision", 
        "solvent", "arrival", "bloater", "bawling", "ransacked", 
        "probate", "hostess", "flushed", "abase", "implied", 
        "drawers", "aberrations", "decorates", "knave", 
        "believing", "illustrator", "bewitches", "primitive", 
        "grievance", "conceives", "discretionary", "strangler", 
        "dependability", "shrub", "tentatively", "imperfectly", 
        "doldrum", "allow", "amaze", "squared", "dam",
        "dispelled", "genie", "feebler",
    };
    
    protected static String[] evenWords2orLonger = {
        "provincial", "novels", "heroes",
        "vies", "lashings", "rims", "autoincrements", "manslaughter",
        "rewarded", "corpus", "ballgown", "executable", "stairway", "slogan",
        "recesses", "handicap", "revolves", "inhibits", "takers", "titled",
        "elasticity", "criticized", "roadways", "centralization", "donation",
        "symposiums", "formalisms", "introspect", "assignable", "advisers",
        "retransmitting", "staunchest", "unifications", "cyclotrons", "adders",
        "regional", "intruder", "stormier", "globes", "revising", "aeration",
        "cherries", "regardless", "visa", "grunts", "capacities", "trampler",
        "infamy", "cowboy", "overlaps"
    };

    protected static String[] anyWords = {
        "descender", "population", "deface",
        "agitated", "prose", "adjuring", "reconfigurer", "fatherly", "expires",
        "whittling", "escapees", "apostolic", "biosphere", "pence", "gangster",
        "closure", "technical", "turtles", "reconstruct", "unqualifiedly",
        "isles", "applicatively", "tropics", "shared", "unknowable",
        "purgatory", "dubbed", "refuels", "satyr", "curdle", "spheroidal",
        "cogs", "epistle", "possessors", "coarsely", "printable", "dihedral",
        "downside", "silencer", "hiker", "anon", "fabled", "listers",
        "covering", "candies", "champion", "interruptive", "joyous",
        "brochure", "digitally",
    };
    
    protected static String className;
    protected static String methodName;
    protected static Class<?>[] params;
    protected static String returnType;
    protected static Object obj;
    protected static Method method;

    protected static int curTest;
    protected static int allTests;
    
    protected Object[][] testData;
    
    private static Method[] methods;
    private static ArrayList<String> methodNames = new ArrayList<String>();
    
    protected static void init() throws Exception
    {
        try {
           Class<?> c = Class.forName(className);
            
            methods = c.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                methodNames.add(methods[i].getName());
            }
            
            obj = c.newInstance();
            method = getMethod(methodName);
            curTest = 0;
        }
        catch (Throwable e)
        {
            e.printStackTrace();;
            fail("Method " + methodName + " not found in " + className);
        }    
    }
    
    /**
     * Make sure that the method exists.
     */
    @Test
    @ScoringWeight(.5)
    public void test00()
    {
        assertTrue("Method " + methodName + " not found in " + className,
            obj != null && method != null);
        out("SYNTAX: " + methodName + " found in " + className);
    }

    /**
     * Check if method is public.
     */
    @Test
    @ScoringWeight(.5)
    public void test01()
    {
        assertMethodIsPublic(methodName);
        out("SYNTAX: " + methodName + " is public");
    }

    /**
     * Check if method is has correct return type.
     */
    @Test
    @ScoringWeight(.5)
    public void test02()
    {
        assertMethodReturnTypeIs(methodName, returnType);
        out("SYNTAX: " + methodName + " return type is " + returnType);
    }

   
    /**
     * Check the parameters.
     */
    @Test
    @ScoringWeight(.5)
    public void test03()
    {
        assertParameters(methodName, params);
        out("SYNTAX: " + methodName + " parameters defined correctly");
    }

    // Let's make sure we don't have any endless loops
    
    @Test(timeout=200) @ScoringWeight(5) public void test1() { runTest(1); }
    @Test(timeout=200) @ScoringWeight(5) public void test2() { runTest(2); }
    @Test(timeout=200) @ScoringWeight(5) public void test3() { runTest(3); }
    @Test(timeout=200) @ScoringWeight(5) public void test4() { runTest(4); }
    @Test(timeout=200) @ScoringWeight(5) public void test5() { runTest(5); }
    @Test(timeout=200) @ScoringWeight(5) public void test6() { runTest(6); }
    @Test(timeout=200) @ScoringWeight(5) public void test7() { runTest(7); }
    @Test(timeout=200) @ScoringWeight(5) public void test8() { runTest(8); }
    @Test(timeout=200) @ScoringWeight(5) public void test9() { runTest(9); }
    @Test(timeout=200) @ScoringWeight(5) public void test10() { runTest(10); }
    @Test(timeout=200) @ScoringWeight(5) public void test11() { runTest(11); }
    @Test(timeout=200) @ScoringWeight(5) public void test12() { runTest(12); }
    @Test(timeout=200) @ScoringWeight(5) public void test13() { runTest(13); }
    @Test(timeout=200) @ScoringWeight(5) public void test14() { runTest(14); }
    @Test(timeout=200) @ScoringWeight(5) public void test15() { runTest(15); }
    @Test(timeout=200) @ScoringWeight(5) public void test16() { runTest(16); }
    @Test(timeout=200) @ScoringWeight(5) public void test17() { runTest(17); }
    @Test(timeout=200) @ScoringWeight(5) public void test18() { runTest(18); }
    @Test(timeout=200) @ScoringWeight(5) public void test19() { runTest(19); }
    @Test(timeout=200) @ScoringWeight(5) public void test20() { runTest(20); }
    @Test(timeout=200) @ScoringWeight(5) public void test21() { runTest(21); }
    @Test(timeout=200) @ScoringWeight(5) public void test22() { runTest(22); }
    @Test(timeout=200) @ScoringWeight(5) public void test23() { runTest(23); }
    @Test(timeout=200) @ScoringWeight(5) public void test24() { runTest(24); }
    @Test(timeout=200) @ScoringWeight(5) public void test25() { runTest(25); }
    @Test(timeout=200) @ScoringWeight(5) public void test26() { runTest(26); }
    @Test(timeout=200) @ScoringWeight(5) public void test27() { runTest(27); }
    @Test(timeout=200) @ScoringWeight(5) public void test28() { runTest(28); }
    @Test(timeout=200) @ScoringWeight(5) public void test29() { runTest(29); }
    @Test(timeout=200) @ScoringWeight(5) public void test30() { runTest(30); }
    
    protected void runTest(int n)
    {
        assumeTrue(n <= testData.length);
        
        assertTrue("Method " + methodName + " not found in " + className,
            obj != null && method != null);
        
        Object[] data = testData[n - 1];
        Object[] args = new Object[data.length - 1];
        System.arraycopy(data, 0, args, 0, args.length);
        
        // SAVE THE ORIGINAL PARAMETER IF ONE ARRAY PASSED
        // CHECK IT AFTER TO MAKE SURE IT HASN'T BEEN MODIFIED
        String clone = null;
        if (args.length == 1 && args[0].getClass().isArray())
            clone = arrayToString(args[0]);
        // FOR ARRAY PROBLEMS THAT USE A SINGLE ARRAY PARAMETER
        
        String desc = getParamDesc(args);
        
        Object expected = data[data.length - 1];

        Object actual = null;
        try {
            try {
                actual = method.invoke(obj, args);
            } catch (Exception e) {
                actual = e.getCause().getClass().getName();
            }
            
            if (expected.getClass().isArray())
            {
                String expectedS = arrayToString(expected);
                String actualS = arrayToString(actual);
                assertEquals(methodName + "(" + desc + ")->",
                    expectedS,
                    actualS);
                if (clone != null)
                    assertTrue("Incorrectly modified array parameter.",
                        clone.equals(arrayToString(args[0])));
            }
            else
            {
                assertEquals(methodName + "(" + desc + ")->",
                    expected,
                    actual);
            }
        } catch (Exception e) {  // MUST BE Exception, NOT Throwable
            System.out.println("Exception->" + e.getCause().getClass());
            System.out.println("Expected->" + expected.getClass());
            fail(methodName +  "(" + desc + ") crashed: " + e.getCause().getMessage());
        }
        out("Success: " + methodName + "(" + desc + ")->" + 
            convertParam(actual));
    }
    
    /**
     * Creates the description String for messages.
     */
    private String getParamDesc(Object[] args)
    {
        String result = "";
        if (args.length > 0)
        {
            result += convertParam(args[0]);
            for (int i = 1; i < args.length; i++)
                result += ", " + convertParam(args[i]);
        }
        return result;
    }
    
    private String convertParam(Object o)
    {
        if (o instanceof String)
            return "\"" + o + "\"";
        else if (o.getClass().isArray())
            return arrayToString(o);
        else
            return o.toString();
    }
    
    private String arrayToString(Object o)
    {
        StringBuilder s;
        if (o.getClass() == int[].class)
            s = new StringBuilder(Arrays.toString((int[])o));
        else if  (o.getClass() == double[].class)
            s = new StringBuilder(Arrays.toString((double[])o));
        else if  (o.getClass() == boolean[].class)
            s = new StringBuilder(Arrays.toString((boolean[])o));
        else
            s = new StringBuilder(Arrays.toString((Object[])o));
        
        s.setCharAt(0, '{');
        s.setCharAt(s.length() - 1, '}');
        return s.toString();
    }
    
    
    /**
     *  Private methods for testing methods.
     *  Returns field given a name.
     */
    protected static Method getMethod(String name) {
        int pos = methodNames.indexOf(name);
        return (pos < 0) ? null : methods[pos];
    }

    /**
     * Checks if the method has the correct return type.
     */
    protected void assertMethodReturnTypeIs(String mname, String desiredType) {
        assertMethodExists(mname);
        Method m = getMethod(mname);
        assertTrue("Not correct return type.", 
            m.getReturnType().getSimpleName().equals(desiredType));
    }

    /**
     * Asserts that the method exists.
     */
    protected void assertMethodExists(String name) {
        assertTrue("Method " + name + " does not exist.", 
            methodNames.contains(name));
    }

    /**
     * Asserts that the method is private.
     */
    protected void assertMethodIsPrivate(String mname) {
        assertMethodExists(mname);
        Method m = getMethod(mname);
        assertTrue("Method " + mname + " is not private.", 
            Modifier.isPrivate(m.getModifiers()));
    }

    /**
     * Asserts that the method is public.
     */
    protected void assertMethodIsPublic(String mname) {
        assertMethodExists(mname);
        Method m = getMethod(mname);
        assertTrue("Method " + mname + " is not public.", 
            Modifier.isPublic(m.getModifiers()));
    }

    /**
     * Asserts that the method is static.
     */
    protected void assertMethodIsStatic(String mname) {
        assertMethodExists(mname);
        Method m = getMethod(mname);
        assertTrue("Method " + mname + " is not static.",
            Modifier.isStatic(m.getModifiers()));
    }

    /**
     * Asserts that the method is final.
     */
    protected void assertMethodIsFinal(String mname) {
        assertMethodExists(mname);
        Method m = getMethod(mname);
        assertTrue("Method " + mname + " is not final.", 
            Modifier.isFinal(m.getModifiers()));
    }

    /**
     * Check type of each method parameter.
     */
    protected void assertParameters(String mname, Class<?>...specifiedParams)
    {
        assertMethodExists(mname);
        Method m = getMethod(mname);
        Class<?>[] declaredParams = m.getParameterTypes();
        if (specifiedParams.length != declaredParams.length)
            fail("Wrong number of parameters passed to " + mname);
        else
        {
            for (int i = 0; i < specifiedParams.length; i++)
                assertEquals("Parameter " + i + " should be type " 
                    + specifiedParams[i].getSimpleName()
                    + " but it is actually "
                    + declaredParams[i].getSimpleName(),
                    declaredParams[i].getSimpleName(),
                    specifiedParams[i].getSimpleName());
        }
    }

    /**
     * Report on a successful test.
     */
    protected void out(String s)
    {
        System.out.print(" + " + s);
    }
    
}
