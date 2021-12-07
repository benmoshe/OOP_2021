# Functions can return another function
from functools import reduce
import math
import time


def create_adder(x):
    def adder(y):
        return y+x
    return adder


my_adder = create_adder(15)
print(my_adder(5))

# Function can be passed to other functions


def seperate_with_line(printing_func):
    def seperator(text):  # This is called the 'wrapper'
        sep = '----------------'
        print(sep)
        printing_func(text)
    return seperator


def print_something(text):
    print(text)


print_with_sep = seperate_with_line(print_something)

print_with_sep('hello world')

# More efficiently


@seperate_with_line
def print_this_with_sep(text):
    print(text)


print_this_with_sep('bye world')

# Another example


def measure_time_decorator(execution_func):
    def wrapper(*args, **kwargs):
        begin = time.time()
        execution_func(*args, **kwargs)
        end = time.time()
        print(execution_func.__name__, 'took', end-begin, 'ms')
    return wrapper


@seperate_with_line  # seperate_with_line(measure_time(calc_factorial(num)))
@measure_time_decorator
def calc_factorial(num):
    print(math.factorial(num))


calc_factorial(10)

# With a reuturn value


def parentheses_decorator(returning_execution_func):
    def wrapper(*args, **kwargs):
        res = returning_execution_func(*args, **kwargs)
        return '('+str(res) + ')'
    return wrapper


@parentheses_decorator
def sum(a, b):
    return a+b


print(sum(5, 6))


@parentheses_decorator
def pass_func():
    return 'pass'


print(pass_func())

# Chaining decorators:


@parentheses_decorator
def inject_char(c: str, list):
    """
    in: $, [5,1,7,3]
    out: 5$1$7$3
    """
    return c.join(list)


def mult_decorator(mult_func):
    def wrapper(*args, **kwargs):
        """
        in: 5,1,7,3
        out: '5*1*7*3', 105
        """
        res = mult_func(*args, **kwargs)

        return inject_char('*', [str(x) for x in args]), res
    return wrapper


def add_decorator(add_func):
    def wrapper(*args, **kwargs):
        """
        in: 5,1,7,3
        out: '5+1+7+3', 16
        """
        res = add_func(*args, **kwargs)
        return inject_char('+', [str(x) for x in args]), res
    return wrapper


def equation_decorator(math_func):
    def wrapper(*args, **kwargs):
        """
        in: '5+1+7+3', 16
        out: '5+1+7+3 = 16'
        """
        left, right = math_func(*args, **kwargs)
        return left + ' = ' + str(right)
    return wrapper


@equation_decorator
@mult_decorator
def mult(*args):
    return reduce(lambda a, b: a*b, args)


@equation_decorator
@add_decorator
def add(*args):
    return reduce(lambda a, b: a+b, args)


print(mult(3, 6, 1))
print(add(3, 6, 1))
