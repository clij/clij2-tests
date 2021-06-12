package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MergeTouchingNeighborsTest {
    @Test
    public void mergeTouchingNeighborsTest() {

        CLIJ2 clij2 = CLIJ2.getInstance("HD");
        System.out.println(clij2.getGPUName());

        ClearCLBuffer input = clij2.pushString("" +
                "1 1 0 2 0 3 0 0 0 0\n" +
                "1 1 2 2 0 3 0 0 4 5\n" +
                "1 0 0 2 0 0 0 0 4 5\n" +
                "7 7 7 7 7 7 7 7 7 6"
        );

        String reference =
                "1.0 1.0 0.0 1.0 0.0 2.0 0.0 0.0 0.0 0.0\n" +
                "1.0 1.0 1.0 1.0 0.0 2.0 0.0 0.0 1.0 1.0\n" +
                "1.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 1.0 1.0\n" +
                "1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0";

        ClearCLBuffer output = clij2.create(input);


        clij2.mergeTouchingLabels(input, output);

        clij2.print(output);

        String result = clij2.pullString(output);
        assertTrue(result.compareTo(reference) == 0);

        input.close();
        output.close();

        System.out.println(clij2.reportMemory());
        clij2.clear();
    }

}
