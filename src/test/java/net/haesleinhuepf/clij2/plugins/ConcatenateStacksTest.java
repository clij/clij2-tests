package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConcatenateStacksTest {
    @Test
    public void combineVerticallyTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer test1 = clij2.pushMat(new double[][][] {
                {{1,1}},
                {{1,1}}
        });
        ClearCLBuffer test2 = clij2.pushMat(new double[][][] {
                {{2,2}},
                {{2,2}},
                {{2,2}}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][][] {
                {{1,1}},
                {{1,1}},
                {{2,2}},
                {{2,2}},
                {{2,2}}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.concatenateStacks(test1, test2, gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0.01);

        clij2.clear();
    }
}
