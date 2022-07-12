package GUI;

import Models.*;
import org.hibernate.SessionFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.List;

public class MyFrame extends JFrame{
    private static final int WINDOW_X_SIZE =550;
    private static final int WINDOW_Y_SIZE = 700;
    private static final Pattern isValidateDate = Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
    private static final Pattern isValidateNumber = Pattern.compile("^(?:[1-9]|\\d{2,3}|[1-4]\\d{3}|5000)$");
    private JLabel img;

    private AddConcertButton addConcertButton;
    private AddConcertButton confirmDateButton;
    private AddConcertButton zatwierdzUmoweButton;
    private AddConcertButton zatwierdzOchroniarzaButton;
    private AddConcertButton zatwierdzBarmanaButton;
    private AddConcertButton potwierdzFormularzButton;
    private AddConcertButton powrotButton;

    private JLabel basicText;
    private JLabel option;
    private JLabel dateTextField;
    private JLabel nazwaUmowyTextField;
    private JLabel warunkiUmowyTextField;
    private JLabel wrongDate;
    private JLabel ochroniarzeLabel;
    private JLabel iloscUczestnikowLabel;
    private JLabel koncertDodanyLabel;

    private DbController dbController;
    private JTextPane data;
    private JTextPane nazwaUmowy;
    private JTextPane warunkiUmowy;

    private JComboBox umowyComboBox;
    private JComboBox managerowieComboBox;
    private JComboBox wlascicieleObiektuComboBox;
    private JComboBox obiektyComboBox;
    private JComboBox operatorzyComboBox;

    private JList<Ochroniarz> ochroniarzJList;
    private DefaultListModel<Ochroniarz> listModelOchroniarz;
    private JList<Barman> barmanJList;
    private DefaultListModel<Barman> listModelBarman;

    private Date dataKoncertu;
    private SessionFactory sessionFactory;
    private List<Ochroniarz> ochroniarzKoncertList;
    private List<Barman> barmanKoncertList;
    private Umowa umowa;
    private Operator operatorKoncert;
    private ObiektKoncertu obiektKoncert;


    public MyFrame(DbController dbController) throws Exception {
        obiektKoncert = null;
        operatorKoncert = null;
        umowa = null;
        dataKoncertu = new Date();
        barmanKoncertList = new ArrayList<>();
        ochroniarzKoncertList = new ArrayList<>();
        this.dbController = dbController;
        this.setTitle("Agencja Koncertowa");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(WINDOW_X_SIZE, WINDOW_Y_SIZE);


        addConcertButton = new AddConcertButton("Dodaj koncert");
        addConcertButton.setBounds(175, 350, 200, 50);
        //addConcertButton.setVisible(false);
        this.add(addConcertButton);

        confirmDateButton  = new AddConcertButton("Zatwierdz");
        confirmDateButton.setBounds(175, 430, 200, 50);
        confirmDateButton.setVisible(false);
        this.add(confirmDateButton);

        zatwierdzUmoweButton  = new AddConcertButton("Dodaj umowe");
        zatwierdzUmoweButton.setBounds(175, 485, 200, 50);
        zatwierdzUmoweButton.setVisible(false);
        this.add(zatwierdzUmoweButton);

        zatwierdzOchroniarzaButton = new AddConcertButton("Zatwierdz");
        zatwierdzOchroniarzaButton.setBounds(175, 555, 200, 50);
        zatwierdzOchroniarzaButton.setVisible(false);
        this.add(zatwierdzOchroniarzaButton);

        zatwierdzBarmanaButton = new AddConcertButton("Zatwierdz");
        zatwierdzBarmanaButton.setBounds(175, 555, 200, 50);
        zatwierdzBarmanaButton.setVisible(false);
        this.add(zatwierdzBarmanaButton);

        potwierdzFormularzButton = new AddConcertButton("Zatwierdz");
        potwierdzFormularzButton.setBounds(175, 435, 200, 50);
        potwierdzFormularzButton.setVisible(false);
        this.add(potwierdzFormularzButton);

        powrotButton = new AddConcertButton("Powrot");
        powrotButton.setBounds(175, 435, 200, 50);
        powrotButton.setVisible(false);
        this.add(powrotButton);

        basicText = new JLabel();
        basicText.setText("USER: WlLASCICIEL AGENCJI");
        basicText.setBounds(20, 10, 200, 50);
        basicText.setFont(new Font("Dialog", Font.PLAIN, 14));
        this.add(basicText);

        option = new JLabel();
        option.setText("OPCJE:");
        option.setBounds(175, 300, 200, 50);
        option.setFont(new Font("Dialog", Font.PLAIN, 14));
       // option.setVisible(false);
        this.add(option);


        BufferedImage myPicture = ImageIO.read(new File("Data/KnockedLoose.jpg"));
        img = new JLabel(new ImageIcon(myPicture));
        img.setBounds(250, 20, 250, 190);
        img.setVisible(true);
        this.add(img);

        wrongDate = new JLabel();
        wrongDate.setBounds((WINDOW_X_SIZE / 2) - 75, 550, 250, 25);
        wrongDate.setFont(new Font("Dialog", Font.PLAIN, 20));
        wrongDate.setForeground(Color.RED);
        wrongDate.setVisible(false);
        this.add(wrongDate);

        data = new JTextPane();
        data.setBounds((WINDOW_X_SIZE / 2) - 100, 400, 200, 25);
        data.setFont(new Font("Dialog", Font.PLAIN, 14));
        data.setBorder(BorderFactory.createLineBorder(Color.black));
        data.setBackground(new Color(255, 250, 250));
        data.setVisible(false);
        this.add(data);
        dateTextField = new JLabel();
        dateTextField.setText("DATA:");
        dateTextField.setBounds((WINDOW_X_SIZE / 2) - 150, 400, 100, 25);
        dateTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
        dateTextField.setVisible(false);
        this.add(dateTextField);

        nazwaUmowy = new JTextPane();
        nazwaUmowy.setBounds((WINDOW_X_SIZE / 2) - 100, 340, 200, 25);
        nazwaUmowy.setBorder(BorderFactory.createLineBorder(Color.black));
        nazwaUmowy.setBackground(new Color(255, 250, 250));
        nazwaUmowy.setVisible(false);
        this.add(nazwaUmowy);
        nazwaUmowyTextField = new JLabel();
        nazwaUmowyTextField.setText("Nazwa: ");
        nazwaUmowyTextField.setBounds((WINDOW_X_SIZE / 2) - 150, 340, 100, 25);
        nazwaUmowyTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
        nazwaUmowyTextField.setVisible(false);
        this.add(nazwaUmowyTextField);

        warunkiUmowy = new JTextPane();
        warunkiUmowy.setBounds((WINDOW_X_SIZE / 2) - 100, 430, 200, 50);
        warunkiUmowy.setBorder(BorderFactory.createLineBorder(Color.black));
        warunkiUmowy.setBackground(new Color(255, 250, 250));
        warunkiUmowy.setVisible(false);
        this.add(warunkiUmowy);
        warunkiUmowyTextField = new JLabel();
        warunkiUmowyTextField.setText("Warunki: ");
        warunkiUmowyTextField.setBounds((WINDOW_X_SIZE / 2) - 165, 430, 100, 25);
        warunkiUmowyTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
        warunkiUmowyTextField.setVisible(false);
        this.add(warunkiUmowyTextField);

        ochroniarzeLabel = new JLabel();
        ochroniarzeLabel.setText("OCHRONIARZE:");
        ochroniarzeLabel.setBounds((WINDOW_X_SIZE / 2) -100, 265, 200, 25);
        ochroniarzeLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        ochroniarzeLabel.setVisible(false);
        this.add(ochroniarzeLabel);

        iloscUczestnikowLabel = new JLabel();
        iloscUczestnikowLabel.setText("Max uczestnikow: ");
        iloscUczestnikowLabel.setBounds((WINDOW_X_SIZE / 2) - 210, 340, 130, 25);
        iloscUczestnikowLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        iloscUczestnikowLabel.setVisible(false);
        this.add(iloscUczestnikowLabel);

        koncertDodanyLabel = new JLabel();
        koncertDodanyLabel.setText("KONCERT DODANY");
        koncertDodanyLabel.setBounds((WINDOW_X_SIZE / 2) - 100, 340, 200, 25);
        koncertDodanyLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        koncertDodanyLabel.setVisible(false);
        this.add(koncertDodanyLabel);

        umowyComboBox = new JComboBox();
        umowyComboBox.setBounds(175, 370, 200, 25);
        umowyComboBox.setVisible(false);
        this.add(umowyComboBox);

        managerowieComboBox = new JComboBox();
        managerowieComboBox.setBounds(175, 370, 200, 25);
        managerowieComboBox.setVisible(false);
        this.add(managerowieComboBox);

        wlascicieleObiektuComboBox = new JComboBox();
        wlascicieleObiektuComboBox.setBounds(175, 400, 200, 25);
        wlascicieleObiektuComboBox.setVisible(false);
        this.add(wlascicieleObiektuComboBox);

        obiektyComboBox = new JComboBox();
        obiektyComboBox.setBounds(175, 370, 200, 25);
        obiektyComboBox.setVisible(false);
        this.add(obiektyComboBox);

        operatorzyComboBox = new JComboBox();
        operatorzyComboBox.setBounds(175, 400, 200, 25);
        operatorzyComboBox.setVisible(false);
        this.add(operatorzyComboBox);

        ochroniarzJList = new JList<>();
        listModelOchroniarz = new DefaultListModel<>();
        ochroniarzJList.setBounds((WINDOW_X_SIZE / 2) - 100, 300, 200, 250);
        ochroniarzJList.setFont(new Font("Dialog", Font.PLAIN, 14));
        ochroniarzJList.setBorder(BorderFactory.createLineBorder(Color.black));
        ochroniarzJList.setVisible(false);
        this.add(ochroniarzJList);

        barmanJList =  new JList<>();
        listModelBarman = new DefaultListModel<>();
        barmanJList.setBounds((WINDOW_X_SIZE / 2) - 100, 300, 200, 250);
        barmanJList.setFont(new Font("Dialog", Font.PLAIN, 14));
        barmanJList.setBorder(BorderFactory.createLineBorder(Color.black));
        barmanJList.setVisible(false);
        this.add(barmanJList);




        addConcertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    listModelBarman.removeAllElements();
                    listModelOchroniarz.removeAllElements();
                    ochroniarzJList.removeAll();
                    barmanJList.removeAll();
                    umowyComboBox.removeAllItems();
                    operatorzyComboBox.removeAllItems();
                    obiektyComboBox.removeAllItems();
                if (dbController.czySaUmowy()){
                    convertUmowyToComboBox();
                    setWyborUmowyView();
                }
                else{
                    managerowieComboBox.removeAllItems();
                    wlascicieleObiektuComboBox.removeAllItems();
                    convertManagerZespoluToComboBox();
                    convertWlascicieleObiektuToComboBox();
                    doajUmoweView();
                }
            }

        });
        confirmDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateTocheck = data.getText();
                if(dateTocheck.isEmpty()||!isValidateDate.matcher(dateTocheck).matches()|| dateTocheck.isEmpty()){
                    wrongDate.setText("BLAD DATY");
                    bladDatyView();
                    System.out.println(1);
                }else{
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date convertedCurrentDate = sdf.parse(dateTocheck);
                         if(dbController.czySaPracownicy(convertedCurrentDate)){
                             dataKoncertu = convertedCurrentDate;
                             listModelOchroniarz.addAll(dbController.wyswietlListeOchroniarzy());
                             ochroniarzJList.setModel(listModelOchroniarz);
                             ochroniarzJList.setSelectedIndex(0);
                             ochroniarzListView();
                         }else{
                             System.out.println(3);
                             wrongDate.setText("TERMIN ZAJETY");
                             bladDatyView();
                        }
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }
            }
        });
        zatwierdzUmoweButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nazwa = nazwaUmowy.getText();
                String warunki = warunkiUmowy.getText();
                if(nazwa.isEmpty()||nazwa.isBlank()||warunki.isEmpty()||warunki.isBlank()){
                    bladWalidacjiDodajUmoweView();
                }else {
                    WlascicielObiektu wlascicielUmowa = (WlascicielObiektu) wlascicieleObiektuComboBox.getSelectedItem();
                    ManagerZespolu managerZespolu = (ManagerZespolu) managerowieComboBox.getSelectedItem();
                    Umowa umowa = new Umowa(nazwa, managerZespolu, wlascicielUmowa, warunki);
                    dbController.dodajUmowe(umowa);
                    umowyComboBox.addItem(umowa);
                    umowyComboBox.setSelectedIndex(0);
                    setWyborUmowyView();
                }
            }
        });

        zatwierdzOchroniarzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ochroniarzKoncertList = ochroniarzJList.getSelectedValuesList();
                listModelBarman.addAll(dbController.wyswietlListeBarmanow());
                barmanJList.setModel(listModelBarman);
                barmanJList.setSelectedIndex(0);
                barmanListView();
            }
        });

        zatwierdzBarmanaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                barmanKoncertList = barmanJList.getSelectedValuesList();
                System.out.println(barmanKoncertList);
                convertOperatorzyToComboBox();
                convertObiektyToComboBox();
                umowaFormularzView();
            }
        });
        potwierdzFormularzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isValidateNumber.matcher(nazwaUmowy.getText()).matches()|| nazwaUmowy.getText().isBlank()||nazwaUmowy.getText().isEmpty()){
                    koncertBladWalidacji();
                }else{
                    operatorKoncert = (Operator) operatorzyComboBox.getSelectedItem();
                    obiektKoncert = (ObiektKoncertu) obiektyComboBox.getSelectedItem();
                    int iloscOsob = Integer.parseInt(nazwaUmowy.getText());
                    dbController.dodajKoncertdoSystemu(umowa,barmanKoncertList,ochroniarzKoncertList,dataKoncertu,operatorKoncert,iloscOsob,obiektKoncert);
                    operatorKoncert = null;
                    obiektKoncert = null;
                    umowa = null;
                    iloscOsob = 0;
                    barmanKoncertList = null;
                    ochroniarzKoncertList = null;
                    koncertDodanyView();
                }
            }
        });

        powrotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                powrotButton.setVisible(false);
                koncertDodanyLabel.setVisible(false);
                addConcertButton.setVisible(true);
                option.setVisible(true);
            }
        });
        wlascicieleObiektuComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        managerowieComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        umowyComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                Umowa selectedItem = (Umowa)umowyComboBox.getSelectedItem();
                umowa = selectedItem;
        }
        });



        this.setVisible(true);
    }
    public void convertUmowyToComboBox () {
        List<Umowa> returnList = dbController.wyswietlUmowy();
        for(Umowa u: returnList){
            umowyComboBox.addItem(u);
        }
        umowyComboBox.setSelectedIndex(0);

    }
    public void convertWlascicieleObiektuToComboBox () {
        List<WlascicielObiektu> returnList = dbController.wyswietlWlascicieliObiektu();
        for(WlascicielObiektu w : returnList){
            wlascicieleObiektuComboBox.addItem(w);
        }
            wlascicieleObiektuComboBox.setSelectedIndex(0);
    }
    public void convertManagerZespoluToComboBox() {
      List<ManagerZespolu> returnList = dbController.wyswietlManagerow();
      for(ManagerZespolu m : returnList){
          managerowieComboBox.addItem(m);
      }
        managerowieComboBox.setSelectedIndex(0);
    }
    public void convertObiektyToComboBox(){
        List<ObiektKoncertu> returnList = dbController.wyswietlListeObiektow();
        for(ObiektKoncertu o : returnList){
            obiektyComboBox.addItem(o);
        }
        obiektyComboBox.setSelectedIndex(0);
    }
    public void convertOperatorzyToComboBox(){
        List<Operator> returnList = dbController.wyswietlListeOperatorw();
        for(Operator o : returnList){
            operatorzyComboBox.addItem(o);
        }
        operatorzyComboBox.setSelectedIndex(0);
    }
    public void setWyborUmowyView(){

        addConcertButton.setVisible(false);
        option.setVisible(false);
        warunkiUmowy.setVisible(false);
        warunkiUmowyTextField.setVisible(false);
        nazwaUmowyTextField.setVisible(false);
        nazwaUmowy.setVisible(false);
        wlascicieleObiektuComboBox.setVisible(false);
        managerowieComboBox.setVisible(false);
        zatwierdzUmoweButton.setVisible(false);
        wrongDate.setVisible(false);
        data.setVisible(true);
        dateTextField.setVisible(true);
        umowyComboBox.setVisible(true);
        confirmDateButton.setVisible(true);

    }
    public void doajUmoweView(){
        addConcertButton.setVisible(false);
        option.setVisible(false);
        zatwierdzUmoweButton.setVisible(true);
        warunkiUmowy.setVisible(true);
        warunkiUmowyTextField.setVisible(true);
        nazwaUmowy.setVisible(true);
        nazwaUmowyTextField.setVisible(true);
        wlascicieleObiektuComboBox.setVisible(true);
        managerowieComboBox.setVisible(true);

    }
    public void bladWalidacjiDodajUmoweView(){
        zatwierdzUmoweButton.setVisible(true);
        warunkiUmowy.setVisible(true);
        warunkiUmowyTextField.setVisible(true);
        nazwaUmowy.setVisible(true);
        nazwaUmowyTextField.setVisible(true);
        wlascicieleObiektuComboBox.setVisible(true);
        managerowieComboBox.setVisible(true);
        wrongDate.setVisible(true);
        wrongDate.setText("WYPELNIJ POLA");
    }
    public void bladDatyView(){
        addConcertButton.setVisible(false);
        option.setVisible(false);
        warunkiUmowy.setVisible(false);
        warunkiUmowyTextField.setVisible(false);
        nazwaUmowy.setVisible(false);
        wlascicieleObiektuComboBox.setVisible(false);
        managerowieComboBox.setVisible(false);
        zatwierdzUmoweButton.setVisible(false);
        data.setVisible(true);
        dateTextField.setVisible(true);
        umowyComboBox.setVisible(true);
        confirmDateButton.setVisible(true);
        wrongDate.setVisible(true);
    }
    public void ochroniarzListView(){
        wrongDate.setVisible(false);
        data.setVisible(false);
        dateTextField.setVisible(false);
        umowyComboBox.setVisible(false);
        confirmDateButton.setVisible(false);
        ochroniarzJList.setVisible(true);
        ochroniarzeLabel.setVisible(true);
        ochroniarzeLabel.setText("OCHRONIARZE:");
        zatwierdzOchroniarzaButton.setVisible(true);
    }

    public void barmanListView(){
        ochroniarzJList.setVisible(false);
        ochroniarzeLabel.setText("BARMANI:");
        zatwierdzOchroniarzaButton.setVisible(false);
        barmanJList.setVisible(true);
        zatwierdzBarmanaButton.setVisible(true);
    }

    public void umowaFormularzView(){
        ochroniarzeLabel.setVisible(false);
        zatwierdzOchroniarzaButton.setVisible(false);
        barmanJList.setVisible(false);
        zatwierdzBarmanaButton.setVisible(false);
        nazwaUmowy.setVisible(true);
        operatorzyComboBox.setVisible(true);
        obiektyComboBox.setVisible(true);
        iloscUczestnikowLabel.setVisible(true);
        potwierdzFormularzButton.setVisible(true);
    }
    public void koncertBladWalidacji(){
        nazwaUmowy.setVisible(true);
        operatorzyComboBox.setVisible(true);
        obiektyComboBox.setVisible(true);
        iloscUczestnikowLabel.setVisible(true);
        potwierdzFormularzButton.setVisible(true);
        wrongDate.setText("ZLA LICZBA");
        wrongDate.setVisible(true);
    }
    public void koncertDodanyView(){
        nazwaUmowy.setVisible(false);
        operatorzyComboBox.setVisible(false);
        obiektyComboBox.setVisible(false);
        iloscUczestnikowLabel.setVisible(false);
        potwierdzFormularzButton.setVisible(false);
        wrongDate.setVisible(false);
        powrotButton.setVisible(true);
        koncertDodanyLabel.setVisible(true);
    }
}
