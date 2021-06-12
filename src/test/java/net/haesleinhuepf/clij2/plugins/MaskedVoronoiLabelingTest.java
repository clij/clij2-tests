package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaskedVoronoiLabelingTest {
    @Test
    public void maskedVoronoiLabelingTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_input = clij2.pushMatXYZ(new double[][] {
                {1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1},
        });

        ClearCLBuffer gpu_mask = clij2.pushMatXYZ(new double[][] {
                {1, 1, 1, 1, 0, 1},
                {1, 0, 0, 1, 0, 1},
                {1, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1},
        });

        ClearCLBuffer gpu_reference = clij2.pushMatXYZ(new double[][]{
                {1, 1, 1, 1, 0, 3},
                {1, 0, 0, 1, 0, 3},
                {1, 0, 1, 1, 0, 3},
                {2, 0, 1, 1, 0, 4},
                {2, 0, 0, 0, 0, 4},
                {2, 2, 2, 4, 4, 4},
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.maskedVoronoiLabeling(gpu_input, gpu_mask, gpu_output);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0);

        clij2.clear();
    }
}
