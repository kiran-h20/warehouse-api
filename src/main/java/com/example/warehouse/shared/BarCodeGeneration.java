package com.example.warehouse.shared;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class BarCodeGeneration {

    public static byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        Code128Writer code128Writer = new Code128Writer();
//        QRCodeWriter code128Writer=new QRCodeWriter();
        BitMatrix bitMatrix = code128Writer.encode(text, BarcodeFormat.CODE_128, width, height);

        try (ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            return pngOutputStream.toByteArray();
        }
    }
}
