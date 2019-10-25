/**
 * Java Server - Endpoints listener.
 *
 * @ 25.10.2019 # Aleksandr Vorkunov <developing@nodes-tech.ru>
 */

package server;

import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TCPServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Frontend frontend;
    //--------------------------------------------------------------------------
    public TCPServer(Frontend frontend){
        this.frontend = frontend;
    }
    //--------------------------------------------------------------------------
    public void error(){
        try {
            StringBuilder output = new StringBuilder();
            output.append("HTTP/1.1 400 Bad request");
            output.append(System.getProperty("line.separator"));
            output.append("Content-Type: text/html; charset=utf-8");
            output.append(System.getProperty("line.separator"));
            output.append(System.getProperty("line.separator"));
            String content = "<!doctype html><html><body><h1>400 Bad Request</h1></body></html>";
            output.append(content);
            System.out.println("Server.error()"); 
            this.out.println(output.toString());
            this.out.close();
        }catch(Exception e){
            System.out.println("Server.error() -> "+e.getMessage()); 
        }
    }
    //--------------------------------------------------------------------------
    public void start(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server successfully started!");
            String command;
            do{
                this.clientSocket = this.serverSocket.accept();
                this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
                command = in.readLine();
                if(command != null) {
                    Pattern pattern = Pattern.compile("GET /(.*?) HTTP");
                    Matcher matcher = pattern.matcher(command);
                    if (matcher.find()) command = matcher.group(1);
                    //----------------
                    if (command.equals("ping")) {
                        //this.pong();
                    } else {
                        if(command.equals("") || command.equals("/")) {
                            command = "index.html";
                        }
                        byte[] data = this.frontend.fout("/"+command);
                        String fout;
                        if(data != null) {
                            this.out.println("HTTP/1.1 200 OK");
                            fout = new String(data);
                        }else{
                            this.out.println("HTTP/1.1 400 Page not found");
                            fout = "Page not found";
                        }
                        this.out.println("Content-Type: "+this.frontend.getMimeType(command)+"; charset=utf-8");
                        this.out.println("Access-Control-Allow-Origin: *");
                        this.out.println("Access-Control-Allow-Headers: *");
                        this.out.println("Access-Control-Allow-Methods: *");
                        this.out.println("Access-Control-Request-Headers: *, x-requested-with *");
                        this.out.println("");
                        this.out.println(fout);
                        this.out.close();
                    }
                }
            }while(command != "restart");
        } catch (IOException e) {
            System.out.println("Server.start() -> "+e.getMessage());
            try{
                this.clientSocket.close();
                this.serverSocket.close();
                this.out.close();
                this.in.close();
            }catch(Exception e1){
                System.out.println("Server.start().catch() -> "+e1.getMessage());
            }
            this.start(port);
        }
    }
}