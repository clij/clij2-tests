package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MeanOfTouchingNeighborsMapTest {
    @Test
    public void meanOfTouchingNeighborsMapTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_intensities = clij2.pushMat(new double[][] {
                {1,1,2,2},
                {1,0,0,2},
                {4,0,0,6},
                {4,4,6,6}
        });

        ClearCLBuffer gpu_labels = clij2.pushMat(new double[][] {
                {1,1,2,2},
                {1,0,0,2},
                {3,0,0,4},
                {3,3,4,4}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][]{
                {2.3333335,2.3333335,3,3},
                {2.3333335,0,0,3},
                {3.6666667,0,0,4},
                {3.6666667,3.6666667,4,4}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.meanOfTouchingNeighborsMap(gpu_intensities, gpu_labels, gpu_output, 1, true);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0.01);

        clij2.clear();
    }
}
