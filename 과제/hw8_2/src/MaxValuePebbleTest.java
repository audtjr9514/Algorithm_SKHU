/* ************************
파일명 : MaxValuePebbleTest.java
작성자 : 김명석
작성일 : 20.05.23
내용 : 3 x n의 조약돌을 놓았을 때 갖는 값이 있는 행렬이 있을 때, 각 조약돌의 패턴에 따라 놓았을때의 최대값을 구하는 프로그램
 ************************ */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class MaxValuePebbleTest {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 조약돌을 놓을 때 최대값을 구하는 동적계획법 함수
    public static int pebbleMax(int pebble[][]) throws IOException {
        int[][] dp = new int[4][pebble[0].length];
        // 조약돌을 놓아가면서 저장되는 값을 저장하는 dp 배열
        for (int p = 0; p < 4; p++) // 맨 처음 시작하기전 1열에 기존 행렬의 값을 저장(맨 첫열)
            if (p == 3) // 4열은 1열과 3열의 값을 저장한다.
                dp[p][0] = pebble[0][0] + pebble[2][0];
            else
                dp[p][0] = pebble[p][0];
        // 행의 갯수 -1만큼 반복하면서 전 값과 비교하여 계산
        for (int i = 1; i < pebble[0].length; i++) {
            for (int p = 0; p < 4; p++) {
                switch (p) { //
                    case 0: // 1행일 때는 직전 열의 2행과 3행 값 중 큰 값을 더한다.
                        dp[0][i] = pebble[0][i] + Math.max(dp[1][i - 1], dp[2][i - 1]);
                        break;
                    case 1: // 2행일 때는 직전 열의 1행과 3행, 4행(전 열의 1,3 행을 더한 값) 중 큰 값을 더한다.
                        int max = dp[0][i - 1];
                        if (dp[2][i - 1] > max)
                            max = dp[2][i - 1];
                        if (dp[3][i - 1] > max)
                            max = dp[3][i - 1];
                        dp[1][i] = pebble[1][i] + max;
                        break;
                    case 2: // 3행일 때는 직전 열의 1행과 2행 값 중 큰 값을 더한다.
                        dp[2][i] = pebble[2][i] + Math.max(dp[0][i - 1], dp[1][i - 1]);
                        break;
                    case 3:// 4행(현재 행의 1열과 3열을 더한 값)일 때는 직전 열의 2행 값을 더한다.
                        dp[3][i] = pebble[0][i] + pebble[2][i] + dp[1][i - 1];
                        break;
                }
            }
        }
        // 계사된 값이 저장된 dp 배열 출력
        bw.write("동적 계획법으로 조약돌 놓기 : \n");
        for (int[] row : dp) {
            for (int col : row)
                bw.write(col + " ");
            bw.newLine();
        }
        bw.newLine();
        // 마지막 계산된 값에서 최대값 출력
        int max = dp[0][pebble[0].length - 1];
        if (max < dp[1][pebble[0].length - 1])
            max = dp[1][pebble[0].length - 1];
        if (max < dp[2][pebble[0].length - 1])
            max = dp[2][pebble[0].length - 1];
        if (max < dp[3][pebble[0].length - 1])
            max = dp[3][pebble[0].length - 1];
        return max;
    }

    public static void main(String[] args) throws IOException {
        bw.write("hw8_2 : 김명석\n");
        bw.newLine();
        bw.write("현재 테이블의 상태 : \n");

        int[][] pebble = {
                {5, 3, -20, 10, 10},
                {2, 5, 10, 5, -5},
                {10, 6, 1, -10, 5}};
        for (int[] row : pebble) {
            for (int col : row)
                bw.write(col + " ");
            bw.newLine();
        }
        bw.newLine();
        bw.write("조약돌 놓기 최대값 : " + pebbleMax(pebble));

        br.close();
        bw.close();
    }
}
