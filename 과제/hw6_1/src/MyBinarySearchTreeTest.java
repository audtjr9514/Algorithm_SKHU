/* ************************
파일명 : MyBinarySearchTreeTest.java
작성자 : 김명석
작성일 : 20.04.27
내용 : 이진 검색 트리에 중복되지 않는 ID값(중복 불가)을 삽입 삭제, 연산, 전체조회 하는 프로그램
       (기존에 계속 Buffer를 이용한 입출력을 사용하였으나, 이번에는 Scanner를 이용하였습니다.)
 ************************ */

import java.util.Scanner;

class MyBinarySearchTree {
    private Node root = null;   // 맨 처음 만들어질 트리의 루트 노드
    private int total = 0;      // 트리의 원소 개수를 저장하는 total 변수

    // 노드 구조를 정해놓은 노드 클래스
    private class Node {
        String Id;  // ID
        Node left;  // 왼쪽 자식 노드
        Node right; // 오른쪽 자식 노드
    }

    // 트리 내의 원소를 모두 출력해주는 print 함수
    public void print() {
        // 키가 하나라도 존재 한다면 총 몇개의 노드가 있는지와 노드를 중위 순회하여 값을 출력
        if (total > 0) {
            System.out.print("현재 저장된 ID의 갯수는 " + size() + " 개이고, \n");
            inorder(root);
            System.out.println("가 저장되어 있습니다!\n");
        } else // total이 0인 경우는 아직 아무것도 삽입이 되어있지 않는 상태
            System.out.println("아무것도 저장되지 않았습니다!\n");
    }

    // 트리 내 원소의 갯수를 반환하는 size 함수
    public int size() {
        return total; // 키의 개수가 저장되어 있는 total 반환
    }

    // 트리 내 입력받은 원소가 존재하는지의 여부를 알려주는 contains 함수
    public boolean contains(String id) {
        if (root == null) return false;
        // 만약 아무것도 들어있지 않다면(root가 null)이라면 검색 실패
        Node search = root; // 탐색용 search 노드에 루트노드를 복사
        while (id.compareTo(search.Id) != 0) { // 만약 입력한 id와 같은 ID가 나온다면 반복문 종료
            if (id.compareTo(search.Id) < 0) { // 만약 입력한 id가 search ID 보다 앞에 있는 값이라면 왼쪽 노드 탐색
                search = search.left;
            } else {
                search = search.right; // // 만약 입력한 id가 search ID 보다 뒤에 있는 값이라면 왼쪽 노드 탐색
            }
            if (search == null)
                return false; // 탐색용 노드가 null 이라면
        }
        return true; // 탐색 성공
    }

    // 트리에 id(노드)를 삽입하는 함수 _ 이미 존재하는 값이라면 삽입 실패
    public boolean add(String id) {
        if (contains(id)) return false; // 만약 값이 존재한다면 삽입 실패
        Node newNode = new Node();      // 새로 삽입할 노드 생성
        newNode.Id = id;                // 삽입할 노드의 Id 설정
        if (root == null) {
            root = newNode;             // 트리가 비어있으면 root 에 삽입
            total++;                    // 삽입 성공시 전체 노드 갯수 1 추가
            return true;
        } else {
            Node search = root;         // 삽입할 위치를 찾아낼 search 노드
            Node chk;                   // search 노드를 반복을 이용해자식노드로 계속해서 이동시켜줄 chk 노드
            while (true) {
                chk = search; //  이동
                if (id.compareTo(chk.Id) < 0) { // 삽입할 노드의 Id 가 만약 search의 Id보다 작다면
                    search = chk.left;          //  search를 왼쪽 노드로
                    if (search == null) {       //  왼쪽 노드가 비어있다면
                        chk.left = newNode;
                        total++;
                        return true;            //  왼쪽 노드에 삽입
                    }
                } else {                        // 삽입할 노드의 Id가 만약 search의 Id보다 크다면
                    search = chk.right;         // 오른쪽으로 이동
                    if (search == null) {       // 오른쪽 노드가 비어있으면
                        chk.right = newNode;
                        total++;
                        return true;            // 오른쪽 노드에 삽입
                    }
                }

            }
        }
    }

    public boolean remove(String id) {
        return false;
    }

    // 트리를 중위 순회하여 키값을 출력하는 함수 (각 노드의 ID의 123,알파벳,가나다 순)
    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.Id + " ");
            inorder(node.right);
        }
    }
}

public class MyBinarySearchTreeTest {
    public static void main(String[] args) {
        System.out.println("hw6_1 : 김명석");
        Scanner scan = new Scanner(System.in);
        MyBinarySearchTree MBST = new MyBinarySearchTree();
        loop:
        while (true) {
            System.out.print("메뉴를 선택해주세요 [ 1 (삽입) / 2 (검색) / 3 (삭제) / 4 (전체조회) / 5 (종료) ] : ");
            int input = scan.nextInt();
            String id;
            switch (input) {
                // 삽입 연산
                case 1:
                    System.out.print("삽입할 ID를 입력해주세요 : ");
                    id = scan.next();
                    if (MBST.add(id))
                        System.out.println(id + "의 삽입을 성공했습니다!\n");
                    else
                        System.out.println(id + "의 삽입을 실패하였습니다!\n");
                    break;
                // 검색 연산
                case 2:
                    System.out.print("검색할 ID를 입력해주세요 : ");
                    id = scan.next();
                    if (MBST.contains(id))
                        System.out.println(id + "는 존재하는 값입니다.\n");
                    else
                        System.out.println(id + "는 존재하지 않는 값입니다.\n");
                    break;
                //식제 연산
                case 3:
                    System.out.print("삭제할 ID를 입력해주세요 : ");
                    id = scan.next();
                    if (MBST.remove(id))
                        System.out.println(id + "의 삭제를 성공했습니다!\n");
                    else
                        System.out.println(id + "의 삭제를 실패했습니다!\n");
                    break;
                // 전체 조회 연산
                case 4:
                    MBST.print();
                    break;
                // 종료
                case 5:
                    System.out.print("연산을 종료합니다.");
                    break loop;
            }
        }
    }
}