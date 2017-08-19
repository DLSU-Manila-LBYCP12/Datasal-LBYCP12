package GUI;
/**
 *
 * @author
 */
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.JFrame;
public class MazeApp extends JFrame implements WindowListener{
    
    private Maze maze;

    public MazeApp(File f){
        super("DATASAL Project");
        this.maze = new Maze(f, this);
        this.add(maze);
        if(maze.getMap().getColums() >= 15 && maze.getMap().getColums() < 20 && maze.getMap().getRows() >= 15 && maze.getMap().getRows() < 20){
            this.setSize(776, 799);
        }   
        else if(maze.getMap().getColums() >= 20 && maze.getMap().getColums() < 26 && maze.getMap().getRows() >= 20 && maze.getMap().getRows() < 26){
            this.setSize(891, 913);
        }
        else if(maze.getMap().getColums() >= 26 && maze.getMap().getColums() <= 30 && maze.getMap().getRows() >= 26 && maze.getMap().getRows() <= 30){
            this.setSize(765, 789);
        }
        else{
            this.setSize(765, 789);
        }
        addWindowListener(this);
        this.setVisible(false);
    }
 

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        if(maze != null)
            maze.getAlgorithm().stop();
        if(editor != null)
            editor.windowClosing(e);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
