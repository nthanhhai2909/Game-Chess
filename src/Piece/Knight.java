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
public class Knight extends Piece{

    public Knight(int _color, Coordinate _coordinate) {
        super(_color, _coordinate);
        if(color == Piece.Black){
            this.name = "Black_Knight";
        }
        else{
            this.name = "White_Knight";
        }
        this.Point = Piece.KNIGHT_POINT;
    }



    @Override
    public List<Coordinate> getPossibleMove(Piece[][] board) {
      List<Coordinate> listCoordinate = new ArrayList<Coordinate>();
      // -2 -1
      if(this.coordinate.getRow() - 2 >= 0 && this.coordinate.getCol() - 1 >= 0){
          if(checkEmptyCell(board, this.coordinate.getRow() - 2, 
                  this.coordinate.getCol() - 1)){
              Coordinate may = new Coordinate(this.coordinate.getRow() - 2,
                              this.coordinate.getCol() - 1);     
                       listCoordinate.add(may);
          }
          else{
              if(this.color != board[this.coordinate.getRow() -2][this.coordinate.getCol()-1].color){
                  Coordinate may = new Coordinate(this.coordinate.getRow() - 2,
                              this.coordinate.getCol() - 1);     
                       listCoordinate.add(may);
              }
          }
      }
      // -1 -2
      if(this.coordinate.getRow() - 1 >= 0 && this.coordinate.getCol() - 2 >= 0){
          if(checkEmptyCell(board, this.coordinate.getRow() - 1, 
                  this.coordinate.getCol() - 2)){
              Coordinate may = new Coordinate(this.coordinate.getRow() - 1,
                              this.coordinate.getCol() - 2);     
                       listCoordinate.add(may);
          }
          else{
              if(this.color != board[this.coordinate.getRow() -1][this.coordinate.getCol()-2].color){
                  Coordinate may = new Coordinate(this.coordinate.getRow() - 1,
                              this.coordinate.getCol() - 2);     
                       listCoordinate.add(may);
              }
          }
      }
      // + 1 -2
      if(this.coordinate.getRow() + 1  <= 7 && this.coordinate.getCol() - 2 >= 0){
          if(checkEmptyCell(board, this.coordinate.getRow() + 1, 
                  this.coordinate.getCol() - 2)){
              Coordinate may = new Coordinate(this.coordinate.getRow() + 1,
                              this.coordinate.getCol() - 2);     
                       listCoordinate.add(may);
          }
          else{
              if(this.color != board[this.coordinate.getRow() + 1][this.coordinate.getCol()-2].color){
                  Coordinate may = new Coordinate(this.coordinate.getRow() + 1,
                              this.coordinate.getCol() - 2);     
                       listCoordinate.add(may);
              }
          }
      }
      
      // +2 -1
      if(this.coordinate.getRow() + 2 <= 7  && this.coordinate.getCol() - 1 >= 0){
          if(checkEmptyCell(board, this.coordinate.getRow() + 2, 
                  this.coordinate.getCol() - 1)){
              Coordinate may = new Coordinate(this.coordinate.getRow() + 2,
                              this.coordinate.getCol() - 1);     
                       listCoordinate.add(may);
          }
          else{
              if(this.color != board[this.coordinate.getRow() +2][this.coordinate.getCol()-1].color){
                  Coordinate may = new Coordinate(this.coordinate.getRow() + 2,
                              this.coordinate.getCol() - 1);     
                       listCoordinate.add(may);
              }
          }
      }
      // + 2 + 1 
      if(this.coordinate.getRow() + 2 <= 7  && this.coordinate.getCol() + 1 <= 7){
          if(checkEmptyCell(board, this.coordinate.getRow() + 2, 
                  this.coordinate.getCol() + 1)){
              Coordinate may = new Coordinate(this.coordinate.getRow() + 2,
                              this.coordinate.getCol() + 1);     
                       listCoordinate.add(may);
          }
          else{
              if(this.color != board[this.coordinate.getRow() +2][this.coordinate.getCol()+1].color){
                  Coordinate may = new Coordinate(this.coordinate.getRow() + 2,
                              this.coordinate.getCol() + 1);     
                       listCoordinate.add(may);
              }
          }
      }
      
      // + 1 + 2
      if(this.coordinate.getRow() + 1 <= 7  && this.coordinate.getCol() + 2 <= 7){
          if(checkEmptyCell(board, this.coordinate.getRow() + 1, 
                  this.coordinate.getCol() + 2)){
              Coordinate may = new Coordinate(this.coordinate.getRow() + 1,
                              this.coordinate.getCol() + 2);     
                       listCoordinate.add(may);
          }
          else{
              if(this.color != board[this.coordinate.getRow() + 1][this.coordinate.getCol() + 2].color){
                  Coordinate may = new Coordinate(this.coordinate.getRow() + 1,
                              this.coordinate.getCol() + 2);     
                       listCoordinate.add(may);
              }
          }
      }
      
      // -1 + 2
      if(this.coordinate.getRow() - 1 >= 0 && this.coordinate.getCol() + 2 <= 7){
          if(checkEmptyCell(board, this.coordinate.getRow() - 1, 
                  this.coordinate.getCol() + 2)){
              Coordinate may = new Coordinate(this.coordinate.getRow() - 1,
                              this.coordinate.getCol() + 2);     
                       listCoordinate.add(may);
          }
          else{
              if(this.color != board[this.coordinate.getRow() -1][this.coordinate.getCol()+2].color){
                  Coordinate may = new Coordinate(this.coordinate.getRow() - 1,
                              this.coordinate.getCol() + 2);     
                       listCoordinate.add(may);
              }
          }
      }
      
      // -2 + 1
      
      if(this.coordinate.getRow() - 2 >= 0 && this.coordinate.getCol() + 1 <= 7){
          if(checkEmptyCell(board, this.coordinate.getRow() - 2, 
                  this.coordinate.getCol() + 1)){
              Coordinate may = new Coordinate(this.coordinate.getRow() - 2,
                              this.coordinate.getCol() + 1);     
                       listCoordinate.add(may);
          }
          else{
              if(this.color != board[this.coordinate.getRow() -2][this.coordinate.getCol()+1].color){
                  Coordinate may = new Coordinate(this.coordinate.getRow() - 2,
                              this.coordinate.getCol() + 1);     
                       listCoordinate.add(may);
              }
          }
      }
      
      return listCoordinate;
    }
    
}
