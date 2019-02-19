package ru.stqa.pft.sandbox;

public class PointSecond {

    public double x;
    public double y;

    /*конструктор класса Point*/
    public PointSecond(double x, double y){
        this.x = x; //this - лдя того чтоб могли использовать одинаковые названия переменных
        this.y = y;
    }

    public  double distanceSecond(double x, double y){
        return Math.sqrt(Math.pow((x - this.x), 2) + Math.pow((y - this.y), 2));
    }
}
