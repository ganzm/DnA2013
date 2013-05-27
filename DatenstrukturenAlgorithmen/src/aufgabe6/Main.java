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
				int[] x = new int[n];
				for (int i = 0; i < n; i++) {
					x[i] = in.nextInt();
				}

				new Main().aufgabe6(x);
			}

		} finally {
			in.close();
		}
	}

	private void aufgabe6(int[] x) {
		StringBuffer buf = new StringBuffer();

		int kKleinst = x.length / 2;
		if (x.length % 2 != 0) {
			kKleinst++;
		}

		int schlussMedian = auswahl(x, kKleinst, 1, buf);
		buf.append(schlussMedian);
		buf.append(" ");

		System.out.println(buf.toString().trim());
	}

	private int auswahl(int[] x, int kKleinst, int auswahlRecursionCounter, StringBuffer buf) {
		// 0. Rekursionsabbruch
		if (auswahlRecursionCounter > 1 && x.length < 5) {
			Arrays.sort(x);
			return x[kKleinst - 1];
		}
		
		int[] mediane = getMediane(x, auswahlRecursionCounter);

		if (auswahlRecursionCounter == 1) {
			for (int i : mediane) {
				buf.append(i);
				buf.append(" ");
			}
		}

		// 3. Wende das Verfahren Auswahlrekursiv auf die Mediane an
		int medianKkleinst = mediane.length / 2;
		if (mediane.length % 2 == 1) {
			medianKkleinst++;
		}
		int medianDerMediane = auswahl(mediane, medianKkleinst, auswahlRecursionCounter + 1, buf);

		if (auswahlRecursionCounter == 1) {
			buf.append(medianDerMediane);
			buf.append(" ");
		}

		// 4. Teile
		return teilen(x, kKleinst, medianDerMediane, auswahlRecursionCounter, buf);
	}

	private int teilen(int[] x, int kKleinst, int pivot, int auswahlRecursionCounter, StringBuffer buf) {
		boolean pivotIgnored = false;

		List<Integer> lower = new ArrayList<Integer>();
		List<Integer> higher = new ArrayList<Integer>();

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

		if (lowerArray.length + 1 == kKleinst) {
			return pivot;
		} else if (lowerArray.length >= kKleinst) {
			return auswahl(lowerArray, kKleinst, 1 + auswahlRecursionCounter, buf);
		} else {
			return auswahl(higherArray, kKleinst - lowerArray.length - 1, 1 + auswahlRecursionCounter, buf);
		}
	}

	private int[] getMediane(int[] x, int auswahlRecursionCounter) {
		int[] x5 = new int[5];
		List<Integer> mediane = new ArrayList<Integer>(x.length / 5 + 1);
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

		return neueMediane;
	}

	private int[] toIntArray(List<Integer> list) {
		int[] ret = new int[list.size()];
		int i = 0;
		for (Integer e : list)
			ret[i++] = e.intValue();
		return ret;
	}

	private Integer median2(int x[]) {
		return Math.min(x[0], x[1]);
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
