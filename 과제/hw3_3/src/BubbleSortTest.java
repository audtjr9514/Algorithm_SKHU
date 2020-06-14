/* ************************
파일명 : BubbleSortSortTest.java
작성자 : 김명석
작성일 : 20.04.03
내용 : 학생 수를 입력 받고, 각 학생의 정보들을 클래스 배열로 저장한 뒤,
       전체 학생의 수를 성적을 기준으로 하여 버블 정렬을 이용하여 내림차순으로 만든 뒤, 출력하는 프로그램
 ************************ */

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

public class BubbleSortTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("hw3_3 : 김명석");
        bw.newLine();
        bw.newLine();
        bw.write("학생수 입력 : ");
        bw.flush();
        int N = Integer.parseInt(br.readLine());
        Student[] Students = new Student[N];
        bw.newLine();
        bw.write(N + "명의 학생 정보를 입력하세요 : ");
        bw.newLine();
        bw.flush();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Students[i] = new Student(); // 학생 클래스 배열의 각 학생 클래스 생성
            Students[i].setName(st.nextToken());
            Students[i].setId(Integer.parseInt(st.nextToken()));
            Students[i].setGrade(Double.parseDouble((st.nextToken())));
        }
        Student temp;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - (i + 1); j++) {
                if (Students[j].getGrade() < Students[j + 1].getGrade()) {
                    temp = Students[j + 1];
                    Students[j + 1] = Students[j];
                    Students[j] = temp;
                }
            }
        }
        bw.newLine();
        bw.write("성적 내림차순 정렬 결과 = ");
        bw.newLine();
        for (Student s : Students) {
            bw.write(s.getName() + " " + s.getId() + " " + s.getGrade() + "\n");
        }

        br.close();
        bw.close();
    }
}
