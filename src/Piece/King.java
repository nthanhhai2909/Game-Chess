/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Piece;
import gamechess.BoardChess;
import gamechess.Main;
import Piece.Piece;
import gamechess.BoardChess;
import gamechess.Main;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nthan
 */
public class King extends Piece{

    public King(int _color, Coordinate _coordinate) {
        super(_color, _coordinate);
        if(color == Piece.Black){
            this.name = "Black_King";
        }
        else{
            this.name = "White_King";
        }
        this.Point = Piece.KING_POINT;
    }



    @Override
    public List<Coordinate> getPossibleMove(Piece[][] board) {
        
        
        List<Coordinate> listCoordinate = new ArrayList<Coordinate>(getCoordinateEmptyOrContainEnemy(board,this));
        
        ArrayList<Coordinate> list = new ArrayList<Coordinate>(checkKingKill(board));
        // Process King die 
        for(int i = 0; i < listCoordinate.size(); i++){
            for(int j = 0; j < list.size(); j++){
                
                if(listCoordinate.get(i).getRow() == list.get(j).getRow() &&
                        listCoordinate.get(i).getCol() == list.get(j).getCol()){  
                     listCoordinate.remove(i);
                     i--;
                     break;
                }
            }
        }
        
        ArrayList<Coordinate> listPawn = new ArrayList<Coordinate>(checkKingDieByPawn(board));
        // Process King die 
        for(int i = 0; i < listCoordinate.size(); i++){
            for(int j = 0; j < listPawn.size(); j++){
                if(listCoordinate.get(i).getRow() == listPawn.get(j).getRow() &&
                        listCoordinate.get(i).getCol() == listPawn.get(j).getCol()){
                    listCoordinate.remove(i);
                    i--;
                    break;
                }
            }
        }   
        
        ArrayList<Coordinate> listKing = new ArrayList<Coordinate>(checkKingKillByKing(board));
        // Process King die 
        for(int i = 0; i < listCoordinate.size(); i++){
            for(int j = 0; j < listKing.size(); j++){
                if(listCoordinate.get(i).getRow() == listKing.get(j).getRow() &&
                        listCoordinate.get(i).getCol() == listKing.get(j).getCol()){
                    listCoordinate.remove(i);
                    i--;
                    break;
                }
            }
        } 
        
        for(int i = 0; i < listCoordinate.size(); i++){
            if(checkBoxCatch(this.coordinate, listCoordinate.get(i), board)){
                listCoordinate.remove(i);
            }
        }

        
        
        // ENTER INTO
            
        List<Coordinate> listAll = new ArrayList<Coordinate>();
        if(this.color == Piece.Black){
            if(!Main.checkKingBlackMove && !Main.checkRookLeftBlackMove &&
                    BoardChess.chessBoard[0][1].color == -1 &&
                    BoardChess.chessBoard[0][2].color == -1 &&
                    BoardChess.chessBoard[0][3].color == -1 ){
                
                 for(int iRow = 0; iRow < 8; iRow++){
                    for(int iCol = 0; iCol < 8; iCol++){
                    if(checkBoxCatch(this.coordinate, new Coordinate(0,2), board)){
                        listCoordinate.add(new Coordinate(0, 2));
                    }
                }
            }
            
            if(!Main.checkKingBlackMove && !Main.checkRookRightBlackMove &&
                    BoardChess.chessBoard[0][5].color == -1 &&
                    BoardChess.chessBoard[0][6].color == -1 ){
                    if(checkBoxCatch(this.coordinate, new Coordinate(0, 6), board)){
                        listCoordinate.add(new Coordinate(0, 6));
                    }
             }     
        }
        }
        
        
        if(this.color == Piece.White){
            if(!Main.checkKingWhiteMove && !Main.checkRookLeftWhiteMove &&
                    BoardChess.chessBoard[7][1].color == -1 &&
                    BoardChess.chessBoard[7][2].color == -1 &&
                    BoardChess.chessBoard[7][3].color == -1 ){
                
                    if(checkBoxCatch(this.coordinate, new Coordinate(7,2), board)){
                        listCoordinate.add(new Coordinate(7, 2));
                    }
            }
            
            if(!Main.checkKingWhiteMove && !Main.checkRookRightWhiteMove &&
                    BoardChess.chessBoard[7][5].color == -1 &&
                    BoardChess.chessBoard[7][6].color == -1 ){
                        if(checkBoxCatch(this.coordinate, new Coordinate(7, 6), board)){
                            listCoordinate.add(new Coordinate(7, 6));
                        }
                 }     
        }
        
        return listCoordinate;   
}
        
    
    
    public boolean checkBoxCatch(Coordinate first, Coordinate last, Piece[][]board){   
       
        int color = BoardChess.chessBoard[last.getRow()][last.getCol()].getColor();
        BoardChess.chessBoard[last.getRow()][last.getCol()].
                setColor(BoardChess.chessBoard[first.getRow()][first.getCol()].color);
        
        for(int iRow = 0; iRow < 8; iRow++){
            for(int iCol = 0; iCol < 8; iCol++){
                if(BoardChess.chessBoard[iRow][iCol].color !=
                        BoardChess.chessBoard[first.getRow()][first.getCol()].color &&
                        !BoardChess.chessBoard[iRow][iCol].name.equals("White_King")&&
                        !BoardChess.chessBoard[iRow][iCol].name.equals("Black_King")){
                    List<Coordinate> list = new
                        ArrayList<Coordinate>(BoardChess.chessBoard[iRow][iCol]
                                .getPossibleMove(board));
                    for(int i = 0; i <list.size(); i++){
                        if(list.get(i).getRow() == last.getRow() &&
                                list.get(i).getCol() == last.getCol()){
                            BoardChess.chessBoard[last.getRow()][last.getCol()].setColor(color);
                            return true;
                        }
                    }
                }    
            }
        }
        
        List<Coordinate> list = new ArrayList<Coordinate>(checkKingKillByKing(board));
        for(int i = 0; i <list.size(); i++){
            if(list.get(i).getRow() == last.getRow() &&
                    list.get(i).getCol() == last.getCol()){
                BoardChess.chessBoard[last.getRow()][last.getCol()].setColor(color);
                return true;
            }
       }
       BoardChess.chessBoard[last.getRow()][last.getCol()].setColor(color);
       return false;
    }
    
    public List<Coordinate> checkKingKill(Piece[][] board){
        
          List<Coordinate> listCoordinate = new ArrayList<Coordinate>();  
        //  Find the moves of enemy troops
        try{
        for(int iRow = 0; iRow < 8; iRow++){
            for(int iCol = 0; iCol < 8; iCol++){                               
                if(board[iRow][iCol].color  != this.color && board[iRow][iCol].color != -1 && 
                        board[iRow][iCol].getName().equals("Black_Pawn") == false &&
                         board[iRow][iCol].getName().equals("White_Pawn") == false &&
                        board[iRow][iCol].getName().equals("White_King") == false &&
                        board[iRow][iCol].getName().equals("Black_King") == false) {
                    ArrayList<Coordinate> list = new ArrayList<Coordinate>(board[iRow][iCol].getPossibleMove(board));
                    listCoordinate.addAll(list);
                  }
            }
        }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }     
        return listCoordinate;
    }
    
    public  List<Coordinate>checkKingDieByPawn(Piece[][] board){
        List<Coordinate> listCoordinate = new ArrayList<Coordinate>();
         try{
            for(int iRow = 0; iRow < 8; iRow++){
                for(int iCol = 0; iCol < 8; iCol++){    
                    if(this.color == Piece.Black){
                        if(board[iRow][iCol].getName()== "White_Pawn"){
                            listCoordinate.add(new Coordinate(iRow -1 , iCol -1));
                            listCoordinate.add(new Coordinate(iRow -1 , iCol +1));
                         }
                    }
                    if(this.color == Piece.White){        
                        if(board[iRow][iCol].getName()== "Black_Pawn"){ 
                            listCoordinate.add(new Coordinate(iRow +1 , iCol -1));
                            listCoordinate.add(new Coordinate(iRow +1 , iCol +1));
                        }     
                    }
                }
            }
         }
         catch(Exception ex){
             System.out.println(ex.getMessage());
         }
     
        return listCoordinate;
    }
    
    
    public List<Coordinate> checkKingKillByKing(Piece[][] board){
         List<Coordinate> listCoordinate = null;
        try{
            for(int iRow = 0; iRow < 8; iRow++){
                for(int iCol = 0; iCol < 8; iCol++){
                    if(this.color == Piece.Black){
                        if(board[iRow][iCol].name.equals("White_King")){
                            listCoordinate = new
                                    ArrayList<Coordinate>(getCoordinateEmptyOrContainEnemy(board,
                                            (King)board[iRow][iCol]));
                        }
                    }
                    if(this.color == Piece.White){
                        if(board[iRow][iCol].name.equals("Black_King")){
                            listCoordinate = new
                                    ArrayList<Coordinate>(getCoordinateEmptyOrContainEnemy(board,
                                            (King)board[iRow][iCol]));
                        }
                    }
                    
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return listCoordinate;
    }
    
    public List<Coordinate> getCoordinateEmptyOrContainEnemy(Piece[][] board, King king){
        List<Coordinate> listCoordinate = new ArrayList<Coordinate>();
        // browse bot
        if(king.coordinate.getRow() > 0){
            if(super.checkEmptyCell(board, king.coordinate.getRow() - 1, 
                    king.coordinate.getCol())){
                Coordinate may = new Coordinate(king.coordinate.getRow() -1,
                king.coordinate.getCol());
                listCoordinate.add(may);
            }
            else{
                if(board[king.coordinate.getRow()-1][king.coordinate.getCol()].color != king.color){
                    Coordinate may = new Coordinate(king.coordinate.getRow() -1,
                     king.coordinate.getCol());
                     listCoordinate.add(may);
                }                  
            }
        }
        // browse left
        if(king.coordinate.getCol() > 0){
            if(king.checkEmptyCell(board, king.coordinate.getRow(), 
                    king.coordinate.getCol() -1)){
                Coordinate may = new Coordinate(king.coordinate.getRow(),
                        king.coordinate.getCol() -1);
                listCoordinate.add(may);
            }
            else{
                if(board[king.coordinate.getRow()][king.coordinate.getCol() -1].color != king.color){
                    Coordinate may = new Coordinate(king.coordinate.getRow(),
                        king.coordinate.getCol() -1);
                    listCoordinate.add(may);
                }
            }
            
        }
        // browse right
        if(king.coordinate.getCol() < 7){
            if(super.checkEmptyCell(board, king.coordinate.getRow(), 
                    king.coordinate.getCol() + 1)){
                Coordinate may = new Coordinate(king.coordinate.getRow(), 
                king.coordinate.getCol() + 1);
                listCoordinate.add(may);
            }
            else{
                if(board[king.coordinate.getRow()][king.coordinate.getCol() + 1].color != king.color){
                    Coordinate may = new Coordinate(king.coordinate.getRow(), 
                    king.coordinate.getCol() + 1);
                    listCoordinate.add(may);
                }
            }
        }
        // browse top
        if(king.coordinate.getRow() < 7){
            if(super.checkEmptyCell(board, king.coordinate.getRow() + 1,
                    king.coordinate.getCol())){
                Coordinate may = new Coordinate(king.coordinate.getRow() + 1,
                king.coordinate.getCol());
                listCoordinate.add(may);
            }
            else{
                if(board[king.coordinate.getRow() + 1][king.coordinate.getCol()].color != king.color){
                    Coordinate may = new Coordinate(king.coordinate.getRow() + 1,
                king.coordinate.getCol());
                listCoordinate.add(may);
                }
            }
        }
        
        if(king.coordinate.getRow() > 0 && king.coordinate.getCol() > 0){
            if(super.checkEmptyCell(board, king.coordinate.getRow() -1,
                    king.coordinate.getCol() -1)){
                Coordinate may = new Coordinate(king.coordinate.getRow() - 1,
                king.coordinate.getCol() -1);
                listCoordinate.add(may);
            }
            else{
                if(board[king.coordinate.getRow() - 1][king.coordinate.getCol() -1].color != king.color){
                    Coordinate may = new Coordinate(king.coordinate.getRow() - 1,
                king.coordinate.getCol() -1);
                listCoordinate.add(may);
                }
            }
        }
        
        if(king.coordinate.getRow() < 7 && king.coordinate.getCol() > 0){
            if(super.checkEmptyCell(board, king.coordinate.getRow() + 1,
                    king.coordinate.getCol() -1)){
                Coordinate may = new Coordinate(king.coordinate.getRow() + 1,
                king.coordinate.getCol() -1);
                listCoordinate.add(may);
            }
            else{
                if(board[king.coordinate.getRow() + 1][king.coordinate.getCol() -1].color != king.color){
                    Coordinate may = new Coordinate(king.coordinate.getRow() + 1,
                king.coordinate.getCol() -1);
                listCoordinate.add(may);
                }
            }
        }
        
        if(king.coordinate.getRow() < 7 && king.coordinate.getCol() < 7){
            if(super.checkEmptyCell(board, king.coordinate.getRow() + 1,
                    king.coordinate.getCol() + 1)){
                Coordinate may = new Coordinate(king.coordinate.getRow() + 1,
                king.coordinate.getCol() + 1);
                listCoordinate.add(may);
            }
            else{
                if(board[king.coordinate.getRow() + 1][king.coordinate.getCol() +1].color != king.color){
                    Coordinate may = new Coordinate(king.coordinate.getRow() + 1,
                king.coordinate.getCol() + 1);
                listCoordinate.add(may);
                }
            }
        }
        
        if(king.coordinate.getRow() > 0 && king.coordinate.getCol() < 7){
            if(super.checkEmptyCell(board, king.coordinate.getRow() - 1,
                    king.coordinate.getCol() + 1)){
                Coordinate may = new Coordinate(king.coordinate.getRow() - 1,
                king.coordinate.getCol() + 1);
                listCoordinate.add(may);
            }
            else{
                if(board[king.coordinate.getRow() - 1][king.coordinate.getCol() + 1].color != king.color){
                    Coordinate may = new Coordinate(king.coordinate.getRow() - 1,
                king.coordinate.getCol() +1);
                listCoordinate.add(may);
                }
            }
        } 
        return listCoordinate;
    }
}

