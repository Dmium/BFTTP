from BFTape import BFTape


class BFInterpreter:
    def __init__(self, path):
        self.instruction_tape = BFTape()
        self.data_tape = BFTape()
        file = open(path + "index.bf", "r")
        data = file.read()
        for val in data:
            self.instruction_tape.write(val)
            self.instruction_tape.move_right()
        file.close()

    def interpret(self):
        dat = []
        for i in range(1, len(self.instruction_tape.data)):
            self.instruction_tape.move_left()
        for i in range(1, len(self.instruction_tape.data)):
            dat.append(self.instruction_tape.read())
            self.instruction_tape.move_right()
        print(dat)
        return dat
