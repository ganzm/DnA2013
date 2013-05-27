package aufgabe1;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * Datenstrukturen & Algorithmen
 * 
 * Uebung 1
 * 
 * @author mat
 * 
 */
public class Main {

	public static void main(String[] args) {

		NumberFormat decimalFormat = new DecimalFormat("#######");

		// Read input
		Scanner in = new Scanner(System.in);

		try {

			// number of TestInstances
			int t = in.nextInt();

			for (int testInstanceIndex = 0; testInstanceIndex < t; testInstanceIndex++) {

				int r_i = in.nextInt();
				int a = in.nextInt();
				int b = in.nextInt();
				int c = in.nextInt();
				int d = in.nextInt();

				double result = 0;

				if (r_i == 0) {
					result = a;
				}

				else if (r_i == 1) {
					result = b;
				} else {

					double r_i_1 = b;
					double r_i_2 = a;

					for (int i = 2; i <= r_i; i++) {

						// calculate stuff
						result = c * r_i_1 + d * r_i_2;

						// shift
						r_i_2 = r_i_1;
						r_i_1 = result;
					}
				}

				// Output result
				System.out.println(decimalFormat.format(result));
			}
		} finally {
			in.close();
		}
	}
}
