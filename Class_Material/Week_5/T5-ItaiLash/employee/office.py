from employee import Employee
from programmer import Programmer
from manager import Manager
import json


class Office:

    def __init__(self):
        self.employees = {}

    def add_employee(self, emp: Employee):
        self.employees[emp.id] = emp

    def __add__(self, emp: Employee):
        self.employees[emp.id] = emp
        return self

    def __sub__(self, emp: Employee):
        if self.employees.get(emp.id) is None:
            raise Exception("ERR")
        del self.employees[emp.id]
        return self

    def __str__(self):
        return self.employees.values().__str__()

    def __repr__(self):
        return self.employees.values().__repr__()

    def highest_paid(self):
        return max(self, key=lambda x: x.salary)

    def __iter__(self):
        return self.employees.values().__iter__()

    def save_to_file(self, file_name: str) -> None:
        try:
            with open(file_name, "w") as f:
                json.dump(self.employees, default=lambda x: x.__dict__, indent=4, fp=f)
        except IOError as e:
            print(e)

    def init_from_file(self, file_name: str) -> None:
        new_employees_dict = {}
        with open(file_name, "r") as f:
            dict_emp = json.load(f)
            for k, v in dict_emp.items():
                emp = Employee( v)
                new_employees_dict[k] = emp
        self.employees = new_employees_dict

    def __str__(self):
        s = ""
        for emp in self.employees.values():
            s += emp.__str__() + '\n'
        return s


if __name__ == '__main__':
    office = Office()
    office.init_from_file("office.txt")
    print(office)
    # office = Office()
    # p1 = Programmer("33333333", "Avi", "Avraham,", 15000, list(("Java", "Python", "C", "C++")))
    # p2 = Programmer("11111111", "Israel", "Israeli", 10000, ["Java", "C"])
    # m1 = Manager("2222222", "A", "B", 30000, ["Java"], {p1.id: p1, p2.id: p2})
    # office + m1 + p1 + p2
    # print(office)
    #
    # office.save_to_file("office.txt")
    # print(office)
    # office - p1
    # print(office)
    # print(office.highest_paid())
    # print('--------------------------')
    # office + Manager("3333333", "C", "D", 30000, [], {})
    # print(office)
    # office = sorted(office.employees.values(), key=lambda x: (x.salary, x.full_name()))
    # print(office)
