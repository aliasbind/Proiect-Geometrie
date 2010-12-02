import java.awt.geom.GeneralPath;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics;

public class MainFrame extends JFrame
{
    MainFrame()
    {
        super("Curbe Bezier");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 350);
        this.setVisible(true);
    }

    //public void paint (Graphics g)
    //{
    //}
}
