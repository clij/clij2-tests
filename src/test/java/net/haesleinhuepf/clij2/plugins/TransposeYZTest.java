package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransposeYZTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer img = clij2.pushString("" +
                "0\n0\n0\n0\n\n" +
                "1\n0\n0\n0\n\n" +
                "2\n3\n0\n0\n\n" +
                "0\n0\n0\n0");

        ClearCLBuffer ref = clij2.pushString("" +
                "0\n1\n2\n0\n\n" +
                "0\n0\n3\n0\n\n" +
                "0\n0\n0\n0\n\n" +
                "0\n0\n0\n0");

        ClearCLBuffer out = clij2.create(ref);

        clij2.transposeYZ(img, out);

        clij2.print(out);

        assertEquals(0, clij2.meanSquaredError(ref, out), 0.01);

        clij2.clear();
    }
}
