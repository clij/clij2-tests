package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetAutomaticThresholdTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer img = clij2.pushString("" +
                "0 0 0 0\n" +
                "0 1 0 3\n" +
                "0 1 0 3\n" +
                "0 0 0 0");

        double threshold = clij2.getAutomaticThreshold(img, "Otsu");

        System.out.println(threshold);

        assertEquals(1, threshold, 0);

        clij2.clear();
    }
}
