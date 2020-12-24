package com.turing.java.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @author xuweizhi
 * @since 2020/12/24 17:04
 */
public class CaUtil {

    private static final String APP_SECRET = "d41a60927a23480d84af5b2cb35bca72";

    private static final String URL = "http://119.6.248.58:9527/cloud-certificate-service/api/cloudCert/open/cert/signExt";
    private static final String userSignConfig = "http://119.6.248.58:9527/cloud-certificate-service/api/cloudCert/open/cert/userSignConfig";

    public static void main(String[] args) throws IllegalAccessException {
        String base64Str = imageToBase64Str("D:\\root\\turing\\turing-java\\kubernetes.png");
        UserSignConfig userSignConfig1 = UserSignConfig.builder()
                .userId("19101017081245511")
                .configKey("1111111111")
                .keypairType("3")
                .certSn("4B8FB2467A8D094AEEE8C79269EE67A80BDA672F")
                .signType("6")
                .signParam("{}")
                .sealImg(base64Str)
                .sealType("3")
                .signTemplate("1")
                .build();
        Object responseMessage = getResponseMessage(userSignConfig1, userSignConfig);
         //certSerialnumber -> 4B8FB2467A8D094AEEE8C79269EE67A80BDA672F
        System.out.println(responseMessage);
        extracted();
    }



    private static void extracted() throws IllegalAccessException {
        CertEnroll certEnroll = CertEnroll.builder()
                .entityId("19101017081245511")
                .entityType("Organizational")
                .orgName("华府医院")
                .orgNumber("123123123")
                .pin("123123123")
                .orgDept("信息科")
                .province("四川省")
                .locality("成都市")
                .personalName("张三")
                .toSign("1111")
                .personalIdNumber("502268199002023659")
                .personalEmail("1231231@qq.com")
                .personalPhone("15968492658")
                .build();
        Object responseMessage = getResponseMessage(certEnroll, URL);
        // certSerialnumber -> 4B8FB2467A8D094AEEE8C79269EE67A80BDA672F
        //certSerialnumber -> 4B8FB2467A8D094AEEE8C79269EE67A80BDA672F
        //certBase64 -> MIIDXDCCAwCgAwIBAgIUS4+yRnqNCUru6MeSae5nqAvaZy8wDAYIKoEcz1UBg3UFADBMMQswCQYDVQQGEwJDTjEUMBIGA1UECgwLU0NDQVRFU1RTTTIxEDAOBgNVBAsMB1RFU1RTTTIxFTATBgNVBAMMDFNDRUdCVEVTVFNNMjAeFw0yMDEyMTgwMjUyNDhaFw0yMTAxMTcwMjUyNDhaMIGBMQswCQYDVQQGEwJDTjESMBAGA1UECAwJ5Zub5bed55yBMRIwEAYDVQQHDAnmiJDpg73luIIxFTATBgNVBAoMDOWNjuW6nOWMu+mZojESMBAGA1UECwwJ5L+h5oGv56eRMR8wHQYDVQQDDBbljY7lupzljLvpmaIg5L+h5oGv56eRMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEfkFgO7WTosDmMXZLGs3nb1kjqODS9JcSu5g8QUEf/Sj8pZUCPbSApArKoTpXgyJgF87BjWOabIARRcLnlIFfD6OCAYYwggGCMAwGA1UdEwEB/wQCMAAwDgYDVR0PAQH/BAQDAgbAMFIGCCsGAQUFBwEBBEYwRDBCBggrBgEFBQcwAoY2aHR0cDovLzE4Mi4xMzguMTAxLjE4NDo1MDgyL1RvcENBL2NhY2VydC9zY2VnYl9zbTIuY3J0MG4GA1UdHwRnMGUwY6BhoF+GXWh0dHA6Ly8xODIuMTM4LjEwMS4xODQ6NTA4Mi9Ub3BDQS9wdWJsaWMvaXRydXNjcmw/Q0E9NzQzRDg5RDE3NjIzMUNBQzUxMDYxMzUxMzEzMEM2RkRFNkVFRTFEQTAfBgNVHSMEGDAWgBRLwu3WSEP7ZI04xxfT94wuKuTCfzAdBgNVHQ4EFgQUT0Ba21KJBBO5bfdR6l56w6BuRuQwPQYDVR0gBDYwNDAyBgYqgRyG8AYwKDAmBggrBgEFBQcCARYaaHR0cDovL3d3dy5zY2NhLmNvbS5jbi9jcHMwHwYHKoEchvAGC
        System.out.println(responseMessage);
    }

    /**
     * base64编码字符串转换为图片
     *
     * @param imgStr base64编码字符串
     * @param path   图片路径
     * @return
     */
    public static boolean base64StrToImage(String imgStr, String path) {
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //文件夹不存在则自动创建
            File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(tempFile);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 图片转base64字符串
     *
     * @param imgFile 图片路径
     * @return
     */
    public static String imageToBase64Str(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    private static <T> Object getResponseMessage(T data, String url) throws IllegalAccessException {
        // 初始化签名参数
        TreeMap<String, String> paramMap = new TreeMap<>();
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        getSingMap(data, paramMap, postParameters);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders(getSignature(getToSign(data, paramMap)));
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);
        return restTemplate.postForObject(url, r, Object.class);
    }

    private static String getSignature(String toSign) {
        byte[] hmacSha1 = doHmacSha1(toSign, APP_SECRET);
        String signature = doHex(hmacSha1);
        return signature;
    }

    private static HttpHeaders getHttpHeaders(String signature) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("app_id", "SCCA1326415582401110017");
        headers.add("signature", signature);
        return headers;
    }

    private static <T> String getToSign(T data, TreeMap<String, String> paramMap) {
        Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            Entry<String, Object> next = (Entry) iterator.next();
            if (next.getValue() != null) {
                sb.append(next.getValue()).append("&");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        // 待签名原文
        String toSign = sb.toString();
        if(data instanceof CertEnroll){
            CertEnroll data1 = (CertEnroll) data;
            data1.setToSign(toSign);
        }
        //certEnroll.setToSign(toSign);
        return toSign;
    }

    public static <T> void getSingMap(T data, TreeMap<String, String> paramMap, MultiValueMap<String, Object> postParameters) throws IllegalAccessException {
        Class<?> clazz = data.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Optional.ofNullable(declaredField.get(data)).ifPresent(value -> {
                String stringValue = (String) value;
                String key = declaredField.getName();
                //if(key.equals("toSign")){
                paramMap.put(key, stringValue);
                //}
                postParameters.add(key, stringValue);
            });
        }
    }

    /**
     * 计算hmac sha1
     *
     * @param data 原文
     * @param key  app_secret
     * @return
     */
    public static byte[] doHmacSha1(String data, String key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] result = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return result;
        } catch (Throwable var4) {
            throw new RuntimeException("hmac签名失败.", var4);
        }
    }

    /**
     * 转换为hex
     *
     * @param bytes
     * @return
     */
    public static String doHex(byte[] bytes) {
        Formatter formatter = new Formatter();
        byte[] var2 = bytes;
        int var3 = bytes.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}
