package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import java.nio.FloatBuffer;

import static org.junit.Assert.assertEquals;

public class ArgMaximumZProjectionTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ClearCLBuffer buffer = clij2.pushString("1 0\n\n2 1\n\n3 0\n\n0 0");

        ClearCLBuffer maxProjection = clij2.create(2,1,1);
        ClearCLBuffer argMaxProjection = clij2.create(2,1,1);

        System.out.println("Input");
        System.out.println(clij2.pullString(buffer));

        clij2.argMaximumZProjection(buffer, maxProjection, argMaxProjection);

        float[] arr = new float[2];

        // check max projection
        maxProjection.writeTo(FloatBuffer.wrap(arr), true);

        System.out.println("Max Proj");
        System.out.println(clij2.pullString(maxProjection));

        assertEquals(3, arr[0], 0);
        assertEquals(1, arr[1], 0);


        // check arg-max projection
        argMaxProjection.writeTo(FloatBuffer.wrap(arr), true);

        System.out.println("Arg-Max Proj");
        System.out.println(clij2.pullString(argMaxProjection));

        assertEquals(2, arr[0], 0);
        assertEquals(1, arr[1], 0);

        clij2.clear();
    }
}
