package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaximumIntensityMapTest {
    @Test
    public void maximumIntensityMapTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_intensity = clij2.pushMatXYZ(new double[][] {
                {1, 1, 2},
                {4, 0, 0},
                {5, 3, 0},
        });

        ClearCLBuffer gpu_labels = clij2.pushMatXYZ(new double[][] {
                {1, 1, 2},
                {1, 0, 0},
                {3, 3, 0},
        });

        ClearCLBuffer gpu_reference = clij2.pushMatXYZ(new double[][]{
                {4, 4, 2},
                {4, 0, 0},
                {5, 5, 0},
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.maximumIntensityMap(gpu_intensity, gpu_labels, gpu_output);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0);

        clij2.clear();
    }
}
