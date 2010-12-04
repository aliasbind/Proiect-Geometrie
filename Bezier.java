import java.util.ArrayList;
import java.awt.geom.Point2D;

public class Bezier
{
    static boolean compare(ArrayList<Point2D.Float> curba1, ArrayList<Point2D.Float> curba2)
    {
        if(curba1.size() != curba2.size())
            return false;

        ArrayList<Point2D.Float> DiferentaCurbelor = new ArrayList<Point2D.Float>();
        
        int i;
        for(i=0; i<curba1.size(); i++)
            DiferentaCurbelor.add(new Point2D.Float(curba1.get(i).x - curba2.get(i).x,
                                                    curba1.get(i).y - curba2.get(i).y));
        for(i=0; i<curba1.size()-1; i++)
            if( (DiferentaCurbelor.get(i).x != DiferentaCurbelor.get(i+1).x) ||
                (DiferentaCurbelor.get(i).y != DiferentaCurbelor.get(i+1).y))
                return false;
        
        return true;
    }
}
