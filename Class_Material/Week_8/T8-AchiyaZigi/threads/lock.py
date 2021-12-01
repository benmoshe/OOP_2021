from threading import Thread, Lock

x = 0
lock = Lock()


def inc_dummy():
    global x
    for i in range(1000000):
        lock.acquire()
        x += 1
        lock.release()


t0 = Thread(target=inc_dummy)
t1 = Thread(target=inc_dummy)
t0.start()
t1.start()
t0.join()
t1.join()
print(x)
