import java.io.IOException;
import java.util.*;

public class task_2 {


    private static int INF = 200000;
    private static Scanner in;
    private int n; //amount of cities
    private ArrayList<Integer> city[];
    private ArrayList<Integer> cost[];
    private Map<String, Integer> mapCity = new HashMap<>();
    private boolean used[];
    private int price[];
    private int result;


    private void dijkstra(int start, int stop) {   // Dijkstra's algorithm
        price[start] = 0; // set the distance to the start city
        for (int i = 0; i < n; ++i) {
            int v = -1;
            int priceV = INF;
            for (int j = 0; j < n; ++j) {   // search the city with an uncertain distance
                if (used[j]) {
                    continue;
                }
                if (priceV < price[j]) {
                    continue;
                }
                v = j;
                priceV = price[j];
            }

            for (int k = 0; k < this.city[v].size(); ++k) {   // Compare prices for cities - neighbors
                int u = this.city[v].get(k);
                int costU = cost[v].get(k);

                if (price[v] + costU < price[u]) {
                    price[u] = price[v] + costU;
                }
                if (u == stop) {   //  stop if the city is a destination
                    result = price[u];
                }
            }
            used[v] = true;   // mark the city if distance was found
        }
        System.out.println();
        System.out.println(result);   // output the result to screen
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
        in = new Scanner(System.in);   // input the numbers of paths to find
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

        for (int i = 0; i < paths; ++i) {   // the input of routes
            in = new Scanner(System.in);
            System.out.println("Input paths to find (Start_City Destination_City)");
            String path = in.nextLine();
            int q = path.indexOf(" ");

            startCity[i] = path.substring(0, q);   // read the starting city from the string path
            destinationCity[i] = path.substring(q + 1);   // ead the destination city from the string path

            String s = startCity[i];
            String d = destinationCity[i];

            numStartCity[i] = mapCity.get(s);   // searching numbers of the city by the name
            numDestinationCity[i] = mapCity.get(d);


        }
        for (int j = 0; j < paths; ++j) {
            used = new boolean[n];      // array of labels for visited cities
            Arrays.fill(used, false);

            price = new int[n];         // storage array for prices
            Arrays.fill(price, INF);

            dijkstra(numStartCity[j], numDestinationCity[j]);   // method call pricing
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

    // redefinition of the methods equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        task_2 task_2 = (task_2) o;

        if (n != task_2.n) return false;
        if (result != task_2.result) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(city, task_2.city)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(cost, task_2.cost)) return false;
        if (mapCity != null ? !mapCity.equals(task_2.mapCity) : task_2.mapCity != null) return false;
        if (!Arrays.equals(used, task_2.used)) return false;
        return Arrays.equals(price, task_2.price);

    }

    @Override
    public int hashCode() {
        int result1 = n;
        result1 = 31 * result1 + Arrays.hashCode(city);
        result1 = 31 * result1 + Arrays.hashCode(cost);
        result1 = 31 * result1 + (mapCity != null ? mapCity.hashCode() : 0);
        result1 = 31 * result1 + Arrays.hashCode(used);
        result1 = 31 * result1 + Arrays.hashCode(price);
        result1 = 31 * result1 + result;
        return result1;
    }
}