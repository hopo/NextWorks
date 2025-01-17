package pc33.gdrive03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class GdriveCommand implements Gdrivable {

	@Override
	public void list() {
		// ! gdrive list
		BufferedReader reader = null;
		PrintWriter writer = null;
		
		
		try {

			// ;; create a new array strings
			String[] cmdArray = new String[2];

			// ;; first argument is the program we want to open
			cmdArray[0] = "/usr/local/bin/gdrive";
			cmdArray[1] = "list";

			// ;; second argument is a txt file we want to open with notepad

			// ;; print a message
			System.out.println(">>> $ gdrive list");

			// ;; create a process and execute cmdArray
			Process process = Runtime.getRuntime().exec(cmdArray);
			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			writer = new PrintWriter(System.out);

			String line;
			StringBuffer strbuf = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				strbuf.append(line + "\n");
			}

			System.out.println(">>> list make");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
			}
		}

	}

	@Override
	public void download() {
		// ! gdrive download ID --path ./

	}

	@Override
	public void upload(String filePath) {
		// ! gdrive upload ./

		try {

			// ;; create a new array of 2 strings
			String[] cmdArray = new String[3];

			// ;; first argument is the program we want to open
			cmdArray[0] = "/usr/local/bin/gdrive";
			cmdArray[1] = "upload";

			// ;; second argument is a txt file we want to open with notepad
			cmdArray[2] = filePath;

			// ;; print a message
			System.out.println(">>> Executing gdrive upload");

			// ;; create a process and execute cmdArray
			Process process = Runtime.getRuntime().exec(cmdArray);
			// process.destroy();

			// ;; print another message
			System.out.println(">>> " + filePath + " upload complete");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
