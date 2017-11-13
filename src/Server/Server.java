/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
import Message.Request;
import Piece.Piece;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author nthan
 */
public class Server {
    
    public static List<ServerThread> listClient = new ArrayList<ServerThread>();
    public static void main(String argv[]){
        
        try{
          ServerSocket serverSocket = new ServerSocket(2909);
          // get IP SERVER
            InetAddress myHost = InetAddress.getLocalHost();
            System.out.println("Server IP: " + myHost.getHostAddress());
            System.out.println("Server name: " + myHost.getHostName());
            System.out.println("Server PORT: 2909" );
            while(true){
                Socket client = serverSocket.accept();
                ServerThread serverTheard = new ServerThread(client,
                        client.getInetAddress().toString());
                System.out.println(client.getInetAddress() + " " 
                        + client.getPort() + " connected");
                
                listClient.add(serverTheard);
                // respone list onoline
                ResponeListOnlineForPlayer();

                serverTheard.start();
            }
        }
        catch(Exception ex){ex.printStackTrace();}
    }
    
    public static void ResponeListOnlineForPlayer(){
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < listClient.size(); i++){
            list.add(Server.listClient.get(i).getClient().
                    getInetAddress().toString());
              
        }
        
        for(int i = 0; i <listClient.size(); i++){
            List temp = new ArrayList<String>(list);
            temp.remove(i);
            
            Request req = new Request(Request.ONLINE_PLAYER);
            req.setPlayerList(temp);
            try{
                ObjectOutputStream objout = new
                    ObjectOutputStream(Server.listClient.get(i)
                            .getClient().getOutputStream());
                objout.writeObject(req);
                objout.flush();
            }
            catch(Exception ex){ex.printStackTrace();}
            
        }
        
    }
}
