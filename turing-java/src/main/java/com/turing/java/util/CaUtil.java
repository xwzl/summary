package com.turing.java.util;

import com.alibaba.fastjson.JSONObject;
import com.turing.java.util.res.CaResponse;
import com.turing.java.util.res.CertEnrollRes;
import com.turing.java.util.res.FetchUserSealRes;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
import java.util.*;
import java.util.Map.Entry;

/**
 * @author xuweizhi
 * @since 2020/12/24 17:04
 */
public class CaUtil {

    private static final String APP_SECRET = "d41a60927a23480d84af5b2cb35bca72";

    private static final String URL =
        "http://119.6.248.58:9527/cloud-certificate-service/api/cloudCert/open/cert/signExt";
    private static final String userSignConfig =
        "http://119.6.248.58:9527/signature-server/api/open/signature/userSignConfig";
    private static final String fetchUserSeal =
        "http://119.6.248.58:9527/signature-server/api/open/signature/fetchUserSeal";
    private static final String signPdf = "http://119.6.248.58:9527/signature-server/api/open/signature/signPdf";
    private static final String fetchSignedFile =
        "http://119.6.248.58:9527/signature-server/api/open/signature/fetchSignedFile";

    public static final String PDF_PATH = "D:\\root\\turing\\turing-java\\普通药品处方.pdf";

    private static String userId = "ssssssssssssssssssss";

    /**
     * {"image":"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","imgWidth":"200","imgHeight":"200","pageNo":"1","x":"300","y":"400","entityType":"Personal","personalName":"周江场景证书测试","personalIdNumber":"502268199002023614"}
     */
    public static void main(String[] args) throws IllegalAccessException {
        CaResponse<CertEnrollRes> extracted = extracted();
        getUserSignConfig(extracted);
        getFetchUserSeal();
        CaResponse<List<LinkedHashMap<String, String>>> pdfSign = getPdfSign();
        FetchSignedFile fetchSignedFile1 = new FetchSignedFile();
        fetchSignedFile1.setFileId(pdfSign.getBody().get(0).get("fileId"));
        fetchSignedFile1.setUserId(userId);
        getResponseMessage(fetchSignedFile1, fetchSignedFile, new ParameterizedTypeReference<CaResponse<Object>>() {});
    }

    private static CaResponse<List<LinkedHashMap<String, String>>> getPdfSign() throws IllegalAccessException {
        String base64Str = imageToBase64Str("D:\\root\\turing\\turing-java\\kubernetes.png");
        SignPdf signPdf1 = SignPdf.builder().userId(userId).configKey(userId).cloudCertPass(userId).build();
        ArrayList<Integer> pageList = new ArrayList<>();
        pageList.add(1);
        PdfSignParam pdfSignParam =
            PdfSignParam.builder().llx(50).lly(100).urx(150).ury(200).pageList(pageList).sealImg(base64Str).build();
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(pdfSignParam);
        signPdf1.setSignParams(JSONObject.toJSONString(objects));
        return getPdfSignRest(signPdf1).getBody();
    }

    private static ResponseEntity<CaResponse<List<LinkedHashMap<String, String>>>> getPdfSignRest(SignPdf signPdf1)
        throws IllegalAccessException {
        TreeMap<String, String> paramMap = new TreeMap<>();
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        getSingMap(signPdf1, paramMap, postParameters);
        postParameters.add("pdfFile", new FileSystemResource(PDF_PATH));
        HttpHeaders headers = getHttpHeaders(getSignature(getToSign(signPdf1, paramMap)), "multipart/form-data");

        return restTemplate.exchange(signPdf, HttpMethod.POST, new HttpEntity<>(postParameters, headers),
            new ParameterizedTypeReference<CaResponse<List<LinkedHashMap<String,String>>>>() {});
    }

    private static void getFetchUserSeal() throws IllegalAccessException {
        FetchUserSeal fetchUserSeal1 = new FetchUserSeal();
        fetchUserSeal1.setUserId(userId);
        CaResponse<List<FetchUserSealRes>> responseMessage = getResponseMessage(fetchUserSeal1, fetchUserSeal,
            new ParameterizedTypeReference<CaResponse<List<FetchUserSealRes>>>() {});
        System.out.println(responseMessage);
        FetchUserSealRes fetchUserSealRes = responseMessage.getBody().get(0);
        String sealImg = fetchUserSealRes.getSealImg();
        base64StrToImage(sealImg, "D:\\root\\turing\\turing-java\\re.png");
    }

    private static void getUserSignConfig(CaResponse<CertEnrollRes> extracted) throws IllegalAccessException {
        String base64Str = imageToBase64Str("D:\\root\\turing\\turing-java\\kubernetes.png");
        // UserSignConfig userSignConfig1 = UserSignConfig.builder().userId(userId).configKey(userId).keypairType("3")
        // .certSn(extracted.getBody().getCertSerialnumber()).signType("4").sealImg(base64Str).sealType("1")
        // .signTemplate("1").build();
        UserSignConfig userSignConfig1 = UserSignConfig.builder().userId(userId).configKey(userId).keypairType("3")
            .certSn(extracted.getBody().getCertSerialnumber()).signType("1").sealImg(base64Str).sealType("1")
            .signTemplate("1").build();
        CaResponse<Object> responseMessage = getResponseMessage(userSignConfig1, userSignConfig,
            new ParameterizedTypeReference<CaResponse<Object>>() {});
        System.out.println(responseMessage);
    }

    private static CaResponse<CertEnrollRes> extracted() throws IllegalAccessException {
        CertEnroll certEnroll = CertEnroll.builder().entityId(userId).entityType("Organizational").orgName("华府医院")
            .orgNumber("1231231231").pin(userId).orgDept("信息科").province("四川省").locality("成都市").personalName("周江场景证书测试")
            .toSign("312312312").personalIdNumber("502268199002023614").personalEmail("1231231@qq.com")
            .personalPhone("15968492657").build();
        CaResponse<CertEnrollRes> responseMessage =
            getResponseMessage(certEnroll, URL, new ParameterizedTypeReference<CaResponse<CertEnrollRes>>() {});
        System.out.println(responseMessage);
        return responseMessage;
    }

    public static boolean base64StrToImage(String imgStr, String path) {
        if (imgStr == null) {
            return false;
        }
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
            // 文件夹不存在则自动创建
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

    private static <T, E> CaResponse<E> getResponseMessage(T data, String url,
        ParameterizedTypeReference<CaResponse<E>> parameterizedTypeReference) throws IllegalAccessException {
        TreeMap<String, String> paramMap = new TreeMap<>();
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        getSingMap(data, paramMap, postParameters);
        HttpHeaders headers = getHttpHeaders(getSignature(getToSign(data, paramMap)), null);
        List<String> signature = headers.get("signature");
        System.out.println(signature);
        System.out.println(data);
        return restTemplate
            .exchange(url, HttpMethod.POST, new HttpEntity<>(postParameters, headers), parameterizedTypeReference)
            .getBody();
    }

    private static String getSignature(String toSign) {
        byte[] hmacSha1 = doHmacSha1(toSign, APP_SECRET);
        return doHex(hmacSha1);
    }

    private static HttpHeaders getHttpHeaders(String signature, String contentType) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", contentType == null ? "application/x-www-form-urlencoded" : contentType);
        headers.add("app_id", "SCCA1326415582401110017");
        headers.add("signature", signature);
        return headers;
    }

    private static <T> String getToSign(T data, TreeMap<String, String> paramMap) {
        Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            Entry<String, Object> next = (Entry)iterator.next();
            if (next.getValue() != null) {
                sb.append(next.getValue()).append("&");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        // 待签名原文
        String toSign = sb.toString();
        if (data instanceof CertEnroll) {
            CertEnroll certEnroll = (CertEnroll)data;
            certEnroll.setToSign(toSign);
        }
        return toSign;
    }

    public static <T> void getSingMap(T data, TreeMap<String, String> paramMap,
        MultiValueMap<String, Object> postParameters) throws IllegalAccessException {
        Class<?> clazz = data.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);

            Optional.ofNullable(declaredField.get(data)).ifPresent(value -> {
                if ("pdfFile".equals(declaredField.getName())) {
                    // String key = declaredField.getName();
                    // paramMap.put(key, value.toString());
                    // postParameters.add(key, value);
                    return;
                }
                String stringValue = (String)value;
                String key = declaredField.getName();
                paramMap.put(key, stringValue);
                postParameters.add(key, stringValue);
            });
        }
    }

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
