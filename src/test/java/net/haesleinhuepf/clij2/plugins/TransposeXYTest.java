package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransposeXYTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer img = clij2.pushString("" +
                "0 0 0 0\n" +
                "1 0 0 0\n" +
                "2 3 0 0\n" +
                "0 0 0 0");

        ClearCLBuffer ref = clij2.pushString("" +
                "0 1 2 0\n" +
                "0 0 3 0\n" +
                "0 0 0 0\n" +
                "0 0 0 0");

        ClearCLBuffer out = clij2.create(ref);

        clij2.transposeXY(img, out);

        clij2.print(out);

        assertEquals(0, clij2.meanSquaredError(ref, out), 0.01);

        clij2.clear();
    }
}
