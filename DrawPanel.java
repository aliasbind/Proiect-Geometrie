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

import java.util.ArrayList;

public class DrawPanel extends JPanel
{
    public ArrayList<Point2D.Float> curba1;
    public ArrayList<Point2D.Float> curba2;

    public GeneralPath path1;
    public GeneralPath path2;

    public int CurbaCurenta;

    DrawPanel()
    {
        curba1 = new ArrayList<Point2D.Float>();
        curba2 = new ArrayList<Point2D.Float>();

        path1 = new GeneralPath();
        path2 = new GeneralPath();
        CurbaCurenta = 1;
        //Cam pe aici vom avea arraylist-ul de puncte, la inceput fiind gol.
        //Odata ce utilizatorul clickeaza pe fereastra, vom adauga puncte in arraylist,
        //apoi vom gasi vreun mod sa desenam curba bezier.

        //Dupa ce omul nostru termina de clickat poligonul de control, va apasa o combinatie
        //(ma gandesc eu: CTRL+RCLICK) si va clicka poligonul de control al celei de-a doua
        //curbe bezier (apoi vom vedea cum le-om compara noi).
        this.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("Clicked!");
            }
            
            public void mouseExited(MouseEvent e)
            {
                System.out.println("Exited Panel!");
            }

            public void mouseEntered(MouseEvent e)
            {
                System.out.println("Entered Panel!");
            }

            public void mousePressed(MouseEvent e)
            {
                int button = e.getButton();
                if(button == MouseEvent.BUTTON3)
                {
                    switch(CurbaCurenta)
                    {
                        case 1:
                            CurbaCurenta = 2;
                            break;

                        case 2:
                            if(Bezier.compare(curba1, curba2) == true)
                                JOptionPane.showMessageDialog(getParent(), "DA!");
                            else
                                JOptionPane.showMessageDialog(getParent(), "NU");
                            repaint(0, 0, 0, getSize().height, getSize().width);
                            CurbaCurenta = 1;
                            break;
                    }
                }
                else
                {
                    Rectangle2D.Float pct = new Rectangle2D.Float(e.getX(), e.getY(), 0, 0);
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.draw(pct);
                    if(CurbaCurenta == 1)
                    {
                        curba1.add(new Point2D.Float(pct.x, pct.y));
                        Point2D.Float CurrentPoint = curba1.get(curba1.size()-1);
                        if( (curba1.size() - 1) % 3 == 0)
                        {
                            if(curba1.size()-1 != 0)
                            {
                                Point2D.Float point1 = curba1.get(curba1.size()-3);
                                Point2D.Float point2 = curba1.get(curba1.size()-2);
                                Point2D.Float point3 = curba1.get(curba1.size()-1);
                                path1.curveTo(point1.x, point1.y, point2.x, point2.y,
                                        point3.x, point3.y);
                                g2d.setColor(Color.blue);
                                g2d.draw(path1);
                            }
                            path1.moveTo(CurrentPoint.x, CurrentPoint.y);
                        }
                    }
                    else
                    {
                        if(CurbaCurenta == 2)
                        {
                            curba2.add(new Point2D.Float(pct.x, pct. y));
                            Point2D.Float CurrentPoint = curba2.get(curba2.size()-1);
                            if( (curba2.size() - 1) % 3 == 0)
                            {
                                if(curba2.size()-1 != 0)
                                {
                                    Point2D.Float point1 = curba2.get(curba2.size()-3);
                                    Point2D.Float point2 = curba2.get(curba2.size()-2);
                                    Point2D.Float point3 = curba2.get(curba2.size()-1);
                                    path2.curveTo(point1.x, point1.y, point2.x, point2.y,
                                            point3.x, point3.y);
                                    g2d.setColor(Color.red);
                                    g2d.draw(path2);
                                }
                                path2.moveTo(CurrentPoint.x, CurrentPoint.y);
                            }
                        }
                    }
                    //Deseneaza un dreptunghi, in punctu in care se afla mouse-ul cand
                    //s-a facut click, care are o lungime si latime de 0px, adica va desena 
                    //un punct.

                    //Pana acum se deseneaza un punct pentru orice click 
                    //(fie el LEFT sau RIGHT sau chiar MIDDLE).
                    System.out.println("Pressed!");
                }
            }
            
            public void mouseReleased(MouseEvent e)
            {
                System.out.println("Released!");
            }
        });

        //Inca mai e mult de lucru la DrawPanel
    }

    //protected void paintComponent(Graphics g)
    //{
        //Functia asta se apeleaza automat de interpretor, oferindu-ti obiectul 'g'
        //care te va lasa sa desenezi ce vrei tu in Panel.
        //
        //Cand vom termina proiectul (doamne-ajuta!), nu vom mai avea nevoie
        //de functia asta, pentru ca, dupa cum ati observat in metoda mousePressed() de mai sus,
        //vom lua noi obiectul 'Graphics' folosind metoda getGraphics() si vom desena in
        //interiorul Event-Handler-ului. Functia asta am scris-o doar sa vad daca aveam
        //dreptate cu GeneralPath :).

    //Graphics2D g2d;
    //g2d = (Graphics2D) g;

        //GeneralPath path = new GeneralPath();
        //path.moveTo(0,0);
        //path.curveTo(10, 15, 1, 80, 50, 50);

        //g2d.draw(path);
    //}
}
