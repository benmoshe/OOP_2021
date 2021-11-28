# motivated from: https://www.geeksforgeeks.org/python-lambda-anonymous-functions-filter-map-reduce/
x = "test string"
(lambda x: print(x))(x)

def square(y):
    return y * y

lambda_square = lambda y: y * y

print(square(4))
print(lambda_square(4))

max1 = lambda a, b: a if (a > b) else b
print(max1(1, 2))

List = [[2, 3, 4,1,0], [1, 4, 16, 64], [14, 6, 9, 12]]
sortList = lambda x: (sorted(i) for i in x)
secondLargest = lambda x, f: [y[len(y) - 2] for y in f(x)]
res = secondLargest(List, sortList)
print(res)

ages = [13, 90, 17, 59, 21, 60, 5]
adults = list(filter(lambda age: age > 18, ages))
print(adults)

li = [5, 7, 22, 97, 54, 62, 77, 23, 73, 61]
final_list = list(map(lambda x: x * 2, li))
print(final_list)

from functools import reduce
li = [1, 2, 5, 10, 20, 50]
sum = reduce((lambda x, y: x + y), li)
# Here the results of previous two elements are added to the next element and
# this goes on till the end of the list like (((((1+2)+5)+10)+20)+50).
print (sum)