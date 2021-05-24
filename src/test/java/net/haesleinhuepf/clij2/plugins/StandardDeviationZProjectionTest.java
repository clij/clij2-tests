package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StandardDeviationZProjectionTest {
    @Test
    public void standardDeviationZProjectionTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_input = clij2.pushMatXYZ(new double[][][] {
                {
                {1, 0, 0, 0, 9},
            {0, 2, 0, 8, 0},
            {3, 0, 1, 0, 10},
            {0, 4, 0, 7, 0},
            {5, 0, 6, 0, 10}
        }, {
            {0, 2, 0, 8, 0},
            {1, 0, 0, 0, 9},
            {3, 0, 1, 0, 10},
            {0, 4, 0, 7, 0},
            {5, 0, 6, 0, 10}
        }, {
            {0, 2, 0, 8, 0},
            {3, 0, 1, 0, 10},
            {0, 4, 0, 7, 0},
            {1, 0, 0, 0, 9},
            {5, 0, 6, 0, 10}
        }, {
            {0, 2, 0, 8, 0},
            {1, 0, 0, 0, 9},
            {0, 4, 0, 7, 0},
            {3, 0, 1, 0, 10},
            {5, 0, 6, 0, 10}
        }, {
            {1, 0, 0, 0, 9},
            {0, 4, 0, 7, 0},
            {3, 0, 1, 0, 10},
            {0, 2, 0, 8, 0},
            {5, 0, 6, 0, 10}
        }
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][]{
                {3.94, 3.46, 3.46, 3.46, 3.94},
                {3.46, 3.94, 4.21, 3.94, 3.19},
                {4.21, 4.21, 3.19, 3.19, 4.21},
                {3.19, 3.19, 3.94, 4.21, 3.46},
                {4.27, 4.27, 4.27, 4.27, 4.27}
        });

        ClearCLBuffer gpu_output = clij2.create(5, 5);
        clij2.standardDeviationZProjection(gpu_input, gpu_output);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0.01);

        clij2.clear();
    }
}
