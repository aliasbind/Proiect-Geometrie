import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class Main
{ 
    private static void createAndShowGUI()
    {
        //Se creaza fereastra principala.
        JFrame frame = new JFrame("Curbe Bezier");

        //Se creaza DrawPanel-ul princpal.
        DrawPanel drawpanel = new DrawPanel();

        //Se seteaza actiunea ce va fi executata la inchiderea ferestrei,
        //adica iesirea din program.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Adaugarea DrawPanel-ului nostru in continutul ferestrei.
        frame.getContentPane().add(drawpanel);

        //Crearea unui panou al carui scop este de a contine ManualInputArea-ul
        //si StatusBar-ul.
        JPanel bottomPanel = new JPanel();

        //Elemente ce tin de setarea layout-ului. Sincer sa fiu, nici eu nu prea stiu
        //ce fac astea. Speram sa aranjez cumva elementele sa arate cat mai bine, dar
        //tot cam aiurea arata.
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        bottomPanel.setLayout(layout);

        //Sper sa fie evidente astea doua instructiuni:
        ManualInputArea mia = new ManualInputArea();
        StatusBar sbar = new StatusBar();

        //Asta ii spune managerului de layout cum sa aseze ManualInputArea si StatusBar-ul,
        //adica unul pe fiecare rand.
        constraints.gridwidth = GridBagConstraints.REMAINDER; 
        
        layout.setConstraints(mia, constraints);
        layout.setConstraints(sbar, constraints);
        
        //Adaugarea elementelor in panou.
        bottomPanel.add(mia);
        bottomPanel.add(sbar);

        //Legarea evenimentului de miscare de DrawPanel si StatusBar. (vezi StatusBar.java)
        StatusBar.attachMouseMotionEvents(drawpanel, sbar);
        
        //Adaugarea panoului in partea de jos a ferestrei.
        frame.getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);
        
        //Asezarea corecta a componentelor conform layout-ului dat, setarea marimii ferestrei
        //si setarea visibilitatii.
        frame.pack();
        frame.setSize(450, 450);
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    createAndShowGUI();
                }
            });
    }
}
