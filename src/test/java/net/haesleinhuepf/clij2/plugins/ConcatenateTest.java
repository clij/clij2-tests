package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConcatenateTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        String stack1 = "0 1 2 3\n" +
                        "3 4 5 6\n\n" +
                        "0 1 2 3\n" +
                        "3 2 5 6";

        String stack2 = "0 1 2 3\n" +
                "3 4 5 6\n\n" +
                "0 1 2 3\n" +
                "3 2 0 0";

        ClearCLBuffer in1 = clij2.pushString(stack1);
        ClearCLBuffer in2 = clij2.pushString(stack2);

        ClearCLBuffer ref = clij2.pushString(stack1 + "\n\n" + stack2);

        ClearCLBuffer out = clij2.create(ref);

        clij2.concatenateStacks(in1, in2, out);

        clij2.print(out);

        assertEquals(0, clij2.meanSquaredError(ref, out), 0);

        clij2.clear();
    }
}
