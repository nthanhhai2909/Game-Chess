/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;
import Piece.Coordinate;
import Piece.EmptyCell;
import Piece.Piece;
import gamechess.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author nthan
 */
public class AI { 
    public PieceGo findPieceGo(Piece[][] board, int turn){
        PieceGo piecego = null;
        List<ChessAndCoordinare> listchessAndCoordinare = new ArrayList<ChessAndCoordinare>();
        // get PossibleMove of turn
        for(int iRow = 0; iRow < 8; iRow++){
            for(int iCol = 0; iCol < 8; iCol++){
                if(board[iRow][iCol].getColor() == turn){
                    listchessAndCoordinare.add(new ChessAndCoordinare(
                            board[iRow][iCol].getPossibleMove(board),
                            new Coordinate(iRow, iCol)));       
                }
            }
        }
        
        // Find the invalid
        if(listchessAndCoordinare.size() != 0){
            for(int i = 0; i < listchessAndCoordinare.size(); i++){
                int iRowStart = listchessAndCoordinare.get(i).getMyChess().getRow();
                int iColStart = listchessAndCoordinare.get(i).getMyChess().getCol();
                for(int j = 0; j < listchessAndCoordinare.get(i).getListCoordinate()
                        .size(); j++){
                    
                    int iRowTarget = listchessAndCoordinare.get(i).getListCoordinate().
                            get(j).getRow();
                    int iColTarget = listchessAndCoordinare.get(i).getListCoordinate().
                            get(j).getCol();

                    //--------------------------------------------------------------
                    /*
                        CHECK MATE
                        + Try to move the flag
                        + CHECK MATE RETURN TRUE OR FALSE
                            -> TRUE: REMOVE LISTCOORDINATE
                            -> FALSE: INVALID
                    */ 
                    Piece temp;
                    temp = board[iRowTarget][iColTarget];
                    
                    board[iRowTarget][iColTarget] = board[iRowStart][iColStart];
                    board[iRowTarget][iColTarget].setCoordinate(new 
                         Coordinate(iRowTarget, iColTarget));
                    board[iRowStart][iColStart] = new EmptyCell(-1,
                            new Coordinate(iRowStart, iColStart));

                    if(BoardChess.checkMate()){
                        listchessAndCoordinare.get(i).getListCoordinate().remove(j);
                        j--;
                    }

                    // RETURN BOARD OLD
                    board[iRowStart][iColStart] = board[iRowTarget][iColTarget];
                    board[iRowStart][iColStart].setCoordinate(new 
                         Coordinate(iRowStart, iColStart));
                    board[iRowTarget][iColTarget] = temp;
                    board[iRowTarget][iColTarget].setCoordinate(new
                         Coordinate(iRowTarget, iColTarget));


                    //-------------------------------------------------------------- 
                }
            }
        }
        int point = -1;
        // Mode piece go
        if(listchessAndCoordinare.size() > 0){
            for(int i = 0; i < listchessAndCoordinare.size(); i++){
                for(int j = 0; j < listchessAndCoordinare.get(i).
                        getListCoordinate().size(); j++){
                    
                    int iRow = listchessAndCoordinare.get(i).getListCoordinate()
                            .get(j).getRow();
                    int iCol = listchessAndCoordinare.get(i).getListCoordinate()
                            .get(j).getCol();
                    
                    //ALGORITHM
                    if(board[iRow][iCol].getPoint() > point){
                        point = board[iRow][iCol].getPoint();
                        piecego = new PieceGo(listchessAndCoordinare.get(i)
                                .getMyChess(), new Coordinate(iRow, iCol));
                    }                 
                }
            }
        }
        return piecego; 
    }   
}


