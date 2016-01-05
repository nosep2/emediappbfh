package simulationData;


import java.util.ArrayList;

/**
 * Created by corina on 19.11.15.
 *
 * Diese Klasse enthaelt eine beispielhafte Medikation basierend auf der Klasse Medication
 */
public class Medicationplan {

    private ArrayList<Medication> mMediplan = new ArrayList<Medication>();

    private static Medicationplan _instance;
    public static Medicationplan Instance()
    {
        if (_instance == null)
        {
            _instance = new Medicationplan();
        }
        return _instance;
    }

    public Medicationplan(){
        initData();
    }

    private void initData(){
        if (!mMediplan.isEmpty())
        {mMediplan.clear();}

        mMediplan.add(new Medication(1001, "Allopur", "100mg", "Allopurinol", "Tablette", "standard", "morgens, 1 Tablette", "12.08.15", "noEnd", "SPZ Biel, Innere Medizin, 2503 Biel", "morgens: 1 Tablette"));
        mMediplan.add(new Medication(1002, "Durogesic Matrix TTS", "100mcg/h", "Fentanyl", "Pflaster", "xInterval", "days, 3", "28.12.15", "weeks, 3", "Praxis Dr. Matt, Biel", "alle 3 Tage"));
        mMediplan.add(new Medication(1003, "Vesicare", "5mg", "Solifenacin succinat", "Tablette", "standard", "nachts, 1 Tablette", "01.10.15", "months, 6", "Praxis Dr. Matt, Biel", "nachts: 1 Tablette"));

    }

    public ArrayList<Medication> getmMediplan() {
        return mMediplan;
    }

}
