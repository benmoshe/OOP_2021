import unittest
from Class_Material.Week_12.T12_AchiyaZigi.exams.oop2021a.oop2021 import *


class TestMedicPayroll(unittest.TestCase):
    def test_all_month_salary(self):
        m = MedicPayroll()
        m.add_medic(Nurse('Noa', 'Levi', '1223', experience=2))
        m.add_medic(Doctor('Michael', 'Wag', '1111', experience=2))
        m.add_medic(Doctor('Yoav', 'Cohen', '1331', experience=1))
        m.add_medic(Nurse('Avi', 'Segal', '2231', experience=6))
        self.assertEqual(m.get_all_month_salary(), 49400)

    def test_most_expensive_medic(self):
        m = MedicPayroll()
        m.add_medic(Nurse('Noa', 'Levi', '1223', experience=2))
        doc_michael = Doctor('Michael', 'Wag', '1111', experience=2)
        m.add_medic(doc_michael)
        m.add_medic(Doctor('Yoav', 'Cohen', '1331', experience=1))
        m.add_medic(Nurse('Avi', 'Segal', '2231', experience=6))
        self.assertEqual(m.get_most_expensive_medic(), doc_michael)


if __name__ == '__main__':
    unittest.main()
