package uet.oop.bomberman.entities;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_INPeer;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends AnimatedEntity {

    public static int speed = 4;

    private boolean _moving;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update(){

    }

    public int getSpeed(){
        return this.speed;
    }

}
