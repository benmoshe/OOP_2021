import pygame, sys
from pygame.locals import *
pygame.init()
FPS = 20
fpsClock = pygame.time.Clock()
Displaysrf = pygame.display.set_mode((700, 600), 0, 20)
pygame.display.set_caption('Animation')
white = (255, 255, 255)
dImg = pygame.image.load('img2.png')
dx = 10
dy = 10
direction = 'right'
while True:
    Displaysrf.fill(white)
    if direction == 'right':
        dx += 5
        if dx == 280:
            direction = 'down'
    elif direction == 'down':
        dy += 5
        if dy == 220:
            direction = 'left'
    elif direction == 'left':
        dx -= 5
        if dx == 10:
            direction = 'up'
    elif direction == 'up':
        dy -= 5
        if dy == 10:
            direction = 'right'
    Displaysrf.blit(dImg, (dx, dy))
    for event in pygame.event.get():
        if event.type == QUIT:
            pygame.quit()
            sys.exit()
    pygame.display.update()
    fpsClock.tick(FPS)