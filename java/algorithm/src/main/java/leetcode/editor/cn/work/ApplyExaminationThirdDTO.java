package leetcode.editor.cn.work;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 门急诊检查申请单
 *
 * @author xuweizhi
 * @since 2022/03/09 14:23
 */
@Data
@SuppressWarnings("all")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplyExaminationThirdDTO implements Serializable {

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
     * 字段描述: 卡号(长度:100)
     * 字段定义: 根据不同卡类型，患者提供的卡号
     */
    @JsonProperty("WS99_99_903_40")
    private String patientCardNum;

    /**
     * 字段描述: 卡类型代码(长度:10)
     * 字段定义: 患者提供的有效卡的类型代码
     */
    @JsonProperty("WS99_99_902_07")
    private String patientCardType;

    /**
     * 字段描述: 卡类型名称(长度:50)
     * 字段定义: 患者提供的有效卡的卡类型名称
     */
    @JsonProperty("CT99_99_902_07")
    private String patientCardTypeName;

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
    @NotNull(message = "身份证件类别名称不能为空")
    @JsonProperty("CT02_01_031_01")
    private String idCardTypeName;

    /**
     * 字段描述: 身份证件号码(长度:32)
     * 字段定义: 身份证件上唯一的法定标识符
     * 国标字段: DE02.01.030.00
     */
    @NotNull(message = "身份证件号码不能为空")
    @JsonProperty("WS02_01_030_01")
    private String idCardNum;

    /**
     * 字段描述: 患者类型代码(长度:20)
     * 字段定义: 患者就诊类型的分类代码
     * 规则: 1、在【CV09.00.404患者类型代码】值域范围内，且与【患者类型名称】匹配
     * 国标字段: DE02.01.060.00
     */
    @JsonProperty("WS02_01_060_01")
    private String type;

    /**
     * 字段描述: 患者类型名称(长度:100)
     * 字段定义: 患者就诊类型的分类名称
     */
    @JsonProperty("CT02_01_060_01")
    private String typeName;

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
     * 字段描述: 申请单名称(长度:20)
     * 字段定义: 用于描述在一张申请单上检查、检验项目的具体名称（例如血常规、肝功10项）
     */
    @JsonProperty("WS01_00_906_01")
    private String applyName;

    /**
     * 字段描述: 疾病诊断编码(长度:20)
     * 字段定义: 患者所患的疾病诊断特定编码体系中的编码
     * 规则: 1、在【ICD-10国际疾病分类标准编码】值域范围内，且与【疾病诊断名称】匹配
     * 国标字段: DE05.01.024.00
     */
    @JsonProperty("WS05_01_024_01")
    private String diagnosisCode;

    /**
     * 字段描述: 疾病诊断名称(长度:100)
     * 字段定义: 患者所患疾病的西医诊断名称
     * 国标字段: DE05.01.025.00
     */
    @JsonProperty("CT05_01_024_01")
    private String diagnosisName;

    /**
     * 字段描述: 院内疾病诊断代码(长度:10)
     * 字段定义: 医院内部根据一定的编码规则对疾病进行的编码
     */
    @JsonProperty("WS99_99_902_09")
    private String campusDiagnosisCode;

    /**
     * 字段描述: 院内疾病诊断名称(长度:100)
     * 字段定义: 医院内部根据一定的编码规则对疾病进行的编码
     */
    @JsonProperty("CT99_99_902_09")
    private String campusDiagnosisName;

    /**
     * 字段描述: 症状描述(长度:100)
     * 字段定义: 对患者出现症状的详细描述
     * 国标字段: DE04.01.117.00
     */
    @JsonProperty("WS04_01_117_02")
    private String diagnosisDescription;

    /**
     * 字段描述: 项目序号(长度:20)
     * 字段定义: 用于描述本条记录的排列顺序号码
     */
    @NotNull(message = "项目序号不能为空")
    @JsonProperty("WS01_00_907_01")
    private String itemNum;

    /**
     * 字段描述: 医嘱号(长度:50)
     * 字段定义: 医嘱顺序号
     */
    @JsonProperty("WS01_00_903_01")
    private String doctorOrderId;

    /**
     * 字段描述: 申请项目代码(长度:50)
     * 字段定义: 申请检查项目在机构诊疗项目目录中的编码
     */
    @NotNull(message = "申请项目代码不能为空")
    @JsonProperty("WS02_10_916_01")
    private String applyItemCode;

    /**
     * 字段描述: 申请项目名称(长度:100)
     * 字段定义: 申请检查项目的名称
     */
    @NotNull(message = "申请项目名称不能为空")
    @JsonProperty("WS02_10_916_02")
    private String applyItemName;

    /**
     * 字段描述: 申请日期时间(长度:14)
     * 字段定义: 提交申请时的公元纪年日期和时间的完整描述
     * 规则: 1、符合【yyyyMMddHHmmss】的格式
     */
    @NotNull(message = "申请日期时间不能为空")
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonProperty("WS06_00_917_01")
    private LocalDateTime applyTime;

    /**
     * 字段描述: 作废标识(长度:3)
     * 字段定义: 标识该医嘱（处方）、申请单等是否作废（0否【未作废】；1是【作废】）
     * 规则: 1、在【CT09.00.001布尔代码】值域范围内
     */
    @JsonProperty("WS06_00_901_01")
    private String cancel;

    /**
     * 字段描述: 检查目的及要求(长度:100)
     * 字段定义: 申请单中描述的本次检查要达到的目的及一些检查要求
     */
    @FillEmpty
    @JsonProperty("WS04_30_901_01")
    private String examinationTarget;

    /**
     * 字段描述: 特殊检查标志(长度:3)
     * 字段定义: 标识患者有无特殊检查操作经历的标志（1无；2有）
     * 规则: 1、在【CT02.01.001有无情况代码】值域范围内
     * 国标字段: DE02.01.079.00
     */
    @JsonProperty("WS02_01_079_01")
    private String specialInspectionMark;

    /**
     * 字段描述: 申请人姓名(长度:50)
     * 字段定义: 开立检验或检查申请人签署的在公安户籍管理部门正式登记注册的姓氏和名称
     */
    @JsonProperty("WS02_01_039_097")
    private String docotorName;

    /**
     * 字段描述: 申请医生身份证号(长度:18)
     * 字段定义: 申请医生身份证件上唯一的法定标识符
     */
    @JsonProperty("WS99_99_030_03")
    private String doctorIdCard;

    /**
     * 字段描述: 申请科室代码(长度:100)
     * 字段定义: 申请检查、检验的科室代码
     * 规则: 1、在【CT08.00.002科室代码】值域范围内，且与【申请科室名称】匹配
     * 国标字段: DE08.10.026.00
     */
    @JsonProperty("WS08_10_025_02")
    private String applyDeptId;

    /**
     * 字段描述: 申请科室名称(长度:80)
     * 字段定义: 申请检查、检验的科室名称
     */
    @JsonProperty("CT08_10_025_02")
    private String applyDeptName;

    /**
     * 字段描述: 院内科室代码(长度:30)
     * 字段定义: 医院内部的科室按照一定的编码规则编写的代码
     */
    @JsonProperty("WS99_99_902_08")
    private String campusDeptId;

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
    private String campusStandarDeptName;

    /**
     * 字段描述: 医院规范科室代码(长度:200)
     * 字段定义: 杭州市医政医管科室单元代码
     * 规则: 在【CT08.10.012 医院规范科室代码表】值域范围内，且与【医院规范科室名称】匹配
     */
    @NotNull(message = "医院规范科室代码不能为空")
    @JsonProperty("WS08_01_025_28")
    private String campusStandardDeptCode;

    /**
     * 字段描述: 申请机构代码(长度:60)
     * 字段定义: 申请作检查、检验项目的医疗机构的代码
     * 规则: 1、在【CT99.00.001机构代码（本地化）】值域范围内，且与【申请机构名称】匹配
     * 国标字段: DE08.10.013.00
     */
    @NotNull(message = "申请机构代码不能为空")
    @JsonProperty("WS08_10_052_02")
    private String applyOrgId;

    /**
     * 字段描述: 申请机构名称(长度:120)
     * 字段定义: 申请作检查、检验项目的医疗机构的名称
     */
    @NotNull(message = "申请机构名称不能为空")
    @JsonProperty("CT08_10_052_02")
    private String applyOrgName;

    /**
     * 字段描述: 被申请机构代码(长度:60)
     * 字段定义: 被申请作检查、检验项目的医疗机构的代码
     * 规则: 1、在【CT99.00.001机构代码（本地化）】值域范围内，且与【被申请机构名称】匹配
     */
    @JsonProperty("WS08_10_052_22")
    private String beAppliedOrgId;

    /**
     * 字段描述: 被申请机构名称(长度:120)
     * 字段定义: 被申请作检查、检验项目的医疗机构的名称
     */
    @JsonProperty("CT08_10_052_22")
    private String beAppliedOrgName;

}
