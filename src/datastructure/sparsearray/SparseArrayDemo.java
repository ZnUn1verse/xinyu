package datastructureandalgorithms.sparsearray;

import java.io.*;

/**
 * @author Xinyu Zhang
 * @version 1.0
 * @desc 二维数组 --> 稀疏数组
 * @date 2021 2021/6/14 15:23
 */
public class SparseArrayDemo {
    public static void main(String[] args) {
        //原始数组
        int[][] originalArray = new int[10][10];
        originalArray[1][1] = 1;
        originalArray[2][2] = 2;
        originalArray[4][4] = 4;
        int sum = 0;
        System.out.println("原始数组");
        for (int i = 0; i < originalArray.length; i++) {
            for (int j = 0; j < originalArray[i].length; j++) {
                if (originalArray[i][j] != 0) {
                    sum++;
                }
                System.out.print(originalArray[i][j] + " ");
            }
            System.out.println();
        }

        //转换成稀疏数组
        System.out.println("转换成稀疏数组");
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = originalArray.length;
        sparseArray[0][1] = originalArray.length;
        sparseArray[0][2] = sum;
        int count = 0;
        for (int i = 0; i < originalArray.length; i++) {
            for (int j = 0; j < originalArray[i].length; j++) {
                if (originalArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = originalArray[i][j];
                }
            }
        }
        for (int i = 0; i < sparseArray.length; i++) {
            for (int j = 0; j < sparseArray[i].length; j++) {
                System.out.print(sparseArray[i][j] + " ");
            }
            System.out.println();
        }
        arrayToDisk(sparseArray, "C:\\Users\\98427\\Desktop\\sparseArray.txt");
        //转回二维数组
        System.out.println("恢复后的二维数组");
        int[][] originalArray2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            for (int j = 0; j < sparseArray[i].length; j++) {
                originalArray2[i][j] = sparseArray[i][j];
            }
        }
        for (int i = 0; i < originalArray2.length; i++) {
            for (int j = 0; j < originalArray2[i].length; j++) {
                System.out.print(originalArray[i][j] + " ");
            }
            System.out.println();
        }
        diskToArray("C:\\Users\\98427\\Desktop\\sparseArray.txt");
    }

    /**
     * 将稀疏数组写入磁盘
     *
     * @param sparseArr
     */
    private static void arrayToDisk(int[][] sparseArr, String path) {
        System.out.println("将稀疏数组写入磁盘");
        FileWriter writer = null;
        try {
            File file = new File(path);
            writer = new FileWriter(file);
            for (int[] row : sparseArr) {
                for (int data : row) {
                    writer.write(data + ",");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 在磁盘中读取稀疏数组
     *
     * @param path
     * @return
     */
    private static int[][] diskToArray(String path) {
        System.out.println("在磁盘中读取稀疏数组");
        System.out.println("--------------------------------------------");
        File file = new File(path);
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            String[] split = line.split(",");
            int row = split.length / 3;
            int[][] SparseArray1 = new int[row][3];
            for (int i = 0; i < split.length; i++) {
                int parseInt = Integer.parseInt(split[i]);
                SparseArray1[i / 3][i % 3] = parseInt;
            }
            //稀疏数组遍历：
            System.out.println("输出新的稀疏数组：");
            for (int[] rw : SparseArray1) {
                for (int data : rw) {
                    System.out.print(data + "\t");
                }
                System.out.println();
            }
            bufferedReader.close();
            return SparseArray1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
