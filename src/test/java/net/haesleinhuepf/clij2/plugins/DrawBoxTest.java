package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.coremem.enums.NativeTypeEnum;
import net.haesleinhuepf.clij2.plugins.DrawBox;
import net.haesleinhuepf.clijx.CLIJx;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DrawBoxTest {
    @Test
    public void test2D() {
        CLIJx clijx = CLIJx.getInstance();
        ClearCLBuffer image = clijx.create(new long[]{100, 100}, NativeTypeEnum.Float);

        float x = 10;
        float y = 10;
        float w = 20;
        float h = 50;

        clijx.drawBox(image, x, y, 0f, w, h, 0f);

        double[] box = clijx.boundingBox(image);

        assertEquals(box[0], x, 0);
        assertEquals(box[1], y, 0);
        assertEquals(box[3], w + 1, 0);
        assertEquals(box[4], h + 1, 0);

        //clijx.op.drawLine(image, 10f, 10f, 0f, 10f, 50f, 0f, 5f);

        //new ImageJ();
        //clijx.show(image, "image");
        //new WaitForUserDialog("helo").show();
        image.close();
    }

    @Test
    public void test3D() {
        CLIJx clijx = CLIJx.getInstance();
        ClearCLBuffer image = clijx.create(new long[]{100, 100, 100}, NativeTypeEnum.Float);

        float x = 10;
        float y = 11;
        float z = 14;
        float w = 20;
        float h = 50;
        float d = 30;

        clijx.drawBox(image, x, y, z, w, h, d);

        double[] box = clijx.boundingBox(image);

        assertEquals(box[0], x, 0);
        assertEquals(box[1], y, 0);
        assertEquals(box[2], z, 0);
        assertEquals(box[3], w + 1, 0);
        assertEquals(box[4], h + 1, 0);
        assertEquals(box[5], d + 1, 0);

        //clijx.op.drawLine(image, 10f, 10f, 0f, 10f, 50f, 0f, 5f);

        //new ImageJ();
        //clijx.show(image, "image");
        //new WaitForUserDialog("helo").show();
        image.close();
    }

}