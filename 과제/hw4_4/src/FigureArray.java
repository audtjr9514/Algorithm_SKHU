/* ************************
파일명 : FigureArray.java
작성자 : 김명석
작성일 : 20.04.08
내용 : 원과 삼격형의 갯수들을 각각 입력받아 도형 배열로 만들고,
       도형 내의 각 정보(반지름 또는 밑변, 높이)와 면적을 계산하여 출력하는 프로그램
 ************************ */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

interface Figure {
    public abstract double getArea();
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

public class FigureArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write("hw4_4 : 김명석");
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

        //삼각형의 갯수만큼 도형 배열을 초기화
        bw.newLine();
        bw.write(cnt_tri + "개의 삼각형 정보(밑변과 높이)를 입력하세요 :");
        bw.newLine();
        bw.flush();
        for (int i = cnt_circle; i < cnt_circle + cnt_tri; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Figures[i] = new Triangle(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }

        // 각 클래스에 오버라이딩되어있는 toString을 사용하여 배열 내에 있는 각 도형의 정보와 면적을 출력
        bw.newLine();
        for (Figure f : Figures)
            bw.write(String.valueOf(f) + "\n");
        br.close();
        bw.close();
    }
}