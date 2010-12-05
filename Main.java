import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

//Nu se face mare branza aici. Se initializeaza fereastra dupa care se adauga in continut
//un DrawPanel (care mosteneste JPanel).

public class Main
{ 
    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("Curbe Bezier");
        DrawPanel drawpanel = new DrawPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(drawpanel); //Vezi DrawPanel.java pentru detalii

        JPanel bottomPanel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        bottomPanel.setLayout(layout);

        ManualInputArea mia = new ManualInputArea();
        StatusBar sbar = new StatusBar();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 0.25;
        
        layout.setConstraints(mia, constraints);
        constraints.fill = GridBagConstraints.BOTH;
        layout.setConstraints(sbar, constraints);
        
        bottomPanel.add(mia);
        bottomPanel.add(sbar);
        StatusBar.attachMouseMotionEvents(drawpanel, sbar);
        
        frame.getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);
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
