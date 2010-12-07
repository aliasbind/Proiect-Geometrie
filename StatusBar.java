import java.awt.Dimension;
import java.awt.Container;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StatusBar extends JLabel
{
    StatusBar()
    {
        super();
        setMessage("Ready");
    }

    //Asta e o metoda cam urata de a lega un eveniment de doua obiecte.
    //Trebuie sa facem cumva ca StatusBar-ul sa ia coordonatele mouse-ului atunci cand
    //se afla in zona DrawPanel-ului. Ca sa realizam acest lucru, am declarat functia
    //in acest fel pentru a fi apelata din createAndDrawGUI(), dupa ce au fost create deja
    //obiectele. Dupa ce va fi apelata aceasta functie, parametrul 'panel' va reprezenta
    //DrawPanel-ul nostru, iar 'sbar' StatusBar-ul creat.
    
    //Din motive inca neelucidate, nu am putut declara parametrul 'panel' sa fie de tipul
    //DrawPanel ca zicea ca metoda addMouseMotionListener nu exista, desi DrawPanel mosteneste
    //JPanel, deci ar fi trebuit sa aibe si el metoda asta. Totodata, imi dadea eroare
    //daca nu declaram parametrul 'sbar' sa fie 'final' (habar n-am de ce).
    static public void attachMouseMotionEvents(JPanel panel, final StatusBar sbar)
    {
        panel.addMouseMotionListener(new MouseMotionListener()
            {
                public void mouseDragged(MouseEvent e)
                {
                }

                public void mouseMoved(MouseEvent e)
                {
                    //Functia asta se executa ori de cate ori se misca mouse-ul deasupra
                    //DrawPanel-ului. Se iau coordonatele mouse-ului si se modifica textul
                    //StatusBar-ului.
                    sbar.setMessage("X: " + e.getX() + " Y: " + e.getY());
                }
            });
    }

    //Functia asta are rolul de a modifica textul ce este afisat in zona StatusBar-ului.
    public void setMessage(String msg)
    {
        setText("" + msg);
    }
}
