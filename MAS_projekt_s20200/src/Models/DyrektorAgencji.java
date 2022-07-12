package Models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name="DyrektorAgencji")
public class DyrektorAgencji extends Osoba{
    private Date dataObjeciaStanowiska;
    ObiektKoncertu obiekt;
    public DyrektorAgencji(String imie, String nazwisko, int pesel, String adresZamieszkania, Date dataObjeciaStanowiska) {
        super(imie, nazwisko, pesel, adresZamieszkania);
        this.dataObjeciaStanowiska = dataObjeciaStanowiska;

    }
    public DyrektorAgencji(){

    }
    @Basic
    public Date getDataObjeciaStanowiska() {
        return dataObjeciaStanowiska;
    }
    public void setDataObjeciaStanowiska(Date dataObjeciaStanowiska) {
        this.dataObjeciaStanowiska = dataObjeciaStanowiska;
    }
}
