package heavenchess;

import org.apache.commons.cli.ParseException;
import java.util.ArrayList;
import heavenchess.ai.AttackEnumerator;
import heavenchess.board.BasicChessboard;
import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;
import heavenchess.ai.AllPossibleMoveProvider;
import heavenchess.movement.BasicModeValidator;
import heavenchess.movement.ChessboardValidator;
import heavenchess.movement.FlickClampManager;
import heavenchess.movement.MoveProvider;
import heavenchess.movement.Move;
import heavenchess.ui.ConsoleChessboardDrawer;
import heavenchess.ui.ConsoleMovementProvider;

public class Main {

    public static void main(String[] args) {
        ChessOptions chessOptions;
        try {
            chessOptions = ChessOptions.parse(args);
        } catch(ParseException pex) {
            System.err.println("input cannot be parsed as valid options.");
            System.err.println(pex.getMessage());
            return;
        }
        
        if(chessOptions.isEnumerating()) {
            enumerate();
        } else {
            consoleAppRun();
        }
    }

    public static void enumerate() {
        Chessboard chessboard = new BasicChessboard();
        FlickClampManager chessboardManager = new FlickClampManager(chessboard);
        AttackEnumerator evaluator = new AttackEnumerator();
        ChessboardValidator validator = new BasicModeValidator();

        ConsoleChessboardDrawer drawer = new ConsoleChessboardDrawer();
        MoveProvider moveProvider = new ConsoleMovementProvider();

        drawer.Draw(chessboard);
        ChessboardState currentState = ChessboardState.LeftOn;
        while(chessboard.countSlotsOfState(currentState) > 0) {
            AllPossibleMoveProvider enumerator = new AllPossibleMoveProvider(chessboard, currentState);
            int num = 0;
            while(enumerator.hasNext()) {
                Move step = enumerator.next();
                Chessboard moveResult = chessboard.move2(step, currentState);
                int changedNum = chessboardManager.runFlip(currentState);
                int score = changedNum + evaluator.score(currentState, moveResult);
                System.out.println(step+" "+changedNum+" "+score);
                num++;
            }
            System.out.println(num+" cases");

            Move move = moveProvider.next();
            if(move == null || !validator.isMovementValid(move, chessboard, currentState)) {
                continue;
            }
            chessboard.move(move, currentState);
            int changedNum = chessboardManager.runFlip(currentState);
            System.out.println(changedNum+" chessmen changed for "+currentState);
            drawer.Draw(chessboard);
            currentState = currentState.getFlip();
        }
        System.out.println(currentState.getFlip()+" wins!");
    }

    public static void consoleAppRun() {
        Chessboard chessboard = new BasicChessboard();
        FlickClampManager chessboardManager = new FlickClampManager(chessboard);
        AttackEnumerator evaluator = new AttackEnumerator();
        ChessboardValidator validator = new BasicModeValidator();

        ConsoleChessboardDrawer drawer = new ConsoleChessboardDrawer();
        MoveProvider moveProvider = new ConsoleMovementProvider();

        drawer.Draw(chessboard);
        ChessboardState currentState = ChessboardState.LeftOn;
        while(chessboard.countSlotsOfState(currentState) > 0) {
            Move move = moveProvider.next();
            if(move == null || !validator.isMovementValid(move, chessboard, currentState)) {
                continue;
            }
            chessboard.move(move, currentState);
            int changedNum = chessboardManager.runFlip(currentState);
            System.out.println(changedNum+" chessmen changed for "+currentState);
            
            ArrayList<Move> possibleAttacks = evaluator.getAllAttackableTargets(currentState, chessboard);
            if(possibleAttacks.size() == 0) {
                System.out.println("not attackable!");
            } else {
                for(Move attack : possibleAttacks) {
                    System.out.println("    "+attack.toString());
                }
            }
            drawer.Draw(chessboard);
            currentState = currentState.getFlip();
        }
        System.out.println(currentState.getFlip()+" wins!");
    }
}
