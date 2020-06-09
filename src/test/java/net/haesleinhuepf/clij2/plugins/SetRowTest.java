package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SetRowTest {
    @Test
    public void test3D() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer image = clij2.pushString("" +
                "1 1 0 0\n" +
                "1 1 2 2\n\n" +
                "1 1 0 0\n" +
                "1 1 2 2");

        ClearCLBuffer ref = clij2.pushString("" +
                "1 1 0 0\n" +
                "1 1 2 2\n\n" +
                "4 4 4 4\n" +
                "1 1 2 2");
        clij2.setRow(image, 2, 4);
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
                "1 1 0 0\n" +
                "4 4 4 4");
        clij2.setRow(image, 1, 4);
        clij2.print(image);

        String a = clij2.pullString(image);
        String b = clij2.pullString(ref);

        assertTrue(a.compareTo(b) == 0);
    }

}
