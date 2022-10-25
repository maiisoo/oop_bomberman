package uet.oop.bomberman.entities.StaticEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
public class Brick extends Entity {
    public Brick(int x, int y, Image img) {super(x,y,img);}

    public void update(){
        if(gotBlasted()){
            obj_matrix[this.x/32][this.y/32] = 1;
            this.setImg(Sprite.grass.getFxImage());
        }
    }

    public boolean gotBlasted(){
        return(list_kill[this.x/32][this.y/32] == 4);
    }
}
