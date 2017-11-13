/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Piece;

import Piece.Piece;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nthan
 */
public class EmptyCell extends Piece{

    public EmptyCell(int _color, Coordinate _coordinate) {
        super(_color, _coordinate);
        this.name = "";
        this.Point = Piece.EMPTY_POINT;
    }
    @Override
    public List<Coordinate> getPossibleMove(Piece[][] board) {
         List<Coordinate> list = new ArrayList<Coordinate>() ;
         return list;
    }
    
}
