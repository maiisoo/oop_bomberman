package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Enemy.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.control.Move;

import java.beans.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BombermanGame extends Application {

    public static boolean isDead = false;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static int[][] obj_matrix = new int[WIDTH][HEIGHT];  // A binary matrix of map
    // 0: occupied by an obj, 1: pass-able (grass)

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();

    public static Bomber bomberman;

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            boolean momentum = false;
            boolean switch_direction = true;
            if (true){
            switch (event.getCode()) {
                case UP:
                    Move.up(bomberman);
                    break;
                case DOWN:
                    Move.down(bomberman);
                    break;
                case RIGHT:
                    Move.right(bomberman);
                    break;
                case LEFT:
                    Move.left(bomberman);
                    break;
                case SPACE:
                    isDead = true;
                    break;
            }
            }
        });
        createMap();
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        //Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        //entities.add(bomberman);
    }

    public void createMap() {
        /* for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
         */

        try {
            bomberman = new Bomber(1,1,Sprite.player_right_2.getFxImage());
            entities.add(bomberman);
            File myObj = new File("res/levels/Level1.txt");
            Scanner myReader = new Scanner(myObj);
            int level = myReader.nextInt();
            int height = myReader.nextInt();
            int width = myReader.nextInt();
            String blank = myReader.nextLine();
            for(int i = 0; i < height; i++) {
                String data = myReader.nextLine();
                Entity object;
                for(int j = 0; j < width; j++){
                    switch(data.charAt(j)) {
                        case '#':
                            object = new Wall(j, i, Sprite.wall.getFxImage());
                            obj_matrix[j][i] = 0;
                            break;
                        case 'x':
                            object = new Portal(j, i, Sprite.portal.getFxImage());
                            Grass grass1 = new Grass(j,i,Sprite.grass.getFxImage());
                            stillObjects.add(grass1);
                            obj_matrix[j][i] = 1;
                            break;
                        case '1':
                            object = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                            enemies.add((Balloom) object);
                            Grass grass2 = new Grass(j,i,Sprite.grass.getFxImage());
                            stillObjects.add(grass2);
                            obj_matrix[j][i] = 1;
                            break;
                        case '2':
                            object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                            enemies.add((Oneal) object);
                            Grass grassUnderOneal = new Grass(j,i,Sprite.grass.getFxImage());
                            stillObjects.add(grassUnderOneal);
                            obj_matrix[j][i] = 1;
                            break;
                        case '3':
                            object = new Doll(j, i, Sprite.doll_left1.getFxImage());
                            enemies.add((Doll) object);
                            Grass grassUnderDoll = new Grass(j,i,Sprite.grass.getFxImage());
                            stillObjects.add(grassUnderDoll);
                            obj_matrix[j][i] = 1;
                            break;
                        case '4':
                            object = new Kondoria(j, i, Sprite.kondoria_left1.getFxImage());
                            enemies.add((Kondoria) object);
                            Grass grassUnderKondoria = new Grass(j,i,Sprite.grass.getFxImage());
                            stillObjects.add(grassUnderKondoria);
                            obj_matrix[j][i] = 1;
                            break;
                        case 'b':
                            object = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                            Grass grass3 = new Grass(j,i,Sprite.grass.getFxImage());
                            stillObjects.add(grass3);
                            obj_matrix[j][i] = 1;
                            break;
                        case 'f':
                            object = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                            Grass grass4 = new Grass(j,i,Sprite.grass.getFxImage());
                            stillObjects.add(grass4);
                            obj_matrix[j][i] = 1;
                            break;
                        case 's':
                            object = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                            Grass grass5 = new Grass(j,i,Sprite.grass.getFxImage());
                            stillObjects.add(grass5);
                            obj_matrix[j][i] = 1;
                            break;
                        default:
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                            obj_matrix[j][i] = 1; //set value matrix element corresponding to obj grass
                            break;
                    }
                    stillObjects.add(object);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void update() {
        enemies.forEach(Enemy::update);
        bomberman.update();
        for (Enemy e : enemies) {
            e.setCount_to_run(e.getCount_to_run() + 1);
            if (e.getCount_to_run() == 4) {
                Move.checkRun(e);
                e.setCount_to_run(0);
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }
}
