package checkresults;
import static checkresults.ReflectionSupport.invoke;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AbstractSimpleInheritanceDefinition
{
    protected static String className;
    protected static String superClassName;
    
    protected static Class<?> thisClass;
    protected static Class<?> superClass;

    protected static Object obj;
    protected static Object testObj;
    
    protected static Field[] fields;
    protected static Field[] superFields;
    protected static ArrayList<String> fieldNames = new ArrayList<String>();
    protected static ArrayList<String> superFieldNames = new ArrayList<String>();
    
    protected static Method[] methods;
    protected static Method[] superMethods;
    protected static ArrayList<String> methodNames = new ArrayList<String>();
    protected static ArrayList<String> superMethodNames = new ArrayList<String>();

    protected static Constructor<?>[] constructors;
    protected static ArrayList<String[]> constructorSigs = new ArrayList<String[]>();

    protected static Random gen = new Random();
    
    protected static int curTest;
    protected static int allTests;
    
    private final int METHOD_TYPE = 0;
    private final int METHOD_NAME = 1;
    private final int METHOD_PARAM = 2;
    private final int FIELD_NAME = 0;
    private final int FIELD_TYPE = 1;
    
    // Maximum methods? Start with 10.
    protected String[][] methodData = 
    {
    };
    
    // Maximum methods? Start with 10.
    protected String[][] superMethodData = 
    {
    };
    
    // Maximum fields? Start with 10.
    protected String[][] fieldData =
    {
    };
    
    // Maximum fields? Start with 10.
    protected String[][] superFieldData =
    {
    };
    
    // Constructors?
    protected String[][] constructorData =
    {
    };
    
    // Names of methods to test in order
    // Correctness tests
    protected String[] correctnessData = { };

    // Status flags that can be checked.
    protected static boolean classExists;
    protected static boolean superClassExists;
    
    /**
     * Checks the type and other items.
     */
    protected static void init() throws Exception
    {
        try {
            thisClass = Class.forName(className);
            classExists = true;
            superClass = thisClass.getSuperclass();
            superClassExists = superClass.getSimpleName().equals(superClassName);
            
            fields = thisClass.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fieldNames.add(fields[i].getName());
            }
            
            superFields = superClass.getDeclaredFields();
            for (int i = 0; i < superFields.length; i++) {
                superFieldNames.add(superFields[i].getName());
            }
            
            methods = thisClass.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                methodNames.add(methods[i].getName());
            }

            superMethods = superClass.getDeclaredMethods();
            for (int i = 0; i < superMethods.length; i++) {
                superMethodNames.add(superMethods[i].getName());
            }
            
            constructors = thisClass.getDeclaredConstructors();
            for (Constructor<?> c : constructors)
            {
                Class<?>[] types = c.getParameterTypes();
                String[] params = new String[types.length];
                for (int i = 0; i < types.length; i++)
                    params[i] = types[i].getSimpleName();
                constructorSigs.add(params);
            }
            
            curTest = 0;
        }
        catch (Throwable e) {
            classExists = false;
            fail("Class " + className + " not defined.");
        }
    }

///////////////////// INHERITED TESTS /////////////////////////////////
///////////////////// CLASS CHECK /////////////////////////////////////    
    /**
     * Make sure that the class exists.
     */
    @Test
    public void testClassExists()
    {
        checkClassExists(); 
        out("SYNTAX:  Class " + className + " is defined.");
    }
    
    private void checkClassExists()
    {
        if (! classExists) fail ("SYNTAX: Class " + className + " not defined.");
    }
    
    private void checkSuperClassExists()
    {
        if (! superClassExists) 
            fail("SYNTAX:  Class " + className + " DOES NOT correctly extend " 
            + superClassName + ".");
    }
    
    // Make sure that the class is public
    @Test
    @ScoringWeight(.25)
    public void testClassIsPublic() 
    {
        checkClassExists();
        if (! Modifier.isPublic(thisClass.getModifiers()))
            fail("SYNTAX: Class " + className + " should be public.");
        out("SYNTAX: Class " + className + " is public.");
    }
    
    /**
     * Make sure that the class exists.
     */
    @Test
    @ScoringWeight(4)
    public void testInheritanceCorrect()
    {
        checkSuperClassExists();
        out("SYNTAX:  Class " + className + " correctly extends " 
            + superClassName + ".");
    }
    
///////////////////// FIELD CHECKS /////////////////////////////////////    

    /// PART I: Field Exists
    @Test
    @ScoringWeight(.25)
    public void testFieldExists0() { checkFieldExists(0); }
    @Test
    @ScoringWeight(.25)
    public void testFieldExists1() { checkFieldExists(1); }
    @Test
    @ScoringWeight(.25)
    public void testFieldExists2() { checkFieldExists(2); }
    @Test
    @ScoringWeight(.25)
    public void testFieldExists3() { checkFieldExists(3); }
    @Test
    @ScoringWeight(.25)
    public void testFieldExists4() { checkFieldExists(4); }
    @Test
    @ScoringWeight(.25)
    public void testFieldExists5() { checkFieldExists(5); }
    @Test
    @ScoringWeight(.25)
    public void testFieldExists6() { checkFieldExists(6); }
    @Test
    @ScoringWeight(.25)
    public void testFieldExists7() { checkFieldExists(7); }
    @Test
    @ScoringWeight(.25)
    public void testFieldExists8() { checkFieldExists(8); }
    @Test
    @ScoringWeight(.25)
    public void testFieldExists9() { checkFieldExists(9); }

    @Test
    @ScoringWeight(.25)
    public void testSuperFieldExists0() { checkSuperFieldExists(0); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldExists1() { checkSuperFieldExists(1); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldExists2() { checkSuperFieldExists(2); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldExists3() { checkSuperFieldExists(3); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldExists4() { checkSuperFieldExists(4); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldExists5() { checkSuperFieldExists(5); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldExists6() { checkSuperFieldExists(6); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldExists7() { checkSuperFieldExists(7); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldExists8() { checkSuperFieldExists(8); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldExists9() { checkSuperFieldExists(9); }
    
    /// PART II: Field is private
    @Test
    @ScoringWeight(.25)
    public void testFieldModifiers0() { checkFieldModifiers(0); }
    @Test
    @ScoringWeight(.25)
    public void testFieldModifiers1() { checkFieldModifiers(1); }
    @Test
    @ScoringWeight(.25)
    public void testFieldModifiers2() { checkFieldModifiers(2); }
    @Test
    @ScoringWeight(.25)
    public void testFieldModifiers3() { checkFieldModifiers(3); }
    @Test
    @ScoringWeight(.25)
    public void testFieldModifiers4() { checkFieldModifiers(4); }
    @Test
    @ScoringWeight(.25)
    public void testFieldModifiers5() { checkFieldModifiers(5); }
    @Test
    @ScoringWeight(.25)
    public void testFieldModifiers6() { checkFieldModifiers(6); }
    @Test
    @ScoringWeight(.25)
    public void testFieldModifiers7() { checkFieldModifiers(7); }
    @Test
    @ScoringWeight(.25)
    public void testFieldModifiers8() { checkFieldModifiers(8); }
    @Test
    @ScoringWeight(.25)
    public void testFieldModifiers9() { checkFieldModifiers(9); }

    // Superclass
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldModifiers0() { checkSuperFieldModifiers(0); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldModifiers1() { checkSuperFieldModifiers(1); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldModifiers2() { checkSuperFieldModifiers(2); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldModifiers3() { checkSuperFieldModifiers(3); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldModifiers4() { checkSuperFieldModifiers(4); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldModifiers5() { checkSuperFieldModifiers(5); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldModifiers6() { checkSuperFieldModifiers(6); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldModifiers7() { checkSuperFieldModifiers(7); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldModifiers8() { checkSuperFieldModifiers(8); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldModifiers9() { checkSuperFieldModifiers(9); }
    
    /// PART IV: Field type is correct
    @Test
    @ScoringWeight(.25)
    public void testFieldTypeIsCorrect0() { checkFieldTypeIsCorrect(0); }
    @Test
    @ScoringWeight(.25)
    public void testFieldTypeIsCorrect1() { checkFieldTypeIsCorrect(1); }
    @Test
    @ScoringWeight(.25)
    public void testFieldTypeIsCorrect2() { checkFieldTypeIsCorrect(2); }
    @Test
    @ScoringWeight(.25)
    public void testFieldTypeIsCorrect3() { checkFieldTypeIsCorrect(3); }
    @Test
    @ScoringWeight(.25)
    public void testFieldTypeIsCorrect4() { checkFieldTypeIsCorrect(4); }
    @Test
    @ScoringWeight(.25)
    public void testFieldTypeIsCorrect5() { checkFieldTypeIsCorrect(5); }
    @Test
    @ScoringWeight(.25)
    public void testFieldTypeIsCorrect6() { checkFieldTypeIsCorrect(6); }
    @Test
    @ScoringWeight(.25)
    public void testFieldTypeIsCorrect7() { checkFieldTypeIsCorrect(7); }
    @Test
    @ScoringWeight(.25)
    public void testFieldTypeIsCorrect8() { checkFieldTypeIsCorrect(8); }
    @Test
    @ScoringWeight(.25)
    public void testFieldTypeIsCorrect9() { checkFieldTypeIsCorrect(9); }

    // Superclass
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldTypeIsCorrect0() { checkSuperFieldTypeIsCorrect(0); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldTypeIsCorrect1() { checkSuperFieldTypeIsCorrect(1); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldTypeIsCorrect2() { checkSuperFieldTypeIsCorrect(2); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldTypeIsCorrect3() { checkSuperFieldTypeIsCorrect(3); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldTypeIsCorrect4() { checkSuperFieldTypeIsCorrect(4); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldTypeIsCorrect5() { checkSuperFieldTypeIsCorrect(5); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldTypeIsCorrect6() { checkSuperFieldTypeIsCorrect(6); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldTypeIsCorrect7() { checkSuperFieldTypeIsCorrect(7); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldTypeIsCorrect8() { checkSuperFieldTypeIsCorrect(8); }
    @Test
    @ScoringWeight(.25)
    public void testSuperFieldTypeIsCorrect9() { checkSuperFieldTypeIsCorrect(9); }

    
    // CHECK THAT NUMBER OF FIELDS IS CORRECT
    @Test
    @ScoringWeight(2)
    public void testCorrectNumberOfFieldsDefined()
    {
        assertEquals("SYNTAX: should have exactly " + fieldData.length + " fields defined",
            fieldData.length,
            fieldNames.size());
        out("SYNTAX: correct number of fields defined (" 
            + fieldData.length + ")");
    }
    
///////////////////// METHOD CHECKS /////////////////////////////////////    

    /// PART I: Method Exists
    @Test
    @ScoringWeight(.25)
    public void testMethodExists0() { checkMethodExists(0); }
    @Test
    @ScoringWeight(.25)
    public void testMethodExists1() { checkMethodExists(1); }
    @Test
    @ScoringWeight(.25)
    public void testMethodExists2() { checkMethodExists(2); }
    @Test
    @ScoringWeight(.25)
    public void testMethodExists3() { checkMethodExists(3); }
    @Test
    @ScoringWeight(.25)
    public void testMethodExists4() { checkMethodExists(4); }
    @Test
    @ScoringWeight(.25)
    public void testMethodExists5() { checkMethodExists(5); }
    @Test
    @ScoringWeight(.25)
    public void testMethodExists6() { checkMethodExists(6); }
    @Test
    @ScoringWeight(.25)
    public void testMethodExists7() { checkMethodExists(7); }
    @Test
    @ScoringWeight(.25)
    public void testMethodExists8() { checkMethodExists(8); }
    @Test
    @ScoringWeight(.25)
    public void testMethodExists9() { checkMethodExists(9); }

    /// PART II: Method is public, non-static
    @Test
    @ScoringWeight(.25)
    public void testMethodModifiers0() { checkMethodModifiers(0); }
    @Test
    @ScoringWeight(.25)
    public void testMethodModifiers1() { checkMethodModifiers(1); }
    @Test
    @ScoringWeight(.25)
    public void testMethodModifiers2() { checkMethodModifiers(2); }
    @Test
    @ScoringWeight(.25)
    public void testMethodModifiers3() { checkMethodModifiers(3); }
    @Test
    @ScoringWeight(.25)
    public void testMethodModifiers4() { checkMethodModifiers(4); }
    @Test
    @ScoringWeight(.25)
    public void testMethodModifiers5() { checkMethodModifiers(5); }
    @Test
    @ScoringWeight(.25)
    public void testMethodModifiers6() { checkMethodModifiers(6); }
    @Test
    @ScoringWeight(.25)
    public void testMethodModifiers7() { checkMethodModifiers(7); }
    @Test
    @ScoringWeight(.25)
    public void testMethodModifiers8() { checkMethodModifiers(8); }
    @Test
    @ScoringWeight(.25)
    public void testMethodModifiers9() { checkMethodModifiers(9); }


    /// PART 3: Method type is correct
    @Test
    @ScoringWeight(.25)
    public void testMethodTypeIsCorrect0() { checkMethodTypeIsCorrect(0); }
    @Test
    @ScoringWeight(.25)
    public void testMethodTypeIsCorrect1() { checkMethodTypeIsCorrect(1); }
    @Test
    @ScoringWeight(.25)
    public void testMethodTypeIsCorrect2() { checkMethodTypeIsCorrect(2); }
    @Test
    @ScoringWeight(.25)
    public void testMethodTypeIsCorrect3() { checkMethodTypeIsCorrect(3); }
    @Test
    @ScoringWeight(.25)
    public void testMethodTypeIsCorrect4() { checkMethodTypeIsCorrect(4); }
    @Test
    @ScoringWeight(.25)
    public void testMethodTypeIsCorrect5() { checkMethodTypeIsCorrect(5); }
    @Test
    @ScoringWeight(.25)
    public void testMethodTypeIsCorrect6() { checkMethodTypeIsCorrect(6); }
    @Test
    @ScoringWeight(.25)
    public void testMethodTypeIsCorrect7() { checkMethodTypeIsCorrect(7); }
    @Test
    @ScoringWeight(.25)
    public void testMethodTypeIsCorrect8() { checkMethodTypeIsCorrect(8); }
    @Test
    @ScoringWeight(.25)
    public void testMethodTypeIsCorrect9() { checkMethodTypeIsCorrect(9); }

    /// PART 4: Method parameters are correct
    @Test
    @ScoringWeight(.25)
    public void testMethodParametersAreCorrect0() { checkMethodParametersAreCorrect(0); }
    @Test
    @ScoringWeight(.25)
    public void testMethodParametersAreCorrect1() { checkMethodParametersAreCorrect(1); }
    @Test
    @ScoringWeight(.25)
    public void testMethodParametersAreCorrect2() { checkMethodParametersAreCorrect(2); }
    @Test
    @ScoringWeight(.25)
    public void testMethodParametersAreCorrect3() { checkMethodParametersAreCorrect(3); }
    @Test
    @ScoringWeight(.25)
    public void testMethodParametersAreCorrect4() { checkMethodParametersAreCorrect(4); }
    @Test
    @ScoringWeight(.25)
    public void testMethodParametersAreCorrect5() { checkMethodParametersAreCorrect(5); }
    @Test
    @ScoringWeight(.25)
    public void testMethodParametersAreCorrect6() { checkMethodParametersAreCorrect(6); }
    @Test
    @ScoringWeight(.25)
    public void testMethodParametersAreCorrect7() { checkMethodParametersAreCorrect(7); }
    @Test
    @ScoringWeight(.25)
    public void testMethodParametersAreCorrect8() { checkMethodParametersAreCorrect(8); }
    @Test
    @ScoringWeight(.25)
    public void testMethodParametersAreCorrect9() { checkMethodParametersAreCorrect(9); }

////////// CONSTRUCTORS
    @Test
    @ScoringWeight(2)
    public void testConstructorDefined0() { checkConstructorDefined(0); }
    @Test
    @ScoringWeight(2)
    public void testConstructorDefined1() { checkConstructorDefined(1); }
    @Test
    @ScoringWeight(2)
    public void testConstructorDefined2() { checkConstructorDefined(2); }
    @Test
    @ScoringWeight(2)
    public void testConstructorDefined3() { checkConstructorDefined(3); }
    @Test
    @ScoringWeight(2)
    public void testConstructorDefined4() { checkConstructorDefined(4); }
    

///////// CHECK CONSTRUTOR
    protected void checkConstructorDefined(int num)
    {
        assumeTrue(num < constructorData.length);
        String[] lookFor = constructorData[num];
        boolean found = false;
        for (String[] sig : constructorSigs)
        {
            if (lookFor.length != sig.length) continue;
            int i = 0;
            for (i = 0; i < lookFor.length; i++)
            {
                if(! sig[i].equals(lookFor[i]))
                    break;
            }
            if (i == lookFor.length) 
            {
                found = true;
                break;
            }
        }
        
        String msg = "constructor " + className
            + Arrays.toString(lookFor).replace("[", "(").replace("]",")");
        assertTrue("SYNTAX: Missing " + msg, found);
        out("SYNTAX: Found " + msg);
    }
    
///////// CHECK FOR CORRECTNESS /////////////////
    @Test
    @ScoringWeight(2)
    public void testCorrectness0() throws Throwable { checkCorrectness(0); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness1() throws Throwable { checkCorrectness(1); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness2() throws Throwable { checkCorrectness(2); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness3() throws Throwable { checkCorrectness(3); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness4() throws Throwable { checkCorrectness(4); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness5() throws Throwable { checkCorrectness(5); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness6() throws Throwable { checkCorrectness(6); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness7() throws Throwable { checkCorrectness(7); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness8() throws Throwable { checkCorrectness(8); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness9() throws Throwable { checkCorrectness(9); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness10() throws Throwable { checkCorrectness(10); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness11() throws Throwable { checkCorrectness(11); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness12() throws Throwable { checkCorrectness(12); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness13() throws Throwable { checkCorrectness(13); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness14() throws Throwable { checkCorrectness(14); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness15() throws Throwable { checkCorrectness(15); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness16() throws Throwable { checkCorrectness(16); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness17() throws Throwable { checkCorrectness(17); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness18() throws Throwable { checkCorrectness(18); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness19() throws Throwable { checkCorrectness(19); }
    @Test
    @ScoringWeight(2)
    public void testCorrectness20() throws Throwable { checkCorrectness(19); }
    
    protected void checkCorrectness(int num) throws Throwable
    {
        assumeTrue(num < correctnessData.length);
        String testName = correctnessData[num];
        try {
            testObj = Class.forName(className + "Test").newInstance();
            invoke(testObj, testName);
        } 
        catch (IllegalAccessException iae) {
            fail("Cannot create test object.");
        }
        catch (InstantiationException e) {
            fail("Cannot create test object.");
        }
        catch (ClassNotFoundException e) {
            fail("Cannot create test object.");
        }
        catch (RuntimeException e) {
            throw e.getCause();
        }
    }
    
///////// PROTECTED METHODS TO PROCESS CLASSES    
    
    /**
     * Check if a class name is defined.
     */
    protected boolean isClassDefined(String className1)
    {
        try {
            Class.forName(className1);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    
    protected Field getField(String fName)
    {
        int pos = fieldNames.indexOf(fName);
        if (pos >= 0)
            return fields[pos];
        return null;
    }
    
    protected Field getSuperField(String fName)
    {
        int pos = superFieldNames.indexOf(fName);
        if (pos >= 0)
                return superFields[pos];
        return null;
    }
    
    protected Method getMethod(String mName)
    {
        int pos = methodNames.indexOf(mName);
        if (pos >= 0) return methods[pos];
        return null;
    }
    
    protected Method getSuperMethod(String mName)
    {
        int pos = superMethodNames.indexOf(mName);
        if (pos >= 0) return superMethods[pos];
        return null;
    }
    
    // FIELDS ////////////////////////////////////////////////////
    protected void checkFieldExists(int num)
    {
        assumeTrue(num < fieldData.length);
        checkFieldExists(fieldData[num][FIELD_NAME]);
    }

    protected void checkSuperFieldExists(int num)
    {
        assumeTrue(num < superFieldData.length);
        checkSuperFieldExists(superFieldData[num][FIELD_NAME]);
    }
    
    protected void checkFieldExists(String name)
    {
        checkClassExists();
        if (fieldNames.contains(name))
            out("SYNTAX: Field " + name + " found in " + className);
        else
            fail("SYNTAX: Field " + name + " is not found.");
    }
    
    protected void checkSuperFieldExists(String name)
    {
        checkSuperClassExists();
        if (superFieldNames.contains(name))
            out("SYNTAX: Field " + name + " found in " + superClassName);
        else
            fail("SYNTAX: Field " + name + " is not found.");
    }
    
    protected void checkFieldIsPrivate(int num)
    {
        assumeTrue(num < fieldData.length);
        checkFieldIsPrivate(fieldData[num][FIELD_NAME]);
    }
    
    protected void checkFieldModifiers(int num)
    {
        assumeTrue(num < fieldData.length);
        checkFieldModifiers(fieldData[num][FIELD_NAME]);
    }
    
    protected void checkSuperFieldModifiers(int num)
    {
        assumeTrue(num < superFieldData.length);
        checkSuperFieldModifiers(superFieldData[num][FIELD_NAME]);
    }
    
    protected void checkMethodModifiers(int num)
    {
        assumeTrue(num < methodData.length);
        checkMethodModifiers(methodData[num][METHOD_NAME]);
    }
    
    protected void checkFieldModifiers(String name)
    {
        checkClassExists();
        if (! fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (Modifier.isPrivate(fname.getModifiers())
            && ! Modifier.isStatic(fname.getModifiers()))
            out("SYNTAX: Field " + name + " uses correct modifiers.");
        else
            if (! Modifier.isPrivate(fname.getModifiers()))
                fail("SYNTAX: Field " + name + " is not private. (Should be)");
            else if (Modifier.isStatic(fname.getModifiers()))
                fail("SYNTAX: Field " + name + " declared static. (Should NOT be)");
    }

    protected void checkSuperFieldModifiers(String name)
    {
        checkSuperClassExists();
        if (! superFieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getSuperField(name);
        if (Modifier.isPrivate(fname.getModifiers())
            && ! Modifier.isStatic(fname.getModifiers()))
            out("SYNTAX: Field " + name + " uses correct modifiers.");
        else
            if (! Modifier.isPrivate(fname.getModifiers()))
                fail("SYNTAX: Field " + name + " is not private. (Should be)");
            else if (Modifier.isStatic(fname.getModifiers()))
                fail("SYNTAX: Field " + name + " declared static. (Should NOT be)");
    }
    
    protected void checkMethodModifiers(String name)
    {
        checkClassExists();
        if (! methodNames.contains(name))
            fail("SYNTAX: Method " + name + " not found in class.");
        Method fname = getMethod(name);
        if (Modifier.isPublic(fname.getModifiers())
            && ! Modifier.isStatic(fname.getModifiers()))
            out("SYNTAX: Method " + name + " uses correct modifiers.");
        else
            if (! Modifier.isPublic(fname.getModifiers()))
                fail("SYNTAX: Method " + name + " is NOT public. (Should be)");
            else if (Modifier.isStatic(fname.getModifiers()))
                fail("SYNTAX: Method " + name + " declared static. (Should NOT be)");
    }
    
    protected void checkFieldIsPrivate(String name)
    {
        checkClassExists();
        if (! fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (Modifier.isPrivate(fname.getModifiers()))
            out("SYNTAX: Field " + name + " is private.");
        else
            fail("SYNTAX: Field " + name + " is not private. (Should be)");
    }

    protected void checkFieldIsNotStatic(int num)
    {
        assumeTrue(num < fieldData.length);
        checkFieldIsNotStatic(fieldData[num][FIELD_NAME]);
    }
    
    protected void checkFieldIsNotStatic(String name)
    {
        checkClassExists();
        if (! fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (! Modifier.isStatic(fname.getModifiers()))
            out("SYNTAX: Field " + name + " is not static.");
        else
            fail("SYNTAX: Field " + name + " is static. (Should NOT be)");
    }

    protected void checkFieldTypeIsCorrect(int num)
    {
        assumeTrue(num < fieldData.length);
        String desiredType = fieldData[num][FIELD_TYPE]; // desired type
        if (desiredType.equals("String"))
            checkFieldIsTypeString(fieldData[num][FIELD_NAME]);
        else if (desiredType.equals("int"))
            checkFieldIsTypeInt(fieldData[num][FIELD_NAME]);
        else if (desiredType.equals("double"))
            checkFieldIsTypeDouble(fieldData[num][FIELD_NAME]);
        else if (desiredType.equals("boolean"))
            checkFieldIsTypeBoolean(fieldData[num][FIELD_NAME]);
        else 
            fail("SYNTAX: No type for " + fieldData[num][FIELD_NAME] 
                     + "? CHECK TEST PROGRAM STEVE.");
    }
    
    protected void checkSuperFieldTypeIsCorrect(int num)
    {
        assumeTrue(num < superFieldData.length);
        String desiredType = superFieldData[num][FIELD_TYPE]; // desired type
        if (desiredType.equals("String"))
            checkSuperFieldIsTypeString(fieldData[num][FIELD_NAME]);
        else if (desiredType.equals("int"))
            checkSuperFieldIsTypeInt(fieldData[num][FIELD_NAME]);
        else if (desiredType.equals("double"))
            checkSuperFieldIsTypeDouble(fieldData[num][FIELD_NAME]);
        else if (desiredType.equals("boolean"))
            checkSuperFieldIsTypeBoolean(fieldData[num][FIELD_NAME]);
        else 
            fail("SYNTAX: No type for " + fieldData[num][FIELD_NAME] 
                     + "? CHECK TEST PROGRAM STEVE.");
    }
    

    protected void checkFieldIsTypeString(String name)
    {
        checkClassExists();
        if (! fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (fname.getType() == String.class)
            out("SYNTAX: Field " + name + " is String type.");
        else
            fail("SYNTAX: Field " + name + " is not String type. (Should be)");
    }
    
    protected void checkFieldIsTypeDouble(String name)
    {
        checkClassExists();
        if (! fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (fname.getType() == double.class)
            out("SYNTAX: Field " + name + " is double type.");
        else
            fail("SYNTAX: Field " + name + " is not double type. (Should be)");
    }
    
    protected void checkFieldIsTypeInt(String name)
    {
        checkClassExists();
        if (! fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (fname.getType() == int.class)
            out("SYNTAX: Field " + name + " is int type.");
        else
            fail("SYNTAX: Field " + name + " is not int type. (Should be)");
    }
    
    protected void checkFieldIsTypeBoolean(String name)
    {
        checkClassExists();
        if (! fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (fname.getType() == boolean.class)
            out("SYNTAX: Field " + name + " is boolean type.");
        else
            fail("SYNTAX: Field " + name + " is not boolean type. (Should be)");
    }
    
    protected void checkSuperFieldIsTypeString(String name)
    {
        checkSuperClassExists();
        if (! superFieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getSuperField(name);
        if (fname.getType() == String.class)
            out("SYNTAX: Field " + name + " is String type.");
        else
            fail("SYNTAX: Field " + name + " is not String type. (Should be)");
    }
    
    protected void checkSuperFieldIsTypeDouble(String name)
    {
        checkSuperClassExists();
        if (! superFieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getSuperField(name);
        if (fname.getType() == double.class)
            out("SYNTAX: Field " + name + " is double type.");
        else
            fail("SYNTAX: Field " + name + " is not double type. (Should be)");
    }
    
    protected void checkSuperFieldIsTypeInt(String name)
    {
        checkSuperClassExists();
        if (! superFieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getSuperField(name);
        if (fname.getType() == int.class)
            out("SYNTAX: Field " + name + " is int type.");
        else
            fail("SYNTAX: Field " + name + " is not int type. (Should be)");
    }
    
    protected void checkSuperFieldIsTypeBoolean(String name)
    {
        checkSuperClassExists();
        if (! superFieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getSuperField(name);
        if (fname.getType() == boolean.class)
            out("SYNTAX: Field " + name + " is boolean type.");
        else
            fail("SYNTAX: Field " + name + " is not boolean type. (Should be)");
    }
    
    // METHODS ////////////////////////////////////////////////////
    protected void checkMethodExists(int num)
    {
        assumeTrue(num < methodData.length);
        checkMethodExists(methodData[num][METHOD_NAME]);
    }
    
    protected void checkMethodExists(String name)
    {
        checkClassExists();
        if (methodNames.contains(name))
            out("SYNTAX: Method " + name + " found in " + className);
        else
            fail("SYNTAX: Method " + name + " is not found.");
    }
    
    protected void checkMethodIsPublic(int num)
    {
        assumeTrue(num < methodData.length);
        checkMethodIsPublic(methodData[num][METHOD_NAME]);
    }
    
    protected void checkMethodIsPublic(String name)
    {
        checkClassExists();
        if (! methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (Modifier.isPublic(fname.getModifiers()))
            out("SYNTAX: Correct - " + name + " is public.");
        else
            fail("SYNTAX: Method " + name + " is not public. (Should be)");
    }

    protected void checkMethodIsNotStatic(int num)
    {
        assumeTrue(num < methodData.length);
        checkMethodIsNotStatic(methodData[num][METHOD_NAME]);
    }
    
    protected void checkMethodIsNotStatic(String name)
    {
        checkClassExists();
        if (! methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (! Modifier.isStatic(fname.getModifiers()))
            out("SYNTAX: Method " + name + " is not static.");
        else
            fail("SYNTAX: Method " + name + " is static. (Should NOT be)");
    }

    protected void checkMethodTypeIsCorrect(int num)
    {
        assumeTrue(num < methodData.length);
        String desiredType = methodData[num][METHOD_TYPE]; // desired type
        if (desiredType.equals("String"))
            checkMethodIsTypeString(methodData[num][METHOD_NAME]);
        else if (desiredType.equals("int"))
            checkMethodIsTypeInt(methodData[num][METHOD_NAME]);
        else if (desiredType.equals("double"))
            checkMethodIsTypeDouble(methodData[num][METHOD_NAME]);
        else if (desiredType.equals("boolean"))
            checkMethodIsTypeBoolean(methodData[num][METHOD_NAME]);
        else if (desiredType.equals("void"))
            checkMethodIsTypeVoid(methodData[num][METHOD_NAME]);
        else 
            fail("SYNTAX: No type for " + methodData[num][METHOD_NAME] + 
                "? CHECK TEST PROGRAM STEVE.");
    }
    
    protected void checkMethodIsTypeString(String name)
    {
        checkClassExists();
        if (! methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (fname.getReturnType() == String.class)
            out("SYNTAX: Method " + name + " is String return type.");
        else
            fail("SYNTAX: Method " + name + " is not String return type. (Should be)");
    }
    
    protected void checkMethodIsTypeDouble(String name)
    {
        checkClassExists();
        if (! methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (fname.getReturnType() == double.class)
            out("SYNTAX: Method " + name + " is double return type.");
        else
            fail("SYNTAX: Method " + name + " is not double return type. (Should be)");
    }
    
    protected void checkMethodIsTypeInt(String name)
    {
        checkClassExists();
        if (! methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (fname.getReturnType() == int.class)
            out("SYNTAX: Method " + name + " is int return type.");
        else
            fail("SYNTAX: Method " + name + " is not int return type. (Should be)");
    }
    
    protected void checkMethodIsTypeBoolean(String name)
    {
        checkClassExists();
        if (! methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (fname.getReturnType() == boolean.class)
            out("SYNTAX: Method " + name + " is boolean return type.");
        else
            fail("SYNTAX: Method " + name + " is not boolean return type. (Should be)");
    }
    
    protected void checkMethodIsTypeVoid(String name)
    {
        checkClassExists();
        if (! methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (fname.getReturnType() == void.class)
            out("SYNTAX: Method " + name + " is void return type.");
        else
            fail("SYNTAX: Method " + name + " is not void return type. (Should be)");
    }
    
    protected void checkMethodParametersAreCorrect(int num)
    {
        assumeTrue(num < methodData.length);
        int numParameters = methodData[num].length - 2; // number of parameters
        String methodName = methodData[num][METHOD_NAME];
        String desiredSig = "(";
        if (numParameters <= 0)
            desiredSig += ")";
        else
        {
            desiredSig += methodData[num][METHOD_PARAM];
            for (int i = 1; i < numParameters; i++)
            {
                desiredSig += ", " + methodData[num][METHOD_PARAM + i];
            }
            desiredSig += ")";
        }
        
        // Now get the actual parameters.
        Method m = null;
        int pos = -1;
        int count = 0;
        for (int i = 0; i < methods.length; i++)
        {
            if (methods[i].getName().equals(methodName))
            {
                count++;
                pos = i;
            }
        }
        
        if (count == 0)
            fail("SYNTAX: Method " + methodName + " not found.");
        else if (count > 1)
        {
            // OK, handle multiple versions of method; OK
        }
        else
        {
            // Only one version - a little easier.
            m = methods[pos];
            String actualSig = "(";
            Class<?>[] params = m.getParameterTypes();
            for (int i = 0; i < params.length; i++)
                actualSig += (i == 0) 
                    ? params[i].getSimpleName()
                    : ", " + params[i].getSimpleName();
            actualSig += ")";
            
            assertEquals("SYNTAX: Method " + methodName + " parameters: ",
                desiredSig,
                actualSig);
            
            out("SYNTAX: Method " + methodName + " has correct parameters");
        }
    }
 
    /**
     * Report on a successful test.
     */
    protected void out(String s)
    {
        System.out.print(" + " + s);
    }
    
/////////////// TEST FIELDS AND MUTATORS ////////////////////////////    
    protected void testIntMutator(String name, String fname, int arg, int expected)
    {
        try {
            
            Object[] args = {arg};
            invoke(obj, name, args);
            Field f = getField(fname);
            f.setAccessible(true);
            int actual = (Integer) (f.get(obj));
            assertEquals("INCORRECT--calling " + name + "(" + arg + "): ",
                expected, actual);
            out("CORRECT--calling " + name + "(" + arg + ")");
        } catch (Exception e) {
            fail("CRASH calling  " + name + "(" + arg + ")");
        }
    }
    
    protected void testIntsMutator(String name, String fname, 
        int expected, Object...args)
    {
        String desc = getParamDesc(args);
        try {
            invoke(obj, name, args);
            Field f = getField(fname);
            f.setAccessible(true);
            int actual = (Integer) (f.get(obj));
            assertEquals("INCORRECT--calling " + name + "(" + desc + "): ",
                expected, actual);
            out("CORRECT--calling " + name + "(" + desc + ")");
        } catch (Exception e) {
            fail("CRASH calling  " + name + "(" + desc + ")");
        }
    }
    
    protected void testDoubleMutator(String name, String fname, 
        double arg, double expected)
    {
        final double tolerance = 1E-10;
        try {
            Object[] args = {arg};
            invoke(obj, name, args);
            Field f = getField(fname);
            f.setAccessible(true);
            double actual = (Double) (f.get(obj));
            assertEquals("INCORRECT--calling " + name + "(" + arg + "): ",
                expected, actual, tolerance);
            out("CORRECT--calling " + name + "(" + arg + ")");
        } catch (Exception e) {
            fail("CRASH calling  " + name + "(" + arg + ")");
        }
    }
    
    protected void testStringMutator(String name, String fname, 
        String arg, String expected)
    {
        try {
            Object[] args = {arg};
            invoke(obj, name, args);
            Field f = getField(fname);
            f.setAccessible(true);
            String actual = (String) (f.get(obj));
            assertEquals("INCORRECT--calling " + name + "(\"" + arg + "\"): ",
                expected, actual);
            out("CORRECT--calling " + name + "(\"" + arg + "\")");
        } catch (Exception e) {
            fail("CRASH calling  " + name + "(\"" + arg + "\")");
        }
    }
    
    protected void testIntAccessor(String name, int expected, Object...args)
    {
        try {
            int actual = args.length == 0 ? 
                invoke(obj, Integer.class, name) :
                invoke(obj, Integer.class, name, args);
            assertEquals("INCORRECT--calling " + name + "(): ",
                expected, actual);
            out("CORRECT--calling " + name + "()-> " + expected);
        } catch (Exception e) {
            fail("CRASH calling  " + name + "()");
        }
    }
    
    protected void testStringAccessor(String name, String expected, Object...args)
    {
        try {
            String actual = args.length == 0 ? 
                invoke(obj, String.class, name) :
                invoke(obj, String.class, name, args);
            assertEquals("INCORRECT--calling " + name + "(): ",
                expected, actual);
            out("CORRECT--calling " + name + "()-> " + expected);
        } catch (Exception e) {
            fail("CRASH calling  " + name + "()");
        }
    }
    
    protected void testDoubleAccessor(String name, double expected, Object...args)
    {
        double tolerance = .005;
        try {
            double actual = args.length == 0 ?
                invoke(obj, Double.class, name) :
                invoke(obj, Double.class, name, args);    
            assertEquals("INCORRECT--calling " + name + "(): ",
                expected, actual, tolerance);
            out("CORRECT--calling " + name + "()-> " + expected);
        } catch (Exception e) {
            fail("CRASH calling  " + name + "()");
        }
    }
    
    protected void testIntFieldInitialization(String name, int value)
    {
        try {
            Field f = getField(name);
            f.setAccessible(true);
            int actual = (Integer) (f.get(obj));
            assertEquals("INCORRECT: " + name + " initialization: ",
                value,
                actual);
            out("CORRECT: " + name + " initialization.");
        } catch (Exception e) {
            fail("CRASH: Cannot extract field " + name + " value.");
        }
    }
    
    protected void testDoubleFieldInitialization(String name, double value)
    {
        try {
            Field f = getField(name);
            f.setAccessible(true);
            double actual = (Double) (f.get(obj));
            assertEquals("INCORRECT: " + name + " initialization: ",
                value,
                actual, 1E-10);
            out("CORRECT: " + name + " initialization.");
        } catch (Exception e) {
            fail("CRASH: Cannot extract field " + name + " value.");
        }
    }
    
    protected void testStringFieldInitialization(String name, String value)
    {
        try {
            Field f = getField(name);
            f.setAccessible(true);
            String actual = (String) (f.get(obj));
            assertEquals("INCORRECT: " + name + " initialization: ",
                value,
                actual);
            out("CORRECT: " + name + " initialization.");
        } catch (Exception e) {
            fail("CRASH: Cannot extract field " + name + " value.");
        }
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
    
    
}
