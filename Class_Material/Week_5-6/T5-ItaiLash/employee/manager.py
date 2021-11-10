from programmer import Programmer


class Manager(Programmer):

    def __init__(self, id: str, first_name: str, last_name: str, salary: int, lang_list: list, prog_dic: dict):
        super().__init__(id, first_name, last_name, salary, lang_list)
        self.prog_dic = prog_dic.copy()

    def get_employees(self) -> list:
        prog_list = []
        for p in self.prog_dic.values():
            prog_list.append(p.full_name())
        return prog_list

    def __str__(self):
        return f"Programmer: id:{self.id}, name:{self.full_name()}, salary:{self.salary}, languages:{self.lang_list}," \
               f" employees:{self.get_employees()}"

    def __repr__(self):
        return f"Programmer: id:{self.id}, name:{self.full_name()}, salary:{self.salary}, languages:{self.lang_list}," \
               f" employees:{self.get_employees()}"

    # def __dict__(self):
    #     dic = super().__dict__()
    #     dic["employees"] = self.get_employees()
    #     return dic


if __name__ == '__main__':
    p1 = Programmer("316485176", "Itai", "Lashover", 15000, list(("Java", "Python", "C", "C++")))
    p2 = Programmer("11111111", "Israel", "Israeli", 10000, ["Java", "C"])
    m1 = Manager("2222222", "A", "B", 30000, ["Java"], {p1.id: p1, p2.id: p2})
    print(m1.get_employees())
    print(m1)
