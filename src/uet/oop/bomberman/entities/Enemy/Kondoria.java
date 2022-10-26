package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AIBFS;
import uet.oop.bomberman.AI.AISimple;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.control.Move4Kondoria;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Kondoria extends Enemy{
    private static int swap_kill = 1;
    private static int count_kill = 0;  // Count the number of Kondoria destroyed

    private static boolean isEgde = false;
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

    private void moveKondoria(){
        if (this.y % 16 == 0 && this.x % 16 == 0) {
            if (this.x / 32 <= 1 || this.x / 32 >= WIDTH - 2)
                isEgde = !isEgde;
            if (isEgde == false)
                Move4Kondoria.left(this);
            else {
                Move4Kondoria.right(this);
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
        moveKondoria();
    }
}
