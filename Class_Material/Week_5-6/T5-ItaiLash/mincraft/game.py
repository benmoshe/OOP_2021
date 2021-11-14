from ursina import Ursina

from block_type import Block
from player import Player
from sky import Sky
from world import World
from ursina import *
from ursina import camera, mouse
from ursina.prefabs.first_person_controller import FirstPersonController


class Game():

    def __init__(self):
        self.app = Ursina()
        window.fullscreen = True
        self.word = World()
        self.player = Player()
        self.sky = Sky()
        self.app.run()



if __name__ == '__main__':
    game = Game()
