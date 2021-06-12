package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TouchMatrixToMeshTest {
    @Test
    public void touchMatrixToMeshTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_touch_matrix = clij2.pushMat(new double[][] {
                {0,0,0},
                {0,0,0},
                {0,1,0}
        });

        ClearCLBuffer gpu_point_list = clij2.pushMat(new double[][] {
                {1,4},
                {2,5}
        });

        ClearCLBuffer gpu_reference = clij2.pushMatXYZ(new double[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.touchMatrixToMesh(gpu_point_list, gpu_touch_matrix, gpu_output);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0);

        clij2.clear();
    }
}
