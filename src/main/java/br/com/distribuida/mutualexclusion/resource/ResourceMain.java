package br.com.distribuida.mutualexclusion.resource;

import java.io.*;

public class ResourceMain {
    private static final String SERVER_HOST = "127.0.0.1";
    private static final String NAME_FILE = "resource.txt";

    public static Integer readLastValueFile() throws IOException {
        String lastLine = "";
        String currentLine;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(NAME_FILE));
        while((currentLine = bufferedReader.readLine()) != null){
            lastLine = currentLine;
        }

        return Integer.parseInt(lastLine);
    }

    public static void writeValue(final Integer value) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NAME_FILE, true));
        bufferedWriter.write("\n".concat(String.valueOf(value)));
        bufferedWriter.close();
    }
}
