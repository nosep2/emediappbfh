package clinappteam2hs15.emediapp;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Created by Philipp on 11.12.2015.
 */
public class QrCode {
    QRCodeWriter writer = new QRCodeWriter();

    //content

    public Bitmap generateQrcode() {
        try {
            BitMatrix bitMatrix = writer.encode("test", BarcodeFormat.QR_CODE, 512, 512);
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
