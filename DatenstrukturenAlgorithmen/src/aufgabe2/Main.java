package aufgabe2;

import java.util.Scanner;

/**
 * Subset sum
 * 
 * @author mat
 * 
 */
public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		try {
			int t = in.nextInt();

			for (int tIdx = 0; tIdx < t; tIdx++) {

				int n = in.nextInt();
				int[] d = new int[n];
				for (int i = 0; i < n; i++) {
					d[i] = in.nextInt();
				}

				int res = subsetSum(d);
				System.out.println(res);
			}

		} finally {
			in.close();
		}

	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	private static int subsetSum(int[] d) {
		int n = d.length;

		// scanline Algorithmus gemäss Buch Seite 15

		// maximum bis zur aktuellen Position der Scanline
		int scanMax = 0;

		// maximum aus dem bereits gescannten Bereich
		int bisMax = 0;

		// "Scanline" 1x durchs Array
		for (int i = 0; i < n; i++) {

			if (scanMax + d[i] > 0) {
				scanMax += d[i];
			} else {
				scanMax = 0;
			}

			if (scanMax > bisMax) {
				bisMax = scanMax;
			}
		}

		return bisMax;
	}

	/**
	 * Subset Max Sum Naiv und langsam
	 * 
	 * @param d
	 * @return
	 */
	private static int subsetSumNaiv(int[] d) {
		int res = 0;
		int n = d.length;

		for (int i = 0; i < n; i++) {
			// don't start with a negative value
			if (i < 0) {
				break;
			}

			int subsetSum = 0;
			for (int j = i; j < n; j++) {
				subsetSum += d[j];
				res = Math.max(res, subsetSum);
			}
		}

		return res;
	}

}
