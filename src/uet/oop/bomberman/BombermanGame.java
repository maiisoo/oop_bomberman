package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Enemy.*;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticEntity.*;
import uet.oop.bomberman.entities.items.BombItem;
import uet.oop.bomberman.entities.items.FlameItem;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BombermanGame extends Application {
    public static int bombStock = 20;

    public static boolean isDead = false;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    //Media media = new Media(new File("res/Music/title_screen.mp3").toURI().toString());
    //MediaPlayer mediaPlayer = new MediaPlayer(media);

    public static int[][] obj_matrix = new int[WIDTH][HEIGHT];  // A binary matrix of map
                                                                // 0: occupied by an obj, 1: pass-able (grass)
    public static ArrayList<String> map = new ArrayList<>();
    private GraphicsContext gc;
    private Canvas canvas;
    public static ImageView view = new ImageView();
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();

    public static List<Item> items = new ArrayList<>();

    public static Bomber bomberman;

    public static int[][] list_kill = new int[WIDTH][HEIGHT];
    @Override
    public void start(Stage stage) throws Exception{
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(view);
        //Tao bao so luong bomb
        Text text = new Text();
        text.setText("Bombs remaining: " + bombStock);
        text.setX(20);
        text.setY(25);
        root.getChildren().add(text);
        //Tao giao dien

        // Tao scene
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            if (bomberman.isAlive()) {
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
                        Bomb.placeBomb();
                        break;
                }
            }
        });

        //mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        //mediaPlayer.play();

        /*scene.setOnKeyReleased(event -> {
            if (true){
                switch (event.getCode()) {
                    case UP:
                        Move.down(bomberman);
                        break;
                    case DOWN:
                        Move.up(bomberman);
                        break;
                    case RIGHT:
                        Move.left(bomberman);
                        break;
                    case LEFT:
                        Move.right(bomberman);
                        break;
                }
            }
        });*/

        createMap();
        // Them scene vao stage
        stage.setScene(scene);
        stage.setTitle("BombermanGame: Tai Duc - Mai Anh");
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                text.setText("Bombs remaining: " + bombStock);
                render();
                update();
            }
        };
        timer.start();

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
            bomberman = new Bomber(1, 1, Sprite.player_right_2.getFxImage());
            entities.add(bomberman);
            File myObj = new File("res/levels/Level1.txt");
            Scanner myReader = new Scanner(myObj);
            int level = myReader.nextInt();
            int height = myReader.nextInt();
            int width = myReader.nextInt();
            String blank = myReader.nextLine();
            for (int i = 0; i < height; i++) {
                String data = myReader.nextLine();
                Entity object;
                for (int j = 0; j < width; j++) {
                    switch (data.charAt(j)) {
                        case '#':
                            object = new Wall(j, i, Sprite.wall.getFxImage());
                            stillObjects.add(object);
                            obj_matrix[j][i] = 0;
                            break;
                        case '*':
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(object);
                            obj_matrix[j][i] = 3;
                            break;
                        case 'x':
                            object = new Portal(j, i, Sprite.portal.getFxImage());
                            stillObjects.add(object);
                            Grass grass1 = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(grass1);
                            obj_matrix[j][i] = 1;
                            break;
                        case '1':
                            object = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                            enemies.add((Balloom) object);
                            Grass grassUnderBalloom = new Grass(j,i,Sprite.grass.getFxImage());
                            stillObjects.add(grassUnderBalloom);
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
                            BombItem bombitem = new BombItem(j,i, Sprite.powerup_bombs.getFxImage());
                            items.add(bombitem);
                            obj_matrix[j][i] = 1;
                            break;
                        case 'f':
                            FlameItem flameItem = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                            items.add(flameItem);
                            obj_matrix[j][i] = 3;
                            Grass grass4 = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(grass4);
                            break;
                        case 's':
                            SpeedItem speeditem1 = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                            items.add(speeditem1);
                            Brick brick2 = new Brick(j,i,Sprite.brick.getFxImage());
                            stillObjects.add(brick2);
                            Grass grass5 = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(grass5);
                            obj_matrix[j][i] = 1;
                            break;
                        default:
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                            obj_matrix[j][i] = 1; //set value matrix element corresponding to obj grass
                            break;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void update() {
        bomberman.update();
        stillObjects.forEach(Entity::update);
        items.forEach(Item::update);
        enemies.forEach(Enemy::update);
        bomberman.setCount_to_run(bomberman.getCount_to_run() + 1);
        if (bomberman.getCount_to_run() == 4) {
            Move.checkRun(bomberman);
            bomberman.setCount_to_run(0);
        }
        for (Enemy e : enemies) {
            e.setCount_to_run(e.getCount_to_run() + 1);
            if (e.getCount_to_run() == 4) {
                Move.checkRun(e);
                e.setCount_to_run(0);
            }
        }
        if (enemies.size() == 0) {
            Portal portal = new Portal(WIDTH-2, HEIGHT-2, Sprite.portal.getFxImage());
            portal.setOpen(true);
            stillObjects.add(portal);
            if (bomberman.getX() == portal.getX() && bomberman.getY() == portal.getY()){
                Image lvUp = new Image("uploadedImg/Win.png");
                view.setImage(lvUp);
            }
        }
        //if(!bomberman.isAlive()) mediaPlayer.pause();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        items.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }
}
