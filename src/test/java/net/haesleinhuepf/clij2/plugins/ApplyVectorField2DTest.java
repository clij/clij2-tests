package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

public class ApplyVectorField2DTest {
    @Test
    public void executeTest2D() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer image = clij2.pushString("" +
                "1 1 0 0\n" +
                "1 1 2 2\n" +
                "1 1 2 2");

        ClearCLBuffer result = clij2.create(image);
        clij2.applyVectorField2D(image, image, image, result);
        clij2.print(image);
    }
}
