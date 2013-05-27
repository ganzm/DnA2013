package aufgabe8;

import java.util.Scanner;

/**
 * Longest common subsequence
 * 
 * @author mat
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
				String a = in.next();
				String b = in.next();

				new Main().aufgabe8(a, b);
			}

		} finally {
			in.close();
		}
	}

	private void aufgabe8(String a, String b) {
		int m = a.length();
		int n = b.length();

		// A[i][j] contains the length of the longest
		int[][] A = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					A[i][j] = A[i - 1][j - 1] + 1;
				} else {
					int tmp1 = A[i - 1][j];
					int tmp2 = A[i][j - 1];
					A[i][j] = Math.max(tmp1, tmp2);
				}
			}
		}

		int k = A[m][n];
		char[] result = new char[k];

		// backtracking
		int i = m;
		int j = n;
		while (k > 0) {
			if (a.charAt(i - 1) == b.charAt(j - 1)) {
				result[--k] = a.charAt(i - 1);
				i--;
				j--;
			} else {
				if (A[i - 1][j] == A[i][j]) {
					i--;
				} else {
					j--;
				}
			}
		}

		System.out.println(A[m][n] + " " + new String(result));
	}
}
