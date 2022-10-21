package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.BFS;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class Doll extends Enemy {
    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Doll(int xUnit, int yUnit, Image img, int is_move, int swap, String direction, int count, int count_to_run) {
        super(xUnit, yUnit, img, is_move, swap, direction, count, count_to_run);
    }

    @Override
    public void update() {
        if (this.x % 32 == 0 && this.y % 32 == 0) {
            BFS bfsDoll = new BFS(bomberman, this);
            bfsDoll.nextMove();
        }

    }

}
