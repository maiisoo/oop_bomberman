package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.Bomber;

public class Oneal extends Enemy {
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Oneal(int xUnit, int yUnit, Image img, int is_move, int swap, String direction, int count, int count_to_run) {
        super(xUnit, yUnit, img, is_move, swap, direction, count, count_to_run);
    }

   /* @Override
    public void update() {
        if (this.y % 16 == 0 && this.x % 16 == 0) {
            if (Bomber.getX() < this.x) {
                Move.left(this);
            }
            if (Bomber.getX() > this.x) {
                Move.right(this);
            }
            if (Bomber.getY() > this.y) {
                Move.down(this);
            }
            if (Bomber.getY() < this.y) {
                Move.up(this);
            }
        }
    }*/
}
