package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GenerateTouchCountMatrixTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer image1 = clij2.pushString("" +
                "1 1 0 3\n" +
                "1 1 2 2\n" +
                "1 1 2 2\n\n" +
                "1 1 0 3\n" +
                "1 1 2 2\n" +
                "1 1 2 2");

        ClearCLBuffer ref = clij2.pushString("" +
                "0 0 0 0\n" +
                "2 0 0 0\n" +
                "2 4 0 0\n" +
                "2 0 2 0");

        ClearCLBuffer touch_count_matrix = clij2.create(4, 4);

        GenerateTouchCountMatrix.generateTouchCountMatrix(clij2, image1, touch_count_matrix);

        String a = clij2.pullString(touch_count_matrix);
        String b = clij2.pullString(ref);

        assertTrue(a.compareTo(b) == 0);


    }
}
