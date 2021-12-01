import pygame
from pygame import Color, display, time, Rect
from pygame.constants import K_LEFT, K_RIGHT, KEYDOWN, KEYUP, QUIT
import random
from pygameMenuPro.pygame_menu_pro import COLOR_WHITE

# Classes


class Player:
    def __init__(self, initial_pos) -> None:
        self.size = (50, 50)
        self.rect = Rect(initial_pos, self.size)
        self.speed = 5
        self.color = Color(30, 100, 42)
        self.dir = 0

    def move(self):
        self.rect.move_ip(self.dir*self.speed, 0)
        self.rect.topleft = (
            self.rect.topleft[0] % WIDTH, self.rect.topleft[1])

    def get_pos(self):
        return self.rect.topleft

    def draw(self, surface: pygame.Surface):
        pygame.draw.rect(surface, self.color, self.rect)


class Tile:
    def __init__(self):
        self.rect = Rect((0, HEIGHT), (30, 60))
        self.speed = 0

    def set_pos(self, pos):
        self.rect.topleft = pos

    def get_pos(self):
        return self.rect.topleft

    def move(self):
        self.rect.move_ip(0, self.speed)

    def draw(self, surface):
        pygame.draw.rect(surface, Color(163, 128, 91), self.rect)


pygame.init()
clock = time.Clock()

WIDTH = 800
HEIGHT = (WIDTH * 9)//16
screen = display.set_mode((WIDTH, HEIGHT), depth=64)

game_over = False
player = Player((WIDTH//2 - 25, (HEIGHT*5)//6))
tiles = [Tile()]*10

while(not game_over):
    screen.fill(Color(0, 0, 0))
    for t in tiles:
        if(t.rect.colliderect(player.rect)):
            game_over = True
        if(t.get_pos()[1] >= HEIGHT):
            t.set_pos((random.randint(0, WIDTH - 30), -60))
            t.speed = random.randint(1, 3)
        t.move()
        t.draw(screen)
    player.draw(screen)
    player.move()
    for event in pygame.event.get():
        if event.type == QUIT:
            pygame.quit()
            exit(0)
        if event.type == KEYDOWN:
            if(event.key == K_LEFT):
                player.dir = -1
            elif(event.key == K_RIGHT):
                player.dir = 1
        elif event.type == KEYUP:
            player.dir = 0

    display.update()
    clock.tick(60)
