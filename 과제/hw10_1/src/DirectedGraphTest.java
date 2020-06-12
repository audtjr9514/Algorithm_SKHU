/* ************************
파일명 : DirectedGraphTest.java
작성자 : 김명석
작성일 : 20.06.04
내용 : 0 ~ (n-1) 까지의 정점에서 사용자가 간선을 입력받고 방향 그래프를 인접리스트로 만들어 출력하는 프로그램
 ************************ */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class DirectedGraph {
    private Node[] list; // 인접리스트를 가리키는 노드 리스트
    private int numberOfVertices; // 정점수

    // 노드 클래스
    private class Node {
        int vertex; // 정점
        Node link; // 방향 간선

        // 생성자
        Node() {
            link = null;
        }

        public int getVertex() {
            return vertex;
        }

        public Node getLink() {
            return link;
        }

        public void setLink(Node link) {
            this.link = link;
        }

        public void setVertex(int vertex) {
            this.vertex = vertex;
        }
    }

    // 생성자
    public DirectedGraph(int n) {
        list = new Node[n];
        for (int i = 0; i < n; i++) {
            list[i] = new Node();
            list[i].setVertex(i);
        }
        numberOfVertices = n;
        // 정점 수만큼 인접 리스트 배열을 생성 및 0~n-1 만큼 초기화
    }

    // 두 정점을 입력받아 간선 그래프를 추가하는 간선 추가 메소드
    public void addEdge(int v1, int v2) {
        // 만약 입력받은 정점들이 둘 중 하나라도 없는 정점이라면 입력 불가
        if (v1 >= 0 && v2 < numberOfVertices && v1 >= 0 && v2 < numberOfVertices)
            if (list[v1].getLink() == null) {
                Node insertNode = new Node();
                insertNode.setVertex(v2);
                list[v1].setLink(insertNode);
            } else {
                Node insertNode = new Node();
                insertNode.setVertex(v2);
                insertNode.setLink(list[v1].getLink());
                list[v1].setLink(insertNode);
            }
        else
            System.out.println("간선 삽입 오류 -  잘못된 정점 번호 입니다. <" + v1 + "," + v2 + ">");
    }

    // 현재 각 정점마다 가리키고 있는 간선들을 출력하는 메소드
    public void printAdjacencyList() {
        for (int i = 0; i < list.length; i++) {
            System.out.print("정점 " + i + "에 인접한 정점 = ");
            if (list[i].link == null) {
                System.out.println();
            } else {
                Node temp = new Node();
                temp.setLink(list[i].getLink());
                while (temp.getLink() != null) {
                    System.out.print(temp.getLink().getVertex() + " ");
                    temp.setLink(temp.getLink().getLink());
                }
                System.out.println();
            }
        }
    }
}

public class DirectedGraphTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        System.out.println("hw10_1 : 김명석\n");
        System.out.print("정점 수 입력 : ");
        int n = Integer.parseInt(br.readLine());
        DirectedGraph graph = new DirectedGraph(n);
        // 정점수를 입력하고 정점수 만큼의 인접리스트 생성
        System.out.print("간선 수 입력 : ");
        int e = Integer.parseInt(br.readLine());
        // 정점 간 존재하는 간선 수 입력
        System.out.println();

        System.out.println(e + "개의 간선 입력 ( 각 간선은 정점 번호 2개를 whitespace로 구분하여 입력) : ");
        for (int i = 0; i < e; i++) {
            System.out.print("간선" + (i + 1) + " 입력 : ");
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph.addEdge(v1, v2);
        }
        System.out.println();
        graph.printAdjacencyList();
        br.close();
    }
}
