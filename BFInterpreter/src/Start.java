import java.io.FileNotFoundException;

public class Start {

	public static void main(String[] args) {
		Interpreter interpreter;
		try {
			interpreter = new Interpreter(args[0]);
			try {
				while (interpreter.interpretNext(' ')) {

				}
				System.out.println((char)0);
			} catch (EndOfTapeException e) {
				System.out.println("You fucked up and you went too far left");
				e.printStackTrace();
			} catch (PairlessBracketException e) {
				System.out.println("Pair your brackets scrub");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			System.out.println("Create a test.bf file to run");
			e1.printStackTrace();
		}

	}

}
