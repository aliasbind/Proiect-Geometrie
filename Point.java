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