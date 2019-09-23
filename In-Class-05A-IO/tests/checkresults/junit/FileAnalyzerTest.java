package checkresults.junit;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import checkresults.AbstractFunctionTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileAnalyzerTest extends AbstractFunctionTest
{
    @BeforeClass
    public static void initializeTests() throws Exception
    {
        className = "occ.cs272.ic05.FileAnalyzer";
        methodName = "longestWord";
        params = new Class[] {String.class};
        returnType = "String";
        init();
    }

    final FileNotFoundException fne = new FileNotFoundException();

    public FileAnalyzerTest()
    {
        testData = 
                new Object[][] {
                {"mary.txt", "everywhere"},
                {"JackJill.txt", "tumbling"},
                {"does-not-exist.txt", fne.toString()} 
      };
    }
}
