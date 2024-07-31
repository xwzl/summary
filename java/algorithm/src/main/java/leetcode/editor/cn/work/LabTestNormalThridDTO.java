package leetcode.editor.cn.work;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 门(急)诊检验常规报告
 *
 * @author xuweizhi
 * @since 2022/03/09 14:23
 */
@Data
@SuppressWarnings("all")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LabTestNormalThridDTO implements Serializable {

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
    @FillEmpty
    @JsonProperty("WS02_01_032_02")
    private String month;

    /**
     * 字段描述: 门(急)诊号(长度:50)
     * 字段定义: 按照某一特定编码规则赋予门（急）诊就诊对象的顺序号（唯一标识一次门急诊业务，若机构的门诊号采取循环序列，则可联合挂号时间唯一确定一次业务）
     * 国标字段: DE01.00.010.00
     */
    @NotNull(message = "门(急)诊号不能为空")
    @JsonProperty("WS01_00_010_01")
    private String healthcareRecordId;

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
     * 字段描述: 项目序号(长度:20)
     * 字段定义: 用于描述本条记录的排列顺序号码
     */
    @NotNull(message = "项目序号不能为空")
    @JsonProperty("WS01_00_907_01")
    private String itemNum;

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
    private String applyItemCode;

    /**
     * 字段描述: 申请项目名称(长度:100)
     * 字段定义: 申请检查项目的名称
     */
    @JsonProperty("WS02_10_916_02")
    private String applyItemName;

    /**
     * 字段描述: 检验结果（数值）(长度:100)
     * 字段定义: 患者检验结果数值
     * 国标字段: DE04.30.009.00
     */
    @NotNull(message = "检验结果（数值）不能为空")
    @JsonProperty("WS04_30_009_03")
    private String checkResult;

    /**
     * 字段描述: 临床意义(长度:1000)
     * 字段定义: 对该检验项目在临床上的意义和医生建议的描述
     */
    @FillEmpty
    @JsonProperty("WS05_01_904_02")
    private String clinicalSignificance;

    /**
     * 字段描述: 危急值标志(长度:2)
     * 字段定义: 危急值标志
     * 规则: 1、类型为数值1、在【CT05.01.001是否代码】值域范围内
     */
    @FillEmpty
    @JsonProperty("WS05_10_926_01")
    private String criticalValueSign;

    /**
     * 字段描述: 检验结果代码(长度:5)
     * 字段定义: 检验结果代码 （1正常；2异常）
     * 规则: 1、在【CT04.30.001正异结果代码】值域范围内
     */
    @JsonProperty("WS04_30_009_06")
    private String checkResultCode;

    /**
     * 字段描述: 仪器编码(长度:20)
     * 字段定义: 检验仪器编码
     */
    @FillEmpty
    @JsonProperty("WS04_30_023_04")
    private String instrumentCode;

    /**
     * 字段描述: 仪器名称(长度:20)
     * 字段定义: 检验仪器名称
     */
    @FillEmpty
    @JsonProperty("WS04_30_023_05")
    private String equipmentName;

    /**
     * 字段描述: 计量单位(长度:20)
     * 字段定义: 受检者定量检查/检验结果测量值的计量单位
     * 国标字段: DE04.30.016.00
     */
    @JsonProperty("WS04_30_016_01")
    private String measurementUnit;

    /**
     * 字段描述: 检验结果描述(长度:1000)
     * 字段定义: 对检验结果的详细描述
     */
    @FillEmpty
    @JsonProperty("WS04_50_913_01")
    private String checkResultDescription;

    /**
     * 字段描述: 参考值(长度:20)
     * 字段定义: 检验结果在合理区间内的参数
     */
    @FillEmpty
    @JsonProperty("WS04_50_902_01")
    private String reference;

    /**
     * 字段描述: 异常结果(长度:100)
     * 字段定义: 每一项检验结果的异常信息，如：上下箭头、加减号、H、L等偏差异常结果的表示符号
     */
    @FillEmpty
    @JsonProperty("WS04_10_903_01")
    private String abnormalResult;

    /**
     * 字段描述: 检查、检验方法(长度:100)
     * 字段定义: 患者接受医学检查项目所采用的检查方法
     */
    @FillEmpty
    @JsonProperty("WS02_10_027_01")
    private String inspectionMethod;

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
     * 字段描述: 报告日期时间(长度:14)
     * 字段定义: 生成报告的当日的公元纪年日期和时间
     * 规则: 1、符合【yyyyMMddHHmmss】的格式
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @NotNull(message = "报告日期时间不能为空")
    @JsonProperty("WS06_00_926_01")
    private LocalDateTime reportTime;

    /**
     * 字段描述: 检验互认编码(长度:50)
     * 字段定义: 检验互认编码
     * 规则: 在【CT99.99.397检验互认编码】值域范围内
     */
    @JsonProperty("WS99_99_999_17")
    private String checkMutualRecognitionCode;

    /**
     * 字段描述: 结果正常标志(长度:10)
     * 字段定义: 结果正常标志
     * 规则: 在【CT99.99.395结果正常标志】值域范围内
     */
    @NotNull(message = "结果正常标志不能为空")
    @JsonProperty("WS99_99_999_19")
    private String okResultSign;

    /**
     * 字段描述: 检验日期时间(长度:14)
     * 字段定义: 检验日期时间
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @NotNull(message = "检验日期时间不能为空")
    @JsonProperty("WS06_00_925_01")
    private LocalDateTime inspectionTime;

    /**
     * 字段描述: CA签名值(长度:1000)
     * 字段定义: 最后一名签名医生（护士）的CA医生签名
     * 规则: 1、门(急)诊检验报告有CA签名可不填
     */
    @JsonProperty("WS99_99_010_48")
    private String caSign;

}
