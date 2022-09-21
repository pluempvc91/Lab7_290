import java.util.Arrays;
public class Lab_MatrixMul_290 {
    public static void main(String[] args) {
        int[][] inputA = { { 5, 6, 7 }, { 4, 8, 9 } };
        int[][] inputB = { { 6, 4 }, { 5, 7 }, { 1, 1 } };
        MyData matA = new MyData(inputA);
        MyData matB = new MyData(inputB);
        int matC_r = matA.data.length;
        int matC_c = matB.data[0].length;
        MyData matC = new MyData(matC_r, matC_c);
        /* Q4 construct 2D array for each "thread" with respected to each cell in matC, then start each thread */
        Thread[][] MyThread = new Thread[matC_r][matC_c];
        for(int i = 0; i < matC_r; i++){
            for(int j = 0; j < matC_c; j++){
                thread[i][j] = new Thread(new MatrixMulThread(i, j, matA, matB, matC));
                thread[i][j].start();
            }
        }
        
        /* Q5 join each thread */
        for(int i = 0; i < matC_r; i++){
            for(int j = 0; j < matC_c; j++){
                try {
                    thread[i][j].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
        matC.show();
    }
}

class MatrixMulThread implements Runnable {
    int processing_row; int processing_col;
    MyData datA; MyData datB; MyData datC;
    MatrixMulThread(int tRow, int tCol, MyData a, MyData b, MyData c) {
    /* Q1 code here */
    processing_row = tRow;
    processing_col = tCol;
    datA = a;
    datB = b;
    datC = c;
    }
    /* Q2 */ 
    public void run() { 
        //Q3
        int i = 0, j = 0;
        datC.data[processing_row][processing_col] = 0;
        while(i < datA.data[0].length && j < datB.data.length){
            datC.data[processing_row][processing_col] += datA.data[processing_row][i] * datB.data[j][processing_col];
            ++i;
            ++j;
        }
        System.out.println("perform sum of multiplication of assigned row and col");
    }
}

class MyData {
    int[][] data;
    MyData(int[][] m) { data = m; }
    MyData(int r, int c) {
        data = new int[r][c];
        for (int[] aRow : data)
            Arrays.fill(aRow, 9);       
    }
    void show() {
    System.out.println(Arrays.deepToString(data));
    }
}