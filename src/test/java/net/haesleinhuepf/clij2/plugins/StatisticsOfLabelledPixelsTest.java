package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.CLIJ;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import net.haesleinhuepf.clij2.plugins.StatisticsOfLabelledPixels;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticsOfLabelledPixelsTest {


    @Test
    public void testStats() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        Img<UnsignedShortType> img = ArrayImgs.unsignedShorts(new short[]{
                1, 1, 2,
                1, 2, 2,
                2, 2, 5,
                2, 5, 5
        }, new long[]{3, 2, 2});

        Img<UnsignedShortType> lab = ArrayImgs.unsignedShorts(new short[]{
                1, 1, 2,
                1, 2, 2,
                1, 1, 2,
                1, 2, 2
        }, new long[]{3, 2, 2});


        ClearCLBuffer input = clij2.push(img);
        ClearCLBuffer labels = clij2.push(lab);

        int num_labels = (int) clij2.maximumOfAllPixels(labels);

        double[][]stats = StatisticsOfLabelledPixels.statisticsOfLabelledPixels_single_threaded(clij2, input, labels, 1, num_labels);
        System.out.println("Objects found: " + stats.length);

        assertEquals(6, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.PIXEL_COUNT.value], 0);
        assertEquals(9, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.SUM_INTENSITY.value], 0);
        assertEquals(1.5, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.MEAN_INTENSITY.value], 0);
        assertEquals(0.5, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.STANDARD_DEVIATION_INTENSITY.value], 0);

        assertEquals(6, stats[1][StatisticsOfLabelledPixels.STATISTICS_ENTRY.PIXEL_COUNT.value], 0);
        assertEquals(21, stats[1][StatisticsOfLabelledPixels.STATISTICS_ENTRY.SUM_INTENSITY.value], 0);
        assertEquals(3.5, stats[1][StatisticsOfLabelledPixels.STATISTICS_ENTRY.MEAN_INTENSITY.value], 0);
        assertEquals(1.5, stats[1][StatisticsOfLabelledPixels.STATISTICS_ENTRY.STANDARD_DEVIATION_INTENSITY.value], 0);


        stats = StatisticsOfLabelledPixels.statisticsOfLabelledPixels(clij2, input, labels);
        System.out.println("Objects found: " + stats.length);

        assertEquals(6, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.PIXEL_COUNT.value], 0);
        assertEquals(9, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.SUM_INTENSITY.value], 0);
        assertEquals(1.5, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.MEAN_INTENSITY.value], 0);
        assertEquals(0.5, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.STANDARD_DEVIATION_INTENSITY.value], 0);

        assertEquals(6, stats[1][StatisticsOfLabelledPixels.STATISTICS_ENTRY.PIXEL_COUNT.value], 0);
        assertEquals(21, stats[1][StatisticsOfLabelledPixels.STATISTICS_ENTRY.SUM_INTENSITY.value], 0);
        assertEquals(3.5, stats[1][StatisticsOfLabelledPixels.STATISTICS_ENTRY.MEAN_INTENSITY.value], 0);
        assertEquals(1.5, stats[1][StatisticsOfLabelledPixels.STATISTICS_ENTRY.STANDARD_DEVIATION_INTENSITY.value], 0);

        clij2.clear();
    }

    @Test
    public void testAspectRatio() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer labelmap = clij2.pushString("" +
                "1 1 1 1 1 2 2 2\n"+
                "1 1 1 1 1 2 2 2\n"+
                "1 1 1 1 1 2 2 2\n\n"+
                "1 1 1 1 1 2 2 2\n"+
                "1 1 1 1 1 2 2 2\n"+
                "1 1 1 1 1 2 2 2\n\n"+
                "1 1 1 1 1 2 2 2\n"+
                "1 1 1 1 1 2 2 2\n"+
                "1 1 1 1 1 2 2 2"
        );

        double[][] stats = clij2.statisticsOfLabelledPixels(labelmap, labelmap);

        assertEquals(2.449489742783178, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.MAX_DISTANCE_TO_MASS_CENTER.value], 0.0001);
        assertEquals(1.7320508075688772, stats[1][StatisticsOfLabelledPixels.STATISTICS_ENTRY.MAX_DISTANCE_TO_MASS_CENTER.value], 0.0001);

        assertEquals(2.449489742783178, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.MAX_DISTANCE_TO_CENTROID.value], 0.0001);
        assertEquals(1.7320508075688772, stats[1][StatisticsOfLabelledPixels.STATISTICS_ENTRY.MAX_DISTANCE_TO_CENTROID.value], 0.0001);

        assertEquals(2, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.CENTROID_X.value], 0.0001);
        assertEquals(1, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.CENTROID_Y.value], 0.0001);
        assertEquals(1, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.CENTROID_Z.value], 0.0001);

        assertEquals(1.4075472387377392, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.MAX_MEAN_DISTANCE_TO_CENTROID_RATIO.value], 0.0001);
        assertEquals(1.4075472387377392, stats[0][StatisticsOfLabelledPixels.STATISTICS_ENTRY.MAX_MEAN_DISTANCE_TO_MASS_CENTER_RATIO.value], 0.0001);

        assertEquals(1.269867513096764, stats[1][StatisticsOfLabelledPixels.STATISTICS_ENTRY.MAX_MEAN_DISTANCE_TO_CENTROID_RATIO.value], 0.0001);
        assertEquals(1.269867513096764, stats[1][StatisticsOfLabelledPixels.STATISTICS_ENTRY.MAX_MEAN_DISTANCE_TO_MASS_CENTER_RATIO.value], 0.0001);







    }


}