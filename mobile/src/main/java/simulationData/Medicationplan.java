package simulationData;


import java.util.ArrayList;

/**
 * Created by corina on 19.11.15.
 *
 * Diese Klasse enthaelt eine beispielhafte Medikation basierend auf der Klasse Medication
 */
public class Medicationplan {

    private ArrayList<Medication> mMediplan = new ArrayList<Medication>();


    public Medicationplan(){
        initData();
    }

    private void initData(){
        if (!mMediplan.isEmpty())
        {mMediplan.clear();}

        mMediplan.add(new Medication(1001, "Allopur, 100mg", "Allopurinol", "Tablette", "standard", "morgens, 1 Tablette", "12.08.15", "noEnd", "SPZ Biel, Innere Medizin, 2503 Biel", "Gicht - Senkung der Harnsäure"));
        mMediplan.add(new Medication(1002, "Durogesic Matrix TTS, 100mcg/h", "Fentanyl", "Pflaster", "xInterval", "days, 3", "28.12.15", "weeks, 3", "Praxis Dr. Matt, Biel", ""));
        mMediplan.add(new Medication(1003, "Vesicare, 5mg", "Solifenacin succinat", "Tablette", "standard", "nachts, 1 Tablette", "01.10.15", "months, 6", "Praxis Dr. Matt, Biel", ""));

    }

    public ArrayList<Medication> getmMediplan() {
        return mMediplan;
    }

}
