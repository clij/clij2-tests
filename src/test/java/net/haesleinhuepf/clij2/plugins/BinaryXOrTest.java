package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryXOrTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer in1 = clij2.pushString("1 0 0 0\n1 1 1 1");
        ClearCLBuffer in2 = clij2.pushString("1 0 1 0\n1 0 0 1");
        ClearCLBuffer ref = clij2.pushString("0 0 1 0\n0 1 1 0");
        ClearCLBuffer out = clij2.create(ref);

        clij2.binaryXOr(in1, in2, out);

        assertEquals(0, clij2.meanSquaredError(ref, out), 0);

        clij2.clear();
    }
}
