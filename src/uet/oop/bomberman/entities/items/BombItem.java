package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;

public class BombItem extends Item {
    public BombItem(int x, int y, Image img){
        super(x,y,img);
    }

    @Override
    public void update() {}

    @Override
    public void activateImpactOnPlayer() {
        /*if(!this.isGotEaten()) {
            Bomb.bomb_range++;
            this.setImg(null);
            BombermanGame.items.remove(this);
            this.setGotEaten(true);
        }*/
    }
}
