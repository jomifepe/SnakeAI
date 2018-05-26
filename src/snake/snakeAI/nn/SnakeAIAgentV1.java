package snake.snakeAI.nn;

import snake.Action;
import snake.Cell;
import snake.Perception;
import snake.SnakeAgent;
import snake.snakeAI.nn.utils.ActivationFunction;

import java.awt.*;

public class SnakeAIAgentV1 extends SnakeAIAgent {

    public SnakeAIAgentV1(Cell cell, int inputLayerSize, int hiddenLayerSize, int outputLayerSize, Color color) {
        super(cell, inputLayerSize, hiddenLayerSize, outputLayerSize, color);
    }

    @Override
    protected int[] buildInputsFromPerception(Perception perception) {
        Cell n = perception.getN();
        Cell s = perception.getS();
        Cell e = perception.getE();
        Cell w = perception.getW();
        Cell food = environment.getFood();

//        int[] distancesToFood = {
//                n != null ? Math.abs(food.getLine() - n.getLine()) + (food.getColumn() - n.getColumn()) : Integer.MAX_VALUE,
//                s != null ? Math.abs(food.getLine() - s.getLine()) + (food.getColumn() - s.getColumn()) : Integer.MAX_VALUE,
//                e != null ? Math.abs(food.getLine() - e.getLine()) + (food.getColumn() - e.getColumn()): Integer.MAX_VALUE,
//                w != null ? Math.abs(food.getLine() - w.getLine()) + (food.getColumn() - w.getColumn()) : Integer.MAX_VALUE
//        };
//
//        int decision = 0;
//        int minDistance = distancesToFood[0];
//        for (int i = 0; i < 4; i++) {
//            if (distancesToFood[i] < minDistance)
//                decision = i;
//        }

        return new int[] {
                food.isToTheNorthOf(head) ? 1 : 0,
                food.isToTheSouthOf(head) ? 1 : 0,
                food.isToTheEastOf(head) ? 1 : 0,
                food.isToTheWestOf(head) ? 1 : 0,
                n != null && n.isFree() ? (previous == null ? 1 : (previous.opposite() != Action.NORTH ? 1 : 0)) : 0,
                s != null && s.isFree() ? (previous == null ? 1 : (previous.opposite() != Action.SOUTH ? 1 : 0)) : 0,
                e != null && e.isFree() ? (previous == null ? 1 : (previous.opposite() != Action.EAST ? 1 : 0)) : 0,
                w != null && w.isFree() ? (previous == null ? 1 : (previous.opposite() != Action.WEST ? 1 : 0)) : 0
        };
    }
}