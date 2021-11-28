import sys

import pygame
pygame.init()
w=550;
h=400
scr = pygame.display.set_mode( (w, h) )
pygame.display.set_caption('click on image')
image = pygame.image.load("img.jpg").convert()
x = 20;
y = 30;
scr.blit(image, (x,y))
pygame.display.flip()
running = True
while (running):
    for e in pygame.event.get():
        if e.type == pygame.QUIT:
            running = False
        if e.type == pygame.MOUSEBUTTONDOWN:
            x, y = e.pos
            if image.get_rect().collidepoint(x, y):
                print('clicked on image:', str(x), ",", str(y))
pygame.quit()
sys.exit()