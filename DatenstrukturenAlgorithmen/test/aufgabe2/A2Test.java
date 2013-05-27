package aufgabe2;

import java.io.IOException;

import org.junit.Test;

import base.TestBase;

public class A2Test extends TestBase {

	/**
	 * specify which class you want to test
	 * 
	 * Note: specify package name to avoid the trouble of accidently calling the wrong class since everything is called Main
	 */
	private Class<?> classUnderTest = aufgabe2.Main.class;

	/** 
	 * specify folder containing test data
	 * 
	 *  use "/" instead of "\\" for max/linux
	 */
	private String testDataFolder = "testdata\\uebung02";
	
	@Test
	public void testUebung2() {
		/* @formatter:off */
		String input = "2" + separator
				+ "32 -2 4 4 0 -1 5 -3 2 -2 -5 4 -12 1 -6 1 2 8 -1 3 -4 -2 -5 2 -5 2 -1 1 4 -2 6 2 -4" + separator 
				+ "10 -5 -1 -4 -2 -2 -9 -10 -2 -3 -4" + separator;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput = 
				"13" + separator + 
				"0" + separator;
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