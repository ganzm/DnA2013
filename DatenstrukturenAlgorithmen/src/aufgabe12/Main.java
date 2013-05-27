package aufgabe12;

import java.util.Scanner;

/**
 * 
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

				// number of dots
				int n = in.nextInt();
				int m = in.nextInt();

				Edge[] edges = new Edge[m];
				for (int i = 0; i < m; i++) {

					Edge e = new Edge();

					e.u = in.nextInt() - 1;
					e.v = in.nextInt() - 1;
					e.c = in.nextInt();

					edges[i] = e;
				}

				shortestPath(edges, n);
			}

		} finally {
			in.close();
		}
	}

	private static void shortestPath(Edge[] edges, int n) {
		// previous node of our shortest path
		int[] predecessor = new int[n];
		// sum of c along the shortest path
		int[] distance = new int[n];

		// initialisation
		for (int i = 0; i < n; i++) {
			predecessor[i] = -1;
			distance[i] = Integer.MAX_VALUE;
		}

		// init start node
		predecessor[0] = 0;
		distance[0] = 0;

		for (int i = 0; i < n; i++) {
			for (Edge edge : edges) {
				if (predecessor[edge.u] != -1) {

					int newDist = edge.c + distance[edge.u];
					if (newDist < distance[edge.v]) {
						distance[edge.v] = newDist;
						predecessor[edge.v] = edge.u;
					}
				}
			}
		}

		// check for negative-weight cycles
		for (Edge edge : edges) {
			if (distance[edge.u] + edge.c < distance[edge.v]) {
				System.out.println("NEGATIVE-CYCLE");
				return;
			}
		}

		boolean isFirst = true;
		for (int dist : distance) {
			if (!isFirst) {
				System.out.print(" ");
			}
			System.out.print(dist);
			isFirst = false;
		}
		System.out.print("\n");

	}

	private static class Edge implements Comparable<Edge> {
		public int u;
		public int v;
		public int c;

		@Override
		public String toString() {
			return u + "/" + v + " c=" + c;
		}

		@Override
		public int compareTo(Edge o) {
			int res = u - o.u;
			if (res != 0) {
				return res;
			}

			res = v = o.v;
			if (res != 0) {
				return res;
			}

			return c = o.c;
		}
	};
}
