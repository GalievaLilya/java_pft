package ru.stqa.pft.sandbox;

public class PointSecond {

    public double x;
    public double y;
    public double x1;
    public double y1;
    /*конструктор класса Point*/
    public PointSecond(double x, double y, double x1, double y1){
        this.x = x; //this - лдя того чтоб могли использовать одинаковые названия переменных
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
    }

    public  double distanceSecond(){
        double rez = Math.sqrt(Math.pow((x - x1), 2) + Math.pow((y - y1), 2));
        return rez;
    }
}
