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

        mMediplan.add(new Medication("Allopur 100mg", "standard", "morgens: 1 Tablette", "ab 12.Aug", "SPZ Biel, Innere Medizin, 2503 Biel"));

    }

    public String getMedication() {
     /* add  try catch */
        Medication mMedi = mMediplan.get(0);
        return mMedi.toString();
    }

}
