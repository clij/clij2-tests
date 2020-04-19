package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConvertTest {
    @Test
    public void testFloat() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer in1 = clij2.pushString("1 2 3\n4 5 6");

        ClearCLBuffer out1 = clij2.create(in1.getDimensions(), clij2.UnsignedByte);
        ClearCLBuffer out2 = clij2.create(in1.getDimensions(), clij2.UnsignedShort);
        ClearCLBuffer out3 = clij2.create(in1.getDimensions(), clij2.Float);
        ClearCLBuffer out4 = clij2.create(in1.getDimensions(), clij2.Float);

        clij2.copy(in1, out1);
        clij2.copy(in1, out2);
        clij2.copy(out1, out3);
        clij2.copy(out2, out4);

        assertEquals(0, clij2.meanSquaredError(in1, out1), 0);
        assertEquals(0, clij2.meanSquaredError(in1, out2), 0);
        assertEquals(0, clij2.meanSquaredError(in1, out3), 0);
        assertEquals(0, clij2.meanSquaredError(in1, out4), 0);

        clij2.clear();
    }
}
