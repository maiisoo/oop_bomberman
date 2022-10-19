package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.BFS;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Enemy.Enemy;

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
            BFS bfs = new BFS(bomberman, this);
            int start = bfs.nodeMatrix[this.getY()][this.getX()];
            int end = bfs.nodeMatrix[bomberman.getY()][bomberman.getX()];
            int result = bfs.nextMove(start, end);
            if (result - start == 1) Move.right(this);
            if (start - result == 1) Move.left(this);
            if (start > result) Move.up(this);
            if (start < result) Move.down(this);
        }

    }

}
