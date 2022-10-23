package uet.oop.bomberman.AI;

import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Enemy.Enemy;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class AISimple extends AI{
    protected Bomber bomber;
    protected Enemy enemy;
    public AISimple(Bomber bomber, Enemy enemy) {
        this.bomber = bomber;
        this.enemy = enemy;
    }

    public void nextMove() {
        if (enemy.getY() % 8 == 0 && enemy.getX() % 8 == 0) {
            if (bomberman.getX() < enemy.getX()) {
                Move.left(enemy);
            }
            if (bomberman.getX() > enemy.getX()) {
                Move.right(enemy);
            }
            if (bomberman.getY() > enemy.getY()) {
                Move.down(enemy);
            }
            if (bomberman.getY() < enemy.getY()) {
                Move.up(enemy);
            }
        }
    }
}
