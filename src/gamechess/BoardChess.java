/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamechess;

import Piece.Bishop;
import Piece.Coordinate;
import Piece.EmptyCell;
import Piece.Rook;
import Piece.King;
import Piece.Knight;
import Piece.Queen;
import Piece.Pawn;
import Piece.Piece;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author nthan
 */
public class BoardChess implements  Serializable{

    public static Piece[][] chessBoard = new Piece[8][8];
    public static Piece[][] chessBoardReverse = new Piece[8][8];
    public final static int WIDEBOARD = 600;
    public final static int HEIGHTBOARD = 600;
    public final static int WIDEBOX = 600;
    public final static int HEIGTHBOX = 600;
    public static boolean checkInit = false;
    public static int turn = Piece.White;
    
    public static void initBoard(Piece [][] board){        
        // chess Black
        board[0][0] = new Rook(Piece.Black, new Coordinate(0, 0));
        board[0][7] = new Rook(Piece.Black, new Coordinate(0, 7));
        board[0][1] = new Knight(Piece.Black, new Coordinate(0, 1));
        board[0][6] = new Knight(Piece.Black, new Coordinate(0, 6));
        board[0][2] = new Bishop(Piece.Black, new Coordinate(0, 2));
        board[0][5] = new Bishop(Piece.Black, new Coordinate(0, 5));
        board[0][3] = new Queen(Piece.Black, new Coordinate(0, 3));
        board[0][4] = new King(Piece.Black, new Coordinate(0, 4));
        for(int i=0; i<8; i++){
            board[1][i] = new Pawn(Piece.Black, new Coordinate(1, i));
        }
        
        // chess White
        board[7][0] = new Rook(Piece.White, new Coordinate(7, 0));
        board[7][7] = new Rook(Piece.White, new Coordinate(7, 7));
        board[7][1] = new Knight(Piece.White, new Coordinate(7, 1));
        board[7][6] = new Knight(Piece.White, new Coordinate(7, 6));
        board[7][2] = new Bishop(Piece.White, new Coordinate(7, 2));
        board[7][5] = new Bishop(Piece.White, new Coordinate(7, 5));
        board[7][3] = new Queen(Piece.White, new Coordinate(7, 3));
        board[7][4] = new King(Piece.White, new Coordinate(7, 4));
        for(int i=0; i<8; i++){
            board[6][i] = new Pawn(Piece.White, new Coordinate(6, i));
        }
        
        
        // Cell empty
        for(int iRow = 2; iRow <= 5; iRow ++){
            for(int iCol = 0; iCol <= 7; iCol++){
                board[iRow][iCol] = new EmptyCell(-1, new Coordinate(iRow,iCol));
            }
        }  
        
    }
    
    
    public static void ReverseChessBoard(Piece [][] boardNormal, Piece [][] boardReverse){
        for(int iRow = 0; iRow < 8; iRow++){
            for(int iCol = 0; iCol < 8; iCol++){
                boardReverse[iRow][iCol] = boardNormal[7 - iRow][7-iCol];

            }
        }
    }
    
    public static void copyBoard(Piece [][] boardByCopy, Piece [][] boardCopy)
    {
        for(int iRow = 0; iRow < 8; iRow++){
            for(int iCol = 0; iCol < 8; iCol++){
                boardCopy[iRow][iCol] = boardByCopy[iRow][iCol];
                boardCopy[iRow][iCol].getCoordinate().setRow(iRow);
                boardCopy[iRow][iCol].getCoordinate().setCol(iCol);
            }
        }
    }
    
    // BoardChess chessbox
    public static JPanel paintChessBox(Piece pieceSelected,
            ArrayList<Coordinate> listPieceGo){
        if(checkInit == false){
            initBoard(chessBoard);
            BoardChess.ReverseChessBoard(chessBoard, chessBoardReverse);
        }
        
        if(BoardChess.turn == Piece.Black){
             Main.Mymain.lb_turn.setText("Turn of Black");
        }
         if(BoardChess.turn == Piece.White){
             Main.Mymain.lb_turn.setText("Turn of White");
         }
         
         
         
        checkInit = true;
        JPanel chessBoard = new JPanel();
        chessBoard.setLayout(new GridLayout(8,8));
        chessBoard.setPreferredSize(new Dimension(WIDEBOARD,HEIGHTBOARD));
        
        
        for(int iRow = 1;iRow <= 8; iRow++){
            for(int iCol = 1; iCol <= 8; iCol++){
                // create chessbox
                JPanel square = new JPanel();
                square.setPreferredSize(new Dimension(WIDEBOX,HEIGTHBOX));
                
                // box is select
                if(iRow == pieceSelected.getCoordinate().getRow() + 1 && 
                        iCol == pieceSelected.getCoordinate().getCol() + 1
                        && pieceSelected.getColor() != -1){
                    square.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                
                // List ches piece can go
                if( listPieceGo != null){
                    for(int i=0; i<listPieceGo.size(); i++){
                        if(iRow == listPieceGo.get(i).getRow() + 1  && 
                                iCol == listPieceGo.get(i).getCol() + 1)
                        square.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                    }
                }
                
                // Paint color board
                if(iRow %2 != 0){
                    if(iCol %2 != 0){
                        square.setBackground(Color.LIGHT_GRAY);
                    }
                    else{
                        square.setBackground(Color.GRAY);
                    }
                }
                if(iRow %2 == 0){
                    if(iCol %2 !=0){
                        square.setBackground(Color.GRAY);
                    }
                    else{
                        square.setBackground(Color.LIGHT_GRAY);
                    }
                    
                    
                }
                if(Main.Mode == Main.PLAYER_ONLINE && Main.capacity == Main.TO_BE_CHALLENGED){
                    if(BoardChess.chessBoardReverse[iRow -1][iCol - 1].getColor() != -1){
                        ImageIcon icon = new ImageIcon("image/" + 
                                            BoardChess.chessBoardReverse[iRow -1][iCol - 1].getName()
                                            +".png");        

                        JLabel label = new JLabel(); 
                            label.setIcon(icon); 
                            square.add(label); 
                    }
                }
                else{
                    if(BoardChess.chessBoard[iRow -1][iCol - 1].getColor() != -1){
                        ImageIcon icon =  icon = new 
                                    ImageIcon("image/" + 
                                            BoardChess.chessBoard[iRow -1][iCol - 1].getName()
                                            +".png");        

                        JLabel label = new JLabel(); 
                            label.setIcon(icon); 
                            square.add(label); 
                    }
                    
                } 
                chessBoard.add(square);
            }
        }
        return chessBoard;
    }
    
    
    //  CHECK WIN 1 - WIN ; 2 hòa
    public static int checkResult(){
        //check WIN
        if(checkMate() == true){
            if(breakSecret() == false)
                return 1;
        }
        
        // check Hoa
        if(checkMate() == false){
            if(BoardChess.getAllPossibleOfMyChess().size() == 0){
                return 2;
            }
        }
        
        return 0;
    }
    
    // Checkmate
    public static boolean checkMate(){
        List<Coordinate> listCoordinate = new ArrayList<Coordinate>();
        Coordinate king = null;
        for(int iRow = 0; iRow < 8; iRow++){
            for(int iCol = 0; iCol < 8; iCol++){
                if(BoardChess.chessBoard[iRow][iCol].getColor() != BoardChess.turn){
                    listCoordinate.addAll(BoardChess.chessBoard[iRow][iCol].getPossibleMove(chessBoard));       
                }
                if(BoardChess.turn == Piece.Black){
                    if(BoardChess.chessBoard[iRow][iCol].getName().equals("Black_King")){
                        king = new Coordinate(iRow, iCol);
                    }
                }
                if(BoardChess.turn == Piece.White){
                    if(BoardChess.chessBoard[iRow][iCol].getName().equals("White_King")){
                        king = new Coordinate(iRow, iCol);
                    }
                }
            }
         }
        
        for(int i = 0; i < listCoordinate.size(); i++){
            if(listCoordinate.get(i).getRow() == king.getRow() &&
                    listCoordinate.get(i).getCol() == king.getCol()){
                return true;
            }
        }
        return false;
    }
    
    public static boolean breakSecret(){
        List<ChessAndCoordinare> listChessAndCoordiante = new ArrayList<ChessAndCoordinare>();
        // get All chess of Player 
        listChessAndCoordiante = BoardChess.getAllPossibleOfMyChess();
        
        // Test breakSecret
        for(int i = 0; i < listChessAndCoordiante.size(); i++){
            int iRowBefore = listChessAndCoordiante.get(i).getMyChess().getRow();
            int iColBefore = listChessAndCoordiante.get(i).getMyChess().getCol();
            for(int j = 0; j < listChessAndCoordiante.get(i).getListCoordinate().
                    size();
                    j++){
                int iRowAfter = listChessAndCoordiante.get(i).getListCoordinate().
                        get(j).getRow();
                int iColAfter = listChessAndCoordiante.get(i).getListCoordinate()
                        .get(j).getCol();
                //Swap chess and test Breaksecret
                    // Swap
                Piece temp = BoardChess.chessBoard[iRowAfter][iColAfter];
                temp.getCoordinate().setRow(iRowAfter);
                temp.getCoordinate().setCol(iColAfter);

                BoardChess.chessBoard[iRowAfter][iColAfter] =
                        BoardChess.chessBoard[iRowBefore][iColBefore];
                BoardChess.chessBoard[iRowAfter][iColAfter].getCoordinate().setRow(iRowAfter);
                BoardChess.chessBoard[iRowAfter][iColAfter].getCoordinate().setRow(iColAfter);
                
                BoardChess.chessBoard[iRowBefore][iColBefore] = new
                  EmptyCell(-1, new Coordinate(iRowBefore, iColBefore));
                
                //TEST check Mate
                if(checkMate() == false){
                    BoardChess.chessBoard[iRowBefore][iColBefore] =
                            BoardChess.chessBoard[iRowAfter][iColAfter];
                     BoardChess.chessBoard[iRowBefore][iColBefore].
                             getCoordinate().setRow(iRowBefore);
                      BoardChess.chessBoard[iRowBefore][iColBefore].
                              getCoordinate().setCol(iColBefore);
                      
                    BoardChess.chessBoard[iRowAfter][iColAfter] = temp;
                    BoardChess.chessBoard[iRowAfter][iColAfter].
                            getCoordinate().setRow(temp.getCoordinate().getRow());
                    BoardChess.chessBoard[iRowAfter][iColAfter].
                            getCoordinate().setCol(temp.getCoordinate().getCol());

                    return true;
                }
                else{
                     BoardChess.chessBoard[iRowBefore][iColBefore] =
                            BoardChess.chessBoard[iRowAfter][iColAfter];
                     BoardChess.chessBoard[iRowBefore][iColBefore].
                             getCoordinate().setRow(iRowBefore);
                      BoardChess.chessBoard[iRowBefore][iColBefore].
                              getCoordinate().setCol(iColBefore);
                      
                    BoardChess.chessBoard[iRowAfter][iColAfter] = temp;
                    BoardChess.chessBoard[iRowAfter][iColAfter].
                            getCoordinate().setRow(temp.getCoordinate().getRow());
                    BoardChess.chessBoard[iRowAfter][iColAfter].
                            getCoordinate().setCol(temp.getCoordinate().getCol());;
                }       
            }
        }
        return false;
    }
    
    // Get all possible
    
    public static List<ChessAndCoordinare> getAllPossibleOfMyChess(){
        List<ChessAndCoordinare> list = new ArrayList<ChessAndCoordinare>();
         // get All chess of Player 
        for(int iRow = 0; iRow < 8; iRow++){
            for(int iCol = 0; iCol < 8; iCol++){
                if(BoardChess.chessBoard[iRow][iCol].getColor() == BoardChess.turn){
                    Coordinate myChess = new Coordinate(iRow, iCol);
                    ChessAndCoordinare temp = new 
                    ChessAndCoordinare(BoardChess.chessBoard[iRow][iCol].
                            getPossibleMove(chessBoard), myChess);
                    list.add(temp);
                    
                }
            }
        }
        return list;
    }
    
    public static void initValue(){
        BoardChess.turn = Piece.White;
        BoardChess.checkInit = false;

    }
}
