package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.NewImage;
import ij.process.ImageProcessor;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij.test.TestUtilities;
import net.haesleinhuepf.clij2.CLIJ2;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SumOfAllPixelsTest {
    private static double relativeTolerance = 0.001;

    @Test
    public void sumPixelsSliceBySlice() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus mask3d = TestUtilities.getRandomImage(100, 100, 3, 32, 1, 100);
        ClearCLImage maskCL = clij2.convert(mask3d, ClearCLImage.class);

        double sum = clij2.sumOfAllPixels(maskCL);
        double[] sumSliceWise = clij2.sumImageSliceBySlice(maskCL);

        assertEquals(1.0, sum / new Sum().evaluate(sumSliceWise), relativeTolerance);

        maskCL.close();
        IJ.exit();
        clij2.clear();
    }

    @Test
    public void sumPixelsSliceBySlice_Buffers() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus mask3d = TestUtilities.getRandomImage(100, 100, 3, 32, 1, 100);
        ClearCLBuffer maskCL = clij2.convert(mask3d, ClearCLBuffer.class);

        double sum = clij2.sumOfAllPixels(maskCL);
        double[] sumSliceWise = clij2.sumImageSliceBySlice(maskCL);

        assertEquals(1.0, sum / new Sum().evaluate(sumSliceWise), relativeTolerance);

        maskCL.close();
        IJ.exit();
        clij2.clear();
    }

    @Test
    public void sumPixels3d() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus mask3d = NewImage.createByteImage("Test", 10, 10, 5, NewImage.FILL_BLACK);
        mask3d.setZ(2);
        ImageProcessor ip = mask3d.getProcessor();
        ip.set(3,3, 1);

        ClearCLImage maskCL = clij2.convert(mask3d, ClearCLImage.class);
        double sum = clij2.sumOfAllPixels(maskCL);
        assertEquals(1, sum, 0.0);

        ClearCLImage maskCL2 = clij2.create(maskCL);
        clij2.dilateBox(maskCL, maskCL2);
        double sum2 = clij2.sumOfAllPixels(maskCL2);
        assertEquals(27, sum2, 0.0);

        ClearCLImage maskCL3 = clij2.create(maskCL);
        clij2.dilateSphere(maskCL, maskCL3);
        double sum3 = clij2.sumOfAllPixels(maskCL3);
        assertEquals(7, sum3, 0.0);


        maskCL.close();
        maskCL2.close();
        maskCL3.close();
        IJ.exit();
        clij2.clear();
    }

    @Test
    public void sumPixels3d_Buffers() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus mask3d = NewImage.createByteImage("Test", 10, 10, 5, NewImage.FILL_BLACK);
        mask3d.setZ(2);
        ImageProcessor ip = mask3d.getProcessor();
        ip.set(3,3, 1);

        ClearCLBuffer maskCL = clij2.convert(mask3d, ClearCLBuffer.class);
        double sum = clij2.sumOfAllPixels(maskCL);
        assertEquals(1, sum, 0.0);

        ClearCLBuffer maskCL2 = clij2.create(maskCL);
        clij2.dilateBox(maskCL, maskCL2);
        double sum2 = clij2.sumOfAllPixels(maskCL2);
        assertEquals(27, sum2, 0.0);

        ClearCLBuffer maskCL3 = clij2.create(maskCL);
        clij2.dilateSphere(maskCL, maskCL3);
        double sum3 = clij2.sumOfAllPixels(maskCL3);
        assertEquals(7, sum3, 0.0);


        maskCL.close();
        maskCL2.close();
        maskCL3.close();
        IJ.exit();
        clij2.clear();
    }

    @Test
    public void sumPixels2d() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus imp = NewImage.createByteImage("test", 10, 10, 1, NewImage.FILL_BLACK);
        imp.getProcessor().set(5,5, 1);
        imp.getProcessor().set(5,6, 2);
        imp.getProcessor().set(5,7, 3);

        ClearCLImage maskCL = clij2.convert(imp, ClearCLImage.class);

        double sum = clij2.sumOfAllPixels(maskCL);

        assertTrue(sum == 6);

        maskCL.close();
        IJ.exit();
        clij2.clear();
    }

    @Test
    public void sumPixels2d_Buffers() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus imp = NewImage.createByteImage("test", 10, 10, 1, NewImage.FILL_BLACK);
        imp.getProcessor().set(5,5, 1);
        imp.getProcessor().set(5,6, 2);
        imp.getProcessor().set(5,7, 3);

        ClearCLBuffer maskCL = clij2.convert(imp, ClearCLBuffer.class);

        double sum = clij2.sumOfAllPixels(maskCL);

        assertTrue(sum == 6);

        maskCL.close();
        IJ.exit();
        clij2.clear();
    }

}