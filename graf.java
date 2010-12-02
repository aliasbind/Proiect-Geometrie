package beziecurve;

import java.awt.geom.GeneralPath;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics;

public class graf extends JFrame
{
    graf()
    {
        super("Default Layout");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 350);
        this.setVisible(true);
    }

    public void paint (Graphics g)
    {
        g.p
        GeneralPath path = new GeneralPath();
        path.moveTo(0, 0);
        path.curveTo(10, 20, 45, 26, 50, 90);
        
        Graphics g=new

    }
}
