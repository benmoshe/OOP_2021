from abc import ABC, abstractclassmethod

# product abstruct


class Button(ABC):

    @abstractclassmethod
    def on_click(func):
        pass

    @abstractclassmethod
    def render():
        pass

# product 1


class AndroidButton(Button):
    def on_click(self, func):
        print('Android button: ', end=' ')
        func()

    def render(self):
        print("""
        _________
       || android|
        ---------
        """)

# product 2


class IosButton(Button):
    def on_click(self, func):
        print('ios button: ', end=' ')
        func()

    def render(self):
        print("""
        _________
       || ios    |
        ---------
        """)


# factory abstruct
class ElementsFactory(ABC):
    @abstractclassmethod
    def create_button(self) -> Button:
        pass

    def render(self):
        def print_clicked():
            print('clicked!')

        b = self.create_button()
        b.on_click(print_clicked)
        b.render()

# factory 1


class IosElement(ElementsFactory):
    def create_button(self) -> Button:
        return IosButton()

# factory 2


class AndroidElement(ElementsFactory):
    def create_button(self) -> Button:
        return AndroidButton()


# client code:
print('enter os:')
os = input()
elements: ElementsFactory = None
if(os == 'android'):
    elements = AndroidElement()
elif(os == 'ios'):
    elements = IosElement()
else:
    raise Exception('didnt recognize os type')

elements.render()
