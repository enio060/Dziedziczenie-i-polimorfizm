import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

interface Oprocentowanie {
    BigDecimal getOprocentowanie();
}

interface Podatek {
    BigDecimal getPodatek();
}

interface Wyszukiwalny {
    boolean pasujeDoWzorca(String imie, String nazwisko);
}

abstract class Konto implements Oprocentowanie, Podatek,Wyszukiwalny {
    protected BigDecimal aktulanyStanKonta;
    protected String imie;
    protected String nazwisko;
    protected Long pesel;

    public Konto(BigDecimal aktulanyStanKonta, String imie, String nazwisko, Long pesel) {
        this.aktulanyStanKonta = aktulanyStanKonta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
    }

    public BigDecimal getAktualnyStanKonta() {
        return aktulanyStanKonta;
    }

    @Override
    public String toString() {
        return "Konto{" +
                "aktulanyStanKonta=" + aktulanyStanKonta +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel=" + pesel +
                '}';
    }

    public void wplata(BigDecimal kwota) {
        this.aktulanyStanKonta = this.aktulanyStanKonta.add(kwota);
        System.out.println("Wplata: " + kwota + ". Aktualny stan konta: " + this.aktulanyStanKonta);
    }

    public void wyplata(BigDecimal kwota) {
        if (kwota.compareTo(BigDecimal.ZERO) > 0 && this.aktulanyStanKonta.compareTo(kwota) >= 0) {
            this.aktulanyStanKonta = this.aktulanyStanKonta.subtract(kwota);
            System.out.println("Wypłata: " + kwota + ". Aktualny stan konta: " + this.aktulanyStanKonta);
        } else {
            throw new IllegalArgumentException("Niewystarczające środki na koncie lub nieprawidłowa kwota.");
        }
    }

    public abstract BigDecimal obliczStanPoMiesiacach(int miesiace);

    @Override
    public boolean pasujeDoWzorca(String imie, String nazwisko) {
        return this.imie.equals(imie) && this.nazwisko.equals(nazwisko);
    }
}

class KontoBankowe extends Konto {
    private final BigDecimal oplataMiesieczna = BigDecimal.valueOf(10);

    public KontoBankowe(BigDecimal aktulanyStanKonta, String imie, String nazwisko, Long pesel) {
        super(aktulanyStanKonta, imie, nazwisko, pesel);
    }

    @Override
    public BigDecimal getOprocentowanie() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getPodatek() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal obliczStanPoMiesiacach(int miesiace) {
        BigDecimal saldo = this.aktulanyStanKonta;
        for (int i = 0; i < miesiace; i++) {
            saldo = saldo.subtract(oplataMiesieczna);
        }
        return saldo;
    }
}

class KontoOszczednosciowe extends Konto {
    private final BigDecimal oprocentowanie = BigDecimal.valueOf(0.02);
    private final BigDecimal podatek = BigDecimal.valueOf(0.19);
    private final BigDecimal oplataMiesieczna = BigDecimal.valueOf(5);

    public KontoOszczednosciowe(BigDecimal aktulanyStanKonta, String imie, String nazwisko, Long pesel) {
        super(aktulanyStanKonta, imie, nazwisko, pesel);
    }

    @Override
    public BigDecimal getOprocentowanie() {
        return oprocentowanie;
    }

    @Override
    public BigDecimal getPodatek() {
        return podatek;
    }

    @Override
    public BigDecimal obliczStanPoMiesiacach(int miesiace) {
        BigDecimal saldo = this.aktulanyStanKonta;
        for (int i = 0; i < miesiace; i++) {
            BigDecimal odsetki = saldo.multiply(oprocentowanie);
            BigDecimal podatekOdOdsetek = odsetki.multiply(podatek);
            saldo = saldo.add(odsetki.subtract(podatekOdOdsetek));
            saldo = saldo.subtract(oplataMiesieczna);
        }
        return saldo.setScale(2,RoundingMode.HALF_UP);
    }
}

class LokataBankowa extends Konto {
    private final BigDecimal oprocentowanie;
    private final int okres;

    public LokataBankowa(BigDecimal aktulanyStanKonta, String imie, String nazwisko, Long pesel, BigDecimal oprocentowanie, int okres) {
        super(aktulanyStanKonta, imie, nazwisko, pesel);
        this.oprocentowanie = oprocentowanie;
        this.okres = okres;
    }

    @Override
    public BigDecimal getOprocentowanie() {
        return oprocentowanie;
    }

    @Override
    public BigDecimal getPodatek() {
        return BigDecimal.valueOf(0.19);
    }

    @Override
    public BigDecimal obliczStanPoMiesiacach(int miesiace) {
        if (miesiace < okres) {
            throw new IllegalArgumentException("Lokata jeszcze nie zakończona!");
        }
        BigDecimal saldo = this.aktulanyStanKonta;
        BigDecimal odsetki = saldo.multiply(oprocentowanie);
        BigDecimal podatekOdOdsetek = odsetki.multiply(getPodatek());
        saldo = saldo.add(odsetki.subtract(podatekOdOdsetek));
        return saldo.setScale(2,RoundingMode.HALF_UP);
    }
}

public class Cw_4_7 {
    public static void main(String[] args) {
        List<Konto> konta = new ArrayList<>();
        konta.add(new KontoOszczednosciowe(BigDecimal.valueOf(1000), "Joanna", "Nowak", 12345678901L));
        konta.add(new LokataBankowa(BigDecimal.valueOf(2000), "Anna", "Nowak", 10987654321L, BigDecimal.valueOf(0.03), 12));
        konta.add(new KontoOszczednosciowe(BigDecimal.valueOf(5000), "Joanna", "Nowak", 12345678901L));

        String imieSzukane = "Joanna";
        String nazwiskoSzukane = "Nowak";

        System.out.println("Konta należące do " + imieSzukane + " " + nazwiskoSzukane + ":");
        for (Konto konto : konta) {
            BigDecimal stanPoMiesiacach = konto.obliczStanPoMiesiacach(12);
            System.out.println(konto.imie + " " + konto.nazwisko + " - Stan konta po 12 miesiącach: " + stanPoMiesiacach);
        }
    }
}
