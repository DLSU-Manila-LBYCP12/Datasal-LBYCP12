package LOGIC;
/**
 *A special cell that would indicate the goal of a Cow Object
 * @author
 */
import acm.io.*;
import acm.program.*;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class Gate extends Cell {
    
    public final static Color GATE_COLOR = Color.GRAY;
    public final static String GOAL_IMG = "C:\\Users\\Ryan\\Documents\\NetBeansProjects\\DATASALPROJECT\\resource\\Goal.jpg";
    protected JFrame frame;
    
    public Gate(double x, double y, int posX, int posY, int width, int height, JFrame frame){
        super(x, y, posX, posY, width, height);
        this.frame = frame;
        this.curColor = GATE_COLOR;
        this.status = Cell.GATE_STAT;
        setImg(GOAL_IMG);
    }
    
    public Gate(double x, double y, int posX, int posY, int width, int height){
        super(x, y, posX, posY, width, height);
    }
    
    public void goal(){
        IODialog dialog = new IODialog();
        dialog.print("Congrats");
        JOptionPane.showMessageDialog(null, "Congratulations", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
    }
    
    @Override
    public String toString(){
        return "G";
    }
}
