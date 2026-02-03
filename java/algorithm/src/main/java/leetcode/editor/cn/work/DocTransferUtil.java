// package leetcode.editor.cn.work;
//
// import com.fasterxml.jackson.annotation.JsonProperty;
// import lombok.Builder;
// import lombok.Data;
// import lombok.ToString;
// import org.apache.commons.lang3.StringUtils;
// import tools.jackson.databind.util.JSONPObject;
//
// import java.io.Serializable;
// import java.lang.reflect.Field;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Objects;
//
// /**
//  * 文档转换工具类: 仅仅适用于杭州市全民健康信息互联互通文档
//  *
//  * @author xuweizhi
//  * @since 2022/03/09 14:36
//  */
// public class DocTransferUtil {
//
//
//     public static final int FLAG_INDEX = 6;
//     public static final int ZERO = 0;
//
//     private static final Map<String, Doc> cache = new HashMap<>();
//
//     @Data
//     @Builder
//     @ToString
//     public static class Doc {
//
//         /**
//          * 字段名称
//          */
//         private String fieldName;
//
//         /**
//          * 字段类型
//          */
//         private String fieldType;
//
//         /**
//          * 是否必填
//          */
//         private Boolean require;
//
//         /**
//          * 字段描述
//          */
//         private String fieldDefinition;
//
//         /**
//          * 字段描述
//          */
//         private String fieldDescription;
//
//         /**
//          * 规则约束
//          */
//         private String ruleLimit;
//
//         /**
//          * 国标字段
//          */
//         private String nationalStandardField;
//
//     }
//
//     /**
//      * 解析 content 返回  doc list
//      *
//      * @param content //
//      * @return //
//      */
//     public static List<Doc> resolveContent(String content) {
//         String[] split = content.split("\n");
//
//         List<Doc> docList = new ArrayList<>();
//         for (String singleContent : split) {
//             char[] chars = singleContent.toCharArray();
//             int index = ZERO;
//             int[] flagIndex = new int[FLAG_INDEX];
//             for (int i = ZERO; i < chars.length; i++) {
//                 if (chars[i] == '\t') {
//                     flagIndex[index++] = i;
//                 }
//             }
//             int startIndex = ZERO;
//             Doc doc = Doc.builder()
//                     .fieldName(getValue(singleContent, flagIndex, startIndex++))
//                     .fieldType(getValue(singleContent, flagIndex, startIndex++))
//                     .require(StringUtils.isNotBlank(getValue(singleContent, flagIndex, startIndex++)))
//                     .fieldDescription(getValue(singleContent, flagIndex, startIndex++))
//                     .fieldDefinition(getValue(singleContent, flagIndex, startIndex++))
//                     .ruleLimit(getValue(singleContent, flagIndex, startIndex++))
//                     .nationalStandardField(getValue(singleContent, flagIndex, startIndex))
//                     .build();
//             docList.add(doc);
//         }
//         return docList;
//     }
//
//     /**
//      * 获取value 值
//      *
//      * @param content    //
//      * @param flagIndex  //
//      * @param startIndex //
//      * @return //
//      */
//     public static String getValue(String content, int[] flagIndex, int startIndex) {
//         if (startIndex == ZERO) {
//             return content.substring(ZERO, flagIndex[startIndex]);
//         } else if (startIndex == FLAG_INDEX) {
//             return content.substring(flagIndex[startIndex - 1]);
//         } else {
//             return content.substring(flagIndex[startIndex - 1], flagIndex[startIndex]);
//         }
//
//     }
//
//     public static void main(String[] args) throws IllegalAccessException {
//         // 检验申请单
//         // String json = "{\"age\":30,\"applyDeptId\":\"\",\"applyDeptName\":\"\",\"applyId\":\"922001\",\"applyItemCode\":\"MR-00000004\",\"applyItemName\":\"颈椎一部位MR1.5T\",\"applyName\":\"颈椎一部位MR1.5T\",\"applyOrgId\":\"47033256033018311A2221\",\"applyOrgName\":\"富阳区中医骨伤医院\",\"applyTime\":\"2022-04-29T08:24:32\",\"beAppliedOrgId\":\"47033256033018311A2221\",\"beAppliedOrgName\":\"富阳区中医骨伤医院\",\"campusDeptId\":\"40014031\",\"campusDeptName\":\"测试急诊门诊\",\"campusDiagnosisCode\":\"4957802\",\"campusDiagnosisName\":\"骨折病\",\"campusStandarDeptName\":\"\",\"campusStandardDeptCode\":\"\",\"cancel\":\"1\",\"diagnosisCode\":\"BGG000\",\"diagnosisName\":\"骨折病\",\"docotorName\":\"张吉翔\",\"doctorIdCard\":\"32010319910605637X\",\"examinationTarget\":\" \",\"gender\":\"1\",\"genderName\":\"男\",\"healthcareRecordId\":\"525791\",\"idCardNum\":\"34032319910605637X\",\"idCardType\":\"01\",\"idCardTypeName\":\"居民身份证\",\"itemNum\":\"1\",\"patientCardNum\":\"10694862\",\"patientCardType\":\"1_4.03\",\"patientCardTypeName\":\"病历号\",\"patientName\":\"富阳骨伤门急诊测试\",\"specialInspectionMark\":\"1\",\"type\":\"2\",\"typeName\":\"急诊\"}";
//         // 检查申请但
//         String json = "{\"age\":30,\"applyDeptId\":\"\",\"applyDeptName\":\"\",\"applyId\":\"100003004\",\"applyItemCode\":\"JY-00000612\",\"applyItemName\":\"血沉\",\"applyName\":\"血沉\",\"applyOrgId\":\"47033256033018311A2221\",\"applyOrgName\":\"富阳区中医骨伤医院\",\"applyTime\":\"2022-04-29T10:37:32\",\"beAppliedOrgId\":\"47033256033018311A2221\",\"beAppliedOrgName\":\"富阳区中医骨伤医院\",\"brith\":\"1991-06-05\",\"campusApplyDeptId\":\"\",\"campusApplyDeptName\":\"\",\"campusDiagnosisCode\":\"4957672\",\"campusDiagnosisName\":\"儿科虫病（小儿绦虫病）\",\"campusStandardDeptCode\":\"\",\"campusStandardDeptName\":\"\",\"cancel\":\"0\",\"day\":\" \",\"diagnosisCode\":\"BEC000\",\"diagnosisName\":\"儿科虫病（小儿绦虫病）\",\"docotorName\":\"陈猛\",\"doctorIdCard\":\"cm223\",\"doctorOrderId\":\"144521201\",\"examinationItemCode\":\"JY-00000612\",\"examinationItemName\":\"血沉\",\"gender\":\"1\",\"genderName\":\"男\",\"healthcareRecordId\":\"526792\",\"hour\":\" \",\"idCardNum\":\"34032319910605637X\",\"idCardType\":\"01\",\"idCardTypeName\":\"居民身份证\",\"itemNum\":\"418424910017251630\",\"month\":\" \",\"patientCardNum\":\"10694862\",\"patientCardType\":\"1_4.03\",\"patientCardTypeName\":\"病历号\",\"patientName\":\"富阳骨伤门急诊测试\",\"patientPhone\":\"17681885405\",\"type\":\"2\",\"typeName\":\"急诊\"}";
//
//         // String content = getString();
//         String content = "WS02_01_039_001\tVARCHAR2(50)\tY\t本人姓名\t本人在公安管理部门正式登记注册的姓氏和名称\t\tDE02.01.039.00\n" +
//                 "WS02_01_040_01\tVARCHAR2(20)\t\t性别代码\t本人生理性别的代码\t1、在【GB/T 2261.1人的性别代码】值域范围内，且与【性别名称】匹配\tDE02.01.040.00\n" +
//                 "CT02_01_040_01\tVARCHAR2(100)\t\t性别名称\t本人生理性别的名称\t\t\n" +
//                 "WS02_01_026_01\tNUMBER(3)\t\t年龄(岁)\t患者年龄满1周岁的实足年龄，为患者出生后按照日历计算的历法年龄，以实足年龄的相应整数填写\t1、类型为数值\tDE02.01.026.00\n" +
//                 "WS02_01_032_02\tVARCHAR2(10)\t\t年龄(月)\t年龄不足1周岁的实足年龄的月龄，以分数形式表示：分数的整数部分代表实足月龄，分数部分分母为30，分子为不足1个月的天数（例如12/30）\t\tDE02.01.032.00\n" +
//                 "WS99_99_026_01\tVARCHAR2(10)\t\t年龄（日）\t年龄不足1周月的实足年龄的日龄\t\t\n" +
//                 "WS99_99_026_02\tVARCHAR2(10)\t\t年龄（小时）\t年龄不足1周日的实足年龄的时龄\t\t\n" +
//                 "WS99_99_903_40\tVARCHAR2(100)\t\t卡号\t根据不同卡类型，患者提供的卡号\t\t\n" +
//                 "WS99_99_902_07\tVARCHAR2(10)\t\t卡类型代码\t患者提供的有效卡的类型代码\t\t\n" +
//                 "CT99_99_902_07\tVARCHAR2(50)\t\t卡类型名称\t患者提供的有效卡的卡类型名称\t\t\n" +
//                 "WS02_01_060_01\tVARCHAR2(20)\t\t患者类型代码\t患者就诊类型的分类代码\t1、在【CV09.00.404患者类型代码】值域范围内，且与【患者类型名称】匹配\tDE02.01.060.00\n" +
//                 "CT02_01_060_01\tVARCHAR2(100)\t\t患者类型名称\t患者就诊类型的分类名称\t\t\n" +
//                 "WS02_01_005_01_01\tDATE(8)\t\t出生日期\t卫生服务对象出生当日的公元纪年日期\t1、符合【yyyyMMdd】的格式\tDE02.01.005.01\n" +
//                 "WS02_01_031_01\tVARCHAR2(20)\tY\t身份证件类别代码\t本人身份证件类别的代码\t1、在【CV02.01.101身份证件类别代码表】值域范围内，且与【身份证件类别名称】匹配\tDE02.01.031.00\n" +
//                 "CT02_01_031_01\tVARCHAR2(100)\tY\t身份证件类别名称\t本人身份证件类别的名称\t\t\n" +
//                 "WS02_01_030_01\tVARCHAR2(32)\tY\t身份证件号码\t身份证件上唯一的法定标识符\t\tDE02.01.030.00\n" +
//                 "WS02_01_010_01\tVARCHAR2(50)\t\t本人电话号码\t本人联系电话的号码，包括国际、国内区号和分机号\t\tDE02.01.010.00\n" +
//                 "WS01_00_010_01\tVARCHAR2(50)\tY\t门(急)诊号\t按照某一特定编码规则赋予门（急）诊就诊对象的顺序号（唯一标识一次门急诊业务，若机构的门诊号采取循环序列，则可联合挂号时间唯一确定一次业务）\t\tDE01.00.010.00\n" +
//                 "WS01_00_008_03\tVARCHAR2(50)\tY\t电子申请单编号\t按照某一特定编码规则赋予电子申请单的顺序号\t\tDE01.00.008.00\n" +
//                 "WS01_00_906_01\tVARCHAR2(20)\t\t申请单名称\t用于描述在一张申请单上检查、检验项目的具体名称（例如血常规、肝功10项）\t\t\n" +
//                 "WS01_00_018_01\tVARCHAR2(50)\t\t报告单编号\t按照某一特定编码规则赋予报告单的顺序号\t报告单未产生时非必填，报告单生成后要求必填且与报告单一同上传\tDE01.00.018.00\n" +
//                 "WS05_01_024_01\tVARCHAR2(20)\t\t疾病诊断编码\t患者所患的疾病诊断特定编码体系中的编码\t1、在【ICD-10国际疾病分类标准编码】值域范围内，且与【疾病诊断名称】匹配\tDE05.01.024.00\n" +
//                 "CT05_01_024_01\tVARCHAR2(100)\t\t疾病诊断名称\t患者所患疾病的西医诊断名称\t\tDE05.01.025.00\n" +
//                 "WS99_99_902_09\tVARCHAR2(10)\tY\t院内疾病诊断代码\t医院内部根据一定的编码规则对疾病进行的编码\t\t\n" +
//                 "CT99_99_902_09\tVARCHAR2(100)\tY\t院内疾病诊断名称\t医院内部根据一定的编码规则对疾病进行的编码\t\t\n" +
//                 "WS06_00_901_01\tVARCHAR2(3)\tY\t作废标识\t标识该医嘱（处方）、申请单等是否作废（0否【未作废】；1是【作废】）\t1、在【CT09.00.001布尔代码】值域范围内\t\n" +
//                 "WS01_00_907_01\tVARCHAR2(20)\tY\t项目序号\t用于描述本条记录的排列顺序号码\t\t\n" +
//                 "WS04_30_019_01\tVARCHAR2(30)\t\t检查/检验项目编码\t受检者检查/检验项目在特定编码体系中的编码,如LOINC的代码值\t\tDE04.30.019.00\n" +
//                 "WS04_30_020_01\tVARCHAR2(100)\t\t检查/检验项目名称\t检查报告项目的名称\t\tDE04.30.020.00\n" +
//                 "WS01_00_903_01\tVARCHAR2(50)\t\t医嘱号\t医嘱顺序号\t\t\n" +
//                 "WS02_10_916_01\tVARCHAR2(50)\tY\t申请项目代码\t申请检查项目在机构诊疗项目目录中的编码\t\t\n" +
//                 "WS02_10_916_02\tVARCHAR2(100)\tY\t申请项目名称\t申请检查项目的名称\t\t\n" +
//                 "WS02_01_039_097\tVARCHAR2(50)\t\t申请人姓名\t开立检验或检查申请人签署的在公安户籍管理部门正式登记注册的姓氏和名称\t\t\n" +
//                 "WS99_99_030_03\tVARCHAR2(18)\t\t申请医生身份证号\t申请医生身份证件上唯一的法定标识符\t\t\n" +
//                 "WS06_00_917_01\tDATE(14)\tY\t申请日期时间\t提交申请时的公元纪年日期和时间的完整描述\t1、符合【yyyyMMddHHmmss】的格式\t\n" +
//                 "WS08_10_025_02\tVARCHAR2(100)\t\t申请科室代码\t申请检查、检验的科室代码\t1、在【CT08.00.002科室代码】值域范围内，且与【申请科室名称】匹配\tDE08.10.026.00\n" +
//                 "CT08_10_025_02\tVARCHAR2(80)\t\t申请科室名称\t申请检查、检验的科室名称\t\t\n" +
//                 "WS99_99_902_95\tVARCHAR2(30)\t\t院内申请科室代码\t院内申请科室代码\t\t\n" +
//                 "CT99_99_902_95\tVARCHAR2(100)\t\t院内申请科室名称\t院内申请科室名称\t\t\n" +
//                 "CT08_01_025_28\tVARCHAR2(200)\tY\t医院规范科室名称\t杭州市医政医管科室单元名称\t在【CT08.10.012 医院规范科室代码表】值域范围内，且与【医院规范科室代码】匹配\t\n" +
//                 "WS08_01_025_28\tVARCHAR2(200)\tY\t医院规范科室代码\t杭州市医政医管科室单元代码\t在【CT08.10.012 医院规范科室代码表】值域范围内，且与【医院规范科室代码】匹配\t\n" +
//                 "WS08_10_052_02\tVARCHAR2(60)\tY\t申请机构代码\t申请作检查、检验项目的医疗机构的代码\t1、在【CT99.00.001机构代码（本地化）】值域范围内，且与【申请机构名称】匹配\tDE08.10.013.00\n" +
//                 "CT08_10_052_02\tVARCHAR2(120)\tY\t申请机构名称\t申请作检查、检验项目的医疗机构的名称\t\t\n" +
//                 "WS08_10_052_22\tVARCHAR2(60)\t\t被申请机构代码\t被申请作检查、检验项目的医疗机构的代码\t1、在【CT99.00.001机构代码（本地化）】值域范围内，且与【被申请机构名称】匹配\t\n" +
//                 "CT08_10_052_22\tVARCHAR2(120)\t\t被申请机构名称\t被申请作检查、检验项目的医疗机构的名称\t\t";
//         extracted(json, content, ApplyLabTestThirdDTO.class);
//     }
//
//     private static <T> void extracted(String json, String content, Class<T> clazz) throws IllegalAccessException {
//         T source = JSONPObject.fromJson(json, clazz);
//         printJavaString(content);
//         // 简述、字段名称、字段名称、必填项、长度、是否超过长度、字段值、规则约束、国标、内容
//         String format = "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s";
//
//         Field[] declaredFields = clazz.getDeclaredFields();
//         for (Field field : declaredFields) {
//             field.setAccessible(true);
//             JsonProperty annotation = field.getAnnotation(JsonProperty.class);
//             String docFieldName = annotation.value();
//             String fieldName = field.getName();
//             Object value = field.get(source);
//             Doc doc = cache.get(docFieldName);
//             if (doc == null) {
//                 continue;
//             }
//             // 简述、字段名称、字段名称、必填项、字段值、规则约束、国标、内容
//             System.out.println(String.format(format,
//                     doc.getFieldDescription(), docFieldName,
//                     fieldName, doc.getRequire(),
//                     parseLength(doc.getFieldType()), !Objects.isNull(value) && value.toString().length() > parseLength(doc.getFieldType()),
//                     value, doc.getRuleLimit(),
//                     doc.getNationalStandardField(), doc.getFieldDefinition())
//             );
//
//         }
//         System.out.println(cache.values().stream().filter(Doc::getRequire).count());
//     }
//
//     // 检查申请单
//     private static String getString() {
//         String content = "WS02_01_039_001\tVARCHAR2(50)\tY\t本人姓名\t本人在公安管理部门正式登记注册的姓氏和名称\t\tDE02.01.039.00\n" +
//                 "WS02_01_040_01\tVARCHAR2(20)\t\t性别代码\t本人生理性别的代码\t1、在【GB/T 2261.1人的性别代码】值域范围内，且与【性别名称】匹配\tDE02.01.040.00\n" +
//                 "CT02_01_040_01\tVARCHAR2(100)\t\t性别名称\t本人生理性别的名称\t\t\n" +
//                 "WS02_01_026_01\tNUMBER(3)\t\t年龄(岁)\t患者年龄满1周岁的实足年龄，为患者出生后按照日历计算的历法年龄，以实足年龄的相应整数填写\t1、类型为数值\tDE02.01.026.00\n" +
//                 "WS02_01_032_02\tVARCHAR2(10)\t\t年龄(月)\t年龄不足1周岁的实足年龄的月龄，以分数形式表示：分数的整数部分代表实足月龄，分数部分分母为30，分子为不足1个月的天数（例如12/30）\t\tDE02.01.032.00\n" +
//                 "WS99_99_026_01\tVARCHAR2(10)\t\t年龄（日）\t年龄不足1周月的实足年龄的日龄\t\t\n" +
//                 "WS99_99_026_02\tVARCHAR2(10)\t\t年龄（小时）\t年龄不足1周日的实足年龄的时龄\t\t\n" +
//                 "WS99_99_903_40\tVARCHAR2(100)\t\t卡号\t根据不同卡类型，患者提供的卡号\t\t\n" +
//                 "WS99_99_902_07\tVARCHAR2(10)\t\t卡类型代码\t患者提供的有效卡的类型代码\t\t\n" +
//                 "CT99_99_902_07\tVARCHAR2(50)\t\t卡类型名称\t患者提供的有效卡的卡类型名称\t\t\n" +
//                 "WS02_01_031_01\tVARCHAR2(20)\tY\t身份证件类别代码\t本人身份证件类别的代码\t1、在【CV02.01.101身份证件类别代码表】值域范围内，且与【身份证件类别名称】匹配\tDE02.01.031.00\n" +
//                 "CT02_01_031_01\tVARCHAR2(100)\tY\t身份证件类别名称\t本人身份证件类别的名称\t\t\n" +
//                 "WS02_01_030_01\tVARCHAR2(32)\tY\t身份证件号码\t身份证件上唯一的法定标识符\t\tDE02.01.030.00\n" +
//                 "WS02_01_060_01\tVARCHAR2(20)\t\t患者类型代码\t患者就诊类型的分类代码\t1、在【CV09.00.404患者类型代码】值域范围内，且与【患者类型名称】匹配\tDE02.01.060.00\n" +
//                 "CT02_01_060_01\tVARCHAR2(100)\t\t患者类型名称\t患者就诊类型的分类名称\t\t\n" +
//                 "WS01_00_010_01\tVARCHAR2(50)\tY\t门(急)诊号\t按照某一特定编码规则赋予门（急）诊就诊对象的顺序号（唯一标识一次门急诊业务，若机构的门诊号采取循环序列，则可联合挂号时间唯一确定一次业务）\t\tDE01.00.010.00\n" +
//                 "WS01_00_008_03\tVARCHAR2(50)\tY\t电子申请单编号\t按照某一特定编码规则赋予电子申请单的顺序号\t\tDE01.00.008.00\n" +
//                 "WS01_00_906_01\tVARCHAR2(20)\t\t申请单名称\t用于描述在一张申请单上检查、检验项目的具体名称（例如血常规、肝功10项）\t\t\n" +
//                 "WS05_01_024_01\tVARCHAR2(20)\t\t疾病诊断编码\t患者所患的疾病诊断特定编码体系中的编码\t1、在【ICD-10国际疾病分类标准编码】值域范围内，且与【疾病诊断名称】匹配\tDE05.01.024.00\n" +
//                 "CT05_01_024_01\tVARCHAR2(100)\t\t疾病诊断名称\t患者所患疾病的西医诊断名称\t\tDE05.01.025.00\n" +
//                 "WS99_99_902_09\tVARCHAR2(10)\t\t院内疾病诊断代码\t医院内部根据一定的编码规则对疾病进行的编码\t\t\n" +
//                 "CT99_99_902_09\tVARCHAR2(100)\t\t院内疾病诊断名称\t医院内部根据一定的编码规则对疾病进行的编码\t\t\n" +
//                 "WS04_01_117_02\tVARCHAR2(100)\t\t症状描述\t对患者出现症状的详细描述\t\tDE04.01.117.00\n" +
//                 "WS01_00_907_01\tVARCHAR2(20)\tY\t项目序号\t用于描述本条记录的排列顺序号码\t\t\n" +
//                 "WS01_00_903_01\tVARCHAR2(50)\t\t医嘱号\t医嘱顺序号\t\t\n" +
//                 "WS02_10_916_01\tVARCHAR2(50)\tY\t申请项目代码\t申请检查项目在机构诊疗项目目录中的编码\t\t\n" +
//                 "WS02_10_916_02\tVARCHAR2(100)\tY\t申请项目名称\t申请检查项目的名称\t\t\n" +
//                 "WS06_00_917_01\tDATE(14)\tY\t申请日期时间\t提交申请时的公元纪年日期和时间的完整描述\t1、符合【yyyyMMddHHmmss】的格式\t\n" +
//                 "WS06_00_901_01\tVARCHAR2(3)\t\t作废标识\t标识该医嘱（处方）、申请单等是否作废（0否【未作废】；1是【作废】）\t1、在【CT09.00.001布尔代码】值域范围内\t\n" +
//                 "WS04_30_901_01\tVARCHAR2(100)\t\t检查目的及要求\t申请单中描述的本次检查要达到的目的及一些检查要求\t\t\n" +
//                 "WS02_01_079_01\tVARCHAR2(3)\t\t特殊检查标志\t标识患者有无特殊检查操作经历的标志（1无；2有）\t1、在【CT02.01.001有无情况代码】值域范围内\tDE02.01.079.00\n" +
//                 "WS02_01_039_097\tVARCHAR2(50)\t\t申请人姓名\t开立检验或检查申请人签署的在公安户籍管理部门正式登记注册的姓氏和名称\t\t\n" +
//                 "WS99_99_030_03\tVARCHAR2(18)\t\t申请医生身份证号\t申请医生身份证件上唯一的法定标识符\t\t\n" +
//                 "WS08_10_025_02\tVARCHAR2(100)\t\t申请科室代码\t申请检查、检验的科室代码\t1、在【CT08.00.002科室代码】值域范围内，且与【申请科室名称】匹配\tDE08.10.026.00\n" +
//                 "CT08_10_025_02\tVARCHAR2(80)\t\t申请科室名称\t申请检查、检验的科室名称\t\t\n" +
//                 "WS99_99_902_08\tVARCHAR2(30)\t\t院内科室代码\t医院内部的科室按照一定的编码规则编写的代码\t\t\n" +
//                 "CT99_99_902_08\tVARCHAR2(200)\t\t院内科室名称\t按照医院内部科室命名规则命名的科室名称\t\t\n" +
//                 "CT08_01_025_28\tVARCHAR2(200)\tY\t医院规范科室名称\t杭州市医政医管科室单元名称\t在【CT08.10.012 医院规范科室代码表】值域范围内，且与【医院规范科室代码】匹配\t\n" +
//                 "WS08_01_025_28\tVARCHAR2(200)\tY\t医院规范科室代码\t杭州市医政医管科室单元代码\t在【CT08.10.012 医院规范科室代码表】值域范围内，且与【医院规范科室名称】匹配\t\n" +
//                 "WS08_10_052_02\tVARCHAR2(60)\tY\t申请机构代码\t申请作检查、检验项目的医疗机构的代码\t1、在【CT99.00.001机构代码（本地化）】值域范围内，且与【申请机构名称】匹配\tDE08.10.013.00\n" +
//                 "CT08_10_052_02\tVARCHAR2(120)\tY\t申请机构名称\t申请作检查、检验项目的医疗机构的名称\t\t\n" +
//                 "WS08_10_052_22\tVARCHAR2(60)\t\t被申请机构代码\t被申请作检查、检验项目的医疗机构的代码\t1、在【CT99.00.001机构代码（本地化）】值域范围内，且与【被申请机构名称】匹配\t\n" +
//                 "CT08_10_052_22\tVARCHAR2(120)\t\t被申请机构名称\t被申请作检查、检验项目的医疗机构的名称\t\t";
//         return content;
//     }
//
//
//     public static int parseLength(String source) {
//         int i = source.indexOf("(");
//         String substring = source.substring(++i, source.length() - 1);
//         return Integer.parseInt(substring);
//     }
//
//
//     public static void printJavaString(String content) {
//         List<Doc> docList = resolveContent(content);
//         List<Class<? extends Serializable>> classes = Arrays.asList(ApplyExaminationThirdDTO.class, ApplyLabTestThirdDTO.class, ExaminationThirdDTO.class,
//                 LabTestThirdDTO.class, LabTestCellThridDTO.class, LabTestNormalThridDTO.class);
//         Map<String, String> mappings = new HashMap<>();
//
//         for (Class<? extends Serializable> clazz : classes) {
//             Field[] declaredFields = clazz.getDeclaredFields();
//             for (Field declaredField : declaredFields) {
//                 declaredField.setAccessible(true);
//                 JsonProperty annotation = declaredField.getAnnotation(JsonProperty.class);
//                 if (annotation == null) {
//                     continue;
//                 }
//                 String value = annotation.value();
//                 String s = mappings.get(value);
//                 if (StringUtils.isBlank(s)) {
//                     mappings.put(value, declaredField.getName());
//                 }
//             }
//         }
//
//         for (Doc doc : docList) {
//             String fieldType = doc.getFieldType().trim();
//             String length = getLength(fieldType);
//             if (fieldType.toUpperCase().startsWith("VARCHAR")) {
//                 fieldType = "String";
//
//             } else if (fieldType.startsWith("NUMBER")) {
//                 fieldType = "Integer";
//             } else if (fieldType.startsWith("DATE")) {
//                 fieldType = "LocalDateTime";
//             }
//             StringBuilder sb = new StringBuilder();
//             sb.append("/**\n");
//             sb.append("* 字段描述: ").append(doc.getFieldDescription().trim()).append("(长度:").append(length).append(")\n");
//             sb.append("* 字段定义: ").append(doc.getFieldDefinition().trim()).append("\n");
//             if (StringUtils.isNotBlank(doc.getRuleLimit().trim())) {
//                 sb.append("* 规则: ").append(doc.getRuleLimit().trim()).append("\n");
//             }
//             if (StringUtils.isNotBlank(doc.getNationalStandardField())) {
//                 sb.append("* 国标字段: ").append(doc.getNationalStandardField().trim()).append("\n");
//             }
//             sb.append("*/\n");
//             if (fieldType.equals("LocalDateTime")) {
//                 sb.append("@JsonFormat(pattern = \"yyyyMMddHHmmss\")\n");
//             }
//             if (doc.getRequire()) {
//                 sb.append("@NotNull(message=\"").append(doc.getFieldDescription().trim()).append("不能为空\")\n");
//             }
//
//             sb.append("@JsonProperty(\"" + doc.getFieldName().trim() + "\")\n");
//
//             String fieldName = doc.getFieldName();
//             // if (StringUtils.isNotBlank(mappings.get(fieldName))) {
//             //     fieldName = mappings.get(fieldName);
//             // }
//             sb.append("private ").append(fieldType).append(" ").append(fieldName).append(";\n");
//             cache.put(doc.getFieldName(), doc);
//
//         }
//
//     }
//
//     private static String getLength(String fieldType) {
//         return fieldType.substring(fieldType.indexOf('(') + 1, fieldType.indexOf(')'));
//     }
// }
//
