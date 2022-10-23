package uet.oop.bomberman.AI;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Enemy.Enemy;

public class AIBFS extends AI{
    private Bomber bomber;
    private Enemy enemy;

    public AIBFS(Bomber bomber, Enemy enemy) {
        this.bomber = bomber;
        this.enemy = enemy;
    }

    public ArrayList<Integer> shortestPath = new ArrayList();

    public int w = BombermanGame.WIDTH;
    public int h = BombermanGame.HEIGHT;
    public int nameOfVertex;
    public int countNode = (w + 1) * (h); // tổng số đỉnh

    /**
     * node: ma trận đỉnh kề
     * mỗi đỉnh có tối đa 4 cạnh kề
     * 0 là ko kề với đỉnh nào, node lưu đỉnh kề
     */

    public int[][] node = new int[countNode][4];
    public int[][] nodeMatrix = new int[1000][1000];
    public String filePath = "./res/levels/Level1.txt";
    public ArrayList<String> map = new ArrayList();

    public ArrayList<Integer> getShortestPath() {
        return shortestPath;
    }

    /**
     * đọc map từ file
     */
    public void readMapFromFile() {
        // init
        String str = null;
        File file = new File(this.filePath);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                str = sc.nextLine();
                map.add(str);

            }

        } catch (Exception e) {
            e.getMessage(); // lấy lỗi
        }

    }

    /**
     * # -> 0: không đi được
     * * or x -> negative vertex: có thể đi đươc trong tương lai
     * còn lại là 1: đi được tương ứng với các đỉnh
     */

    public void convertToMatrix() {

        int vertex = 1;
        for (int i = 1; i < this.map.size(); i++) {
            for (int j = 0; j < this.map.get(i).length(); j++)

                if (this.map.get(i).charAt(j) == '#') {
                    this.nodeMatrix[i][j] = 0;

                } else if (this.map.get(i).charAt(j) == '*' || this.map.get(i).charAt(j) == 'x') {

                    this.nodeMatrix[i][j] = vertex * (-1);
                    vertex++;

                } else {
                    this.nodeMatrix[i][j] = vertex;
                    vertex++;
                }

        }
    }

    /**
     * từ ma trận đỉnh chuyển sang ma trân cạnh kề
     */


    public void convertToNodeMatrix() {

        for (int i = 1; i < this.h; i++) {
            for (int j = 1; j < this.w; j++) {
                if (this.nodeMatrix[i][j] > 0) {
                    if (this.nodeMatrix[i][j - 1] > 0) {
                        this.node[this.nodeMatrix[i][j]][0] = this.nodeMatrix[i][j - 1];
                    } else {
                        this.node[this.nodeMatrix[i][j]][0] = 0; // không có đỉnh kể
                    }
                    //bên phải
                    if (this.nodeMatrix[i][j + 1] > 0) {
                        this.node[this.nodeMatrix[i][j]][1] = this.nodeMatrix[i][j + 1];
                    } else {
                        this.node[this.nodeMatrix[i][j]][1] = 0;
                    }

                    //cùng cột
                    //bên trên

                    if (this.nodeMatrix[i - 1][j] > 0) {
                        this.node[this.nodeMatrix[i][j]][2] = this.nodeMatrix[i - 1][j];
                    } else {
                        this.node[this.nodeMatrix[i][j]][2] = 0;
                    }

                    //bên dưới
                    if (this.nodeMatrix[i + 1][j] > 0) {
                        this.node[this.nodeMatrix[i][j]][3] = this.nodeMatrix[i + 1][j];
                    } else {
                        this.node[this.nodeMatrix[i][j]][3] = 0;
                    }

                }

            }
        }


    }

    public void updateDestroy_Brick(){
        //https://github.com/17021084/BomberMan_Base_NES/blob/master/src/uet/oop/bomberman/entities/character/enemy/ai/AIAdvance.java
    }


    public int nextDirection(int start, int end) throws IllegalStateException { // exception khi queue full
        Queue<Integer> qNode = new LinkedList<Integer>();

        int[] parent = new int[countNode + 1];
        boolean[] visited = new boolean[countNode + 1];

        if ( start < 0 ) start *=-1;
        if ( end < 0 ) end *=-1;

        visited[start] = false;
        parent[start] = -1;
        parent[end] = -1;

        qNode.add(start);

        while (!qNode.isEmpty()) {
            int currentNode = qNode.poll(); // dequeue
            // duyệt toàn bộ đỉnh kể với current , nếu chưa visit thì  visited<-true
            for (int i = 0; i < 4; i++) {
                if (!visited[node[currentNode][i]] && node[currentNode][i] != 0) {
                    // dán nhãn đã thăm
                    visited[node[currentNode][i]] = true;
                    // gán đỉnh cha
                    parent[node[currentNode][i]] = currentNode;
                    // cho vào queue
                    qNode.add(node[currentNode][i]);

                }

            }
        }

        int p_end = parent[end];
        if (p_end == -1) {
            return -1;
        } else {
            shortestPath.add(end);
            shortestPath.add(p_end);
            while (p_end != start) {
                p_end = parent[p_end];
                shortestPath.add(p_end);
            }
            return shortestPath.get(shortestPath.size()-2);
        }

    }

    @Override
    public void nextMove(){
        int start = nodeMatrix[enemy.getY()][enemy.getX()];
        int end = nodeMatrix[bomber.getY()][bomber.getX()];
        int result = this.nextDirection(start, end);
        if (result - start == 1) Move.right(enemy);
        if (start - result == 1) Move.left(enemy);
        if (start > result) Move.up(enemy);
        if (start < result) Move.down(enemy);
    }

}