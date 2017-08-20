package LOGIC;
/**
 *A special cell that would indicate the goal of a Vlad Object
 * @author
 */
import acm.io.*;
import acm.program.*;
import acm.util.RandomGenerator;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class Goal extends Cell {
    
    public final static Color GOAL_COLOR = Color.GRAY;
    public final static String GOAL_IMG = "C:\\Users\\Ryan\\Documents\\NetBeansProjects\\DATASALPROJECT\\resource\\Melvin.png";
    protected JFrame frame;
    private DatasalQuestionDatabase q = new DatasalQuestionDatabase(); 
    public Goal(double x, double y, int posX, int posY, int width, int height, JFrame frame){
        super(x, y, posX, posY, width, height);
        this.frame = frame;
        this.curColor = GOAL_COLOR;
        this.status = Cell.GOAL_STAT;
        setImg(GOAL_IMG);
    }
    
    public Goal(double x, double y, int posX, int posY, int width, int height){
        super(x, y, posX, posY, width, height);
    }
    
    public void goal(){
        RandomGenerator rgen = RandomGenerator.getInstance();
      DatasalQuestion question = q.getQuestionData(rgen.nextInt(1, q.getNumQuestions()));
        Object[] choices = question.getChoices();
        
        // String answer = String.valueOf(JOptionPane.showInputDialog(null, question.getQuestion(), "Question", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]));
        while(true) {
            String answer = String.valueOf(JOptionPane.showInputDialog(null, question.getQuestion(), "Question", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]));
            if(answer!=null && answer.length()>0) {
                if(answer.equals(question.getAnswer())) {
                    JOptionPane.showMessageDialog(null, "Answer is Correct", "Question", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Answer incorrect.", "Question", JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            else {
                int confirmExit = JOptionPane.showConfirmDialog(null, "Are you sure? You're goint to lose points.", "Question", JOptionPane.YES_NO_OPTION);
                if(confirmExit==JOptionPane.YES_OPTION) break;
            }
        }
        
        
        // JOptionPane.showMessageDialog(null, "Congratulations", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
        
    }
    
    @Override
    public String toString(){
        return "G";
    }
}
