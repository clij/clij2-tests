package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;

public class GenerateBinaryOverlapMatrixTest {
    public static void main(String[] args) {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer labelmap1 = clij2.pushString("" +
                "1 2\n" +
                "1 3");

        ClearCLBuffer labelmap2 = clij2.pushString("" +
                "1 2\n" +
                "1 2");

        ClearCLBuffer reference = clij2.pushString("" +
                "0.0 0.0 0.0 0.0\n" +
                "0.0 1.0 0.0 0.0\n" +
                "0.0 0.0 1.0 1.0");

        ClearCLBuffer result = clij2.create(reference);

        clij2.generateBinaryOverlapMatrix(labelmap1, labelmap2, result);

        clij2.print(reference);
        clij2.print(result);

        clij2.clear();
    }
}
