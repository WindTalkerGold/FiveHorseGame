package heaven.clr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

import java.util.Iterator;

public class ClrExe {
    private Process proc;

    public ClrExe(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(args);
        proc = pb.start();
    }

    public ArrayList<String> waitUntilFinish() {
        ArrayList<String> output = new ArrayList<String>();
        final Scanner in = new Scanner(proc.getInputStream());
        while(in.hasNextLine()) {
            String line = in.nextLine();
            if(line.equals("<End>")) {
                break;
            }
            output.add(in.nextLine());
        }

        return output;
    }

    public void feed(Iterator<String> data) {
        PrintWriter out = new PrintWriter(proc.getOutputStream());
        while(data.hasNext()) {
            out.println(data.next());
        }
        out.flush();

    }




}
