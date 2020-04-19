package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij.test.TestUtilities;
import net.haesleinhuepf.clij.utilities.AffineTransform;
import net.haesleinhuepf.clij2.CLIJ2;
import net.imglib2.realtransform.AffineTransform2D;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class Rotate2DTest {
    private final static float tolerance = 1f;
    private final static double angle = 45.0;


    @Test
    public void rotate2d() throws InterruptedException {
        CLIJ2 clij2 = CLIJ2.getInstance("1070");
        
        ImagePlus test2D = IJ.openImage("src/test/resources/blobs.tif");
        IJ.run(test2D, "Invert LUT", "");
        IJ.run(test2D, "32-bit", "");

        test2D.show();

        // do operation with ImageJ
        ImagePlus rotIJ = test2D.duplicate();
        IJ.run(rotIJ, "Rotate... ", "angle=" + angle + " interpolation=Bilinear");
        rotIJ.show();

        // do operation with OpenCL
        ClearCLImage src = clij2.convert(test2D, ClearCLImage.class);
        ClearCLImage dst = clij2.create(src);

        float angleRad = (float) (-angle / 180.0 * Math.PI);
        
        AffineTransform2D at = new AffineTransform2D();
        at.translate(-src.getWidth() / 2, -src.getHeight() / 2);
        at.rotate(angleRad);
        at.translate(src.getWidth() / 2, src.getHeight() / 2);
        
        clij2.affineTransform2D(src, dst, AffineTransform.matrixToFloatArray2D(at));
     
        ImagePlus rotCL = clij2.convert(dst, ImagePlus.class);

        ClearCLImage imageJResult = clij2.convert(rotIJ, ClearCLImage.class);
        long countNonZeroPixels = TestUtilities.countPixelsWithDifferenceAboveTolerance(clij2.getCLIJ(), imageJResult, dst, tolerance);

        // differences are an edge-artefact. Check that only a number of pixels is affected that's
        // smaller than all edge pixels
        System.out.println("pixels with huge differences: " + countNonZeroPixels);

        assertTrue( countNonZeroPixels < 2 * imageJResult.getWidth() + 2 * imageJResult.getHeight());

        imageJResult.close();

        clij2.show(rotCL, "rotCL");

        src.close();
        dst.close();

        IJ.exit();
        clij2.clear();
    }


    @Test
    public void rotate2d_Buffers() throws InterruptedException {

        CLIJ2 clij2 = CLIJ2.getInstance();
        
        ImagePlus test2D = IJ.openImage("src/test/resources/blobs.tif");
        IJ.run(test2D, "Invert LUT", "");
        IJ.run(test2D, "32-bit", "");

        test2D.show();

        // do operation with ImageJ
        ImagePlus rotIJ = test2D.duplicate();
        IJ.run(rotIJ, "Rotate... ", "angle=" + angle + " interpolation=None");
        rotIJ.show();

        // do operation with OpenCL
        ClearCLBuffer src = clij2.convert(test2D, ClearCLBuffer.class);
        ClearCLBuffer dst = clij2.create(src);

        float angleRad = (float) (-angle / 180.0 * Math.PI);

        AffineTransform2D at = new AffineTransform2D();

        at.translate(-src.getWidth() / 2, -src.getHeight() / 2);
        at.rotate(angleRad);
        at.translate(src.getWidth() / 2, src.getHeight() / 2);
        clij2.affineTransform2D(src, dst, AffineTransform.matrixToFloatArray2D(at));

        ImagePlus rotCL = clij2.convert(dst, ImagePlus.class);

        ClearCLBuffer imageJResult = clij2.convert(rotIJ, ClearCLBuffer.class);
        long countNonZeroPixels = TestUtilities.countPixelsWithDifferenceAboveTolerance(clij2.getCLIJ(), imageJResult, dst, tolerance);

        // differences are an edge-artefact. Check that only a number of pixels is affected that's
        // smaller than all edge pixels
        System.out.println("pixels with huge differences: " + countNonZeroPixels);

        assertTrue( countNonZeroPixels < 2 * imageJResult.getWidth() + 2 * imageJResult.getHeight());

        imageJResult.close();

        src.close();
        dst.close();

        IJ.exit();
        clij2.clear();
    }


}