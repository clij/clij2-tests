package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GradientYTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer in1 = clij2.pushString("" +
                "3 2 0 0\n" +
                "2 1 0 0");
        ClearCLBuffer ref = clij2.pushString(""+
                "-1 -1 0 0\n" +
                "-1 -1 0 0");
        ClearCLBuffer out = clij2.create(ref);

        clij2.gradientY(in1, out);

        clij2.print(out);

        assertEquals(0, clij2.meanSquaredError(ref, out), 0);

        clij2.clear();
    }
}