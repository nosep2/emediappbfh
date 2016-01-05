package simulationData;

/**
 * Created by Corina on 19.11.15.
 * Updated by Philipp on 05.01.16
 *
 * Medication ist die Verschreibung zum Medikament, das heisst Einnahmehäufigkeit, Verschreibungsart, Verschreibung, Dauer der Einnahme und Verschreiber
 * Zur Einfachheit wird zu diesem Stadium der Umsetzung:
 * - die Verschreibungsart und Verschreibung nicht auseinandergenommen
 * - das Medikament nicht als eigenes Objekt erstellt sondern ist Teil der Medication
 * ToDo: Klassenstruktur zu Medikament, Medikation und  Verschreibungsart/Verschreibung erstellen
 */
public class Medication {

    private int mNumber;
    private String mDosage;
    private String mMedi;
    private String mWirkstoff;
    private String mApplikationsform;
    private String mVerschreibungsArt;
    private String mVerschreibung;
    private String mEinnahmestart;
    private String mEinnahmeDauer;
    private String mVerschreiber;
    private String mHinweiseBemerkung;


    public Medication (int number, String medi, String dosage, String wirkstoff, String applikationsform, String verschreibungsArt, String verschreibung, String einnahmestart ,String einnahmeDauer,  String verschreiber, String hinweiseBemerkung){

        mNumber = number;
        mDosage = dosage;
        mMedi = medi;
        mWirkstoff = wirkstoff;
        mApplikationsform = applikationsform;
        mVerschreibungsArt = verschreibungsArt;
        mVerschreibung = verschreibung;
        mEinnahmestart = einnahmestart;
        mEinnahmeDauer = einnahmeDauer;
        mVerschreiber = verschreiber;
        mHinweiseBemerkung = hinweiseBemerkung;
    }

    public int getmNumber() {
        return mNumber;
    }

        public String getmMedi() {
        return mMedi;
    }

    public String getmDosage() {
        return mDosage;
    }

    public String getmWirkstoff() {
        return mWirkstoff;
    }

    public String getmApplikationsform() {
        return mApplikationsform;
    }

    public String getmVerschreibungsArt() {
        return mVerschreibungsArt;
    }

    public String getmVerschreibung() {
        return mVerschreibung;
    }

    public String getmEinnahmestart() {
        return mEinnahmestart;
    }

    public String getmEinnahmeDauer() {
        return mEinnahmeDauer;
    }

    public String getmVerschreiber() {
        return mVerschreiber;
    }

    public String getmHinweiseBemerkung() {
        return mHinweiseBemerkung;
    }

    public String toString() {
        return getmMedi() + " " + getmVerschreibung() + " " + getmVerschreiber();
    }

    /*
    @return getQrRepresentation:
    generiert String gemäss der Struktur beschrieben im Dokument "Medikationsplan_aktualiesiert" Seite 18.
    Jeder String in der Methode entspricht einer Tabellenzeile
    Informationen von Frau Brönnimann und Dr. Wenger aus BFH Medizininformatik Wiki
    Feldcode 3.1-3.x fehlt im Detschen Standard, Einträge wurden gemäss "Medikationsplan_aktualiesiert" Seite 18 gemacht.
     */
    public String getQrRepresentationPerson() {
        String mediplanAttributes = "|||||||||\n";
        String receiver = "Elisabeth|Broennimann-Bertholet|80756015090002647590|19370303||Kreuzweg 11|2501|Biel||elisabeth@broennimann.today|\n";
        String printedBy = "Praxis Wenger|Kreuzweg 10||Biel|||\n";
        String gewicht = "87 kg|||\n";

        return mediplanAttributes + receiver + printedBy + gewicht;
    }

    public String getQrRepresentationMedi() {
        String Medikament = getmWirkstoff()+"|"+getmMedi()+"|"+getmDosage()+"|"+getmApplikationsform()+"|"+getmVerschreibung()+"|"+getmHinweiseBemerkung()+"\n";


        return Medikament;
    }
}
