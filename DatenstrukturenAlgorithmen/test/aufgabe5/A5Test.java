package aufgabe5;

import java.io.IOException;

import org.junit.Test;

import base.TestBase;

public class A5Test extends TestBase {

	/** 
	 * specify which class you want to test
	 * 
	 * Note: specify package name to avoid the trouble of accidently calling the wrong class since everything is called Main
	 */
	private Class<?> classUnderTest = aufgabe5.Main.class;

	/** 
	 * specify folder containing test data
	 * 
	 *  use "/" instead of "\\" for max/linux
	 */
	private String testDataFolder = "testdata\\uebung05";

	/**
	 * Perform test using sample input from exercise pdf
	 */
	@Test
	public void testUebung5() {

		/* @formatter:off */
		String input = 
				"2" + separator + 
				"5 1 2 3 4 5" + separator + 
				"7 7 3 5 1 9 8 11" + separator;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput = 
				"5 0 0 4 0 1 3 0 2 2 0 3 1 0 4 " + separator + 
				"1 0 0 5 0 0 3 1 1 8 0 0 11 0 0 9 1 1 7 2 2 " + separator;
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