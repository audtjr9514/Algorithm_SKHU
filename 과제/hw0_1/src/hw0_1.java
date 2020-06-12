/* ************************
파일명 : hw0_1.java
작성자 : 김명석
작성일 : 20.04.03
내용 : 사용자가 입력한 정수값이 기존에 있던 각 10가지 정수값마다 얼마 가까운가에 따라 점수를 매기고
       점수가 가장 작은 때의 차이를 출력하는 프로그램
 ************************ */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class hw0_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write("hw0_1 : 김명석\n");
        bw.write("숫자 맞추기 프로그램입니다.\n");
        bw.write("하나의 정수값을 입력하세요 : ");
        bw.flush();
        int[] numbers = {-30, -15, 1, 40, 50, 200, 315, 800, 9000, 12345};
        int num = Integer.parseInt(br.readLine());
        int score = 0;
        for (int i = 0; i < 10; i++) {
            numbers[i] = 100 - (numbers[i] - num > 0 ? numbers[i] - num : num - numbers[i]) > 0 ? 100 - (numbers[i] - num > 0 ? numbers[i] - num : num - numbers[i]) : 0;
            // 입력한 값과 초기에 설정한 배열의 각 원소와의 차이를 절대값으로 만들고
            // 각 배열에 다시 원소를 넣을 때 각 절댓값이 100보다 크다면 0으로 만들고, 아니면 절댓값을 넣는다.
            if (numbers[i] > score)
                score = numbers[i];
            // 최솟값 찾아내기
        }
        bw.write("점수 = " + score);

        br.close();
        bw.close();
    }
}
