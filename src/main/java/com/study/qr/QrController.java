package com.study.qr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("api/v1/generate")
@RestController
public class QrController {
    // Injecting QrGenerator
    private final QrGenerator qrGenerator;

    @Autowired
    public QrController(QrGenerator qrGenerator) {
        this.qrGenerator = qrGenerator;
    }

    /**
     * Simple API that return a QRCode
     * @param data
     * @return Object with message and image fields
     */
    @PostMapping("/qr")
    public ResponseEntity<?> generateQrCode(@RequestBody Map<String, String> data){
        Map<String,Object> response = qrGenerator.generateQRCode(data.get("message"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

