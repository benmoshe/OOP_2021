# adaptee
class EuSocket:
    def voltage(self):
        return 220

    def ground(self):
        return 0

    def live(self):
        return 1

    def neutral(self):
        return -1

# target


class UsaSocket:
    def voltage(self): pass

    def ground(self): pass

# adapter


class PowerAdapter(UsaSocket):
    def __init__(self, adaptee) -> None:
        self._adaptee = adaptee

    def voltage(self):
        return 110

    def ground(self):
        return 0

    def live(self):
        return self._adaptee.live()

    def neutral(self):
        return self._adaptee.neutral()


# client
class Kettle:
    def __init__(self, power) -> None:
        self.power = power

    def boil(self):
        if self.power.voltage() > 110:
            print("Kettle on fire!")
        else:
            if self.power.live() == 1 and self.power.neutral() == -1:
                print("Coffee time!")
            else:
                print("No power.")


print('no adapter:')
kettle_with_no_adapter = Kettle(EuSocket())
kettle_with_no_adapter.boil()
print('with adapter:')
kettle_with_adapter = Kettle(PowerAdapter(EuSocket()))
kettle_with_adapter.boil()


# same concept with multi inheritance:

class Target:
    """
    The Target defines the domain-specific interface used by the client code.
    """

    def request(self) -> str:
        return "Target: The default target's behavior."


class Adaptee:
    """
    The Adaptee contains some useful behavior, but its interface is incompatible
    with the existing client code. The Adaptee needs some adaptation before the
    client code can use it.
    """

    def specific_request(self) -> str:
        return ".eetpadA eht fo roivaheb laicepS"


class Adapter(Target, Adaptee):
    """
    The Adapter makes the Adaptee's interface compatible with the Target's
    interface via multiple inheritance.
    """

    def request(self) -> str:
        return f"Adapter: (TRANSLATED) {self.specific_request()[::-1]}"


def client_code(target: "Target") -> None:
    """
    The client code supports all classes that follow the Target interface.
    """

    print(target.request(), end="")


if __name__ == "__main__":
    print("Client: I can work just fine with the Target objects:")
    target = Target()
    client_code(target)
    print("\n")

    adaptee = Adaptee()
    print("Client: The Adaptee class has a weird interface. "
          "See, I don't understand it:")
    print(f"Adaptee: {adaptee.specific_request()}", end="\n\n")

    print("Client: But I can work with it via the Adapter:")
    adapter = Adapter()
    client_code(adapter)
    print()
