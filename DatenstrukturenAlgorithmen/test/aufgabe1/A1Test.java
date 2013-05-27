package aufgabe1;

import java.io.IOException;

import org.junit.Test;

import base.TestBase;

public class A1Test extends TestBase {

	/**
	 * specify which class you want to test
	 * 
	 * Note: specify package name to avoid the trouble of accidently calling the wrong class since everything is called Main
	 */
	private Class<?> classUnderTest = aufgabe1.Main.class;

	/** 
	 * specify folder containing test data
	 * 
	 *  use "/" instead of "\\" for max/linux
	 */
	private String testDataFolder = "testdata\\uebung01";
	
	/**
	 * Perform Test with sample input from the exercise pdf
	 */
	@Test
	public void testUebung1() {

		/* @formatter:off */
		String input = 
				"2" + separator + 
				"20 0 1 1 1" + separator + 
				"22 5 10 1 -1" + separator ;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput = 
				"6765" + separator + 
				"-10" + separator;
		/* @formatter:on */

		performTestWithStrings(classUnderTest, input, expectedOutput);
	}
	

	@Test
	public void testEasy() throws IOException {
		String inFile = "easy.in";
		String outFile = "easy.out";
		performTestWithFiles(classUnderTest, testDataFolder, inFile, outFile);
	}

	@Test
	public void testMedium() throws IOException {
		String inFile = "medium.in";
		String outFile = "medium.out";
		performTestWithFiles(classUnderTest, testDataFolder, inFile, outFile);
	}

	@Test
	public void testHard() throws IOException {
		String inFile = "hard.in";
		String outFile = "hard.out";
		performTestWithFiles(classUnderTest, testDataFolder, inFile, outFile);
	}

	
}