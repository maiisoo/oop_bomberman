package uet.oop.bomberman.entities.StaticEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

import static uet.oop.bomberman.BombermanGame.*;
public class Brick extends Entity {
    public Brick(int x, int y, Image img) {
        super(x,y,img);
    }

    public static ArrayList<Brick> brokenBrick = new ArrayList<>();

    public static ArrayList<Brick> brokenBrick = new ArrayList<>();
    public void update(){
        if(gotBlasted()){
            obj_matrix[this.x/32][this.y/32] = 1;
            this.setImg(Sprite.grass.getFxImage());
        }
    }

    public boolean gotBlasted(){
        boolean isBlasted = (list_kill[this.x/32][this.y/32] == 4);
        if (isBlasted) brokenBrick.add(this);
        return(isBlasted);
    }
}
