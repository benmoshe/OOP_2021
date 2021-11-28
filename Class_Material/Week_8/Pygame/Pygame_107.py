import sys

import pygame
import random
pygame.init()
black = [0, 0, 0]
white = [255, 255, 255]
size = [600, 400]
scr = pygame.display.set_mode(size)
pygame.display.set_caption("Snow Animation")
snow_list = []
for i in range(50):
    x = random.randrange(0, 700)
    y = random.randrange(0, 600)
    snow_list.append([x, y])
clock = pygame.time.Clock()
done = False
while not done:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            done = True
    scr.fill(black)
    for i in range(len(snow_list)):
        pygame.draw.circle(scr, white, snow_list[i], 2)
        snow_list[i][1] += 1
        if snow_list[i][1] > 400:
            y = random.randrange(-50, -10)
            snow_list[i][1] = y
            x = random.randrange(0, 400)
            snow_list[i][0] = x
    pygame.display.flip()
    clock.tick(20)
pygame.quit()
sys.exit()