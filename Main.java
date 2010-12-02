import java.util.*;
import java.awt.*;


class Point
{
    float x;
    float y;

    Point() {}
    Point(float x, float y)
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

    static long factorial(int nr)
    {
        if (nr==0)
        {
            return 1;
        }
        else
        {
            return factorial(nr-1) * nr;
        }
    }

    static long combinari(int nr, int k)
    {
        return factorial(nr) / (factorial(nr-k) * factorial(k));
    }

    static Point Bernstein(int n, float t, int i, Point punct)
    { //i=contor
       double aux = combinari(n,i) * Math.pow(1-t,n-i) * Math.pow(t,i);
       punct.x*=aux;
       punct.y*=aux;
       return punct;
    }
}

public class Main
{ 
    public static void main(String[] args)
    {
        int n1=5;
        int ok=0; //pt comparare

        ArrayList<Point> points1 = new ArrayList<Point>();
        points1.add(new Point(10, 15));
        points1.add(new Point(20, 25));
        points1.add(new Point(30, 35));
        points1.add(new Point(40, 45));
        points1.add(new Point(50, 55));

        float t=0;
        float sx1=0;
        float sy1=0;
        int aux;

        Point finale1=new Point(0,0);
        for(t=0; t<=1; t+=0.001)
        {
            for (int i=0; i<n1; i++)
            {
                finale1.addTo(Curve.Bernstein(n1, t, i, points1.get(i)));
            }
            System.out.println(finale1.x + " " + finale1.y);
        }

        int n2=5;
        ArrayList<Point> points2 = new ArrayList<Point>();
        points2.add(new Point(5, 21));
        points2.add(new Point(10, 37));
        points2.add(new Point(17, 41));
        points2.add(new Point(27, 57));
        points2.add(new Point(37, 100));

        t=0;
        float sx2=0;
        float sy2=0;
        int aux2;

        Point finale2=new Point(0,0);
        for (t=0;t<=1;t+=0.01)
        {
            for (int i=0;i<n2;i++)
            {
                finale2.addTo(Curve.Bernstein(n2,t,i, points2.get(i)));
            }
            System.out.println(finale2.x + " " + finale2.y);
        }
    }
}
