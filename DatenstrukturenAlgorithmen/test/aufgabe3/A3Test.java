package aufgabe3;

import java.io.IOException;

import org.junit.Test;

import base.TestBase;

public class A3Test extends TestBase {

	/** 
	 * specify which class you want to test
	 */
	private Class<?> classUnderTest = aufgabe3.Main.class;
	
	/** 
	 * specify folder containing test data
	 * 
	 *  use "/" instead of "\\" for max/linux
	 */
	private String testDataFolder = "testdata\\uebung03";
	
	@Test
	public void testUebung2() {
		/* @formatter:off */
		String input = 
				"2" + separator + 
				"3 1 2 3" + separator + 
				"5 5 7 3 4 2" + separator;
		/* @formatter:on */
		
		/* @formatter:off */
		String expectedOutput = 
				"1 2 3" + separator + 
				"1 2 3" + separator + 
				"5 7 5 7 4" + separator + 
				"2 3 4 5 7" + separator;
		/* @formatter:on */

		/* ************************* */

		performTestWithStrings(classUnderTest, input, expectedOutput);
	}

	@Test
	public void testUebung2_1() {
		/* @formatter:off */
		String input = 
				"1" + separator + 
				"5 -5 -7 -7 2 -10" + separator;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput = 
				"-5 -5 -7 2 -5" + separator + 
				"-10 -7 -7 -5 2" + separator;
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