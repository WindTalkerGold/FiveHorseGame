package heavenchess;

import org.apache.commons.cli.ParseException;

public class Main {
    public static void main(String[] args) {
        try {
            ChessOptions chessOptions = ChessOptions.parse(args);
        } catch(ParseException pex) {
            System.err.println("input cannot be parsed as valid options.");
            System.err.println(pex.getMessage());

        }
    }
}
