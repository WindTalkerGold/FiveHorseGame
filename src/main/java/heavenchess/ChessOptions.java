package heavenchess;

import org.apache.commons.cli.*;

// assume always left side moves first, left uses to specify human move first or not
public class ChessOptions {
    private boolean leftBot;
    private boolean rightBot;
    private boolean enumNextSteps;

    // all false by now
    private boolean advanced = false;

    public boolean isEnumerating() {
        return enumNextSteps;
    }

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
        options.addOption("e", false, "to enumerate scores");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        ChessOptions chessOptions = new ChessOptions();
        chessOptions.leftBot = cmd.hasOption("l");
        chessOptions.rightBot = cmd.hasOption("r");
        chessOptions.enumNextSteps = cmd.hasOption("e");

        if(chessOptions.leftBot && chessOptions.rightBot) {
            throw new IllegalArgumentException("Cannot specify with both left and right side as bot!");
        }

        return chessOptions;
    }
}
