package checkresults;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import java.lang.reflect.*;
import java.util.*;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static checkresults.ReflectionSupport.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AbstractClassDefinitionTest
{
    protected static String className;
    protected static String fullClassName;
    protected static Class<?> thisClass;
    protected static Object obj;
    protected static Field[] fields;
    protected static ArrayList<String> fieldNames = new ArrayList<>();
    protected static Method[] methods;
    protected static ArrayList<String> methodNames = new ArrayList<>();
    protected static Constructor<?>[] constructors;
    protected static ArrayList<String[]> constructorSigs = new ArrayList<>();
    protected ArrayList<String> testNames = new ArrayList<>();

    protected Random gen = new Random();
    protected double tolerance = 1E-8;

    private final int METHOD_TYPE = 0;
    private final int METHOD_NAME = 1;
    private final int METHOD_PARAM = 2;
    private final int FIELD_NAME = 0;
    private final int FIELD_TYPE = 1;

    protected void getTests(Class<?> klass)
    {
        Method[] testMethods = klass.getDeclaredMethods();
        for (Method m : testMethods)
            testNames.add(m.getName());
    }

    // Maximum methods? Start with 10.
    protected String[][] methodData = {};

    // Maximum fields? Start with 10.
    protected String[][] fieldData = {};

    // Constructors?
    protected String[][] constructorData = {};

    // Status flags that can be checked.
    protected static boolean classExists;

    /**
     * Checks the type and other items.
     */
    protected static void init() throws Exception
    {
        fieldNames = new ArrayList<>();
        methodNames = new ArrayList<>();
        constructorSigs = new ArrayList<>();
        try
        {
            thisClass = Class.forName(className);
            classExists = true;
            if (className.lastIndexOf(".") > 0)
                className = className.substring(className.lastIndexOf('.') + 1);
            fields = thisClass.getDeclaredFields();
            for (int i = 0; i < fields.length; i++)
            {
                fieldNames.add(fields[i].getName());
            }

            methods = thisClass.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++)
            {
                methodNames.add(methods[i].getName());
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

        }
        catch (Throwable e)
        {
            classExists = false;
            fail("Class " + className + " not defined.");
        }
    }

    private void test(int n)
    {
        final String name = "_test_" + n;
        assumeTrue(testNames.contains(name));
        invoke(this, name);
        
    }

    @Test(timeout = 200) @ScoringWeight(2.0) public void test01() {test(1);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test02() {test(2);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test03() {test(3);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test04() {test(4);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test05() {test(5);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test06() {test(6);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test07() {test(7);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test08() {test(8);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test09() {test(9);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test10() {test(10);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test11() {test(11);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test12() {test(12);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test13() {test(13);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test14() {test(14);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test15() {test(15);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test16() {test(16);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test17() {test(17);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test18() {test(18);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test19() {test(19);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test20() {test(20);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test21() {test(21);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test22() {test(22);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test23() {test(23);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test24() {test(24);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test25() {test(25);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test26() {test(26);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test27() {test(27);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test28() {test(28);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test29() {test(29);}
    @Test(timeout = 200) @ScoringWeight(2.0) public void test30() {test(30);}


    // /////////////////// INHERITED TESTS /////////////////////////////////
    // /////////////////// CLASS CHECK /////////////////////////////////////
    /**
     * Make sure that the class exists.
     */
    @Test
    public void _000ClassExists()
    {
        checkClassExists();
        out("Syntax: Class " + className + " is defined.");
    }

    private void checkClassExists()
    {
        if (!classExists) fail("SYNTAX: Class " + className + " not defined.");
    }

    // Make sure that the class is public
    @Test
    public void _001ClassIsPublic()
    {
        checkClassExists();
        if (!Modifier.isPublic(thisClass.getModifiers()))
            fail("SYNTAX: Class " + className + " should be public.");
        out("Syntax: Class " + className + " is public.");
    }

    // /////////////////// FIELD CHECKS /////////////////////////////////////

    // / PART I: Field Exists
    @Test
    @ScoringWeight(.25)
    public void _002test02()
    {
        checkFieldExists(0);
    }

    @Test
    @ScoringWeight(.25)
    public void _003test03()
    {
        checkFieldExists(1);
    }

    @Test
    @ScoringWeight(.25)
    public void _004test04()
    {
        checkFieldExists(2);
    }

    @Test
    @ScoringWeight(.25)
    public void _005test05()
    {
        checkFieldExists(3);
    }

    @Test
    @ScoringWeight(.25)
    public void _006test06()
    {
        checkFieldExists(4);
    }

    @Test
    @ScoringWeight(.25)
    public void _007test07()
    {
        checkFieldExists(5);
    }

    @Test
    @ScoringWeight(.25)
    public void _008test08()
    {
        checkFieldExists(6);
    }

    @Test
    @ScoringWeight(.25)
    public void _009test09()
    {
        checkFieldExists(7);
    }

    @Test
    @ScoringWeight(.25)
    public void _010test10()
    {
        checkFieldExists(8);
    }

    @Test
    @ScoringWeight(.25)
    public void _011test11()
    {
        checkFieldExists(9);
    }

    // / PART II: Field is private
    @Test
    @ScoringWeight(.25)
    public void _012test12()
    {
        checkFieldModifiers(0);
    }

    @Test
    @ScoringWeight(.25)
    public void _013test13()
    {
        checkFieldModifiers(1);
    }

    @Test
    @ScoringWeight(.25)
    public void _014test14()
    {
        checkFieldModifiers(2);
    }

    @Test
    @ScoringWeight(.25)
    public void _015test15()
    {
        checkFieldModifiers(3);
    }

    @Test
    @ScoringWeight(.25)
    public void _016test16()
    {
        checkFieldModifiers(4);
    }

    @Test
    @ScoringWeight(.25)
    public void _017test17()
    {
        checkFieldModifiers(5);
    }

    @Test
    @ScoringWeight(.25)
    public void _018test18()
    {
        checkFieldModifiers(6);
    }

    @Test
    @ScoringWeight(.25)
    public void _019test19()
    {
        checkFieldModifiers(7);
    }

    @Test
    @ScoringWeight(.25)
    public void _020test20()
    {
        checkFieldModifiers(8);
    }

    @Test
    @ScoringWeight(.25)
    public void _021test21()
    {
        checkFieldModifiers(9);
    }

    // / PART IV: Field type is correct
    @Test
    @ScoringWeight(.25)
    public void _022test22()
    {
        checkFieldTypeIsCorrect(0);
    }

    @Test
    @ScoringWeight(.25)
    public void _023test23()
    {
        checkFieldTypeIsCorrect(1);
    }

    @Test
    @ScoringWeight(.25)
    public void _024test24()
    {
        checkFieldTypeIsCorrect(2);
    }

    @Test
    @ScoringWeight(.25)
    public void _025test25()
    {
        checkFieldTypeIsCorrect(3);
    }

    @Test
    @ScoringWeight(.25)
    public void _026test26()
    {
        checkFieldTypeIsCorrect(4);
    }

    @Test
    @ScoringWeight(.25)
    public void _027test27()
    {
        checkFieldTypeIsCorrect(5);
    }

    @Test
    @ScoringWeight(.25)
    public void _028test28()
    {
        checkFieldTypeIsCorrect(6);
    }

    @Test
    @ScoringWeight(.25)
    public void _029test29()
    {
        checkFieldTypeIsCorrect(7);
    }

    @Test
    @ScoringWeight(.25)
    public void _030test30()
    {
        checkFieldTypeIsCorrect(8);
    }

    @Test
    @ScoringWeight(.25)
    public void _031test31()
    {
        checkFieldTypeIsCorrect(9);
    }

    // /////////////////// METHOD CHECKS /////////////////////////////////////

    // / PART I: Method Exists
    @Test
    @ScoringWeight(.25)
    public void _032test32()
    {
        checkMethodExists(0);
    }

    @Test
    @ScoringWeight(.25)
    public void _033test33()
    {
        checkMethodExists(1);
    }

    @Test
    @ScoringWeight(.25)
    public void _034test34()
    {
        checkMethodExists(2);
    }

    @Test
    @ScoringWeight(.25)
    public void _035test35()
    {
        checkMethodExists(3);
    }

    @Test
    @ScoringWeight(.25)
    public void _036test36()
    {
        checkMethodExists(4);
    }

    @Test
    @ScoringWeight(.25)
    public void _037test37()
    {
        checkMethodExists(5);
    }

    @Test
    @ScoringWeight(.25)
    public void _038test38()
    {
        checkMethodExists(6);
    }

    @Test
    @ScoringWeight(.25)
    public void _039test39()
    {
        checkMethodExists(7);
    }

    @Test
    @ScoringWeight(.25)
    public void _040test40()
    {
        checkMethodExists(8);
    }

    @Test
    @ScoringWeight(.25)
    public void _041test41()
    {
        checkMethodExists(9);
    }

    // / PART II: Method is public, non-static
    @Test
    @ScoringWeight(.25)
    public void _042test42()
    {
        checkMethodModifiers(0);
    }

    @Test
    @ScoringWeight(.25)
    public void _043test43()
    {
        checkMethodModifiers(1);
    }

    @Test
    @ScoringWeight(.25)
    public void _044test44()
    {
        checkMethodModifiers(2);
    }

    @Test
    @ScoringWeight(.25)
    public void _045test45()
    {
        checkMethodModifiers(3);
    }

    @Test
    @ScoringWeight(.25)
    public void _046test46()
    {
        checkMethodModifiers(4);
    }

    @Test
    @ScoringWeight(.25)
    public void _047test47()
    {
        checkMethodModifiers(5);
    }

    @Test
    @ScoringWeight(.25)
    public void _048test48()
    {
        checkMethodModifiers(6);
    }

    @Test
    @ScoringWeight(.25)
    public void _049test49()
    {
        checkMethodModifiers(7);
    }

    @Test
    @ScoringWeight(.25)
    public void _050test50()
    {
        checkMethodModifiers(8);
    }

    @Test
    @ScoringWeight(.25)
    public void _051test51()
    {
        checkMethodModifiers(9);
    }

    // / PART 3: Method type is correct
    @Test
    @ScoringWeight(.25)
    public void _052test52()
    {
        checkMethodTypeIsCorrect(0);
    }

    @Test
    @ScoringWeight(.25)
    public void _053test53()
    {
        checkMethodTypeIsCorrect(1);
    }

    @Test
    @ScoringWeight(.25)
    public void _054test54()
    {
        checkMethodTypeIsCorrect(2);
    }

    @Test
    @ScoringWeight(.25)
    public void _055test55()
    {
        checkMethodTypeIsCorrect(3);
    }

    @Test
    @ScoringWeight(.25)
    public void _056test56()
    {
        checkMethodTypeIsCorrect(4);
    }

    @Test
    @ScoringWeight(.25)
    public void _057test57()
    {
        checkMethodTypeIsCorrect(5);
    }

    @Test
    @ScoringWeight(.25)
    public void _058test58()
    {
        checkMethodTypeIsCorrect(6);
    }

    @Test
    @ScoringWeight(.25)
    public void _059test59()
    {
        checkMethodTypeIsCorrect(7);
    }

    @Test
    @ScoringWeight(.25)
    public void _060test60()
    {
        checkMethodTypeIsCorrect(8);
    }

    @Test
    @ScoringWeight(.25)
    public void _061test61()
    {
        checkMethodTypeIsCorrect(9);
    }

    // / PART 4: Method parameters are correct
    @Test
    @ScoringWeight(.25)
    public void _062test62()
    {
        checkMethodParametersAreCorrect(0);
    }

    @Test
    @ScoringWeight(.25)
    public void _063test63()
    {
        checkMethodParametersAreCorrect(1);
    }

    @Test
    @ScoringWeight(.25)
    public void _064test64()
    {
        checkMethodParametersAreCorrect(2);
    }

    @Test
    @ScoringWeight(.25)
    public void _065test65()
    {
        checkMethodParametersAreCorrect(3);
    }

    @Test
    @ScoringWeight(.25)
    public void _066test66()
    {
        checkMethodParametersAreCorrect(4);
    }

    @Test
    @ScoringWeight(.25)
    public void _067test67()
    {
        checkMethodParametersAreCorrect(5);
    }

    @Test
    @ScoringWeight(.25)
    public void _068test68()
    {
        checkMethodParametersAreCorrect(6);
    }

    @Test
    @ScoringWeight(.25)
    public void _069test69()
    {
        checkMethodParametersAreCorrect(7);
    }

    @Test
    @ScoringWeight(.25)
    public void _070test70()
    {
        checkMethodParametersAreCorrect(8);
    }

    @Test
    @ScoringWeight(.25)
    public void _071test71()
    {
        checkMethodParametersAreCorrect(9);
    }

    // //////// CONSTRUCTORS
    @Test
    @ScoringWeight(2)
    public void _072test72()
    {
        checkConstructorDefined(0);
    }

    @Test
    @ScoringWeight(2)
    public void _073test73()
    {
        checkConstructorDefined(1);
    }

    @Test
    @ScoringWeight(2)
    public void _074test74()
    {
        checkConstructorDefined(2);
    }

    @Test
    @ScoringWeight(2)
    public void _075test75()
    {
        checkConstructorDefined(3);
    }

    @Test
    @ScoringWeight(2)
    public void _076test76()
    {
        checkConstructorDefined(4);
    }

    // /////// CHECK CONSTRUTOR
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
                if (!sig[i].equals(lookFor[i])) break;
            }
            if (i == lookFor.length)
            {
                found = true;
                break;
            }
        }

        String msg = "constructor " + className
                + Arrays.toString(lookFor).replace("[", "(").replace("]", ")");
        assertTrue("Missing " + msg, found);
        out("Found " + msg);
    }

    // /////// PROTECTED METHODS TO PROCESS CLASSES

    /**
     * Check if a class name is defined.
     */
    protected boolean isClassDefined(String klassName)
    {
        try
        {
            Class.forName(klassName);
            return true;
        }
        catch (ClassNotFoundException e)
        {
            return false;
        }
    }

    protected Field getField(String fName)
    {
        int pos = fieldNames.indexOf(fName);
        if (pos >= 0) return fields[pos];
        return null;
    }

    protected Method getMethod(String mName)
    {
        int pos = methodNames.indexOf(mName);
        if (pos >= 0) return methods[pos];
        return null;
    }

    // FIELDS ////////////////////////////////////////////////////
    protected void checkFieldExists(int num)
    {
        assumeTrue(num < fieldData.length);
        checkFieldExists(fieldData[num][FIELD_NAME]);
    }

    protected void checkFieldExists(String name)
    {
        checkClassExists();
        if (name.equals("?")) // can have any name
            out("Syntax: Field <any name> found in " + className);
        else if (fieldNames.contains(name))
            out("Syntax: Field " + name + " found in " + className);
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

    protected void checkMethodModifiers(int num)
    {
        assumeTrue(num < methodData.length);
        checkMethodModifiers(methodData[num][METHOD_NAME]);
    }

    protected void checkFieldModifiers(String name)
    {
        checkClassExists();
        if (!fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (Modifier.isPrivate(fname.getModifiers())
                && !Modifier.isStatic(fname.getModifiers()))
            out("Syntax: Field " + name + " uses correct modifiers.");
        else if (!Modifier.isPrivate(fname.getModifiers()))
            fail("SYNTAX: Field " + name + " is not private. (Should be)");
        else if (Modifier.isStatic(fname.getModifiers()))
            fail("SYNTAX: Field " + name + " declared static. (Should NOT be)");
    }

    protected void checkMethodModifiers(String name)
    {
        checkClassExists();
        if (!methodNames.contains(name))
            fail("SYNTAX: Method " + name + " not found in class.");
        Method fname = getMethod(name);
        if (Modifier.isPublic(fname.getModifiers())
                && !Modifier.isStatic(fname.getModifiers()))
            out("Syntax: Method " + name + " uses correct modifiers.");
        else if (!Modifier.isPublic(fname.getModifiers()))
            fail("SYNTAX: Method " + name + " is NOT public. (Should be)");
        else if (Modifier.isStatic(fname.getModifiers()))
            fail("SYNTAX: Method " + name + " declared static. (Should NOT be)");
    }

    protected void checkFieldIsPrivate(String name)
    {
        checkClassExists();
        if (!fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (Modifier.isPrivate(fname.getModifiers()))
            out("Syntax: Field " + name + " is private.");
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
        if (!fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (!Modifier.isStatic(fname.getModifiers()))
            out("Syntax: Field " + name + " is not static.");
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

    protected void checkFieldIsTypeString(String name)
    {
        checkClassExists();
        if (!fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (fname.getType() == String.class)
            out("Syntax: Field " + name + " is String type.");
        else
            fail("SYNTAX: Field " + name + " is not String type. (Should be)");
    }

    protected void checkFieldIsTypeDouble(String name)
    {
        checkClassExists();
        if (!fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (fname.getType() == double.class)
            out("Syntax: Field " + name + " is double type.");
        else
            fail("SYNTAX: Field " + name + " is not double type. (Should be)");
    }

    protected void checkFieldIsTypeInt(String name)
    {
        checkClassExists();
        if (!fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (fname.getType() == int.class)
            out("Syntax: Field " + name + " is int type.");
        else
            fail("SYNTAX: Field " + name + " is not int type. (Should be)");
    }

    protected void checkFieldIsTypeBoolean(String name)
    {
        checkClassExists();
        if (!fieldNames.contains(name))
            fail("SYNTAX: Field " + name + " is not found.");
        Field fname = getField(name);
        if (fname.getType() == boolean.class)
            out("Syntax: Field " + name + " is boolean type.");
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
            out("Syntax: Method " + name + " found in " + className);
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
        if (!methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (Modifier.isPublic(fname.getModifiers()))
            out("Syntax: Correct - " + name + " is public.");
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
        if (!methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (!Modifier.isStatic(fname.getModifiers()))
            out("Syntax: Method " + name + " is not static.");
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
            fail("SYNTAX: No type for " + methodData[num][METHOD_NAME]
                    + "? CHECK TEST PROGRAM STEVE.");
    }

    protected void checkMethodIsTypeString(String name)
    {
        checkClassExists();
        if (!methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (fname.getReturnType() == String.class)
            out("Syntax: Method " + name + " is String return type.");
        else
            fail("SYNTAX: Method " + name
                    + " is not String return type. (Should be)");
    }

    protected void checkMethodIsTypeDouble(String name)
    {
        checkClassExists();
        if (!methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (fname.getReturnType() == double.class)
            out("Syntax: Method " + name + " is double return type.");
        else
            fail("SYNTAX: Method " + name
                    + " is not double return type. (Should be)");
    }

    protected void checkMethodIsTypeInt(String name)
    {
        checkClassExists();
        if (!methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (fname.getReturnType() == int.class)
            out("Syntax: Method " + name + " is int return type.");
        else
            fail("SYNTAX: Method " + name
                    + " is not int return type. (Should be)");
    }

    protected void checkMethodIsTypeBoolean(String name)
    {
        checkClassExists();
        if (!methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (fname.getReturnType() == boolean.class)
            out("Syntax: Method " + name + " is boolean return type.");
        else
            fail("SYNTAX: Method " + name
                    + " is not boolean return type. (Should be)");
    }

    protected void checkMethodIsTypeVoid(String name)
    {
        checkClassExists();
        if (!methodNames.contains(name))
            fail("SYNTAX: Method " + name + " is not found.");
        Method fname = getMethod(name);
        if (fname.getReturnType() == void.class)
            out("Syntax: Method " + name + " is void return type.");
        else
            fail("SYNTAX: Method " + name
                    + " is not void return type. (Should be)");
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
                actualSig += (i == 0) ? params[i].getSimpleName() : ", "
                        + params[i].getSimpleName();
            actualSig += ")";

            assertEquals("SYNTAX: Method " + methodName + " parameters: ",
                    desiredSig, actualSig);

            out("Syntax: Method " + methodName + " has correct parameters");
        }
    }

    /**
     * Report on a successful test.
     */
    protected void out(String s)
    {
        System.out.print(" + " + s);
    }

    // ///////////// TEST FIELDS AND MUTATORS ////////////////////////////
    protected void testIntMutator(String name, String fname, int arg,
            int expected)
    {
        try
        {

            Object[] args =
            { arg };
            invoke(obj, name, args);
            Field f = getField(fname);
            f.setAccessible(true);
            int actual = (Integer) (f.get(obj));
            assertEquals("INCORRECT--calling " + name + "(" + arg + "): ",
                    expected, actual);
            out("CORRECT--calling " + name + "(" + arg + ")");
        }
        catch (Exception e)
        {
            fail("CRASH calling  " + name + "(" + arg + ")");
        }
    }

    protected void testIntsMutator(String name, String fname, int expected,
            Object... args)
    {
        String desc = getParamDesc(args);
        try
        {
            invoke(obj, name, args);
            Field f = getField(fname);
            f.setAccessible(true);
            int actual = (Integer) (f.get(obj));
            assertEquals("INCORRECT--calling " + name + "(" + desc + "): ",
                    expected, actual);
            out("CORRECT--calling " + name + "(" + desc + ")");
        }
        catch (Exception e)
        {
            fail("CRASH calling  " + name + "(" + desc + ")");
        }
    }

    protected void testDoubleMutator(String name, String fname, double arg,
            double expected)
    {
        try
        {
            Object[] args =
            { arg };
            invoke(obj, name, args);
            Field f = getField(fname);
            f.setAccessible(true);
            double actual = (Double) (f.get(obj));
            assertEquals("INCORRECT--calling " + name + "(" + arg + "): ",
                    expected, actual, tolerance);
            out("CORRECT--calling " + name + "(" + arg + ")");
        }
        catch (Exception e)
        {
            fail("CRASH calling  " + name + "(" + arg + ")");
        }
    }

    protected void testStringMutator(String name, String fname, String arg,
            String expected)
    {
        try
        {
            Object[] args =
            { arg };
            invoke(obj, name, args);
            Field f = getField(fname);
            f.setAccessible(true);
            String actual = (String) (f.get(obj));
            assertEquals("INCORRECT--calling " + name + "(\"" + arg + "\"): ",
                    expected, actual);
            out("CORRECT--calling " + name + "(\"" + arg + "\")");
        }
        catch (Exception e)
        {
            fail("CRASH calling  " + name + "(\"" + arg + "\")");
        }
    }

    protected void testIntAccessor(String name, int expected, Object... args)
    {
        try
        {
            int actual = args.length == 0 ? invoke(obj, Integer.class, name)
                    : invoke(obj, Integer.class, name, args);
            assertEquals("INCORRECT--calling " + name + "(): ", expected,
                    actual);
            out("CORRECT--calling " + name + "()-> " + expected);
        }
        catch (Exception e)
        {
            fail("CRASH calling  " + name + "()");
        }
    }

    protected void testStringAccessor(String name, String expected,
            Object... args)
    {
        try
        {
            String actual = args.length == 0 ? invoke(obj, String.class, name)
                    : invoke(obj, String.class, name, args);
            assertEquals("INCORRECT--calling " + name + "(): ", expected,
                    actual);
            out("CORRECT--calling " + name + "()-> " + expected);
        }
        catch (Exception e)
        {
            fail("CRASH calling  " + name + "()");
        }
    }

    protected void testDoubleAccessor(String name, double expected,
            Object... args)
    {
        double tolerance1 = .005;
        try
        {
            double actual = args.length == 0 ? invoke(obj, Double.class, name)
                    : invoke(obj, Double.class, name, args);
            assertEquals("INCORRECT--calling " + name + "(): ", expected,
                    actual, tolerance1);
            out("CORRECT--calling " + name + "()-> " + expected);
        }
        catch (Exception e)
        {
            fail("CRASH calling  " + name + "()");
        }
    }

    protected void testIntFieldInitialization(String name, int value)
    {
        try
        {
            Field f = getField(name);
            f.setAccessible(true);
            int actual = (Integer) (f.get(obj));
            assertEquals("INCORRECT: " + name + " initialization: ", value,
                    actual);
            out("CORRECT: " + name + " initialization.");
        }
        catch (Exception e)
        {
            fail("CRASH: Cannot extract field " + name + " value.");
        }
    }

    protected void testDoubleFieldInitialization(String name, double value)
    {
        try
        {
            Field f = getField(name);
            f.setAccessible(true);
            double actual = (Double) (f.get(obj));
            assertEquals("INCORRECT: " + name + " initialization: ", value,
                    actual, 1E-10);
            out("CORRECT: " + name + " initialization.");
        }
        catch (Exception e)
        {
            fail("CRASH: Cannot extract field " + name + " value.");
        }
    }

    protected void testStringFieldInitialization(String name, String value)
    {
        try
        {
            Field f = getField(name);
            f.setAccessible(true);
            String actual = (String) (f.get(obj));
            assertEquals("INCORRECT: " + name + " initialization: ", value,
                    actual);
            out("CORRECT: " + name + " initialization.");
        }
        catch (Exception e)
        {
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
            s = new StringBuilder(Arrays.toString((int[]) o));
        else if (o.getClass() == double[].class)
            s = new StringBuilder(Arrays.toString((double[]) o));
        else if (o.getClass() == boolean[].class)
            s = new StringBuilder(Arrays.toString((boolean[]) o));
        else
            s = new StringBuilder(Arrays.toString((Object[]) o));

        s.setCharAt(0, '{');
        s.setCharAt(s.length() - 1, '}');
        return s.toString();
    }

}
