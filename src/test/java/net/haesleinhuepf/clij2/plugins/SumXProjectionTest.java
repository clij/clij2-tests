package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import net.haesleinhuepf.clijx.CLIJx;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SumXProjectionTest {
    @Test
    public void testCLIJ2VariableDims() {
        ImagePlus imp = IJ.openImage("src/test/resources/Haarlem_DZ_thumbnails_sb_text.gif");

        CLIJ2 clij2 = CLIJ2.getInstance();


        ClearCLBuffer input = clij2.push(imp);
        ClearCLBuffer output1 = clij2.create(new long[]{input.getDepth(), input.getHeight()}, clij2.Float);
        ClearCLBuffer output1a = clij2.create(new long[]{input.getDepth(), input.getHeight(), 1}, clij2.Float);
        clij2.sumXProjection(input, output1);

        double mean1 = clij2.meanOfAllPixels(output1);

        System.out.println("mean1 " + mean1);

        clij2.sumXProjection(input, output1a); // 3d -> 3d

        double mean1a = clij2.meanOfAllPixels(output1a);

        System.out.println("mean1a " + mean1a);

        assertEquals(mean1, mean1a, 0);
        assertNotEquals(0, mean1a, 0);

        clij2.clear();
    }
}
