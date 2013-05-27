package aufgabe6;

import java.util.Arrays;
import java.util.Scanner;

public class MainIvo {

	public static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static int medianPosInArray(int length) {
		return (int) Math.ceil(length / 2.0) - 1;
	}

	public static int getMedian(int[] a) {
		Arrays.sort(a);
		return a[medianPosInArray(a.length)];
	}

	public static int indexOf(int[] a, int l, int r, int e) {
		for (int i = l; i <= r; i++) {
			if (a[i] == e)
				return i;
		}
		return -1;
	}

	public static int[] getMedians(int[] a, int l, int r) {
		int[] medians = new int[(int) Math.ceil((r - l + 1) / 5.0)];
		for (int i = l; i <= r; i = i + 5) {
			medians[(i - l) / 5] = getMedian(Arrays.copyOfRange(a, i, Math.min(i + 5, r + 1)));
		}
		return medians;
	}

	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
	}

	// return index of pivot
	public static int selectPivot(int[] a, int l, int r, boolean print) {
		int[] medians = getMedians(a, l, r);

		if (print)
			printArray(medians);

		if (medians.length <= 5) {
			return indexOf(a, l, r, getMedian(medians));
		}

		int pivot = medians[select(medians, 0, medians.length - 1, medianPosInArray(medians.length), false)];
		return indexOf(a, l, r, pivot);
	}

	// return index of pivot in splited array
	public static int split(int[] a, int l, int r, int v) {
		int p = a[v];
		int s = l;

		swap(a, v, r - 1);
		for (int i = l; i <= r; i++) {
			if (a[i] < p) {
				swap(a, i, s);
				s++;
			}
		}
		swap(a, s, r - 1);
		return s;
	}

	// returns pos of median
	public static int select(int[] a, int l, int r, int i, boolean print) {

		int v = selectPivot(a, l, r, print);

		if (print)
			System.out.print(a[v] + " ");

		if (l >= r)
			return l;

		int m = split(a, l, r, v);

		if (i == m - l)
			return m;

		else if (i < m - l)
			return select(a, l, m - 1, i, false);

		else
			return select(a, m + 1, r, i - (m + 1 - l), false);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt(); // num tests

		for (int j = 0; j < t; j++) {
			int n = in.nextInt(); // num inputs

			int[] row = new int[n];

			for (int i = 0; i < n; i++) {
				int x = in.nextInt(); // input
				row[i] = x;
			}

			int m = select(row, 0, row.length - 1, medianPosInArray(row.length), true);
			System.out.print(row[m] + "\n");
			// System.out.print(m(row.clone()) + "\n");
		}

		in.close();
	}

	static public int m(int[] a) {
		Arrays.sort(a);
		return a[medianPosInArray(a.length)];
	}

}