import pygame
from pygame import display, Color, time, Rect
from pygame.constants import K_LEFT, K_RIGHT, KEYDOWN, KEYUP, QUIT
from Player import Player
from Tile import Tile
from random import randint

pygame.font.init()


def clamp(player):
    x, y = player.get_pos()
    w = player.get_size()[0]
    player.set_pos((x % (WIDTH - w), y))


WIDTH = 800
HEIGHT = (WIDTH * 6)//9
REFRESH_RATE = 60
FONT = game_over_screen = pygame.font.SysFont(
    'Arial', 60, bold=True)

SCORE_POS = ((WIDTH * 8)//9, (HEIGHT*6)//7)

# initialization
pygame.init()

# clock for refresh rate
clock = time.Clock()

# setting display
screen = display.set_mode((WIDTH, HEIGHT), depth=32)
display.set_caption('My First Game')

game_over = False

# creating player
player_initial_size = (50, 50)
player = Player(
    (WIDTH//2 - player_initial_size[0]//2, (HEIGHT * 5)//6), player_initial_size)
player.speed = 5

# creating tiles
tile_size = (30, 60)
tiles = [Tile((randint(0, WIDTH - tile_size[0]), 0), tile_size)
         for i in range(10)]

for t in tiles:
    t.speed = randint(1, 3)


while(not game_over):

    # checking events and inputs
    for event in pygame.event.get():
        if event.type == QUIT:
            pygame.quit()
            exit(0)
        elif event.type == KEYDOWN:
            if event.key == K_LEFT:
                player.dir = -1
            elif event.key == K_RIGHT:
                player.dir = 1
        elif event.type == KEYUP:
            player.dir = 0

    # tiles manage
    for t in tiles:
        if t.get_pos()[1] >= HEIGHT:
            t.set_pos((randint(0, WIDTH - tile_size[0]), 0))
            t.speed = randint(1, 6)
            player.score += 1
        t.move()
        # game_over_check
        if t.rect.colliderect(player.rect):
            game_over = True

    # move player
    player.move()
    clamp(player)

    # refresh screen surface
    screen.fill(Color(0, 0, 0))

    # draw tile
    for t in tiles:
        pygame.draw.rect(screen, t.color, t.rect)

    # draw player
    pygame.draw.rect(screen, player.color, player.rect)

    # draw score
    score_surface = FONT.render(
        str(player.score), True, Color(255, 255, 255, 200))
    screen.blit(score_surface, SCORE_POS)

    # update main display
    display.update()
    clock.tick(REFRESH_RATE)

screen.fill(Color(255, 0, 0))
game_over_screen = FONT.render('GAME OVER', False, Color(0, 0, 0))
screen.blit(game_over_screen, (WIDTH//2 -
            game_over_screen.get_width()//2, HEIGHT//2 - game_over_screen.get_height()//2))
display.update()
clock.tick(1)
pygame.quit()
