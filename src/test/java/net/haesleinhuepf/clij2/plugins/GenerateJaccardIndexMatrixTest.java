package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GenerateJaccardIndexMatrixTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer image1 = clij2.pushString("" +
                "1 1 0 0\n" +
                "1 1 2 2\n\n" +
                "1 1 0 0\n" +
                "1 1 2 2");

        ClearCLBuffer image2 = clij2.pushString("" +
                "1 1 0 2\n" +
                "1 1 0 2\n\n" +
                "1 1 0 2\n" +
                "1 1 0 2");

        ClearCLBuffer ref = clij2.pushString("" +
                "0.33333334 0 0.33333334\n" +
                "0 1 0\n" +
                "0.33333334 0 0.33333334");


        ClearCLBuffer jaccard_matrix = clij2.create(3, 3);

        GenerateJaccardIndexMatrix.generateJaccardIndexMatrix(clij2, image1, image2, jaccard_matrix);

        clij2.print(jaccard_matrix);


        ClearCLBuffer jaccard_matrix_0 = clij2.create(3, 3);

        UndefinedToZero.undefinedToZero(clij2, jaccard_matrix, jaccard_matrix_0);

        clij2.print(jaccard_matrix_0);

        String a = clij2.pullString(jaccard_matrix_0);
        String b = clij2.pullString(ref);

        assertTrue(a.compareTo(b) == 0);
    }

}
