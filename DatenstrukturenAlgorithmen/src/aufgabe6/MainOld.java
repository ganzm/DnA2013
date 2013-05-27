package aufgabe6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Aufgabe 6 Blum Alorithmus
 * 
 * 
 * @author mat
 * 
 */
public class MainOld {

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
				int[] x = new int[n];
				for (int i = 0; i < n; i++) {
					x[i] = in.nextInt();
				}

				new MainOld().aufgabe6(x);
			}

		} finally {
			in.close();
		}
	}

	private void aufgabe6(int[] x) {
		StringBuffer buf = new StringBuffer();

		int schlussMedian = getMedianWithBlum(x, 1, buf);
		buf.append(schlussMedian);
		buf.append(" ");

		System.out.println(buf.toString().trim());
	}

	private int getMedianWithBlum(int[] x, int blumRecursionCount, StringBuffer buf) {
		// Step 1 Median der Mediane
		int medianDerMediane = getMediane(x, blumRecursionCount, 1, buf);

		if (blumRecursionCount == 1) {

			buf.append(medianDerMediane);
			buf.append(" ");
		}

		// Step 2

		return teilen(x, medianDerMediane, blumRecursionCount, buf);
	}

	private int teilen(int[] x, int pivot, int blumRecursionCount, StringBuffer buf) {
		boolean pivotIgnored = false;

		List<Integer> lower = new ArrayList<>();
		List<Integer> higher = new ArrayList<>();

		for (int i : x) {
			if (i < pivot) {
				lower.add(i);
			} else if (i > pivot) {
				higher.add(i);
			} else if (pivotIgnored == false) {
				pivotIgnored = true;
			} else {
				lower.add(i);
			}
		}

		int[] lowerArray = toIntArray(lower);
		int[] higherArray = toIntArray(higher);

		if (lowerArray.length == higherArray.length) {
			// pivot ist unser median
			return pivot;
		} else if (lowerArray.length > higherArray.length) {
			return getMedianWithBlum(lowerArray, ++blumRecursionCount, buf);
		} else {
			return getMedianWithBlum(higherArray, ++blumRecursionCount, buf);
		}
	}

	private int getMediane(int[] x, int blumRecursionCount, int medianRecursionCount, StringBuffer out) {

		int[] x5 = new int[5];
		List<Integer> mediane = new ArrayList<>(x.length / 5 + 1);
		for (int i = 0; i < x.length / 5; i++) {
			x5[0] = x[5 * i];
			x5[1] = x[5 * i + 1];
			x5[2] = x[5 * i + 2];
			x5[3] = x[5 * i + 3];
			x5[4] = x[5 * i + 4];
			mediane.add(median5(x5));
		}

		int remainder = x.length % 5;
		switch (remainder) {
		case 4:
			mediane.add(median4(new int[] { x[x.length - 4], x[x.length - 3], x[x.length - 2], x[x.length - 1] }));
			break;
		case 3:
			mediane.add(median3(new int[] { x[x.length - 3], x[x.length - 2], x[x.length - 1] }));
			break;
		case 2:
			mediane.add(median2(new int[] { x[x.length - 2], x[x.length - 1] }));
			break;
		case 1:
			mediane.add(x[x.length - 1]);
			break;
		}

		int[] neueMediane = toIntArray(mediane);

		if (medianRecursionCount == 1 && blumRecursionCount == 1) {
			for (int i : neueMediane) {
				out.append(i);
				out.append(" ");
			}
		}

		if (neueMediane.length == 1) {
			return neueMediane[0];
		} else {
			return getMediane(neueMediane, blumRecursionCount, ++medianRecursionCount, out);
		}
	}

	private int[] toIntArray(List<Integer> list) {
		int[] ret = new int[list.size()];
		int i = 0;
		for (Integer e : list)
			ret[i++] = e.intValue();
		return ret;
	}

	private Integer median2(int x[]) {
		return Math.max(x[0], x[1]);
	}

	private Integer median3(int x[]) {
		Arrays.sort(x);
		return x[1];
	}

	private Integer median4(int x[]) {
		Arrays.sort(x);
		return x[1];
	}

	private int median5(int[] x) {
		Arrays.sort(x);
		return x[2];
	}
}
