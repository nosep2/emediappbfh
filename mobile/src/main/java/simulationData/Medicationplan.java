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
        mMediplan.add(new Medication(1004, "Norflocin-Mepha ", "400mg", "Norfloxacin", "Tablette", "standard", "mittags|nachts, 1 Tablette", "04.01.16", "weeks, 2", "Praxis Dr. Matt, Biel", "mittags und nachts: 1 Tablette"));
        mMediplan.add(new Medication(1005, "Marcoumar", "3mg", "Phenpro coumon", "Tablette", "tagesvariabel", "mo,1 1/4|3/4|1|3/4|3/4|1|3/4", "01.10.15", "months, 6", "Praxis Dr. Matt, Biel", "heute 1 1/4 Tablette (Sa 3/4,So 1)"));

    }

    public ArrayList<Medication> getmMediplan() {
        return mMediplan;
    }

}
