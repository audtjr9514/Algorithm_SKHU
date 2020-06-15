/* ************************
파일명 : matrixPathTest.java
작성자 : 김명석
작성일 : 20.05.23
내용 : n x n의 행렬을 (1,1)에서 출발하여 (n,n)까지 도착할 때의 경로의 최대값을 출력하는 프로그램
 ************************ */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class matrixPathTest {
    static int dp[][]; //행렬을 동적계획법으로 풀기 위한 dp 배열

    // 행렬 경로의 최대값을 구하는 함수
    public static int dp_matrix(int[][] matrix) {
        dp = new int[matrix.length + 1][matrix.length + 1];
        for (int i = 0; i < matrix.length + 1; i++) {
            dp[i][0] = 0;
            dp[0][i] = 0;
        }
        for (int i = 1; i < matrix.length + 1; i++) {
            for (int j = 1; j < matrix.length + 1; j++) {
                dp[i][j] = matrix[i - 1][j - 1] + Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[matrix.length][matrix.length];
    }

    // 행렬 최대값이 나오게 되는 경로를 구하는 함수
    public static StringBuilder matrix_path() {
        StringBuilder path = new StringBuilder();
        int i = 1, j = 1;
        path.append("(" + i + "," + j + ") "); // 출발은 1, 1
        while (true) {
            // 행렬의 끝을 도달하면 반복 종료
            if (i == dp.length - 1 && j == dp.length - 1)
                break;
            // 만약 행의 끝에 도달하면 열을 증가시킨다. ( 무조건 열만 증가할 수 밖에 없다.)
            if (i == dp.length - 1) {
                j++;
                path.append("(" + i + "," + j + ") ");
                continue;
            }
            // 만약 열의 끝에 도달하면 열을 증가시킨다. ( 무조건 행만 증가할 수 밖에 없다.)
            if (j == dp.length - 1) {
                i++;
                path.append("(" + i + "," + j + ") ");
                continue;
            }
            // i, j 기준으로 행+1 한 것과, 열+1 한 것의 값을 비교한다.
            if (dp[i][j + 1] < dp[i + 1][j]) {
                i++;
                path.append("(" + i + "," + j + ") ");
            } else {
                j++;
                path.append("(" + i + "," + j + ") ");
            }
        }
        return path;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("hw8_1 : 김명석\n\n");
        bw.write("행의 크기 입력 : ");
        bw.flush();
        int n = Integer.parseInt(br.readLine());
        int matrix[][] = new int[n][n];
        bw.write(n + " x " + n + " 크기의 행렬 원소 입력 : \n");
        bw.flush();
        // 행렬의 입력
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bw.write("최대 점수 = " + dp_matrix(matrix) + "\n");
        bw.write("최대 점수 경로 = " + matrix_path());
        br.close();
        bw.close();
    }
}

