package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticEntity.Bomb;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item {
    public FlameItem(int x, int y, Image img){
        super(x,y,img);
    }

    @Override
    public void activateImpactOnPlayer() {
        if(!this.isGotEaten()) {
            Bomb.bomb_range++;
            this.setImg(Sprite.grass.getFxImage());;
            this.setGotEaten(true);
            BombermanGame.items.remove(this);
        }
    }

    void changePic(){
        this.setImg(Sprite.powerup_flames.getFxImage());
    }
}
