public class Thermostat {
    private String location;
    private int temp;
    private static final int MIN=16,MAX=30;
    private static int activeCount=0;

    Thermostat(String location,int startTemp)
    {
        this.location=location;
        if(startTemp>=MIN && startTemp<=MAX)
        {
            temp=startTemp;
        }
        else
        {
            temp=22;
        }
        activeCount++;
    }
    Thermostat(String location) {
        this(location, 22);
    }
    public int getTemp() {
        return temp;
    }
    public void raise()
    {
        if(temp<MAX) {
            temp++;
            activeCount++;
        }
        else
            System.out.println("Already at Maximum(30)");
    }
    public void lower()
    {
       int  startTemp=getTemp();
        if(temp>MIN) {
            temp--;
            activeCount--;
        }
        else
            System.out.println("Already at Minimum(16)");
    }
    public static int getActiveCount() {
        return activeCount;
    }

    public static void main(String[] args)
    {
        Thermostat t1 = new Thermostat("xyz");
        Thermostat t2 = new Thermostat("abc",20);
        for(int i=0; i<10; i++)
        {
            t2.raise();
            System.out.println("TEMP Raising: " + t2.getTemp());
        }
        for(int i=0; i<20; i++)
        {
            t2.lower();
            System.out.println("TEMP Lower: " + t2.getTemp());
        }
    }
}
