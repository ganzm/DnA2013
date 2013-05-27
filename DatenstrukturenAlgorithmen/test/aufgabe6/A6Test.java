package aufgabe6;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

import base.TestBase;

public class A6Test extends TestBase {

	/**
	 * specify which class you want to test
	 * 
	 * Note: specify package name to avoid the trouble of accidently calling the wrong class since everything is called Main
	 */
	private Class<?> classUnderTest = aufgabe6.Main.class;

	/**
	 * specify folder containing test data
	 * 
	 * use "/" instead of "\\" for max/linux
	 */
	private String testDataFolder = "testdata\\uebung06";

	/**
	 * Perform test using sample input from exercise pdf
	 */
	@Test
	public void testUebung6() {

		/* @formatter:off */
		String input = 
				"2" + separator +
				"5 1 2 3 4 5" + separator +
				"13 7 3 5 1 9 8 11 21 4 10 2 6 9" + separator;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput = 
				"3 3 3" + separator +
				"5 10 6 6 7" + separator;
		/* @formatter:on */

		performTestWithStrings(classUnderTest, input, expectedOutput);
	}

	@Test
	public void testUebung6_2() {

		/* @formatter:off */
		String input = 
				"1" + separator +
				"13 7 3 5 1 9 8 11 21 4 10 2 6 9" + separator;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput = 
				"5 10 6 6 7" + separator;
		/* @formatter:on */

		performTestWithStrings(classUnderTest, input, expectedOutput);
	}

	@Test
	public void testUebung6_3() {

		/* @formatter:off */
		String input = 
				"1" + separator +
				"17 5 -25 -9 24 25 -30 -18 18 8 -31 10 -6 -33 -4 28 23 -17" + separator;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput = 
				"5 -18 -4 -17 -17 -4" + separator;
		/* @formatter:on */

		performTestWithStrings(classUnderTest, input, expectedOutput);
	}

	@Test
	public void testUebung6Zahlen() {

		/* @formatter:off */
		String input = 
				"1" + separator +
				"6 1 2 3 4 5 6" + separator;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput = 
				"3 6 3 3" + separator;
		/* @formatter:on */

		performTestWithStrings(classUnderTest, input, expectedOutput);
	}
	
	@Test
	public void Ivo1() {
		
		/* @formatter:off */
		String input =
				"1"+ separator +
				"4 85 88 47 13"+ separator;
		
		/* @formatter:on */
		
		/* @formatter:off */
		String expectedOutput = 
				"47 47 47" + separator;
		/* @formatter:on */
		
		performTestWithStrings(classUnderTest, input, expectedOutput);
	}

	
	
	@Test
	public void testUebung7Zahlen() {

		/* @formatter:off */
		String input = 
				"1" + separator +
				"7 1 2 3 4 5 6 7" + separator;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput = 
				"3 6 3 4" + separator;
		/* @formatter:on */

		performTestWithStrings(classUnderTest, input, expectedOutput);
	}

	@Test
	public void testUebung8Zahlen() {

		/* @formatter:off */
		String input = 
				"1" + separator +
				"8 1 2 3 4 5 6 7 8" + separator;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput = 
				"3 7 3 4" + separator;
		/* @formatter:on */

		performTestWithStrings(classUnderTest, input, expectedOutput);
	}

	@Test
	public void testUebung9Zahlen() {

		/* @formatter:off */
		String input = 
				"1" + separator +
				"9 1 2 3 4 5 6 7 8 9" + separator;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput = 
				"3 7 3 5" + separator;
		/* @formatter:on */

		performTestWithStrings(classUnderTest, input, expectedOutput);
	}

	@Test
	public void testUebung10Zahlen() {

		/* @formatter:off */
		String input = 
				"1" + separator +
				"10 1 2 3 4 5 6 7 8 9 10" + separator;
		/* @formatter:on */

		/* @formatter:off */
		String expectedOutput = 
				"3 8 3 5" + separator;
		/* @formatter:on */

		performTestWithStrings(classUnderTest, input, expectedOutput);
	}

	@Test
	public void testPublic() throws IOException {
		String inFile = "public.in";
		String outFile = "public.out";
		performTestWithFiles(classUnderTest, testDataFolder, inFile, outFile);
	}

	@Test
	public void testCompareWithIvo() {

		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;

		try {

			for (int i = 4; i < 30; i++) {

				for (int j = 0; j < 10; j++) {

					String input = generateInput(i);

					originalOut.println("Test: " + input);

					InputStream inputStreamIvo = new ByteArrayInputStream(input.getBytes());
					InputStream inputStream = new ByteArrayInputStream(input.getBytes());

					// Output stuff
					ByteArrayOutputStream byteArrayOutputStreamIvo = new ByteArrayOutputStream();
					PrintStream outputStreamIvo = new PrintStream(byteArrayOutputStreamIvo);

					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					PrintStream outputStream = new PrintStream(byteArrayOutputStream);

					System.setIn(inputStreamIvo);
					System.setOut(outputStreamIvo);

					MainIvo.main(new String[0]);

					System.setIn(inputStream);
					System.setOut(outputStream);

					Main.main(new String[0]);

					String outIvo = new String(byteArrayOutputStreamIvo.toByteArray());
					String out = new String(byteArrayOutputStream.toByteArray());

					// avoid some problems with line breaks
					outIvo = outIvo.replaceAll("(\r\n|\n|\r)", "\n");
					out = out.replaceAll("(\r\n|\n|\r)", "\n");

					if (!out.equals(outIvo)) {
						System.out.println("Expected Output:\n" + outIvo + "\n\nbut was:\n" + out);
					}
					Assert.assertEquals(outIvo, out);
				}
			}

		} finally {
			System.setIn(originalIn);
			System.setOut(originalOut);

		}
	}

	private Random rand = new Random(1);

	private String generateInput(int numInput) {
		StringBuffer buf = new StringBuffer();

		buf.append("1");
		buf.append(separator);
		buf.append(numInput);
		for (int i = 0; i < numInput; i++) {
			buf.append(" ");
			buf.append(rand.nextInt(100));
		}

		buf.append(separator);

		return buf.toString();
	}

}