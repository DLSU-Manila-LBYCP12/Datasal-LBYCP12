package LOGIC;

/**
 * A special Cell that can move around
 *
 * @author
 */
import java.awt.Color;
import GUI.Maze;
import LIST.MyList;
import acm.graphics.GLabel;

public class Vlad extends Cell implements PlayerListener {

    private int score = 0;
    private MyList<Integer> highScore = new MyList<Integer>();
    public final static Color VLAD_COLOR = Color.BLUE;
    public final static String VLAD_IMG = "C:\\Users\\Ryan\\Documents\\NetBeansProjects\\DATASALPROJECT\\resource\\Vlad.png";

    public Vlad(double x, double y, int posX, int posY, int width, int height) {
        super(x, y, posX, posY, width, height);
        this.status = Cell.VLAD_STAT;
        this.curColor = VLAD_COLOR;
        setImg(VLAD_IMG);
        highScore.createList();
        printWinner();
    }

    private void printWinner() {
        int n = 0;
        if (highScore.size() == 0) {
            highScore.add(1, score);
        } else {
            for (int i = 1; i <= highScore.size(); i++) {
                if (score >= highScore.get(i)) {
                    if (highScore.size() == 5) {
                        highScore.remove(5);
                    }
                    highScore.add(i, score);
                    n = 1;
                    break;
                }
            }
            if (n == 0) {
                highScore.add(highScore.size() + 1, score);
            }
        }
        for (int i = 1; i <= highScore.size(); i++) {
            GLabel item = new GLabel("" + i + ". " + highScore.get(i));
            System.out.println(highScore);
        }
    }

    private void setHighScore(int g) {
        score = g;
    }

    private int getHighScore() {
        return score;
    }

    @Override
    public void moveLeft(Maze m) {
        if (posX > 0) {
            if (m.getTiles()[posY][posX - 1] instanceof Tile) {
                if (!((Tile) m.getTiles()[posY][posX - 1]).getWall()) {
                    m.getTiles()[posY][posX].x -= super.width + 2;
                    m.getTiles()[posY][posX - 1].x += super.height + 2;
                    m.swap(posX, posY, posX - 1, posY);
                }
            } else if (m.getTiles()[posY][posX - 1] instanceof Goal) {
                ((Goal) m.getTiles()[posY][posX - 1]).goal();
                System.out.println(highScore);
            }
            score++;
            System.out.println(score);
            
        }
    }

    @Override
    public void moveRight(Maze m) {
        if (posX < m.getMap().getRows() - 1) {
            if (m.getTiles()[posY][posX + 1] instanceof Tile) {
                if (!((Tile) m.getTiles()[posY][posX + 1]).getWall()) {
                    m.getTiles()[posY][posX].x += super.width + 2;
                    m.getTiles()[posY][posX + 1].x -= super.height + 2;
                    m.swap(posX, posY, posX + 1, posY);
                }
            } else if (m.getTiles()[posY][posX + 1] instanceof Goal) {
                ((Goal) m.getTiles()[posY][posX + 1]).goal();
                System.out.println(highScore);
            }
            score++;
            System.out.println(score);
        }
    }

    @Override
    public void moveUp(Maze m) {
        if (posY > 0) {
            if (m.getTiles()[posY - 1][posX] instanceof Tile) {
                if (!((Tile) m.getTiles()[posY - 1][posX]).getWall()) {
                    m.getTiles()[posY][posX].y -= super.width + 2;
                    m.getTiles()[posY - 1][posX].y += super.height + 2;
                    m.swap(posX, posY, posX, posY - 1);
                }
            } else if (m.getTiles()[posY - 1][posX] instanceof Goal) {
                ((Goal) m.getTiles()[posY - 1][posX]).goal();
                System.out.println(highScore);
            }
            score++;
            System.out.println(score);
        }
    }

    @Override
    public void moveDown(Maze m) {
        if (posY < m.getMap().getColums() - 1) {
            if (m.getTiles()[posY + 1][posX] instanceof Tile) {
                if (!((Tile) m.getTiles()[posY + 1][posX]).getWall()) {
                    m.getTiles()[posY][posX].y += super.width + 2;
                    m.getTiles()[posY + 1][posX].y -= super.height + 2;
                    m.swap(posX, posY, posX, posY + 1);
                }
            } else if (m.getTiles()[posY + 1][posX] instanceof Goal) {
                ((Goal) m.getTiles()[posY + 1][posX]).goal();
                System.out.println(highScore);
            }
            score++;
            System.out.println(score);
        }
    }

    @Override
    public String toString() {
        return "S";
    }

}
