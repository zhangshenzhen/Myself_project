package com.shenzhen.print.prin_t;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import android.util.Log;


import com.koolpos.usbprinterlib.constants.labelprinter.TsplBarcodeType;
import com.koolpos.usbprinterlib.constants.labelprinter.TsplFontName;
import com.koolpos.usbprinterlib.utils.TsplCmd;
import com.koolpos.usbprinterlib.utils.TsplCmd.DimensionUnit;
import com.koolpos.usbprinterlib.utils.TsplCmd.RotationDegrees;
import com.shenzhen.print.R;
import com.shenzhen.print.koolpos.mobiledevdemo.DeviceModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PrepareReceiptInfo {

    private static final int FONT_DEFAULT = 0x1111;
    private static final int FONT_BIG = 0x1421;
    private static String prtIndex = "0";

    public static void setPrtIndex(String index) {
        prtIndex = index;
    }

    private static final byte[][] labelPrintCmd = {
            TsplCmd.setLabelSize(40, 30, DimensionUnit.mm),
            TsplCmd.setGapSize(2, 0, DimensionUnit.mm),
            TsplCmd.setCls(),
            TsplCmd.addText(28, 8, TsplFontName.FONT_TSS24_BF2, RotationDegrees.Degrees0, (byte) 1, (byte) 1,
                    "得力无线装订本（80页）", null),

            TsplCmd.addBarCode(10, 50, TsplBarcodeType.TYPE_EAN13, 48, true, RotationDegrees.Degrees0, 1, 2,
                    "6921734976543"),
            TsplCmd.addText(76, 140, TsplFontName.FONT_TSS24_BF2, RotationDegrees.Degrees0, (byte) 1, (byte) 1,
                    "单价：3.6元/本", null),
            TsplCmd.addBar(0, 170, 320, 2),
            TsplCmd.addText(52, 180, TsplFontName.FONT_TSS24_BF2, RotationDegrees.Degrees0, (byte) 1, (byte) 1,
                    "无锡酷银科技测试字", null), TsplCmd.print(1, 0) };

    private static String addTsplCmd() {
        StringBuilder builder = new StringBuilder();
        builder.append("LabelFlag★");
        for (byte[] cmd : labelPrintCmd) {
            try {
                String cmdStr = new String(cmd, "GB2312");
                builder.append(cmdStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }

    /**
     * 封装酷券核销小票打印信息
     * 
     * @param context
     * @return
     */
    public static JSONObject getJsonReceipt(Context context) {
        return getJsonReceipt(context,false);
    }

    /**
     * 封装酷券核销小票打印信息
     * 
     * @param context
     * @param context
     * @return isLablePrinter 是否是标签打印机
     */
    public static JSONObject getJsonReceipt(Context context, boolean isLablePrinter) {
        JSONArray array = new JSONArray();
        if (isLablePrinter) {
            addTextJson(array, FONT_DEFAULT, addTsplCmd(), PrinterManager.CONTENT_ALIGN_LEFT);
        } else {
            
            if (DeviceModel.isKool10()) {

                // 打开钱箱
                // addCmdJson(array, getOpenBoxCmd());
                // 执行自定义指令（此处仅作为指令执行演示）
                StringBuilder cmdBuilder = new StringBuilder();
                cmdBuilder.append((char) 29);
                cmdBuilder.append((char) 33);
                cmdBuilder.append((char) 17);
             //   cmdBuilder.append("购物小票");
                cmdBuilder.append((char) 29);
                cmdBuilder.append((char) 33);
                cmdBuilder.append((char) 0);
                addTextJson(array, FONT_DEFAULT, cmdBuilder.toString(), PrinterManager.CONTENT_ALIGN_CENTER);
            }else {
              //  addTextJson(array, FONT_BIG, "购物小票", PrinterManager.CONTENT_ALIGN_CENTER);
            }
            
            
            addTextJson(array, FONT_DEFAULT, "用户联", PrinterManager.CONTENT_ALIGN_LEFT);
            addDashLine(array);
         /*  addTextJson(array, FONT_DEFAULT, "商户名：测试商户demo", PrinterManager.CONTENT_ALIGN_LEFT);
            addTextJson(array, FONT_DEFAULT, "商户号：1234567890", PrinterManager.CONTENT_ALIGN_LEFT);
            addTextJson(array, FONT_DEFAULT, "终端号：111111", PrinterManager.CONTENT_ALIGN_LEFT);
            addTextJson(array, FONT_DEFAULT, "流水号：12345678", PrinterManager.CONTENT_ALIGN_LEFT);
            addTextJson(array, FONT_DEFAULT, "操作员：01", PrinterManager.CONTENT_ALIGN_LEFT);
            addTextJson(array, FONT_DEFAULT, "交易类型：消费", PrinterManager.CONTENT_ALIGN_LEFT);
            addTextJson(array, FONT_DEFAULT, "交易时间：2016-03-16 10:40:58", PrinterManager.CONTENT_ALIGN_LEFT);
            addDashLine(array);
           addMultiTextJson(array, FONT_BIG, "金额：", "¥555"); // P8000不支持
            addTextJson(array, FONT_BIG, "金额：555.00元", PrinterManager.CONTENT_ALIGN_LEFT);
            addBarCode(array, "6921734976512", PrinterManager.CONTENT_ALIGN_CENTER);
            addQrCode(array, "A123123T", PrinterManager.CONTENT_ALIGN_CENTER);
            addBitmapJson(array, BitmapFactory.decodeResource(context.getResources(), R.drawable.qrcode),
                    PrinterManager.CONTENT_ALIGN_CENTER);*/
           /* addBlankLine(array);
            addBlankLine(array);
            addBlankLine(array);
            addBlankLine(array);
            addBlankLine(array);*/
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("printerIndex", prtIndex); // 设置打印机序号
            jsonObject.put("page", array); // 设置打印内容
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("",jsonObject.toString());
        return jsonObject;
    }

    /**
     * 加空行
     * 
     * @param array
     */
    private static void addBlankLine(JSONArray array) {
        try {
            JSONObject json = new JSONObject();
            json.put("type", PrinterManager.PRINT_CONTENT_TYPE_BLANK_LINE);
            array.put(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加分割线
     * 
     * @param array
     */
    private static void addDashLine(JSONArray array) {
        try {
            JSONObject json = new JSONObject();
            json.put("type", PrinterManager.PRINT_CONTENT_TYPE_DASH_LINE);
            array.put(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印图片
     * 
     * @param array
     * @param bitmap
     * @param align
     */
    private static void addBitmapJson(JSONArray array, Bitmap bitmap, int align) {
        try {
            JSONObject json = new JSONObject();
            json.put("type", PrinterManager.PRINT_CONTENT_TYPE_BITMAP);
            json.put("bitmap", bitmapToBase64(bitmap));
            json.put("align", align);
            array.put(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印文字
     * 
     * @param array
     * @param size
     * @param text
     * @param align
     */
    private static void addTextJson(JSONArray array, int size, String text, int align) {
        try {
            JSONObject json = new JSONObject();
            json.put("type", PrinterManager.PRINT_CONTENT_TYPE_TEXT);
            json.put("size", size);
            json.put("text", text);
            json.put("align", align);
            array.put(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印条形码
     * 
     * @param array
     * @param barCode
     */
    private static void addBarCode(JSONArray array, String barCode, int alignment) {
        try {
            JSONObject json = new JSONObject();
            json.put("type", PrinterManager.PRINT_CONTENT_TYPE_BAR_CODE);
            json.put("barCode", barCode);
            json.put("align", alignment);
            array.put(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印二维码
     * 
     * @param array
     * @param barCode
     */
    private static void addQrCode(JSONArray array, String qrCode, int alignment) {
        try {
            JSONObject json = new JSONObject();
            json.put("type", PrinterManager.PRINT_CONTENT_TYPE_QR_CODE);
            json.put("qrCode", qrCode);
            json.put("align", alignment);
            array.put(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 两端打印文字(部分机型不支持，谨慎使用)
     * 
     * @param array
     * @param size
     * @param textLeftAlign
     * @param textRightAlign
     */
    private static void addMultiTextJson(JSONArray array, int size, String textLeftAlign, String textRightAlign) {

        try {
            JSONObject json = new JSONObject();
            json.put("type", PrinterManager.PRINT_CONTENT_TYPE_MULTI_TEXT);
            json.put("size", size);
            json.put("textLeftAlign", textLeftAlign);
            json.put("textRightAlign", textRightAlign);
            array.put(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 打印文字
     * 
     * @param array
     * @param size
     * @param text
     * @param align
     */
    private static void addCmdJson(JSONArray array, String cmd) {
        try {
            JSONObject json = new JSONObject();
            json.put("type", PrinterManager.PRINT_CONTENT_TYPE_TEXT);
            json.put("size", FONT_DEFAULT);
            json.put("text", cmd);
            json.put("align", PrinterManager.CONTENT_ALIGN_LEFT);
            array.put(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开钱箱指令</br>
     * 条件：钱箱与外置打印机连接</br>
     * 将此字符串，以打印普通文字的形式传给打印机即可。
     * 
     * @return
     */
    private static String getOpenBoxCmd() {
        StringBuilder builder = new StringBuilder();
        builder.append((char) 27);
        builder.append((char) 112);
        builder.append((char) 0);
        builder.append((char) 200);
        builder.append((char) 200);
        return builder.toString();
    }

    /**
     * bitmap转为base64
     * 
     * @param bitmap
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
