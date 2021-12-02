from pygame import Rect
import pygame
from pygame import Color


class Player:
    def __init__(self, initial_pos: tuple[int, int], initial_size: tuple[int, int]):
        self.dir = 0
        self.speed = 0
        self.score = 0
        self.rect = Rect(initial_pos, initial_size)
        self.color = Color(72, 37, 123)

    def get_pos(self):
        return self.rect.topleft

    def set_pos(self, pos):
        self.rect.topleft = pos

    def get_size(self):
        return self.rect.size

    def set_size(self, size):
        self.rect.size = size

    def move(self):
        self.rect.move_ip(self.dir*self.speed, 0)
