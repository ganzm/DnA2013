package aufgabe9;

import java.io.IOException;

import org.junit.Test;

import base.TestBase;

public class A9Test extends TestBase {

	/**
	 * specify which class you want to test
	 * 
	 * Note: specify package name to avoid the trouble of accidently calling the wrong class since everything is called Main
	 */
	private Class<?> classUnderTest = aufgabe9.Main.class;

	/**
	 * specify folder containing test data
	 * 
	 * use "/" instead of "\\" for max/linux
	 */
	private String testDataFolder = "testdata\\uebung09";

	/**
	 * Perform test using sample input from exercise pdf
	 */
	@Test
	public void testUebung9() {

		/* @formatter:off */
		String input = 
				"1"+ separator +
				"10 15 1 2 1 2 3 2 1 4 7 2 5 2 3 6 3 3 7 5 4 5 4 5 6 3 6 7 2 4 8 1 5 9 3 6 9 4 7 10 6 8 9 5 9 10 3"+ separator;
				
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput =
				"21"+ separator;
		/* @formatter:on */

		performTestWithStrings(classUnderTest, input, expectedOutput);
	}


	@Test
	public void testLarge() throws IOException {
		String inFile = "large.in";
		String outFile = "large.out";
		performTestWithFiles(classUnderTest, testDataFolder, inFile, outFile);
	}
	
	@Test
	public void testMedium() throws IOException {
		String inFile = "medium.in";
		String outFile = "medium.out";
		performTestWithFiles(classUnderTest, testDataFolder, inFile, outFile);
	}
	
	@Test
	public void testSmall() throws IOException {
		String inFile = "small.in";
		String outFile = "small.out";
		performTestWithFiles(classUnderTest, testDataFolder, inFile, outFile);
	}
}