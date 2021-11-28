import sqlite3
con = sqlite3.connect('example.db')
cur = con.cursor()

# Create table
#cur.execute('''CREATE TABLE stocks
#               (date text, trans text, symbol text, qty real, price real)''')

# Insert a row of data
cur.execute("INSERT INTO stocks VALUES ('2006-01-05','BUY','RHAT',100,35.14)")
cur.execute("INSERT INTO stocks VALUES ('2006-03-28', 'BUY', 'IBM', 1000, 45.0)")
cur.execute("INSERT INTO stocks VALUES('2006-04-06', 'SELL', 'IBM', 500, 53.0)")
cur.execute("INSERT INTO stocks VALUES('2006-04-05', 'BUY', 'MSFT', 1000, 72.0)")
# Save (commit) the changes
con.commit()

# We can also close the connection if we are done with it.
# Just be sure any changes have been committed or they will be lost.
con.close()
