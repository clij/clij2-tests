package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VoronoiLabelingTest {
    @Test
    public void voronoiLabelingTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_input = clij2.pushMat(new double[][] {
                {0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][]{
                {1, 1, 1, 2, 2, 2},
                {1, 1, 1, 2, 2, 2},
                {1, 1, 1, 2, 2, 2},
                {3, 3, 3, 4, 4, 4},
                {3, 3, 3, 4, 4, 4},
                {3, 3, 3, 4, 4, 4}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.voronoiLabeling(gpu_input, gpu_output);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0);

        clij2.clear();
    }
}
