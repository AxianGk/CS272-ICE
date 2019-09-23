package checkresults.junit;

import static checkresults.ReflectionSupport.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import org.junit.Test;

import occ.cs272.ic05.CaesarCipher;

public class CaesarCipherTest
{
    private ByteArrayOutputStream out;
    private CaesarCipher cipher;
    private ByteArrayInputStream inStr1, inStr2;
    
    public void setup(int n, String inStr)
    {
        cipher = new CaesarCipher(n);
        out = new ByteArrayOutputStream();
        inStr1 = getIn(inStr);
        inStr2 = getIn(inStr);
    }
    
    private ByteArrayInputStream getIn(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }
    
    private String encImpl(ByteArrayInputStream in, int key)
    {
        ByteArrayOutputStream sout = new ByteArrayOutputStream();
        boolean done = false;
        while (!done)
        {
            int next = in.read();
            if (next == -1)
                done = true;
            else
            {
                byte b = (byte) next;
                byte c = (byte) (b + key);
                sout.write(c);
            }
        }
        return sout.toString();
    }
    
    private String decImpl(ByteArrayInputStream in, int key)
    {
        ByteArrayOutputStream sout = new ByteArrayOutputStream();
        boolean done = false;
        while (!done)
        {
            int next = in.read();
            if (next == -1)
                done = true;
            else
            {
                byte b = (byte) next;
                byte c = (byte) (b - key);
                sout.write(c);
            }
        }
        return sout.toString();
    }
    
    private void runEncryptTest(int nInput, int key)
    {
        try {
            setup(key, input[nInput]);
            cipher.encryptStream(inStr1, out);
            String expected = encImpl(inStr2, key);
            assertEquals(expected, out.toString());
            out("Encrypt " + "\"" + input[nInput] + "\" with key " + key + "->\"" + expected + "\"");
        }
        catch (Exception e) {
            fail("Cannot run with this input.");
        }
    }
    
    private void runDecryptTest(int nInput, int key)
    {
        try {
            String decryptString = encImpl(getIn(input[nInput]), key);
            setup(key, decryptString);
            Object[] args = {inStr1, out};
            try {
                invoke(cipher, "decryptStream", args);
            } catch (Throwable t) {
                fail("Decrypt: missing method decryptStream(InputStream, OutputStream)");
            }
            String expected = decImpl(inStr2, key);
            assertEquals(expected, out.toString());
            out("Decrypt " + "\"" + decryptString + "\" with key " + key + "->\"" + expected + "\"");
        }
        catch (Exception e) {
            fail("Cannot run with this input.");
        }
    }
    
    @Test public void testEnc1() { runEncryptTest(0, 0); }
    @Test public void testEnc2() { runEncryptTest(1, 1); }
    @Test public void testEnc3() { runEncryptTest(2, 2); }
    @Test public void testEnc4() { runEncryptTest(3, 3); }
    @Test public void testEnc5() { runEncryptTest(4, 4); }
    @Test public void testDec1() { runDecryptTest(0, 0); }
    @Test public void testDec2() { runDecryptTest(1, 1); }
    @Test public void testDec3() { runDecryptTest(2, 2); }
    @Test public void testDec4() { runDecryptTest(3, 3); }
    @Test public void testDec5() { runDecryptTest(4, 4); }

    private static Random gen = new Random();
    protected static String[] words = {
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
        "provincial", "novels", "heroes",
        "vies", "lashings", "rims", "autoincrements", "manslaughter",
        "rewarded", "corpus", "ballgown", "executable", "stairway", "slogan",
        "recesses", "handicap", "revolves", "inhibits", "takers", "titled",
        "elasticity", "criticized", "roadways", "centralization", "donation",
        "symposiums", "formalisms", "introspect", "assignable", "advisers",
        "retransmitting", "staunchest", "unifications", "cyclotrons", "adders",
        "regional", "intruder", "stormier", "globes", "revising", "aeration",
        "cherries", "regardless", "visa", "grunts", "capacities", "trampler",
        "infamy", "cowboy", "overlaps",
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
    
    private static String randWords(int n)
    {
        String result = words[gen.nextInt(words.length)];
        for (int i = 1; i < n; i++)
            result += " " + words[gen.nextInt(words.length)];
        
        return result;
    }
    
    private static String[] input = {
      randWords(1), randWords(2), randWords(3), randWords(4), randWords(5)  
    };
    
    /**
     * Report on a successful test.
     */
    protected void out(String s)
    {
        System.out.print(" + " + s);
    }

}
