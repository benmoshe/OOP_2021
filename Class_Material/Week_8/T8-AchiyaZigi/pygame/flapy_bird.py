import pygame
from pygame import Rect, time, display, Color, QUIT, KEYDOWN, KEYUP, K_SPACE
from random import randint

HOLE_HEIGHT = 170
pygame.font.init()
FONT = pygame.font.SysFont('Arial', 50)


class Bird:
    def __init__(self, initial_pos):
        self.velocity = 0
        self.speed = 1
        self.score = 0
        self.rect = Rect(initial_pos, (50, 50))
        self.color = Color(10, 105, 33)

    def flap(self):
        self.velocity = -12

    def update_position(self):
        self.velocity += 1
        self.rect.top += self.velocity

    def get_score_srf(self) -> pygame.Surface:
        return FONT.render(str(self.score), True, Color(255, 255, 255))


class Obstacle:
    def __init__(self, tophole, bottomhole):
        self.color = Color(70, 105, 240)
        self.toprect = Rect((WIDTH, 0), (30, tophole))
        self.bottomrect = Rect((WIDTH, bottomhole), (30, HEIGHT - bottomhole))

    def update_pos(self, speed):
        self.bottomrect.left -= speed
        self.toprect.left -= speed

    def set_pos(self):
        self.toprect.move_ip(WIDTH, 0)
        self.bottomrect.move_ip(WIDTH, 0)

    def set_hole(self, top, bottom):
        self.toprect = Rect((WIDTH, 0), (30, top))
        self.bottomrect = Rect((WIDTH, bottom), (30, HEIGHT - bottom))

    def check_collision(self, rect):
        return self.toprect.colliderect(rect) or self.bottomrect.colliderect(rect)


pygame.init()
clock = time.Clock()

WIDTH, HEIGHT = 400, 500
REFRESH_RATE = 60

screen = display.set_mode((WIDTH, HEIGHT), depth=32)

b = Bird((WIDTH//5, 100))
top = randint(10, HEIGHT - HOLE_HEIGHT - 10)
o = Obstacle(top, top+HOLE_HEIGHT)

game_over = False
while not game_over:
    for event in pygame.event.get():
        if event.type == QUIT:
            pygame.quit()
            exit(0)
        elif event.type == KEYDOWN:
            if event.key == K_SPACE:
                b.flap()
    b.update_position()
    o.update_pos(b.speed)
    if o.toprect.left <= 0:
        b.score += 1
        o.set_pos()
        top = randint(10, HEIGHT - HOLE_HEIGHT - 10)
        o.set_hole(top, top + HOLE_HEIGHT)

    if o.check_collision(b.rect):
        game_over = True

    screen.fill(Color(0, 0, 0))

    pygame.draw.rect(screen, b.color, b.rect)
    pygame.draw.rect(screen, o.color, o.toprect)
    pygame.draw.rect(screen, o.color, o.bottomrect)
    screen.blit(b.get_score_srf(), (WIDTH - 100, HEIGHT - 100))
    display.update()
    clock.tick(REFRESH_RATE)
pygame.quit()
