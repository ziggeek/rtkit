package dev.zig.util;

//https://www.logicbig.com/how-to/code-snippets/jcode-java-command-line-animation
public class ConsoleHelper {

    private static final ConsoleHelper INSTANCE = new ConsoleHelper();

    public ConsoleHelper() {
    }

    public static ConsoleHelper getInstance() {
        return INSTANCE;
    }

    private String lastLine = "";

    public void print(String line) {
        //clear the last line if longer
        if (lastLine.length() > line.length()) {
            String temp = "";
            for (int i = 0; i < lastLine.length(); i++) {
                temp += " ";
            }
            if (temp.length() > 1)
                System.out.print("\r" + temp);
        }
        System.out.print("\r" + line);
        lastLine = line;
    }

    private byte anim;

    public void animate(String line) {
        switch (anim) {
            case 1:
                print("Идёт загрзука данных в БД [ \\ ] " + line);
                break;
            case 2:
                print("Идёт загрзука данных в БД [ | ] " + line);
                break;
            case 3:
                print("Идёт загрзука данных в БД [ / ] " + line);
                break;
            default:
                anim = 0;
                print("Идёт загрзука данных в БД [ - ] " + line);
        }
        anim++;
    }
}
