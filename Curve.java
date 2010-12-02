public class Curve
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
