package uet.oop.bomberman.entities.StaticEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Portal extends Entity {
    private boolean isOpen = false;

    public Portal(int x, int y, Image img) {         // Create a contructor of the Portal class
        super(x, y, img);
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void update() {

    }
}
