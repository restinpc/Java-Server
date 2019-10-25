/**
 * Java server - Main class
 *
 * @ 25.10.2019 # Aleksandr Vorkunov <developing@nodes-tech.ru>
 */

package server;

public class Master {
    public static void main(String[] args) {
        System.out.println("Socket based server with support of indexing files from environment.");
        Frontend frontend = new Frontend();
        int port = 4000;
        if(args.length > 0 && Integer.parseInt(args[0])>0){
            port = Integer.parseInt(args[0]);
        }
        System.out.println("Server will be available as 127.0.0.1:"+port);
        TCPServer server = new TCPServer(frontend);
        server.start(port);
    }
}