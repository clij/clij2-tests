package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.plugin.Duplicator;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij.test.TestUtilities;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ResliceRightTest {
    @Test
    public void resliceRight() throws InterruptedException {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testFlyBrain3D = IJ.openImage("src/test/resources/flybrain.tif");

        testFlyBrain3D.setRoi(0, 0, 256, 128);
        ImagePlus testImage = new Duplicator().run(testFlyBrain3D);
        testImage.show();

        // do operation with ImageJ
        new ImageJ();
        IJ.run(testImage, "Reslice [/]...", "output=1.0 start=Right avoid");
        Thread.sleep(500);
        ImagePlus reference = IJ.getImage();

        // do operation with OpenCL
        ClearCLImage inputCL = clij2.convert(testImage, ClearCLImage.class);
        ClearCLImage outputCL = clij2.create(new long[]{inputCL.getHeight(), inputCL.getDepth(), inputCL.getWidth()}, inputCL.getChannelDataType());

        clij2.resliceRight(inputCL, outputCL);

        ImagePlus result = clij2.pull(outputCL);

        //clij.show(reference, "ref");
        //clij.show(result, "res");
        //new WaitForUserDialog("wait").show();

        assertTrue(TestUtilities.compareImages(reference, result));

        IJ.exit();
        clij2.clear();
    }

    @Test
    public void resliceRight_Buffers() throws InterruptedException {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testFlyBrain3D = IJ.openImage("src/test/resources/flybrain.tif");

        testFlyBrain3D.setRoi(0, 0, 256, 128);
        ImagePlus testImage = new Duplicator().run(testFlyBrain3D);
        testImage.show();

        // do operation with ImageJ
        new ImageJ();
        IJ.run(testImage, "Reslice [/]...", "output=1.0 start=Right avoid");
        Thread.sleep(500);
        ImagePlus reference = IJ.getImage();

        // do operation with OpenCL
        ClearCLBuffer inputCL = clij2.convert(testImage, ClearCLBuffer.class);
        ClearCLBuffer outputCL = clij2.create(new long[]{inputCL.getHeight(), inputCL.getDepth(), inputCL.getWidth()}, inputCL.getNativeType());

        clij2.resliceRight(inputCL, outputCL);

        ImagePlus result = clij2.convert(outputCL, ImagePlus.class);

        //clij.show(reference, "ref");
        //clij.show(result, "res");
        //new WaitForUserDialog("wait").show();

        assertTrue(TestUtilities.compareImages(reference, result));

        IJ.exit();
        clij2.clear();
    }

}