package leetcode.editor.cn.work;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 门(急)诊检验报告
 *
 * @author xuweizhi
 * @since 2022/03/09 14:23
 */
@Data
@SuppressWarnings("all")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LabTestThirdDTO implements Serializable {

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
    @FillEmpty
    @JsonProperty("WS02_01_032_02")
    private String month;

    /**
     * 字段描述: 年龄（日）(长度:10)
     * 字段定义: 年龄不足1周月的实足年龄的日龄
     */
    @FillEmpty
    @JsonProperty("WS99_99_026_01")
    private String day;

    /**
     * 字段描述: 年龄（小时）(长度:10)
     * 字段定义: 年龄不足1周日的实足年龄的时龄
     */
    @FillEmpty
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
     * 字段描述: 患者ID(长度:50)
     * 字段定义: 患者在医院内部的唯一编号
     */
    @NotNull(message = "患者ID不能为空")
    @JsonProperty("WS99_99_999_16")
    private String patientId;

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
     * 字段描述: 出生日期(长度:8)
     * 字段定义: 卫生服务对象出生当日的公元纪年日期
     * 规则: 1、符合【yyyyMMdd】的格式
     * 国标字段: DE02.01.005.01
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonProperty("WS02_01_005_01_01")
    private LocalDateTime brith;

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
     * 字段描述: 报告单名称(长度:100)
     * 字段定义: 报告单名称
     */
    @NotNull(message = "报告单名称不能为空")
    @JsonProperty("WS01_00_900_02")
    private String reportName;

    /**
     * 字段描述: 报告单类别代码(长度:3)
     * 字段定义: 报告单出具时的类别代码描述（1临时报告；2最终报告）
     * 规则: 1、在【CT01.00.003报告单类别代码】值域范围内，且与【报告单类别名称】匹配
     */
    @NotNull(message = "报告单类别代码不能为空")
    @JsonProperty("WS01_00_900_01")
    private String reportType;

    /**
     * 字段描述: 报告单类别名称(长度:100)
     * 字段定义: 报告单出具时的类别描述，临时报告还是最终报告
     */
    @JsonProperty("CT01_00_900_01")
    private String reportTypeName;

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
     * 字段描述: 标本类别代码(长度:20)
     * 字段定义: 对标本类别所属分类的代码
     * 规则: 1、在【CT04.50.008标本类别代码】值域范围内，且与【标本类别名称】匹配
     * 国标字段: DE04.50.134.00
     */
    @FillEmpty
    @JsonProperty("WS04_50_134_01")
    private String specimenCategoryCode;

    /**
     * 字段描述: 标本类别名称(长度:100)
     * 字段定义: 标本类别所属分类的名称
     */
    @FillEmpty
    @NotNull(message = "标本类别名称不能为空")
    @JsonProperty("CT04_50_134_01")
    private String specimenCategoryName;

    /**
     * 字段描述: 院内标本类别代码(长度:30)
     * 字段定义: 院内标本类别代码
     */
    @FillEmpty
    @JsonProperty("WS99_99_902_128")
    private String campusSpecimenCategoryCode;

    /**
     * 字段描述: 院内标本类别名称(长度:100)
     * 字段定义: 院内标本类别名称
     */
    @FillEmpty
    @JsonProperty("CT99_99_902_128")
    private String campusSpecimenCategoryName;

    /**
     * 字段描述: 标本编号(长度:50)
     * 字段定义: 按照某一特定编码规则赋予检查、检验标本的顺序号
     * 国标字段: DE01.00.003.00
     */
    @FillEmpty
    @NotNull(message = "标本编号不能为空")
    @JsonProperty("WS01_00_003_01")
    private String specimenNumber;

    /**
     * 字段描述: 标本状态(长度:20)
     * 字段定义: 对受检标本状态的描述
     * 国标字段: DE04.50.135.00
     */
    @FillEmpty
    @JsonProperty("WS04_50_135_01")
    private String specimenStatus;

    /**
     * 字段描述: 标本部位(长度:200)
     * 字段定义: 病理标本所取患者部位
     */
    @FillEmpty
    @JsonProperty("WS04_30_904_01")
    private String specimenSite;

    /**
     * 字段描述: 标本采样日期时间(长度:14)
     * 字段定义: 采集标本时的公元纪年日期和时间的完整描述
     * 规则: 1、符合【yyyyMMddHHmmss】的格式
     * 国标字段: DE04.50.137.00
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @NotNull(message = "标本采样日期时间不能为空")
    @JsonProperty("WS04_50_137_01")
    private LocalDateTime specimenSamplingTime;

    /**
     * 字段描述: 采样人姓名(长度:50)
     * 字段定义: 采样人员在公安户籍管理部门正式登记注册的姓氏和名称
     * 国标字段: DE02.01.039.00
     */
    @FillEmpty
    @NotNull(message = "采样人姓名不能为空")
    @JsonProperty("WS02_01_039_061")
    private String samplerName;

    /**
     * 字段描述: 送检人姓名(长度:100)
     * 字段定义: 送检人在公安户籍管理部门正式登记注册的姓氏和名称
     */
    @NotNull(message = "送检人姓名不能为空")
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

    /**
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
     * 规则: 在【CT08.10.012 医院规范科室代码表】值域范围内，且与【医院规范科室代码】匹配
     */
    @NotNull(message = "医院规范科室代码不能为空")
    @JsonProperty("WS08_01_025_28")
    private String campusStandardDeptCode;

    /**
     * 字段描述: 送检机构代码(长度:60)
     * 字段定义: 送检机构代码
     * 规则: 1、在【CT99.00.001机构代码（本地化）】值域范围内，且与【送检机构名称】匹配
     */
    @NotNull(message = "送检机构代码不能为空")
    @JsonProperty("WS08_10_052_05")
    private String inspectorOrgCode;

    /**
     * 字段描述: 送检机构名称(长度:120)
     * 字段定义: 送检机构名称
     */
    @NotNull(message = "送检机构名称不能为空")
    @JsonProperty("CT08_10_052_05")
    private String inspectorOrgName;

    /**
     * 字段描述: 接收标本日期时间(长度:14)
     * 字段定义: 检查科室实际接收标本时的公元纪年日期和时间的完整描述
     * 规则: 1、符合【yyyyMMddHHmmss】的格式
     * 国标字段: DE04.50.141.00
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @NotNull(message = "接收标本日期时间不能为空")
    @JsonProperty("WS04_50_141_01")
    private LocalDateTime specimenReceiptTime;

    /**
     * 字段描述: 核收人姓名(长度:100)
     * 字段定义: 核收人在户籍管理部门正式登记注册的姓氏和名称
     */
    @NotNull(message = "核收人姓名不能为空")
    @JsonProperty("WS02_01_039_056")
    private String recipientName;

    /**
     * 字段描述: 是否做药敏实验(长度:3)
     * 字段定义: 本次检验是否做了药敏检验的标识（0否【未做】；1是【做】）
     * 规则: 1、在【CT09.00.001布尔代码】值域范围内
     */
    @JsonProperty("WS04_50_901_01")
    private String drugSusceptibilityTest;

    /**
     * 字段描述: 检测结果(长度:300)
     * 字段定义: 检测结果描述
     */
    @FillEmpty
    @JsonProperty("WS04_50_908_01")
    private String checkResult;

    /**
     * 字段描述: 备注信息(长度:4000)
     * 字段定义: 对下达医嘱的补充说明和注意事项提示
     * 国标字段: DE06.00.179.00
     */
    @FillEmpty
    @JsonProperty("WS06_00_179_01")
    private String remark;

    /**
     * 字段描述: 检验技师姓名(长度:100)
     * 字段定义: 检验技师签署的在公安户籍管理部门正式登记注册的姓氏和名称
     * 国标字段: DE02.01.039.00
     */
    @FillEmpty
    @NotNull(message = "检验技师姓名不能为空")
    @JsonProperty("WS02_01_039_065")
    private String inspectionTechnicianName;

    /**
     * 字段描述: 检验医生姓名(长度:100)
     * 字段定义: 检验医生姓名
     */
    @JsonProperty("WS02_01_039_173")
    private String examiningDoctorName;

    /**
     * 字段描述: 检验日期时间(长度:14)
     * 字段定义: 检验当日的公元纪年日期和时间
     * 规则: 1、符合【yyyyMMddHHmmss】的格式
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @NotNull(message = "检验日期时间不能为空")
        @JsonProperty("WS06_00_925_01")
    private LocalDateTime inspectionTime;

    /**
     * 字段描述: 报告医师姓名(长度:100)
     * 字段定义: 报告医师在公安管理部门正式登记注册的姓氏和名称
     * 国标字段: DE02.01.039.00
     */
    @FillEmpty
    @NotNull(message = "报告医师姓名不能为空")
    @JsonProperty("WS02_01_039_049")
    private String reportingPhysicianName;

    /**
     * 字段描述: 报告医生身份证号(长度:18)
     * 字段定义: 报告医生的身份证上唯一的法定标识符
     */
    @JsonProperty("WS99_99_906_01")
    private String reportDoctorIdCardNum;

    /**
     * 字段描述: 审核医生身份证号(长度:18)
     * 字段定义: 审核医生身份证件上唯一的法定标识符
     */
    @JsonProperty("WS99_99_030_04")
    private String checkDoctorIdCardNum;

    /**
     * 字段描述: 审核人姓名(长度:50)
     * 字段定义: 审核人签署的在公安户籍管理部门正式登记注册的姓氏和名称
     * 国标字段: DE02.01.039.00
     */
    @NotNull(message = "审核人姓名不能为空")
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
     * 字段描述: 报告日期时间(长度:14)
     * 字段定义: 生成报告的当日的公元纪年日期和时间
     * 规则: 1、符合【yyyyMMddHHmmss】的格式
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @NotNull(message = "报告日期时间不能为空")
    @JsonProperty("WS06_00_926_01")
    private LocalDateTime reportTime;

    /**
     * 字段描述: 报告科室代码(长度:100)
     * 字段定义: 出具检查、检验报告的科室的代码
     * 规则: 1、在【CT08.00.002科室代码】值域范围内，且与【报告科室名称】匹配
     * 国标字段: DE08.10.026.00
     */
    @FillEmpty
    @JsonProperty("WS08_10_025_03")
    private String reportDeptCode;

    /**
     * 字段描述: 报告科室名称(长度:80)
     * 字段定义: 出具检查、检验报告的科室的名称
     */
    @FillEmpty
    @JsonProperty("CT08_10_025_03")
    private String reportDeptName;

    /**
     * 字段描述: 院内报告科室代码(长度:30)
     * 字段定义: 院内报告科室代码
     */
    @FillEmpty
    @JsonProperty("WS99_99_902_98")
    private String campusReportDeptCode;

    /**
     * 字段描述: 院内报告科室名称(长度:100)
     * 字段定义: 院内报告科室名称
     */
    @FillEmpty
    @JsonProperty("CT99_99_902_98")
    private String campusReportDeptName;

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
     * 字段描述: 检验技师身份证号(长度:18)
     * 字段定义: 检验技师身份证号
     */
    @FillEmpty
    @JsonProperty("WS99_99_030_10")
    private String inspectionTechnicianIdCardNum;

    /**
     * 字段描述: 联系电话(长度:36)
     * 字段定义: 联系电话
     */
    @JsonProperty("WS02_01_010_22")
    private String patientPhone;

    /**
     * 字段描述: 检验目的(长度:100)
     * 字段定义: 检验目的
     */
    @FillEmpty
    @NotNull(message = "检验目的不能为空")
    @JsonProperty("WS99_99_034_69")
    private String inspectionPurpose;

    /**
     * 字段描述: 检验目的编码(长度:50)
     * 字段定义: 检验目的编码
     */
    @FillEmpty
    @NotNull(message = "检验目的编码不能为空")
    @JsonProperty("WS99_99_999_24")
    private String inspectionPurposeCode;

    /**
     * 字段描述: 申请日期时间(长度:14)
     * 字段定义: 申请日期时间
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @NotNull(message = "申请日期时间不能为空")
    @JsonProperty("WS06_00_917_01")
    private LocalDateTime applyTime;

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
     * 字段描述: 送检日期时间(长度:14)
     * 字段定义: 送检日期时间
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @NotNull(message = "送检日期时间不能为空")
    @JsonProperty("WS06_00_917_03")
    private LocalDateTime sendInspectionTime;

    /**
     * 字段描述: 送检医生身份证号(长度:18)
     * 字段定义: 送检医生身份证号
     */
    @JsonProperty("WS99_99_030_32")
    private String submittingDoctorNum;

    /**
     * 字段描述: 采样医生身份证号(长度:18)
     * 字段定义: 采样医生身份证号
     */
    @JsonProperty("WS99_99_030_33")
    private String samplingDoctorIdCardNum;

    /**
     * 字段描述: 接收人身份证号(长度:18)
     * 字段定义: 接收人身份证号
     */
    @JsonProperty("WS99_99_030_34")
    private String recipientIdCardNum;

    /**
     * 字段描述: 检验医生身份证号(长度:18)
     * 字段定义: 检验医生身份证号
     */
    @JsonProperty("WS99_99_030_35")
    private String inspectDoctorIdCardNum;

    /**
     * 字段描述: 报告单查看地址(长度:1333)
     * 字段定义: 报告单查看地址
     */
    @JsonProperty("WS99_99_999_25")
    private String reportIpAddr;

    /**
     * 字段描述: 诊断信息(长度:1333)
     * 字段定义: 诊断信息
     */
    @NotNull(message = "诊断信息不能为空")
    @JsonProperty("WS99_99_902_685")
    private String diagnosticInformation;

    /**
     * 字段描述: 检验互认编码(长度:50)
     * 字段定义: 检验互认编码,多个以^^^隔开
     * 规则: 在【CT99.99.397检验互认编码】值域范围内
     */
    @FillEmpty
    @JsonProperty("WS99_99_999_17")
    private String checkMutualRecognitionCode;

    /**
     * 字段描述: 是否互认标志(长度:2)
     * 字段定义: 是否互认标志
     * 规则: 在【CT99.99.396是否互认标志】值域范围内
     */
    @NotNull(message = "是否互认标志不能为空")
    @JsonProperty("WS99_99_999_18")
    private String mutualRecognitionSign;

    /**
     * 字段描述: 作废标识(长度:3)
     * 字段定义: 作废标识
     * 规则: 1、在【CT09.00.001布尔代码】值域范围内
     */
    @JsonProperty("WS06_00_901_01")
    private String cancel;

    /**
     * 字段描述: CA签名值(长度:1000)
     * 字段定义: 最后一名签名医生（护士）的CA医生签名
     */
    @JsonProperty("WS99_99_010_48")
    private String caSign;

}
