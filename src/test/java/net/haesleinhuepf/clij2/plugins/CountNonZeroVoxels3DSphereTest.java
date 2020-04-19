package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountNonZeroVoxels3DSphereTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer in1 = clij2.pushString("" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n\n" +

                "0 0 0 0 0 0 0 0 0\n" +
                "0 1 0 0 0 0 0 0 0\n" +
                "0 1 1 1 1 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n\n" +

                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0"
        );


        ClearCLBuffer ref = clij2.pushString("" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 1 0 0 0 0 0 0 0\n" +
                "0 1 1 1 1 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n\n" +

                "0 1 0 0 0 0 0 0 0\n" +
                "1 2 2 1 1 0 0 0 0\n" +
                "1 3 3 3 2 1 0 0 0\n" +
                "0 1 1 1 1 0 0 0 0\n\n" +

                "0 0 0 0 0 0 0 0 0\n" +
                "0 1 0 0 0 0 0 0 0\n" +
                "0 1 1 1 1 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0"
        );
        ClearCLBuffer out = clij2.create(in1);

        clij2.countNonZeroVoxels3DSphere(in1, out, 1, 1, 1);

        clij2.print(out);

        assertEquals(0, clij2.meanSquaredError(ref, out), 0);

        clij2.clear();
    }
}
