package study;

import java.util.*;

public class BaekJoon2667 {

    static int n;
    static int cnt;
    static int totalCount;
    static int[][] map;
    static boolean[][] check;
    static List<Integer> answers = new ArrayList<>();
    static int[] _x = {-1, 1, 0, 0};
    static int[] _y = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];
        check = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String input = sc.next();
            for (int j = 0; j < input.length(); j++) {
                char c = input.charAt(j);
                map[i][j] = Character.getNumericValue(c);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !check[i][j]) {
                    calculation(i, j);
                }
            }
        }

        calculation(0, 0);
        Collections.sort(answers);

        System.out.println(cnt);
        for (Integer answer : answers) {
            System.out.println(answer);
        }
    }

    static void calculation(int a, int b) {
        check[a][b] = true;

        for (int i = 0; i < 4; i++) {
            int x = a + _x[i];
            int y = b + _y[i];
            if (x >= 0 && y >= 0 && x < n && y < n) {
                if (map[x][y] == 1 && !check[x][y]) {
                    cnt++;
                    calculation(x, y);
                }
            }
        }
        addAndClear();
    }

    static void addAndClear() {
        answers.add(cnt);
        cnt = 0;
    }
}
