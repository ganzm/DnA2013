package aufgabe7;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Rucksack Problem
 * 
 * @author mat
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {
			// number of test instances
			int t = in.nextInt();

			for (int tIdx = 0; tIdx < t; tIdx++) {

				int n = in.nextInt();
				int wmax = in.nextInt();

				int[] v = new int[n];
				int[] w = new int[n];

				for (int i = 0; i < n; i++) {
					v[i] = in.nextInt();
				}

				for (int i = 0; i < n; i++) {
					w[i] = in.nextInt();
				}

				new Main().aufgabe7(v, w, wmax);
			}

		} finally {
			in.close();
		}
	}

	/**
	 * 
	 * @param v
	 *            Values
	 * @param w
	 *            Weights
	 * @param wMax
	 *            maximum weight
	 */
	private void aufgabe7(int[] v, int[] w, int wMax) {
		System.out.println(getMaxLoad(v, w, wMax));
	}

	private String getMaxLoad(int[] v, int[] w, int wMax) {

		// 1. Dynamical stuff
		// a[i][j] represents the value reached with objects chosen from {1..i} and maximum weight j
		int iMax = v.length + 1;
		int jMax = wMax + 1;
		int[][] a = new int[iMax][jMax];

		// not required to initialize
		// a[i][0] = 0;
		// a[0][j] = 0;

		for (int j = 1; j < jMax; j++) {
			for (int i = 1; i < iMax; i++) {

				if (w[i - 1] <= j && a[i - 1][j - w[i - 1]] + v[i - 1] >= a[i - 1][j]) {
					a[i][j] = a[i - 1][j - w[i - 1]] + v[i - 1];
				} else {
					a[i][j] = a[i - 1][j];
				}
			}
		}

		// 2. Backtracking
		int nIndex = iMax - 1;
		int wIndex = jMax - 1;
		List<Integer> backpack = new ArrayList<Integer>();

		while (nIndex > 0 && wIndex > 0) {
			int wn = w[nIndex - 1];
			int vn = v[nIndex - 1];
			if (wn <= wIndex && a[nIndex][wIndex] == a[nIndex - 1][wIndex - wn] + vn) {

				backpack.add(nIndex);

				wIndex -= wn;
			}
			nIndex--;

		}

		
		// 3. Prepare output
		StringBuffer buf = new StringBuffer();
		buf.append(a[iMax - 1][jMax - 1]);

		Object[] integerArray = backpack.toArray();
		Arrays.sort(integerArray);

		for (Object o : integerArray) {

			buf.append(" ");
			buf.append(o);
		}

		return buf.toString();
	}
}
