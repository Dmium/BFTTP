from BFTape import BFTape
from BFInterpretReturn import BFInterpretReturn
from BFInterpreterReturnType import BFInterpreterReturnType
import os


class BFInterpreter:
    def __init__(self, path):
        self.instruction_tape = BFTape()
        self.data_tape = BFTape()
        self.instruction_length = 0
        if os.path.exists(path + "index.bf"):
            file = open(path + "index.bf", "r")
            data = file.read()
            for val in data:
                self.instruction_tape.write(val)
                self.instruction_tape.move_right()
                self.instruction_length += 1
            for i in range(0, self.instruction_length):
                self.instruction_tape.move_left()
            file.close()

# TODO Interpret until , or , here.
    def interpret(self, input_list):
        while self.instruction_tape.tape_pointer < self.instruction_length:
            print(self.instruction_tape.data)
            print(self.instruction_tape.tape_pointer)
            if self.instruction_tape.read() == "+":
                self.inc()
            elif self.instruction_tape.read() == "-":
                self.dec()
            elif self.instruction_tape.read() == ">":
                self.right()
            elif self.instruction_tape.read() == "<":
                self.left()
            elif self.instruction_tape.read() == "[":
                self.left_bracket()
            elif self.instruction_tape.read() == "]":
                self.right_bracket()
            elif self.instruction_tape.read() == ".":
                self.instruction_tape.move_right()
                return BFInterpretReturn(BFInterpreterReturnType.OUTPUT, self.data_tape.read())
            elif self.instruction_tape.read() == ",":
                self.data_tape.write(input_list[0])
                del(input_list[0])
            self.instruction_tape.move_right()
        return BFInterpretReturn(BFInterpreterReturnType.LOGIC, 0)

    def right(self):
        self.data_tape.move_right()

    def left(self):
        self.data_tape.move_left()

    def inc(self):
        self.data_tape.write(self.data_tape.read() + 1)

    def dec(self):
        self.data_tape.write(self.data_tape.read() - 1)

    def right_bracket(self):
        if self.data_tape.read() != 0:
            self.find_left()

    def left_bracket(self):
        if self.data_tape.read() == 0:
            self.find_right()

    def find_right(self):
        while self.instruction_tape.read() != "]":
            self.instruction_tape.move_right()
            if self.instruction_tape.read() == "[":
                self.find_right()

    def find_left(self):
        while self.instruction_tape.read() != "[":
            self.instruction_tape.move_left()
            if self.instruction_tape.read() == "]":
                self.find_left()
