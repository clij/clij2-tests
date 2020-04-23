package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import net.imglib2.img.array.ArrayImgs;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdjacencyMatrixTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer touch_matrix = clij2.push(ArrayImgs.floats(new float[]{
                        0, 0, 0, 0, 0,
                        1, 0, 0, 0, 0,
                        1, 1, 0, 0, 0,
                        0, 1, 0, 0, 0,
                        0, 0, 1, 0, 0
                }, new long[]{5, 5}
        ));

        ClearCLBuffer ref = clij2.push(ArrayImgs.floats(new float[]{
                        1, 1, 1, 0, 0,
                        1, 1, 1, 1, 0,
                        1, 1, 1, 0, 1,
                        0, 1, 0, 1, 0,
                        0, 0, 1, 0, 1
                }, new long[]{5, 5}
        ));

        ClearCLBuffer adj = clij2.create(touch_matrix);
        clij2.touchMatrixToAdjacencyMatrix(touch_matrix, adj);
        clij2.print(adj);

        assertEquals(0, clij2.meanSquaredError(ref, adj), 0);

        clij2.clear();

    }
}
