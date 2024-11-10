abstract class Figura //nie mozna tworzyc instancji tej klasy
{
    abstract double pole(); //metoda abstrakcyjna
    abstract double obwod();

    void info()
    {
        System.out.println(this);
    }
}
class Okrag extends Figura
{
    double promien;

    Okrag(double promien)
    {
        this.promien=promien;
    }

    double pole()
    {
        return 3.14*promien*promien;
    }

    double obwod()
    {
        return 2*3.14*promien;
    }

    public String toString()
    {
        return "okrag o pr. "+promien;
    }
}

class Prostokat extends Figura
{
    double dlugosc;
    double szerokosc;

    Prostokat(double dlugosc,double szerokosc)
    {
        this.dlugosc=dlugosc;
        this.szerokosc=szerokosc;
    }

    double pole()
    {
        return dlugosc*szerokosc;
    }

    double obwod()
    {
        return 2*dlugosc+2*szerokosc;
    }

    public String toString()
    {
        return "prostokat o wym. "+dlugosc+" na "+szerokosc;
    }
}

class Trojkat extends Figura
{
    double bok_1 ;
    double bok_2 ;
    double bok_3 ;

    public Trojkat(double bok_1, double bok_2, double bok_3) {
        this.bok_1 = bok_1;
        this.bok_2 = bok_2;
        this.bok_3 = bok_3;
    }


    double pole()
    {
        double p = obwod();

        return Math.sqrt(p * (p - bok_1 )*(p - bok_2 )*(p - bok_3 ));
    }

    double obwod()
    {
        return bok_1 + bok_2 + bok_3;
    }

    public String toString()
    {
        return "trójkąt o wym. "+bok_1+" , "+bok_2 + " , " + bok_3;
    }
}

class Trapez extends Figura
{
    double k_podstawa ;
    double d_podstawa;
    double wysokosc;
    double bok_c;
    double bok_d;

    public Trapez(double k_podstawa, double d_podstawa, double wysokosc, double bok_c, double bok_d) {
        this.k_podstawa = k_podstawa;
        this.d_podstawa = d_podstawa;
        this.wysokosc = wysokosc;
        this.bok_c = bok_c;
        this.bok_d = bok_d;
    }


    double pole()
    {
        return (k_podstawa+d_podstawa) * wysokosc / 2;
    }

    double obwod()
    {
        return bok_c + bok_d + k_podstawa + d_podstawa ;
    }

    public String toString()
    {
        return "trapez o wym. "+k_podstawa+" , "+d_podstawa + " , " + bok_d + " , " + bok_c ;
    }
}

public class Cw_4_4
{
    public static void main(String[] args)
    {
        Figura z = new Okrag(2);
        z.info();

        Figura[] a = {
                new Prostokat(3, 5),
                new Okrag(8),
                new Trojkat(3, 4, 5),
                new Trapez(3, 5, 4, 4, 4),
        };

        Figura x;
        double suma = 0;

        for (int i = 0; i < a.length; i++)
        {
            x = a[i];
            x.info();
            suma += x.pole();
        }

        System.out.println("Suma pól figur: " + suma);
    }
}
