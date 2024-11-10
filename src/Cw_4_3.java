import java.awt.*;

class Prostokat1 extends Rectangle {

    Prostokat1(int szerokosc, int wysokosc, Point wierzcholek) {
        super(wierzcholek.x, wierzcholek.y, szerokosc, wysokosc);
    }

    Prostokat1(Point wierzcholek, int dlugosc, int szerokosc) {
        super(wierzcholek.x, wierzcholek.y, szerokosc, dlugosc);
    }

    void info() {
        System.out.println(this);
    }

    public boolean przylega(Prostokat1 prostokat) {
        boolean lewy = (this.x + this.width == prostokat.x) &&
                (this.y < prostokat.y + prostokat.height) &&
                (this.y + this.height > prostokat.y);

        boolean prawy = (prostokat.x + prostokat.width == this.x) &&
                (prostokat.y < this.y + this.height) &&
                (this.y < prostokat.y + prostokat.height);

        boolean gorny = (this.y + this.height == prostokat.y) &&
                (this.x < prostokat.x + prostokat.width) &&
                (this.x + this.width > prostokat.x);

        boolean dolny = (prostokat.y + prostokat.height == this.y) &&
                (this.x < prostokat.x + prostokat.width) &&
                (this.x + this.width > prostokat.x);

        return lewy || prawy || gorny || dolny;
    }
}

public class Cw_4_3 {
    public static void main(String[] args) {
        Prostokat1 p1 = new Prostokat1(new Point(0, 0), 4, 4);
        Prostokat1 p2 = new Prostokat1(new Point(4, 0), 4, 4);
        System.out.println("Test 1: Przyleganie z lewej strony - " + p1.przylega(p2)); // Oczekiwane: true

        Prostokat1 p3 = new Prostokat1(new Point(0, 4), 4, 4);
        System.out.println("Test 2: Przyleganie z góry - " + p1.przylega(p3)); // Oczekiwane: true

        Prostokat1 p4 = new Prostokat1(new Point(10, 10), 2, 2);
        System.out.println("Test 3: Nie przylegają - " + p1.przylega(p4)); // Oczekiwane: false

        Prostokat1 p5 = new Prostokat1(new Point(0, 0), 4, 4);
        Prostokat1 p6 = new Prostokat1(new Point(4, 0), 4, 4);
        System.out.println("Test 4: Przyleganie z prawej strony - " + p5.przylega(p6)); // Oczekiwane: true

        Prostokat1 p7 = new Prostokat1(new Point(0, 0), 4, 4);
        Prostokat1 p8 = new Prostokat1(new Point(0, 4), 4, 4);
        System.out.println("Test 5: Przyleganie z dołu - " + p7.przylega(p8)); // Oczekiwane: true
    }
}
