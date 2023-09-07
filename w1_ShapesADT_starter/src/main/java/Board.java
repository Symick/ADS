
public class Board {
    private int boardSize;
    private Shape[][] board;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.board = new Shape[boardSize][boardSize];
    }

    public boolean add(Shape shape, int x, int y) {
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

    public Shape remove(int x, int y) {
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
            throw new IndexOutOfBoundsException(
                    String.format("Position %d,%d is not available on a board of size %d", x, y, boardSize)
            );
        }

        if (board[x][y] == null) {
            return null;
        }
        Shape shape = board[x][y];
        board[x][y] = null; //remove shape from array, aka set the grid location to null.
        return shape; // return the shape removed

    }

    public Shape[][] getGrid() {
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
