package heavenchess;

import org.apache.commons.cli.*;

// assume always left side moves first, left uses to specify human move first or not
public class ChessOptions {
    private boolean leftBot;
    private boolean rightBot;

    // all false by now
    private boolean advanced = false;

    public boolean isLeftBot() {
        return leftBot;
    }

    public boolean isRightBot() {
        return rightBot;
    }

    public boolean isAdvancedMode() {
        return advanced;
    }

    public static ChessOptions parse(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("l", false, "is left hand human");
        options.addOption("r", false, "is right hand human");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        ChessOptions chessOptions = new ChessOptions();
        chessOptions.leftBot = cmd.hasOption("l");
        chessOptions.rightBot = cmd.hasOption("r");
        if(chessOptions.leftBot && chessOptions.rightBot) {
            throw new IllegalArgumentException("Cannot specify with both left and right side as bot!");
        }

        return chessOptions;
    }
}
