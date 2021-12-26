#!/usr/bin/env python
"""
@author AchiyaZigi
OOP - Ex4
Do NOT change this file - it should be remained "AS IS" - as it communicating with the "server"
"""
import socket

MSGLEN = 10000


class Client:

    def start_connection(self, ip, port):
        """
        use with ip='127.0.0.1' , port=6666
        to start a new connection to the game server
        """
        self.soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.soc.connect((ip, port))

    def __send_message(self, msg):
        self.soc.send((msg + "\n").encode())
        return self.soc.recv(MSGLEN).decode().strip()

    def get_agents(self):
        """
        returns: json str of agents. for example:\n
        {
            "Agents":[
                {
                    "Agent":
                    {
                        "id":0,
                        "value":0.0,
                        "src":0,
                        "dest":1,
                        "speed":1.0,
                        "pos":"35.18753053591606,32.10378225882353,0.0"
                    }
                }
            ]
        }
        """
        return self.__send_message('getAgents')

    def add_agent(self, json_of_node):
        """
        param json_of_node should be in this format: '{"id":0}'
        (replace 0 with the desired starting node for the agent.)
        returns 'true' (as str) iff the agent has been added succesfuly
        """
        res = self.__send_message("addAgent")
        if res == "getNode":
            res = self.__send_message(json_of_node)
        return res

    def get_graph(self):
        """
        returns the graph as json str. for example:\n
        {
            "Edges":[
                {
                    "src":0,
                    "w":1.4004465106761335,
                    "dest":1
                },
                {
                    "src":0,
                    "w":1.4620268165085584,
                    "dest":10
                }
            ],
            "Nodes":[
                {
                    "pos":"35.18753053591606,32.10378225882353,0.0",
                    "id":0
                },
                {
                    "pos":"35.18958953510896,32.10785303529412,0.0",
                    "id":1
                },
                {
                    "pos":"35.19341035835351,32.10610841680672,0.0",
                    "id":10
                }
            ]
        }
        """
        res = self.__send_message("getGraph")
        return res

    def get_info(self):
        """
        returns the current game info. for example:\n
        {
            "GameServer":{
                "pokemons":1,
                "is_logged_in":false,
                "moves":1,
                "grade":0,
                "game_level":0,
                "max_user_level":-1,
                "id":0,
                "graph":"data/A0",
                "agents":1
            }
        }
        """
        res = self.__send_message("getInfo")
        return res

    def get_pokemons(self):
        """
        returns the current pokemons state as json str.\n
        for pokemon lying on edge (src,dest), then:\n
        src < dest => type > 0\n
        dest < src => type < 0\n
        example:\n
        {
            "Pokemons":[
                {
                    "Pokemon":{
                        "value":5.0,
                        "type":-1,
                        "pos":"35.197656770719604,32.10191878639921,0.0"
                    }
                }
            ]
        }

        """
        res = self.__send_message("getPokemons")
        return res

    def is_running(self):
        """
        returns 'true' (as str) if the game is still running,
        else: returns 'false' (also str)
        """
        res = self.__send_message("isRunning")
        return res

    def time_to_end(self):
        """
        returns time to end in mili-seconds str.
        for example: '29996'
        """
        res = self.__send_message("timeToEnd")
        return res

    def start(self):
        """
        use start to run the game
        """
        res = self.__send_message("startGame")

    def stop(self):
        """
        use stop to end the game and upload results.
        Note: results will be uploaded only after login and scores > 0.
        """
        res = self.__send_message("stopGame")

    def move(self):
        """
        activate all valid choose_next_edge calls.
        returns: agents state with the same form as get_agents()
        """
        res = self.__send_message("move")
        return res

    def choose_next_edge(self, next_agent_node_json):
        """
        choosing the next destination for a specific agent.
        param: next_agent_node_json should be in format:\n
        '{"agent_id":0, "next_node_id":1}'.
        Note that if\n
        1. the agent is still moving on some edge, (a.k. agent.dest != -1)
        or 2. the "next_node_id" isn't an adjacent vertex of agent.src,
        then move() won't be affected by this invalid "next_node_id" choice.
        """
        res = self.__send_message("chooseNextEdge")
        if res == "getAgentAndNode":
            res = self.__send_message(next_agent_node_json)

    def log_in(self, id_str):
        """
        enter your id as str to login and upload your score to the web server
        """
        res = self.__send_message("login")
        if res == "getId":
            res = self.__send_message(id_str)

    def stop_connection(self):
        """
        use it to close the connection 'gracefuly'
        """
        res = self.__send_message('.')
        if res == 'good bye':
            self.soc.close()
