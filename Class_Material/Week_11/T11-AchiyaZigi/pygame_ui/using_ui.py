import pygame
from pygame import *
from ui_elements import *

# pygame initialization

pygame.init()

clock = pygame.time.Clock()

screen = display.set_mode((800, 600), depth=32)

# ==========the ui tree========
#                             m
#            file--------------------------------edit
#   save---------------load                    edit_node
#  pdf-jpg    pdf-------jpg---------more
#                              option1-option2


# save buttons
save_pdf = Button('pdf', (70, 50))
save_pdf.add_click_listener(lambda: print('saving as pdf!'))
save_jpg = Button('jpg', (70, 50))
save_jpg.add_click_listener(lambda: print('saving as jpg!'))

# save menu
save = SubMenuItem('save', (70, 50), [
                   save_jpg, save_pdf], color=Color(152, 122, 19))

# load more options buttons
load_moreoptions_option1 = Button('option 1', (70, 50))
load_moreoptions_option2 = Button('option 2', (70, 50))

# load more options menu
load_moreoptions = SubMenuItem('more...', (70, 50), [
                               load_moreoptions_option1, load_moreoptions_option2], color=Color(152, 122, 19))

# load buttons
load_pdf = Button('load pdf', (70, 50))
load_pdf.add_click_listener(lambda: print('loading from pdf file'))
load_jpg = Button('load jpg', (70, 50))
load_jpg.add_click_listener(lambda: print('loading from jpg file'))

# load menu
load = SubMenuItem('load', (70, 50), [
                   load_jpg, load_pdf, load_moreoptions], color=Color(152, 122, 19))

# file menu
file = MenuItem('file', (70, 20), [save, load], Color(62, 200, 30))

# edit buttons
edit_node = Button('edit node', (70, 50))
edit_node.add_click_listener(lambda: print('editing node!'))

# edit menu
edit = MenuItem('edit', (70, 20), [edit_node], Color(62, 200, 30))

# menubar
m = MenuBar([file, edit])


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
