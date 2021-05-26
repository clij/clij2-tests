package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbsoluteDifferenceTest {
    @Test
    public void absoluteDifferenceTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_input1 = clij2.pushMat(new double[][] {
                {1, 5, 3}
        });

        ClearCLBuffer gpu_input2 = clij2.pushMat(new double[][] {
                {4, 2, 7}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][]{
                {3, 3, 4}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.absoluteDifference(gpu_input2, gpu_input1, gpu_output);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0);

        clij2.clear();
    }
}
