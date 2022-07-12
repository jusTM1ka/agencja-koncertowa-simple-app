package Models;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Operator")
public class Operator extends  Pracownik {
    private String wymaganiaSprzetowe;
    private List<Koncert> koncerty;

    public Operator(String imie, String nazwisko, int pesel, String adresZamieszkania, String wymaganiaSprzetowe, double wysokoscWynagrodzenia, int stazPracy, int nrTelefonu) {
        super(imie, nazwisko, pesel, adresZamieszkania, wysokoscWynagrodzenia, stazPracy, nrTelefonu);
        this.wymaganiaSprzetowe = wymaganiaSprzetowe;
        this.koncerty = new ArrayList<>();
    }

    public Operator() {

    }

    @OneToMany(
            mappedBy = "operator",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Koncert> getKoncerty() {
        return koncerty;
    }

    public void setKoncerty(List<Koncert> koncerty) {
        this.koncerty = koncerty;
    }

    @Basic
    public String getWymaganiaSprzetowe() {
        return wymaganiaSprzetowe;
    }

    public void setWymaganiaSprzetowe(String wymaganiaSprzetowe) {
        this.wymaganiaSprzetowe = wymaganiaSprzetowe;
    }
}
