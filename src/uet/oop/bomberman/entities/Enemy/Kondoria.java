package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;

public class Kondoria extends Enemy{
    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Kondoria(int xUnit, int yUnit, Image img, int is_move, int swap, String direction, int count, int count_to_run) {
        super(xUnit, yUnit, img, is_move, swap, direction, count, count_to_run);
    }
}
