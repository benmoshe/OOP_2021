package ex4_java_client; /**
 * @author AchiyaZigi
 * @since 0.0
 * @version 1.0
 * OOP Ex4 - Do NOT change this class! as it is the server "api"
 * 
 */
import java.io.*;
import java.net.*;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     *
     * use with ip='127.0.0.1' , port=6666
     * to start a new connection to the game server
     * 
     * @param ip
     * @param port
     * @throws UnknownHostException
     * @throws IOException
     */
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

    /**
     * use this function to signal end of comunication with the server.
     * 
     * @throws IOException
     */
    public void stopConnection() throws IOException {
        String res = sendMessage(".");
        in.close();
        out.close();
        clientSocket.close();
    }

    /**
     * 
     * returns: json str of agents. for example:
     * 
     * <pre>
     * {
     *     "Agents":[
     *         {
     *             "Agent":
     *             {
     *                 "id":0,
     *                 "value":0.0,
     *                 "src":0,
     *                 "dest":1,
     *                 "speed":1.0,
     *                 "pos":"35.18753053591606,32.10378225882353,0.0"
     *             }
     *         }
     *     ]
     * }
     * </pre>
     * 
     * @return json str of agents
     */
    public String getAgents() {
        String res = "";
        try {
            res = sendMessage("getAgents");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;

    }

    /**
     * 
     * @param jsonOfNode should be in this format:
     * 
     *                   <pre>
     * '{"id":0}'
     *                   </pre>
     * 
     *                   (replace 0 with the desired starting node for the agent.)
     * @return 'true' (as str) iff the agent has been added succesfuly
     */
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

    /**
     * use start to run the game
     */
    public void start() {
        try {
            String res1 = sendMessage("startGame");
        } catch (IOException e) {
            System.out.println("problem with the server. make sure server is running");
            e.printStackTrace();
        }
    }

    /**
     * <html>
     * <h1>choosing the next destination for a specific agent.</h1>
     * <h2>Note that if</h2>
     * <p>
     * 1. the agent is still moving on some edge, (a.k. agent.dest != -1)
     * or<br>
     * 2. the "next_node_id" isn't an adjacent vertex of agent.src,
     * then move() won't be affected by this invalid "next_node_id" choice.
     * </p>
     * </html>
     * 
     * @param jsonAgentAndNode next_agent_node_json should be in format:
     * 
     *                         <pre>
     * '{"agent_id":0, "next_node_id":1}'.
     *                         </pre>
     */
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

    /**
     * activate all valid choose_next_edge calls. returns: agents state with the
     * same form as get_agents()
     */
    public void move() {
        try {
            String res1 = sendMessage("move");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * use stop to end the game and upload results.
     * Note: results will be uploaded only after login and scores > 0.
     */
    public void stop() {
        try {
            String res1 = sendMessage("stopGame");
        } catch (IOException e) {
            System.out.println("problem with the server. make sure server is running");
            e.printStackTrace();
        }
    }

    /**
     * returns the graph as json str. for example:
     * 
     * <pre>
     * {
     *     "Edges":[
     *         {
     *             "src":0,
     *             "w":1.4004465106761335,
     *             "dest":1
     *         },
     *         {
     *             "src":0,
     *             "w":1.4620268165085584,
     *             "dest":10
     *         }
     *     ],
     *     "Nodes":[
     *         {
     *             "pos":"35.18753053591606,32.10378225882353,0.0",
     *             "id":0
     *         },
     *         {
     *             "pos":"35.18958953510896,32.10785303529412,0.0",
     *             "id":1
     *         },
     *         {
     *             "pos":"35.19341035835351,32.10610841680672,0.0",
     *             "id":10
     *         }
     *     ]
     * }
     * </pre>
     * 
     * @return the graph as json str
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

    /**
     * enter your id as str to login and upload your score to the web server
     * 
     * @param id
     */
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

    /**
     * returns the current pokemons state as json str.
     * example:
     * 
     * <pre>
     * {
     *     "Pokemons":[
     *         {
     *             "Pokemon":{
     *                 "value":5.0,
     *                 "type":-1,
     *                 "pos":"35.197656770719604,32.10191878639921,0.0"
     *             }
     *         }
     *     ]
     * }
     * </pre>
     * 
     * @return returns the current pokemons state as json str.
     *         for pokemon lying on edge (src,dest), then:
     * 
     *         <pre>
     * src < dest => type > 0
     * dest < src => type < 0
     *         </pre>
     */
    public String getPokemons() {
        String res = null;
        try {
            res = sendMessage("getPokemons");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 
     * @return time to end in mili-seconds str. for example: '29996'
     */
    public String timeToEnd() {
        String res = null;
        try {
            res = sendMessage("timeToEnd");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 
     * @return 'true' (as str) if the game is still running, else: returns 'false'
     *         (also str)
     */
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

    /**
     * returns the current game info. for example:
     * 
     * <pre>
     * {
     *     "GameServer":{
     *         "pokemons":1,
     *         "is_logged_in":false,
     *         "moves":1,
     *         "grade":0,
     *         "game_level":0,
     *         "max_user_level":-1,
     *         "id":0,
     *         "graph":"data/A0",
     *         "agents":1
     *     }
     * }
     * </pre>
     * 
     * @return the current game info.
     */
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