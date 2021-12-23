import pygame
from pygame import *

font.init()
arial_font = font.SysFont('Arial', 15, bold=True)


class Button:
    """
    simple button, base for everything
    """

    def __init__(self, title: str, size: tuple[int, int], color=Color(155, 230, 250)) -> None:
        self.title = title
        self.size = size
        self.color = color
        self.rect = Rect((0, 0), size)
        self.on_click = []
        self.show = True
        self.disabled = False

    def add_click_listener(self, func):
        self.on_click.append(func)

    def render(self, surface: Surface, pos):
        if(not self.show):
            return
        self.rect.topleft = pos

        title_srf = arial_font.render(self.title, True, Color(70, 50, 111))
        title_rect = title_srf.get_rect(center=self.rect.center)
        pygame.draw.rect(surface, self.color, self.rect)
        pygame.draw.rect(surface, Color(50, 50, 50), self.rect, width=5)
        surface.blit(title_srf, title_rect)

    def check(self):
        if self.on_click != [] and not self.disabled:
            mouse_pos = pygame.mouse.get_pos()
            if self.rect.collidepoint(mouse_pos):
                clicked, _, _ = pygame.mouse.get_pressed()
                if clicked:
                    for f in self.on_click:
                        f()


class MenuItem(Button):
    """
    drop down to bottom side
    """

    def __init__(self, title: str, size, buttons: list[Button], color=Color(155, 230, 250)) -> None:
        super().__init__(title, size, color=color)
        max_w = max(buttons, key=lambda b: b.rect.width).rect.width
        sum_h = sum(b.rect.height for b in buttons)
        self.menu_rect = Rect((0, 0), (max_w, sum_h))
        self.show_menu = False

        self.buttons = buttons

        def toggle_menu():
            self.show_menu = not self.show_menu
        self.add_click_listener(toggle_menu)

    def check(self):
        super().check()
        if self.show_menu:
            for b in self.buttons:
                b.check()

    def render(self, surface: Surface, pos):
        super().render(surface, pos)
        if self.show_menu:
            self.menu_rect.topleft = self.rect.bottomleft
            last_button_rect = self.rect
            for b in self.buttons:
                b.render(surface, (self.menu_rect.left, last_button_rect.bottom))
                last_button_rect = b.rect
                b.disabled = False
            pygame.draw.rect(surface, self.color, self.menu_rect, width=5)
        else:
            for b in self.buttons:
                b.disabled = True


class SubMenuItem(MenuItem):
    """
    dropdown to the right
    """

    def render(self, surface: Surface, pos):
        Button.render(self, surface, pos)
        if self.show_menu:
            self.menu_rect.topleft = self.rect.topright
            last_button_bottom = self.menu_rect.top
            for b in self.buttons:
                b.render(surface, (self.menu_rect.left, last_button_bottom))
                last_button_bottom = b.rect.bottom
                b.disabled = False
            pygame.draw.rect(surface, self.color, self.menu_rect, width=5)
        else:
            for b in self.buttons:
                b.disabled = True


class MenuBar:
    """
    container for menu items
    """

    def __init__(self, menu_items: list[MenuItem]):
        self.menu_items = menu_items
        max_h = max(m.rect.height for m in menu_items)
        sum_w = sum(m.rect.width for m in menu_items)
        self.rect = Rect((0, 0), (sum_w, max_h))

    def check(self):
        for m in self.menu_items:
            m.check()

    def render(self, surface, pos):
        self.rect.topleft = pos
        last_menu_pos = pos
        for m in self.menu_items:
            m.render(surface, last_menu_pos)
            last_menu_pos = m.rect.topright
