package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CloseIndexGapsInLabelMapTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer in1 = clij2.pushString("1 2 0 0\n1 5 5 8");
        ClearCLBuffer ref = clij2.pushString("1 2 0 0\n1 3 3 4");
        ClearCLBuffer out = clij2.create(ref);

        clij2.closeIndexGapsInLabelMap(in1, out);

        assertEquals(0, clij2.meanSquaredError(ref, out), 0);

        clij2.clear();
    }
}
