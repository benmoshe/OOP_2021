package ex4_java_client;

/**
 * OOP Ex4 - Do NOT change this class! as it is the server "api"
 */
import java.io.*;
import java.net.*;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws UnknownHostException,
            IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        String res = sendMessage(".");
        in.close();
        out.close();
        clientSocket.close();
    }

    public String getAgents() {
        String res = "";
        try {
            res = sendMessage("getAgents");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;

    }

    public String addAgent(String jsonOfNode) {
        String res2 = "false";
        try {
            String res1 = sendMessage("addAgent");
            if (res1.equals("getNode")) {

                res2 = sendMessage(jsonOfNode);
            }
        } catch (IOException e) {
            System.out.println("problem with the server. make sure server is running");
            e.printStackTrace();
        }
        return res2;
    }

    public void start() {
        try {
            String res1 = sendMessage("startGame");
        } catch (IOException e) {
            System.out.println("problem with the server. make sure server is running");
            e.printStackTrace();
        }
    }

    public void chooseNextEdge(String jsonAgentAndNode) {
        String res1;
        try {
            res1 = sendMessage("chooseNextEdge");
            if (res1.equals("getAgentAndNode")) {
                String res2 = sendMessage(jsonAgentAndNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move() {
        try {
            String res1 = sendMessage("move");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            String res1 = sendMessage("stopGame");
        } catch (IOException e) {
            System.out.println("problem with the server. make sure server is running");
            e.printStackTrace();
        }
    }

    /**
     * Receives graph structure from game server
     * 
     * @return if respond has been received: the graph structure as json. else: null
     */
    public String getGraph() {
        String res = null;
        try {
            res = sendMessage("getGraph");
        } catch (IOException e) {
            System.out.println("problem with the server. make sure server is running");
            e.printStackTrace();
        }
        return res;
    }

    public void login(String id) {
        String res1;
        try {
            res1 = sendMessage("login");
            if (res1.equals("getId")) {
                String res2 = sendMessage(id);
            } else {
                throw new IOException("coudn't comunicate with server");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPokemons() {
        String res = null;
        try {
            res = sendMessage("getPokemons");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String timeToEnd() {
        String res = null;
        try {
            res = sendMessage("timeToEnd");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String isRunning() {
        String res = "false";
        try {
            res = sendMessage("isRunning");
        } catch (IOException e) {
            System.err.println("ERR: no connection - to the server - it is down!");
            // e.printStackTrace();
        }
        return res;
    }

    public String getInfo() {
        String res = null;
        try {
            res = sendMessage("getInfo");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}