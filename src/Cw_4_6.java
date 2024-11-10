import java.io.*;

class Osoba1 implements Serializable {
    String imie;
    String nazwisko;
    int rokUrodzenia;

    Osoba1(String imie, String nazwisko, int rokUrodzenia) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rokUrodzenia = rokUrodzenia;
    }

    Osoba1(BufferedReader br) {
        try {
            System.out.print("imie: ");
            this.imie = br.readLine();

            System.out.print("nazwisko: ");
            this.nazwisko = br.readLine();

            while (true) {
                try {
                    System.out.print("rok urodzenia: ");
                    this.rokUrodzenia = Integer.parseInt(br.readLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Błąd: rok urodzenia musi być liczbą całkowitą. Spróbuj ponownie.");
                }
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas odczytu danych: " + e.getMessage());
        }
    }
}

class DowodOsobisty1 implements Serializable
{
    Osoba1 posiadacz;
    String numer;

    DowodOsobisty1(BufferedReader br)
    {
        try
        {
            this.posiadacz=new Osoba1(br);

            System.out.print("numer do: ");
            this.numer=br.readLine();
        }
        catch(IOException e){
            System.out.println("Błąd podczas odczytu numeru dowodu: " + e.getMessage());
        }
    }

    public String toString()
    {
        return "<do:> "+posiadacz.toString()+" "+this.numer;
    }

    void info()
    {
        System.out.println(this);
    }
}

public class Cw_4_6
{
    public static void main(String[] args)
    {
        System.out.println("-- do zapisu --");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        DowodOsobisty1 z= new DowodOsobisty1(br);
        z.info();

        try
        {
            ObjectOutputStream outp=new ObjectOutputStream(new FileOutputStream("plik.dat"));
            outp.writeObject(z);
            outp.close();
        }catch (FileNotFoundException e) {
            System.out.println("Plik nie został znaleziony lub nie można go utworzyć: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisywania do pliku: " + e.getMessage());
        }




        System.out.println("\n-- z pliku --");
        ObjectInputStream inp;

        try
        {
            inp=new ObjectInputStream(new FileInputStream("plik.dat"));
            Object o=inp.readObject();
            DowodOsobisty1 x=(DowodOsobisty1)o;
            inp.close();
            x.info();
        }catch (FileNotFoundException e) {
            System.out.println("Plik nie został znaleziony. Sprawdź, czy plik 'plik.dat' istnieje.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas odczytywania z pliku: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Nie znaleziono klasy dla odczytywanego obiektu: " + e.getMessage());
        }
    }
}
