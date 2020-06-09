package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.test.TestUtilities;
import net.haesleinhuepf.clij2.CLIJ2;
import net.imglib2.img.array.ArrayImgs;
import org.junit.Test;

public class EntropyTest {
    @Test
    public void test2D() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer input_image = clij2.push(ArrayImgs.floats(new float[]{
                        0, 0, 0, 0, 0,
                        0, 1, 1, 1, 0,
                        0, 1, 1, 1, 0,
                        0, 1, 1, 1, 0,
                        0, 0, 0, 0, 0
                }, new long[]{5, 5}
        ));

        ClearCLBuffer refereanceResult = clij2.push(ArrayImgs.floats(new float[]{
                0, 0, 0, 0, 0,
                0, 1, 1, 1, 0,
                0, 1, 0, 1, 0,
                0, 1, 1, 1, 0,
                0, 0, 0, 0, 0
                }, new long[]{5, 5}
        ));

        ClearCLBuffer result = clij2.create(input_image);

        clij2.entropyBox(input_image, result, 3, 3, 0);

        System.out.println("Result: ");
        clij2.print(result);

        TestUtilities.clBuffersEqual(clij2.getCLIJ(), result, refereanceResult, 0);

        clij2.clear();
    }

    @Test
    public void test3D() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer input_image = clij2.push(ArrayImgs.floats(new float[]{
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,

                0, 0, 0, 0, 0,
                0, 1, 1, 1, 0,
                0, 1, 1, 1, 0,
                0, 1, 1, 1, 0,
                0, 0, 0, 0, 0,

                0, 0, 0, 0, 0,
                0, 1, 1, 1, 0,
                0, 1, 1, 1, 0,
                0, 1, 1, 1, 0,
                0, 0, 0, 0, 0,

                0, 0, 0, 0, 0,
                0, 1, 1, 1, 0,
                0, 1, 1, 1, 0,
                0, 1, 1, 1, 0,
                0, 0, 0, 0, 0,

                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0

                }, new long[]{5, 5, 5}
        ));

        ClearCLBuffer refereanceResult = clij2.push(ArrayImgs.floats(new float[]{
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,

                0, 0, 0, 0, 0,
                0, 1, 1, 1, 0,
                0, 1, 1, 1, 0,
                0, 1, 1, 1, 0,
                0, 0, 0, 0, 0,

                0, 0, 0, 0, 0,
                0, 1, 1, 1, 0,
                0, 1, 0, 1, 0,
                0, 1, 1, 1, 0,
                0, 0, 0, 0, 0,

                0, 0, 0, 0, 0,
                0, 1, 1, 1, 0,
                0, 1, 1, 1, 0,
                0, 1, 1, 1, 0,
                0, 0, 0, 0, 0,

                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0

                }, new long[]{5, 5,  5}
        ));

        ClearCLBuffer result = clij2.create(input_image);

        clij2.entropyBox(input_image, result, 3, 3, 3);

        System.out.println("Result: ");
        clij2.print(result);

        TestUtilities.clBuffersEqual(clij2.getCLIJ(), result, refereanceResult, 0);

        clij2.clear();
    }
}