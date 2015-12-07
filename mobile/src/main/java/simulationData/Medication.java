package simulationData;

/**
 * Created by corina on 19.11.15.
 *
 * Medication ist die Verschreibung zum Medikament, das heisst Einnahmehäufigkeit, Verschreibungsart, Verschreibung, Dauer der Einnahme und Verschreiber
 * Zur Einfachheit wird zu diesem Stadium der Umsetzung:
 * - die Verschreibungsart und Verschreibung nicht auseinandergenommen
 * - das Medikament nicht als eigenes Objekt erstellt sondern ist Teil der Medication
 * ToDo: Klassenstruktur zu Medikament, Medikation und  Verschreibungsart/Verschreibung erstellen
 */
public class Medication {

    private int mNumber;
    private String mMedi;
    private String mWirkstoff;
    private String mApplikationsform;
    private String mVerschreibungsArt;
    private String mVerschreibung;
    private String mEinnahmestart;
    private String mEinnahmeDauer;
    private String mVerschreiber;
    private String mHinweiseBemerkung;

    public Medication (int number, String medi, String wirkstoff, String applikationsform, String verschreibungsArt, String verschreibung, String einnahmestart ,String einnahmeDauer,  String verschreiber, String hinweiseBemerkung){

        mNumber = number;
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

    public int getmNumber() { return mNumber;}

    public String getmMedi() {
        return mMedi;
    }

    public String getmWirkstoff() {
        return mWirkstoff;
    }

    public String getmApplikationsform() { return mApplikationsform; }
    public String getmVerschreibungsArt() {
        return mVerschreibungsArt;
    }

    public String getmVerschreibung() {
        return mVerschreibung;
    }

    public String getmEinnahmestart() { return mEinnahmestart; }

    public String getmEinnahmeDauer() {
        return mEinnahmeDauer;
    }

    public String getmVerschreiber() {
        return mVerschreiber;
    }
    public String getmHinweiseBemerkung() { return mHinweiseBemerkung; }

    public String toString() {
        return getmMedi() + " " + getmVerschreibung() + " " + getmVerschreiber();

    }
}
