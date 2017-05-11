import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientListener {

	public static void main(String argv[]) throws Exception {
		ArrayList<String> clientRequest;
		String[] requestParse;
		Interpreter interpreter;
		ServerSocket welcomeSocket = new ServerSocket(8080);
		String outputHTML;

		while (true) {
			clientRequest = new ArrayList<>();
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			BufferedWriter outToClient = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
			while (inFromClient.ready())
				clientRequest.add(inFromClient.readLine());
			System.out.println(clientRequest);
			requestParse = clientRequest.get(0).split(" ");
			System.out.println(requestParse[0]);
			if (requestParse[0].equals("GET")){
				System.out.println("^.v");
				interpreter = new Interpreter(requestParse[1]+ "index.bf");
				Thread.sleep(100);
				outputHTML = interpreter.readToEnd();
				System.out.println(outputHTML);
				//outputHTML = "cake";
				outToClient.write("HTTP/1.1 200 OK");
				outToClient.newLine();
				outToClient.newLine();
				outToClient.write(outputHTML);
				outToClient.newLine();
				outToClient.flush();
			}
			connectionSocket.close();
		}
	}
}
