package heaven.clr;

import java.io.IOException;
import java.util.ArrayList;

public class ClrExeMain {
    public static void main(String[] args) throws IOException {
        String[] args2 = new String[] {"D:\\Coding\\Git\\HeavenRepo\\csharp\\Arsenal\\Arsenal\\bin\\Release\\Arsenal.exe", "exit", "10"};

        ClrExe exe = new ClrExe(args2);
        String[] params = new String[]{"hdsakjdhkasjd", "sldh sjdk dskjlhd", "dsah asjdhaksj aksjdh a", "exit"};
        ArrayList<String> args3 = new ArrayList<String>();

        for (String param: params) {
            args3.add(param);

        }
        exe.feed(args3.iterator());
        for(String line : exe.waitUntilFinish()) {
            System.out.println(line);
        }
        System.out.println("=======================");

    }
}
