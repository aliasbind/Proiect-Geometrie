//Clasa asta se dovedeste inutila pentru ca Java deja are clasa Point
//(cu clasele interioare Point2D.Float si Point2D.Double).
//Metoda addTo() nu o are Java, dar nici nu ne va folosi.

public class Point
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
