package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetJaccardIndexTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer in1 = clij2.pushString("" +
                "0 0 0 0\n" +
                "0 1 1 0\n" +
                "0 1 1 0\n" +
                "0 0 0 0");

        ClearCLBuffer in2 = clij2.pushString("" +
                "0 0 0 0\n" +
                "0 0 1 1\n" +
                "0 0 1 1\n" +
                "0 0 0 0");

        double jaccardIndex = clij2.getJaccardIndex(in1, in2);

        System.out.println(jaccardIndex);

        assertEquals(0.33, jaccardIndex, 0.01);

        clij2.clear();
    }
}
