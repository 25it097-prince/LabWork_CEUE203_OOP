public abstract class Media {
    protected String title;
    protected int id;
    protected int lateDays;

    public Media(String title, int id, int lateDays) {
        this.title = title;
        this.id = id;
        this.lateDays = lateDays;
    }

    public abstract double lateFee();
}

class Movie extends Media {

    public Movie(String title, int id, int lateDays) {
        super(title, id, lateDays);
    }

    @Override
    public double lateFee() {
        return lateDays * 10;
    }
}

class Book extends Media {

    public Book(String title, int id, int lateDays) {
        super(title, id, lateDays);
    }

    @Override
    public double lateFee() {
        return lateDays * 5;
    }
}

class Game extends Media {

    public Game(String title, int id, int lateDays) {
        super(title, id, lateDays);
    }

    @Override
    public double lateFee() {
        return lateDays * 15;
    }
}
