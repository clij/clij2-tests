package net.haesleinhuepf.clij2.plugins;

import ij.ImageJ;
import ij.ImagePlus;
import ij.gui.NewImage;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLDevice;
import net.haesleinhuepf.clij.utilities.CLInfo;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

public class DetectMaximaBoxTest {
    //public static void main(String[] args) {
    @Test
    public void test() {
        new ImageJ();
        ImagePlus imp = NewImage.createFloatImage("", 512, 1024, 150, NewImage.FILL_RANDOM);

        CLIJ2 clij2 = CLIJ2.getInstance();
        System.out.println(CLInfo.clinfo());

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    ClearCLDevice dev = clij2.getCLIJ().getClearCLContext().getDevice();
                    System.out.println(System.currentTimeMillis() + ": " + dev.getNumberOfLiveContexts() + "/" + dev.getNumberOfAvailableContexts());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        System.out.println(clij2.getGPUName());
        ClearCLBuffer input = clij2.push(imp);
        ClearCLBuffer output = clij2.create(input);
        ClearCLBuffer temp = clij2.create(input);
        System.out.println("A");

        clij2.blur(input, output, 15, 15);
        System.out.println("B");

        //clij2.copy(output, input);
        clij2.subtractImages(input, output, temp);

        System.out.println("C");
        clij2.detectMaximaBox(temp, output, 1);
        System.out.println("D");

        clij2.show(output, "out");
        System.out.println(clij2.reportMemory());





    }

}
