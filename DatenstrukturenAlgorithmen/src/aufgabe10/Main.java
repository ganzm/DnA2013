package aufgabe10;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Edmonds und Karp
 * 
 * MaxFlow
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

				System.out.println(maxFlow(n, edges));
			}

		} finally {
			in.close();
		}
	}

	private static Queue<Integer> bfsQueue;

	/**
	 * @param n
	 *            vertex count
	 * @param edges
	 * @return
	 */
	private static int maxFlow(int n, Edge[] edges) {
		bfsQueue = new ArrayBlockingQueue<Integer>(n);

		int maxflow = 0;

		// create capacity matrix
		int[][] c = new int[n][n];
		for (Edge e : edges) {
			c[e.u][e.v] = e.c;
		}

		// parent table shows for each node its predecessor when do breadth search
		int[] parentTable = new int[n];

		// initialization finished
		while (true) {
			// reset parent table
			for (int i = 0; i < parentTable.length; i++) {
				parentTable[i] = -1;
			}

			// search new flow path
			int pathCapacity = breadthFirstSearch(c, parentTable);
			if(pathCapacity <= 0){
				// we are done now
				return maxflow;
			}
			
			maxflow += pathCapacity;
			
			// updated capacity matrix
			int current = n-1;
			int previous;
			while(current != 0){
				previous = parentTable[current];
				
				c[previous][current] -= pathCapacity;
				c[current][previous] += pathCapacity;

				current = previous;
			}
		}
	}

	/**
	 * 
	 * @param c
	 * @param capacityTable
	 * @param parentTable
	 * @return maximum capacity on the path
	 */
	private static int breadthFirstSearch(int[][] c, int[] parentTable) {
		bfsQueue.clear();
		bfsQueue.add(0);

		int t = parentTable.length - 1;

		int[] capacityTable = new int[c[0].length];
		capacityTable[0] = Integer.MAX_VALUE;

		while (!bfsQueue.isEmpty()) {
			int currentNode = bfsQueue.poll();

			// init with 1 because we don't want to visit the start node again
			for (int i = 1; i < c.length; i++) {
				// there is remaining capacity and we have not yet visited the node
				if (c[currentNode][i] > 0 && parentTable[i] < 0) {

					parentTable[i] = currentNode;
					capacityTable[i] = Math.min(c[currentNode][i], capacityTable[currentNode]);

					bfsQueue.add(i);

					if (i == t) {
						// we found end node t
						return capacityTable[t];
					}
				}
			}
		}

		// no flow found
		return 0;
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
