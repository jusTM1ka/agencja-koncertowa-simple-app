package Models;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name="ManagerZespolu")
public class ManagerZespolu extends  Osoba{
    private String nazwaZespolu;
    private List<Umowa> umowy;
    public ManagerZespolu(String imie, String nazwisko, int pesel, String adresZamieszkania, String nazwaZespolu) {
        super(imie, nazwisko, pesel, adresZamieszkania);
        this.nazwaZespolu = nazwaZespolu;
        this.umowy = new ArrayList<>();
    }
    public ManagerZespolu(){

    }
    @OneToMany(
            mappedBy = "manager",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Umowa> getUmowy() {
        return umowy;
    }

    public void setUmowy(List<Umowa> umowy) {
        this.umowy = umowy;
    }

    @Basic
    public String getNazwaZespolu() {
        return nazwaZespolu;
    }
    public void setNazwaZespolu(String nazwaZespolu) {
        this.nazwaZespolu = nazwaZespolu;
    }
}
