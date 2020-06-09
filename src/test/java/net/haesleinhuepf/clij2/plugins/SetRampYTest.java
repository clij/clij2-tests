package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SetRampYTest {
    @Test
    public void test3D() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer image = clij2.pushString("" +
                "1 1 0 0\n" +
                "1 1 2 2\n\n" +
                "1 1 0 0\n" +
                "1 1 2 2");

        ClearCLBuffer ref = clij2.pushString("" +
                "0 0 0 0\n" +
                "1 1 1 1\n\n" +
                "0 0 0 0\n" +
                "1 1 1 1");
        clij2.setRampY(image);
        clij2.print(image);

        String a = clij2.pullString(image);
        String b = clij2.pullString(ref);

        assertTrue(a.compareTo(b) == 0);
    }

    @Test
    public void test2D() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer image = clij2.pushString("" +
                "1 1 0 0\n" +
                "1 1 2 2");

        ClearCLBuffer ref = clij2.pushString("" +
                "0 0 0 0\n" +
                "1 1 1 1");
        clij2.setRampY(image);
        clij2.print(image);

        String a = clij2.pullString(image);
        String b = clij2.pullString(ref);

        assertTrue(a.compareTo(b) == 0);
    }

}
