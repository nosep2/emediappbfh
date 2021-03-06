package clinappteam2hs15.emediapp;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import simulationData.Medication;
import simulationData.Medicationplan;

/**
 * Created by Philipp on 11.12.2015.
 * Updated by Corina on 2.01.2016
 *
 * @return QrCode:
 * Generates a Qr-Code from the Information of the Medication using the Google Library zxing
 * returns a bmp file ready to be displayed by the MainMediplanTab
 *
 */

public class QrCode {
    QRCodeWriter writer = new QRCodeWriter();
    Medicationplan currentMedicationplan = new Medicationplan();
    Medication medication = new Medication(0,"","","","","","","","","","");
    //content


    public Bitmap generateQrcode() {


        String medicationplanAsString=medication.getQrRepresentationPerson();


        for(Medication medication: currentMedicationplan.getmMediplan()){
            String m = medication.getQrRepresentationMedi();
            medicationplanAsString = medicationplanAsString + m;
        }


        try {
            BitMatrix bitMatrix = writer.encode(medicationplanAsString, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            return bmp;

        } catch (WriterException e) {
            e.printStackTrace();

        }
        return null;
    }

}
