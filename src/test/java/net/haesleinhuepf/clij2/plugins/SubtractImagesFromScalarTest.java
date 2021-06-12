package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubtractImagesFromScalarTest {
    @Test
    public void subtractImagesFromScalarTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_input1 = clij2.pushMat(new double[][] {
                {0, 0},
                {1, 1},
                {2, 2}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][]{
                {5, 5},
                {4, 4},
                {3, 3},
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.subtractImageFromScalar(gpu_input1, gpu_output, 5);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0);

        clij2.clear();
    }
}
