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

    private String mMedi;
    private String mVerschreibungsArt;
    private String mVerschreibung;
    private String mEinnahmeDauer;
    private String mVerschreiber;

    public Medication (String medi, String verschreibungsArt, String verschreibung, String einnahmeDauer,  String verschreiber){

        mMedi = medi;
        mVerschreibungsArt = verschreibungsArt;
        mVerschreibung = verschreibung;
        mEinnahmeDauer = einnahmeDauer;
        mVerschreiber = verschreiber;
    }

    public void setmMedi(String mMedi) {
        this.mMedi = mMedi;
    }

    public void setmVerschreibungsArt(String mVerschreibungsArt) {
        this.mVerschreibungsArt = mVerschreibungsArt;
    }

    public void setmVerschreibung(String mVerschreibung) {
        this.mVerschreibung = mVerschreibung;
    }

    public void setmEinnahmeDauer(String mEinnahmeDauer) {
        this.mEinnahmeDauer = mEinnahmeDauer;
    }

    public void setmVerschreiber(String mVerschreiber) {
        this.mVerschreiber = mVerschreiber;
    }

    public String getmMedi() {
        return mMedi;
    }
    public String getmVerschreibungsArt() {
        return mVerschreibungsArt;
    }

    public String getmVerschreibung() {
        return mVerschreibung;
    }

    public String getmEinnahmeDauer() {
        return mEinnahmeDauer;
    }

    public String getmVerschreiber() {
        return mVerschreiber;
    }

    public String toString() {
        return getmMedi() + " " + getmVerschreibung() + " " + getmVerschreiber();

    }
}
