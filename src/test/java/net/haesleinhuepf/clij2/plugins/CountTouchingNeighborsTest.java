package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountTouchingNeighborsTest {
    @Test
    public void countTouchingNeighborsTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_labels = clij2.pushMat(new double[][] {
                {1,1,0,3,3},
                {1,1,2,3,3},
                {0,2,2,2,0},
                {4,4,2,5,5},
                {4,4,0,5,5}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][]{
                {5, 2, 5, 2, 2, 2}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);

        ClearCLBuffer touch_matrix = clij2.create(6,6);
        clij2.generateTouchMatrix(gpu_labels, touch_matrix);

        clij2.countTouchingNeighbors(touch_matrix, gpu_output);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0.01);

        clij2.clear();
    }
}
