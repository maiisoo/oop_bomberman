package uet.oop.bomberman.entities;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_INPeer;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaView;
//import javafx.scene.media.MediaPlayer;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.Enemy.Balloom;
import uet.oop.bomberman.entities.Enemy.Enemy;
import uet.oop.bomberman.entities.StaticEntity.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import uet.oop.bomberman.BombermanGame.*;

import java.io.File;

import static uet.oop.bomberman.BombermanGame.list_kill;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends AnimatedEntity {

    private final static String playerDied = new String("res/Music/just_died.mp3");

    private static boolean deadPlayerAudioPlayed = false;
    public static int count_kill = 0;
    public static int speed = 1;

    //Media media = new Media(new File(playerDied).toURI().toString());
    //MediaPlayer mediaPlayer = new MediaPlayer(media);
    private boolean _moving;

    private int deadSwap = 1;

    public static List<Bomb> bombPlanted = new ArrayList<Bomb>();

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update(){
        flameExposure();
        enemiesExposure();
        count_kill++;
        if(!this.isAlive){
            if(!deadPlayerAudioPlayed){
                //mediaPlayer.play();
                deadPlayerAudioPlayed = true;
            }
            bomberDead();
        }
    }

    private void flameExposure(){
        if (list_kill[BombermanGame.bomberman.getX() / 32][BombermanGame.bomberman.getY() / 32] == 4)
            BombermanGame.bomberman.setAlive(false);
    }

    private void enemiesExposure(){
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
                //BombermanGame.isDead = true;
                this.setAlive(false);
            }
        }
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
        if(1 <= this.deadSwap && this.deadSwap <= 4){
            this.setImg(Sprite.player_dead1.getFxImage());
            this.deadSwap++;
        }
        else if(5 <= this.deadSwap && this.deadSwap <= 8){
            this.setImg(Sprite.player_dead2.getFxImage());
            this.deadSwap++;
        }
        else if(9 <= this.deadSwap && this.deadSwap <= 12){
            this.setImg(Sprite.player_dead3.getFxImage());
            this.deadSwap++;
        }
        else {
            this.setImg(Sprite.transparent.getFxImage());
        }
    }

    public int getSpeed(){
        return this.speed;
    }

    public List<Bomb> getBombs() {
        return bombPlanted;
    }

}
