package LOGIC;
/**
 *
 * @author 
 */
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
public abstract class Cell extends Rectangle{
    
    protected double x, y;
    protected int posX, posY;
    protected int status;
    protected Color curColor;
    public final static int PATH_STAT = 0;
    public final static int WALL_STAT = 1;
    public final static int COW_STAT = 2;
    public final static int GATE_STAT = 3;
    public Image img;
    
    public Cell(double x, double y, int posX, int posY, int width, int height){
        this.x = x;
        this.y = y;
        super.x = (int)x;
        super.y = (int)y;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }
    
    public Cell(){}
    public void setImg(String path){
        this.img = new ImageIcon(path).getImage();
    }
    
    public int getPosX(){return this.posX;}
    public int getPosY(){return this.posY;}
    public int getStatus(){return this.status;}
    public void setPosX(int x){this.posX = x;}
    public void setPosY(int y){this.posY = y;}
    public Color getColor(){return this.curColor;}
    public void setColor(Color color){this.curColor = color;}
    public Point getPoint(){return new Point((int)this.x, (int)this.y);}
    @Override
    public double getX(){return this.x;}
    @Override
    public double getY(){return this.y;}
    
    @Override
    public boolean contains(Point p){
        double pX = p.getX();
        double pY = p.getY();
        
        if( (pX >= x && pX <= x + width) && (pY >= y && pY <= y + height) )
            return true;
        
        return false;
    }
    
}
