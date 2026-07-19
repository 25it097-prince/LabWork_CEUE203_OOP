public class ParkingLot {
    private int twoWheelers,fourWheelers;
    private final int twoCap;
    private final int fourCap;
    private static long revenue=0;

    public ParkingLot(int twoCap, int fourCap) {
        this.twoCap = twoCap;
        this.fourCap = fourCap;
        this.twoWheelers = 0;
        this.fourWheelers = 0;
    }
    void park(String type)
    {
        if (type.equals("two")) {
            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
            } else {
                System.out.println("Full");
            }
        }
        if (type.equals("four")) {
            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
            } else {
                System.out.println("Full");
            }
        }
    }
    void leave(String type)
    {
        if(type.equals("two"))
        {
            if(twoWheelers>0)
                 twoWheelers--;
        }
        if(type.equals("four"))
        {
            if(fourWheelers>0)
                fourWheelers--;
        }
    }
    long Revenue()
    {
        revenue=20*(twoWheelers)+40*(fourWheelers);
        return revenue;
    }
    void display()
    {
        System.out.println("Two Wheelers : "+twoWheelers);
        System.out.println("Four Wheelers : "+fourWheelers);
        System.out.println("Total Revenue : "+Revenue());

    }
    public static void main(String[] args)
    {
        ParkingLot p1=new ParkingLot(5,4);
        p1.park("four");
        p1.park("four");
        p1.park("two");
        p1.park("two");
        p1.display();
    }
}
