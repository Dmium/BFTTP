import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Interpreter {
	private Process proc;
	private BufferedReader br;

	public Interpreter(String filename) throws IOException {
		proc = Runtime.getRuntime().exec("java -classpath BFInt Start BFInt" + filename);
		br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
	}

	public String readToEnd() throws IOException {
		String sb = " ";
		while (sb.toCharArray()[sb.length() - 1] != (char) 0) {
			while (br.ready()) {
				for (char c : br.readLine().toCharArray()) {
					sb += c;
					//System.out.println(sb);
				}
			}
		}
		// sb += "</p>";
		System.out.println(sb);
		return sb;
	}
}
