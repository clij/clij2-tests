package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

public class AffineTransform3DTest {
    @Test
    public void executeTest3D() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer image = clij2.pushString("" +
                "1 1 0 0\n" +
                "1 1 2 2\n\n" +
                "1 1 0 0\n" +
                "1 1 2 2");

        ClearCLBuffer result = clij2.create(image);
        clij2.affineTransform3D(image, result, "scale=2 translatex=10 rotatex=45");
        clij2.print(image);
    }
}
