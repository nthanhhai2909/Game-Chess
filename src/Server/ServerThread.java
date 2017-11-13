/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.ObjectInputStream;
import java.net.Socket;
import Message.Request;
import Piece.Coordinate;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nthan
 */
public class ServerThread extends Thread{
    public static final int CHALLENGE = 103;
    public static final int TO_BE_CHALLENGED = 104;
    private Socket client;
    private String namePlayer;
    private String namePlayerEnemy ="";
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public String getNamePlayerEnemy() {
        return namePlayerEnemy;
    }

    public void setNamePlayerEnemy(String namePlayerEnemy) {
        this.namePlayerEnemy = namePlayerEnemy;
    }

    public ServerThread(Socket client, String name) {
        this.client = client;
        this.namePlayer = name;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public String getnamePlayer() {
        return namePlayer;
    }

    public void setnamePlayer(String name) {
        this.namePlayer= name;
    }
    
    public void run(){
        while(true){
            try{
                ObjectInputStream obj = new
                    ObjectInputStream(client.getInputStream());
                Request request = (Request)obj.readObject();
                
                switch(request.getType()){
                    case Request.ACCEPT_CHALLENGE:
                        System.out.println("ACCEPT_CHALLENGE");
                        capacity = TO_BE_CHALLENGED;
                        namePlayerEnemy = request.getIPChallenge();
                        for(int i = 0; i < Server.listClient.size(); i++){
                            if(namePlayerEnemy.equals(Server.listClient.get(i).getNamePlayer())){
                                Server.listClient.get(i).setNamePlayerEnemy(namePlayer);
                                Request reqApp = new Request(Request.ACCEPT_CHALLENGE);
                                reqApp.setIPChallenge(namePlayer);
                                try{
                                    ObjectOutputStream objout = new
                                        ObjectOutputStream(Server.listClient
                                                .get(i).client.getOutputStream());
                                    objout.writeObject(reqApp);
                                    objout.flush();
                                }            
                                catch(Exception ex){ex.printStackTrace();}
                                break;
                            }
                        }                           
                        break;
                    case Request.ONLINE_PLAYER:
                        System.out.println("GET_ONLINE_PLAYER");
                        Request req = new Request(Request.ONLINE_PLAYER);
                        List<String> list = new ArrayList<String>();
                        for(int i = 0; i < Server.listClient.size(); i++){
                            list.add(Server.listClient.get(i).getClient().
                                    getInetAddress().toString());
                        }
                       
                        list.remove(this.client.getInetAddress().toString());
                            
                        req.setPlayerList(list);
                        try{
                            ObjectOutputStream objout = new
                                ObjectOutputStream(client.getOutputStream());
                            objout.writeObject(req);
                            objout.flush();
                        }            
                        catch(Exception ex){ex.printStackTrace();}  
                        break;
                    case Request.MOVE_CHESS:  
                        System.out.println("MOVE_CHESS");
                        
                        Request reqMovechess = new Request(Request.MOVE_CHESS);   
                        reqMovechess.setStart(new
                        Coordinate(request.getStart().getRow(), request.getStart().getCol()));
                        reqMovechess.setTarget(new Coordinate(request.getTarget().getRow(),
                                request.getTarget().getCol()));        
                        
                        for(int i = 0 ; i<Server.listClient.size(); i++){
                            if(namePlayerEnemy.equals(Server.listClient.get(i).namePlayer)){
                                try{
                                    ObjectOutputStream objout = new
                                        ObjectOutputStream(Server.listClient.get(i).
                                                client.getOutputStream());
                                    objout.writeObject(reqMovechess);
                                    objout.flush();
                                    }            
                                  catch(Exception ex){ex.printStackTrace();}
                            }
                        }
                         break;
                    case Request.CROWN:
                        System.out.println("CROWN");
                        for(int i = 0 ; i<Server.listClient.size(); i++){
                            if(namePlayerEnemy.equals(Server.listClient.get(i).namePlayer)){
                                try{
                                    ObjectOutputStream objout = new
                                        ObjectOutputStream(Server.listClient.get(i).
                                                client.getOutputStream());
                                    objout.writeObject(request);
                                    objout.flush();
                                    }            
                                  catch(Exception ex){ex.printStackTrace();}
                            }
                        }
                        break;
                             
                    case Request.REFUSE_CHALLENGE:
                        System.out.println("REFUSE_CHALLENGE");
                        for(int i = 0; i <Server.listClient.size(); i++){
                            if(request.getIPChallenge().
                                    equals(Server.listClient.get(i).getnamePlayer())){
                                Request reqRefuse = new Request(Request.REFUSE_CHALLENGE);
                                reqRefuse.setIPChallenge(namePlayer);
                                try{
                                    ObjectOutputStream objout = new
                                        ObjectOutputStream(Server.listClient.get(i).
                                                client.getOutputStream());
                                    objout.writeObject(reqRefuse);
                                    objout.flush();
                                    }            
                                  catch(Exception ex){ex.printStackTrace();}
                              }
                        }
                        break;
                    case Request.SEND_CHALLENGE:
                        System.out.println("SEND_CHALLENGE");
                        capacity = CHALLENGE;
                        for(int i = 0; i <Server.listClient.size(); i++){
                            if(request.getIPChallenge().
                                    equals(Server.listClient.get(i).getnamePlayer()) &&
                                    Server.listClient.get(i).namePlayerEnemy.equals("")){
                                Request reqChall = new Request(Request.SEND_CHALLENGE);
                                reqChall.setIPChallenge(namePlayer);
                                try{
                                ObjectOutputStream objout = new
                                    ObjectOutputStream(Server.listClient.get(i).
                                            client.getOutputStream());
                                objout.writeObject(reqChall);
                                objout.flush();
                                break;
                                 }catch(Exception ex){ex.printStackTrace();}
                                }
                            if(!Server.listClient.get(i).namePlayerEnemy.equals("")){
                                Request reqplay = new Request(Request.PLAYING);
                                reqplay.setIPChallenge(request.getIPChallenge());
                                try{    
                                    ObjectOutputStream objout = new
                                        ObjectOutputStream(client.getOutputStream());
                                    objout.writeObject(reqplay);
                                    objout.flush();
                                    break;
                                 }catch(Exception ex){ex.printStackTrace();}
                            }
                         }
                        
                        break;
                    case Request.SURRENDER:
                        System.out.println("Sur");
                        for(int i = 0 ; i<Server.listClient.size(); i++){
                            if(namePlayerEnemy.equals(Server.listClient.get(i).namePlayer)){
                                try{
                                    ObjectOutputStream objout = new
                                        ObjectOutputStream(Server.listClient.get(i).
                                                client.getOutputStream());
                                    objout.writeObject(request);
                                    objout.flush();
                                    }            
                                  catch(Exception ex){ex.printStackTrace();}
                            }
                        }                            break;
                    case Request.DISCONNECT:
                        System.out.println("DISCONNECT");
                        this.client.close();
                        Server.listClient.remove(this);
                        Server.ResponeListOnlineForPlayer();
                        System.out.println( Server.listClient.size() + "-----   ");
                        this.stop();
                        break;
                }
                
            }
            catch(Exception ex){}
        }
        
    }
    
}
