package LOGIC;

/**
 *
 * @author
 */
import java.io.*;

public class Map {

    //private String mapName;
    private int rows, colums;
    private int[][] map;
    public final static int PATH = 0;
    public final static int WALL = 1;
    public final static int VLAD = 2;
    public final static int GOAL = 3;
    public final static int MAX_SIZE = 30;
    public final static int MIN_SIZE = 15;
    public final static int NORTH = 100;
    public final static int SOUTH = 200;
    public final static int EAST = 300;
    public final static int WEST = 400;

    public Map(int rows, int colums) {
        this.rows = rows;
        this.colums = colums;
        this.map = new int[colums][rows];
        for (int x = 0; x < colums; x++) {
            for (int y = 0; y < rows; y++) {
                map[x][y] = WALL;
            }
        }
    }

    public int getRows() {
        return this.rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColums() {
        return this.colums;
    }

    public void setColums(int colums) {
        this.colums = colums;
    }

    public int[][] getMap() {
        return this.map;
    }

    public void setMap(int x, int y, int status) {
        this.map[y][x] = status;
    }

    public boolean isLegitMap() {
        int vladCount = 0;
        int goalCount = 0;

        for (int i = 0; i < this.colums; i++) {
            for (int j = 0; j < this.rows; j++) {
                if (map[i][j] == VLAD) {
                    vladCount++;
                } else if (map[i][j] == GOAL) {
                    goalCount++;
                }
            }
        }

        return vladCount == 1 && goalCount == 1 && this.rows <= MAX_SIZE && this.colums <= MAX_SIZE && this.rows >= MIN_SIZE && this.colums >= MIN_SIZE;
    }


   public static Map loadMap(File f) {
        BufferedReader br = null;
        String line;
        Map m = null;
        String con = "";

        try {
            br = new BufferedReader(new FileReader(f));
            int content;
            int ctr;
            while ((line = br.readLine()) != null) {
                con = con + line + "\n";
            }
        } catch (IOException e) {
            System.out.println("(Map:loadMap): " + e.toString());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e2) {
                }
            }
        }

        int row = Integer.parseInt(con.substring(0, con.indexOf(" ")));
        int col = Integer.parseInt(con.substring(con.indexOf(" ") + 1, con.indexOf("\n")));
        con = con.substring(con.indexOf("\n") + 1);
        char[] arr = con.replace("\n", "").toCharArray();
        int x = 0, y = 0;
        m = new Map(row, col);
        if (row * col == arr.length) {
            for (int i = 0; i < arr.length; i++) {
                if (x % row == 0 && x != 0) {
                    x = 0;
                    y++;
                }
                if (arr[i] == ' ') {
                    m.setMap(x, y, PATH);
                }
                if (arr[i] == '#') {
                    m.setMap(x, y, WALL);
                }
                if (arr[i] == 'G') {
                    m.setMap(x, y, GOAL);
                }
                if (arr[i] == 'S') {
                    m.setMap(x, y, VLAD);
                }
                x++;
            }
        } else {

        }
        return m;
    }

}
