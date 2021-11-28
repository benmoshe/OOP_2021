import sqlite3
con = sqlite3.connect('example.db')
cur = con.cursor()
for row in cur.execute('SELECT * FROM stocks ORDER BY price'):
        print(row)