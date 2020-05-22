package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImageJ;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;

public class HistogramTest {

    public static void main(String[] args) {
        new ImageJ();

        CLIJ2 clij2 = CLIJ2.getInstance();
        ClearCLBuffer buff = clij2.push(IJ.openImage("c:/structure/data/histogramm33.tif"));
        //clij2.pushString("" +
        //"0 2 204 4\n" +
        //"0 2 203 3");

        ClearCLBuffer histogram  = clij2.create(205 ,1, 1);
        clij2.histogram(buff, histogram, 205, 0, 204, false);

        clij2.print(histogram);



    }

}
