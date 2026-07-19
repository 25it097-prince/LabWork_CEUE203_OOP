public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked=0;

    CinemaShow(String title, int capacity)
    {
        this.title=title;
        this.capacity = capacity;
        this.seatsAvailable=capacity;
    }
    CinemaShow(String title)
    {
        this(title,100);
    }
    boolean book(int n)
    {
        if(n<=seatsAvailable)
        {
            seatsAvailable-=n;
            totalBooked+=n;
            return true;
        }
        else
        {
            return false;
        }
    }
    void cancel(int n)
    {
        seatsAvailable += n;
        if (seatsAvailable > capacity) {
            seatsAvailable = capacity;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args)
    {

        CinemaShow c1=new CinemaShow("xyz");
        CinemaShow c2=new CinemaShow("xyz",50);

        c1.book(10);
        System.out.println(c1.getTotalBooked()+" Seats Booked successfully");
        System.out.println(c1.getSeatsAvailable()+" Seats Available");
        c1.book(50);
        System.out.println(c1.getTotalBooked()+" Seats Booked successfully");
        System.out.println(c1.getSeatsAvailable()+" Seats Available");
        c1.cancel(30);
        System.out.println("Seats canceled successfully");
        System.out.println("Remaining Seats :"+ c1.getSeatsAvailable());
    }
}
