from pygame import mixer
mixer.init()
mixer.music.load("music1.ogg")
mixer.music.set_volume(0.5)
mixer.music.play()
while True:
    print("Press 'p' to pause, 'r' to resume")
    print("Press 'e' to end the program")
    enter = input("  ")
    if enter == 'p':
        mixer.music.pause()
    elif enter == 'r':
        mixer.music.unpause()
    elif enter == 'e':
        mixer.music.stop()
        break