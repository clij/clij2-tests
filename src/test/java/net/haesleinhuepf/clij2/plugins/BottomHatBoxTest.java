package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BottomHatBoxTest {
    @Test
    public void bottomHatBoxTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_input = clij2.pushMat(new double[][] {
                {0, 0, 0, 0, 0},
                {0, 50, 50, 50, 0},
                {0, 50, 100, 50, 0},
                {0, 50, 50, 50, 0},
                {0, 0, 0, 0, 0}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_input);
        clij2.bottomHatBox(gpu_input, gpu_output, 1, 1, 0);

        clij2.print(gpu_output);

        assertEquals(0, clij2.minimumOfAllPixels(gpu_output), 0);
        assertEquals(50, clij2.maximumOfAllPixels(gpu_output), 0);

        clij2.clear();
    }
}