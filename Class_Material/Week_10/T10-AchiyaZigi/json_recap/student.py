import json
import os


class Student:
    def __init__(self, id, first_name, last_name, year, address, future_courses, grades) -> None:
        self.id = id
        self.first_name = first_name
        self.last_name = last_name
        self.address = address
        self.year = year
        self.future_courses = future_courses
        self.grades = grades

    def avg(self):
        return sum(self.grades.values()) / len(self.grades)

    def __repr__(self):
        return str(self.id)+'--->' + self.grades.__repr__()+'=>' + str(self.avg())


root_path = os.path.dirname(os.path.abspath(__file__))

with open(root_path+'/students.json', 'r') as file:
    list_of_stud_dict = json.load(file)['students']
    list_of_stud = [Student(**s) for s in list_of_stud_dict]


for s in list_of_stud:
    print(s)
