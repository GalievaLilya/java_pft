package ru.stqa.pft.sandbox;

public class solveDistanceSecond {

    public static void main(String[] args){
        PointSecond p = new PointSecond(1,1, 2, 3);
        System.out.println("Distance from A to B = " + p.distance());

        PointSecond p1 = new PointSecond(10,5, 25, 10);
        System.out.println("Distance from C to D = " + p1.distance());

    }

}
