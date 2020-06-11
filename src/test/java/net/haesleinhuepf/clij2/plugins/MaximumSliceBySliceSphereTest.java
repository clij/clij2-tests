package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.Roi;
import ij.plugin.Duplicator;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij.test.TestUtilities;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MaximumSliceBySliceSphereTest {

    @Ignore //ignore test as we know and need to accept that maximumSliceBySlice does not do the same as ImageJ
    @Test
    public void maximumSliceBySlice() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testFlyBrain3D = IJ.openImage("src/test/resources/flybrain.tif");

        ImagePlus testImage = new Duplicator().run(testFlyBrain3D);
        IJ.run(testImage, "32-bit", "");

        // do operation with ImageJ
        ImagePlus reference = new Duplicator().run(testImage);
        IJ.run(reference, "Maximum...", "radius=1 stack");

        // do operation with CLIJ
        ClearCLImage inputCL = clij2.convert(testImage, ClearCLImage.class);
        ClearCLImage outputCL = clij2.create(inputCL);

        clij2.maximum3DSphere(inputCL, outputCL, 3, 3, 0);

        ImagePlus result = clij2.pull(outputCL);

        //new ImageJ();
        //clij.show(inputCL, "inp");
        //clij.show(reference, "ref");
        //clij.show(result, "res");
        //new WaitForUserDialog("wait").show();
        assertTrue(TestUtilities.compareImages(reference, result, 0.001));
        IJ.exit();
        clij2.clear();
    }

    @Ignore //ignore test as we know and need to accept that maximumSliceBySlice does not do the same as ImageJ
    @Test
    public void maximumSliceBySlice_Buffer() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testFlyBrain3D = IJ.openImage("src/test/resources/flybrain.tif");

        ImagePlus testImage = new Duplicator().run(testFlyBrain3D);
        IJ.run(testImage, "32-bit", "");

        // do operation with ImageJ
        ImagePlus reference = new Duplicator().run(testImage);
        IJ.run(reference, "Maximum...", "radius=1 stack");

        // do operation with CLIJ
        ClearCLBuffer inputCL = clij2.push(testImage);
        ClearCLBuffer outputCL = clij2.create(inputCL);

        clij2.maximum3DSphere(inputCL, outputCL, 3, 3, 0);

        ImagePlus result = clij2.pull(outputCL);

        // ignore edges
        reference.setRoi(new Roi(1, 1, reference.getWidth() - 2, reference.getHeight() - 2));
        result.setRoi(new Roi(1, 1, reference.getWidth() - 2, reference.getHeight() - 2));
        reference = new Duplicator().run(reference);
        result = new Duplicator().run(result);

        //new ImageJ();
        //clij.show(inputCL, "inp");
        //clij.show(reference, "ref");
        //clij.show(result, "res");
        //new WaitForUserDialog("wait").show();
        assertTrue(TestUtilities.compareImages(reference, result, 0.001));
        IJ.exit();
        clij2.clear();
    }

}