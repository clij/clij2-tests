package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConnectedComponentsLabelingBoxTest {
    @Test
    public void connectedComponentsLabelingBoxTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer test1 = clij2.pushMat(new double[][][] {
                {{1,0,1}},
                {{1,0,0}},
                {{0,0,1}}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][][] {
                {{1,0,2}},
                {{1,0,0}},
                {{0,0,3}}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.connectedComponentsLabelingBox(test1, gpu_output);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0.01);

        clij2.clear();
    }

    @Test
    public void connectedComponentsLabelingBoxTest2() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer test1 = clij2.pushMat(new double[][][] {
                {{1,0,1}},
                {{1,1,0}},
                {{0,0,1}}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][][] {
                {{1,0,1}},
                {{1,1,0}},
                {{0,0,1}}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        clij2.connectedComponentsLabelingBox(test1, gpu_output);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0.01);

        clij2.clear();
    }

}
