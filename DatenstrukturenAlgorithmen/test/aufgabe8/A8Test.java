package aufgabe8;

import java.io.IOException;

import org.junit.Test;

import base.TestBase;

public class A8Test extends TestBase {

	/**
	 * specify which class you want to test
	 * 
	 * Note: specify package name to avoid the trouble of accidently calling the wrong class since everything is called Main
	 */
	private Class<?> classUnderTest = aufgabe8.Main.class;

	/**
	 * specify folder containing test data
	 * 
	 * use "/" instead of "\\" for max/linux
	 */
	private String testDataFolder = "testdata\\uebung08";

	/**
	 * Perform test using sample input from exercise pdf
	 */
	@Test
	public void testUebung8() {

		/* @formatter:off */
		String input = 
				"2"+ separator +
				"AGCAT"+ separator +
				"GAC"+ separator +
				"ROCK"+ separator +
				"ROLL"+ separator;
				
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput =
				"2 AC"+ separator +
				"2 RO"+ separator;
		/* @formatter:on */

		performTestWithStrings(classUnderTest, input, expectedOutput);
	}


	@Test
	public void testHard() throws IOException {
		String inFile = "public.in";
		String outFile = "public.out";
		performTestWithFiles(classUnderTest, testDataFolder, inFile, outFile);
	}
}