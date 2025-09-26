package practice.DesignAss1;

import java.util.Arrays;
import java.util.Comparator;

public class сlosestPair {

    public static int currentDepth = 0;
    public static int maxDepth = 0;

    public static void resetDepth() {
        currentDepth = 0;
        maxDepth = 0;
    }

    public static double closestPair(double[][] pts) {
        Point[] arr = new Point[pts.length];
        for (int i = 0; i < pts.length; i++) {
            arr[i] = new Point(pts[i][0], pts[i][1]);
        }
        return closestPair(arr);
    }

    public static double closestPairNaive(double[][] pts) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                double dx = pts[i][0] - pts[j][0];
                double dy = pts[i][1] - pts[j][1];
                double d = Math.sqrt(dx * dx + dy * dy);
                if (d < min) min = d;
            }
        }
        return min;
    }

    public static class Point {
        public final double x;
        public final double y;
        public Point(double x, double y) {
            this.x = x; this.y = y;
        }
    }

    public static double closestPair(Point[] points) {
        Point[] ptsSortedByX = points.clone();
        Arrays.sort(ptsSortedByX, Comparator.comparingDouble(p -> p.x));
        Point[] aux = new Point[points.length];
        return closestRec(ptsSortedByX, aux, 0, points.length - 1);
    }

    private static double closestRec(Point[] ptsX, Point[] aux, int left, int right) {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;

        if (right - left <= 3) {
            double res = bruteForce(ptsX, left, right);
            currentDepth--;
            return res;
        }

        int mid = (left + right) / 2;
        double midX = ptsX[mid].x;
        double dLeft = closestRec(ptsX, aux, left, mid);
        double dRight = closestRec(ptsX, aux, mid + 1, right);
        double d = Math.min(dLeft, dRight);

        mergeByY(ptsX, aux, left, mid, right);
        int m = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(ptsX[i].x - midX) < d) {
                aux[m++] = ptsX[i];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m && (aux[j].y - aux[i].y) < d; j++) {
                double dist = dist(aux[i], aux[j]);
                if (dist < d) d = dist;
            }
        }

        currentDepth--;
        return d;
    }

    private static double bruteForce(Point[] pts, int left, int right) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double dist = dist(pts[i], pts[j]);
                if (dist < min) min = dist;
            }
        }
        Arrays.sort(pts, left, right + 1, Comparator.comparingDouble(p -> p.y));
        return min;
    }

    private static void mergeByY(Point[] pts, Point[] aux, int left, int mid, int right) {
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (pts[i].y <= pts[j].y) aux[k++] = pts[i++];
            else aux[k++] = pts[j++];
        }
        while (i <= mid) aux[k++] = pts[i++];
        while (j <= right) aux[k++] = pts[j++];
        System.arraycopy(aux, 0, pts, left, k);
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
