package assignment2018.codeprovided;

import assignment2018.codeprovided.Piece;

public class Move {

    private Piece p;
    private int initialX;
    private int initialY;
    private int finalX;
    private int finalY;
    private boolean isOccupied;


    public Move(Piece p, int ix, int iy, int fi, int fy, boolean b) {
        this.p = p;
        this.initialX = ix;
        this.initialY = iy;
        this.finalX = fi;
        this.finalY = fy;
        this.isOccupied = b;

    }

    /**
     *
     * return :true if both move objects hava the same state
     * */
    public boolean sameMove(Move m){

        if(this.p == m.getP() && this.getInitialX()==m.getinitialX() && this.getInitialY()== m.getInitialY() && this.finalX ==m.getFinalX()&& this.getFinalY() ==p.getFinalY() && this.isOccupied()== m.isOccupied()) ;
    }
    // ACCESSOR METHODS

    /**

     */

    // initial and final X coordinates
    public int getInitialX() {
        return initialX;
    }

    public int getFinalX() {
        return finalX;
    }

    // initial and final Y coordinates
    public int getInitialY() {
        return this.initialY;
    }

    public int getFinalY() {
        return finalY;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Piece getP() {
        return p;
    }


    // MUTATOR methods
    public void setInitialX(int initialX) {
        this.initialX = initialX;
    }

    public void setFinalX(int finalX) {
        this.finalX = finalX;
    }

    public int setInitialY() {
        return initialY;
    }

    public void setFinalY(int finalY) {
        this.finalY = finalY;
    }

    public void setP(Piece p) {
        this.p = p;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }




    // straight line moves
    boolean vertical;
    boolean horizontal;
    boolean diagonal;

    // checks whether a piece can move from start to end vertically


    public boolean straightLineCheckVertical() {
        // if the initialX and finalX are different or
        // if the final coordinates are out of range, or initialX == finalX  return false
        if (this.p.getBoard().outOfRange(this.finalX, this.finalY) || this.initialY == this.finalY || !(this.initialX == this.finalX)) {
            vertical = false;
        }

        // if the final coordinates are in the range and initialY != finalY
        else {

            boolean verticalSearchTrue = false;
            boolean oppAtFinalCoords = false;

            int start = this.getInitialY();
            if (this.initialY < this.finalY) {

                while (!verticalSearchTrue) {
                    start += 1;
                    verticalSearchTrue = (this.p.getBoard().occupied(this.initialX, start));

                    // if the final position is occupied by an opponent, the position is occupiable
                    if (start == this.finalY && this.p.getColour() != p.getBoard().getPiece(this.finalX, start).getColour() && this.p.getBoard().occupied(this.initialX, start)) {

                        verticalSearchTrue = true;
                        oppAtFinalCoords = true;
                    }

                    // if the final position is occupied by an opponent, the position is not occupiable
                    if (start == this.finalY && this.p.getColour() == p.getBoard().getPiece(this.finalX, start).getColour() && this.p.getBoard().occupied(this.initialX, start)) {

                        verticalSearchTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }

                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied

                    else if (start == this.finalY && !this.p.getBoard.occupied(this.initialX, start)) {

                        break;
                    }
                }
                vertical = verticalSearchTrue;
            } else if (this.initialY > this.finalY) {

                while (!verticalSearchTrue) {
                    start -= 1;
                    verticalSearchTrue = (this.p.getBoard().occupied(this.initialX, start));

                    // if the final position is occupied by an opponent, the position is occupiable
                    if (start == this.finalY && this.p.getColour() != p.getBoard().getPiece(this.finalX, start).getColour() && this.p.getBoard().occupied(this.initialX, start)) {

                        verticalSearchTrue = true;
                        oppAtFinalCoords = true;
                    }

                    // if the final position is occupied by an opponent, the position is not occupiable
                    if (start == this.finalY && this.p.getColour() == p.getBoard().getPiece(this.finalX, start).getColour() && this.p.getBoard().occupied(this.initialX, start)) {

                        verticalSearchTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }

                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied

                    else if (start == this.finalY && !this.p.getBoard.occupied(this.initialX, start)) {

                        break;

                    }
                }
            }
            vertical = verticalSearchTrue;

        }
        return vertical;

    }


    // checks whether a piece can move from start to end horizontally (for Rook and queen)
    public boolean straightLineCheckHorizontal() {

        // if the initialY and finalY are different or
        // if the final coordinates are out of range, return false
        if (this.p.getBoard().outOfRange(this.finalX, this.finalY) || (this.initialY != this.finalY) || (this.initialX == this.finalX)) {
            horizontal = false;
        }

        // if the final coordinates are in the range and initialX != finalX
        else {

            boolean horizontalSearchTrue = false;
            boolean oppAtFinalCoords = false;

            int start = this.getInitialX();
            if (this.getInitialX() < this.getFinalX()) {

                while (!horizontalSearchTrue) {
                    start += 1;
                    // check for the horizontally right moves
                    horizontalSearchTrue = (this.p.getBoard().occupied(start, this.getInitialY()));

                    // if the final position is occupied by an opponent(only different color)
                    if ((start == this.getFinalX()) && (this.p.getColour() != p.getBoard().getPiece(start, this.getFinalY()).getColour()) && (this.p.getBoard().occupied(start, this.getFinalY()))) {

                        horizontalSearchTrue = true;
                        oppAtFinalCoords = true;
                    }

                    // if the final position is occupied by an opponent, the position is not occupiable
                    if ((start == this.getFinalX()) && (this.p.getColour() == p.getBoard().getPiece(start, this.getFinalY()).getColour()) && (this.p.getBoard().occupied(start, this.getFinalY()))) {

                        horizontalSearchTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }

                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied

                    else if (start == this.getFinalX() && !this.p.getBoard.occupied(start, this.getFinalY())) {

                        break;
                    }
                }
                horizontal = horizontalSearchTrue;
            } else if (this.getInitialX() > this.getFinalX()) {


                while (!horizontalSearchTrue) {
                    start -= 1;
                    // check for the horizontally right moves
                    horizontalSearchTrue = (this.p.getBoard().occupied(start, this.getInitialY()));

                    // if the final position is occupied by an opponent(only different color)
                    if ((start == this.getFinalX()) && (this.p.getColour() != p.getBoard().getPiece(start, this.getFinalY()).getColour()) && (this.p.getBoard().occupied(start, this.getFinalY()))) {

                        horizontalSearchTrue = true;
                        oppAtFinalCoords = true;
                    }

                    // if the final position is occupied by an opponent, the position is not occupiable
                    if ((start == this.getFinalX()) && (this.p.getColour() == p.getBoard().getPiece(start, this.getFinalY()).getColour()) && (this.p.getBoard().occupied(start, this.getFinalY()))) {

                        horizontalSearchTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }

                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied

                    else if (start == this.getFinalX() && !this.p.getBoard.occupied(start, this.getFinalY())) {

                        break;
                    }
                }
                horizontal = horizontalSearchTrue;

            }
        }

        return horizontal;
    }

    // checking the validity of a diagonal move
    // for bishop and queen

    public boolean straightLineCheckDiagonal() {

        //boolean diagonalXTrue = false ;
        //boolean diagonalYTrue = false ;

        int startX = this.getInitialX();
        int startY = this.getInitialY();
        boolean oppAtFinalCoords;

        // if the final coordinates are out of range, or the return false
        if (this.p.getBoard().outOfRange(this.getFinalX(), this.getFinalY())|| (this.getInitialY()==this.getFinalY()) ||(this.getInitialX() == this.getFinalX())) {
            diagonal = false;
        } else {
            boolean diagonalTrue = false;
            // move towards top-right direction
            if ((this.getInitialX() < this.getFinalX()) && (this.getInitialY() < this.getFinalY())) {

                while (!diagonalTrue) {

                    startX += 1;
                    startY += 1;

                    //will turn false if the next diagonally top-right box is occupied
                    diagonalTrue = (this.p.getBoard().occupied(startX, startY));

                    // if the path is clear till the final coordinates check whether the destination coordinates are occupied or not
                    //if occupied, check the color of the piece, if same color return false, if different color - true
                    if ((startX == this.getFinalX()) && (startY == this.getFinalY()) && (this.p.getColour() != p.getBoard().getPiece(startX, startY).getColour()) && (this.p.getBoard().occupied(startX, startY))) {

                        diagonalTrue = true;
                        oppAtFinalCoords = true;

                    } else if ((startX == this.getFinalX()) && (startY == this.getFinalY()) && (this.p.getColour() == p.getBoard().getPiece(startX, startY).getColour()) && (this.p.getBoard().occupied(startX, startY))) {

                        diagonalTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }


                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied

                    else if (startX == this.getFinalX() && (startY == this.getFinalY()) && !(this.p.getBoard.occupied(startX, startY))) {

                        break;
                    }
                }
                diagonal = diagonalTrue;
            }

            // a move to bottom left direction
            else if ((this.getInitialX() > this.getFinalX()) && (this.getInitialY() > this.getFinalY())) {


                while (!diagonalTrue) {

                    startX -= 1;
                    startY -= 1;

                    //will turn false if the next diagonally top-right box is occupied
                    diagonalTrue = (this.p.getBoard().occupied(startX, startY));

                    // if the path is clear till the final coordinates check whether the destination coordinates are occupied or not
                    //if occupied, check the color of the piece, if same color return false, if different color - true
                    if ((startX == this.getFinalX()) && (startY == this.getFinalY()) && (this.p.getColour() != p.getBoard().getPiece(startX, startY).getColour()) && (this.p.getBoard().occupied(startX, startY))) {

                        diagonalTrue = true;
                        oppAtFinalCoords = true;

                    } else if ((startX == this.getFinalX()) && (startY == this.getFinalY()) && (this.p.getColour() == p.getBoard().getPiece(startX, startY).getColour()) && (this.p.getBoard().occupied(startX, startY))) {

                        diagonalTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }


                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied

                    else if (startX == this.getFinalX() && (startY == this.getFinalY()) && !(this.p.getBoard.occupied(startX, startY))) {

                        break;
                    }
                }
                diagonal = diagonalTrue;


            }

            // move towards bottom -right direction
            else if ((this.getInitialX() < this.getFinalX()) && (this.getInitialY() > this.getFinalY())) {


                while (!diagonalTrue) {

                    startX += 1;
                    startY -= 1;

                    //will turn false if the next diagonally top-right box is occupied
                    diagonalTrue = (this.p.getBoard().occupied(startX, startY));

                    // if the path is clear till the final coordinates check whether the destination coordinates are occupied or not
                    //if occupied, check the color of the piece, if same color return false, if different color - true
                    if ((startX == this.getFinalX()) && (startY == this.getFinalY()) && (this.p.getColour() != p.getBoard().getPiece(startX, startY).getColour()) && (this.p.getBoard().occupied(startX, startY))) {

                        diagonalTrue = true;
                        oppAtFinalCoords = true;

                    } else if ((startX == this.getFinalX()) && (startY == this.getFinalY()) && (this.p.getColour() == p.getBoard().getPiece(startX, startY).getColour()) && (this.p.getBoard().occupied(startX, startY))) {

                        diagonalTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }


                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied

                    else if (startX == this.getFinalX() && (startY == this.getFinalY()) && !(this.p.getBoard.occupied(startX, startY))) {

                        break;
                    }
                }
                diagonal = diagonalTrue;
            }

            // move towards top -left direction
            else if ((this.getInitialX() > this.getFinalX()) && (this.getInitialY() < this.getFinalY())) {


                while (!diagonalTrue) {

                    startX -= 1;
                    startY += 1;

                    //will turn false if the next diagonally top-right box is occupied
                    diagonalTrue = (this.p.getBoard().occupied(startX, startY));

                    // if the path is clear till the final coordinates check whether the destination coordinates are occupied or not
                    //if occupied, check the color of the piece, if same color return false, if different color - true
                    if ((startX == this.getFinalX()) && (startY == this.getFinalY()) && (this.p.getColour() != p.getBoard().getPiece(startX, startY).getColour()) && (this.p.getBoard().occupied(startX, startY))) {

                        diagonalTrue = true;
                        oppAtFinalCoords = true;

                    } else if ((startX == this.getFinalX()) && (startY == this.getFinalY()) && (this.p.getColour() == p.getBoard().getPiece(startX, startY).getColour()) && (this.p.getBoard().occupied(startX, startY))) {

                        diagonalTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }


                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied

                    else if (startX == this.getFinalX() && (startY == this.getFinalY()) && !(this.p.getBoard.occupied(startX, startY))) {

                        break;
                    }
                }
                diagonal = diagonalTrue;

            }
        }

        return diagonal ;
    }





}
