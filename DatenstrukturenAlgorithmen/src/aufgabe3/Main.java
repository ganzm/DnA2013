package aufgabe3;

import java.util.Scanner;

/**
 * MIN heap sort
 * 
 * @author mat
 * 
 */
public class Main {

	private int[] heap;
	private int heapSize;

	private Main(int maxHeapSize) {
		heap = new int[maxHeapSize];
		heapSize = 0;
	}

	private static void performTest(int[] d) {
		Main a = new Main(d.length);

		// phase 1
		boolean first = true;
		for (int i = 0; i < d.length; i++) {
			a.insert(d[i]);

			int last = a.queryLast();

			if (!first) {
				System.out.print(" ");
			}

			System.out.print(last);
			first = false;
		}

		// line break
		System.out.println("");

		// phase 2
		first = true;
		for (int i = 0; i < d.length; i++) {
			int min = a.extractMin();
			if (!first) {
				System.out.print(" ");
			}

			System.out.print(min);
			first = false;
		}

		// line break
		System.out.println("");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {
			// number of test instances
			int t = in.nextInt();

			for (int tIdx = 0; tIdx < t; tIdx++) {

				int n = in.nextInt();
				int[] d = new int[n];
				for (int i = 0; i < n; i++) {
					d[i] = in.nextInt();
				}

				performTest(d);

			}

		} finally {
			in.close();
		}
	}

	/**
	 * Insert(v) fugt das Element v in den Heap ein und stellt die
	 * Heap-Bedingung wieder her
	 * 
	 * @param v
	 */
	private void insert(int v) {
		// spezialfall, wenn heap leer
		if (heapSize == 0) {
			heap[0] = v;
			heapSize++;
			return;
		}

		// platziert das neue element zuhinterst
		heap[heapSize++] = v;

		// Element v das wir an Position heap[heapSize-1] eingefügt haben nach
		// oben durchschieben
		int toSinkIndex = heapSize - 1;
		int parentIndex = getParentIndex(toSinkIndex);

		while (heap[parentIndex] > heap[toSinkIndex]) {

			// swap element with parent
			int tmp = heap[parentIndex];
			heap[parentIndex] = heap[toSinkIndex];
			heap[toSinkIndex] = tmp;

			if (parentIndex == 0) {
				// we're on top of the heap
				break;
			}

			// move one up
			toSinkIndex = parentIndex;
			parentIndex = getParentIndex(parentIndex);
		}
	}

	/**
	 * 
	 * @param i
	 *            zero bound heap array index
	 * @return zero bound heap array index
	 */
	private int getParentIndex(int i) {
		return ((i + 1) / 2) - 1;
	}

	private int getLeftChildIndex(int i) {
		int idx = ((i + 1) << 1) - 1;
		return idx;
	}

	private int getRightChildIndex(int i) {
		int idx = (i + 1) << 1;
		return idx;
	}

	/**
	 * Extract-Min extrahiert das Minimum aus dem Heap und liefert es zuruck.
	 * 
	 * @param heap
	 * @param heapSize
	 * @return
	 */
	private int extractMin() {
		// since this is a Min heap sort, the min element is on top of the heap
		int min = heap[0];

		// das letzte Array-Element kommt in den Heap Rootknoten ...
		heap[0] = heap[--heapSize];

		// ... und muss nun versickert werden
		int indexToSink = 0;
		int left = -1;
		int right = -1;
		boolean running = true;
		while (running) {
			left = getLeftChildIndex(indexToSink);
			right = getRightChildIndex(indexToSink);

			if (left >= heapSize) {
				// no more leaves
				break;
			} else if (right >= heapSize) {
				// only one leafe (the left one)
				// special case where node has only one child

				// check if we need to swap with left element (there is no right
				// element)
				if (heap[indexToSink] > heap[left]) {
					// swap
					int tmp = heap[indexToSink];
					heap[indexToSink] = heap[left];
					heap[left] = tmp;
				}

				// done with sinking
				break;
			} else if (heap[indexToSink] > heap[left]
					&& heap[left] <= heap[right]) {
				// swap
				int tmp = heap[indexToSink];
				heap[indexToSink] = heap[left];
				heap[left] = tmp;

				// follow left path
				indexToSink = left;
			} else if (heap[indexToSink] > heap[right]
					&& heap[left] > heap[right]) {
				// swap
				int tmp = heap[indexToSink];
				heap[indexToSink] = heap[right];
				heap[right] = tmp;

				// follow right path
				indexToSink = right;
			} else {
				// we're done with the sinking
				// there are leaves but we found the right spot for the element
				// to sink
				break;
			}
		}

		return min;
	}

	/**
	 * liefert das Element in der letzten Position des Arrays zuruck, das den
	 * Heap repräsentiert.
	 * 
	 * @param array
	 * @param heapSize
	 * @return
	 */
	private int queryLast() {
		return heap[heapSize - 1];
	}
}
