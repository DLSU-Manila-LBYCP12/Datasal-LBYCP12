package LOGIC;
/**
 *
 * @author
 */
public class Node {
    
    private int x, y;
    
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Node){
            Node node = (Node) o;
            if(this.x == node.x && this.y == node.y){
                return true;
            }
        }
        else{
            return false;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "(x, y) = (" + x + ", " + y + ")";
    }
    
}
