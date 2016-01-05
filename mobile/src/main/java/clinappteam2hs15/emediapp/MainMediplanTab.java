package clinappteam2hs15.emediapp;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import simulationData.Medication;
import simulationData.Medicationplan;

/**
 * Created by corina on 23.12.15.
 *
 * Dies ist der Inhalt des Mediplan Tabs in der Startactivität (MainPageActivity.java)
 * dazugehöriges Layout: content_mediplan_main_page.xml
 *
 * In der Ansicht befindet sich der Button um die Qr-Barcode-Generierung des Medikamentenplanes zu generieren
 * und die Auflistung der aktuellen Medikation, mit dem Datum, wann die letzte Änderung erfolgte
 */
public class MainMediplanTab extends Fragment {

    /**
     * Innere Klassen für die Auflistung der Medikamente, nach aktueller Vorgabe von Android Studio
     *
     */
    private class MedicationHolder extends RecyclerView.ViewHolder {
        /*
        Auflistung der gewünschten Attribute einer Medikation als TextViews
         */
        public TextView mMedicationName;
        public TextView mMedicationApplicationForm;
        public TextView mMedicationVerschrieber;
        public TextView mMedicationHinweise;

        public MedicationHolder(View itemView) {
            super(itemView);
            mMedicationName = (TextView) itemView.findViewById(R.id.list_item_medication_name);
            mMedicationApplicationForm = (TextView) itemView.findViewById(R.id.list_item_medication_application);
            mMedicationVerschrieber = (TextView) itemView.findViewById(R.id.list_item_medication_verschreiber);
            mMedicationHinweise = (TextView) itemView.findViewById(R.id.list_item_medication_hinweise);
        }

        public void bind(Medication m) {
            mMedicationName.setText(m.getmMedi());
            mMedicationApplicationForm.setText(m.getmApplikationsform());
            mMedicationVerschrieber.setText(m.getmVerschreiber());
            mMedicationHinweise.setText(m.getmHinweiseBemerkung());
        }
    }

    //Adapter für die ListView
    private class MedicationAdapter extends RecyclerView.Adapter<MedicationHolder> {
        private List<Medication> mMedications;
        public MedicationAdapter(List<Medication> medications) {
            mMedications = medications;
        }

        //der Adapter kreiert einen ViewHolder
        public MedicationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            View v = inflater.inflate(R.layout.list_item_medication, parent, false);

            return new MedicationHolder(v);
        }

                public void onBindViewHolder(MedicationHolder holder, int position) {
            Medication m = mMedications.get(position);
            holder.bind(m);
        }

        // Anzahl Medikamente in der aktuellen Medikation
        public int getItemCount() {
            return mMedications.size();
        }
    }

    public static final int NOTIFICATION_ID = 1;
    private RecyclerView medicationListView;
    private MedicationAdapter mMedicationAdapter;
    private Button mCreateQrBarcode;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout of this view
        View view = inflater.inflate(R.layout.content_mediplan_main_page, container, false);

        medicationListView = (RecyclerView)view.findViewById(R.id.mediplan_listView);
        medicationListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mCreateQrBarcode = (Button) view.findViewById(R.id.createQrCode_button);
        mCreateQrBarcode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBarcodeClick(v);
            }
        });

        UpdateUI();
        return view;

    }
    private void UpdateUI()
    {
        List<Medication> medications = Medicationplan.Instance().getmMediplan();
        mMedicationAdapter = new MedicationAdapter(medications);
        medicationListView.setAdapter(mMedicationAdapter);

    }

    /**
     * Shows the button for the Qr-code generating
     * returns upon clicking a dialog window with the generated code ready to be read
     */
    public void onBarcodeClick(View view) {
        QrCode qr = new QrCode();
        Bitmap bmp = qr.generateQrcode();

        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        ImageView imgView = new ImageView(getActivity());
        imgView.setImageBitmap(bmp);
        dialog.setContentView(imgView);
        dialog.show();
        

    }
}