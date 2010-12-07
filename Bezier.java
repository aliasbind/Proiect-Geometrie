import java.util.ArrayList;
import java.awt.geom.Point2D;

public class Bezier
{
    //Avem aici functia de comparare a doua curbe.
    //
    //Am desenat eu pe hartie doua curbe identice, apoi am zis sa scad punctele intre ele
    //astfel:
    //
    //curba1[0] - curba2[0]
    //curba1[1] - curba2[1]
    //
    //etc.
    //
    //Rezultatul fiecarei scaderi il puneam intr-o lista (DiferentaCurbelor).
    //Am observat atunci ca toate punctele din aceasta lista au coordonatele identice.
    //
    //Deci am presupus prin absurd ca doua curbe sunt simetrice daca scazand elementele din
    //prima curba cu elementele din a doua curba obtinem o lista cu toate punctele egale.
    //N-am reusit sa demonstrez acum acest lucru, dar nu am gasit un exemplu care sa contrazica
    //aceasta presupunere.
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
