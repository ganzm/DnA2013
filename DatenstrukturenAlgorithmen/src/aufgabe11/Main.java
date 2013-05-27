package aufgabe11;

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * Convex Hull Graham's Scan
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

				Point[] points = new Point[n];
				for (int i = 0; i < n; i++) {

					Point p = new Point();
					p.x = in.nextInt();
					p.y = in.nextInt();

					points[i] = p;
				}

				Stack<Point> hullPoints = convexHull(points);

				Iterator<Point> hullPointIter = hullPoints.iterator();
				boolean first = true;
				while (hullPointIter.hasNext()) {
					Point p = hullPointIter.next();

					if (!first) {
						System.out.print(" ");
					}
					System.out.print(p.x + " " + p.y);
					first = false;
				}
				System.out.println("");
			}

		} finally {
			in.close();
		}
	}

	private static Stack<Point> convexHull(Point[] points) {

		// alle punkte nach x,y sortieren
		XAxisPointComparator comparator = new XAxisPointComparator();
		Arrays.sort(points, comparator);

		Stack<Point> hullPoints = new Stack<Point>();

		Point previous = points[0];
		Point current = points[1];

		// der Punkt ganz links ist in der hülle
		hullPoints.push(previous);
		hullPoints.push(current);

		// -------------------------------------------------
		// obere hülle bestimmen
		// -------------------------------------------------
		for (int i = 2; i < points.length; i++) {
			Point candidate = points[i];

			while (isOnTheLeftSide(previous, current, candidate)) {
				if (hullPoints.size() == 2) {
					hullPoints.pop();
					break;
				}

				// get rid of the previously added hullpoint
				hullPoints.pop();
				current = hullPoints.peek();
				previous = hullPoints.get(hullPoints.size() - 2);
			}
			// candidate links(über) der aktuellen Geraden

			previous = hullPoints.peek();
			hullPoints.push(candidate);
			current = candidate;
		}

		// wir sind am rechten ende angekommen
		// füge den punkt an zweiter stelle von rechts zur hülle hinzu
		previous = current;
		current = points[points.length - 2];
		hullPoints.add(current);

		// -------------------------------------------------
		// untere hülle bestimmen
		for (int i = points.length - 3; i >= 0; i--) {
			Point candidate = points[i];

			while (isOnTheLeftSide(previous, current, candidate)) {
				if (hullPoints.size() == 2) {
					hullPoints.pop();
					break;
				}

				// get rid of the previously added hullpoint
				hullPoints.pop();
				current = hullPoints.peek();
				previous = hullPoints.get(hullPoints.size() - 2);
			}
			// candidate links(über) der aktuellen Geraden

			previous = hullPoints.peek();
			hullPoints.push(candidate);
			current = candidate;
		}

		// remove endpoint because it's also the startpoint
		hullPoints.pop();
		return hullPoints;
	}

	/**
	 * Rechnet Winke mit Rechte-Hand-Regel
	 * 
	 * zwischen Vektor (from, to) und (to, toCheck)
	 * 
	 * @param from
	 * @param to
	 * @param toCheck
	 * @return
	 */
	private static boolean isOnTheLeftSide(Point from, Point to, Point toCheck) {
		double angleTo = Math.atan2(to.y - from.y, to.x - from.x);
		double angleToCheck = Math.atan2(toCheck.y - to.y, toCheck.x - to.x);
		double angle = angleToCheck - angleTo;

		double angleDegree1 = Math.toDegrees(angle);
		if (angle > Math.PI) {
			angle = -2 * Math.PI + angle;
		} else if (angle < -Math.PI) {
			angle = 2 * Math.PI + angle;
		}

		double angleDegree = Math.toDegrees(angle);

		return angle > 0;
	}

	private static class XAxisPointComparator implements Comparator<Point> {
		@Override
		public int compare(Point p0, Point p1) {
			int res = p0.x - p1.x;
			if (res == 0) {
				return p0.y - p1.y;
			}
			return res;
		}
	};
}
