package assignment2018;

import assignment2018.codeprovided.Piece;


public class Move {

    private Piece p;
    private int initialX;
    private int initialY;
    private int finalX;
    private int finalY;
    private boolean occupied;



    public Move(Piece p, int ix, int iy, int fi, int fy, boolean b) {
        this.p = p;
        this.initialX = ix;
        this.initialY = iy;
        this.finalX = fi;
        this.finalY = fy;
        this.occupied = b;

    }

    /**
     *@param    m   object of the type move
     *@return       true if both Move objects hava the same state
     * */
    public boolean sameMove(Move m){

        if(this.p == m.getP() && this.getInitialX()==m.getInitialX() && this.getInitialY()== m.getInitialY() && this.finalX ==m.getFinalX()&& this.getFinalY() == m.getFinalY() && this.occupied()== m.occupied()){
            return true ;
        }
        else{
            return false ;
        }
    }
    // ACCESSOR METHODS

    /**
     *@return   int   the initial X coordinates of the move of object
     * */
    public int getInitialX() {
        return initialX;
    }

    /**
     *@return   int     the final X coordinates of the move
     * */
    public int getFinalX() {
        return finalX;
    }

    /**
     *
     * @return  int     the initial Y coordinates of the move
     * */
    public int getInitialY() {
        return this.initialY;
    }

    /**
     * @return  int     the final Y coordinates of the move object
     */
    public int getFinalY() {
        return finalY;
    }

    /**
     * @return  boolean     true if the Move eliminates a piece of the opponent
     * */
    public boolean occupied() {
        return occupied;
    }

    /**
     * @return  Piece the Piece whose Move object is defined
     * */
    public Piece getP() {
        return p;
    }



// MUTATOR methods

    /**
     * @param    initialX    the initial x coordinate of a Move Object
     * */
    public void setInitialX(int initialX) {
        this.initialX = initialX;
    }

    /**
     * @param    finalX      the final x coordinate of the Move object
     * */
    public void setFinalX(int finalX) {
        this.finalX = finalX;
    }

    /**
     *@param     finalY      the final Y coordinate of the Move object
     * */
    public void setFinalY(int finalY) {
        this.finalY = finalY;
    }

    /**
     * @param      p   the piece
     * */
    public void setP(Piece p) {
        this.p = p;
    }

    /**
     * @param    occupied    sets the occupied to true/false
     * */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
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
            int start = 0;
            if (this.initialY < this.finalY) {
                while (!verticalSearchTrue) {
                    start += 1;
                    int tempYCoord = start + this.getInitialY() ;
                    //verticalSearchTrue = (this.p.getBoard().occupied(this.initialX, start));

                    // if the any position b/w the startY and the finalY is occupied, return false
                    if(this.p.getBoard().occupied(this.initialX, tempYCoord) && tempYCoord != this.getFinalY() && !this.p.getBoard().outOfRange(this.initialX, tempYCoord)){
                        verticalSearchTrue = false ;
                        break ;
                    }

                    // if the final position is occupied by an opponent, the position is occupiable
                    if (tempYCoord == this.finalY && this.p.getBoard().occupied(this.initialX,tempYCoord) && this.p.getColour() != p.getBoard().getPiece(this.finalX, tempYCoord).getColour()) {
                        verticalSearchTrue = true;
                        oppAtFinalCoords = true;
                    }
                    // if the final position is occupied by an piece of same color, the position is not occupiable
                    if (tempYCoord == this.finalY && this.p.getBoard().occupied(this.initialX, tempYCoord) && this.p.getColour() == p.getBoard().getPiece(this.finalX, tempYCoord).getColour()) {
                        verticalSearchTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }
                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied
                    else if (tempYCoord == this.finalY && !this.p.getBoard().occupied(this.initialX, tempYCoord)) {
                        verticalSearchTrue = true ;
                        oppAtFinalCoords = false ;
                        break;
                    }
                }
                vertical = verticalSearchTrue;
            }
            else if (this.initialY > this.finalY) {
                while (!verticalSearchTrue) {
                    start -= 1;
                    //verticalSearchTrue = (this.p.getBoard().occupied(this.initialX, start));
                    // if the any position b/w the startY and the finalY is occupied, return false
                    int tempYCoord = this.getInitialY() + start ;
                    if(this.p.getBoard().occupied(this.initialX,tempYCoord) && tempYCoord!= this.getFinalY() && !this.p.getBoard().outOfRange(this.initialX, tempYCoord)){
                        verticalSearchTrue = false ;
                        break ;
                    }

                    // if the final position is occupied by an opponent, the position is occupiable
                    if ((tempYCoord == this.finalY) && this.p.getBoard().occupied(this.initialX, tempYCoord)  && (this.p.getColour() != p.getBoard().getPiece(this.finalX,tempYCoord).getColour()) ) {
                        verticalSearchTrue = true;
                        oppAtFinalCoords = true;
                    }
                    // if the final position is occupied by an opponent, the position is not occupiable
                    if (tempYCoord == this.finalY && this.p.getBoard().occupied(this.initialX, tempYCoord) && this.p.getColour() == p.getBoard().getPiece(this.finalX, tempYCoord).getColour()) {
                        verticalSearchTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }
                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied
                    else if (tempYCoord == this.finalY && !this.p.getBoard().occupied(this.initialX, tempYCoord)) {
                        verticalSearchTrue = true ;
                        oppAtFinalCoords = false ;
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

            int start = 0 ;
            if (this.getInitialX() < this.getFinalX()) {
                while (!horizontalSearchTrue) {
                    start += 1;
                    int tempXCoord= this.getInitialX() + start ;
                    // check for the horizontally right moves
                    // if any poition is occupied b/w the start and the end positions of the move return false
                    if(this.p.getBoard().occupied(tempXCoord,this.getInitialY()) && tempXCoord != this.getFinalX()&& !this.p.getBoard().outOfRange(tempXCoord,this.initialY)){
                        horizontalSearchTrue = false ;
                        break ;
                    }
                    //horizontalSearchTrue = (this.p.getBoard().occupied(start, this.getInitialY()));
                    // if the final position is occupied by an opponent(only different color)
                    if ((tempXCoord == this.getFinalX()) && (this.p.getBoard().occupied(tempXCoord, this.getFinalY())) && (this.p.getColour() != p.getBoard().getPiece(tempXCoord, this.getFinalY()).getColour())) {
                        horizontalSearchTrue = true;
                        oppAtFinalCoords = true;
                    }
                    // if the final position is occupied by an opponent, the position is not occupiable
                    if ((tempXCoord == this.getFinalX())  && (this.p.getBoard().occupied(tempXCoord, this.getFinalY())) && (this.p.getColour() == p.getBoard().getPiece(tempXCoord, this.getFinalY()).getColour())) {
                        horizontalSearchTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }
                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied
                    else if (tempXCoord == this.getFinalX() && !this.p.getBoard().occupied(tempXCoord, this.getFinalY())) {
                        horizontalSearchTrue = true ;
                        oppAtFinalCoords = false ;
                        break;
                    }
                }
                horizontal = horizontalSearchTrue;
            }
            else if (this.getInitialX() > this.getFinalX()) {
                while (!horizontalSearchTrue) {
                    start -= 1;
                    // check for the horizontally right moves
                    int tempXCoord = this.getInitialX() + start ;
                    // if any position is occupied b/w the start and the end positions of the move return false
                    if(this.p.getBoard().occupied(tempXCoord,this.getInitialY()) && tempXCoord!= this.getFinalX() && !this.p.getBoard().outOfRange(tempXCoord,this.initialY)){
                        horizontalSearchTrue = false ;
                        break ;
                    }
                    //horizontalSearchTrue = (this.p.getBoard().occupied(start, this.getInitialY()));
                    // if the final position is occupied by an opponent(only different color)
                    if ((tempXCoord == this.getFinalX()) && (this.p.getBoard().occupied(tempXCoord, this.getFinalY())) && (this.p.getColour() != p.getBoard().getPiece(tempXCoord, this.getFinalY()).getColour())) {
                        horizontalSearchTrue = true;
                        oppAtFinalCoords = true;
                    }
                    // if the final position is occupied by an opponent, the position is not occupiable
                    if ((tempXCoord == this.getFinalX()) && (this.p.getBoard().occupied(tempXCoord, this.getFinalY())) && (this.p.getColour() == p.getBoard().getPiece(tempXCoord, this.getFinalY()).getColour())) {
                        horizontalSearchTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }
                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied
                    else if (tempXCoord == this.getFinalX() && !this.p.getBoard().occupied(tempXCoord, this.getFinalY())) {
                        horizontalSearchTrue  = true ;
                        oppAtFinalCoords = false ;
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
    /**
     * @return boolean returns whether a move can be made in a diagonal direction or not
     *
     * */
    public boolean straightLineCheckDiagonal() {

        //boolean diagonalXTrue = false ;
        //boolean diagonalYTrue = false ;
        //int startX = 0;
        //int startY = 0;
        boolean oppAtFinalCoords;
        // if the final coordinates are out of range, or the return false
        if (this.p.getBoard().outOfRange(this.getFinalX(), this.getFinalY())|| (this.getInitialY()==this.getFinalY()) ||(this.getInitialX() == this.getFinalX())) {
            diagonal = false;
        }
        else {
            boolean diagonalTrue = false;
            // move towards top-right direction
            if ((this.getInitialX() < this.getFinalX()) && (this.getInitialY() < this.getFinalY())) {
                int startX = 0;
                int startY = 0;

                while (!diagonalTrue) {
                    startX += 1;
                    startY += 1;
                    int tempXCoord = startX + this.getInitialX() ;
                    int tempYCoord = this.getInitialY()+startY ;
                    //will turn false if the next diagonally top-right box is occupied

                    if(this.p.getBoard().occupied(tempXCoord, tempYCoord) && (tempXCoord!= this.getFinalX() || tempYCoord!=this.getFinalY()) && !this.p.getBoard().outOfRange(tempXCoord,tempYCoord)){
                        diagonalTrue = false ;
                        break ;
                    }
                    // if the path is clear till the final coordinates check whether the destination coordinates are occupied or not
                    //if occupied, check the color of the piece, if same color return false, if different color - true
                    if ((tempXCoord == this.getFinalX()) && (tempYCoord == this.getFinalY()) && (this.p.getBoard().occupied(tempXCoord, tempYCoord)) && (this.p.getColour()!= p.getBoard().getPiece(tempXCoord, tempYCoord).getColour()) ) {
                        diagonalTrue = true;
                        oppAtFinalCoords = true;
                    }
                    else if ((tempXCoord == this.getFinalX()) && (tempYCoord == this.getFinalY()) && (this.p.getBoard().occupied(tempXCoord,tempYCoord)) && (this.p.getColour() == p.getBoard().getPiece(tempXCoord,tempYCoord).getColour())) {
                            diagonalTrue = false;
                            oppAtFinalCoords = false;
                            break;
                    }
                    // break out of the loop if the path is clear (start = finalY only if the path is cleared) and the final coordinates are not occupied

                    else if (tempXCoord == this.getFinalX() && (tempYCoord==this.getFinalY()) && !(this.p.getBoard().occupied(tempXCoord, tempYCoord))) {
                        diagonalTrue = true ;
                        oppAtFinalCoords = false ;
                        break;
                    }
                }
                diagonal = diagonalTrue;
            }
            // a move to bottom left direction
            else if ((this.getInitialX() > this.getFinalX()) && (this.getInitialY() > this.getFinalY())) {
                int startX = 0;
                int startY = 0;
                while (!diagonalTrue) {

                    startX -= 1;
                    startY -= 1;
                    //will turn false if the next diagonally top-right box is occupied

                    int tempXCoord = (startX + this.getInitialX()) ;
                    int tempYCoord = (startY + this.getInitialY());

                    if(this.p.getBoard().occupied(tempXCoord, tempYCoord) && (tempXCoord!= this.getFinalX() || tempYCoord!=this.getFinalY()) && !this.p.getBoard().outOfRange(tempXCoord,tempYCoord)){
                        diagonalTrue = false ;
                        break ;
                    }

                    // if the path is clear till the final coordinates check whether the destination coordinates are occupied or not
                    //if occupied, check the color of the piece, if same color return false, if different color - true
                    if ((tempXCoord == this.getFinalX()) && (tempYCoord == this.getFinalY())  && (this.p.getBoard().occupied(tempXCoord,tempYCoord)) && (this.p.getColour() != p.getBoard().getPiece(tempXCoord, tempYCoord).getColour())) {
                        diagonalTrue = true;
                        oppAtFinalCoords = true;
                    }
                    else if ((tempXCoord == this.getFinalX()) && (tempYCoord == this.getFinalY()) && (this.p.getBoard().occupied(tempXCoord,tempYCoord)) && (this.p.getColour() == p.getBoard().getPiece(tempXCoord, tempYCoord).getColour()) ) {
                        diagonalTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }
                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied
                    else if (tempXCoord == this.getFinalX() && (tempYCoord == this.getFinalY()) && !(this.p.getBoard().occupied(tempXCoord, tempYCoord))) {
                        diagonalTrue = true  ;
                        oppAtFinalCoords = false ;
                        break;
                    }
                }
                diagonal = diagonalTrue;
            }
            // move towards bottom -right direction
            else if ((this.getInitialX() < this.getFinalX()) && (this.getInitialY() > this.getFinalY())) {
                int startX = 0;
                int startY = 0;

                while (!diagonalTrue) {
                    startX += 1;
                    startY -= 1;
                    int tempXCoord = (startX + this.getInitialX()) ;
                    int tempYCoord = (this.getInitialY()+startY) ;
                    //will turn false if the next diagonally top-right box is occupied
                    //diagonalTrue = (this.p.getBoard().occupied(startX, startY));
                    // if any position before the final one is occupied return false
                    if(this.getFinalY()!= tempYCoord && this.getFinalX()!= tempXCoord && this.getP().getBoard().occupied(tempXCoord,tempYCoord)  && !this.p.getBoard().outOfRange(tempXCoord,tempYCoord)){
                        diagonalTrue = false ;
                        break ;

                    }
                    // if the path is clear till the final coordinates check whether the destination coordinates are occupied or not
                    //if occupied, check the color of the piece, if same color return false, if different color - true
                    if ((tempXCoord == this.getFinalX()) && (tempYCoord == this.getFinalY()) && (this.p.getBoard().occupied(tempXCoord, tempYCoord)) && (this.p.getColour() != p.getBoard().getPiece(tempXCoord, tempYCoord).getColour()) ) {
                        diagonalTrue = true;
                        oppAtFinalCoords = true;

                    }
                    else if ((tempXCoord == this.getFinalX()) && (tempYCoord == this.getFinalY())&& (this.p.getBoard().occupied(tempXCoord, tempYCoord)) && (this.p.getColour() == p.getBoard().getPiece(tempXCoord, tempYCoord).getColour()) ) {
                        diagonalTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }
                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied
                    else if (tempXCoord == this.getFinalX() && (tempYCoord == this.getFinalY()) && !(this.p.getBoard().occupied(tempXCoord, tempYCoord))) {
                        diagonalTrue = true;
                        oppAtFinalCoords = false ;
                        break;
                    }
                }
                diagonal = diagonalTrue;
            }

            // move towards top -left direction
            else if((this.getInitialX() > this.getFinalX()) && (this.getInitialY() < this.getFinalY())) {
                int startX = 0;
                int startY = 0;

                while (!diagonalTrue) {
                    startX -= 1;
                    startY += 1;
                    int tempYCoord  = (this.getInitialY()+startY) ;
                    int tempXCoord  = (startX + this.getInitialX()) ;

                    //will turn false if the next diagonally top-right box is occupied
                    if(this.getFinalY()!= tempYCoord && this.getFinalX()!= tempXCoord && this.getP().getBoard().occupied(tempXCoord,tempYCoord)  && !this.p.getBoard().outOfRange(tempXCoord,tempYCoord) ){
                        diagonalTrue = false ;
                        break ;

                    }
                    // if the path is clear till the final coordinates check whether the destination coordinates are occupied or not
                    //if occupied, check the color of the piece, if same color return false, if different color - true
                    if ((tempXCoord == this.getFinalX()) && tempYCoord == this.getFinalY() && (this.p.getBoard().occupied(tempXCoord, tempYCoord)) && (this.p.getColour() != p.getBoard().getPiece(tempXCoord, tempYCoord).getColour())) {
                        diagonalTrue = true;
                        oppAtFinalCoords = true;
                        }
                    else if ((tempXCoord == this.getFinalX()) && (tempYCoord == this.getFinalY()) && (this.p.getBoard().occupied(tempXCoord, tempYCoord)) && (this.p.getColour() == p.getBoard().getPiece(tempXCoord, tempYCoord).getColour())) {
                        diagonalTrue = false;
                        oppAtFinalCoords = false;
                        break;
                    }

                    // break out of the loop if the path is clear (start = finalY only if the path is cleared)
                    // and the final coordinates are not occupied
                    else if (tempXCoord== this.getFinalX() && (tempYCoord == this.getFinalY()) && !(this.p.getBoard().occupied(tempXCoord, tempYCoord))) {
                        diagonalTrue = true ;
                        oppAtFinalCoords = false ;
                        break;
                    }
                }
                diagonal = diagonalTrue;
            }
        }
        return diagonal ;
    }

}
