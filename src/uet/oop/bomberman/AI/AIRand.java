package uet.oop.bomberman.AI;

import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.Enemy.Enemy;

import java.util.Random;

public class AIRand {
    private Enemy enemy;

    public AIRand(Enemy enemy) {
        this.enemy = enemy;
    }

    public void nextMove() {
        if (enemy.getY() % 16 == 0 && enemy.getX() % 16 == 0) {
            Random random = new Random();
            int direction = random.nextInt(4);
            //System.out.println(direction);
            switch (direction) {
                case 0:
                    Move.down(enemy);
                    break;
                case 1:
                    Move.up(enemy);
                    break;
                case 2:
                    Move.left(enemy);
                    break;
                case 3:
                    Move.right(enemy);
                    break;
            }
        }
    }
}