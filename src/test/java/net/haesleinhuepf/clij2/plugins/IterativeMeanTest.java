package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

public class IterativeMeanTest {
    @Test
    public void testIterativeMean() {
        ImagePlus imp = IJ.openImage("src/test/resources/blobs.tif");

        CLIJ2 clij2 = CLIJ2.getInstance();



        for (float sigma = 0; sigma < 10; sigma++) {
            ClearCLBuffer input = clij2.push(imp);

            ClearCLBuffer gaussianBlurred = clij2.create(input);

            ClearCLBuffer meanBlurred = clij2.create(input);

            long time = System.currentTimeMillis();
            clij2.gaussianBlur2D(input, gaussianBlurred, sigma, sigma);
            System.out.println("gauss duration: " + (System.currentTimeMillis() - time));


            double min_mse = Double.MAX_VALUE;
            int min_mse_i = -1;
            time = System.currentTimeMillis();
            long duration = 0;
            for (int i = 0; i < 40; i++) {
                clij2.mean2DBox(input, meanBlurred, 1, 1);

                clij2.copy(meanBlurred, input);

                double mse = clij2.meanSquaredError(gaussianBlurred, meanBlurred);

                if (mse < min_mse) {
                    min_mse = mse;
                    min_mse_i = i;
                    duration = System.currentTimeMillis() - time;
                }
            }


            System.out.println("Sigma " + sigma + " i " + min_mse_i + " mse " + min_mse + " dur " + duration);

        }



    }
}
