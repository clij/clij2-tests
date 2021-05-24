package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimumOfTouchingNeighborsMapTest {
    @Test
    public void minimumOfTouchingNeighborsMapTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_intensities = clij2.pushMat(new double[][] {
                {1,1,2,2},
                {1,0,0,2},
                {4,0,0,6},
                {4,4,6,6}
        });

        ClearCLBuffer gpu_labels = clij2.pushMat(new double[][] {
                {1,1,2,2},
                {1,0,0,2},
                {3,0,0,4},
                {3,3,4,4}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][]{
                {1,1,1,1},
                {1,0,0,1},
                {1,0,0,2},
                {1,1,2,2}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.minimumOfTouchingNeighborsMap(gpu_intensities, gpu_labels, gpu_output, 1, true);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0.01);

        clij2.clear();
    }
}
