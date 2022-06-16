import pygame
from pygame import *
from ui_elements import *

# pygame initialization

pygame.init()

clock = pygame.time.Clock()

screen = display.set_mode((800, 600), depth=32)
Button.default_size = (100, 50)
MenuItem.default_size = (100, 20)
# ==========the ui tree========
#                             m
#            file--------------------------------edit
#   save---------------load                    edit_node
#  pdf-jpg    pdf-------jpg---------more
#                              option1-option2


def add_button_to_menu(menu: MenuBar, button: Button):
    def wrapper():
        menu.menu_items.append(button)
    return wrapper


# save buttons
save_pdf = Button('pdf')
save_pdf.add_click_listener(lambda: print('saving as pdf!'))
save_jpg = Button('jpg')
save_jpg.add_click_listener(lambda: print('saving as jpg!'))

# save menu
save = SubMenuItem(
    'save', buttons=[save_jpg, save_pdf], color=Color(152, 122, 19))

# load more options buttons
load_moreoptions_option1 = Button('option 1')
load_moreoptions_option2 = Button('option 2')

# load more options menu
load_moreoptions = SubMenuItem('more...', buttons=[
                               load_moreoptions_option1, load_moreoptions_option2], color=Color(152, 122, 19))

# load buttons
load_pdf = Button('load pdf')
load_pdf.add_click_listener(lambda: print('loading from pdf file'))
load_jpg = Button('load jpg')
load_jpg.add_click_listener(lambda: print('loading from jpg file'))

# load menu
load = SubMenuItem('load', buttons=[
                   load_jpg, load_pdf, load_moreoptions], color=Color(152, 122, 19))

# file menu
file = MenuItem('file', buttons=[
                save, load], color=Color(62, 200, 30))

# info buttons
info_author = Button('about the author')
info_author.add_click_listener(lambda: print(
    'Achiyazigi!'))
info_program = Button('about the program')
info_program.add_click_listener(lambda: print(
    'pygame ui is fun!'))

# info sub menu
info = SubMenuItem(
    'info', buttons=[info_author, info_program], color=Color(152, 122, 19))

# help buttons
search = Button('search')
search.add_click_listener(lambda: print(
    'searching for help never helped me...'))

sos = Button('sos')
sos.add_click_listener(lambda: print(
    'come onnnn im stuck here'))

# help menu
help = MenuItem('help', buttons=[
                sos, info, search], color=Color(62, 200, 30))

# edit buttons
add_menu_item = Button('add menu item')

# edit menu
edit = MenuItem('edit', buttons=[
                add_menu_item], color=Color(62, 200, 30))

# menubar
m = MenuBar([file, edit, help])
add_menu_item.add_click_listener(add_button_to_menu(m, MenuItem('added!')))


while True:

    for event in pygame.event.get():
        if event.type == QUIT:
            quit()
            exit(0)
        elif event.type == MOUSEBUTTONDOWN:
            # check menubar clicks
            m.check()

    screen.fill(Color(0, 0, 0))

    # render what you need

    # render menubar
    m.render(screen, (0, 0))

    display.update()
    clock.tick(60)
