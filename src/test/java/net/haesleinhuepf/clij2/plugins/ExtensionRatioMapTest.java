package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExtensionRatioMapTest {
    @Test
    public void extensionRatioMapTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_labels = clij2.pushMatXYZ(new double[][] {
                {1, 1, 2},
                {1, 0, 2},
                {3, 3, 0},
        });

        ClearCLBuffer gpu_reference = clij2.pushMatXYZ(new double[][]{
                {1.1396203, 1.1396203, 1},
                {1.1396203, 0, 1},
                {1, 1, 0},
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.extensionRatioMap(gpu_labels, gpu_output);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0.01);

        clij2.clear();
    }
}
