/* ************************
작성자 : 김명석
작성일 : 20.06.05
내용 : 0 ~ (n-1) 까지의 정점에서 사용자가 간선을 입력받고 방향 그래프를 인접리스트로 만들어 출력한 뒤,
       그래프를 위상정렬한 상태로 한번 더 출력하는 프로그램
 ************************ */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class DirectedGraph {
    private Node[] list; // 인접리스트를 가리키는 노드 리스트
    private int numberOfVertices; // 정점수
    private boolean[] visited; // 위상정렬할시 방문을 체크할때 필요한 정점수 만큼의 visited 배열
    private Node topologicalList; // 위상정렬한 내용이 저장될 연결리스트

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
        if (v1 >= 0 && v1 < numberOfVertices && v2 >= 0 && v2 < numberOfVertices)
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

    // 현재 각 정점마다 가리키고 있는 간선들을 출력하고 위상정렬하여 한번 더 출력하는 메소드
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
        System.out.println();

        TopologicalSortMethod(); // 위상정렬 메소드
        // 위상정렬 결과 출력
        System.out.print("위상정렬 결과 : ");
        Node temp = new Node();
        temp.setLink(topologicalList.getLink());
        while (temp.getLink() != null) {
            System.out.print(temp.getLink().getVertex() + " ");
            temp.setLink(temp.getLink().getLink());
        }
    }

    // 위상정렬 메소드
    public void TopologicalSortMethod() {
        visited = new boolean[numberOfVertices];
        topologicalList = new Node();
        for (int i = 0; i < numberOfVertices; i++)
            visited[i] = false; // 먼저 정점 방문 배열을 false로 초기화
        for (int i = 0; i < numberOfVertices; i++) // 0번 정점부터 dfs를 실행
            if (visited[i] == false)
                dfs(i);
    }

    // 깊이 우선 탐색 메소드
    public void dfs(int v) {
        visited[v] = true; // v 정점을 방문한 것을 체크
        Node tempNode = list[v];
        // 연결된 간선이 없을때까지 dfs를 재귀 호출하여 깊이를 우선적으로 탐색한다.
        while (tempNode.getLink() != null) {
            if (visited[tempNode.getLink().getVertex()] == false)
                dfs(tempNode.getLink().getVertex());
            tempNode.setLink(tempNode.getLink().getLink());
        }
        // 깊이 우선 탐색한 결과를 연결 리스트로 저장 _ 연결리스트 자체가 위상정렬 결과가 된다.
        Node insertNode = new Node();
        insertNode.setVertex(v);
        if (topologicalList.getLink() == null)
            topologicalList.setLink(insertNode);
        else {
            insertNode.setLink(topologicalList.getLink());
            topologicalList.setLink(insertNode);
        }

    }
}

public class TopologicalSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        System.out.println("hw10_2 : 김명석\n");
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
