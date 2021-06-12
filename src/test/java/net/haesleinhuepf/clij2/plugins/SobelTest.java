package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SobelTest {
    @Test
    public void sobelTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_input = clij2.pushMat(new double[][] {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][]{
                {0, 0, 0, 0, 0},
                {0, 1.41, 2, 1.41, 0},
                {0, 3.16, 2, 3.16, 0},
                {0, 3.16, 2, 3.16, 0},
                {0, 1.41, 2, 1.41, 0}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.sobel(gpu_input, gpu_output);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0.01);

        clij2.clear();
    }
}
