/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Piece.Coordinate;

/**
 *
 * @author nthan
 */
public class PieceGo {
    private Coordinate start;

    public Coordinate getStart() {
        return start;
    }

    public void setStart(Coordinate start) {
        this.start = start;
    }

    public Coordinate getTarget() {
        return target;
    }

    public void setTarget(Coordinate target) {
        this.target = target;
    }
    private Coordinate target;

    public PieceGo(Coordinate start, Coordinate target) {
        this.start = start;
        this.target = target;
    }
    
    
}
