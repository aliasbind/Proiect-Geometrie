import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

import java.util.ArrayList;

public class DrawPanel extends JPanel
{
    //Avem aici un ArrayList de ArrayListuri de puncte avand variabile de tip float.
    //Mai pe romaneste, avem cele doua liste de puncte retinute intr-o alta lista.
    //Astfel, lista 'curbe' nu are decat doua elemente. Le-as fi declarat mai usor de inteles
    //
    //ArrayList <Point2D.Float> curba1;
    //ArrayList <Point2D.Float> curba2;
    //
    //dar restul codului ar fi fost redundant si mult mai urat decat este acum.
    //
    //Ca sa fiu mai clar,
    //
    //curba1 == curbe.get(0);
    //curba2 == curbe.get(1);
    public ArrayList< ArrayList<Point2D.Float> > curbe;

    //Aici 'paths' este un array simplu de doua obiecte de tip GeneralPath (unul pentru
    //fiecare curba). Scopul lor este de ii spune obiectului Graphics2D ce sa deseneze.
    //Este, cum ar veni, o clasa ajutatoare (dar necesara) pentru Graphics2D.
    public GeneralPath[] paths;

    //Specifica ce curba se deseneaza acum (prima sau a doua).
    //
    //prima curba = 0
    //a doua curba = 1
    public int CurbaCurenta;

    DrawPanel()
    {
        //Aloca listele.
        clearLists();

        //Seteaza curba curenta ca fiind prima.
        CurbaCurenta = 0;

        //Se leaga evenimentele mouse-ului de DrawPanel.
        this.addMouseListener(new MouseListener()
        {
            //Fiind interfata, noi trebuie sa declaram toate metodele MouseListener-ului.
            //Cum noi nu avem nevoie decat de functia in care se proceseaza apasarea unui buton,
            //vom folosi doar mousePressed() pentru actiunile noastre, iar restul functiilor
            //le vom lasa 'goale' (adica sa nu faca nimic).
            public void mouseClicked(MouseEvent e)
            {
            }
            public void mouseExited(MouseEvent e)
            {
            }
            public void mouseEntered(MouseEvent e)
            {
            }

            public void mousePressed(MouseEvent e)
            {
                int button = e.getButton();
                if(button == MouseEvent.BUTTON3)
                {
                    //Avem aici ramura ce se va executa cand butonul apasat este BUTTON3,
                    //adica Right-Click. Cand acest buton este apasat se intelege ca s-a
                    //terminat procesul de introducere a punctelor primei curbe si se trece
                    //la introducerea celei de-a doua curbe. (case 1)
                    //
                    //In cazul in care curba curenta era cea de-a doua, 
                    //se lanseaza functia de comparare a celor doua curbe. Dupa ce s-a terminat
                    //operatia de comparare, se afiseaza un mesaj daca aceste curbe sunt
                    //sau nu 'egale'. Apoi se seteaza curba curenta ca fiind '1' 
                    //(o luam de la capat) se golesc listele (clearLists())
                    //si se goleste DrawPanel-ul de toate punctele si curbele care apar pe el.
                    //(repaint()).
                    switch(CurbaCurenta)
                    {
                        case 0:
                            CurbaCurenta = 1;
                            break;

                        case 1:
                            if(Bezier.compare(curbe.get(0), curbe.get(1)) == true)
                                JOptionPane.showMessageDialog(getParent(), "DA!");
                            else
                                JOptionPane.showMessageDialog(getParent(), "NU");

                            repaint(0, 0, 0, getSize().width, getSize().height);
                            clearLists();
                            CurbaCurenta = 0;
                            break;
                    }
                }
                else
                {
                    //Daca orice alt buton al mouse-ului este apasat, se executa functia
                    //de adaugare a punctului in lista corespunzatoare si functia de desenare
                    //a curbei (daca este cazul).
                    addPoint(e.getX(), e.getY());
                }
            }
            
            public void mouseReleased(MouseEvent e)
            {
            }
        });

    }

    //addPoint() deseneaza punctul pe ecran, apoi deseneaza un cerc portocaliu in jurul lui,
    //dupa care paseaza controlul lui processCurbe() pentru adaugarea punctului curent in lista.
    public void addPoint(float x, float y)
    {
        //Se ia obiectul de grafica corespunzator panoului.
        Graphics2D g2d = (Graphics2D) getGraphics();

        //Intrucat Java nu are o clasa de tip 'punct' care poate fi desenata cu draw(),
        //am folosit clasa Rectangle2D.Float care este folosita pentru desenarea
        //dreptunghiurilor, deci vom desena un dreptunghi cu originea in 'x' si 'y',
        //avand o lungime si latime 0.
        Rectangle2D.Float pct = new Rectangle2D.Float(x, y, 0, 0);
        g2d.draw(pct);

        //Desenam cerculetul portocaliu.
        g2d.setColor(Color.orange);
        g2d.draw(new Ellipse2D.Float(x-5, y-5, 10, 10));

        //Trecem la cea mai grea parte a programului: procesarea curbei.
        processCurve(new Point2D.Float(x, y));
    }

    //Functia de eliberat continutul listelor si array-ului de GeneralPath-uri si de realocare
    //a lor. De fapt, nu prea se elibereaza nimic, ci se aloca o alta serie de obiecte.
    //Obiectele folosite ultimadata vor mai exista ceva in memorie pana cand le va distruge
    //'Garbage Collector'-ul.
    public void clearLists()
    {
        int i;
        curbe = new ArrayList< ArrayList<Point2D.Float> >();
        paths = new GeneralPath[2];
        for(i=0; i<2; i++)
        {
            paths[i] = new GeneralPath();
            curbe.add(new ArrayList<Point2D.Float>());
        }
    }

    public void processCurve(Point2D.Float CurrentPoint)
    {
        //Luam obiectul de desenare.
        Graphics2D g2d = (Graphics2D) getGraphics();

        //Adaugam punctul in lista corespunzatoare.
        curbe.get(CurbaCurenta).add(CurrentPoint);

        //Verificam daca numarul de puncte din lista de puncte 'n'-1 este divizibil cu 3.
        //Spre exemplu, daca asta este primul punct adaugat in lista,
        //
        //'n' = 1;
        //'n'-1 este divizibil cu 3, deci se va intra doar in primul "if", deoarece
        //al doilea "if" verifica daca lista are doar un element. Astfel, se va initializa 
        //originea primei curbei, fiind apelata doar functia moveTo().
        //
        //Dar, daca sunt introduse 4 puncte in lista (CurrentPoint inclus), atunci se intra
        //in ambele if-uri si se va desena curba folosindu-se ultimele 3 puncte introduse 
        //in lista.
        //
        //Cand se termina de desenat curba, se va seta originea urmatoarei curbe in ultimul 
        //punct introdus.
        //
        //Analog, daca am introdus (3 | n-1) curbe.
        if( (curbe.get(CurbaCurenta).size() - 1) % 3 == 0)
        {
            if(curbe.get(CurbaCurenta).size() - 1 != 0)
            {
                //Indicii ultimelor 3 puncte din lista.
                int ante_pen_ultimul = curbe.get(CurbaCurenta).size() - 3;
                int pen_ultimul =      curbe.get(CurbaCurenta).size() - 2;
                int ultimul =          curbe.get(CurbaCurenta).size() - 1;

                //Luarea celor 3 puncte si punerea lor in variabile separate.
                Point2D.Float point1 = curbe.get(CurbaCurenta).get(ante_pen_ultimul);
                Point2D.Float point2 = curbe.get(CurbaCurenta).get(pen_ultimul);
                Point2D.Float point3 = curbe.get(CurbaCurenta).get(ultimul); 

                //NOTA: curbe.get(CurbaCurenta).get(ultimul) == CurrentPoint;
                //dar am scris asa sa fie mai usor de inteles

                //Configurarea propriu-zisa a curbei in GeneralPath-ul ei.
                paths[CurbaCurenta].curveTo(point1.x, point1.y, 
                                            point2.x, point2.y,
                                            point3.x, point3.y);

                //Selectarea culorii de desenare.
                if(CurbaCurenta == 0)
                    g2d.setColor(Color.blue);
                else
                    g2d.setColor(Color.red);

                //Desenarea propriu-zisa.
                g2d.draw(paths[CurbaCurenta]);
            }
            paths[CurbaCurenta].moveTo(CurrentPoint.x, CurrentPoint.y);
        }
    }
}
