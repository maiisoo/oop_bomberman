package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AIRand;
import uet.oop.bomberman.AI.AISimple;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Balloom extends Enemy {
    private static int swap_kill = 1;
    private static int count_kill = 0;  // Count the number of Ballooms destroyed

    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public Balloom(int xUnit, int yUnit, Image img, int is_move, int swap, String direction, int count, int count_to_run) {
        super(xUnit, yUnit, img, is_move, swap, direction, count, count_to_run);
    }

    private void killBalloom(Enemy enemy)  {    //Bomber destroys Balloon
        if (count_kill % 16 == 0) {
            if (swap_kill < 4) {
                enemy.setImg(Sprite.balloom_dead.getFxImage());
                swap_kill++;
            }
            else if (swap_kill == 4) {
                enemy.setImg(Sprite.player_dead1.getFxImage());
                enemy.setImg(Sprite.player_dead2.getFxImage());
                enemy.setImg(Sprite.player_dead3.getFxImage());
                swap_kill ++;
            }
            else {
                enemy.setAlive(false);
                enemy.setImg(Sprite.transparent.getFxImage());
                enemies.remove(enemy);
                swap_kill = 1;
            }
        }
    }

    public void moveBalloom(){
        /*if (this.y % 16 == 0 && this.x % 16 == 0) {
            Random random = new Random();
            int direction = random.nextInt(4);
            switch (direction) {
                case 0:
                    Move.down(this);
                    break;
                case 1:
                    Move.up(this);
                    break;
                case 2:
                    Move.left(this);
                    break;
                case 3:
                    Move.right(this);
                    break;
            }
        }*/
        AIRand ai = new AIRand(this);
        ai.nextMove();
    }

    @Override
    public void update() {
        killed();
        count_kill++;
        for (Enemy enemy : enemies) {
            if (enemy instanceof Balloom && !((Balloom) enemy).isAlive)
                killBalloom(enemy);
        }
        moveBalloom();
    }
}