from employee import Employee


class Programmer(Employee):

    def __init__(self, id: str, first_name: str, last_name: str, salary: int, lang_list: list):
        super().__init__(id, first_name, last_name, salary, lang_list)

    def __str__(self):
        return f"Programmer: id:{self.id}, name:{self.full_name()}, salary:{self.salary}, languages:{self.lang_list}"

    def __repr__(self):
        return f"Programmer: id:{self.id}, name:{self.full_name()}, salary:{self.salary}, languages:{self.lang_list}"

    def __lt__(self, other):
        return self.salary > other.salary

    def __add__(self, other: int):
        self.salary += other


if __name__ == '__main__':
    p1 = Programmer("22222222", "Avraham", "Avi", 15000, list(("Java", "Python", "C", "C++")))
    p2 = Programmer("11111111", "Israel", "Israeli", 10000, ["Java", "C"])
    print(p1)
    print(p2)
    p_list = [p2, p1]
    print(p_list)
    p_list.sort()
    print(p_list)
    p1+1000
    print(p1.__dict__)
