package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.NewImage;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MaximumOfAllPixelsTest {
    @Test
    public void maxPixels2d() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus imp = NewImage.createByteImage("test", 10, 10, 1, NewImage.FILL_BLACK);
        imp.getProcessor().set(5,5, 1);
        imp.getProcessor().set(5,6, 2);
        imp.getProcessor().set(5,7, 3);

        ClearCLImage maskCL = clij2.convert(imp, ClearCLImage.class);

        double max = clij2.maximumOfAllPixels(maskCL);

        assertTrue(max == 3);

        maskCL.close();
        IJ.exit();
        clij2.clear();
    }

    @Test
    public void maxPixels2d_Buffers() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus imp = NewImage.createByteImage("test", 10, 10, 1, NewImage.FILL_BLACK);
        imp.getProcessor().set(5,5, 1);
        imp.getProcessor().set(5,6, 2);
        imp.getProcessor().set(5,7, 3);

        ClearCLBuffer maskCL = clij2.convert(imp, ClearCLBuffer.class);

        double max = clij2.maximumOfAllPixels(maskCL);

        assertTrue(max == 3);

        maskCL.close();
        IJ.exit();
        clij2.clear();
    }

}