package aufgabe4;

import java.util.Scanner;

/**
 * Aufgabe 4
 * 
 * Offenes Hashing
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

				int m = in.nextInt();
				int n = in.nextInt();
				int[] k = new int[n];
				for (int i = 0; i < n; i++) {
					k[i] = in.nextInt();
				}

				performTest(m, k);

			}

		} finally {
			in.close();
		}
	}

	private static void performTest(int m, int[] k) {
		hashLinear(m, k);
		hashQuadratisch(m, k);
		hashDoubleHashingSondiert(m, k);
	}

	private static void hashLinear(int m, int[] k) {
		int[] a = new int[m];
		int collisionCount = 0;

		for (int i = 0; i < k.length; i++) {
			int keyToInsert = k[i];

			// Index we originally want to insert the key
			int h_k = keyToInsert % m;

			if (a[h_k] == 0) {
				// no collision
				a[h_k] = keyToInsert;
			} else {
				// collision
				collisionCount++;

				// apply sondierungsfunktion
				for (int j = 1; j < m; j++) {

					// linear "sondierung"
					int tmp = (m + h_k - j) % m;
					if (a[tmp] == 0) {
						a[tmp] = keyToInsert;
						break;
					} else {
						collisionCount++;
					}
				}
			}
		}

		System.out.println(collisionCount + " " + arrayToString(a));
	}

	private static void hashQuadratisch(int m, int[] k) {
		int[] a = new int[m];
		int collisionCount = 0;

		for (int i = 0; i < k.length; i++) {
			int keyToInsert = k[i];

			// Index we originally want to insert the key
			int h_k = keyToInsert % m;

			if (a[h_k] == 0) {
				// no collision
				a[h_k] = keyToInsert;
			} else {
				// collision
				collisionCount++;

				// apply quadratic sondierungsfunktion
				for (int j = 1; j < m; j++) {

					// quadratische "sondierung"
					int tmp = 0;
					if (j % 2 == 0) {
						tmp = j / 2;
						tmp = tmp * tmp;
					} else {
						tmp = ((j / 2) + 1);
						tmp = -tmp * tmp;
					}

					tmp = h_k - tmp;
					// avoid negative array indexing
					while (tmp < 0) {
						tmp += m;
					}

					tmp = tmp % m;

					if (a[tmp] == 0) {
						a[tmp] = keyToInsert;
						break;
					} else {
						collisionCount++;
					}
				}
			}
		}

		System.out.println(collisionCount + " " + arrayToString(a));
	}

	private static void hashDoubleHashingSondiert(int m, int[] k) {

		int[] a = new int[m];
		int collisionCount = 0;

		for (int i = 0; i < k.length; i++) {
			int keyToInsert = k[i];

			// Index we originally want to insert the key
			int h_k = keyToInsert % m;

			if (a[h_k] == 0) {
				// no collision
				a[h_k] = keyToInsert;
			} else {
				// collision
				collisionCount++;

				// apply doubleHashing
				for (int j = 1; j < m; j++) {
					int h_strich = 1 + (keyToInsert % (m - 2));
					int tmp = h_k - (j * h_strich);

					// avoid negative array indexing
					while (tmp < 0) {
						tmp += m;
					}

					tmp = tmp % m;

					if (a[tmp] == 0) {
						a[tmp] = keyToInsert;
						break;
					} else {
						collisionCount++;
					}
				}
			}
		}

		System.out.println(collisionCount + " " + arrayToString(a));
	}

	private static String arrayToString(int[] a) {
		StringBuffer buf = new StringBuffer();
		boolean isFirst = true;
		for (int i = 0; i < a.length; i++) {
			if (!isFirst) {
				buf.append(" ");
			}
			buf.append(a[i]);
			isFirst = false;
		}
		return buf.toString();
	}
}
