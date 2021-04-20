package com.kzw.leisure;

public class Cat {

    public void move(int x,int y) {
        int[][] map = new int[8][8];
        map[1][1] = 1;
        map[1][2] = 1;
        map[1][3] = 1;
        map[1][5] = 1;
        map[1][6] = 1;
        map[2][1] = 1;
        map[2][6] = 1;
        map[3][3] = 1;
        map[3][4] = 1;
        map[3][6] = 1;
        map[4][1] = 1;
        map[4][3] = 1;
        map[4][4] = 1;
        map[5][1] = 1;
        map[5][6] = 1;
        map[6][1] = 1;
        map[6][2] = 1;
        map[6][4] = 1;
        map[6][5] = 1;
        map[6][6] = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        way(map,x,y);
    }

    public boolean way(int[][] map, int i, int j) {
        if(map[i][j]==1){
            return false;
        }else if (map[i][j] == 0) {
            map[i][j]=2;
            if (way(map, i-1, j)) {//向左走
                return true;
            } else if (way(map, i+1, j)) {//向右走
                return true;
            } else if (way(map, i, j-1)) {//向上走
                return true;
            } else if (way(map, i, j+1)) {//向下走
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }
}
