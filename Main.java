package beziecurve;

import java.util.*;

class Point
{
    double x;
    double y;

    Point () {}
    Point(double x, double y)
    {
        this.x=x;
        this.y=y;
    }

    void addTo(Point a) 
    {
        this.x+=a.x;
        this.y+=a.y;
    }
}

class Curve
{
    static int n; //nr puncte
    Point[] points;

    static long factorial (int nr) {
        if (nr==0) return 1;
        else return (factorial(nr-1)*(nr));
    }

    static long combinari (int nr, int k) {
        return factorial(nr)/(factorial(nr-k)*factorial(k));
    }

    static Point Bernstein (int n, double t, int i, Point punct ) 
    { //i=contor
   
       double aux=combinari(n,i)*Math.pow(1-t,n-i)*Math.pow(t,i);
       punct.x*=aux;
       punct.y*=aux;
       return punct;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        int n=5;

//        Point[] points=new Point[n];
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(10, 15));
        points.add(new Point(20, 25));
        points.add(new Point(30, 35));
        points.add(new Point(40, 45));
        points.add(new Point(50, 55));
        double t=0;
        double sx=0;
        double sy=0;
        int aux;

        Point finale=new Point(0,0);
        for (t=0;t<=1;t+=0.01) 
        {
            for (int i=0;i<n;i++)
            {
                finale.addTo(Curve.Bernstein(n,t,i, points.get(i)));
            }
        System.out.println(finale.x + " " + finale.y);
        }
    }

}
