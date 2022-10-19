package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.control.Move;

import java.util.Random;

public class Balloom extends Enemy {
    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }




    @Override
    public void update() {
        if (this.y % 16 == 0 && this.x % 16 == 0) {
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
        }
    }
}