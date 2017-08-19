package LOGIC;
/**
 *Movements of what a PlayerListener can do
 * @author 
 * 
 */
import GUI.Maze;
public interface PlayerListener {
    public void moveLeft(Maze m);
    public void moveRight(Maze m);
    public void moveUp(Maze m);
    public void moveDown(Maze m);
}
