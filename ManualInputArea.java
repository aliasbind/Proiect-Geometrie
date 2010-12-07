import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class ManualInputArea extends JPanel
{
    //Declar componentele acestei zone.
    //Doua JLabel-uri care arata unde se introduc X-ul si Y-ul punctelor pe care vrei sa le
    //adaugi si doua JTextField fiind zona de inputare a textului.
    //Evident, JButton este butonul pe care clickezi ca sa introduci coordonatele
    //(butonul "Adauga").
    JLabel X;
    JTextField Xtxt;
    JLabel Y;
    JTextField Ytxt;
    JButton addButton;

    ManualInputArea()
    {
        super();

        //Aceasta functie seteaza ordinea in care sunt introduse componentele in panou.
        //In cazul nostru, ordonoare se face pe un singur rand, iar de fiecare data cand
        //se face add(componenta) se adauga componenta noua la dreapta ultimei adaugate.
        setLayout(new GridLayout());

        //Se initializeaza aceste componente ale panoului.
        X = new JLabel("X:");
        Xtxt = new JTextField();
        Y = new JLabel("Y:");
        Ytxt = new JTextField();
        addButton = new JButton("Adaugă");

        //Se "leaga" de butonul "Adauga" un ascultator de evenimente astfel incat, atunci cand
        //este apasat, butonul va executa functia definita in actionPerformed().
        addButton.addActionListener(new ActionListener()
            {
                
                //Aici se desfasoara marea sarcina de a adauga punctul in panoul principal
                //(DrawPanel). Mai intai se obtine obiectul ferestrei (JFrame-ul), apoi din 
                //aceasta fereastra se ia obiectul Container ce contine obiectele continute de
                //fereastra si, intr-un final, se ia primul obiect din container, fiind
                //DrawPanel-ul nostru.
                public void actionPerformed(ActionEvent e)
                {
                    JFrame mainframe = (JFrame) getTopLevelAncestor();
                    Container container = mainframe.getContentPane();
                    DrawPanel drawpanel = (DrawPanel) container.getComponent(0);

                    //Urmeaza luarea valorilor din JTextField-uri si convertirea lor
                    //in float-uri pentru a fi desenate. (Membrul getText() al clasei
                    //JTextField returneaza String).
                    float x, y;
                    x = Float.parseFloat(Xtxt.getText());
                    y = Float.parseFloat(Ytxt.getText());

                    //Executam operatiile de adaugare si desenare a punctului.
                    drawpanel.addPoint(x, y);
                }
            });

        //Adaugam obiectele mentionate mai sus in ManualInputArea.
        add(X);
        add(Xtxt);
        add(Y);
        add(Ytxt);
        add(addButton);
    }
}
