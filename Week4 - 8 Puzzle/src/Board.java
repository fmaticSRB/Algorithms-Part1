 /***********************************************************
 * AUTHOR: Filip Matic
 * Title: Board.java
 * Description: Creates a data type that models an 
 * n-by-n board with sliding tiles. 
 ************************************************************/
import java.util.Arrays;
import java.util.Stack;


public final class Board 
{
    private final int[][] blocks;

    // create a board from an n-by-n array of tiles
    // where tiles[row][col] tile at (row, col)
    public Board(int[][] blocks) 
    {
        this.blocks = copy(blocks);
    }

    public static void main(String[] args) 
    {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 5}});
        System.out.println(board.toString());
        for (Board board1 :
                board.neighbors()) {
            System.out.println(board1);
        }

    }

    private int[][] copy(int[][] blocks) 
    {
        int[][] copy = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) 
            {
                copy[i][j] = blocks[i][j];
            }
        }
        return copy;
    }

    // String representation of this board
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < blocks.length; i++) 
        {
            for (int j = 0; j < blocks.length; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // Board dimension n
    public int dimension() 
    {
        return blocks.length;
    }

    // Does this board equal y
    @Override
    public boolean equals(Object obj) 
    {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Board that = (Board) obj;
        return Arrays.deepEquals(this.blocks, that.blocks);
    }

    // A board that is obtained by exchanging any pair of tiles
    public Board twin() 
    {
        int[][] twinTiles = copy(this.blocks);

        if (twinTiles[0][0] != 0 && twinTiles[0][1] != 0)
            return new Board(swap(0, 0, 0, 1));
        else
            return new Board(swap(1, 0, 1, 1));
    }

    private int[][] swap(int i1, int j1, int i2, int j2) 
    {
        int[][] copy = copy(blocks);
        int temp = copy[i1][j1];
        copy[i1][j1] = copy[i2][j2];
        copy[i2][j2] = temp;
        return copy;
    }

    // Is this board the goal board
    public boolean isGoal() 
    {
        return hamming() == 0;
    }

    // All neighboring boards
    public Iterable<Board> neighbors() 
    {
        Stack<Board> neighbours = new Stack<>();
        int position = blankPosition();
        int i = position / dimension();
        int j = position % dimension();
        if (i > 0)
            neighbours.push(new Board(swap(i, j, i - 1, j)));
        if (i < blocks.length - 1)
            neighbours.push(new Board(swap(i, j, i + 1, j)));
        if (j > 0)
            neighbours.push(new Board(swap(i, j, i, j - 1)));
        if (j < blocks.length - 1)
            neighbours.push(new Board(swap(i, j, i, j + 1)));

        return neighbours;
    }

    private int blankPosition() 
    {
        for (int i = 0; i < blocks.length; i++)
            for (int j = 0; j < blocks.length; j++)
                if (blocks[i][j] == 0)
                    return j + i * dimension();
        return -1;
    }

    // Number of tiles out of place
    public int hamming() 
    {
        int hamming = 0;
        for (int i = 0; i < blocks.length; i++)
            for (int j = 0; j < blocks.length; j++)
                if (blocks[i][j] != 0 && blocks[i][j] != j + i * dimension() + 1) hamming++;
        return hamming;
    }

    // Sum of Manhattan distances between tiles and goal
    public int manhattan() 
    {
        int manhattan = 0;
        for (int i = 0; i < blocks.length; i++)
            for (int j = 0; j < blocks.length; j++)
                if (blocks[i][j] != 0 && blocks[i][j] != j + i * dimension() + 1)
                    manhattan += manhattanDistance(i, j, blocks[i][j]);
        return manhattan;
    }

    private int manhattanDistance(int i, int j, int square) 
    {
        square--;
        int horizontal = Math.abs(square % dimension() - j);
        int vertical = Math.abs(square / dimension() - i);
        return horizontal + vertical;
    }
}