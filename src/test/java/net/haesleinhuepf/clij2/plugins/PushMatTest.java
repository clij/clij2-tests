package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij2.CLIJ2;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class PushMatTest {
    @Test
    public void testPushDouble2D() {
        double[][] var = {
            {1,2,3},
            {4,5,6}
        };

        CLIJ2 clij2 = CLIJ2.getInstance();
        ClearCLBuffer buffer = clij2.pushMat(var);
        clij2.print(buffer);
        String result = clij2.pullString(buffer);
        assertTrue(result.compareTo("1.0 2.0 3.0\n4.0 5.0 6.0") == 0);
        //assertTrue(arraysEqual(clij2.pullMat(buffer), var));

        ClearCLBuffer buffer1 = clij2.pushMatXYZ(var);
        clij2.print(buffer1);
        assertTrue(clij2.pullString(buffer1).compareTo("1.0 4.0\n2.0 5.0\n3.0 6.0") == 0);
        //assertTrue(arraysEqual(clij2.pullMatXYZ(buffer1), var));

        clij2.clear();

    }

    @Test
    public void testPushDouble3D() {
        double[][][] var =
            {
                {
                        {1, 2, 3},
                        {4, 5, 6}
                },{
                        {7, 8, 9},
                        {10, 11, 12}
                }
            };

        CLIJ2 clij2 = CLIJ2.getInstance();
        ClearCLBuffer buffer = clij2.pushMat(var);
        clij2.print(buffer);
        String result = clij2.pullString(buffer);
        assertTrue(result.compareTo("1.0 2.0 3.0\n4.0 5.0 6.0\n\n7.0 8.0 9.0\n10.0 11.0 12.0") == 0);
        //assertTrue(arraysEqual(clij2.pullMat(buffer), var));

        ClearCLBuffer buffer1 = clij2.pushMatXYZ(var);
        clij2.print(buffer1);
        assertTrue(clij2.pullString(buffer1).compareTo("1.0 7.0\n" +
                "4.0 10.0\n" +
                "\n" +
                "2.0 8.0\n" +
                "5.0 11.0\n" +
                "\n" +
                "3.0 9.0\n" +
                "6.0 12.0") == 0);
        //assertTrue(arraysEqual(clij2.pullMatXYZ(buffer1), var));

        clij2.clear();

    }


    @Test
    public void testPushFloat3D() {
        float[][][] var =
                {
                        {
                                {1, 2, 3},
                                {4, 5, 6}
                        },{
                        {7, 8, 9},
                        {10, 11, 12}
                }
                };

        CLIJ2 clij2 = CLIJ2.getInstance();
        ClearCLBuffer buffer = clij2.pushMat(var);
        clij2.print(buffer);
        String result = clij2.pullString(buffer);
        assertTrue(result.compareTo("1.0 2.0 3.0\n4.0 5.0 6.0\n\n7.0 8.0 9.0\n10.0 11.0 12.0") == 0);
        assertTrue(arraysEqual(clij2.pullMat(buffer), var));

        ClearCLBuffer buffer1 = clij2.pushMatXYZ(var);
        clij2.print(buffer1);
        assertTrue(clij2.pullString(buffer1).compareTo("1.0 7.0\n" +
                "4.0 10.0\n" +
                "\n" +
                "2.0 8.0\n" +
                "5.0 11.0\n" +
                "\n" +
                "3.0 9.0\n" +
                "6.0 12.0") == 0);
        assertTrue(arraysEqual(clij2.pullMatXYZ(buffer1), var));

        clij2.clear();

    }

    @Test
    public void testPushFloat2D() {
        float[][] var = {
                {1,2,3},
                {4,5,6}
        };

        CLIJ2 clij2 = CLIJ2.getInstance();
        ClearCLBuffer buffer = clij2.pushMat(var);
        clij2.print(buffer);
        String result = clij2.pullString(buffer);
        assertTrue(result.compareTo("1.0 2.0 3.0\n4.0 5.0 6.0") == 0);
        assertTrue(arraysEqual(clij2.pullMat(buffer), var));

        ClearCLBuffer buffer1 = clij2.pushMatXYZ(var);
        clij2.print(buffer1);
        assertTrue(clij2.pullString(buffer1).compareTo("1.0 4.0\n2.0 5.0\n3.0 6.0") == 0);
        assertTrue(arraysEqual(clij2.pullMatXYZ(buffer1), var));

        clij2.clear();

    }

    @Test
    public void testPushChar3D() {
        char[][][] var =
                {
                        {
                                {1, 2, 3},
                                {4, 5, 6}
                        },{
                        {7, 8, 9},
                        {10, 11, 12}
                }
                };

        CLIJ2 clij2 = CLIJ2.getInstance();
        ClearCLBuffer buffer = clij2.pushMat(var);
        clij2.print(buffer);
        String result = clij2.pullString(buffer);
        assertTrue(result.compareTo("1.0 2.0 3.0\n4.0 5.0 6.0\n\n7.0 8.0 9.0\n10.0 11.0 12.0") == 0);
        assertTrue(arraysEqual(clij2.pullMat(buffer), var));

        ClearCLBuffer buffer1 = clij2.pushMatXYZ(var);
        clij2.print(buffer1);
        assertTrue(clij2.pullString(buffer1).compareTo("1.0 7.0\n" +
                "4.0 10.0\n" +
                "\n" +
                "2.0 8.0\n" +
                "5.0 11.0\n" +
                "\n" +
                "3.0 9.0\n" +
                "6.0 12.0") == 0);
        assertTrue(arraysEqual(clij2.pullMatXYZ(buffer1), var));

        clij2.clear();

    }


    @Test
    public void testPushChar2D() {
        char[][] var = {
                {1,2,3},
                {4,5,6}
        };

        CLIJ2 clij2 = CLIJ2.getInstance();
        ClearCLBuffer buffer = clij2.pushMat(var);
        clij2.print(buffer);
        String result = clij2.pullString(buffer);
        assertTrue(result.compareTo("1.0 2.0 3.0\n4.0 5.0 6.0") == 0);
        assertTrue(arraysEqual(clij2.pullMat(buffer), var));

        ClearCLBuffer buffer1 = clij2.pushMatXYZ(var);
        clij2.print(buffer1);
        assertTrue(clij2.pullString(buffer1).compareTo("1.0 4.0\n2.0 5.0\n3.0 6.0") == 0);
        assertTrue(arraysEqual(clij2.pullMatXYZ(buffer1), var));

        clij2.clear();

    }

    @Test
    public void testPushByte3D() {
        byte[][][] var =
                {
                        {
                                {1, 2, 3},
                                {4, 5, 6}
                        },{
                        {7, 8, 9},
                        {10, 11, 12}
                }
                };

        CLIJ2 clij2 = CLIJ2.getInstance();
        ClearCLBuffer buffer = clij2.pushMat(var);
        clij2.print(buffer);
        String result = clij2.pullString(buffer);
        assertTrue(result.compareTo("1.0 2.0 3.0\n4.0 5.0 6.0\n\n7.0 8.0 9.0\n10.0 11.0 12.0") == 0);
        assertTrue(arraysEqual(clij2.pullMat(buffer), var));

        ClearCLBuffer buffer1 = clij2.pushMatXYZ(var);
        clij2.print(buffer1);
        assertTrue(clij2.pullString(buffer1).compareTo("1.0 7.0\n" +
                "4.0 10.0\n" +
                "\n" +
                "2.0 8.0\n" +
                "5.0 11.0\n" +
                "\n" +
                "3.0 9.0\n" +
                "6.0 12.0") == 0);
        assertTrue(arraysEqual(clij2.pullMatXYZ(buffer1), var));

        clij2.clear();

    }


    @Test
    public void testPushByte2D() {
        byte[][] var = {
                {1,2,3},
                {4,5,6}
        };

        CLIJ2 clij2 = CLIJ2.getInstance();
        ClearCLBuffer buffer = clij2.pushMat(var);
        clij2.print(buffer);
        String result = clij2.pullString(buffer);
        assertTrue(result.compareTo("1.0 2.0 3.0\n4.0 5.0 6.0") == 0);
        assertTrue(arraysEqual(clij2.pullMat(buffer), var));

        ClearCLBuffer buffer1 = clij2.pushMatXYZ(var);
        clij2.print(buffer1);
        assertTrue(clij2.pullString(buffer1).compareTo("1.0 4.0\n2.0 5.0\n3.0 6.0") == 0);
        assertTrue(arraysEqual(clij2.pullMatXYZ(buffer1), var));

        clij2.clear();
    }

    private boolean arraysEqual(Object obj, byte[][] var2) {
        byte[][] var1 = (byte[][]) obj;

        if (var1.length != var2.length) {
            return false;
        }
        if (var1[0].length != var2[0].length) {
            return false;
        }


        for (int y = 0; y < var1[0].length; y++) {
            for (int x = 0; x < var1.length; x++) {
                if (var1[x][y] != var2[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean arraysEqual(Object obj, char[][] var2) {
        char[][] var1 = (char[][]) obj;

        if (var1.length != var2.length) {
            return false;
        }
        if (var1[0].length != var2[0].length) {
            return false;
        }


        for (int y = 0; y < var1[0].length; y++) {
            for (int x = 0; x < var1.length; x++) {
                if (var1[x][y] != var2[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean arraysEqual(Object obj, float[][] var2) {
        float[][] var1 = (float[][]) obj;

        if (var1.length != var2.length) {
            return false;
        }
        if (var1[0].length != var2[0].length) {
            return false;
        }


        for (int y = 0; y < var1[0].length; y++) {
            for (int x = 0; x < var1.length; x++) {
                if (var1[x][y] != var2[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean arraysEqual(Object obj, double[][] var2) {
        double[][] var1 = (double[][]) obj;

        if (var1.length != var2.length) {
            return false;
        }
        if (var1[0].length != var2[0].length) {
            return false;
        }

        for (int y = 0; y < var1[0].length; y++) {
            for (int x = 0; x < var1.length; x++) {
                if (var1[x][y] != var2[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean arraysEqual(Object obj, byte[][][] var2) {
        byte[][][] var1 = (byte[][][]) obj;

        if (var1.length != var2.length) {
            return false;
        }
        if (var1[0].length != var2[0].length) {
            return false;
        }


        for (int z = 0; z < var1[0][0].length; z++) {
            for (int y = 0; y < var1[0].length; y++) {
                for (int x = 0; x < var1.length; x++) {
                    if (var1[x][y][z] != var2[x][y][z]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean arraysEqual(Object obj, char[][][] var2) {
        char[][][] var1 = (char[][][]) obj;

        if (var1.length != var2.length) {
            return false;
        }
        if (var1[0].length != var2[0].length) {
            return false;
        }


        for (int z = 0; z < var1[0][0].length; z++) {
            for (int y = 0; y < var1[0].length; y++) {
                for (int x = 0; x < var1.length; x++) {
                    if (var1[x][y][z] != var2[x][y][z]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean arraysEqual(Object obj, float[][][] var2) {
        float[][][] var1 = (float[][][]) obj;

        if (var1.length != var2.length) {
            return false;
        }
        if (var1[0].length != var2[0].length) {
            return false;
        }


        for (int z = 0; z < var1[0][0].length; z++) {
            for (int y = 0; y < var1[0].length; y++) {
                for (int x = 0; x < var1.length; x++) {
                    if (var1[x][y][z] != var2[x][y][z]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private boolean arraysEqual(Object obj, double[][][] var2) {
        double[][][] var1 = (double[][][]) obj;

        if (var1.length != var2.length) {
            return false;
        }
        if (var1[0].length != var2[0].length) {
            return false;
        }


        for (int z = 0; z < var1[0][0].length; z++) {
            for (int y = 0; y < var1[0].length; y++) {
                for (int x = 0; x < var1.length; x++) {
                    if (var1[x][y][z] != var2[x][y][z]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
