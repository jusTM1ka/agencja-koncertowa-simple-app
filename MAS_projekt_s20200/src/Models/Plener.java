package Models;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity(name="Plener")
public class Plener extends ObiektKoncertu{
    private String godzinaCiszyNccnej;
    private String typNawierzchni;

    public Plener( String nazwa, String adres, WlascicielObiektu wlascicielObiektu, String godzinaCiszyNccnej, String typNawierzchni) {
        super(nazwa, adres, wlascicielObiektu);
        this.godzinaCiszyNccnej = godzinaCiszyNccnej;
        this.typNawierzchni = typNawierzchni;

    }
    public Plener(){

    }
    @Basic
    public String getGodzinaCiszyNccnej() {
        return godzinaCiszyNccnej;
    }

    public void setGodzinaCiszyNccnej(String godzinaCiszyNccnej) {
        this.godzinaCiszyNccnej = godzinaCiszyNccnej;
    }
    @Basic
    public String getTypNawierzchni() {
        return typNawierzchni;
    }

    public void setTypNawierzchni(String typNawierzchni) {
        this.typNawierzchni = typNawierzchni;
    }
}
