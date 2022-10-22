package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AISimple;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Oneal extends Enemy {
    private static int swap_kill = 1;
    private static int count_kill = 0;
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Oneal(int xUnit, int yUnit, Image img, int is_move, int swap, String direction, int count, int count_to_run) {
        super(xUnit, yUnit, img);
        this.is_move = 4;
        this.swap = 1;
        this.direction = "up";
        this.count = 0;
        this.count_to_run = 0;
    }

    private void killOneal(Enemy enemy) {
        if (count_kill % 16 == 0) {
            if (swap_kill < 4) {
                enemy.setImg(Sprite.oneal_dead.getFxImage());
                swap_kill++;
            }
            else if (swap_kill == 4) {
                enemy.setImg(Sprite.player_dead1.getFxImage());
                enemy.setImg(Sprite.player_dead2.getFxImage());
                enemy.setImg(Sprite.player_dead3.getFxImage());
                swap_kill++;
            }
            else {
                enemy.setAlive(false);
                enemy.setImg(Sprite.transparent.getFxImage());
                enemies.remove(enemy);
                swap_kill = 1;
            }
        }
    }

    public void moveOneal(){
        if (this.x % 32 == 0 && this.y % 32 == 0) {
            AISimple ai = new AISimple(bomberman, this);
            ai.nextMove();
        }
    }

    @Override
    public void update() {
        killed();
        count_kill++;
        for (Enemy enemy : enemies) {
            if (enemy instanceof Oneal && !((Oneal) enemy).isAlive)
                killOneal(enemy);
        }
        moveOneal();
    }

}
