package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.Duplicator;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij.test.TestUtilities;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AddImageAndScalarTest {
    @Test
    public void addScalar3d() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testImp1 = TestUtilities.getRandomImage(100, 100, 3, 8, 0, 15);

        // do operation with ImageJ
        ImagePlus added = new Duplicator().run(testImp1);
        IJ.run(added, "Add...", "value=1 stack");

        // do operation with ClearCL
        ClearCLImage src = clij2.convert(testImp1, ClearCLImage.class);
        ClearCLImage dst = clij2.convert(testImp1, ClearCLImage.class);

        clij2.addImageAndScalar(src, dst, 1f);
        ImagePlus addedFromCL = clij2.convert(dst, ImagePlus.class);

        assertTrue(TestUtilities.compareImages(added, addedFromCL));

        src.close();
        dst.close();
        testImp1.close();
        added.close();
        addedFromCL.close();
        clij2.clear();
    }


    @Test
    public void addScalar3d_Buffer() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testImp1 = TestUtilities.getRandomImage(100, 100, 3, 8, 0, 15);

        // do operation with ImageJ
        ImagePlus added = new Duplicator().run(testImp1);
        IJ.run(added, "Add...", "value=1 stack");

        // do operation with ClearCL
        ClearCLBuffer src = clij2.convert(testImp1, ClearCLBuffer.class);
        ClearCLBuffer dst = clij2.convert(testImp1, ClearCLBuffer.class);

        clij2.addImageAndScalar(src, dst, 1f);
        ImagePlus addedFromCL = clij2.convert(dst, ImagePlus.class);

        assertTrue(TestUtilities.compareImages(added, addedFromCL));

        src.close();
        dst.close();
        testImp1.close();
        added.close();
        addedFromCL.close();
        clij2.clear();
    }

    @Test
    public void addScalar2d() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testImp2D1 = TestUtilities.getRandomImage(100, 100, 1, 8, 0, 15);

        // do operation with ImageJ
        ImagePlus added = new Duplicator().run(testImp2D1);
        IJ.run(added, "Add...", "value=1");

        // do operation with ClearCL
        ClearCLImage src = clij2.convert(testImp2D1, ClearCLImage.class);
        ClearCLImage dst = clij2.convert(testImp2D1, ClearCLImage.class);

        clij2.addImageAndScalar(src, dst, 1f);
        ImagePlus addedFromCL = clij2.convert(dst, ImagePlus.class);

        assertTrue(TestUtilities.compareImages(added, addedFromCL));

        src.close();
        dst.close();
        testImp2D1.close();
        added.close();
        addedFromCL.close();
        clij2.clear();
    }

    @Test
    public void addScalar2d_Buffers() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testImp2D1 = TestUtilities.getRandomImage(100, 100, 1, 8, 0, 15);
        // do operation with ImageJ
        ImagePlus added = new Duplicator().run(testImp2D1);
        IJ.run(added, "Add...", "value=1");

        // do operation with ClearCL
        ClearCLBuffer src = clij2.convert(testImp2D1, ClearCLBuffer.class);
        ClearCLBuffer dst = clij2.convert(testImp2D1, ClearCLBuffer.class);

        clij2.addImageAndScalar(src, dst, 1f);
        ImagePlus addedFromCL = clij2.convert(dst, ImagePlus.class);

        assertTrue(TestUtilities.compareImages(added, addedFromCL));

        src.close();
        dst.close();
        testImp2D1.close();
        added.close();
        addedFromCL.close();
        clij2.clear();
    }

}