import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int vertex;
    static int edge;
    static int start;
    static int[][] visit;
    static boolean[] checkDfs;
    static boolean[] checkBfs;

    //출력 용
    static StringBuilder bfsSb = new StringBuilder();
    static StringBuilder dfsSb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //정점
        vertex = sc.nextInt();
        //간선
        edge = sc.nextInt();
        //탐색 시작 위치
        start = sc.nextInt();

        //visit[v1][v2] = visit[v2][v1] = 1; 으로 작성하기 위해
        visit = new int[vertex + 1][vertex + 1];

        //방문했는지 체크하는 배열
        checkDfs = new boolean[vertex + 1];
        checkBfs = new boolean[vertex + 1];

        for (int i = 0; i < edge; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();

            //간선이 연결하는 두 정점의 번호는 양방향이기 때문에
            visit[v1][v2] = visit[v2][v1] = 1;
        }

        System.out.println(dfs(start));
        System.out.println(bfs());
    }

    static String dfs(int v) {

        checkDfs[v] = true;
        dfsSb.append(v + " ");

        //테스트 케이스 1번 기준
        //현재 dfsSb에 1
        //visit[2][1]이 아래 조건에 걸림 dfsSb에 2가 추가되어 1 2
        //visit[i][2]로 반복문 시작 visit[3][2]가 조건에 걸림 dfsSb에 3이 추가되어 1 2 3
        //visit[i][3]으로 반복문 시작 visit[4][3]이 조건에 걸림 dfsSb에 4가 추가되어 1 2 3 4
        for (int i = 1; i < visit.length; i++) {
            if (visit[i][v] == 1 && !checkDfs[i]) {
                dfs(i);
            }
        }
        return dfsSb.toString();
    }

    static String bfs() {
        //탐색 시작 위치 넣어놓고 시작 위치는 더 이상 탐색하지 않아도 되기 때문에 true로 초기화
        Queue<Integer> queue = new LinkedList<>();
        checkBfs[start] = true;
        queue.add(start);
        bfsSb.append(start + " ");

        while (!queue.isEmpty()) {
            int poll = queue.poll();

            //배열 크기만큼 반복하면서
            for (int i = 1; i < visit.length; i++) {
                //ex visit[2][1]은 1이고 check[1]은 false이기 때문에 큐에 넣고 방문했기 때문에 true로 처리
                if (visit[i][poll] == 1 && !checkBfs[i]) {
                    queue.add(i);
                    checkBfs[i] = true;
                    bfsSb.append(i + " ");
                }
            }
        }
        return bfsSb.toString();
    }
}