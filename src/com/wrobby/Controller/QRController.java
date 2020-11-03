package com.wrobby.Controller;

import com.google.zxing.WriterException;
import com.wrobby.Dao.QR.QRCodeDao;
import com.wrobby.Dao.QR.QRConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Base64;

@Controller
public class QRController {

    @ResponseBody
    @RequestMapping("/getQR")
    public ResponseEntity<byte[]> QrGetter(QRConfig config){
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        ResponseEntity<byte[]> entity=null;
        try {

            entity= new ResponseEntity<>(QRCodeDao.getQr(config), headers, HttpStatus.OK);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        return entity;
    }
/*
* 用于ajax展示图片
* */
    @ResponseBody
    @RequestMapping(value = "/postQR",method = RequestMethod.POST)
    public String post(QRConfig config) {
        byte[] bytes =null;
        try {
            bytes=QRCodeDao.getQr(config);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
            System.out.println("----------");
            System.out.println(e.getMessage());
        }
        if (bytes.length!=0){
            return Base64.getEncoder().encodeToString(bytes);
        }else {
            return "<h1>错误</h1>";
        }


    }
}
