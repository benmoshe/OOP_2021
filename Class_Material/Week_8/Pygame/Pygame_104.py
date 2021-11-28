import pygame
import sys
pygame.init()
s = (600, 500)
screen = pygame.display.set_mode(s)
color_white = (255,255,255)
color_light = (180,180,180)
color_dark = (110,110,110)
width = screen.get_width()
height = screen.get_height()
smallfont = pygame.font.SysFont('Arial',30)
text = smallfont.render('Quit' , True , color_white)
while True:
    for e in pygame.event.get():
        if e.type == pygame.QUIT:
            pygame.quit()
        if e.type == pygame.MOUSEBUTTONDOWN:
            if width/2 <= mouse[0] <= width/2+140 and height/2 <= mouse[1] <= height/2+40:
                pygame.quit()
                sys.exit()
    screen.fill((0,0,0))
    mouse = pygame.mouse.get_pos()
    if width/2 <= mouse[0] <= width/2+140 and height/2 <= mouse[1] <= height/2+40:
        pygame.draw.rect(screen,color_light,[width/2,height/2,140,40])
    else:
        pygame.draw.rect(screen,color_dark,[width/2,height/2,140,40])
    screen.blit(text , (width/2+50,height/2))
    pygame.display.update()