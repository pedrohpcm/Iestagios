/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Database.ProfileDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.websocket.OnMessage;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author joaovictor
 */
@ServerEndpoint("/server")
public class Server {
    public Server() {
        
    }
    
    @OnMessage
    public void Message(Session session, String text) throws IOException, InterruptedException {
        System.out.println("Servidor recebeu uma mensagem: " + text);
        String[] array = text.split(",");
        
        if ("register".equals(array[0])) {
            Profile profile = new Profile();
            profile.setEmail(array[1]);
            profile.setPassword(array[2]);
            
            ProfileDAO dao = new ProfileDAO();
            dao.Create(profile);
        }
        if ("enter".equals(array[0])) {
            ArrayList profiles;
            ProfileDAO dao = new ProfileDAO();
            profiles = dao.Retrieve();
            boolean registered = false;
            
            for (Iterator it = profiles.iterator(); it.hasNext();) {
                Object p = it.next();
                if (((Profile) p).getEmail().equals(array[1]) && ((Profile) p).getPassword().equals(array[2])) {
                    registered = true;
                    RemoteEndpoint.Basic remote = session.getBasicRemote();
                    remote.sendText("yes");
                }
            }
            if (registered == false) {
                RemoteEndpoint.Basic remote = session.getBasicRemote();
                remote.sendText("no");
            }
        }
        if ("profile".equals(array[0])) {
            Profile profile = new Profile();
            profile.setFirstname(array[1]);
            profile.setLastname(array[2]);
            profile.setEmail(array[3]);
            profile.setPassword(array[4]);
            profile.setAge(Integer.parseInt(array[5]));
            
            ProfileDAO dao = new ProfileDAO();
            dao.Update(profile);
        }
        
        Thread.sleep(5000);
        session.close();
    }
}
