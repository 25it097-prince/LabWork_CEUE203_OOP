import java.util.Scanner;
public class TollBooth {
    public static void main(String[] args) {
        int totaltoll = 0;
        int truck = 0, bike = 0, car = 0;

        System.out.println("Enter Vehicle(car/bike/truck/done) : ");
        Scanner sc = new Scanner(System.in);
        int toll = 0;
        String type;
        do {
            type = sc.next().toLowerCase();
            switch (type) {
                case "bike":
                    bike++;
                    toll = 20;
                    break;
                case "car":
                    car++;
                    toll = 50;
                    break;
                case "truck":
                    truck++;
                    toll = 150;
                    break;
                default:
                    toll = 0;
                    break;
            };
            totaltoll += toll;
        } while (!type.equals("done"));
        System.out.println("cars : "+ car + " bikes : "+ bike + " trucks : "+ truck);
        System.out.println("Total toll collected: " + totaltoll);
    }
    }

