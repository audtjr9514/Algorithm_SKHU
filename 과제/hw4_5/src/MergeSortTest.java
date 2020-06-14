/* ************************
파일명 : hw4_5.java
작성자 : 김명석
작성일 : 20.04.08
내용 : 원과 삼격형의 갯수들을 각각 입력받아 도형 배열로 만들고,
       도형의 면적을 기준으로 병합 정렬하여 출력하는 프로그램
 ************************ */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

interface Figure {
    // public abstract 생략 가능
    double getArea();
}

class Circle implements Figure {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getArea() {
        return radius * radius * 3.14;
    }

    @Override
    public String toString() {
        return "원 반지름 = " + radius + " 면적 = " + getArea();
    }
}

class Triangle implements Figure {
    private double baseLine;
    private double height;

    public Triangle(double baseLine, double height) {
        this.baseLine = baseLine;
        this.height = height;
    }

    public void setBaseLine(int baseLine) {
        this.baseLine = baseLine;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBaseLine() {
        return baseLine;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return height * baseLine / 2;
    }

    @Override
    public String toString() {
        return "삼각형 밑변 = " + baseLine + " 높이 = " + height + " 면적 = " + String.format("%,.3f", getArea());
    }
}

class FigureSortService {
    public static void mergeSort(Figure[] Figures) {
        int bottom = 0;
        int top = Figures.length - 1;
        if (bottom < top) {
            int mid = (bottom + top) / 2; // 총 원소 갯수의 중간 위치 계산
            mergeSort(Figures, bottom, mid); // 중간위치의 앞부분 정렬 (재귀)
            mergeSort(Figures, mid + 1, top); // 중간위치의 뒷부분 정렬 (재귀)
            merge(Figures, bottom, top); // 정렬이 된 2개의 부분 배열 합병
        }
    }
    // mergeSort 오버로딩
    public static void mergeSort(Figure[] Figures, int bottom, int top) {
        if (bottom < top) {
            int mid = (bottom + top) / 2;
            mergeSort(Figures, bottom, mid);
            mergeSort(Figures, mid + 1, top);
            merge(Figures, bottom, top);
        }
    }

    public static void merge(Figure[] Figures, int bottom, int top) {
        int i = bottom;
        int mid= (bottom + top) / 2;
        int j =  mid+ 1;
        int k = bottom;
        Figure[] temp = new Figure[Figures.length]; // 정렬된 배열을 넣을 임시 배열
        // 나눠져있는 두 배열을 값을 비교(정렬)하면서 임시배열에 넣는다.
        while (i <= mid  && j <= top) {
            if (Figures[i].getArea() <= Figures[j].getArea()) {
                temp[k++] = Figures[i++];
            } else {
                temp[k++] = Figures[j++];
            }
        }
        // 남아 있는 값들을 채워 넣는다.
        while (i <= mid)
            temp[k++] = Figures[i++];
        while (j <= top)
            temp[k++] = Figures[j++];
        // 임시 배열에 있는 원소를 다시 원 배열에 복사
        for (int index = bottom; index < top+1; index++)
            Figures[index] = temp[index];
    }
}

public class MergeSortTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write("hw4_5 : 김명석");
        bw.newLine();
        bw.newLine();

        // 원의 갯수 입력
        bw.write("원 갯수 입력 : ");
        bw.flush();
        int cnt_circle = Integer.parseInt(br.readLine());
        // 삼격형의 갯수 입력
        bw.write("삼각형 갯수 입력 : ");
        bw.flush();
        int cnt_tri = Integer.parseInt(br.readLine());
        // 원과 삼각형의 갯수를 합친만큼의 도형 배열 생성
        Figure[] Figures = new Figure[cnt_circle + cnt_tri];

        // 원의 갯수만큼 도형 배열을 초기화
        bw.newLine();
        bw.write(cnt_circle + "개의 원 정보(반지름)을 입력하세요 :");
        bw.newLine();
        bw.flush();
        for (int i = 0; i < cnt_circle; i++)
            Figures[i] = new Circle(Double.parseDouble(br.readLine()));

        //삼격형의 갯수만큼 도형 배열을 초기화
                bw.newLine();
        bw.write(cnt_tri + "개의 삼각형 정보(밑변과 높이)를 입력하세요 :");
        bw.newLine();
        bw.flush();
        for (int i = cnt_circle; i < cnt_circle + cnt_tri; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Figures[i] = new Triangle(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }
        bw.flush();
        FigureSortService.mergeSort(Figures);

        // 각 클래스에 오버라이딩되어있는 toString을 사용하여 배열 내에 있는 각 도형의 정보와 면적을 출력
        bw.newLine();
        bw.write("병합 정렬 결과 "+Figures.length+"개의 도형 정보와 면적 =\n");
        for (Figure f : Figures)
            bw.write(f + "\n");
        br.close();
        bw.close();
    }
}