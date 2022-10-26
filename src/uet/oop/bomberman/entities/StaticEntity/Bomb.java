package uet.oop.bomberman.entities.StaticEntity;

import javafx.scene.image.Image;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.Blocked;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.entities.Bomber.bombPlanted;

public class Bomb extends AnimatedEntity {
    private static final String boomboom = new String("res/Music/bomb_explosion.mp3");

    private static int swap_active = 1;

    private static long bombAppearTime;

    private static long time_tmp;

    private static AnimatedEntity theBomb;

    private static int explosion_swap = 1;

    private static final List<AnimatedEntity> list_bomb_middle_width = new ArrayList();

    private static final List<AnimatedEntity> list_bomb_middle_height = new ArrayList();

    public static int bomb_range = 0;

    private static int power_bomb_down = 0;     // Bomb's destructive power from top to bottom

    private static int power_bomb_up = 0;       // The bomb's destructive power is from the bottom up

    private static int power_bomb_left = 0;     // Bomb's destructive power is from right to left

    private static int power_bomb_right = 0;    // The explosive power of the bomb is from left to right

    private static AnimatedEntity edge_down = null;     // The bottom edge of the block blocks the character from going through

    private static AnimatedEntity edge_up = null;       // The up edge of the block blocks the character from going through

    private static AnimatedEntity edge_left = null;     // The left edge of the block blocks the character from going through

    private static AnimatedEntity edge_right = null;    // The right edge of the block blocks the character from going through

    private static boolean is_edge = false;     // Check if that edge exists

    private static boolean is_middle = false;   // Check if the bomb explodes in the center (plus sign, not T )

    public static int is_bomb = 0;      // Check to see if there's a bomb there: //0 no bomb  //1 had bomb  //2 explosion

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public static void placeBomb() {
        if(is_bomb == 0) {
            is_bomb = 1;
            BombermanGame.bombStock--;
            bombAppearTime = System.currentTimeMillis();
            time_tmp = bombAppearTime;
            int x = BombermanGame.bomberman.getX() / 32;
            int y = BombermanGame.bomberman.getY() / 32;
            x = Math.round((float) x);
            y = Math.round((float) y);
            theBomb = new Bomb(x, y, Sprite.bomb.getFxImage());
            bombPlanted.add((Bomb) theBomb);
            BombermanGame.stillObjects.add(theBomb);
            BombermanGame.obj_matrix[BombermanGame.bomberman.getX() / 32][BombermanGame.bomberman.getY() / 32] = 4;
        }
    }

    private static void bombStillNotExplode() {
        if (swap_active == 1) {
            theBomb.setImg(Sprite.bomb.getFxImage());
            swap_active = 2;
        }
        else if (swap_active == 2) {
            theBomb.setImg(Sprite.bomb_1.getFxImage());
            swap_active = 3;
        }
        else if (swap_active == 3) {
            theBomb.setImg(Sprite.bomb_2.getFxImage());
            swap_active = 4;
        }
        else {
            theBomb.setImg(Sprite.bomb_1.getFxImage());
            swap_active = 1;
        }
    }


    public static void movementConfining() {
        if (Blocked.block_down_bomb(theBomb, 0)) {
            edge_down = new Bomb(theBomb.getX() / 32, theBomb.getY() / 32 + 1, Sprite.bomb_exploded.getFxImage());
            if (bomb_range > 0) {
                for (int i = 1; i <= bomb_range && Blocked.block_down_bomb(theBomb, i); ++i) {
                    edge_down.setY(theBomb.getY() + 32 + i * 32);
                    ++power_bomb_down;
                }
            }

            BombermanGame.stillObjects.add(edge_down);
        }

        if (Blocked.block_up_bomb(theBomb, 0)) {
            edge_up = new Bomb(theBomb.getX() / 32, theBomb.getY() / 32 - 1, Sprite.bomb_exploded.getFxImage());
            if (bomb_range > 0) {
                for (int i = 1; i <= bomb_range && Blocked.block_up_bomb(theBomb, i); ++i) {
                    edge_up.setY(theBomb.getY() - 32 - i * 32);
                    ++power_bomb_up;
                }
            }

            BombermanGame.stillObjects.add(edge_up);
        }

        if (Blocked.block_left_bomb(theBomb, 0)) {
            edge_left = new Bomb(theBomb.getX() / 32 - 1, theBomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            if (bomb_range > 0) {
                for (int i = 1; i <= bomb_range && Blocked.block_left_bomb(theBomb, i); ++i) {
                    edge_left.setX(theBomb.getX() - 32 - i * 32);
                    ++power_bomb_left;
                }
            }

            BombermanGame.stillObjects.add(edge_left);
        }

        if (Blocked.block_right_bomb(theBomb, 0)) {
            edge_right = new Bomb(theBomb.getX() / 32 + 1, theBomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            if (bomb_range > 0) {
                for (int i = 1; i <= bomb_range && Blocked.block_right_bomb(theBomb, i); ++i) {
                    edge_right.setX(theBomb.getX() + 32 + i * 32);
                    ++power_bomb_right;
                }
            }

            BombermanGame.stillObjects.add(edge_right);
        }
    }

    public static void explodeAtMiddle(){
        AnimatedEntity boogieMid; //A ghost AnimatedEntity to work independently from the stillObjects lists throughout the updates

        int i; //To serve for the loops

        for(i = 1; i <= power_bomb_down; i++) {
            boogieMid = new Bomb(theBomb.getX() / 32, theBomb.getY() / 32 + i, Sprite.bomb_exploded.getFxImage());
            list_bomb_middle_height.add(boogieMid);
        }

        for(i = 1; i <= power_bomb_up; i++) {
            boogieMid = new Bomb(theBomb.getX() / 32, theBomb.getY() / 32 - i, Sprite.bomb_exploded.getFxImage());
            list_bomb_middle_height.add(boogieMid);
        }

        for(i = 1; i <= power_bomb_left; i++) {
            boogieMid = new Bomb(theBomb.getX() / 32 - i, theBomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            list_bomb_middle_width.add(boogieMid);
        }

        for(i = 1; i <= power_bomb_right; i++) {
            boogieMid = new Bomb(theBomb.getX() / 32 + i, theBomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            list_bomb_middle_width.add(boogieMid);
        }

        BombermanGame.stillObjects.addAll(list_bomb_middle_width);
        BombermanGame.stillObjects.addAll(list_bomb_middle_height);
    }

    public static void explosionCenter() {      // Determine the explosion center of the bomb
        if (explosion_swap == 1) {
            theBomb.setImg(Sprite.bomb_exploded.getFxImage());
            BombermanGame.list_kill[theBomb.getX() / 32][theBomb.getY() / 32] = 4;
            if (Blocked.block_down_bomb(theBomb, power_bomb_down)) {
                edge_down.setImg(Sprite.explosion_vertical_down_last.getFxImage());
                BombermanGame.list_kill[edge_down.getX() / 32][edge_down.getY() / 32] = 4;
            }

            if (Blocked.block_up_bomb(theBomb, power_bomb_up)) {
                edge_up.setImg(Sprite.explosion_vertical_top_last.getFxImage());
                BombermanGame.list_kill[edge_up.getX() / 32][edge_up.getY() / 32] = 4;
            }

            if (Blocked.block_left_bomb(theBomb, power_bomb_left)) {
                edge_left.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
                BombermanGame.list_kill[edge_left.getX() / 32][edge_left.getY() / 32] = 4;
            }

            if (Blocked.block_right_bomb(theBomb, power_bomb_right)) {
                edge_right.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
                BombermanGame.list_kill[edge_right.getX() / 32][edge_right.getY() / 32] = 4;
            }

            if (list_bomb_middle_height.size() > 0) {
                for (AnimatedEntity e : list_bomb_middle_height) {
                    e.setImg(Sprite.explosion_vertical.getFxImage());
                    BombermanGame.list_kill[e.getX() / 32][e.getY() / 32] = 4;
                }
            }

            if (list_bomb_middle_width.size() > 0) {
                for (AnimatedEntity e : list_bomb_middle_width) {
                    e.setImg(Sprite.explosion_horizontal.getFxImage());
                    BombermanGame.list_kill[e.getX() / 32][e.getY() / 32] = 4;
                }
            }

            explosion_swap = 2;
        }
        else if (explosion_swap == 2) {
            theBomb.setImg(Sprite.bomb_exploded1.getFxImage());
            if (Blocked.block_down_bomb(theBomb, power_bomb_down)) {
                edge_down.setImg(Sprite.explosion_vertical_down_last1.getFxImage());
            }

            if (Blocked.block_up_bomb(theBomb, power_bomb_up)) {
                edge_up.setImg(Sprite.explosion_vertical_top_last1.getFxImage());
            }

            if (Blocked.block_left_bomb(theBomb, power_bomb_left)) {
                edge_left.setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
            }

            if (Blocked.block_right_bomb(theBomb, power_bomb_right)) {
                edge_right.setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
            }

            if (is_middle) {
                for (AnimatedEntity e : list_bomb_middle_height) {
                    e.setImg(Sprite.explosion_vertical1.getFxImage());
                }
                for (AnimatedEntity e : list_bomb_middle_width) {
                    e.setImg(Sprite.explosion_horizontal1.getFxImage());
                }
            }

            explosion_swap = 3;
        }
        else if (explosion_swap == 3) {
            theBomb.setImg(Sprite.bomb_exploded2.getFxImage());
            if (Blocked.block_down_bomb(theBomb, power_bomb_down)) {
                edge_down.setImg(Sprite.explosion_vertical_down_last2.getFxImage());
            }

            if (Blocked.block_up_bomb(theBomb, power_bomb_up)) {
                edge_up.setImg(Sprite.explosion_vertical_top_last2.getFxImage());
            }

            if (Blocked.block_left_bomb(theBomb, power_bomb_left)) {
                edge_left.setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
            }

            if (Blocked.block_right_bomb(theBomb, power_bomb_right)) {
                edge_right.setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
            }

            if (is_middle) {
                for (AnimatedEntity e : list_bomb_middle_height) {
                    e.setImg(Sprite.explosion_vertical2.getFxImage());
                }
                for (AnimatedEntity e : list_bomb_middle_width) {
                    e.setImg(Sprite.explosion_horizontal2.getFxImage());
                }
            }

            explosion_swap = 1;
        }

    }

    private static void checkActive() {     // Check what stages the bomb has gone through before detonating
        if (is_bomb == 1) {
            if (System.currentTimeMillis() - bombAppearTime < 2000L) {
                if (System.currentTimeMillis() - time_tmp > 100L) {
                    bombStillNotExplode();
                    time_tmp += 100L;
                }
            }
            else {
                is_bomb = 2;
                bombAppearTime = System.currentTimeMillis();
                time_tmp = bombAppearTime;
            }
        }

    }

    private static void explosionExecution() {   // Check the bomb's detonation time after the bomb is activated
        if (is_bomb == 2) {
            if (System.currentTimeMillis() - bombAppearTime < 1000L) {
                if (System.currentTimeMillis() - time_tmp > 100L) {
                    if (!is_edge) {
                        movementConfining();
                        is_edge = true;
                    }

                    if (bomb_range > 0 && !is_middle) {
                        explodeAtMiddle();
                        is_middle = true;
                    }

                    explosionCenter();
                    time_tmp += 100L;
                }
            }
            else {
                is_bomb = 0;
                BombermanGame.obj_matrix[theBomb.getX() / 32][theBomb.getY() / 32] = 1;
                BombermanGame.list_kill[theBomb.getX() / 32][theBomb.getY() / 32] = 1;
                    theBomb.setImg(null);
                if (Blocked.block_down_bomb(theBomb, power_bomb_down)) {
                    edge_down.setImg(null);
                    BombermanGame.obj_matrix[edge_down.getX() / 32][edge_down.getY() / 32] = 1;
                    BombermanGame.list_kill[edge_down.getX() / 32][edge_down.getY() / 32] = 1;
                }

                if (Blocked.block_up_bomb(theBomb, power_bomb_up)) {
                    edge_up.setImg(null);
                    BombermanGame.obj_matrix[edge_up.getX() / 32][edge_up.getY() / 32] = 1;
                    BombermanGame.list_kill[edge_up.getX() / 32][edge_up.getY() / 32] = 1;
                }

                if (Blocked.block_left_bomb(theBomb, power_bomb_left)) {
                    edge_left.setImg(null);
                    BombermanGame.obj_matrix[edge_left.getX() / 32][edge_left.getY() / 32] = 1;
                    BombermanGame.list_kill[edge_left.getX() / 32][edge_left.getY() / 32] = 1;
                }

                if (Blocked.block_right_bomb(theBomb, power_bomb_right)) {
                    edge_right.setImg(null);
                    BombermanGame.obj_matrix[edge_right.getX() / 32][edge_right.getY() / 32] = 1;
                    BombermanGame.list_kill[edge_right.getX() / 32][edge_right.getY() / 32] = 1;
                }

                if (is_middle) {
                    for (AnimatedEntity e : list_bomb_middle_width) {
                        BombermanGame.list_kill[e.getX() / 32][e.getY() / 32] = 1;
                        BombermanGame.obj_matrix[e.getX() / 32][e.getY() / 32] = 1;
                    }
                    for (AnimatedEntity e : list_bomb_middle_height) {
                        BombermanGame.list_kill[e.getX() / 32][e.getY() / 32] = 1;
                        BombermanGame.obj_matrix[e.getX() / 32][e.getY() / 32] = 1;
                    }
                }
                Media media = new Media(new File(boomboom).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                BombermanGame.stillObjects.removeAll(list_bomb_middle_height);
                BombermanGame.stillObjects.removeAll(list_bomb_middle_width);
                list_bomb_middle_height.clear();
                list_bomb_middle_width.clear();
                is_edge = false;
                is_middle = false;
                power_bomb_down = 0;
                power_bomb_up = 0;
                power_bomb_left = 0;
                power_bomb_right = 0;
            }
        }

    }


    public void update() {
        checkActive();
        explosionExecution();
    }

}