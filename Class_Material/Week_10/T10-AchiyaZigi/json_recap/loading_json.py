import json
import os
from types import SimpleNamespace
import pygame
from pygame import Color, display, gfxdraw
from pygame.constants import RESIZABLE

WIDTH, HEIGHT = 1080, 720

pygame.init()
screen = display.set_mode((WIDTH, HEIGHT), depth=32, flags=RESIZABLE)
clock = pygame.time.Clock()
pygame.font.init()

FONT = pygame.font.SysFont('Arial', 20, bold=True)


def scale(data, min_screen, max_screen, min_data, max_data):
    return ((data - min_data) / (max_data-min_data)) * (max_screen - min_screen) + min_screen


root_path = os.path.dirname(os.path.abspath(__file__))

with open(root_path + '/graph_triangle.json', 'r') as file:
    graph = json.load(
        file, object_hook=lambda json_dict: SimpleNamespace(**json_dict))


min_x = min(list(graph.nodes), key=lambda n: n.pos.x).pos.x
min_y = min(list(graph.nodes), key=lambda n: n.pos.y).pos.y
max_x = max(list(graph.nodes), key=lambda n: n.pos.x).pos.x
max_y = max(list(graph.nodes), key=lambda n: n.pos.y).pos.y


def my_scale(data, x=False, y=False):
    if x:
        return scale(data, 50, screen.get_width() - 50, min_x, max_x)
    if y:
        return scale(data, 50, screen.get_height()-50, min_y, max_y)


print(graph.nodes)

radius = 15
i = True
while(True):
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            exit(0)

    screen.fill(Color(0, 0, 0))

    for n in graph.nodes:
        x = my_scale(n.pos.x, x=True)
        y = my_scale(n.pos.y, y=True)
        gfxdraw.filled_circle(screen, int(x), int(y),
                              radius, Color(64, 80, 174))
        gfxdraw.aacircle(screen, int(x), int(y),
                         radius, Color(64, 80, 174))
        id_srf = FONT.render(str(n.id), True, Color(255, 255, 255))
        rect = id_srf.get_rect(center=(x, y))
        screen.blit(id_srf, rect)

    for e in graph.edges:
        src = next(n for n in graph.nodes if n.id == e.src)
        dest = next(n for n in graph.nodes if n.id == e.dest)
        src_x = my_scale(src.pos.x, x=True)
        src_y = my_scale(src.pos.y, y=True)
        dest_x = my_scale(dest.pos.x, x=True)
        dest_y = my_scale(dest.pos.y, y=True)
        pygame.draw.line(screen, Color(61, 72, 126),
                         (src_x, src_y), (dest_x, dest_y))

    display.update()
    clock.tick(60)
