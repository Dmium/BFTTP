class BFTape():
    def __init__(self):
        self.data = [0]
        self.tape_pointer = 0

    def move_right(self):
        self.tape_pointer += 1
        if self.tape_pointer == len(self.data):
            self.data.append(0)

    def move_left(self):
        if self.tape_pointer > 0:
            self.tape_pointer -= 1

    def read(self):
        return self.data[self.tape_pointer]

    def write(self, val):
        self.data[self.tape_pointer] = val
