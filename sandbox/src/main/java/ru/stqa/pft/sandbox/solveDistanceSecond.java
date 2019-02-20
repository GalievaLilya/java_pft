package ru.stqa.pft.sandbox;

public class solveDistanceSecond {

    public static void main(String[] args){
        PointSecond p = new PointSecond(6,1);
        PointSecond p2 = new PointSecond(5,1);
        System.out.println("Distance from A to B = " + p.distanceSecond(p2));

    }

}
