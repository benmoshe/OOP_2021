
import unittest



class GraphInterface:
    def has_node(self, key): pass
    def get_edge(self, src, dest): pass
    def add_node(self, key): pass
    def connect(self, src=None, dest=None, w=None, edge=None): pass
    def get_v(self): pass
    def get_e(self, node_id): pass
    def remove_node(self, key): pass
    def remove_edge(self, src, dest): pass
    def node_size(self): pass
    def edge_size(self): pass
    def get_mc(self): pass


class GraphAlgoInterface:
    def get_graph(self) -> GraphInterface:
        pass

    def shortest_path(self, id1: int, id2: int) -> tuple[float, list]:
        pass

    def is_component(self) -> bool:
        pass


# ===========q4==================

class Medic:
    def __init__(self, name: str, family_name: str, id: str, experience: int):
        self.name: str = name
        self.family_name: str = family_name
        self.id: str = id
        self.experience: int = experience

    def get_salary(self):
        raise NotImplementedError

    def __str__(self):
        return f'name: {self.name} , id: {self.id}'


class MedicPayroll:
    def __init__(self) -> None:
        self.medics: dict[int, Medic] = {}

    def add_medic(self, medic: Medic):
        self.medics[medic.id] = medic

    def get_medic(self, id: str):
        return self.medics.get(id)

    def get_all_month_salary(self):
        sum = 0
        for med in self.medics.values():
            sum += med.get_salary()
        return sum

    def get_most_expensive_medic(self):
        return max(self.medics.values(), key=lambda m: m.get_salary())


class Nurse(Medic):
    def get_salary(self):
        return 8_000 + 800 * self.experience


class Doctor(Medic):
    def get_salary(self):
        return 12_000 + 1_000 * self.experience

# nurse = Nurse('Noa', 'Levi', '1223', experience=2)
# doc = Doctor('Michael', 'Wag', '1111', experience=2)
# m = MedicPayroll()
# m.add_medic(nurse)
# m.add_medic(doc)
# print(nurse.get_salary())
# print(doc.get_salary())
# print(m.get_all_month_salary())
# print(m.get_most_expensive_medic())

