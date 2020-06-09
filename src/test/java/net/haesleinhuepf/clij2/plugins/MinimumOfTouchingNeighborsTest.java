package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.measure.ResultsTable;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

public class MinimumOfTouchingNeighborsTest {

//    public static void main(String[] args) {
    @Test
    public void test() {
        new ImageJ();

        ImagePlus imp = IJ.openImage("C:/structure/data/blobs.tif");
        imp.show();

        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer input = clij2.push(imp);
        ClearCLBuffer temp1 = clij2.create(input);
        ClearCLBuffer temp2 = clij2.create(input);
        ClearCLBuffer temp3 = clij2.create(input);

        clij2.thresholdOtsu(input, temp1);
        clij2.voronoiOctagon(temp1, temp2);

        clij2.binaryNot(temp2, temp1);

        clij2.connectedComponentsLabelingBox(temp1, temp3);

        clij2.maximum2DBox(temp3, temp2, 2, 2);

        int number_of_objects = (int) clij2.maximumOfAllPixels(temp2);

        ClearCLBuffer touch_matrix = clij2.create(new long[]{number_of_objects + 1, number_of_objects + 1});
        clij2.generateTouchMatrix(temp2, touch_matrix);

        ResultsTable table = new ResultsTable();
        clij2.statisticsOfLabelledPixels(input, temp2, table);

        ClearCLBuffer column = clij2.create(table.size(), 1, 1);
        clij2.resultsTableColumnToImage(column, table, "MEAN_INTENSITY");


        clij2.replaceIntensities(temp2, column, temp3);
        clij2.show(temp3, "intensity");

        ClearCLBuffer column2 = clij2.create(column);
        clij2.minimumOfTouchingNeighbors(column, touch_matrix, column2);

        clij2.replaceIntensities(temp2, column2, temp3);
        clij2.show(temp3, "local min intensity");

        clij2.show(column, "col");
        clij2.show(column2, "col2");







    }
}
