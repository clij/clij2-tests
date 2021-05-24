package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TouchingNeighborCountMapTest {
    @Test
    public void touchingNeighborCountMapTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_input = clij2.pushMat(new double[][] {
                {1, 1, 0, 3, 3},
                {1, 1, 2, 3, 3},
                {0, 2, 2, 2, 0},
                {4, 4, 2, 5, 5},
                {4, 4, 0, 5, 5}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][]{
                {1, 1, 0, 1, 1},
                {1, 1, 4, 1, 1},
                {0, 4, 4, 4, 0},
                {1, 1, 4, 1, 1},
                {1, 1, 0, 1, 1}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.touchingNeighborCountMap(gpu_input, gpu_output);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0);

        clij2.clear();
    }
}
