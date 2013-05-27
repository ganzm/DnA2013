package base;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import junit.framework.Assert;

/**
 * D&A
 * 
 * Baseclass
 * 
 * Memo: there is neither support nor guaranteed correctness for this code :-) cheers
 * 
 * @author ganzm@student.etzh.ch
 */
public abstract class TestBase {

	/** get platform specific line separator */
	public static String separator = System.getProperty("line.separator");

	/**
	 * Executes Test
	 * 
	 * @param classUnderTest
	 *            Class under test
	 * @param folder
	 *            path to a folder containing input- output files
	 * @param inFile
	 *            filename of the input file
	 * @param outFile
	 *            filename of the output file
	 */
	protected void performTestWithFiles(Class<?> classUnderTest, String folder, String inFile, String outFile) {
		try {
			String input = readFile(folder + File.separatorChar + inFile);
			String expectedOutput = readFile(folder + File.separatorChar + outFile);
			performTestWithStrings(classUnderTest, input, expectedOutput);
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail(ex.getMessage());
		}
	}

	/**
	 * Executes Test
	 * 
	 * Sample Usage
	 * 
	 * performTest("2\n3 3 3 3\n", "1 2 3 4\n", Main.class);
	 * 
	 * @param input
	 *            String containing input data
	 * @param expectedOutput
	 * @param classUnderTest
	 */
	protected void performTestWithStrings(Class<?> classUnderTest, String input, String expectedOutput) {

		// Do Input stuff
		byte[] inputData = input.getBytes();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputData);

		// Output stuff
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(byteArrayOutputStream);

		// Redirect i/o streams
		PrintStream oldOut = System.out;
		System.setOut(out);
		System.setIn(byteArrayInputStream);

		// Call stuff
		try {
			Class<String[]> argClass = String[].class;
			Method methodUnderTest = classUnderTest.getMethod("main", argClass);
			Object[] args = new String[1];
			methodUnderTest.invoke(null, args);

		} catch (InvocationTargetException ex) {
			ex.getCause().printStackTrace();
			Assert.fail("Your main method threw an exception: " + ex.getCause().getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail(ex.getMessage());
		}

		// undo the i/o redirect
		System.setOut(oldOut);

		// convert output to string
		String output = new String(byteArrayOutputStream.toByteArray());

		// validate stuff
		System.out.println("Input was:\n" + input + "\n");

		// avoid some problems with line breaks
		expectedOutput = expectedOutput.replaceAll("(\r\n|\n|\r)", "\n");
		output = output.replaceAll("(\r\n|\n|\r)", "\n");
		
		if (!output.equals(expectedOutput)) {
			System.out.println("Expected Output:\n" + expectedOutput + "\n\nbut was:\n" + output);
		}
		Assert.assertEquals(expectedOutput, output);
	}

	/**
	 * Reads a file into a string
	 * 
	 * PS: your file should not be too big (but should work for all our D&A Tests)
	 * 
	 * @param path
	 *            absolute or relative path, whatever you prefer
	 * @return String containing the content of the file
	 * @throws IOException
	 *             you probably did something wrong with your file/filename
	 */
	protected String readFile(String path) throws IOException {
		FileInputStream in = new FileInputStream(path);
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			int numbytes;
			byte[] buffer = new byte[512];
			while ((numbytes = in.read(buffer)) >= 0) {
				byteOut.write(buffer, 0, numbytes);
			}

			return new String(byteOut.toByteArray());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println("Error while closing file " + e);
				e.printStackTrace();
			}
		}
	}
}
