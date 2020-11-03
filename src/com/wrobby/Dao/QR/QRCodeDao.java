package com.wrobby.Dao.QR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRCodeDao {
    private static QRCodeWriter qrCodeWriter = new QRCodeWriter();
    private static Map<EncodeHintType, Object> map = new HashMap<>();

    static {
        map.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    }

    public static byte[] getQr(QRConfig config) throws WriterException, IOException {
        Integer length = config.getLength();
        //边界空白宽度
        map.put(EncodeHintType.MARGIN, config.getMargin());
        //纠错等级
        map.put(EncodeHintType.ERROR_CORRECTION, config.getErrorCorrectionLevel());
        BitMatrix bitMatrix = qrCodeWriter.encode(config.getText(), BarcodeFormat.QR_CODE, length, length, map);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //设置logo
        if (config.getLogo()) {
           // File file = new File("logo.png");
            File file =new File("C:/logo.png");
            BufferedImage logo = ImageIO.read(file);
            ImageIO.write(addLogo(image, logo), "png", stream);
        } else {
            ImageIO.write(image, "png", stream);
        }
        byte[] bytes = stream.toByteArray();
        stream.close();
        return bytes;
    }

    /*
     * 添加logo,默认加logo时使用彩色,暂未提供修改
     *
     * */
    public static BufferedImage addLogo(BufferedImage old, BufferedImage logo) {
        int width = old.getWidth();
        int height = old.getHeight();
        int width2 = logo.getWidth();
        int height2 = logo.getHeight();
        //logo起始位置
        int x = (width - width2) / 2;
        int y = (height - height2) / 2;
        //创建空白画布 默认加logo时使用彩色
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //向画布画入二维码和logo
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(old, 0, 0, width, height, null);
        g.drawImage(logo, x, y, width2, height2, null);
        g.dispose();
        return bufferedImage;
    }
}
