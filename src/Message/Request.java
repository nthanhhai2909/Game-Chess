/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;
import Piece.Coordinate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author nthan
 */
public class Request implements Serializable{
   
    public static final int ONLINE_PLAYER = 1000;
    public static final int SEND_CHALLENGE = 1001;
    public static final int ACCEPT_CHALLENGE = 1002;
    public static final int REFUSE_CHALLENGE = 1003;
    public static final int MOVE_CHESS = 1004;
    public static final int CROWN = 1008;
    public static final int SURRENDER = 1005;
    public static final int DISCONNECT = 1006;
    public static final int PLAYING = 1007;
    
    public static final int CROWNROOK = 2000;
    public static final int CROWNKNIGHT= 2001;
    public static final int CROWNBISHOP = 2002;
    public static final int CROWNQUEEN = 2003;
    
    private Coordinate start;
    private Coordinate target;
    private Coordinate coordinateCrown;
    private int type;
    private String IPChallenge;
    private int Crown;

    public Coordinate getCoordinateCrown() {
        return coordinateCrown;
    }

    public void setCoordinateCrown(Coordinate coordinateCrown) {
        this.coordinateCrown = coordinateCrown;
    }

    public int getCrown() {
        return Crown;
    }

    public void setCrown(int Crown) {
        this.Crown = Crown;
    }
    private List<String> playerList = new ArrayList<>();

    public List<String> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<String> playerList) {
        this.playerList = playerList;
    }
    
    public Coordinate getStart() {
        return start;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIPChallenge() {
        return IPChallenge;
    }

    public void setIPChallenge(String IPChallenge) {
        this.IPChallenge = IPChallenge;
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

    public Request(int type) {
        this.type = type;
    }
   

   
    
}
