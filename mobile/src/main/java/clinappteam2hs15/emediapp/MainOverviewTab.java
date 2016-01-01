package clinappteam2hs15.emediapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import simulationData.Medicationplan;

/**
 * Created by corina on 08.12.15.
 *
 * Dies ist der Inhalt des Übersichts Tabs in der Startactivität (MainPageActivity.java)
 * dazugehöriges Layout: overview_main_page.xml
 * (extends Fragment, da die Tabview mit "Fragmenten" arbeitet
 *
 * In der Ansicht befindet sich eine analoge Uhr, das Datum und Informationen zu den Medikationseinnahme am aktuellen Tag
 */
public class MainOverviewTab extends Fragment {


    private Medicationplan mMedicationplan;
    private TextView mMedicationView;

    public static final int NOTIFICATION_ID = 1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout of this view
        View view = inflater.inflate(R.layout.content_overview_main_page, container, false);


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