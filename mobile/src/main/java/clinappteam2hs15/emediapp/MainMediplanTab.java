package clinappteam2hs15.emediapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import simulationData.Medication;
import simulationData.Medicationplan;

/**
 * Created by corina on 23.12.15.
 *
 * Dies ist der Inhalt des Mediplan Tabs in der Startactivität (MainPageActivity.java)
 * dazugehöriges Layout: content_mediplan_main_page.xml
 *
 * In der Ansicht befindet sich ein analoge Uhr, das Datum und Informationen zu den Medikationseinnahme am aktuellen Tag, sowie Termine
 */
public class MainMediplanTab extends Fragment {

  //  private Button mCreateMediplanQrCode;
    private Medicationplan mMedicationplan;
    public static final int NOTIFICATION_ID = 1;
    ArrayList<String>mMediTitelDose = new ArrayList<String>();
    ArrayList<Medication>mMedications = new ArrayList<Medication>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout of this view
        View view = inflater.inflate(R.layout.content_mediplan_main_page, container, false);

        /* Versuch die Daten als Strings in einer Arraylist zu übergeben
        mMedications = mMedicationplan.getmMediplan();
        for (Medication mMedication: mMedications){
        String mMediTD = mMedication.getmMedi();
        mMediTitelDose.add(mMediTD);
        }*/
        String[]myStringArray={"MediA", "MediB", "MediC"};

        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myStringArray);

        ListView medicationList = (ListView)view.findViewById(R.id.mediplan_listView);
        medicationList.setAdapter(myAdapter);

        /*ToDo: auf 2. Menuinhalt verschieben.
        Ziel: Der Medikationsplan soll ausgelesen und dargestellt werden.

        mMedicationplan = new Medicationplan();
        mMedicationView = (TextView) getActivity().findViewById(R.id.Medication_textView);
        mMedicationView.setText((CharSequence) mMedicationplan.getMedication());*/

       /* FloatingActionButton fab_overview_main = (FloatingActionButton) getActivity().findViewById(R.id.fab_overview_main);
        fab_overview_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        return view;

    }

}