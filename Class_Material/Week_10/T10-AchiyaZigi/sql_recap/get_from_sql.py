import mysql.connector
import os

current_path = os.path.dirname(os.path.abspath(__file__))

passord = open(current_path + '/.env', 'r').read().split('SQL_PASSORD=')[1]


def print_record(record: list[dict], slots=None):
    if(slots == None):
        slots = [9]*len(record[0])
    for i, key in enumerate(record[0].keys()):
        print(f'{key:{slots[i]}.{slots[i]}}', end=' ')
    print()

    for row in record:
        for i, val in enumerate(row.values()):
            print(f'{str(val):{slots[i]}.{slots[i]}}', end=' ')
        print()


with mysql.connector.connect(
        host='localhost', database='northwind', user='root', password=passord) as sqlref:

    if(sqlref.is_connected()):
        cursor: mysql.connector.connection.CursorBase = sqlref.cursor(
            dictionary=True)
        cursor.execute('select database();')
        record = cursor.fetchone()

        cursor.execute(
            """select o.CustomerID, od.UnitPrice, od.Quantity
                from Orders as o join `Order Details` as od
                on(o.OrderID = od.OrderID)
                order by od.UnitPrice desc
                limit 50;""")
        res = cursor.fetchall()
        print_record(res, slots=[15, 10, 10])

        cursor.close()
        sqlref.close()
