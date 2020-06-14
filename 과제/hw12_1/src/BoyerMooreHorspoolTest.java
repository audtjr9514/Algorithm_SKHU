/* ************************
파일명 : BoyerMooreHorspoolTest.java
작성자 : 김명석
작성일 : 20.06.18
내용 : 보이어 무어 호스풀 알고리즘을 이용하여, 텍스트 중에 사용자가 원하는 패턴이 몇번 인덱스부터 매칭이 되는지와
       점프를 몇번 하는지 출력하는 프로그램
 ************************ */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BoyerMooreHorspoolTest {
    static int jumpCount = 0; // 점프하는 횟수를 기록하기 위한 jumpCount 변수
    static StringBuilder MatchIndex; // 매칭되는 인덱스를 기록하기 위한 MatchIndex 변수
    static Map<Character, Integer> jumpTable; // 패턴 점프테이블로 만들기 위한 Map 변수

    // 점프 테이블을 만들기 위한 computJump 메소드 _ 패턴의 길이가 m이면 시간복잡도 세타m
    static void computeJump(char[] pattern) {
        jumpTable = new HashMap<>();
        // 패턴의 길이만큼 반복하며 입력
        for (int i = 0; i < pattern.length; i++) {
            // 마지막 배열의 원소를 만나기 전까지는 키, 밸류 - 알파벳, 점프숫자로 입력
            // 만약 같은 문자 중복 시 뒤에 있는 숫자로 초기화
            if (i < pattern.length - 1)
                jumpTable.put(pattern[i], pattern.length - i - 1);
            else {
                // 마지막 배열의 원소 검색시 만약 기존에 중복된 알파벳이 있었다면 바꾸지 않는다.
                if (!jumpTable.containsKey(pattern[i]))
                    jumpTable.put(pattern[i], pattern.length);
            }
        }
    }

    // 패턴과 텍스트를 입력받고 매칭되는 인덱스를 찾아내는 보이어-무어-호스풀 메소드
    static void BoyerMooreHorspool(char[] text, char[] pattern) {
        computeJump(pattern); // 점프테이블 만들기
        int n = text.length; // 텍스트의 길이
        int m = pattern.length; // 패턴의 길이
        int i = 0; // 점프를 체크 하기 위한 변수
        // 배열이므로 i = 0 에서 시작한다.
        while (i <= n - m) { // i가 0부터 시작하므로 -1을 빼고 검사한다.
            int j = m - 1; // 패턴 배열의 인덱스를 검사하기 위한 j 또한 -1을 해준다.
            int k = i + m - 1; // 텍스트 배열의 인덱스를 검사하기 위한 k 변수
            while (j >= 0 && pattern[j] == text[k]) { // 매칭이 되는지를 찾는 반복문
                j--;
                k--;
            }
            if (j == -1) //만약 매칭이 되었다면 매칭이 된 인덱스를 체킹
                MatchIndex.append(i + " ");
            // 점프테이블에서 텍스트의 문자로 얼마나 점프할지 정보를 얻어내기
            if (jumpTable.get(text[i + m - 1]) != null) { // 만약 점프 테이블에 없는 키 값이라면 기타 문자
                i += jumpTable.get(text[i + m - 1]);
            } else
                i = i + m;
            // 만약 점핑을 했을 때 텍스트의 길이를 넘지 않는다면 점핑
//            if (i <= n - m)
                jumpCount++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("hw12_1 : 김명석\n\n");
        // 패턴 입력 _ 입력 받은 String(br.readLine())을 charAt 함수를 이용해 한글자씩 char 배열 pattern에 초기화
        bw.write("패턴 입력 : ");
        bw.flush();
        String input_p = br.readLine();
        char[] pattern = new char[input_p.length()];
        for (int i = 0; i < pattern.length; i++)
            pattern[i] = input_p.charAt(i);
        // 텍스트 입력 _ 패턴 입력 방식과 동일
        bw.write("텍스트 입력 : ");
        bw.flush();
        String input_t = br.readLine();
        char[] text = new char[input_t.length()];
        for (int i = 0; i < text.length; i++)
            text[i] = input_t.charAt(i);
        // 매칭되는 인덱스가 입력 될 MatchIndex 생성
        MatchIndex = new StringBuilder();
        // 매칭되는 패턴을 찾는 보이어-무어-호스풀 함수 호출
        BoyerMooreHorspool(text, pattern);
        bw.write("\n매칭 위치 = " + MatchIndex + "\n");
        bw.write("점프 수 = " + jumpCount);

        br.close();
        bw.close();
    }
}
