package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LabelledSpotsToPointListTest {
    @Test
    public void test() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer lab = clij2.pushString("" +
                "0 0 0 0\n" +
                "0 1 2 0\n" +
                "0 3 0 0\n" +
                "0 0 0 0");

        ClearCLBuffer ref = clij2.pushString("" +
                "1 2 1\n" +
                "1 1 2\n" +
                "0 0 0");

        ClearCLBuffer out = clij2.create(ref);

        clij2.labelledSpotsToPointList(lab, out);

        clij2.print(out);

        assertEquals(0, clij2.meanSquaredError(ref, out), 0.01);

        clij2.clear();
    }

    @Test
    public void test2() {
        CLIJ2 clij2 = CLIJ2.getInstance();
        ClearCLBuffer image = clij2.pushString(""+
                "0 0 0 0 0\n" +
                "0 0 1 0 1\n" +
                "0 1 0 0 0\n" +
                "0 0 0 1 0\n" +
                "1 0 0 0 0"
        );

        ClearCLBuffer ref = clij2.pushString(""+
                "2 4 1 3 0\n" +
                "1 1 2 3 4\n" +
                "0 0 0 0 0");

        ClearCLBuffer pointlist = clij2.create(5, 3);

        clij2.spotsToPointList(image, pointlist);

        System.out.println(clij2.pullString(pointlist));

        double mse = clij2.meanSquaredError(pointlist, ref);
        System.out.println(mse);

    }
}
