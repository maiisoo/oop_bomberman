package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AIBFS;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.BombermanGame.enemies;

public class Kondoria extends Enemy{
    private static int swap_kill = 1;
    private static int count_kill = 0;  // Count the number of Kondoria destroyed
    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Kondoria(int xUnit, int yUnit, Image img, int is_move, int swap, String direction, int count, int count_to_run) {
        super(xUnit, yUnit, img, is_move, swap, direction, count, count_to_run);
    }
    private void killKondoria(Enemy enemy) {
        if (count_kill % 16 == 0) {
            if (swap_kill < 4) {
                enemy.setImg(Sprite.kondoria_dead.getFxImage());
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

    @Override
    public void update() {
        killed();
        count_kill++;
        for (Enemy e : enemies) {
            if (e instanceof Kondoria && !((Kondoria) e).isAlive)
                killKondoria(e);
        }
        if (this.x % 32 == 0 && this.y % 32 == 0) {
            AIBFS bfsKondoria = new AIBFS(bomberman, this);
            bfsKondoria.nextMove();
        }
    }
}
