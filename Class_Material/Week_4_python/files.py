__author__ = "Simon Pikalov"

if __name__ == '__main__':

    file_name = "test1.txt"
    # creat a file and write somthing
    f = open(file_name, "w")
    f.write("this is a test")
    f.close()

    """ One more cool way :) """
    file_name = "test2.txt"
    f = open(file_name, "w")
    print("print test this is a test", file=f)
    f.close()

    """This block will throw an Error due to "file already exists" exception...
    """
    # f = open(file_name, "x")
    # f.write("this is a test"
    # f.close()

    """Same thing but with try and except"""

    try:
        f = open(file_name, "x")
        f.write("this is a test")
    except FileExistsError as e:
        print(e)
    finally:
        f.close()

"""
Same as the the code above 
"""
try:
    with open(file_name, "x") as f:
        f.write("\nwith open\n")
except IOError as e:
    print(str(e)+"  you can add your error explenation here...")
