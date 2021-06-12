package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AverageDistanceOfTouchingNeighborsTest {
    @Test
    public void averageDistanceOfTouchingNeighborsTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_labels = clij2.pushMat(new double[][] {
                {1,1,1,3,3,3},
                {1,1,1,3,3,3},
                {1,1,1,3,3,3},
                {0,0,0,2,2,2},
                {0,0,0,2,2,2},
                {0,0,0,2,2,2}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][]{
                {0, 3, 3, 3}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);

        ClearCLBuffer centroids = clij2.create(3, 2);
        clij2.centroidsOfLabels(gpu_labels, centroids);

        ClearCLBuffer distance_matrix = clij2.create(4,4);
        clij2.generateDistanceMatrix(centroids, centroids, distance_matrix);

        ClearCLBuffer touch_matrix = clij2.create(4,4);
        clij2.generateTouchMatrix(gpu_labels, touch_matrix);

        clij2.averageDistanceOfTouchingNeighbors(distance_matrix, touch_matrix, gpu_output);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0.01);

        clij2.clear();
    }
}
