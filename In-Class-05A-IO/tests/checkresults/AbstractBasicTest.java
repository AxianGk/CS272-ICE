package checkresults;

import static checkresults.ReflectionSupport.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AbstractBasicTest
{
    protected static String className;
    protected static String fullClassName;
    protected static Class<?> thisClass;
    protected static Object obj;
    protected static Method[] methods;
    protected static ArrayList<String> methodNames = new ArrayList<>();

    protected Random gen = new Random();
    protected double tolerance = 1E-8;

    // Status flags that can be checked.
    protected static boolean classExists;

    /**
     * Checks the type and other items.
     */
    protected static void init() throws Exception
    {
        try
        {
            thisClass = Class.forName(className);
            classExists = true;
            fullClassName = className;
            if (className.lastIndexOf(".") > 0)
                className = className.substring(className.lastIndexOf('.') + 1);

            methods = thisClass.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++)
            {
                methodNames.add(methods[i].getName());
            }
        }
        catch (Throwable e)
        {
            classExists = false;
            fail("Class " + className + " not defined.");
        }
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
        try  {
            Field f = thisClass.getField(fName);
            return f;
        }  catch (Exception e) {
            return null;
        }
    }

    protected Method getMethod(String mName, Class<?>...parameterTypes)
    {
        Method m = null;
        try {
            if (parameterTypes.length > 0)
                m = thisClass.getMethod(mName, parameterTypes);
            else
            {
                int pos = methodNames.indexOf(mName);
                if (pos >= 0) m = methods[pos];
                else m = null;
            }
            return m;
        } catch (Exception e) { 
            return null; 
        }
    }

    // FIELDS ////////////////////////////////////////////////////

    protected void checkFieldExists(String name)
    {
        if (getField(name) != null)
            out("Syntax: Field " + name + " found in " + className);
        else
            fail("SYNTAX: Field " + name + " is not found.");
    }

    protected void checkFieldModifiers(String name)
    {
        Field field = getField(name);
        if (field == null)
            fail("SYNTAX: Field " + name + " is not found.");
        if (Modifier.isPrivate(field.getModifiers())
                && !Modifier.isStatic(field.getModifiers()))
            out("Syntax: Field " + name + " uses correct modifiers.");
        else if (!Modifier.isPrivate(field.getModifiers()))
            fail("SYNTAX: Field " + name + " is not private. (Should be)");
        else if (Modifier.isStatic(field.getModifiers()))
            fail("SYNTAX: Field " + name + " declared static. (Should NOT be)");
    }

    protected void checkMethodModifiers(String name)
    {
        Method method = getMethod(name);
        if (method == null)
            fail("SYNTAX: Method " + name + " not found in class.");
        if (Modifier.isPublic(method.getModifiers())
                && !Modifier.isStatic(method.getModifiers()))
            out("Syntax: Method " + name + " uses correct modifiers.");
        else if (!Modifier.isPublic(method.getModifiers()))
            fail("SYNTAX: Method " + name + " is NOT public. (Should be)");
        else if (Modifier.isStatic(method.getModifiers()))
            fail("SYNTAX: Method " + name + " declared static. (Should NOT be)");
    }

    protected void checkFieldIsPrivate(String name)
    {
        Field field = getField(name);
        if (field == null)
            fail("SYNTAX: Field " + name + " is not found.");
        if (Modifier.isPrivate(field.getModifiers()))
            out("Syntax: Field " + name + " is private.");
        else
            fail("SYNTAX: Field " + name + " is not private. (Should be)");
    }

    protected void checkFieldIsNotStatic(String name)
    {
        Field field = getField(name);
        if (field == null)
            fail("SYNTAX: Field " + name + " is not found.");
        if (!Modifier.isStatic(field.getModifiers()))
            out("Syntax: Field " + name + " is not static.");
        else
            fail("SYNTAX: Field " + name + " is static. (Should NOT be)");
    }

    protected void checkFieldIsTypeString(String name)
    {
        Field field = getField(name);
        if (field == null)
            fail("SYNTAX: Field " + name + " is not found.");
        if (field.getType() == String.class)
            out("Syntax: Field " + name + " is String type.");
        else
            fail("SYNTAX: Field " + name + " is not String type. (Should be)");
    }

    protected void checkFieldIsTypeDouble(String name)
    {
        Field field = getField(name);
        if (field == null)
            fail("SYNTAX: Field " + name + " is not found.");
        if (field.getType() == double.class)
            out("Syntax: Field " + name + " is double type.");
        else
            fail("SYNTAX: Field " + name + " is not double type. (Should be)");
    }

    protected void checkFieldIsTypeInt(String name)
    {
        Field field = getField(name);
        if (field == null)
            fail("SYNTAX: Field " + name + " is not found.");
        if (field.getType() == int.class)
            out("Syntax: Field " + name + " is int type.");
        else
            fail("SYNTAX: Field " + name + " is not int type. (Should be)");
    }

    protected void checkFieldIsTypeBoolean(String name)
    {
        Field field = getField(name);
        if (field == null)
            fail("SYNTAX: Field " + name + " is not found.");
        if (field.getType() == boolean.class)
            out("Syntax: Field " + name + " is boolean type.");
        else
            fail("SYNTAX: Field " + name + " is not boolean type. (Should be)");
    }

    // METHODS ////////////////////////////////////////////////////
    protected void checkMethodExists(String name)
    {
        if (getMethod(name) == null)
            fail("SYNTAX: Method " + name + " is not found.");
        else
            out("Syntax: Method " + name + " found in " + className);
    }

    protected void checkMethodIsPublic(String name)
    {
        Method fname = getMethod(name);
        if (fname == null)
            fail("SYNTAX: Method " + name + " is not found.");
        if (Modifier.isPublic(fname.getModifiers()))
            out("Syntax: Correct - " + name + " is public.");
        else
            fail("SYNTAX: Method " + name + " is not public. (Should be)");
    }

    protected void checkMethodIsNotStatic(String name)
    {
        Method fname = getMethod(name);
        if (fname == null)
            fail("SYNTAX: Method " + name + " is not found.");
        if (!Modifier.isStatic(fname.getModifiers()))
            out("Syntax: Method " + name + " is not static.");
        else
            fail("SYNTAX: Method " + name + " is static. (Should NOT be)");
    }

    protected void checkMethodIsTypeString(String name)
    {
        Method fname = getMethod(name);
        if (fname == null)
            fail("SYNTAX: Method " + name + " is not found.");
        if (fname.getReturnType() == String.class)
            out("Syntax: Method " + name + " is String return type.");
        else
            fail("SYNTAX: Method " + name
                    + " is not String return type. (Should be)");
    }

    protected void checkMethodIsTypeDouble(String name)
    {
        Method fname = getMethod(name);
        if (fname == null)
            fail("SYNTAX: Method " + name + " is not found.");
        if (fname.getReturnType() == double.class)
            out("Syntax: Method " + name + " is double return type.");
        else
            fail("SYNTAX: Method " + name
                    + " is not double return type. (Should be)");
    }

    protected void checkMethodIsTypeInt(String name)
    {
        Method fname = getMethod(name);
        if (fname == null)
            fail("SYNTAX: Method " + name + " is not found.");
        if (fname.getReturnType() == int.class)
            out("Syntax: Method " + name + " is int return type.");
        else
            fail("SYNTAX: Method " + name
                    + " is not int return type. (Should be)");
    }

    protected void checkMethodIsTypeBoolean(String name)
    {
        Method fname = getMethod(name);
        if (fname == null)
            fail("SYNTAX: Method " + name + " is not found.");
        if (fname.getReturnType() == boolean.class)
            out("Syntax: Method " + name + " is boolean return type.");
        else
            fail("SYNTAX: Method " + name
                    + " is not boolean return type. (Should be)");
    }

    protected void checkMethodIsTypeVoid(String name)
    {
        Method fname = getMethod(name);
        if (fname == null)
            fail("SYNTAX: Method " + name + " is not found.");
        if (fname.getReturnType() == void.class)
            out("Syntax: Method " + name + " is void return type.");
        else
            fail("SYNTAX: Method " + name
                    + " is not void return type. (Should be)");
    }

    /**
     * Report on a successful test.
     */
    protected void out(String s)
    {
        System.out.print(" + " + s);
    }

    // ///////////// TEST FIELDS AND MUTATORS ////////////////////////////
    protected void testMethod(String name, Object expected, Object... args)
    {
        String desc = "--calling " + name + "(" + getParamDesc(args) + ")";
        try
        {
            Object actual = invoke(obj, getMethod(name), args);
            assertEquals("INCORRECT" + desc + "->", expected, actual);
            out("CORRECT" + desc);
        }
        catch (Exception e)
        {
            Throwable t = e.getCause();
            if (t == null && expected == e.getClass())
                out("EXPECTED EXCEPTION" + desc + "->" + expected);
            else if (t != null && expected == t.getClass())
                out("EXPECTED EXCEPTION" + desc + "->" + expected);
            else
                fail("CRASH" + desc + "->" + e.getMessage());
        }
    }

    protected void testMutator(String name, String fname, Object expected,
            Object... args)
    {
        String desc = "--calling " + name + "(" + getParamDesc(args) + ")";
        try
        {
            invoke(obj, name, args);
            Field f = getField(fname);
            f.setAccessible(true);
            Object actual = f.get(obj);
            assertEquals("INCORRECT" + desc + "->", expected, actual);
            out("CORRECT" + desc);
        }
        catch (Exception e)
        {
            fail("CRASH" + desc);
        }
    }
    
    protected void testFieldInitialization(String name, Object value)
    {
        try
        {
            Field f = getField(name);
            f.setAccessible(true);
            Object actual = f.get(obj);
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
