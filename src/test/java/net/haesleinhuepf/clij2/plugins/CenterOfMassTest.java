package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CenterOfMassTest {
    @Test
    public void centerOfMassTest() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_labels = clij2.pushMat(new double[][] {
                {0,0,0,0,0},
                {1,1,1,1,1},
                {0,0,0,0,0}
        });

        double[] cm = clij2.centerOfMass(gpu_labels);
        assertEquals(2, cm[0], 0);
        assertEquals(1, cm[1], 0);
        assertEquals(2, cm.length, 0);

        clij2.clear();
    }
    @Test
    public void centerOfMassTest3D() {

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer gpu_labels = clij2.pushMat(new double[][][] {
                {
                    {0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0}
            }, {
                    {0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0}
                }
        });

        double[] cm = clij2.centerOfMass(gpu_labels);
        assertEquals(2, cm[0], 0);
        assertEquals(1, cm[1], 0);
        assertEquals(0.5, cm[2], 0);
        assertEquals(3, cm.length, 0);

        clij2.clear();
    }
}
