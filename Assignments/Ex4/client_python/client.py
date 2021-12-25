"""
OOP - Ex4
Do NOT change this file - it should be remained "AS IS" - as it communicating with the "server"
"""
import socket

MSGLEN = 10000

class Client:
    def start_connection(self, ip, port):
        self.soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.soc.connect((ip, port))

    def __send_message(self, msg):
        self.soc.send((msg + "\n").encode())
        return self.soc.recv(MSGLEN).decode().strip()

    def get_agents(self):
        return self.__send_message('getAgents')

    def add_agent(self, json_of_node):
        res = self.__send_message("addAgent")
        if res == "getNode":
            res = self.__send_message(json_of_node)
        return res

    def get_graph(self):
        res = self.__send_message("getGraph")
        return res

    def get_info(self):
        res = self.__send_message("getInfo")
        return res

    def get_pokemons(self):
        res = self.__send_message("getPokemons")
        return res

    def is_running(self):
        res = self.__send_message("isRunning")
        return res

    def time_to_end(self):
        res = self.__send_message("timeToEnd")
        return res

    def start(self):
        res = self.__send_message("startGame")
        return res

    def stop(self):
        res = self.__send_message("stopGame")
        return res

    def move(self):
        res = self.__send_message("move")
        return res

    def choose_next_edge(self, NextAgentNodeJson):
        res = self.__send_message("chooseNextEdge")
        if res == "getAgentAndNode":
            res = self.__send_message(NextAgentNodeJson)
        return res

    def log_in(self, id_str):
        res = self.__send_message("login")
        if res == "getId":
            res = self.__send_message(id_str)
        return res

    def stop_connection(self):
        res = self.__send_message('.')
        if res == 'good bye':
            self.soc.close()