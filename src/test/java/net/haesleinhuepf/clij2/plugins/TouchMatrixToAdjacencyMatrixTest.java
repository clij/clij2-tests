package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.NewImage;
import ij.process.ImageProcessor;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TouchMatrixToAdjacencyMatrixTest {
    @Test
    public void touchMatrixToAdjacencyMatrixTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_input = clij2.pushMat(new double[][] {
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0}
        });

        ClearCLBuffer gpu_reference = clij2.pushMat(new double[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 1}
        });

        ClearCLBuffer gpu_output = clij2.create(gpu_reference);
        TouchMatrixToAdjacencyMatrix.touchMatrixToAdjacencyMatrix(clij2, gpu_input, gpu_output, true);

        clij2.print(gpu_output);

        assertEquals(0, clij2.meanSquaredError(gpu_output, gpu_reference), 0);

        clij2.clear();
    }
}