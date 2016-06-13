import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class task_2 {


    private static int INF = 200000;
    private int s; //number of test
    private int n; //amount of cities
    private ArrayList<Integer> city[]; //список смежности
    private ArrayList<Integer> cost[]; //вес ребра в орграфе
    private String[] startCity;
    private String[] goalCity;
    private Integer[] numStartCity;
    private Integer[] numGoalCity;
    private Map<String, Integer> mapCity = new HashMap<>();
    private boolean used[]; //массив для хранения информации о пройденных и не пройденных вершинах
    private int dist[]; //массив для хранения расстояния от стартовой вершины
    //массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины
    private int pred[];
    private int start; //стартовая вершина, от которой ищется расстояние до всех других

    private BufferedReader cin;
    private PrintWriter cout;
    private StringTokenizer tokenizer;

    //процедура запуска алгоритма Дейкстры из стартовой вершины
    private void dejkstra(int city) {
        dist[city] = 0; //кратчайшее расстояние до стартовой вершины равно 0
        for (int iter = 0; iter < n; ++iter) {
            int v = -1;
            int distV = INF;
            //выбираем вершину, кратчайшее расстояние до которого еще не найдено
            for (int i = 0; i < n; ++i) {
                if (used[i]) {
                    continue;
                }
                if (distV < dist[i]) {
                    continue;
                }
                v = i;
                distV = dist[i];
            }
            //рассматриваем все дуги, исходящие из найденной вершины
            for (int i = 0; i < this.city[v].size(); ++i) {
                int u = this.city[v].get(i);
                int weightU = cost[v].get(i);
                //релаксация вершины
                if (dist[v] + weightU < dist[u]) {
                    dist[u] = dist[v] + weightU;
                    pred[u] = v;
                }
            }
            //помечаем вершину v просмотренной, до нее найдено кратчайшее расстояние
            used[v] = true;
        }
    }

    //процедура считывания входных данных с консоли
    private void readData() throws IOException {
//        cin = new BufferedReader(new InputStreamReader(System.in));
//        cout = new PrintWriter(System.out);
//        tokenizer = new StringTokenizer(cin.readLine());

        Scanner in = new Scanner(System.in);
        System.out.println("Input the number of test");
        s = in.nextInt();

        in = new Scanner(System.in);
        System.out.println("Input the number of cities");
        n = in.nextInt();

        city = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            city[i] = new ArrayList<>();
        }
        cost = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            cost[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            in = new Scanner(System.in);
            System.out.println("Input name of City # " + i + 1);
            String nameCity = in.nextLine();

            mapCity.put(nameCity, i);

            in = new Scanner(System.in);
            System.out.println("Input the number of neighbours ");
            int m = in.nextInt();
            for (int j = 0; i < m; ++j) {
                in = new Scanner(System.in);
                System.out.println("Input the neighbour # " + j + 1);
                city[i].add(in.nextInt());
                in = new Scanner(System.in);
                System.out.println("Input the cost of travel to the neighboring city #" + j + 1);
                cost[i].add(in.nextInt());
            }
        }
        in = new Scanner(System.in);
        System.out.println("Input [the number of paths to find ");
        int paths = in.nextInt();
        for (int k = 0; k < paths; ++k) {
            in = new Scanner(System.in);
            System.out.println("Input [the number of paths to find ");
            String path = in.nextLine();
            int q = path.indexOf(" ");

            startCity[k] = path.substring(0, q);
            goalCity[k] = path.substring(q + 1);

            numStartCity[k] = mapCity.get(startCity[k]);
            numGoalCity[k] = mapCity.get(goalCity[k]);
        }


//        start = Integer.parseInt(tokenizer.nextToken()) - 1;


        used = new boolean[n];
        Arrays.fill(used, false);

        pred = new int[n];
        Arrays.fill(pred, -1);

        dist = new int[n];
        Arrays.fill(dist, INF);

    }

    //процедура восстановления кратчайшего пути по массиву предком
    void printWay(int v) {
        if (v == -1) {
            return;
        }
        printWay(pred[v]);
        cout.print((v + 1) + " ");
    }

    //процедура вывода данных в консоль
    private void printData() throws IOException {
        for (int v = 0; v < n; ++v) {
            if (dist[v] != INF) {
                cout.print(dist[v] + " ");
            } else {
                cout.print("-1 ");
            }
        }
        cout.println();
        for (int v = 0; v < n; ++v) {
            cout.print((v + 1) + ": ");
            if (dist[v] != INF) {
                printWay(v);
            }
            cout.println();
        }

        cin.close();
        cout.close();
    }

    private void run() throws IOException {
        readData();
        dejkstra(start);
        printData();

        cin.close();
        cout.close();
    }

    public static void main(String[] args) throws IOException {
        task_2 solution = new task_2();
        solution.run();
    }
    public String name;

    public int hashCode(){
        return name.hashCode();
    }

    public boolean equals(Object o){
        if(o instanceof task_2){
            return name.equals( ((task_2)o).name );
        }
        return false;
    }
}