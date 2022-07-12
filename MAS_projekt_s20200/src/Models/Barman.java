package Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name ="Barman")
public class Barman extends Pracownik{
    private List<String> znaneKoktajle;
    private Set<Koncert> koncerty;
    public Barman(){

    }
    public Barman(String imie, String nazwisko, int pesel, String adresZamieszkania, String koktajl,double wysokoscWynagrodzenia,int stazPracy, int numerTelefonu ) {
        super(imie, nazwisko, pesel, adresZamieszkania, wysokoscWynagrodzenia, stazPracy,numerTelefonu);
        znaneKoktajle = new ArrayList<>();
        znaneKoktajle.add(koktajl);
        this.koncerty = new HashSet<>();
    }

    @ManyToMany(mappedBy = "barmani")
    public Set<Koncert> getKoncerty() {
        return koncerty;
    }

    public void setKoncerty(Set<Koncert> koncerty) {
       this.koncerty = koncerty;
    }

    @ElementCollection
    public List<String> getZnaneKoktajle(){
        return znaneKoktajle;
    }
    public void setZnaneKoktajle(List<String> znaneKoktajle) {
        this.znaneKoktajle = znaneKoktajle;
    }


    public void dodajKoktajl(String koktajl){
        znaneKoktajle.add(koktajl);
    }

}
