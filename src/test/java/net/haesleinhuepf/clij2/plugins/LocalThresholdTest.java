package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.NewImage;
import ij.process.ImageProcessor;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij.clearcl.util.ElapsedTime;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LocalThresholdTest {
    @Test
    public void localThreshold3D() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus testImp1 = NewImage.createImage("", 100, 100, 12, 16, NewImage.FILL_BLACK);
        for (int z = 0; z < 5; z++) {
            testImp1.setZ(z + 1);
            ImageProcessor ip1 = testImp1.getProcessor();
            ip1.set(5, 5, 1);
            ip1.set(6, 6, 1);
            ip1.set(7, 7, 1);
        }

        ClearCLImage input = clij2.convert(testImp1, ClearCLImage.class);
        ClearCLImage output1 = clij2.create(input);
        ClearCLImage output2 = clij2.create(input);
        ClearCLImage temp = clij2.create(input);
        ClearCLImage blurred = clij2.create(input);

        clij2.gaussianBlur(input, blurred, 2f, 2f, 2f);

        // usual way: blur, subtract, threshold
        ElapsedTime.measureForceOutput("traditional thresholding", () -> {
            clij2.addImagesWeighted(input, blurred, temp, 1f, -1f);
            clij2.threshold(temp, output1, 0);
        });

        // short cut: blur, local threshold
        ElapsedTime.measureForceOutput("local threshold", () -> {
            clij2.localThreshold(input, blurred, output2);
        });

        System.out.println("O1: " + clij2.sumOfAllPixels(output1));
        System.out.println("O2: " + clij2.sumOfAllPixels(output2));

        assertTrue(clij2.sumOfAllPixels(output1) > 0);
        assertTrue(clij2.sumOfAllPixels(output1) == clij2.sumOfAllPixels(output2));

        clij2.addImagesWeighted(output1, output2, temp, 1f, -1f);

        assertTrue(clij2.sumOfAllPixels(temp) == 0);

        input.close();
        output1.close();
        output2.close();
        temp.close();
        blurred.close();
        IJ.exit();
        clij2.clear();
    }
}