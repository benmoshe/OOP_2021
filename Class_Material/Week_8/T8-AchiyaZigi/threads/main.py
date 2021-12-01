from threading import Thread
from concurrent import futures
import time


def io_func(sleep_time):
    print("start sleeping for", sleep_time, "seconds")
    time.sleep(sleep_time)
    print("end sleeping for", sleep_time, "seconds")
    return sleep_time


def heavy_func(n):
    # https://stackoverflow.com/questions/2068372/fastest-way-to-list-all-primes-below-n-in-python/3035188#3035188
    """ Returns  a list of primes < n """
    sieve = [True] * (n//2)
    for i in range(3, int(n**0.5)+1, 2):
        if sieve[i//2]:
            sieve[i*i//2::i] = [False] * ((n-i*i-1)//(2*i)+1)
    return [2] + [2*i+1 for i in range(1, n//2) if sieve[i]]


if __name__ == '__main__':
    # t0 = Thread(target=io_func, args=[1])
    # t1 = Thread(target=io_func, args=[1])

    # start_time = time.perf_counter()
    # threads: list[Thread] = []
    # for i in range(6):
    #     threads.append(Thread(target=io_func, args=[i]))
    #     threads[i].start()
    #     # threads[i].join()
    # for t in threads:
    #     t.join()
    # finish_time = time.perf_counter()
    # print(finish_time-start_time)

    # start_time = time.perf_counter()
    # with futures.ThreadPoolExecutor() as pool:
    #     res = pool.map(io_func, range(6))
    #     for r in res:
    #         print(r)
    #     pool.shutdown(wait=True)
    # finish_time = time.perf_counter()
    # print(finish_time - start_time)

    start_time = time.perf_counter()
    with futures.ThreadPoolExecutor() as pool:
        res = pool.map(heavy_func, [8000000, 100000000, 25000000])
        for r in res:
            print(len(r), end=' , ')
        print()
        pool.shutdown(wait=True)
    finish_time = time.perf_counter()
    print(finish_time - start_time)

    start_time = time.perf_counter()
    with futures.ProcessPoolExecutor() as pool:
        res = pool.map(heavy_func, [8000000, 100000000, 25000000])
        for r in res:
            print(len(r), end=' , ')
        print()
        pool.shutdown(wait=True)
    finish_time = time.perf_counter()
    print(finish_time - start_time)
