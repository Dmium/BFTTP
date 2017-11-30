from flask import Flask
from BFInterpretReturn import  BFInterpretReturn
from BFInterpreter import BFInterpreter
from BFInterpreterReturnType import BFInterpreterReturnType

app = Flask(__name__)


@app.route('/', defaults={'path': ''})
@app.route('/<path:path>')
def catch_all(path):
    input_list = []
    output = ""
    cval = BFInterpretReturn(BFInterpreterReturnType.OUTPUT, "")
    interpreter = BFInterpreter(path)
    while cval.return_type != BFInterpreterReturnType.LOGIC:
        cval = interpreter.interpret(input_list)
        if cval.return_type == BFInterpreterReturnType.OUTPUT:
            output += str(cval.value)
    return output


if __name__ == '__main__':
    app.run()
