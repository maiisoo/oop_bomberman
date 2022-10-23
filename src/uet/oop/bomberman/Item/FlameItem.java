package uet.oop.bomberman.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticEntity.Bomb;

public class FlameItem extends Item {
    public FlameItem(int x, int y, Image img){
        super(x,y,img);
    }
    public FlameItem(int x, int y, Image img, boolean gotEaten){
        super(x,y,img, gotEaten);
    }

    public void activateImpactOnPlayer(){
        if(!this.isGotEaten()) {
            Bomb.bomb_range++;
            this.setImg(null);
            BombermanGame.items.remove(this);
            this.setGotEaten(true);
        }
    }
}
