package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImagePlus;
import net.haesleinhuepf.clij.CLIJ;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.coremem.enums.NativeTypeEnum;
import net.haesleinhuepf.clij2.CLIJ2;
import net.haesleinhuepf.clij2.plugins.ExcludeLabelsOnEdges;
import net.haesleinhuepf.clijx.CLIJx;
import org.junit.Test;

import static net.haesleinhuepf.clij2.plugins.ConnectedComponentsLabeling.connectedComponentsLabeling;
import static org.junit.Assert.*;

public class ExcludeLabelsOnEdgesTest {

    @Test
    public void test() {
        ImagePlus imp = IJ.openImage("src/test/resources/blobs.tif");


        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer input = clij2.push(imp);
        ClearCLBuffer thresholded = clij2.create(input.getDimensions(), NativeTypeEnum.Float);
        ClearCLBuffer connectedComponents = clij2.create(input.getDimensions(), NativeTypeEnum.Float);
        ClearCLBuffer withoutEdges = clij2.create(input.getDimensions(), NativeTypeEnum.Float);

        clij2.threshold(input, thresholded, 127f);
        clij2.show(thresholded, "thresholded");
        clij2.connectedComponentsLabelingBox(thresholded, connectedComponents);

        clij2.excludeLabelsOnEdges(connectedComponents, withoutEdges);


        //new ImageJ();
        //clij.show(withoutEdges, "w");
        //new WaitForUserDialog("dd").show();

        assertEquals(64.0, clij2.maximumOfAllPixels(connectedComponents), 0.1);
        assertEquals(46.0, clij2.maximumOfAllPixels(withoutEdges), 0.1);


        //clij.show(output, "result");

        input.close();
        connectedComponents.close();
        thresholded.close();

    }

}