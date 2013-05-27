package aufgabe7;

import java.io.IOException;

import org.junit.Test;

import base.TestBase;

public class A7Test extends TestBase {

	/**
	 * specify which class you want to test
	 * 
	 * Note: specify package name to avoid the trouble of accidently calling the wrong class since everything is called Main
	 */
	private Class<?> classUnderTest = aufgabe7.Main.class;

	/**
	 * specify folder containing test data
	 * 
	 * use "/" instead of "\\" for max/linux
	 */
	private String testDataFolder = "testdata\\uebung07";

	/**
	 * Perform test using sample input from exercise pdf
	 */
	@Test
	public void testUebung7() {

		/* @formatter:off */
		String input = 
				"2"+ separator +
				"2 3"+ separator +
				"1 1"+ separator +
				"2 5"+ separator +
				"3 5"+ separator +
				"1 3 2"+ separator +
				"2 2 1"+ separator;

		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput =
				"1 1"+ separator +
				"6 1 2 3"+ separator;
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