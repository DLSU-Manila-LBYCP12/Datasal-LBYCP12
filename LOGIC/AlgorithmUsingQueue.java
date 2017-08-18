package LOGIC;
/**
 *
 * @author
 */
import GUI.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionListener;
public class AlgorithmUsingQueue implements ActionListener{
    
    private Queue myQueue;
    private Maze maze;
    private ArrayList<Node> visitedNodes;
    private Node curNode;
    public final static int NORTH = 0;
    public final static int SOUTH = 1;
    public final static int EAST = 2;
    public final static int WEST = 3;
    private Timer t;
    
    public AlgorithmUsingQueue(Maze maze){
        this.myQueue = new Queue();
        this.visitedNodes = new ArrayList<>();
        this.maze = maze;
        this.curNode = new Node(maze.findCow().getPosX(), maze.findCow().getPosY());
        visitedNodes.add(curNode);
        this.t = new Timer(100, this);
        
    }
    
    public void solveMaze(Maze maze){
        Cell[][] cells = maze.getTiles();
        
        if(isPath(curNode, NORTH) && !hasPassed(new Node(curNode.getX(), curNode.getY() - 1))){
            myQueue.enqueue(new Node(curNode.getX(), curNode.getY() - 1));
            visitedNodes.add(new Node(curNode.getX(), curNode.getY() - 1));
        }
        
        if(isPath(curNode, SOUTH) && !hasPassed(new Node(curNode.getX(), curNode.getY() + 1))){
            myQueue.enqueue(new Node(curNode.getX(), curNode.getY() + 1));
            visitedNodes.add(new Node(curNode.getX(), curNode.getY() + 1));
        }
        
        if(isPath(curNode, EAST) && !hasPassed(new Node(curNode.getX() - 1, curNode.getY()))){
            myQueue.enqueue(new Node(curNode.getX() - 1, curNode.getY()));
            visitedNodes.add(new Node(curNode.getX() - 1, curNode.getY()));
        }
        
        if(isPath(curNode, WEST) && !hasPassed(new Node(curNode.getX() + 1, curNode.getY()))){
            myQueue.enqueue(new Node(curNode.getX() + 1, curNode.getY()));
            visitedNodes.add(new Node(curNode.getX() + 1, curNode.getY()));
        }
        else{
            curNode = (Node)myQueue.dequeue();
        }
    }
    
    private boolean isPath(Node node, int direction){
        Cell[][] cells = maze.getTiles();
        switch(direction){
            case NORTH:
                if(node.getY() > 0){
                    if (cells[node.getY() - 1][node.getX()] instanceof Tile) {
                        if (!((Tile) cells[node.getY() - 1][node.getX()]).getWall()) {
                            return true;
                        }
                        else{
                            //System.out.println("CANNOT PASS(N1)");
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }
                break;
                
            case SOUTH:
                if(node.getY() < maze.getMap().getColums() - 1){
                    if (cells[node.getY() + 1][node.getX()] instanceof  Tile) {
                        if (!((Tile) cells[node.getY() + 1][node.getX()]).getWall()) {
                            return true;
                        }
                        else{
                            //System.out.println("CANNOT PASS(S1)");
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }
                break;
                
            case EAST:
                if(node.getX() > 0){
                    if(maze.getTiles()[node.getY()][node.getX() - 1] instanceof Tile){
                        if (!((Tile) cells[node.getY()][node.getX() - 1]).getWall()) {
                            return true;
                        }
                        else{
                            //System.out.println("CANNOT PASS(E1)");
                            return false;
                        }
                    }
                }
                else{
                   //System.out.println("CANNOT PASS(E2)");
                    return false;
                }
                break;
                
            case WEST:
                if(node.getX() < maze.getMap().getRows() - 1){
                    if(maze.getTiles()[node.getY()][node.getX() + 1] instanceof Tile){
                        if(!((Tile) cells[node.getY()][node.getX() + 1]).getWall()){
                            return true;
                        }
                        else{
                            //System.out.println("CANNOT PASS(W1)");
                        }
                    }
                }
                else{
                    //System.out.println("CANNOT PASS(W2)");
                    return false;
                }
                break;
                
        }
        return false;
    }
    
    private boolean hasPassed(Node check){
        if(visitedNodes.isEmpty()){
            //System.out.println("Empty");
            return false;
        }
        else{
            for(Node n : visitedNodes){
                if(check.equals(n)){
                    //System.out.println("Found Node");
                    return true;
                }
            }
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
