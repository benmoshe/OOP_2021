from pygame import Rect
import pygame
from pygame import Color


class Tile:
    def __init__(self, initial_pos, initial_size: tuple[int, int]):
        self.rect = Rect(initial_pos, initial_size)
        self.speed = 0
        self.color = Color(72, 200, 87)

    def move(self):
        self.rect.move_ip(0, self.speed)

    def get_pos(self):
        return self.rect.topleft

    def set_pos(self, pos):
        self.rect.topleft = pos
