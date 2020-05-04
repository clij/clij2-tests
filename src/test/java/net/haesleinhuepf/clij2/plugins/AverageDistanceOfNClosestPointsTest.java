package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AverageDistanceOfNClosestPointsTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer distance_matrix = clij2.pushString("" +
                "0 0 0 0 0\n"+
                "0 0 1 1 1\n" +
                "0 1 0 2 2\n" +
                "0 1 2 0 3\n" +
                "0 1 2 3 0");

        ClearCLBuffer vector = clij2.create(5, 1, 1);

        clij2.averageDistanceOfNClosestPoints(distance_matrix, vector, 1);
        clij2.print(vector);
        assertEquals(clij2.pullString(vector), "0.0 1.0 1.0 1.0 1.0");

        clij2.averageDistanceOfNClosestPoints(distance_matrix, vector, 2);
        clij2.print(vector);
        assertEquals(clij2.pullString(vector), "0.0 1.0 1.5 1.5 1.5");

        clij2.averageDistanceOfNClosestPoints(distance_matrix, vector, 3.0);
        clij2.print(vector);
        assertEquals(clij2.pullString(vector), "0.0 1.0 1.6666667 2.0 2.0");

    }
}
