package uet.oop.bomberman.entities;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_INPeer;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.Enemy.Balloom;
import uet.oop.bomberman.entities.Enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import uet.oop.bomberman.BombermanGame.*;

public class Bomber extends AnimatedEntity {

    public static int speed = 4;

    private boolean _moving;

    private int deadSwap = 1;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update(){
        if(enemiesExposure()){
            BombermanGame.isDead = true;
        }
        if(BombermanGame.isDead){
            bomberDead();
        }
    }

    private boolean enemiesExposure(){
        int ax = BombermanGame.bomberman.getX();
        int ay = BombermanGame.bomberman.getY();
        for (Enemy enemy : BombermanGame.enemies) {
            int bx = enemy.getX();
            int by = enemy.getY();
            if (
                    ax == bx && by - 32 <= ay && by + 32 >= ay
                            || ay == by && bx - 32 <= ax && bx + 32 >= ax
            )
            {
                return true;
            }
        }
        return false;
    }
    public static void setSpeed(int speed) {
        Bomber.speed = speed;
    }

    public boolean is_moving() {
        return _moving;
    }

    public void set_moving(boolean _moving) {
        this._moving = _moving;
    }

    public int getDeadSwap() {
        return deadSwap;
    }

    public void setDeadSwap(int deadSwap) {
        this.deadSwap = deadSwap;
    }

    private void bomberDead(){
        if(this.deadSwap == 1){
            this.setImg(Sprite.player_dead1.getFxImage());
            this.deadSwap = 2;
        }
        else if(this.deadSwap == 2){
            this.setImg(Sprite.player_dead2.getFxImage());
            this.deadSwap = 3;
        }
        else if(this.deadSwap == 3){
            this.setImg(Sprite.player_dead3.getFxImage());
            this.deadSwap = 4;
        }
        else if(this.deadSwap > 3){
            this.setImg(null);
        }
    }

    public int getSpeed(){
        return this.speed;
    }

}
