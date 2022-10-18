package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientStr {
    String name_server = "localhost";
    int serverport = 7073;
    Socket mysocket;
    BufferedReader keyboard;
    String user;
    String received;
    DataOutputStream output;
    BufferedReader input;


    public Socket connect()
    {

        try {
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            mysocket = new Socket(name_server, serverport);
            output = new DataOutputStream(mysocket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));
        } catch (UnknownHostException e) {
            // TODO: handle exception
            System.err.println("Unknowed host");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Error during the connection");
            System.exit(1);
        }
        return mysocket;
        
    }

    public void comunicate()
    {
        for(;;)
        try {
            System.out.println("Insert string "+ '\n');
            user = keyboard.readLine();
            System.out.println("Sending string to server...");
            output.writeBytes(user + '\n');
            received = input.readLine();
            System.out.println("server feedback" + '\n' + received);
            if(user.equals("END"))
            {
                System.out.println("CLIENT : execution ended");
                mysocket.close();
                break;
            }
            else if(user.equals("POWER OFF"))
            {
                System.out.println("CLIENT : all socket closed");
                mysocket.close();
                break;
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            System.out.println("Error during the connection");
            System.exit(1);
        }
    

    } 
}
