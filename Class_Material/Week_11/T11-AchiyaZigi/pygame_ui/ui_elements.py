import pygame
from pygame import *

font.init()
arial_font = font.SysFont('Arial', 15, bold=True)


class Button:
    """
    simple button, base for everything
    """

    default_size: tuple[int, int] = None

    def __init__(self, title: str, size=default_size, color=Color(155, 230, 250)) -> None:
        self.title = title
        self.size = size if size != None else Button.default_size
        self.color = color
        self.rect = Rect((0, 0), self.size)
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
    default_size: tuple[int, int] = Button.default_size

    def __init__(self, title: str, size=None, buttons: list[Button] = [], color=Color(155, 230, 250)) -> None:
        size = size if size != None else MenuItem.default_size
        super().__init__(title, size, color=color)
        max_w = max(buttons, key=lambda b: b.rect.width).rect.width if len(
            buttons) > 0 else 0
        sum_h = sum(b.rect.height for b in buttons)
        self.menu_rect = Rect((0, 0), (max_w, sum_h))
        self.show_menu = False

        self.buttons = buttons

        def toggle_menu():
            self.show_menu = not self.show_menu

        def show_only(me):
            def wraper():
                for button in self.buttons:
                    if me != button and type(button) == SubMenuItem:
                        sub_menu_item: SubMenuItem = button
                        sub_menu_item.show_menu = False
            return wraper
        for button in self.buttons:

            button.add_click_listener(show_only(button))
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
            # pygame.draw.rect(surface, self.color, self.menu_rect, width=5)
        else:
            for b in self.buttons:
                b.disabled = True


class SubMenuItem(MenuItem):
    """
    dropdown to the right
    """
    add_right_sign = True

    def render(self, surface: Surface, pos):
        Button.render(self, surface, pos)
        if SubMenuItem.add_right_sign:
            pygame.draw.polygon(surface, self.color,
                                (self.rect.topright, self.rect.bottomright, (self.rect.right+10, self.rect.centery)))
        if self.show_menu:
            self.menu_rect.topleft = self.rect.topright
            last_button_bottom = self.menu_rect.top
            for b in self.buttons:
                b.render(surface, (self.menu_rect.left, last_button_bottom))
                last_button_bottom = b.rect.bottom
                b.disabled = False
            # pygame.draw.rect(surface, self.color, self.menu_rect, width=5)
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

        def show_only(me):
            def wraper():
                for menu_item in self.menu_items:
                    if me != menu_item:
                        menu_item.show_menu = False
            return wraper
        for menu_item in self.menu_items:

            menu_item.add_click_listener(show_only(menu_item))

    def check(self):
        for m in self.menu_items:
            m.check()

    def render(self, surface, pos):
        self.rect.topleft = pos
        last_menu_pos = pos
        for m in self.menu_items:
            m.render(surface, last_menu_pos)
            last_menu_pos = m.rect.topright
