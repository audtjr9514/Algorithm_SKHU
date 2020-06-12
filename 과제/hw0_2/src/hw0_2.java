/* **************************
 파일명: hw0_2.java
 작성자: 정보통신공학과 201433006 김명석
 작성일: 2020.04.01
 내용:  두 개의 배열을 각각 입력(음수가 입력되거나 100개가 넘어갈 시 입력 중단)하여
        두 배열의 원소들이 같은지 확인하는 프로그램
 ************************** */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class hw0_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr1 = new int[100];
        int[] arr2 = new int[100];
        StringTokenizer st;

        bw.write("hw0_2 : 김명석\n");
        bw.write("양의 정수 집합을 비교하는 프로그램입니다.\n");
        bw.newLine();
        bw.write("첫번째 집합을 입력하세요. 집합 입력을 마치려면 양이 아닌 정수를 입력하세요 : ");
        bw.flush();
        // 두 개의 배열을 StringTokenizer를 통해 입력받기
        // 만약 입력 갯수가 100개를 넘는다면 100까지만 입력받고 추가로 입력된 것은 무시한다.
        st = new StringTokenizer(br.readLine());
        int i = 0;
        int num;
        while (st.hasMoreTokens()) {
            num = Integer.parseInt(st.nextToken());
            if (num > 0 && i < 100) {
                arr1[i] = num;
                i++;
            } else
                break;
        }
        bw.write("두번째 집합을 입력하세요. 집합 입력을 마치려면 양이 아닌 정수를 입력하세요 : ");
        bw.flush();
        st = new StringTokenizer(br.readLine());
        int s = 0;
        while (st.hasMoreTokens()) {
            num = Integer.parseInt(st.nextToken());
            if (num > 0 && s < 100) {
                arr2[i] = num;
                s++;
            } else
                break;
        }
        // 두 개의 배열을 비교하기 위한 정렬
        for (int j = 0; j < i; j++) {
            for (int z = 0; z < j; z++) {
                if (arr1[j] < arr1[z]) {
                    int temp = arr1[z];
                    arr1[z] = arr1[j];
                    arr1[j] = temp;
                }
                if (arr2[j] < arr2[z]) {
                    int temp = arr2[z];
                    arr2[z] = arr2[j];
                    arr2[j] = temp;
                }
            }
        }
        //배열의 원소를 하나씩 비교
        int chk = 0;
        while (chk <= i || chk <= s) {
            if (arr1[chk] != arr2[chk] && i != s) {
                bw.write("두 집합은 서로 다릅니다.");
                break;
                //만약 하나라도 틀리면 반복문 중단하고 다르다고 출력
            }
            chk++;
        }
        // 만약 끝까지 반복문 중단없이 진행되었다면 두 배열은 같다.
        if (chk == i)
            bw.write("두 집합은 같습니다.");
        else
            bw.write("두 집합은 서로 다릅니다.");

        br.close();
        bw.close();
    }
}
