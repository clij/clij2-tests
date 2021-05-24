package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VoronoiOtsuLabelingTest {
    @Test
    public void voronoiOtsuLabelingTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_input = clij2.pushMat(new double[][] {
                {0, 0, 1, 1, 0, 0},
                {0, 1, 8, 9, 1, 0},
                {0, 1, 7, 6, 1, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 0, 1, 8, 7, 1},
                {0, 0, 1, 1, 1, 0}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][]{
                {0, 0, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 0},
                {0, 0, 1, 2, 0, 0},
                {0, 0, 0, 2, 2, 0},
                {0, 0, 2, 2, 2, 0},
                {0, 0, 0, 2, 2, 0}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.voronoiOtsuLabeling(gpu_input, gpu_output, 1, 1);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0);

        clij2.clear();
    }
}
