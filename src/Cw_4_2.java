/**
 * Konstruktory klasy nie są dziedizczone
 */

import java.awt.*;

class Prostokat2 extends Rectangle {

    Prostokat2(int szerokosc, int wysokosc, Point wierzcholek) {
        super(wierzcholek.x, wierzcholek.y, szerokosc, wysokosc);
    }

    Prostokat2(Point wierzcholek, int dlugosc, int szerokosc) {
        super(wierzcholek.x, wierzcholek.y, szerokosc, dlugosc);
    }

    void info() {
        System.out.println(this);
    }
}

public class Cw_4_2 {
    public static void main(String[] args) {

        Prostokat2 a = new Prostokat2(new Point(0, 0), 3, 4);
        a.info();

        Prostokat2 b = new Prostokat2(new Point(1, 1), 2, 2);
        b.info();

        if (a.intersects(b)) {
            System.out.println("-- przecinaja sie --\n");
        } else {
            System.out.println("-- NIE przecinaja sie --\n");
        }

        a.translate(5, 3);
        a.info();

        if (a.intersects(b)) {
            System.out.println("-- przecinaja sie --\n");
        } else {
            System.out.println("-- NIE przecinaja sie --\n");
        }
    }
}
