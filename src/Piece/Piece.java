/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Piece;

import java.util.List;

/**
 *
 * @author nthan
 */
public abstract class Piece {

    public static final int QUEEN_POINT = 10;
    public static final int ROOK_POINT = 5;
    public static final int BISHOP_POINT = 3;
    public static final int KNIGHT_POINT = 3;
    public static final int PAWN_POINT = 1;
    public static final int EMPTY_POINT = 0;
    public static final int KING_POINT = 1000;
 
    
    public final static int White = 1006;
    public final static int Black = 1007;
    protected int color;
    protected int Point;

    public int getPoint() {
        return Point;
    }

    public void setPoint(int Point) {
        this.Point = Point;
    }
    protected Coordinate coordinate;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
    protected String name;

    public void setColor(int color) {
        this.color = color;
    }

    
    
    // Constructor
    public Piece(int _color, Coordinate _coordinate){
        this.color = _color;
        this.coordinate = _coordinate; 
        this.name = "";
    }
    
    public int getColor(){
        return this.color;
    }
    
    public String getName(){
        return this.name;
    }
    
    public boolean checkEmptyCell(Piece[][]board, int iRow, int iCol){
        if(iRow < 0 || iRow > 7 || iCol < 0 || iCol > 7)
            return false;
        if(board[iRow][iCol].color == -1)
            return true;
        return false;
    }
    
    public abstract List<Coordinate> getPossibleMove(Piece[][] board);
    
    
    
    
}
