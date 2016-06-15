import java.io.IOException;
import java.util.*;

public class task_2 {


    private static int INF = 200000;
    private static Scanner in;
    private int n; //amount of cities
    private ArrayList<Integer> city[]; //список смежности
    private ArrayList<Integer> cost[]; //вес ребра в орграфе
    private Map<String, Integer> mapCity = new HashMap<>();
    private boolean used[]; //массив для хранения информации о пройденных и не пройденных вершинах
    private int price[]; //массив для хранения расстояния от стартовой вершины
    private int result;


    //процедура запуска алгоритма Дейкстры из стартовой вершины
    private void dijkstra(int start, int stop) {
        price[start] = 0; //кратчайшее расстояние до стартовой вершины равно 0
        for (int i = 0; i < n; ++i) {
            int v = -1;
            int priceV = INF;
            //выбираем вершину, кратчайшее расстояние до которого еще не найдено
            for (int j = 0; j < n; ++j) {
                if (used[j]) {
                    continue;
                }
                if (priceV < price[j]) {
                    continue;
                }
                v = j;
                priceV = price[j];
            }

            //рассматриваем все дуги, исходящие из найденной вершины
            for (int k = 0; k < this.city[v].size(); ++k) {
                int u = this.city[v].get(k);
                int costU = cost[v].get(k);
                //релаксация вершины
                if (price[v] + costU < price[u]) {
                    price[u] = price[v] + costU;
                }
                if (u == stop) {
                    result = price[u];
                }
            }

            //помечаем вершину v просмотренной, до нее найдено кратчайшее расстояние
            used[v] = true;
        }
        System.out.println(result);
        System.out.println();
    }

    private void readData() throws IOException {    // the reading data from console

        in = new Scanner(System.in);
        System.out.println("Input the number of cities for test");
        n = in.nextInt();
//        n = 4;

        city = new ArrayList[n];    // creating array (of ArrayList) of cities for keeping neighbors
        for (int i = 0; i < n; ++i) {
            city[i] = new ArrayList<>();
        }
        cost = new ArrayList[n];    // creating array (of ArrayList) for keeping fares for each neighbor
        for (int i = 0; i < n; ++i) {
            cost[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; ++i) {    // model input
            in = new Scanner(System.in);
            System.out.println("Input name of City # " + (i + 1));
            String nameCity = in.nextLine();

            mapCity.put(nameCity, i);    // map for keeping name and numbers of cities

            in = new Scanner(System.in);
            System.out.println("Input the number of neighbours ");
            int m = in.nextInt();
            for (int j = 0; j < m; ++j) {
                in = new Scanner(System.in);
                System.out.println("Input the # of neighbour " + (j + 1));
                city[i].add(in.nextInt() - 1);
                in = new Scanner(System.in);
                System.out.println("Input the cost of travel to the neighboring city #" + (j + 1));
                cost[i].add(in.nextInt());
            }
        }

//        mapCity.put("gda", 0);
//        mapCity.put("byd", 1);
//        mapCity.put("tor", 2);
//        mapCity.put("war", 3);
//
//        city[0].add(1);
//        cost[0].add(1);
//        city[0].add(2);
//        cost[0].add(3);
//
//        city[1].add(0);
//        cost[1].add(1);
//        city[1].add(2);
//        cost[1].add(1);
//        city[1].add(3);
//        cost[1].add(4);
//
//        city[2].add(0);
//        cost[2].add(3);
//        city[2].add(1);
//        cost[2].add(1);
//        city[2].add(3);
//        cost[2].add(1);
//
//        city[3].add(1);
//        cost[3].add(4);
//        city[3].add(2);
//        cost[3].add(1);
    }

    private void readPath() throws IOException {
        in = new Scanner(System.in);   //input the numbers of paths to find
        System.out.println("Input the numbers of paths to find ");
        int paths = in.nextInt();
//        int paths = 1;

        String[] startCity;
        startCity = new String[paths];
        String[] destinationCity;
        destinationCity = new String[paths];

        Integer[] numStartCity;
        numStartCity = new Integer[paths];
        Integer[] numDestinationCity;
        numDestinationCity = new Integer[paths];

        for (int i = 0; i < paths; ++i) {
            in = new Scanner(System.in);
            System.out.println("Input paths to find (Start_City Destination_City)");
            String path = in.nextLine();
            int q = path.indexOf(" ");

            startCity[i] = path.substring(0, q);
            destinationCity[i] = path.substring(q + 1);

            String s = startCity[i];
            String d = destinationCity[i];

            numStartCity[i] = mapCity.get(s);
            numDestinationCity[i] = mapCity.get(d);


        }
        for (int j = 0; j < paths; ++j) {
            used = new boolean[n];      // array of labels for visited cities
            Arrays.fill(used, false);

            price = new int[n];         // storage array for prices
            Arrays.fill(price, INF);

            dijkstra(numStartCity[j], numDestinationCity[j]);
        }
    }


    public static void main(String[] args) throws IOException {

        task_2 solution = new task_2();

        int s; //number of test
        in = new Scanner(System.in);
        System.out.println("Input the number of test");
        s = in.nextInt();
//        s = 1;
        for (int count = 1; count <= s; ++count) {

            solution.readData();
            solution.readPath();
        }
    }

    public String name;

    public int hashCode() {
        return name.hashCode();
    }

    public boolean equals(Object o) {
        if (o instanceof task_2) {
            return name.equals(((task_2) o).name);
        }
        return false;
    }
}