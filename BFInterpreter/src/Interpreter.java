import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {
	// TODO replace ArrayLists with Tape class that contains ArrayLists
	private ArrayList<String> programTape = new ArrayList<>();
	private ArrayList<Byte> tapeTape = new ArrayList<>();
	private int programPos = 0;
	private int tapePos = 0;

	public Interpreter(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(filename));
		String line = "";
		while (sc.hasNextLine())
			line += sc.nextLine();
		sc.close();
		for (char c : line.toCharArray())
			programTape.add(String.valueOf(c));
		tapeTape.add((byte) 0);
	}

	public boolean interpretNext(char nextChar) throws EndOfTapeException, PairlessBracketException {
		// return false on program ending
		Byte temp;
		switch (programTape.get(programPos)) {
		case "+":
			temp = tapeTape.get(tapePos);
			temp++;// wut why does it work like this ^.v this language is dumb
					// this is why we need bf
			tapeTape.set(tapePos, temp);
			break;
		case "-":
			temp = tapeTape.get(tapePos);
			temp--;// maybe this is because I'm coding at 5:30am
			tapeTape.set(tapePos, temp);
			break;
		case ">":
			tapePos++;
			if (tapeTape.size() == tapePos) {
				tapeTape.add((byte) 0);
			}
			break;
		case "<":
			if (tapePos== 0) {
				throw new EndOfTapeException();
			}
			tapePos--;
			break;
		case "[":
			if (tapeTape.get(tapePos) == 0) {
				findRight();
			}
			break;
		case "]":
			if (tapeTape.get(tapePos) != 0) {
				findLeft();
			}
			break;
		case ".":
			System.out.println((char) (tapeTape.get(tapePos).byteValue()));
			break;
		case ",":
			temp = tapeTape.get(tapePos);
			temp = (byte) nextChar;
			break;
		default:
			break;
		}/*
		System.out.println(programTape.get(programPos));
		System.out.println(tapePos);
		System.out.println(tapeTape);*/
		programPos++;
		return (programPos != programTape.size());
	}

	private void findRight() throws PairlessBracketException {
		while (!programTape.get(programPos).equals("]")) {
			programPos++;
			if (programPos == programTape.size())
				throw new PairlessBracketException();
			if (programTape.get(programPos).equals("[")) {
				findRight();
			}
		}
		programPos++;
		if (programPos == programTape.size())
			throw new PairlessBracketException();
	}

	private void findLeft() throws PairlessBracketException {
		while (!programTape.get(programPos).equals("[")) {//should probably do while but too tired to think about it
			if (programPos == 0)
				throw new PairlessBracketException();
			programPos--;
			if (programTape.get(programPos).equals("]")) {
				findLeft();
			}
		}
		if (programPos == 0)
			throw new PairlessBracketException();
		programPos--;
	}
}
