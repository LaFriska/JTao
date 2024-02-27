package com.friska.jtau.linalg;

import java.util.Arrays;

public class Matrix {

    private final float[][] state;
    private final MatrixDimension dimensions;


    /**
     * The Matrix class represents a matrix in mathematics where it has more than 1 number of row or columns. The matrix may
     * be further inherited by specific forms of matrices, such as vectors, and square matrices. Thus, the matrix object is malleable
     * and vectors can also be parsed as matrices.
     * **/
    public Matrix(float[][] inputArray){
        if(inputArray == null) throw new IncompatibleMatrixException("The input array must not be null.");
        if(inputArray.length == 0) throw new IncompatibleMatrixException("The input array length must be greater than 0.");
        inputArray = arrayCopy(inputArray);
        checkAndFill(inputArray);
        state = inputArray;
        dimensions = new MatrixDimension(state.length, state[0].length);
    }

    /**
     * Checks to make sure that the input array into the matrix is a rectangle. If not, every row length is changed to match
     * the longest row length in the input array, and the newly created slots are filled with 0s.
     **/
    private static void checkAndFill(float[][] inputArray){
        boolean needModification = false;
        int longestRow = inputArray[0].length;
        for(int r = 1; r < inputArray.length; r++){
            if(inputArray[r].length != longestRow){
                needModification = true;
                if(inputArray[r].length > longestRow) longestRow = inputArray[r].length;
            }
        }
        if(needModification) for(int r = 0; r < inputArray.length; r++){
            if(inputArray[r].length != longestRow){
                inputArray[r] = Arrays.copyOf(inputArray[r], longestRow);
            }
        }
    }

    /**
     * Clones the input array.
     * */
    private static float[][] arrayCopy(float[][] array){
        float[][] res = new float[array.length][];
        for (int r = 0; r < array.length; r++) {
            res[r] = new float[array[r].length];
            System.arraycopy(array[r], 0, res[r], 0, array[r].length);
        }
        return res;
    }

    /**
     * Returns an instance of the MatrixDimension record, where its row length and column length are held.
     * **/
    public final MatrixDimension getDimensions() {
        return dimensions;
    }

    /**
     * Returns the number of rows there are in the matrix.
     * **/
    public final int getRowLength(){
        return dimensions.row();
    }

    /**
     * Returns the number of columns there are in the matrix.
     * **/
    public final int getColLength(){
        return dimensions.col();
    }

    /**
     * Checks if the matrix could be represented as a square matrix.
     * **/
    public final boolean isSquare(){
        return dimensions.row() == dimensions.col();
    }

    /**
     * Checks if the matrix could be represented as a horizontal vector.
     * **/
    public final boolean isHorizontalVector(){
        return dimensions.row() == 1;
    }

    /**
     * Checks if the matrix could be represented as a vertical vector.
     * **/
    public final boolean isVerticalVector(){
        return dimensions.col() == 1;
    }

    /**
     * Retrieves an entry from the matrix given the row and col index. Note that this linear algebra
     * library uses 0-indexing, meaning that the first entry into a matrix always have an index of 0.
     * **/
    public float get(int row, int col){
        if(row >= dimensions.row() || row < 0) throw new IncompatibleMatrixException("Row index of " + row + " is out of bounds.");
        if(col >= dimensions.col() || col < 0) throw new IncompatibleMatrixException("Column index of " + col + " is out of bounds.");
        return state[row][col];
    }

    /**
     * Creates and returns a new SquareMatrix object with the same float array.
     *
     * @throws IncompatibleMatrixException If the number of rows and columns do not match.
     * **/
    public SquareMatrix toSquareMatrix(){
        if(!isSquare()) throw new IncompatibleMatrixException("Cannot convert a non-square matrix into a SquareMatrix object.");
        return new SquareMatrix(state);
    }

    /**
     * Creates and returns a new Vector object with the same data.
     *
     * @throws IncompatibleMatrixException If the number of columns is not 1.
     * **/
    public Vector toVector(){
        if(!isVerticalVector()) throw new IncompatibleMatrixException("Cannot convert a matrix with more than 1 columns to a vector.");
        return getColumnVector(0);
    }

    /**
     * Retrieves one column from the matrix and returns it as a vector. This is used in matrix multiplications, and more.
     * **/
    public Vector getColumnVector(int columnIndex){
        if(columnIndex >= dimensions.col() || columnIndex < 0) throw new IncompatibleMatrixException("Cannot extract column vector, as column index of " + columnIndex + " is out of bounds.");
        float[] vec = new float[dimensions.row()];
        for(int r = 0; r < dimensions.row(); r++){
            vec[r] = state[r][columnIndex];
        }
        return new Vector(vec);
    }

    /**
     * Creates and returns a matrix representation of a row vector from the float array.
     * **/
    public Matrix getRowVector(int rowIndex){
        if(rowIndex >= dimensions.row() || rowIndex < 0) throw new IncompatibleMatrixException("Cannot extract row vector, as row index of " + rowIndex + " is out of bounds.");
        float[][] vec = new float[1][dimensions.col()];
        vec[0] = state[rowIndex];
        return new Matrix(vec);
    }

    /**
     * Creates and returns a new matrix object with the same float array. This method is mainly designed
     * for classes that inherit the matrix class, that the user wants to parse into a matrix object.
     * **/
    public Matrix toMatrix(){
        return new Matrix(state);
    }

    /**
     * With a specified row and column length, this method takes a float array of entries and
     * formats it into a matrix. The procedure follows the way you would read a book (in a Western country),
     * from left to right, top to down, where each element of the first row is input first, then each element of
     * the second row, and so on.
     *
     * @throws IncompatibleMatrixException the row length multiplied by column length is not equal to the numbers of entries
     * **/
    public static Matrix formulate(int rowLength, int colLength, float... entries){
        if(rowLength * colLength != entries.length) throw new IncompatibleMatrixException("Cannot format entries of length " + entries.length + " into a " + rowLength + " x " + colLength + " matrix.");
        int count = 0;
        float[][] state = new float[rowLength][colLength];
        for(int r = 0; r < rowLength; r++){
            for(int c = 0; c < colLength; c++){
                state[r][c] = entries[count];
                count++;
            }
        }
        return new Matrix(state);
    }


    /**
     * Represents the matrix as a string. This is mainly for testing purposes, where the matrix could be printed on
     * the console in similar way as it is represented in mathematics. An example of this would be as follows:
     * <p>
     * ⌈4 2 6 1 5⌉ <p>
     * |3 6 9 2 2|<p>
     * |2 2 6 4 5|<p>
     * |4 6 2 9 8|<p>
     * ⌊0 2 2 2 4⌋<p>
     * **/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < dimensions.row(); r++){
            sb.append(dimensions.row() > 1 ? (r == 0 ? "⌈" : r == dimensions.row() - 1 ? "⌊" : "|") : "[");
            for(int c = 0; c < dimensions.col(); c++){
                sb.append(NumberUtils.format(state[r][c]));
                if(c != dimensions.col() - 1) sb.append(" ");
            }
            sb.append(dimensions.row() > 1 ? (r == 0 ? "⌉" : r == dimensions.row() - 1 ? "⌋" : "|") : "]").append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns the Tex string used to represent the matrix.
     *
     * @param id Signifies what type of brackets around the matrix is desired.
     * **/
    public String getTex(String id){ //TODO javadoc
        StringBuilder rowString = new StringBuilder("\\begin{" + id + "}").append("\n");
        for (int r = 0; r < dimensions.row(); r++) {
            for (int c = 0; c < dimensions.col(); c++) {
                rowString.append(NumberUtils.format(get(r, c))).append(c == dimensions.col() - 1 ?  r == dimensions.row() - 1 ? "" : "\\\\" : "&");
            }
            rowString.append("\n");
        }
        rowString.append("\\end{").append(id).append("}");
        return rowString.toString();
    }


    /**
     * Returns the Tex string used to represent the matrix using conventional square brackets.
     * */
    public String getTex(){
        return getTex("bmatrix");
    }

    /**
     * Returns the Tex string used to represent the matrix using vertical lines as brackets
     * signifying the determinant.
     * */
    public String getTexDeterminant(){
        return getTex("vmatrix");
    }

    /**
     * @return The state float array.
     * */
    protected float[][] getState() {
        return state;
    }
}
