package test;

import java.io.ByteArrayOutputStream;

/**
 * @Auther: sunjx
 * @Date: 2018/8/9 0009 13:29
 * @Description: BCD转码
 */
public class Main {
    public static void main(String[] args) {


        byte[] bytes = hexStringToByte("0D02");
        System.out.println("BCD码 => " + bytesToHexString(bytes));

        byte temp = 0x00;
        for (int i = 0; i < bytes.length; i++) {
            System.out.println("字节 => " + bytes[i] + ",BCD => " + bytesToHexString(new byte[]{bytes[i]}));
            if(i == 0){
                temp = bytes[i];
                continue;
            }
            temp^=bytes[i];
        }
        System.out.println("异或结果: 字节 => " + temp + ",BCD => " + bytesToHexString(new byte[]{temp}));

//        String s = "C50100C711046003921049012304FDB71801";
//        byte[] hexBytes = hexStringToByte(s);
//        byte temp = 0x00;
//        for (int i = 0; i < hexBytes.length; i++) {
//            System.out.println(bytesToHexString(new byte[]{hexBytes[i]}));
//            if(i == 0){
//                temp = hexBytes[i];
//                continue;
//            }
//            temp^=hexBytes[i];
//        }
//        System.out.println(bytesToHexString(new byte[]{temp}));

//        String s = "12345678901";
//        byte[] bcd = bcd(s);
//        for (int i = 0; i < bcd.length; i++) {
//            System.out.println(bytesToHexString(new byte[]{bcd[i]}));
//        }
//        System.out.println("BCD码转字符串 => " + bytesToHexString(bcd));
    }

    /**
     * BCD字节数组转数字字符串
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 数字字符串转BCD字节数组
     */
    public static byte[] bcd(String s) {
        if (s.length() % 2 != 0) {
            s = "0" + s;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i += 2) {
            int high = cs[i] - 48;
            int low = cs[i + 1] - 48;
            baos.write(high << 4 | low);
        }
        return baos.toByteArray();
    }

    /**
     * 把16进制字符串转换成字节数组
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }
}
