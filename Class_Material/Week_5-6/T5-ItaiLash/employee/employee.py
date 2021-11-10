class Employee:
    emp_count = 0

    def __init__(self, id: str = None, first_name: str = None, last_name: str = None, salary: int = 0,
                 lang_list: list = [], **kwargs):
        Employee.emp_count += 1
        self.__emp_id = Employee.emp_count
        self.id = id
        self.f_name = first_name
        self.l_name = last_name
        self.salary = salary
        self.lang_list = lang_list.copy()

    def full_name(self):
        return f"{self.f_name} {self.l_name}"

    def display_count(self):
        print("Total Employee" + Employee.empCount)

    # def __dict__(self):
    #     return {"id": self.id, "first_name": self.f_name, "last_name": self.l_name, "salary": self.salary,
    #             "lang_list": self.lang_list}

    def __eq__(self, o: object) -> bool:
        if isinstance(o, Employee):
            return self.id == o.id
        return False

    def __str__(self):
        return f"Employee: id:{self.id}, name:{self.full_name()}, salary:{self.salary}, languages:{self.lang_list}"

