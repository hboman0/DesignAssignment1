package practice.DesignAss1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class ClosestPairTest {

    private double bruteForce(practice.DesignAss1.сlosestPair.Point[] pts) {
        double min = Double.POSITIVE_INFINITY;
        for (int i=0; i<pts.length; i++) {
            for (int j=i+1; j<pts.length; j++) {
                double dx = pts[i].x - pts[j].x;
                double dy = pts[i].y - pts[j].y;
                double dist = Math.sqrt(dx*dx+dy*dy);
                if (dist<min) min=dist;
            }
        }
        return min;
    }

    @Test
    public void testClosestPairSmallRandom() {
        Random rand = new Random(42);
        for (int t=0; t<20; t++) {
            int n = rand.nextInt(100) + 2;
            practice.DesignAss1.сlosestPair.Point[] pts = new practice.DesignAss1.сlosestPair.Point[n];
            for (int i=0; i<n; i++) {
                pts[i] = new practice.DesignAss1.сlosestPair.Point(rand.nextDouble()*1000, rand.nextDouble()*1000);
            }
            double expected = bruteForce(pts);
            double actual = practice.DesignAss1.сlosestPair.closestPair(pts);
            assertEquals(expected, actual, 1e-9, "Trial " + t + " mismatch");
        }
    }
}
