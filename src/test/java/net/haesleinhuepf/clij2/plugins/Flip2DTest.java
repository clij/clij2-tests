package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij.test.TestUtilities;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Flip2DTest {

    @Test
    public void flip2d() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testImp2D1 = TestUtilities.getRandomImage(100, 100, 1, 32, 1, 100);

        ClearCLImage testCL = clij2.convert(testImp2D1, ClearCLImage.class);
        ClearCLImage flip = clij2.convert(testImp2D1, ClearCLImage.class);
        ClearCLImage flop = clij2.convert(testImp2D1, ClearCLImage.class);

        clij2.flip(testCL, flip, true, false);

        ImagePlus testFlipped = clij2.convert(flip, ImagePlus.class);

        clij2.flip(flip, flop, true, false);
        ImagePlus testFlippedTwice = clij2.convert(flop, ImagePlus.class);

        assertTrue(TestUtilities.compareImages(testImp2D1, testFlippedTwice));
        assertFalse(TestUtilities.compareImages(testImp2D1, testFlipped));

        testCL.close();
        flip.close();
        flop.close();
        IJ.exit();

        clij2.clear();
    }


    @Test
    public void flipBuffer2d() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testImp2D1 = TestUtilities.getRandomImage(100, 100, 1, 32, 1, 100);

        ClearCLBuffer testCL = clij2.convert(testImp2D1, ClearCLBuffer.class);
        ClearCLBuffer flip = clij2.convert(testImp2D1, ClearCLBuffer.class);
        ClearCLBuffer flop = clij2.convert(testImp2D1, ClearCLBuffer.class);

        clij2.flip(testCL, flip, true, false);

        ImagePlus testFlipped = clij2.convert(flip, ImagePlus.class);

        clij2.flip(flip, flop, true, false);
        ImagePlus testFlippedTwice = clij2.convert(flop, ImagePlus.class);

        assertTrue(TestUtilities.compareImages(testImp2D1, testFlippedTwice));
        assertFalse(TestUtilities.compareImages(testImp2D1, testFlipped));

        testCL.close();
        flip.close();
        flop.close();
        IJ.exit();
        clij2.clear();
    }

}