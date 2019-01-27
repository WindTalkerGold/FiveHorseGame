package heavenchess.ui;

import heavenchess.movement.Point;
import heavenchess.movement.Move;
import heavenchess.movement.MoveProvider;

public class ConsoleMovementProvider implements MoveProvider {

    @Override
    public Move next() {
        String line = System.console().readLine();
        String[] split = line.split(" ");
        if(line.length() != 4) {
            System.err.println("Error num of params");
            return null;
        }
        int[] nums = new int[4];
        for(int i=0;i<4;i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        return new Move(new Point(nums[0], nums[1]), 
                        new Point(nums[2], nums[3]));
    }
}