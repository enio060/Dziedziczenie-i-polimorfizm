
class Osoba
{
    String imie ;
    String nazwisko ;
    long pesel;

    @Override
    public String toString() {
        return imie + " " + nazwisko + " PESEL: " + pesel;
    }

    public Osoba(String imie, String nazwisko,  long pesel) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
interface Przeszukiwalne
{
    boolean czyPasuje(String wzorzec);
}


abstract class Dokument implements Przeszukiwalne
{
    protected Osoba wlasciciel ;

    public Dokument(Osoba wlasciciel) {
        this.wlasciciel = wlasciciel;
    }

    @Override
    public boolean czyPasuje(String wzorzec) {
        return wlasciciel.nazwisko.equalsIgnoreCase(wzorzec);
    }
}
class Paszport extends Dokument {
    public Paszport(Osoba wlasciciel) {
        super(wlasciciel);
    }

    @Override
    public String toString() {
        return "Paszport{" +
                "wlasciciel=" + wlasciciel +
                "} " + super.toString();
    }
}
class DowodOsobisty extends Dokument {
    public DowodOsobisty(Osoba wlasciciel) {
        super(wlasciciel);
    }

}
public class Cw_4_5
{
    public static void main(String[] args)
    {
        Osoba osoba1 = new Osoba("Jan", "Kowalski", 12345678901L);
        Osoba osoba2 = new Osoba("Edyta", "Gorniak", 98765432109L);

        Dokument[] bazaDanych = {
                new Paszport(osoba1),
                new DowodOsobisty(osoba2),
                new Paszport(osoba2)
        };

        String wzorzec = "Gorniak";

        for(Dokument z : bazaDanych) {
            if(z.czyPasuje(wzorzec)) {
                System.out.println("Znaleziono: " + z);
            }
        }
    }
}
