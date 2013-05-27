package aufgabe5;

import java.util.Scanner;

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
				int[] k = new int[n];
				for (int i = 0; i < n; i++) {
					k[i] = in.nextInt();
				}

				new Main().aufgabe5(k);
			}

		} finally {
			in.close();
		}
	}

	/**
	 * 
	 * @param k
	 *            keys to insert
	 */
	private void aufgabe5(int[] k) {

		Tree tree = new Tree();

		for (int i : k) {
			tree.insert(i);
		}

		String result = tree.visitPostOrder();
		System.out.println(result);
	}

	private class Tree {
		private Node rootNode;

		public Tree() {
			rootNode = null;
		}

		public void insert(int key) {
			// null case
			if (rootNode == null) {
				rootNode = new Node(key);
				return;
			}

			// do recursive stuff iterative
			Node current = rootNode;
			while (true) {
				if (key <= current.getKey()) {
					if (current.left() == null) {
						current.addLeft(key);
						break;
					} else {
						current = current.left();
					}
				} else {
					if (current.right() == null) {
						current.addRight(key);
						break;
					} else {
						current = current.right();
					}
				}
			}

		}

		public String visitPostOrder() {
			StringBuffer buf = new StringBuffer();

			if (rootNode == null) {
				return "0";
			}

			rootNode.visit(buf);

			// ommit the first space
			return buf.toString();
		}
	}

	private class Node {

		/**
		 * Nodevalue
		 */
		private int key;
		private Node leftChild;
		private Node rightChild;

		public Node(int key) {
			this.key = key;
			this.leftChild = null;
			this.rightChild = null;
		}

		public int visit(StringBuffer buf) {

			int depthLeft = 0;
			int depthRight = 0;
			if (leftChild != null) {
				depthLeft = leftChild.visit(buf);
			}

			if (rightChild != null) {
				depthRight = rightChild.visit(buf);
			}

			int depth = Math.max(depthLeft, depthRight);
			buf.append(key);
			buf.append(" ");
			buf.append(depthLeft);
			buf.append(" ");
			buf.append(depthRight);
			buf.append(" ");

			return 1 + depth;
		}

		public void addRight(int key) {
			rightChild = new Node(key);
		}

		public Node right() {
			return rightChild;
		}

		public int getKey() {
			return key;
		}

		public void addLeft(int key) {
			leftChild = new Node(key);
		}

		public Node left() {
			return leftChild;
		}

		public String toString() {
			return "" + key;
		}
	}
}
