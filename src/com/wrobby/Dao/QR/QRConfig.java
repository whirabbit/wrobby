package com.wrobby.Dao.QR;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Component;

@Component
public class QRConfig{
//    private String encodeHintType;
    private String text;
    private String  errorCorrectionLevel;
    private Integer margin;
    private Integer length;
    private Boolean logo;
    public QRConfig() {
       text="你忘了写内容了";
       errorCorrectionLevel="H";
       margin=0;
       length=100;
       logo=false;
    }

    public String getErrorCorrectionLevel() {
        return errorCorrectionLevel;
    }

    public void setErrorCorrectionLevel(String errorCorrectionLevel) {
        this.errorCorrectionLevel = errorCorrectionLevel;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getLogo() {
        return logo;
    }

    public void setLogo(Boolean logo) {
        this.logo = logo;
    }
}
