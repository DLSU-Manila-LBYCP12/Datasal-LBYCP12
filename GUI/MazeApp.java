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
    private MapEditor editor;
    
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
    
    public MazeApp(MapEditor editor){
        super("DATASAL Project");
        this.editor = editor;
        this.add(editor);
        if(editor.getMap().getColums() >= 15 && editor.getMap().getColums() < 20 && editor.getMap().getRows() >= 15 && editor.getMap().getRows() < 20){
            this.setSize(812, 836);
        }   
        else if(editor.getMap().getColums() >= 20 && editor.getMap().getColums() < 26 && editor.getMap().getRows() >= 20 && editor.getMap().getRows() < 26){
            this.setSize(941, 962);
        }
        else if(editor.getMap().getColums() >= 26 && editor.getMap().getColums() <= 30 && editor.getMap().getRows() >= 26 && editor.getMap().getRows() <= 30){
            this.setSize(826, 849);
        }
        else{
            this.setSize(825, 848);
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
