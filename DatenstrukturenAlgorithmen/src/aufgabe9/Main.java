package aufgabe9;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author mat
 */
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {
			// number of test instances
			int t = in.nextInt();

			for (int tIndex = 0; tIndex < t; tIndex++) {

				// number of vertices
				int n = in.nextInt();
				// number of edges
				int m = in.nextInt();

				Edge[] edges = new Edge[m];
				for (int i = 0; i < m; i++) {

					Edge e = new Edge();
					e.u = in.nextInt();
					e.v = in.nextInt();
					e.c = in.nextInt();

					// first Node is at index 0 not 1
					e.u -= 1;
					e.v -= 1;

					edges[i] = e;
				}

				int totalCost = minimumSpanningTree(n, edges);
				System.out.println(totalCost);
			}

		} finally {
			in.close();
		}
	}

	/**
	 * Gemäss Kruskal
	 * 
	 * @param n
	 * @param edges
	 * @return
	 */
	private static int minimumSpanningTree(int n, Edge[] edges) {
		int[] set = new int[n];
		for (int i = 0; i < n; i++) {
			set[i] = i;
		}

		int totalCost = 0;
		Arrays.sort(edges);
		for (Edge e : edges) {
			if (!isInSameSet(set, e.u, e.v)) {
				// edge e is part of the minimum spanning tree
				
				totalCost += e.c;
				mergeSets(set, e.u, e.v);
			}
		}

		return totalCost;
	}

	private static void mergeSets(int[] set, int u, int v) {
		int toReplace = set[v];
		int newSetNr = set[u];
		for (int i = 0; i < set.length; i++) {
			if (set[i] == toReplace) {
				set[i] = newSetNr;
			}
		}
	}

	private static boolean isInSameSet(int[] set, int u, int v) {
		return set[u] == set[v];
	}

	private static class Edge implements Comparable<Edge> {
		public int u;
		public int v;

		/**
		 * Cost
		 */
		public int c;

		@Override
		public int compareTo(Edge o) {
			if (o == null) {
				return 1;
			}
			return c - o.c;
		}
		
		@Override
		public String toString() {
			return u + " " + v + " " + " c:" + c;
		}
	};
}
