
def singelton(class_):
    instances = {}

    def getInstace(*args, **kwargs):
        if class_ not in instances:
            instances[class_] = class_(*args, **kwargs)
        return instances[class_]
    return getInstace


@singelton
class Logger:
    def __init__(self):
        self.logs: dict[str, list] = dict()

    def log(self, *msgs):
        if(msgs):
            if(msgs[0] not in self.logs):
                self.logs[msgs[0]] = []
            self.logs[msgs[0]].append(' '.join(msgs[1:]))
            print('['+msgs[0]+'] '+self.logs[msgs[0]][-1])


log = Logger()
log.log('achiya zigler', 'math')
log2 = Logger()
log2.log('avi simhon', 'linear algebra')

print(log2.logs)

print(log == log2)
print(type(Logger), type(log))
