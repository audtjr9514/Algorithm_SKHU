/* ************************
파일명 : MyHashtableOptionTest.java
작성자 : 김명석
작성일 : 20.05.10
내용 : 학생들의 학번과 점수를 해시 테이블을 이용해 저장, 삭제, 검색(존재 여부만) 및 전체 조회하는 프로그램
 ************************ */

import java.util.Scanner;

class MyHashtable {
    private Info[] arr; // 해쉬 인덱스 배열
    private int size;
    private static final int EMPTY = -1;

    // 학번과 점수, 체이닝을 위해 다음을 가리킬 link를 변수로 갖고 있는 Info(정보) 클래스
    private class Info {
        int studentNum;
        int grade;
        Info link;

        public Info() {
            this.studentNum = EMPTY;
            this.grade = EMPTY;
            this.link = null;
        }

        public Info(int studentNum, int grade) {
            this.studentNum = studentNum;
            this.grade = grade;
            this.link = null;
        }

        public int getStudentNum() {
            return studentNum;
        }

        public void setStudentNum(int studentNum) {
            this.studentNum = studentNum;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public Info getLink() {
            return link;
        }

        public void setLink(Info link) {
            this.link = link;
        }
    }

    // 해쉬 테이블 생성자
    public MyHashtable(int size) {
        this.size = size;
        this.arr = new Info[size];

        for (int i = 0; i < size; i++) {
            arr[i] = new Info(EMPTY, EMPTY);
        }
    }

    // 해쉬 테이블 (학번, 점수) 삽입
    public boolean put(int studentNum, int grade) {
        int index = h(studentNum);
        Info InsertInfo = new Info(studentNum, grade);
        if (search(studentNum) == null) { // 만약 지금 받은 학번의 인덱스에서 현재 입력받은 학번 값이 없다면 해당 인덱스의 맨앞에 삽입
            InsertInfo.setLink(arr[index].getLink());
            arr[index].setLink(InsertInfo);
        } else { // 인덱스가 비어있지 않다면 해당 학번의 점수를 변경
            Info temp = new Info(); // 삽입할 위치를 지정해주는 temp Info
            temp.setLink(arr[index]);
            while (true) {
                if (temp.getLink().getStudentNum() == studentNum) {
                    temp.getLink().setGrade(grade);
                    break;
                } else
                    temp.setLink(temp.getLink().getLink());
            }
        }
        return true;
    }

    // 해쉬 테이블 검색
    public int get(int studentNum) {
        if (search(studentNum) == null) // 만약 찾는 값이 없다면 비어 있는 값(-1) 반환
            return EMPTY;
        else {
            int index = h(studentNum);
            Info temp = arr[index]; // 입력받은 학번의 인덱스 체이닝을 순서대로 조사하기 위한 temp
            while (true) {
                if (temp.getStudentNum() == studentNum) { // 위에서 검사를 하였기 때문에 무조건 반환
                    return temp.getGrade();
                } else {
                    if (temp.getLink() != null) // 계속해서 체이닝 조사 위해 temp를 줄여간다.
                        temp = temp.getLink();
                    else {
                        return EMPTY;
                    }
                }
            }
        }
    }

    // 해쉬 테이블 삭제
    public boolean remove(int studentNum) {
        int index = h(studentNum);
        if (search(studentNum) == null)
            return false; // 내가 찾는 값이 없다면 실패
        else {
            Info temp = new Info();
            temp.setLink(arr[index]); // 삭제할 위치를 찾기 위한 temp
            while (true) {
                // 만약 찾았다면 삭제를 한뒤 그 뒤의 데이터를 다시 잇는다.
                if (temp.getLink().getLink().getStudentNum() == studentNum) {
                    temp.getLink().setLink(search(studentNum).getLink());
                    break;
                } else { // 못찾았다면 그 다음을 검사하기 위해 temp가 가리키는 노드를 다음으로 이동
                    Info next_chk = temp.getLink();
                    temp.setLink(next_chk.getLink());
                }
            }
        }
        return true;
    }

    // 해쉬 테이블 전체 조회
    public void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("[" + i + "] ");
            if (arr[i].getLink() != null) {
                System.out.print("-> ");
                Info temp = arr[i].getLink();
                while (true) {
                    System.out.print(temp.getStudentNum() == EMPTY ? "" : "[" + temp.getStudentNum() + " " + temp.getGrade() + "]");
                    if (temp.getLink() != null) {
                        temp = temp.getLink();
                        System.out.print(" -> ");
                    } else {
                        break;
                    }
                }
            }
            System.out.println();
        }
    }

    // 해쉬 테이블에서 학번에 맞는 데이터를 출력하는 메소드
    // contains는 점수를 반환하지만 search는 아예 노드 자체를 반환
    private Info search(int studentNum) {
        int index = h(studentNum);
        Info search = arr[index];
        while (search.getLink() != null) {
            if (search.getLink().getStudentNum() == studentNum)
                return search.getLink();
            else
                search = search.getLink();
        }
        return search.getLink();
    }

    // 해쉬 함수
    private int h(int studentNum) {
        return studentNum % size;
    }
}

public class MyHashtableOptionTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("hw7_2 : 김명석 \n\n");
        System.out.print("총 학생 수를 입력해 주세요 : ");
        MyHashtable Students = new MyHashtable(scan.nextInt());

        loop:
        while (true) {
            System.out.print("메뉴를 선택해주세요 [ 1: 삽입 / 2 : 검색 / 3 : 삭제 / 4 : 테이블 조회 / 5 : 종료 ] : ");
            int select = scan.nextInt();
            int studentNum;
            int grade;
            switch (select) {
                // 삽입
                case 1:
                    System.out.print("삽입할 학번을 입력해주세요 : ");
                    studentNum = scan.nextInt();
                    System.out.print("점수를 입력해주세요 : ");
                    grade = scan.nextInt();
                    if (Students.put(studentNum, grade))
                        System.out.println(studentNum + "의 삽입을 성공했습니다!");
                    else
                        System.out.println(studentNum + "의 삽입을 실패했습니다!");
                    System.out.println();
                    break;
                // 검색
                case 2:
                    System.out.print("검색할 학번을 입력해주세요 : ");
                    studentNum = scan.nextInt();
                    if (Students.get(studentNum) < 0)
                        System.out.println(studentNum + "학번은 존재하지 않습니다.");
                    else
                        System.out.println(studentNum + "번 학생의 점수는 " + Students.get(studentNum) + "점입니다.");
                    System.out.println();
                    break;
                // 삭제
                case 3:
                    System.out.print("삭제할 학번을 입력해주세요 : ");
                    studentNum = scan.nextInt();
                    if (Students.remove(studentNum))
                        System.out.println(studentNum + "번 학생을 삭제했습니다!");
                    else
                        System.out.println(studentNum + "번 학생의 삭제를 실패했습니다!");
                    System.out.println();
                    break;
                // 테이블 출력
                case 4:
                    System.out.println("해시 테이블 내용 :");
                    Students.print();
                    System.out.println();
                    break;
                case 5:
                    System.out.print("종료합니다.");
                    break loop;
            }
        }
    }
}
