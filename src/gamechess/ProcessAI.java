/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamechess;

import AI.AI;
import AI.PieceGo;
import Piece.Coordinate;
import Piece.EmptyCell;
import Piece.Piece;

/**
 *
 * @author nthan
 */
public class ProcessAI {
   private Main main;
    private int turn;
    public PieceGo piecego = null;
    private AI aI;
    public ProcessAI(Main _main, int _turn){
        this.main = _main;
        this.turn = _turn;
        aI = new AI();
    }
    public void PieceGO(){
        piecego = aI.findPieceGo(BoardChess.chessBoard, Piece.Black);
        System.out.println("turn:" + BoardChess.turn);
        System.out.println(piecego.getStart().getRow() + "--" + piecego.getStart().getCol());
        System.out.println(piecego.getTarget().getRow() + "--" + piecego.getTarget().getCol());
        int iRowStart = piecego.getStart().getRow();
        int iColStart = piecego.getStart().getCol();
        int iRowTarget = piecego.getTarget().getRow();
        int iColTarget = piecego.getTarget().getCol();

        // piece go
        BoardChess.chessBoard[iRowTarget][iColTarget] =
                BoardChess.chessBoard[iRowStart][iColStart];
        BoardChess.chessBoard[iRowTarget][iColTarget].setCoordinate
                (new Coordinate(iRowTarget, iColTarget));

        BoardChess.chessBoard[iRowStart][iColStart] = new
            EmptyCell(-1,new Coordinate(iRowStart, iColStart));

        Main.waitAI = true;
        BoardChess.turn = Piece.White;          
        main.chessBoard.removeAll();
        main.chessBoard.add(BoardChess.paintChessBox(new
            EmptyCell(-1, new Coordinate(-1, -1)), null));
        main.setVisible(true);
    }
    
}
