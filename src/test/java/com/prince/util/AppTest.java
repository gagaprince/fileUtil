package com.prince.util;

import com.prince.util.fileutil.FileUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testCreateFile()
    {
        FileUtil fileUtil = FileUtil.getInstance();
        String filePath = "/Users/gagaprince/work/index/blogIndex";
//        fileUtil.createFile(filePath);
//        fileUtil.deleteFile(filePath);
    }
}
