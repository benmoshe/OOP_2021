from abc import ABC, abstractclassmethod
import math


class Planet:
    def __init__(self, planet_name: str, pos: tuple[float, float, float]) -> None:
        self.name = planet_name
        self.pos = pos

    def __eq__(self, __o: object) -> bool:
        if type(__o) != type(self):
            return False
        return __o.name == self.name and __o.pos == self.pos

    def __sub__(self, other):
        return tuple([self.pos[i] - other.pos[i] for i in range(len(self.pos))])

    def __repr__(self) -> str:
        return str(self.__dict__)[1: -1]
        # return f'name:{self.name}, pos:{self.pos}'


p1 = Planet('earth', (4, 6, 1))
p2 = Planet('earth', (4, 6, 1))
print(p1 == ['hi'])
print(p1-p2)
print(p1)


class SolarSystem(ABC):
    @abstractclassmethod
    def add_planet(self, planet: Planet) -> bool: pass

    @abstractclassmethod
    def remove_planet(self, planet_name: str) -> bool: pass

    @abstractclassmethod
    def get_planet(self, planet_name: str) -> Planet: pass

    @abstractclassmethod
    def closest_planet(self, pos: tuple) -> Planet: pass


class OurSolarSystem(SolarSystem):
    def __init__(self):
        self.planets: dict[str, Planet] = dict()

    def add_planet(self, planet: Planet) -> bool:
        if planet.name in self.planets:
            return False
        self.planets[planet.name] = planet
        return True

    def remove_planet(self, planet_name: str) -> bool:
        if self.planets.pop(planet_name, None) == None:
            return False
        return True

    def get_planet(self, planet_name: str) -> Planet:
        return self.planets.get(planet_name)

    def closest_planet(self, pos: tuple) -> Planet:
        return min(self.planets.values(), key=lambda p: OurSolarSystem.d(pos, p.pos))

    def d(pos1: tuple, pos2: tuple) -> float:
        return math.sqrt(sum(math.pow(a-b, 2) for a, b in zip(pos1, pos2)))


sun_solar = OurSolarSystem()
sun_solar.add_planet(Planet('sun', (0, 0, 0)))
sun_solar.add_planet(Planet('earth', (163, 16663, 38.32)))
sun_solar.add_planet(Planet('noga', (2457, 2344, 427)))
sun_solar.add_planet(Planet('zodiak', (7241, 632, 72445)))

print(sun_solar.closest_planet((72, 73713, 743)))
print(list(zip(sun_solar.get_planet('earth').pos, sun_solar.get_planet('noga').pos)))
