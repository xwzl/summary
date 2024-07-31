package leetcode.editor.cn.work;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 门(急)诊检查报告
 *
 * @author xuweizhi
 * @since 2022/03/09 14:23
 */
@Data
@SuppressWarnings("all")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExaminationThirdDTO implements Serializable {

    /**
     * 字段描述: 本人姓名(长度:50)
     * 字段定义: 本人在公安管理部门正式登记注册的姓氏和名称
     * 国标字段: DE02.01.039.00
     */
    @NotNull(message = "本人姓名不能为空")
    @JsonProperty("WS02_01_039_001")
    private String patientName;

    /**
     * 字段描述: 性别代码(长度:20)
     * 字段定义: 本人生理性别的代码
     * 规则: 1、在【GB/T 2261.1人的性别代码】值域范围内，且与【性别名称】匹配
     * 国标字段: DE02.01.040.00
     */
    @NotNull(message = "性别代码不能为空")
    @JsonProperty("WS02_01_040_01")
    private String gender;

    /**
     * 字段描述: 性别名称(长度:100)
     * 字段定义: 本人生理性别的名称
     */
    @JsonProperty("CT02_01_040_01")
    private String genderName;

    /**
     * 字段描述: 年龄(岁)(长度:3)
     * 字段定义: 患者年龄满1周岁的实足年龄，为患者出生后按照日历计算的历法年龄，以实足年龄的相应整数填写
     * 规则: 1、类型为数值
     * 国标字段: DE02.01.026.00
     */
    @JsonProperty("WS02_01_026_01")
    private Integer age;

    /**
     * 字段描述: 年龄(月)(长度:10)
     * 字段定义: 年龄不足1周岁的实足年龄的月龄，以分数形式表示：分数的整数部分代表实足月龄，分数部分分母为30，分子为不足1个月的天数（例如12/30）
     * 国标字段: DE02.01.032.00
     */
    @JsonProperty("WS02_01_032_02")
    private String month;

    /**
     * 字段描述: 年龄（日）(长度:10)
     * 字段定义: 年龄不足1周月的实足年龄的日龄
     */
    @JsonProperty("WS99_99_026_01")
    private String day;

    /**
     * 字段描述: 年龄（小时）(长度:10)
     * 字段定义: 年龄不足1周日的实足年龄的时龄
     */
    @JsonProperty("WS99_99_026_02")
    private String hour;

    /**
     * 字段描述: 卡类型名称(长度:50)
     * 字段定义: 患者提供的有效卡的卡类型名称
     */
    @JsonProperty("CT99_99_902_07")
    private String patientCardTypeName;

    /**
     * 字段描述: 卡类型代码(长度:10)
     * 字段定义: 患者提供的有效卡的类型代码
     */
    @JsonProperty("WS99_99_902_07")
    private String patientCardType;

    /**
     * 字段描述: 身份证件类别代码(长度:20)
     * 字段定义: 本人身份证件类别的代码
     * 规则: 1、在【CV02.01.101身份证件类别代码表】值域范围内，且与【身份证件类别名称】匹配
     * 国标字段: DE02.01.031.00
     */
    @NotNull(message = "身份证件类别代码不能为空")
    @JsonProperty("WS02_01_031_01")
    private String idCardType;

    /**
     * 字段描述: 身份证件类别名称(长度:100)
     * 字段定义: 本人身份证件类别的名称
     */
    @JsonProperty("CT02_01_031_01")
    private String idCardTypeName;

    /**
     * 字段描述: 身份证号码(长度:18)
     * 字段定义: 身份证上唯一的法定标识符
     */
    @NotNull(message = "身份证号码不能为空")
    @JsonProperty("WS02_01_906_01")
    private String idCardNum;

    /**
     * 字段描述: 患者ID(长度:50)
     * 字段定义: 患者在医院内部的唯一编号
     */
    @NotNull(message = "患者ID不能为空")
    @JsonProperty("WS99_99_999_16")
    private String patientId;

    /**
     * 字段描述: 门(急)诊号(长度:50)
     * 字段定义: 按照某一特定编码规则赋予门（急）诊就诊对象的顺序号（唯一标识一次门急诊业务，若机构的门诊号采取循环序列，则可联合挂号时间唯一确定一次业务）
     * 国标字段: DE01.00.010.00
     */
    @NotNull(message = "门(急)诊号不能为空")
    @JsonProperty("WS01_00_010_01")
    private String healthcareRecordId;

    /**
     * 字段描述: 电子申请单编号(长度:50)
     * 字段定义: 按照某一特定编码规则赋予电子申请单的顺序号
     * 国标字段: DE01.00.008.00
     */
    @NotNull(message = "电子申请单编号不能为空")
    @JsonProperty("WS01_00_008_03")
    private String applyId;

    /**
     * 字段描述: 报告单编号(长度:50)
     * 字段定义: 按照某一特定编码规则赋予报告单的顺序号
     * 国标字段: DE01.00.018.00
     */
    @NotNull(message = "报告单编号不能为空")
    @JsonProperty("WS01_00_018_01")
    private String applyReportId;

    /**
     * 字段描述: 报告单类别代码(长度:3)
     * 字段定义: 报告单出具时的类别代码描述（1临时报告；2最终报告）
     * 规则: 1、在【CT01.00.003报告单类别代码】值域范围内
     */
    @JsonProperty("WS01_00_900_01")
    private String reportType;

    /**
     * 字段描述: 报告单名称(长度:100)
     * 字段定义: 报告单名称
     */
    @NotNull(message = "报告单名称不能为空")
    @JsonProperty("WS01_00_900_02")
    private String reportName;

    /**
     * 字段描述: 项目序号(长度:20)
     * 字段定义: 用于描述本条记录的排列顺序号码
     */
    @NotNull(message = "项目序号不能为空")
    @JsonProperty("WS01_00_907_01")
    private String projectNum;

    /**
     * 字段描述: 病人影像号(长度:64)
     * 字段定义: 医技系统影像文件中标识患者的唯一号
     */
    @NotNull(message = "病人影像号不能为空")
    @JsonProperty("WS99_99_903_02")
    private String imagePatientId;

    /**
     * 字段描述: 影像序列唯一ID(长度:64)
     * 字段定义: 影像序列唯一ID
     */
    @JsonProperty("WS99_99_903_34")
    private String patientImageUniqueId;

    /**
     * 字段描述: 影像序列描述(长度:1000)
     * 字段定义: 影像序列描述
     */
    @JsonProperty("WS99_99_034_04")
    private String patientImageDescription;

    /**
     * 字段描述: 影像类型(长度:100)
     * 字段定义: 影像类型
     */
    @JsonProperty("WS99_99_902_06")
    private String patientImageType;

    /**
     * 字段描述: 影像唯一ID(长度:64)
     * 字段定义: 影像唯一ID
     * 规则: 检查实例号，例如2.2.940.473.8013.20210701.1083840.258.144650.14465
     */
    @NotNull(message = "影像唯一ID不能为空")
    @JsonProperty("WS99_99_903_33")
    private String imageUniqueId;

    /**
     * 字段描述: 放射性标志(长度:10)
     * 字段定义: 标识此次影像检查是否存在放射性【1，是；2，否】
     * 规则: 1、在【CT05.01.001是否代码】值域范围内
     */
    @JsonProperty("WS99_99_020_08")
    private String radioactiveSign;

    /**
     * 字段描述: 影像数量(长度:12)
     * 字段定义: 影像数量
     * 规则: 1、类型为数值，且精度小于等于2
     */
    @JsonProperty("WS99_99_241_06")
    private Integer imageCount;

    /**
     * 字段描述: 检查类别代码(长度:20)
     * 字段定义: 受检者检查项目所属的类别的代码
     * 规则: 1、在【CT04.10.011检查类别代码】值域范围内，且与【检查类别名称】匹配
     * 国标字段: DE04.30.018.00
     */
    @JsonProperty("WS04_30_018_01")
    private String examinationTypeCode;

    /**
     * 字段描述: 检查类别名称(长度:100)
     * 字段定义: 受检者检验项目所属的类别的名称
     */
    @JsonProperty("CT04_30_018_01")
    private String examinationTypeName;

    /**
     * 字段描述: 院内检查类别代码(长度:30)
     * 字段定义: 院内检查类别代码
     */
    @NotNull(message = "院内检查类别代码不能为空")
    @JsonProperty("WS99_99_902_102")
    private String campusExaminationTypeCode;

    /**
     * 字段描述: 院内检查类别名称(长度:100)
     * 字段定义: 院内检查类别名称
     */
    @NotNull(message = "院内检查类别名称不能为空")
    @JsonProperty("CT99_99_902_102")
    private String campusExaminationTypeName;

    /**
     * 字段描述: 检查/检验项目编码(长度:30)
     * 字段定义: 受检者检查/检验项目在特定编码体系中的编码,如LOINC的代码值
     * 国标字段: DE04.30.019.00
     */
    @NotNull(message = "检查/检验项目编码不能为空")
    @JsonProperty("WS04_30_019_01")
    private String examinationItemCode;

    /**
     * 字段描述: 检查/检验项目名称(长度:100)
     * 字段定义: 检查报告项目的名称
     * 国标字段: DE04.30.020.00
     */
    @NotNull(message = "检查/检验项目名称不能为空")
    @JsonProperty("WS04_30_020_01")
    private String examinationItemName;

    /**
     * 字段描述: 申请项目代码(长度:50)
     * 字段定义: 申请检查项目在机构诊疗项目目录中的编码
     */
    @JsonProperty("WS02_10_916_01")
    private String applyItemtCode;

    /**
     * 字段描述: 申请项目名称(长度:100)
     * 字段定义: 申请检查项目的名称
     */
    @JsonProperty("WS02_10_916_02")
    private String applyItemName;

    /**
     * 字段描述: 作废标识(长度:3)
     * 字段定义: 标识该医嘱（处方）、申请单等是否作废（0否【未作废】；1是【作废】）
     * 规则: 1、在【CT09.00.001布尔代码】值域范围内
     */
    @NotNull(message = "作废标识不能为空")
    @JsonProperty("WS06_00_901_01")
    private String cancel;

    /**
     * 字段描述: 病理号(长度:50)
     * 字段定义: 受检者病理检查登记顺序号
     * 国标字段: DE01.00.005.00
     */
    @JsonProperty("WS01_00_005_01")
    private String pathologyNum;

    /**
     * 字段描述: 标本编号(长度:50)
     * 字段定义: 按照某一特定编码规则赋予检查、检验标本的顺序号
     * 国标字段: DE01.00.003.00
     */
    @JsonProperty("WS01_00_003_01")
    private String specimenNumber;

    /**
     * 字段描述: 标本类别代码(长度:20)
     * 字段定义: 对标本类别所属分类的代码
     * 规则: 1、在【CT04.50.008标本类别代码】值域范围内，且与【标本类别名称】匹配
     * 国标字段: DE04.50.134.00
     */
    @JsonProperty("WS04_50_134_01")
    private String specimenCategoryCode;

    /**
     * 字段描述: 标本类别名称(长度:100)
     * 字段定义: 标本类别所属分类的名称
     */
    @JsonProperty("CT04_50_134_01")
    private String specimenCategoryName;

    /**
     * 字段描述: 院内标本类别代码(长度:30)
     * 字段定义: 院内标本类别代码
     */
    @JsonProperty("WS99_99_902_128")
    private String campusSpecimenCategoryCode;

    /**
     * 字段描述: 院内标本类别名称(长度:100)
     * 字段定义: 院内标本类别名称
     */
    @JsonProperty("CT99_99_902_128")
    private String campusSpecimenCategoryName;

    /**
     * 字段描述: 标本部位(长度:200)
     * 字段定义: 病理标本所取患者部位
     */
    @JsonProperty("WS04_30_904_01")
    private String specimenSite;

    /**
     * 字段描述: 标本状态(长度:20)
     * 字段定义: 对受检标本状态的描述
     * 国标字段: DE04.50.135.00
     */
    @JsonProperty("WS04_50_135_01")
    private String specimenStatus;

    /**
     * 字段描述: 标本固定液名称(长度:100)
     * 字段定义: 用于标本固定的液体的名称
     * 国标字段: DE08.50.027.00
     */
    @JsonProperty("WS08_50_027_01")
    private String specimenFixativeName;

    /**
     * 字段描述: 标本采样日期时间(长度:14)
     * 字段定义: 采集标本时的公元纪年日期和时间的完整描述
     * 规则: 1、符合【yyyyMMddHHmmss】的格式
     * 国标字段: DE04.50.137.00
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonProperty("WS04_50_137_01")
    private LocalDateTime specimenSamplingTime;

    /**
     * 字段描述: 采样人姓名(长度:50)
     * 字段定义: 采样人员在公安户籍管理部门正式登记注册的姓氏和名称
     * 国标字段: DE02.01.039.00
     */
    @JsonProperty("WS02_01_039_061")
    private String samplerName;

    /**
     * 字段描述: 送检人姓名(长度:100)
     * 字段定义: 送检人在公安户籍管理部门正式登记注册的姓氏和名称
     */
    @JsonProperty("WS02_01_039_055")
    private String inspectorName;

    /**
     * 字段描述: 送检科室代码(长度:100)
     * 字段定义: 送检科室代码
     * 规则: 1、在【CT08.00.002科室代码】值域范围内，且与【送检科室名称】匹配
     */
    @NotNull(message = "送检科室代码不能为空")
    @JsonProperty("WS08_10_025_05")
    private String inspectorDeptCode;

    /**
     * 字段描述: 送检科室名称(长度:80)
     * 字段定义: 送检科室名称
     */
    @NotNull(message = "送检科室名称不能为空")
    @JsonProperty("CT08_10_025_05")
    private String inspectorDeptName;

    /**院区
     * 字段描述: 院内送检科室代码(长度:10)
     * 字段定义: 送检科室按照院内规则进行编码
     */
    @NotNull(message = "院内送检科室代码不能为空")
    @JsonProperty("WS99_99_902_14")
    private String campusInspectorDeptCode;

    /**
     * 字段描述: 院内送检科室名称(长度:100)
     * 字段定义: 送检科室按照院内规则进行命名的名称
     */
    @NotNull(message = "院内送检科室名称不能为空")
    @JsonProperty("CT99_99_902_14")
    private String campusInspectorDeptName;

    /**
     * 字段描述: 送检机构代码(长度:60)
     * 字段定义: 送检机构代码
     * 规则: 1、在【CT99.00.001机构代码（本地化）】值域范围内，且与【送检机构名称】匹配
     */
    @JsonProperty("WS08_10_052_05")
    private String inspectorOrgCode;

    /**
     * 字段描述: 送检机构名称(长度:120)
     * 字段定义: 送检机构名称
     */
    @JsonProperty("CT08_10_052_05")
    private String inspectorOrgName;

    /**
     * 字段描述: 接收标本日期时间(长度:14)
     * 字段定义: 检查科室实际接收标本时的公元纪年日期和时间的完整描述
     * 规则: 1、符合【yyyyMMddHHmmss】的格式
     * 国标字段: DE04.50.141.00
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonProperty("WS04_50_141_01")
    private LocalDateTime specimenReceiptTime;

    /**
     * 字段描述: 核收人姓名(长度:100)
     * 字段定义: 核收人在户籍管理部门正式登记注册的姓氏和名称
     */
    @JsonProperty("WS02_01_039_056")
    private String recipientName;

    /**
     * 字段描述: 检查设备仪器名称(长度:60)
     * 字段定义: 检查所使用的检查设备仪器名称
     */
    @JsonProperty("WS99_99_904_03")
    private String checkEquipmentName;

    /**
     * 字段描述: 检查设备仪器号(长度:20)
     * 字段定义: 表示医院内某台物理设备的唯一序号
     */
    @JsonProperty("WS99_99_904_02")
    private String checkEquipmentNum;

    /**
     * 字段描述: 大型设备标志(长度:10)
     * 字段定义: 标识设备是否为大型设备【1是；2否】
     * 规则: 1、在【CT05.01.001是否代码】值域范围内
     */
    @JsonProperty("WS99_99_031_04")
    private String largeEquipmentSign;

    /**
     * 字段描述: 大型设备类别代码(长度:100)
     * 字段定义: 全国卫生资源与医疗服务调查制度中的大型设备类别的代码
     * 规则: 1、在【CT99.99.008大型设备类别代码表】值域范围内，且与【大型设备类别名称】匹配
     */
    @JsonProperty("WS99_99_904_01")
    private String largeEquipmentCode;

    /**
     * 字段描述: 大型设备类别名称(长度:100)
     * 字段定义: 全国卫生资源与医疗服务调查制度中的大型设备类别的名称
     */
    @JsonProperty("CT99_99_904_01")
    private String largeEquipmentName;

    /**
     * 字段描述: 检查部位(长度:20)
     * 字段定义: 检查的身体部位描述
     */
    @NotNull(message = "检查部位不能为空")
    @JsonProperty("WS04_30_905_01")
    private String checkParts;

    /**
     * 字段描述: 检查部位ACR编码(长度:50)
     * 字段定义: 检查部位ACR编码
     */
    @JsonProperty("WS04_30_905_04")
    private String checkPartsACRCode;

    /**
     * 字段描述: 检查、检验方法(长度:100)
     * 字段定义: 患者接受医学检查项目所采用的检查方法
     */
    @JsonProperty("WS02_10_027_01")
    private String inspectionMethod;

    /**
     * 字段描述: 检查过程描述(长度:200)
     * 字段定义: 检查过程的描述记录
     */
    @JsonProperty("WS04_30_903_01")
    private String inspectionProcessDescription;

    /**
     * 字段描述: 检查报告结果一客观所见(长度:200)
     * 字段定义: 检查项目结果报告的客观说明
     * 国标字段: DE04.50.131.00
     */
    @JsonProperty("WS04_50_131_01")
    private String checkReportResultsObjectiveObservation;

    /**
     * 字段描述: 检查报告结果一主观提示(长度:200)
     * 字段定义: 检查项目结果报告的主观说明
     * 国标字段: DE04.50.132.00
     */
    @JsonProperty("WS04_50_132_01")
    private String checkReportResultsSubjectiveCues;

    /**
     * 字段描述: 检查结果(数值)(长度:14)
     * 字段定义: 检查项目中能够记录为数值的结果
     * 规则: 1、类型为数值，且精度小于等于4
     */
    @JsonProperty("WS04_30_015_03")
    private Integer checkReportResultNum;

    /**
     * 字段描述: 检查结果代码(长度:3)
     * 字段定义: 患者检查结果的正异代码（1正常；2异常）
     * 规则: 1、在【CT04.30.001正异结果代码】值域范围内
     * 国标字段: DE04.30.017.00
     */
    @JsonProperty("WS04_30_017_01")
    private String checkReportResultCode;

    /**
     * 字段描述: 院内检查结果代码(长度:30)
     * 字段定义: 院内检查结果代码
     */
    @JsonProperty("WS99_99_902_104")
    private String campusCheckReportResultCode;

    /**
     * 字段描述: 院内检查结果名称(长度:100)
     * 字段定义: 院内检查结果名称
     */
    @JsonProperty("CT99_99_902_104")
    private String campusCheckReportResultName;

    /**
     * 字段描述: 检查结果单位(长度:20)
     * 字段定义: 检查项目中能够记录为数值结果的数值单位，如体温单位℃，脉率（次／min）
     */
    @JsonProperty("WS04_30_016_03")
    private String checkReportResultUnit;

    /**
     * 字段描述: 检查技师姓名(长度:100)
     * 字段定义: 检查技师签署的在公安户籍管理部门正式登记注册的姓氏和名称
     * 国标字段: DE02.01.039.00
     */
    @JsonProperty("WS02_01_039_064")
    private String checkTechnicianName;

    /**
     * 字段描述: 检查医生姓名(长度:100)
     * 字段定义: 检查医生姓名
     */
    @JsonProperty("WS02_01_039_117")
    private String examiningDoctorName;

    /**
     * 字段描述: 检查医生身份证号(长度:18)
     * 字段定义: 检查医生身份证件上唯一的法定标识符
     */
    @JsonProperty("WS99_99_030_01")
    private String examiningDoctorIdCardNum;

    /**
     * 字段描述: 检查日期时间(长度:14)
     * 字段定义: 检查当日的公元纪年日期和时间
     * 规则: 1、符合【yyyyMMddHHmmss】的格式
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonProperty("WS06_00_924_01")
    private LocalDateTime checkTime;

    /**
     * 字段描述: 备注信息(长度:4000)
     * 字段定义: 对下达医嘱的补充说明和注意事项提示
     * 国标字段: DE06.00.179.00
     */
    @JsonProperty("WS06_00_179_01")
    private String remark;

    /**
     * 字段描述: 报告医师姓名(长度:100)
     * 字段定义: 报告医师在公安管理部门正式登记注册的姓氏和名称
     * 国标字段: DE02.01.039.00
     */
    @NotNull(message = "报告医师姓名不能为空")
    @JsonProperty("WS02_01_039_049")
    private String reportingPhysicianName;

    /**
     * 字段描述: 报告日期时间(长度:14)
     * 字段定义: 生成报告的当日的公元纪年日期和时间
     * 规则: 1、符合【yyyyMMddHHmmss】的格式
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @NotNull(message = "报告日期时间不能为空")
    @JsonProperty("WS06_00_926_01")
    private LocalDateTime reportTime;

    /**
     * 字段描述: 审核人编码(长度:60)
     * 字段定义: 审核人编码
     */
    @JsonProperty("WS08_30_908_01")
    private String reviewerCode;

    /**
     * 字段描述: 审核人姓名(长度:50)
     * 字段定义: 审核人签署的在公安户籍管理部门正式登记注册的姓氏和名称
     * 国标字段: DE02.01.039.00
     */
    @JsonProperty("WS02_01_039_057")
    private String reviewerName;

    /**
     * 字段描述: 审核日期时间(长度:14)
     * 字段定义: 审核完成的当日的公元纪年日期和时间
     * 规则: 1、符合【yyyyMMddHHmmss】的格式
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @NotNull(message = "审核日期时间不能为空")
    @JsonProperty("WS06_00_927_01")
    private LocalDateTime auditTime;

    /**
     * 字段描述: 报告科室代码(长度:100)
     * 字段定义: 出具检查、检验报告的科室的代码
     * 规则: 1、在【CT08.00.002科室代码】值域范围内，且与【报告科室名称】匹配
     * 国标字段: DE08.10.026.00
     */
    @JsonProperty("WS08_10_025_03")
    private String reportDeptCode;

    /**
     * 字段描述: 报告科室名称(长度:80)
     * 字段定义: 出具检查、检验报告的科室的名称
     */
    @JsonProperty("CT08_10_025_03")
    private String reportDeptName;

    /**
     * 字段描述: 院内科室代码(长度:30)
     * 字段定义: 医院内部的科室按照一定的编码规则编写的代码
     */
    @JsonProperty("WS99_99_902_08")
    private String campusDeptCode;

    /**
     * 字段描述: 院内科室名称(长度:200)
     * 字段定义: 按照医院内部科室命名规则命名的科室名称
     */
    @JsonProperty("CT99_99_902_08")
    private String campusDeptName;

    /**
     * 字段描述: 医院规范科室名称(长度:200)
     * 字段定义: 杭州市医政医管科室单元名称
     * 规则: 在【CT08.10.012 医院规范科室代码表】值域范围内，且与【医院规范科室代码】匹配
     */
    @NotNull(message = "医院规范科室名称不能为空")
    @JsonProperty("CT08_01_025_28")
    private String campusStandardDeptName;

    /**
     * 字段描述: 医院规范科室代码(长度:200)
     * 字段定义: 杭州市医政医管科室单元代码
     * 规则: 在【CT08.10.012 医院规范科室代码表】值域范围内，且与【医院规范科室名称】匹配
     */
    @NotNull(message = "医院规范科室代码不能为空")
    @JsonProperty("WS08_01_025_28")
    private String campusStandardDeptCode;

    /**
     * 字段描述: 报告机构代码(长度:60)
     * 字段定义: 出具检查、检验的报告单的医疗机构的代码
     * 规则: 1、在【CT99.00.001机构代码（本地化）】值域范围内，且与【报告机构名称】匹配
     * 国标字段: DE08.10.013.00
     */
    @NotNull(message = "报告机构代码不能为空")
    @JsonProperty("WS08_10_052_03")
    private String reportOrgCode;

    /**
     * 字段描述: 报告机构名称(长度:120)
     * 字段定义: 出具检查、检验的报告单的医疗机构的名称
     */
    @NotNull(message = "报告机构名称不能为空")
    @JsonProperty("CT08_10_052_03")
    private String reportOrgName;

    /**
     * 字段描述: 卡号(长度:100)
     * 字段定义: 根据不同卡类型，患者提供的卡号
     */
    @JsonProperty("WS99_99_903_40")
    private String cardNum;

    /**
     * 字段描述: 患者类型代码(长度:20)
     * 字段定义: 患者就诊类型的分类代码
     * 规则: 1、在【CV09.00.404患者类型代码】值域范围内，且与【患者类型名称】匹配
     * 国标字段: DE02.01.060.00
     */
    @NotNull(message = "患者类型代码不能为空")
    @JsonProperty("WS02_01_060_01")
    private String type;

    /**
     * 字段描述: 患者类型名称(长度:100)
     * 字段定义: 患者就诊类型的分类名称
     */
    @JsonProperty("CT02_01_060_01")
    private String typeName;

    /**
     * 字段描述: 联系电话(长度:36)
     * 字段定义: 联系电话
     */
    @JsonProperty("WS02_01_010_22")
    private String patientPhone;

    /**
     * 字段描述: 互认编码列表(长度:50)
     * 字段定义: 互认编码列表,多个以逗号开隔
     */
    @JsonProperty("WS99_99_999_23")
    private String mutualRecognitionCode;

    /**
     * 字段描述: 是否互认标志(长度:2)
     * 字段定义: 是否互认标志
     * 规则: 在【CT99.99.396是否互认标志】值域范围内
     */
    @NotNull(message = "是否互认标志不能为空")
    @JsonProperty("WS99_99_999_18")
    private String mutualRecognitionSign;

    /**
     * 字段描述: 诊断信息(长度:1333)
     * 字段定义: 诊断信息
     */
    @NotNull(message = "诊断信息不能为空")
    @JsonProperty("WS99_99_902_685")
    private String diagnosticInformation;

    /**
     * 字段描述: 检查互认编码(长度:50)
     * 字段定义: 检查互认编码
     * 规则: 在【CT99.99.398检查互认编码】值域范围内
     */
    @JsonProperty("WS99_99_999_20")
    private String checkMutualRecognitionCode;

    /**
     * 字段描述: 检查目的及要求(长度:500)
     * 字段定义: 检查目的及要求
     */
    @JsonProperty("WS04_30_901_01")
    private String examinationPurpose;

    /**
     * 字段描述: 申请日期时间(长度:14)
     * 字段定义: 申请日期时间
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonProperty("WS06_00_917_01")
    private LocalDateTime applyTime;

    /**
     * 字段描述: 检查印象(长度:500)
     * 字段定义: 检查印象
     */
    @JsonProperty("WS99_99_034_70")
    private String checkImpressions;

    /**
     * 字段描述: 检查建议(长度:500)
     * 字段定义: 检查建议
     */
    @JsonProperty("WS99_99_034_258")
    private String checkRecommendations;

    /**
     * 字段描述: 是否阳性(长度:10)
     * 字段定义: 是否阳性
     * 规则: 1、在【CT09.00.001布尔代码】值域范围内
     */
    @JsonProperty("WS99_99_020_130")
    private String positive;

    /**
     * 字段描述: 申请医生身份证号(长度:18)
     * 字段定义: 申请医生身份证号
     */
    @JsonProperty("WS99_99_030_03")
    private String doctorIdCard;

    /**
     * 字段描述: 申请医生姓名(长度:50)
     * 字段定义: 申请医生姓名
     */
    @NotNull(message = "申请医生姓名不能为空")
    @JsonProperty("WS02_01_039_177")
    private String doctorName;

    /**
     * 字段描述: 报告医生身份证号(长度:18)
     * 字段定义: 报告医生身份证号
     */
    @JsonProperty("WS99_99_906_01")
    private String reportDoctorIdCardNum;

    /**
     * 字段描述: 报告地址内网地址(长度:1333)
     * 字段定义: 报告地址内网地址
     */
    @JsonProperty("WS99_99_999_21")
    private String reportIntranetAddress;

    /**
     * 字段描述: 报告地址外网地址(长度:1333)
     * 字段定义: 报告地址外网地址
     */
    @JsonProperty("WS99_99_999_22")
    private String reportInternetAddress;

    /**
     * 字段描述: CA签名值(长度:1000)
     * 字段定义: 最后一名签名医生（护士）的CA医生签名
     */
    @JsonProperty("WS99_99_010_48")
    private String caSign;

}
