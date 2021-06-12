package net.haesleinhuepf.clij2.plugins;

import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.plugin.Duplicator;
import net.haesleinhuepf.clij.CLIJ;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Ignore;
import org.junit.Test;

public class TilorTest {
//    public static void main(String... args) {

    @Ignore
    @Test
    public void testTilor() {
        //new ImageJ();
        ImagePlus imp = IJ.openImage("C:/structure/data/2018-02-14-17-26-57-76-Akanksha_nGFP_001111.raw.tif");
        IJ.run(imp, "32-bit", "");
        imp = new Duplicator().run(imp, 65, 104);

        CLIJ2 clij1 = new CLIJ2(new CLIJ("RTX"));
        CLIJ2 clij2 = new CLIJ2(new CLIJ("CPU"));

        for (int i = 0; i < 3; i++) {
            System.out.println("--------------------");
            {

                ClearCLBuffer input1 = clij1.push(imp);
                ClearCLBuffer input2 = clij1.create(input1);
                ClearCLBuffer output1 = clij1.create(input1);
                clij1.setRampX(input2);

                long time = System.currentTimeMillis();
                clij1.addImages(input1, input2, output1);
                System.out.println("Processing on one GPU (" + clij1.getGPUName() + ", " + clij1.toString() + ") took " + (System.currentTimeMillis() - time) + " ms.");
                //clij1.show(output1, "output1");
            }

            {

                ClearCLBuffer input1 = clij2.push(imp);
                ClearCLBuffer input2 = clij2.create(input1);
                ClearCLBuffer output2 = clij2.create(input1);
                clij2.setRampX(input2);
                clij2.setRampY(output2);
                //clij2.set(input2, 1);


                long time = System.currentTimeMillis();
                AddImages plugin = new AddImages();
                plugin.setArgs(new Object[]{input1, input2, output2, input1.getWidth() / 2, input1.getHeight() / 2, input1.getDepth(), 0, 0, 0});
                plugin.setCLIJ2(clij2);
                plugin.executeCL();
                System.out.println("Processing on several OpenCL devices took " + (System.currentTimeMillis() - time) + "ms.");

                //clij2.show(output2, "output2");
            }
        }
    }
}
