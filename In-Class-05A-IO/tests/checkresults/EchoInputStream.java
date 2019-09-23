package checkresults;


import java.io.*;
/**
 * An "String" input stream that can be used for testing.
 * It acts kind of like the console in that it echoes every
 * byte read back to standard output, but it reads from
 * an array of String.
 *
 *  @author Stephen Gilbert
 *  @version Aug 11, 2009 10:08:41 AM
 *
 */
public class EchoInputStream extends InputStream
{
    private byte[][] buffer;
    private int curRow = 0;
    private ByteArrayInputStream curBuf;
    private static final String EOL = System.getProperty("line.separator");
    
    /**
     * Construct the input stream.
     *
     *  @param str an array of Strings to use for input.
     */
    public EchoInputStream(String...str)
    {   
        int numLines = str.length;
        buffer = new byte[numLines][];
        for (int i = 0; i < numLines; i++)
        {
            buffer[i] = (str[i] + EOL).getBytes();
        }
        curBuf = new ByteArrayInputStream(buffer[0]);
    }


    @Override
    public int available() throws IOException
    {
        return curBuf.available();
    }


    /**
     * Reads into the byte[] buffer b, starting at off, at most len bytes.
     */
    @Override
    public synchronized int read(byte[] b, int off, int len)
    {
        System.out.flush();
        int bytesRead = curBuf.read(b, off, len);
        if (bytesRead < 0 && updateBuf())
        {
            bytesRead = curBuf.read(b, off, len);
        }
        if (bytesRead >= 0)
        {
            byte[] out = new byte[bytesRead];
            System.arraycopy(b, off, out, 0, bytesRead);
            String outStr = new String(out);
            System.out.print(outStr);
            System.out.flush();
        }
        else
        {
            System.out.println();
            System.out.flush();
        }
        return bytesRead;
    }


    /**
     * Forwards the read() request to the current input stream.
     */
    @Override
    public int read() throws IOException
    {
        int n = curBuf.read();
        if (n < 0 && updateBuf())
        {
            n = curBuf.read();
        }
        return n;
    }
    
    /**
     * Updates the current buffer variable.
     *
     *  @return true if current row is less than buffer length.
     *  false when we're out of input.
     */
    private boolean updateBuf()
    {
        curRow++;
        if (curRow < buffer.length)
        {
            curBuf = new ByteArrayInputStream(buffer[curRow]);   
        }
        
        return curRow < buffer.length;
    }
}
