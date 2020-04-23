package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import net.haesleinhuepf.clij.clearcl.ClearCL;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij.test.TestUtilities;
import net.haesleinhuepf.clij2.CLIJ2;
import net.haesleinhuepf.clijx.CLIJx;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SetRandomTest {

    @Test
    public void set2d() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer buffer = clij2.create(10, 10);
        clij2.setRandom(buffer, 0, 1, 5);

        IJ.exit();
        clij2.clear();
    }


    @Test
    public void set3d() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer buffer = clij2.create(10, 10, 10);
        clij2.setRandom(buffer, 0, 1, 5);

        IJ.exit();
        clij2.clear();
    }


}