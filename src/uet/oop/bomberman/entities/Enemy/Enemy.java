package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;

import static uet.oop.bomberman.BombermanGame.enemies;
import static uet.oop.bomberman.BombermanGame.list_kill;

public abstract class Enemy extends AnimatedEntity {
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Enemy(int xUnit, int yUnit, Image img, int is_move, int swap, String direction, int count, int count_to_run) {
        super(xUnit, yUnit, img, is_move, swap, direction, count, count_to_run);
    }

    protected void killed() {
        for (Enemy enemy : enemies) {
            if (list_kill[enemy.getX() / 32][enemy.getY() / 32] == 4) {
                enemy.setAlive(false);
            }
        }
    }

}
