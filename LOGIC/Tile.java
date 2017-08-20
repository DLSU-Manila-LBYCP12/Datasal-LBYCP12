package LOGIC;

/**
 *
 * @author
 */
import java.awt.Color;

public class Tile extends Cell {

    private boolean isWall;
    public final static int IS_WALL = 1;
    public final static int NOT_WALL = 0;
    public final static Color WALL_COLOR = Color.BLACK;
    public final static Color PATH_COLOR = Color.WHITE;
    public final static Color VISITED_COLOR = new Color(135, 206, 250);
    public final static String PATH_IMG = "C:\\Users\\Ryan\\Desktop\\DATASALPROJECT\\resource\\Path.jpg";
    public final static String WALL_IMG = "C:\\Users\\Ryan\\Desktop\\DATASALPROJECT\\resource\\Wall.jpg";
    public final static String VISITED_IMG = "C:\\Users\\Ryan\\Desktop\\DATASALPROJECT\\resource\\Dirt.jpg";

    public Tile(double x, double y, int posX, int posY, int width, int height) {
        super(x, y, posX, posY, width, height);
    }

    public void setWall(int wall) {
        if (wall == 1) {
            this.isWall = true;
            this.status = Cell.WALL_STAT;
            this.curColor = WALL_COLOR;
            setImg(WALL_IMG);

        } else if (wall == 0) {
            this.isWall = false;
            this.status = Cell.PATH_STAT;
            this.curColor = PATH_COLOR;
            setImg(PATH_IMG);
        }
    }

    public boolean getWall() {
        return this.isWall;
    }

    @Override
    public String toString() {
        if (isWall) {
            return "#";
        } else {
            return " ";
        }
    }

}
