package GUI;

/**
 *
 * @author
 */
import LOGIC.*;
import acm.graphics.GLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import LOGIC.Vlad;

public class Maze extends JPanel implements ActionListener, KeyListener {

    private Cell[][] tiles;
    private Map map;
    private JFrame frame;
    private AlgorithmUsingStack algo;
    private AlgorithmUsingQueue anotherOne;
    public int totalX, totalY;
    private String score;

    public Maze(File f, JFrame frame) {
        this.frame = frame;
        this.map = Map.loadMap(f);
        this.tiles = new Cell[map.getColums()][map.getRows()];
        this.algo = new AlgorithmUsingStack(this);
        this.generateMaze();
        //this.anotherOne = new AlgorithmUsingQueue(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        Timer t = new Timer(1, this);
        t.start();
    }

    public AlgorithmUsingStack getAlgorithm() {
        return this.algo;
    }

    public Cell[][] getTiles() {
        return this.tiles;
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void setAnotherAlgo(AlgorithmUsingQueue a){
        this.anotherOne = a;
    }

    public void swap(int x1, int y1, int x2, int y2) {
        this.tiles[y1][x1].setPosX(x2);
        this.tiles[y1][x1].setPosY(y2);
        this.tiles[y2][x2].setPosX(x1);
        this.tiles[y2][x2].setPosY(y1);
        Cell cowTemp = this.tiles[y1][x1];
        this.tiles[y1][x1] = this.tiles[y2][x2];
        this.tiles[y2][x2] = cowTemp;

    }

    public Map getMap() {
        return this.map;
    }

    public void generateMaze() {
        int width = 0, height = 0;
        if (map.getColums() >= 15 && map.getColums() < 20 || map.getRows() >= 15 && map.getRows() < 20) {
            width = 40;
            height = 40;
            totalX = map.getColums() * 40;
            totalY = map.getRows() * 40;
        } else if (map.getColums() >= 20 && map.getColums() < 26 || map.getRows() >= 20 && map.getRows() < 26) {
            width = 35;
            height = 35;
            totalX = map.getColums() * 35;
            totalY = map.getRows() * 35;
        } else {
            width = 25;
            height = 25;
            totalX = map.getColums() * 25;
            totalY = map.getRows() * 25;
        }
        int x = 0, y = 0;
        for (int i = 0; i < map.getColums(); i++) {
            for (int j = 0; j < map.getRows(); j++) {
                if (map.getMap()[i][j] == Map.PATH) {
                    Tile t = new Tile(x, y, j, i, width, height);
                    t.setWall(Tile.NOT_WALL);
                    tiles[i][j] = t;
                } else if (map.getMap()[i][j] == Map.WALL) {
                    Tile t = new Tile(x, y, j, i, width, height);
                    t.setWall(Tile.IS_WALL);
                    tiles[i][j] = t;
                } else if (map.getMap()[i][j] == Map.VLAD) {
                    Vlad r = new Vlad(x, y, j, i, width, height);
                    tiles[i][j] = r;
                } else if (map.getMap()[i][j] == Map.GOAL) {
                    Goal c = new Goal(x, y, j, i, width, height, frame);
                    tiles[i][j] = c;
                }
                x += width + 2;
            }
            y += height + 2;
            x = 0;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = 0, height = 0;
        if (map.getColums() >= 15 && map.getColums() < 20 || map.getRows() >= 15 && map.getRows() < 20) {
            width = 30;
            height = 30;
        } else if (map.getColums() >= 20 && map.getColums() < 26 || map.getRows() >= 20 && map.getRows() < 26) {
            width = 40;
            height = 40;
        } else {
            width = 25;
            height = 25;
        }
        int x = 0, y = 0;
        for (int i = 0; i < map.getColums(); i++) {
            for (int j = 0; j < map.getRows(); j++) {
                if (tiles[i][j] instanceof Tile) {
                    if (((Tile) tiles[i][j]).getWall()) {
                        g2d.drawImage(tiles[i][j].img, x, y, width, height, null);
                    } else {
                        g2d.drawImage(tiles[i][j].img, x, y, width, height, null);
                    }
                } else if (tiles[i][j] instanceof Vlad) {
                    g2d.drawImage(tiles[i][j].img, x, y, width, height, null);
                } else if (tiles[i][j] instanceof Goal) {
                    g2d.drawImage(tiles[i][j].img, x, y, width, height, null);
                }
                x += width;
            }
            y += height;
            x = 0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < map.getColums(); i++) {
            for (int j = 0; j < map.getRows(); j++) {
                repaint(tiles[i][j]);
            }
        }
        updateUI();
    }

    public Vlad findVlad() {
        Vlad r = null;
        for (int i = 0; i < this.map.getColums(); i++) {
            for (int j = 0; j < this.map.getRows(); j++) {
                if (tiles[i][j] instanceof Vlad) {
                    r = (Vlad) tiles[i][j];
                }
            }
        }
        return r;
    }

    public Goal findGoal() {
        Goal c = null;
        for (int i = 0; i < this.map.getColums(); i++) {
            for (int j = 0; j < this.map.getRows(); j++) {
                if (tiles[i][j] instanceof Goal) {
                    c = (Goal) tiles[i][j];
                }
            }
        }
        return c;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    GLabel ScoreDisplay = new GLabel("Score: " + score);

    @Override
    public void keyPressed(KeyEvent e) {
        Vlad r = null;

        for (int i = 0; i < map.getColums(); i++) {
            for (int j = 0; j < map.getRows(); j++) {
                if (tiles[i][j] instanceof Vlad) {
                    r = (Vlad) tiles[i][j];
                }
            }
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (r != null) {
                    r.moveLeft(this);
                    ScoreDisplay.isVisible();
                    ScoreDisplay.sendForward();
                    ScoreDisplay.setLocation(10, 20);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (r != null) {
                    r.moveRight(this);
                }
                ScoreDisplay.isVisible();
                ScoreDisplay.sendForward();
                ScoreDisplay.setLocation(10, 20);
                break;
            case KeyEvent.VK_UP:
                if (r != null) {
                    r.moveUp(this);
                }
                ScoreDisplay.isVisible();
                ScoreDisplay.sendForward();
                ScoreDisplay.setLocation(10, 20);
                break;
            case KeyEvent.VK_DOWN:
                if (r != null) {
                    r.moveDown(this);
                }
                ScoreDisplay.isVisible();
                ScoreDisplay.sendForward();
                ScoreDisplay.setLocation(10, 20);
                break;
            case KeyEvent.VK_ENTER:
                if (r != null) {
                    algo.start();
                }
                break;

            case KeyEvent.VK_BACK_SPACE:
                if (r != null) //System.out.println(anotherOne);
                {
                    algo.start();
                }
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < map.getColums(); i++) {
            for (int j = 0; j < map.getRows(); j++) {
                if (tiles[i][j] instanceof Vlad) {
                    s += "S";
                }
                if (tiles[i][j] instanceof Goal) {
                    s += "G";
                }
                if (tiles[i][j] instanceof Tile) {
                    if (((Tile) tiles[i][j]).getWall()) {
                        s += "#";
                    } else {
                        s += " ";
                    }
                }
            }
            s += "\n";
        }
        return s;
    }
}
