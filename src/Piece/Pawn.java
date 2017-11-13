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
public class Pawn extends Piece{

    public Pawn(int _color, Coordinate _coordinate) {
        super(_color, _coordinate);
        if(color == Piece.Black){
            this.name = "Black_Pawn";
        }
        else{
            this.name = "White_Pawn";
        }
        this.Point = Piece.PAWN_POINT;
    }



    @Override
    public List<Coordinate> getPossibleMove(Piece[][] board) {
       List<Coordinate> listCoordinate = new ArrayList<Coordinate>();
       
       // Process Possible Move
       // Chess Black
       //----------------------------------------------------------------------------------------
       if(this.color == Piece.Black){
           // Often walking By Pawn
           if(this.coordinate.getRow() < 7 ){
            if(super.checkEmptyCell(board, this.coordinate.getRow() + 1, this.coordinate.getCol())){
                 Coordinate may = new Coordinate(this.coordinate.getRow() + 1,
                         this.coordinate.getCol());
                 listCoordinate.add(may);
                }
           }
           
           // Pawm not Yet moved
           if(this.coordinate.getRow() == 1){
               if(super.checkEmptyCell(board, this.coordinate.getRow() + 2, this.coordinate.getCol())
                       && board[this.coordinate.getRow() + 1][this.coordinate.getCol()].color == -1){
                    Coordinate may = new Coordinate(this.coordinate.getRow() + 2,
                           this.coordinate.getCol());     
                    listCoordinate.add(may);
               }
           }
           // When eating another
              // find coordinate Troops are allowed to eat
                  // Pawn is Col = 0
           if(this.coordinate.getCol() == 0){
               if(this.coordinate.getRow() < 7){
                if(super.checkEmptyCell(board, this.coordinate.getRow() +1,
                        this.coordinate.getCol() + 1) == false &&
                        board[this.coordinate.getRow() +1][this.coordinate.getCol()+1].color != Piece.Black){

                    Coordinate may = new Coordinate(this.coordinate.getRow() + 1,
                            this.coordinate.getCol() + 1);     
                     listCoordinate.add(may);
                }
           }
           }
                   // Pawn is Col = 7             
           if(this.coordinate.getCol() == 7){
               if(this.coordinate.getRow() < 7){
                if(super.checkEmptyCell(board, this.coordinate.getRow() +1,
                        this.coordinate.getCol() - 1) == false &&
                        board[this.coordinate.getRow() +1][this.coordinate.getCol()-1].color != Piece.Black){

                    Coordinate may = new Coordinate(this.coordinate.getRow() + 1,
                            this.coordinate.getCol() - 1);     
                     listCoordinate.add(may);
                }
               }
           }
           
                // Pawn is Col [1,6]
          if(this.coordinate.getCol() >= 1 && this.coordinate.getCol() <= 6){
              if(this.coordinate.getRow() < 7){ 
                if(super.checkEmptyCell(board, this.coordinate.getRow() + 1,
                        this.coordinate.getCol() + 1) == false &&
                        board[this.coordinate.getRow() +1][this.coordinate.getCol()+1].color != Piece.Black){
                    Coordinate may = new Coordinate(this.coordinate.getRow() + 1,
                             this.coordinate.getCol() + 1);     
                      listCoordinate.add(may);
                }

                if(super.checkEmptyCell(board, this.coordinate.getRow() +1,
                         this.coordinate.getCol() - 1) == false &&
                        board[this.coordinate.getRow() +1][this.coordinate.getCol()-1].color != Piece.Black){        
                     Coordinate may = new Coordinate(this.coordinate.getRow() + 1,
                             this.coordinate.getCol() - 1);     
                      listCoordinate.add(may);
                 }  
              }
          }      
       }
       
       //------------------------------------------------------------------------
       // Chess White
       if(this.color == Piece.White){
           
           // Often walking By Pawn
          
           if(this.coordinate.getRow() > 0){
            if(super.checkEmptyCell(board, this.coordinate.getRow() - 1, this.coordinate.getCol())){
                 Coordinate may = new Coordinate(this.coordinate.getRow() -1,
                         this.coordinate.getCol());
                 listCoordinate.add(may);
                }
           }
           
           // Pawm not Yet moved
           if(this.coordinate.getRow() == 6){
               if(super.checkEmptyCell(board, this.coordinate.getRow() - 2, this.coordinate.getCol()) &&
                       board[this.coordinate.getRow() - 1][this.coordinate.getCol()].color == -1){
                    Coordinate may = new Coordinate(this.coordinate.getRow() - 2,
                           this.coordinate.getCol());     
                    listCoordinate.add(may);
               }
           }
           
           // When eating another
              // find coordinate Troops are allowed to eat
                  // Pawn is Col = 0
           if(this.coordinate.getCol() == 0){
               if(this.coordinate.getRow() - 1 >  0){
                if(super.checkEmptyCell(board, this.coordinate.getRow() - 1,
                        this.coordinate.getCol() + 1) == false &&
                        board[this.coordinate.getRow()-1][this.coordinate.getCol()+1].color != Piece.White){

                    Coordinate may = new Coordinate(this.coordinate.getRow() - 1,
                            this.coordinate.getCol() + 1);     
                     listCoordinate.add(may);
                }
               }
           }
           
                   // Pawn is Col = 7 
           if(this.coordinate.getCol() == 7){
               if(this.coordinate.getRow() > 0){
                if(super.checkEmptyCell(board, this.coordinate.getRow() - 1,
                        this.coordinate.getCol() - 1) == false &&
                         board[this.coordinate.getRow()-1][this.coordinate.getCol()-1].color != Piece.White){

                    Coordinate may = new Coordinate(this.coordinate.getRow() - 1,
                            this.coordinate.getCol() - 1);     
                     listCoordinate.add(may);
                }
               }
           }
                
                 // Pawn is Col [1,6]
          if(this.coordinate.getCol() >= 1 && this.coordinate.getCol() <= 6){
              if(this.coordinate.getRow() > 0){
                if(super.checkEmptyCell(board, this.coordinate.getRow() - 1,
                        this.coordinate.getCol() + 1) == false &&
                         board[this.coordinate.getRow()-1][this.coordinate.getCol()+1].color != Piece.White){
                    Coordinate may = new Coordinate(this.coordinate.getRow() - 1,
                             this.coordinate.getCol() + 1);     
                      listCoordinate.add(may);
                }

                if(super.checkEmptyCell(board, this.coordinate.getRow() - 1,
                         this.coordinate.getCol() - 1) == false &&
                         board[this.coordinate.getRow()-1][this.coordinate.getCol()-1].color != Piece.White){

                     Coordinate may = new Coordinate(this.coordinate.getRow() - 1,
                             this.coordinate.getCol() - 1);     
                      listCoordinate.add(may);
                 }      
              }
          }   
       }
       //
       return listCoordinate;
    }
    
    

 
}
