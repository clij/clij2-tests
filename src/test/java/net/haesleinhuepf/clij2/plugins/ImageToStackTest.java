package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImageToStackTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer in1 = clij2.pushString("" +
                "1 -2 0 0\n" +
                "-3 -12 0 0");
        ClearCLBuffer ref = clij2.pushString(""+
                "1 -2 0 0\n" +
                "-3 -12 0 0\n\n" +
                "1 -2 0 0\n" +
                "-3 -12 0 0");
        ClearCLBuffer out = clij2.create(ref);

        clij2.imageToStack(in1, out, 2);

        clij2.print(out);

        assertEquals(0, clij2.meanSquaredError(ref, out), 0);

        clij2.clear();
    }
}
