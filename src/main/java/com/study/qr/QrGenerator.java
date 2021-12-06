package com.study.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class QrGenerator {

    public Map<String, Object> generateQRCode(String data) {
        // image saving directory change to yours
        String imageDir = "";
        // the path where your image will be stored
        String path = imageDir + "QR" + UUID.randomUUID() + ".jpg";
        Map<String, Object> response = new HashMap<>();

        try {
            // encoding request data to QR code
            BitMatrix matrix = new MultiFormatWriter()
                    .encode(data, BarcodeFormat.QR_CODE, 250, 250);
            // writes the image into path that we assigned above
            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
            response.put("message", "success");
            // get file bytes
            byte[] fileContent = Files.readAllBytes(Paths.get(path));
            // returning the image as base64 for front-side
            response.put("image", Base64Utils.encodeToString(fileContent));
            return response;
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            response.put("message", "fail");
            // You can delete finally block if you dont need to delete QR image after logic
        } finally {
            // getting file
            File file = new File(Paths.get(path).toString());
            // It need's to remove the file after the logic runs
            file.delete();
        }
        return response;
    }


}
