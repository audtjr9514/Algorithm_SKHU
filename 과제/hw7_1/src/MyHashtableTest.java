/* ************************
파일명 : MyHashtableTest.java
작성자 : 김명석
작성일 : 20.05.10
내용 : 학생들의 학번을 해시 테이블을 이용해 저장, 삭제, 검색(존재 여부만) 및 전체 조회하는 프로그램
 ************************ */

import java.util.Scanner;

class MyHashtable {
    private int[] arr; // 해쉬 테이블
    private int size; // 해쉬 테이블 크기
    private static final int EMPTY = -1; // 비어있는 것을 뜻함
    private static final int DELETED = -2; // 전에 데이터가 있었지만 현재는 삭제된 상태임을 뜻함

    // 해쉬테이블 생성자 _ 입력받은 숫자만큼 해쉬테이블의 크기를 설정
    public MyHashtable(int size) {
        this.size = size;
        this.arr = new int[size];
        for (int i = 0; i < size; i++)
            arr[i] = EMPTY; // 해쉬 테이블을 다 EMPTY로 초기화
    }

    // 해쉬 테이블 삽입
    public boolean put(int studentNum) {
        if (contains(studentNum))
            return false;
        int i = 0;
        int index;
        while (i != size) {
            index = h(i, studentNum); // 해쉬 모듈로 연산 _ 1차 조사법
            if (arr[index] == EMPTY || arr[index] == DELETED) {
                arr[index] = studentNum; // 만약 비어있거나 삭제된 데이터라면 삽입
                return true;
            } else i++; //해쉬 인덱스에 데이터가 있다면 i를 증가시켜 다음 단계 1차 조사법으로 충돌 해결
        }
        return false; // 만약 데이터가 꽉차있다면 삽입 실패
    }

    //해쉬 테이블 검색
    public boolean contains(int studentNum) {
        int i = 0;
        int index = h(studentNum);
        // 해쉬 테이블이 삭제상태거나 비어있다면 게속해서 1차조사법을 이용해 검색
        while (arr[index] != EMPTY && i != size) {
            index = h(i, studentNum);
            if (arr[index] == studentNum) {
                return true; // 검색 성공
            } else i++;
        }
        return false; // 만약 검색 못한채 모든 인덱스를 조사했다면 false 반환
    }

    // 해쉬 테이블 삭제 _ 검색 연산과 비슷하나 값을 찾았을 경우 DELETED로 바꾸고 true 반환
    public boolean remove(int studentNum) {
        int i = 0;
        int index = h(studentNum);
        while (i != size && arr[index] != EMPTY) {
            index = h(i, studentNum);
            if (arr[index] == studentNum) {
                arr[index] = DELETED;
                return true;
            } else i++;
        }
        return false;
    }

    // 해쉬 테이블 조회
    public void print() { //인덱스를 순차적으로 출력 _ 비어있지 않다면 DELETED나 데이터를 출력
        for (int i = 0; i < arr.length; i++) {
            System.out.print("[" + i + "] ");
            if (arr[i] != EMPTY) {
                if (arr[i] == DELETED)
                    System.out.println("DELETED");
                else
                    System.out.println(arr[i]);
            } else
                System.out.println();

        }
    }

    // 해시 함수 _ 테이블 크기로 Division 연산을 한다
    private int h(int studentNum) {
        return studentNum % size;
    }

    // 충돌이 났을 시 사용하는 1차 조사법 해시 함수
    private int h(int i, int studentNum) {
        return (studentNum + i) % size;
    }
}

public class MyHashtableTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("hw7_! : 김명석 \n\n");
        System.out.print("총 학생 수를 입력해 주세요 : ");
        MyHashtable Students = new MyHashtable(scan.nextInt());

        loop:
        while (true) {
            System.out.print("메뉴를 선택해주세요 [ 1: 삽입 / 2 : 검색 / 3 : 삭제 / 4 : 테이블 조회 / 5 : 종료 ] : ");
            int select = scan.nextInt();
            int studentNum;
            switch (select) {
                // 삽입
                case 1:
                    System.out.print("삽입할 학번을 입력해주세요 : ");
                    studentNum = scan.nextInt();
                    if (Students.put(studentNum))
                        System.out.println(studentNum + "의 삽입을 성공했습니다!");
                    else
                        System.out.println(studentNum + "의 삽입을 실패했습니다!");
                    System.out.println();
                    break;
                // 검색
                case 2:
                    System.out.print("검색할 학번을 입력해주세요 : ");
                    studentNum = scan.nextInt();
                    if (Students.contains(studentNum))
                        System.out.println(studentNum + "은 존재하는 학번입니다!");
                    else
                        System.out.println(studentNum + "은 존재하지 않는 학번입니다!");
                    System.out.println();
                    break;
                // 삭제
                case 3:
                    System.out.print("삭제할 학번을 입력해주세요 : ");
                    studentNum = scan.nextInt();
                    if (Students.remove(studentNum))
                        System.out.println(studentNum + "번의 삭제를 성공했습니다!");
                    else
                        System.out.println(studentNum + "번의 삭제를 실패했습니다!");
                    System.out.println();
                    break;
                // 테이블 출력
                case 4:
                    System.out.println("해시테이블 내용 :");
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
