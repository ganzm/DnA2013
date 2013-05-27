package aufgabe12;

import java.io.IOException;

import org.junit.Test;

import base.TestBase;

public class A12Test extends TestBase {

	/**
	 * specify which class you want to test
	 * 
	 * Note: specify package name to avoid the trouble of accidently calling the wrong class since everything is called Main
	 */
	private Class<?> classUnderTest = aufgabe12.Main.class;

	/**
	 * specify folder containing test data
	 * 
	 * use "/" instead of "\\" for max/linux
	 */
	private String testDataFolder = "testdata\\uebung12";

	/**
	 * Perform test using sample input from exercise pdf
	 */
	@Test
	public void testUebung12() {

		/* @formatter:off */
		String input = 
				"2"+ separator +
				"3 3 1 2 2 2 3 -4 3 1 1"+ separator +
				"6 8 1 2 -1 1 3 3 2 4 5 2 5 6 3 5 7 4 6 3 5 4 -4 5 6 2"+ separator;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput =
				"NEGATIVE-CYCLE"+ separator +
				"0 -1 3 1 5 4"+ separator;
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