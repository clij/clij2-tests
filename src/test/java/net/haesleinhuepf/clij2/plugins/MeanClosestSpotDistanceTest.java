package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.plugins.MeanClosestSpotDistance;
import net.haesleinhuepf.clijx.CLIJx;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.numeric.integer.ByteType;
import org.junit.Test;

import static org.junit.Assert.*;

public class MeanClosestSpotDistanceTest {
    double tolerance = 0.01;

    @Test
    public void testSpotdistances(){
        Img<ByteType> binary1 = ArrayImgs.bytes(new byte[]{
                0, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 1, 0,
                0, 0, 0, 0, 0
        }, new long[]{5,5});
        Img<ByteType> binary2 = ArrayImgs.bytes(new byte[]{
                0, 0, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 0, 0
        }, new long[]{5,5});

        CLIJx clijx = CLIJx.getInstance();

        ClearCLBuffer clBinary1 = clijx.push(binary1);
        ClearCLBuffer clBinary2 = clijx.push(binary2);

        double meanShortestDistance = MeanClosestSpotDistance.meanClosestSpotDistance(clijx, clBinary1, clBinary2);
        assertEquals(1, meanShortestDistance, tolerance);

        clBinary1.close();
        clBinary2.close();
    }
}