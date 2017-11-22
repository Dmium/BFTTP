from flask import Flask
from BFInterpreter import BFInterpreter

app = Flask(__name__)


@app.route('/', defaults={'path': ''})
@app.route('/<path:path>')
def catch_all(path):
    return str(BFInterpreter(path).interpret())


if __name__ == '__main__':
    app.run()
