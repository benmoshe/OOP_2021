

class Logger():
    _instance = None

    def __new__(cls, *args, **kwargs):
        if not isinstance(cls._instance, cls):
            cls._instance = object.__new__(cls, *args, **kwargs)
            cls._instance.logs = dict()
            cls._instance.__init__(*args, **kwargs)
        return cls._instance

    def log(self, *msgs):
        if(len(msgs)):
            if(msgs[0] not in self.logs):
                self.logs[msgs[0]] = []
            print('['+msgs[0]+']', end=' ')
            if(len(msgs) > 1):
                self.logs[msgs[0]].append(' '.join(msgs[1:]))
                print(self.logs[msgs[0]][-1])
            else:
                print()


log2 = Logger()
log2.log('avi simhon', 'linear algebra')
log = Logger()
log.log('achiya zigler')

print(log2.logs)
print(log.logs)

print(log == log2)
print(type(Logger), type(log))
