import java.util.Iterator;
import java.util.NoSuchElementException;

public class Board <S extends Shape> implements Iterable<S> {
    private int boardSize;
    private S[][] board;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.board = (S[][]) new Shape[boardSize][boardSize];
    }

    private class BoardIterator implements Iterator<S> {
        private int currentRow = 0;
        private int currentCol = 0;
        @Override
        public boolean hasNext() {
            return currentRow < board.length && currentCol < board[currentRow].length;
        }

        @Override
        public S next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            //get current element
            S shape = board[currentCol][currentRow];

            currentCol++;

            if (currentCol == board[currentRow].length) {
                currentCol = 0;
                currentRow++;
            }

            return shape;
        }

    }

    public Iterator<S> iterator() {
        return new BoardIterator();
    }

    public boolean add(S shape, int x, int y) {
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
            throw new IndexOutOfBoundsException(
                    String.format("Position %d,%d is not available on a board of size %d", x, y, boardSize)
            );
        }

        //if there is a shape then return false.
        if (board[x][y] != null) {
            return false;
        }
        board[x][y] = shape;
        return true;

    }

    public S remove(int x, int y) {
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
            throw new IndexOutOfBoundsException(
                    String.format("Position %d,%d is not available on a board of size %d", x, y, boardSize)
            );
        }

        if (board[x][y] == null) {
            return null;
        }
        S shape = board[x][y];
        board[x][y] = null; //remove shape from array, aka set the grid location to null.
        return shape; // return the shape removed

    }

    public S[][] getGrid() {
        return board;
    }

    public double getShapesArea() {
        double area = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] != null) {
                    area += board[i][j].getArea();
                }
            }
        }
        return area;
    }
}
