/* **************************
 파일명: hw3_1.java
 작성자: 정보통신공학과 201433006 김명석
 작성일: 2020.04.03
 내용: 학생 수를 입력 받고, 각 학생의 정보들을 클래스 배열로 저장한 뒤,
       전체 학생의 성적 평균과 최고점(수석) 학생의 성명을 출력하는 프로그램
 ************************** */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

// 학생 클래스 정의
class Student {
    private String name; // 학생의 이름
    private int id;      // 학생의 학번
    private double grade;// 학생의 성적

    // 학생의 이름 Setter
    public void setName(String name) {
        this.name = name;
    }

    // 학생의 학번 Setter
    public void setId(int id) {
        this.id = id;
    }

    // 학생의 성적 Setter
    public void setGrade(double grade) {
        this.grade = grade;
    }

    //학생의 이름 Getter
    public String getName() {
        return name;
    }

    // 학생의 학번 Getter
    public int getId() {
        return id;
    }

    // 학생의 성적 Getter
    public double getGrade() {
        return grade;
    }
}

public class hw3_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("hw3_1: 김명석");
        bw.newLine();
        bw.newLine();
        bw.write("학생수 입력 : ");
        bw.flush();
        int N = Integer.parseInt(br.readLine()); // 변수 N은 입력될 학생 수
        bw.newLine();
        bw.write(N + "명의 학생 정보를 입력하세요 : ");
        bw.newLine();
        bw.flush();
        Student[] Students = new Student[N]; // 학생 클래스로 배열 생성
        StringTokenizer st;
        double sum = 0; // 학생들의 성적의 합을 담을 sum 변수
        double max = 0; // 학생들의 성적중 최고 점을 비교해줄 max 변수
        String maxName = ""; // 학생들의 성적중 최고점인 학생의 이름을 담을 maxName 변수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Students[i] = new Student(); // 학생 클래스 배열의 각 학생 클래스 생성
            Students[i].setName(st.nextToken());
            Students[i].setId(Integer.parseInt(st.nextToken()));
            Students[i].setGrade(Double.parseDouble((st.nextToken())));
            // 학생 클래스의 클래스 변수를 Setter를 통해 입력
            sum += Students[i].getGrade(); // 입력받은 성적을 sum 변수에 합한다.
            // max 변수를 비교하여 입력받는 학생들 중 가장 높은 점수의 학생의 점수와 이름을 max와 maxName에 담는다.
            if (max < Students[i].getGrade()) {
                max = Students[i].getGrade();
                maxName = Students[i].getName();
            }
        }
        bw.newLine();
        bw.write("성적 평균 = " + (sum / N));
        bw.newLine();
        bw.newLine();
        bw.write("수석 학생 성명 = " + maxName);

        br.close();
        bw.close();
    }
}
