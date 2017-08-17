package LOGIC;
/**
 *
 * @author 
 */
import GUI.Maze;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class AlgorithmUsingStack implements ActionListener {
    
    private Stack myStack;
    private Maze maze;
    private ArrayList <Node> visitedNodes;
    public final static int NORTH = 0;
    public final static int SOUTH = 1;
    public final static int EAST = 2;
    public final static int WEST = 3;
    private Timer t;
    
    public AlgorithmUsingStack(Maze maze){
        this.myStack = new Stack();
        this.maze = maze;
        this.visitedNodes = new ArrayList<>();
        this.t = new Timer(100, this);
    }
    
    public void solveMaze(Maze maze){
        Cow r = maze.findCow();
        if(isPath(maze, NORTH) && !hasPassed(new Node(r.getPosX(), r.getPosY() - 1))){
            myStack.push(new Node(r.getPosX(), r.getPosY()));
            visitedNodes.add(new Node(r.getPosX(), r.getPosY()));
            r.moveUp(maze);
            ((Tile)maze.getTiles()[r.getPosY() + 1][r.getPosX()]).setImg(Tile.VISITED_IMG);
        }
        
        else if(isPath(maze, SOUTH) && !hasPassed(new Node(r.getPosX(), r.getPosY() + 1))){
            myStack.push(new Node(r.getPosX(), r.getPosY()));
            visitedNodes.add(new Node(r.getPosX(), r.getPosY()));
            r.moveDown(maze);
            ((Tile)maze.getTiles()[r.getPosY() - 1][r.getPosX()]).setImg(Tile.VISITED_IMG);
        }
        
        else if(isPath(maze, WEST) && !hasPassed(new Node(r.getPosX() + 1, r.getPosY()))){
            myStack.push(new Node(r.getPosX(), r.getPosY()));
            visitedNodes.add(new Node(r.getPosX(), r.getPosY()));
            r.moveRight(maze);
            ((Tile)maze.getTiles()[r.getPosY()][r.getPosX() - 1]).setImg(Tile.VISITED_IMG);
        }
        
        else if(isPath(maze, EAST) && !hasPassed(new Node(r.getPosX() - 1, r.getPosY()))){
            myStack.push(new Node(r.getPosX(), r.getPosY()));
            visitedNodes.add(new Node(r.getPosX(), r.getPosY()));
            r.moveLeft(maze);
            ((Tile)maze.getTiles()[r.getPosY()][r.getPosX() + 1]).setImg(Tile.VISITED_IMG);
        }
        
        else{
            visitedNodes.add(new Node(r.getPosX(), r.getPosY()));
            
            if(myStack.isEmpty()){
                JOptionPane.showMessageDialog(null, "No PATH to GOAL", "Machine Project", JOptionPane.WARNING_MESSAGE);
                stop();
                maze.getFrame().dispose();
            }
            else{
                stepBack(maze,(Node)myStack.pop());
            }
        }
    }
    
    public Stack getPathCoordinates(){
        Stack a = new Stack();
        do{
            a.push(myStack.pop());
        }while(!myStack.isEmpty());
        return a;
    }
    
    private void stepBack(Maze maze, Node node){
        Cow r = maze.findCow();
        if(r.getPosX() == node.getX() && r.getPosY() == node.getY() - 1){
            r.moveDown(maze);
            ((Tile)maze.getTiles()[r.getPosY() - 1][r.getPosX()]).setImg(Tile.PATH_IMG);
        }
        
        if(r.getPosX() == node.getX() && r.getPosY() == node.getY() + 1){
            r.moveUp(maze);
            ((Tile)maze.getTiles()[r.getPosY() + 1][r.getPosX()]).setImg(Tile.PATH_IMG);
        }
        
        if(r.getPosX() == node.getX() + 1 && r.getPosY() == node.getY()){
            r.moveLeft(maze);
            ((Tile)maze.getTiles()[r.getPosY()][r.getPosX() + 1]).setImg(Tile.PATH_IMG);
        }
        
        if(r.getPosX() == node.getX() - 1 && r.getPosY() == node.getY()){
            r.moveRight(maze);
            ((Tile)maze.getTiles()[r.getPosY()][r.getPosX() - 1]).setImg(Tile.PATH_IMG);
        }
    }
    
    private boolean hasPassed(Node check){
        if(visitedNodes.isEmpty()){
            return false;
        }
        else{
            for(Node n : visitedNodes){
                if(check.equals(n)){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isPath(Maze maze, int direction){
        Cow r = maze.findCow();
        Cell[][] cells = maze.getTiles();
        switch(direction){
            case NORTH://up
                if(r.getPosY() > 0){
                    if(cells[r.getPosY() - 1][r.getPosX()] instanceof Gate){
                        r.moveUp(maze);
                        stop();
                    }
                    if (cells[r.getPosY() - 1][r.getPosX()] instanceof Tile) {
                        if (!((Tile) cells[r.getPosY() - 1][r.getPosX()]).getWall()) {
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }
                break;
                
            case SOUTH://down
                if(r.getPosY() < maze.getMap().getColums() - 1){
                    if(cells[r.getPosY() + 1][r.getPosX()] instanceof Gate){
                        r.moveDown(maze);
                        stop();
                    }
                    if (cells[r.getPosY() + 1][r.getPosX()] instanceof  Tile) {
                        if (!((Tile) cells[r.getPosY() + 1][r.getPosX()]).getWall()) {
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }
                break;
                
            case EAST://left
                if(r.getPosX() > 0){
                    if(cells[r.getPosY()][r.getPosX() - 1] instanceof Gate){
                        r.moveLeft(maze);
                        stop();
                    }
                    if(maze.getTiles()[r.getPosY()][r.getPosX() - 1] instanceof Tile){
                        if (!((Tile) cells[r.getPosY()][r.getPosX() - 1]).getWall()) {
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }
                break;
                
            case WEST://right
                if(r.getPosX() < maze.getMap().getRows() - 1){
                    if(cells[r.getPosY()][r.getPosX() + 1] instanceof Gate){
                        r.moveRight(maze);
                        stop();
                    }
                    if(maze.getTiles()[r.getPosY()][r.getPosX() + 1] instanceof Tile){
                        if(!((Tile) cells[r.getPosY()][r.getPosX() + 1]).getWall()){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }
                break;
        }
        return false;
    }
    
    public void start(){
        t.start();
    }
    
    public void stop(){
        t.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        solveMaze(maze);
    }
    
}
