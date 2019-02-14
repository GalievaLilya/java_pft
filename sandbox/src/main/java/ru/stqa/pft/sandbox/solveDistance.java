package ru.stqa.pft.sandbox;

public class solveDistance {

    public static void main(String[] args){
        Point p1 = new Point(2,2);
        Point p2 = new Point(2,2);
        System.out.println("Distance from A to B = " + distance(p1, p2));

    }
    //функция - именованный фрагмент кода (static- как раз показывает, что функция не ассоциирована ни с каким объектом)
    //метод - функция ассоциированная с каким-то объектом
    //любая функция не является методом, но любой метод является функцией
    public static double distance(Point p1, Point p2){
        double rez =Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
        return rez;
    }
}
