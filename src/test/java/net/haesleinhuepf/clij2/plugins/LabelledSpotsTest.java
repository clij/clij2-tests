package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LabelledSpotsTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer lab = clij2.pushString("" +
                "0 0 0 0\n" +
                "0 1 1 0\n\n" +
                "0 1 0 0\n" +
                "0 0 0 0");

        ClearCLBuffer ref = clij2.pushString("" +
                "0 0 0 0\n" +
                "0 1 2 0\n\n" +
                "0 3 0 0\n" +
                "0 0 0 0");

        ClearCLBuffer out = clij2.create(ref);

        clij2.labelSpots(lab, out);

        clij2.print(out);

        assertEquals(0, clij2.meanSquaredError(ref, out), 0.01);

        clij2.clear();
    }
}
