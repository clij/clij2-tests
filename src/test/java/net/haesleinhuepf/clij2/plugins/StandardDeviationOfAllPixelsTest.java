package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import net.haesleinhuepf.clij2.plugins.StandardDeviationOfAllPixels;
import net.haesleinhuepf.clijx.CLIJx;
import org.junit.Test;

import static org.junit.Assert.*;

public class StandardDeviationOfAllPixelsTest {
    final double tolerance = 0.01;

    @Test
    public void testStdDev() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ImagePlus imp = IJ.openImage("src/test/resources/blobs.tif");

        ClearCLBuffer input = clij2.push(imp);

        double stdDev = clij2.standardDeviationOfAllPixels(input);

        clij2.release(input);

        double stdDevRef = imp.getStatistics().stdDev;

        System.out.println("stdDev: " + stdDev);
        System.out.println("stdDevRef: " + stdDevRef);

        assertEquals(stdDev, stdDevRef, tolerance);
    }
}