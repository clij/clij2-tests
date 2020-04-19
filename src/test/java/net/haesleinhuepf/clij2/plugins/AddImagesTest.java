package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.ImageCalculator;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij.test.TestUtilities;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AddImagesTest {
    @Test
    public void addPixelwise3d() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testImp1 = TestUtilities.getRandomImage(100, 100, 3, 32, 0, 100);
        ImagePlus testImp2 = TestUtilities.getRandomImage(100, 100, 3, 32, 0, 100);

        // do operation with ImageJ
        ImageCalculator ic = new ImageCalculator();
        ImagePlus sumImp = ic.run("Add create stack", testImp1, testImp2);

        // do operation with ClearCL
        ClearCLImage src = clij2.convert(testImp1, ClearCLImage.class);
        //convert(testImp1, ClearCLImage.class);
        ClearCLImage src1 = clij2.convert(testImp2, ClearCLImage.class);
        //convert(testImp2, ClearCLImage.class);
        ClearCLImage dst = clij2.create(src);
        //convert(testImp1, ClearCLImage.class);

        clij2.addImages(src, src1, dst);
        ImagePlus sumImpFromCL = clij2.convert(dst, ImagePlus.class);
        //clij.convert(dst, ImagePlus.class);

        assertTrue(TestUtilities.compareImages(sumImp, sumImpFromCL));

        src.close();
        src1.close();
        dst.close();
        IJ.exit();
        clij2.clear();
    }


    @Test
    public void addPixelwise3d_Buffers() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testImp1 = TestUtilities.getRandomImage(100, 100, 3, 32, 0, 100);
        ImagePlus testImp2 = TestUtilities.getRandomImage(100, 100, 3, 32, 0, 100);

        // do operation with ImageJ
        ImageCalculator ic = new ImageCalculator();
        ImagePlus sumImp = ic.run("Add create stack", testImp1, testImp2);

        // do operation with ClearCL
        ClearCLBuffer src = clij2.convert(testImp1, ClearCLBuffer.class);
        //convert(testImp1, ClearCLBuffer.class);
        ClearCLBuffer src1 = clij2.convert(testImp2, ClearCLBuffer.class);
        //convert(testImp2, ClearCLBuffer.class);
        ClearCLBuffer dst = clij2.create(src);
        //convert(testImp1, ClearCLBuffer.class);

        clij2.addImages(src, src1, dst);
        ImagePlus sumImpFromCL = clij2.pull(dst);
        //clij.convert(dst, ImagePlus.class);

        assertTrue(TestUtilities.compareImages(sumImp, sumImpFromCL));

        src.close();
        src1.close();
        dst.close();

        IJ.exit();
        clij2.clear();
    }

    @Test
    public void addPixelwise2d() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testImp2D1 = TestUtilities.getRandomImage(100, 100, 1, 32, 0, 100);
        ImagePlus testImp2D2 = TestUtilities.getRandomImage(100, 100, 1, 32, 0, 100);

        // do operation with ImageJ
        ImageCalculator ic = new ImageCalculator();
        ImagePlus sumImp = ic.run("Add create", testImp2D1, testImp2D2);

        // do operation with ClearCL
        ClearCLImage src = clij2.convert(testImp2D1, ClearCLImage.class);
        //convert(testImp2D1, ClearCLImage.class);
        ClearCLImage src1 = clij2.convert(testImp2D2, ClearCLImage.class);
        //convert(testImp2D2, ClearCLImage.class);
        ClearCLImage dst = clij2.create(src);
        //convert(testImp2D1, ClearCLImage.class);

        clij2.addImages(src, src1, dst);
        ImagePlus sumImpFromCL = clij2.pull(dst);
        //clij.convert(dst, ImagePlus.class);

        assertTrue(TestUtilities.compareImages(sumImp, sumImpFromCL));

        src.close();
        src1.close();
        dst.close();

        IJ.exit();
        clij2.clear();
    }

    @Test
    public void addPixelwise2d_Buffer() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testImp2D1 = TestUtilities.getRandomImage(100, 100, 1, 32, 0, 100);
        ImagePlus testImp2D2 = TestUtilities.getRandomImage(100, 100, 1, 32, 0, 100);

        // do operation with ImageJ
        ImageCalculator ic = new ImageCalculator();
        ImagePlus sumImp = ic.run("Add create", testImp2D1, testImp2D2);

        // do operation with ClearCL
        ClearCLBuffer src = clij2.convert(testImp2D1, ClearCLBuffer.class);
        //convert(testImp2D1, ClearCLBuffer.class);
        ClearCLBuffer src1 = clij2.convert(testImp2D2, ClearCLBuffer.class);
        //convert(testImp2D2, ClearCLBuffer.class);
        ClearCLBuffer dst = clij2.create(src);
        //convert(testImp2D1, ClearCLBuffer.class);

        clij2.addImages(src, src1, dst);
        ImagePlus sumImpFromCL = clij2.convert(dst, ImagePlus.class);
        //clij.convert(dst, ImagePlus.class);

        assertTrue(TestUtilities.compareImages(sumImp, sumImpFromCL));

        src.close();
        src1.close();
        dst.close();

        IJ.exit();
        clij2.close();
    }

}