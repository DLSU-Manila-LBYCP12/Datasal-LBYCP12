package LOGIC;
/**
 *A special Cell that can move around
 * @author 
 */
import java.awt.Color;
import GUI.Maze;
public class Cow extends Cell implements PlayerListener{
    
    public final static Color COW_COLOR = Color.BLUE;
    public final static String COW_IMG = "C:\\Users\\Ryan\\Documents\\NetBeansProjects\\DATASALPROJECT\\resource\\Cow.png";
    
    public Cow(double x, double y, int posX, int posY, int width, int height){
        super(x, y, posX, posY, width, height);
        this.status = Cell.COW_STAT;
        this.curColor = COW_COLOR;
        setImg(COW_IMG);
    }
    
    @Override
    public void moveLeft(Maze m){
        if(posX > 0) {
            if(m.getTiles()[posY][posX - 1] instanceof Tile){
                if(!((Tile)m.getTiles()[posY][posX - 1]).getWall()){
                    m.getTiles()[posY][posX].x -= super.width + 2;
                    m.getTiles()[posY][posX - 1].x += super.height + 2;
                    m.swap(posX, posY, posX - 1, posY);
                }
            }
            else if(m.getTiles()[posY][posX - 1] instanceof Gate){
                ((Gate)m.getTiles()[posY][posX - 1]).goal();
            }
        }
    }
    
    @Override
    public void moveRight(Maze m){
        if(posX < m.getMap().getRows() - 1){
            if(m.getTiles()[posY][posX + 1] instanceof Tile){
                if(!((Tile)m.getTiles()[posY][posX + 1]).getWall()){
                    m.getTiles()[posY][posX].x += super.width + 2;
                    m.getTiles()[posY][posX + 1].x -= super.height + 2;
                    m.swap(posX, posY, posX + 1, posY);
                }
            }
            else if(m.getTiles()[posY][posX + 1] instanceof Gate){
                ((Gate)m.getTiles()[posY][posX + 1]).goal();
            }
        }
    }

    @Override
    public void moveUp(Maze m){
        if(posY > 0){
            if(m.getTiles()[posY - 1][posX] instanceof Tile){
                if(!((Tile)m.getTiles()[posY - 1][posX]).getWall()){
                    m.getTiles()[posY][posX].y -= super.width + 2;
                    m.getTiles()[posY - 1][posX].y += super.height + 2;
                    m.swap(posX, posY, posX, posY - 1);
                }
            }
            else if(m.getTiles()[posY - 1][posX] instanceof Gate){
                ((Gate)m.getTiles()[posY - 1][posX]).goal();
            }
        }
    }

    @Override
    public void moveDown(Maze m){
        if(posY < m.getMap().getColums() - 1){
            if(m.getTiles()[posY + 1][posX] instanceof Tile){
                if(!((Tile)m.getTiles()[posY + 1][posX]).getWall()){
                    m.getTiles()[posY][posX].y += super.width + 2;
                    m.getTiles()[posY + 1][posX].y -= super.height + 2;
                    m.swap(posX, posY, posX, posY + 1);
                }
            }
            else if(m.getTiles()[posY + 1][posX] instanceof Gate){
                ((Gate)m.getTiles()[posY + 1][posX]).goal();
            }
        }
    }
    
    @Override
    public String toString(){
        return "S";
    }
    
}
