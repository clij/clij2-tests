package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.coremem.enums.NativeTypeEnum;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ImageTypeTest {
    @Test
    public void imageTypeTest() {
        CLIJ2 clij2 = CLIJ2.getInstance();

        ClearCLBuffer in = clij2.pushString("" +
                "0 0\n" +
                "1 2");

        ClearCLBuffer temp = clij2.create(in.getDimensions(), NativeTypeEnum.Int);
        clij2.copy(in, temp);

        ClearCLBuffer out = clij2.create(in.getDimensions(), NativeTypeEnum.Float);
        clij2.copy(temp, out);

        clij2.print(out);

        double mse = clij2.meanSquaredError(in, out);
        System.out.println(mse);

        assertTrue(mse == 0);

    }
}
