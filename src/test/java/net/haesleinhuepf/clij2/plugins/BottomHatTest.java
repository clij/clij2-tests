package net.haesleinhuepf.clij2.plugins;

import ij.ImagePlus;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij.test.TestUtilities;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

public class BottomHatTest {
    @Test
    public void executeTestOnImages() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testImp2 = TestUtilities.getRandomImage(100, 100, 1, 8, 0, 1);

        ClearCLBuffer input = clij2.push(testImp2);
        ClearCLBuffer output = clij2.create(input);

        clij2.bottomHatBox(input, output, 1, 2, 3);

        clij2.bottomHatSphere(input, output, 1, 2, 3);

        clij2.clear();
    }
}
