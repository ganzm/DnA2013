package aufgabe4;

import java.io.IOException;

import org.junit.Test;

import base.TestBase;

public class A4Test extends TestBase {

	/** 
	 * specify which class you want to test
	 * 
	 * Note: specify package name to avoid the trouble of accidently calling the wrong class since everything is called Main
	 */
	private Class<?> classUnderTest = aufgabe4.Main.class;

	/** 
	 * specify folder containing test data
	 * 
	 *  use "/" instead of "\\" for max/linux
	 */
	private String testDataFolder = "testdata\\uebung04";

	/**
	 * Perform test using sample input from exercise pdf
	 */
	@Test
	public void testUebung4() {

		/* @formatter:off */
		String input = 
				"2"+ separator + 
				"5 3 1 2 3"+ separator + 
				"5 4 1 2 3 8"
				+ separator;

		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput = 
				"0 0 1 2 3 0"+ separator + 
				"0 0 1 2 3 0"+ separator + 
				"0 0 1 2 3 0"+ separator + 
				"3 8 1 2 3 0"+ separator + 
				"1 0 1 2 3 8"+ separator + 
				"1 8 1 2 3 0"+ separator;
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