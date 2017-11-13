/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamechess;

import Message.Request;
import Piece.Coordinate;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author nthan
 */
public class Client {
    public static Socket server;
    public String name;

    public Client(){
    }

    public static void connect(String IP, int port){
        try{
            server = new Socket(IP, port);
        }
        catch(Exception ex){ex.printStackTrace();}
    }

    public static synchronized void requestPlayerList(){
        Request req = new Request(Request.ONLINE_PLAYER);
        sendRequest(req);
    }
    
    public static void requestSurrener(){
        Request req = new Request(Request.SURRENDER);
        sendRequest(req);
    }
    
    public static void requestMoveChess(Coordinate start, Coordinate target){
        Request req = new Request(Request.MOVE_CHESS);
        req.setStart(start);
        req.setTarget(target);
        sendRequest(req);
    }
    
    public static void requestCrown(Coordinate coordinate, int CrownPiece){
        Request req = new Request(Request.CROWN);
        req.setCoordinateCrown(coordinate);
        req.setCrown(CrownPiece);
        sendRequest(req);
    }
    public static void requestRefuseChallenge(String enemy){
        Request req = new Request(Request.REFUSE_CHALLENGE);
        req.setIPChallenge(enemy);
        sendRequest(req);
    }


    public static void requestAcceptChallenge(String enemy){
        Request req = new Request(Request.ACCEPT_CHALLENGE);
        req.setIPChallenge(enemy);
        sendRequest(req);
    }

    public static void requestChallenge(String IPChallenge){
        Request req = new Request(Request.SEND_CHALLENGE);
        req.setIPChallenge(IPChallenge);
        sendRequest(req);
    }
    
    public static void requestPlaying(){
        Request req = new Request(Request.PLAYING);
        sendRequest(req);
    }

    public static void RequestDisConnect(String IPDisConnect){
        Request req = new Request(Request.DISCONNECT);
        req.setIPChallenge(IPDisConnect);
        sendRequest(req);
    }
    public static Request response(){
        Request req = null;
        try{
            ObjectInputStream obj = new
                    ObjectInputStream(server.getInputStream());
            req = (Request)obj.readObject();
        }
        catch(Exception ex){ex.printStackTrace();}
        return req;
    }
    
    
    

    private static void sendRequest(Request request) {
        try{
            ObjectOutputStream obj = new
                    ObjectOutputStream(server.getOutputStream());
            obj.writeObject(request);
            obj.flush();
        }
        catch(Exception ex){ex.printStackTrace();}
    }


}
