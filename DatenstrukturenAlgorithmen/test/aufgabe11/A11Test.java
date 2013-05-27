package aufgabe11;

import java.io.IOException;

import org.junit.Test;

import base.TestBase;

public class A11Test extends TestBase {

	/**
	 * specify which class you want to test
	 * 
	 * Note: specify package name to avoid the trouble of accidently calling the wrong class since everything is called Main
	 */
	private Class<?> classUnderTest = aufgabe11.Main.class;

	/**
	 * specify folder containing test data
	 * 
	 * use "/" instead of "\\" for max/linux
	 */
	private String testDataFolder = "testdata\\uebung11";

	/**
	 * Perform test using sample input from exercise pdf
	 */
	@Test
	public void testUebung11() {

		/* @formatter:off */
		String input = 
				"2"+ separator +
				"3 1 1 2 4 3 9"+ separator +
				"5 0 0 0 3 2 3 2 0 1 1"+ separator;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput =
				"1 1 3 9 2 4"+ separator +
				"0 0 0 3 2 3 2 0"+ separator;
		/* @formatter:on */

		performTestWithStrings(classUnderTest, input, expectedOutput);
	}

	@Test
	public void testPublic() throws IOException {
		String inFile = "public.in";
		String outFile = "public.out";
		performTestWithFiles(classUnderTest, testDataFolder, inFile, outFile);
	}

}