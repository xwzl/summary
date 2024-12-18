package leetcode.editor.cn.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author xuweizhi
 * @since 2024-11-20 17:17
 */
public class xx {

    public static void main(String[] args) {
        String str1 = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String sql = "INSERT INTO `his_ca_sit`.`base_diagnosis` (`diag_id`, `org_id`, `diag_icd_code`, `diag_name`, `diag_morphological_code`, `diag_pinyin`, `diag_search_code`, `diag_search_instructions`, `diag_instructions`, `diag_type`, `diag_scope_outpatient`, `diag_scope_inpatient`, `diag_scope_emergency`, `diag_scope_emergency_keep`, `diag_infection`, `diag_special_disease`, `diag_status`, `diag_disease_report`, `diag_remark`, `create_by`, `create_time`, `update_by`, `update_time`, `is_deleted`) VALUES (null, 1, '"
                + str1 + "', '" +
                str2
                + "', NULL, '" + str3 + "', '" + str4 + "', NULL, NULL, 'FH0022.04', 1, 1, 1, 1, NULL, NULL, 1, NULL, NULL, 1, '2024-03-04 00:00:00', 1, '2024-03-04 00:00:00', 0);";


        String read = NioUtil.read("/Users/xuweizhi/Documents/projects/summary/java/algorithm/src/main/java/leetcode/editor/cn/utils/xx");



        try (BufferedReader reader = Files.newBufferedReader(Paths.get("/Users/xuweizhi/Documents/projects/summary/java/algorithm/src/main/java/leetcode/editor/cn/utils/xx"))){


            String line;
            while ((line = reader.readLine()) != null) {
                // 处理每一行数据，例如转换为大写
                String processedLine = line.toUpperCase();
                String[] split1 = line.split("\t");
                str1 = split1[0];
                str2 = split1[1];
                str3 = split1[2];
                str4 = split1[2];
                sql = "INSERT INTO `his_ca_sit`.`base_diagnosis` (`diag_id`, `org_id`, `diag_icd_code`, `diag_name`, `diag_morphological_code`, `diag_pinyin`, `diag_search_code`, `diag_search_instructions`, `diag_instructions`, `diag_type`, `diag_scope_outpatient`, `diag_scope_inpatient`, `diag_scope_emergency`, `diag_scope_emergency_keep`, `diag_infection`, `diag_special_disease`, `diag_status`, `diag_disease_report`, `diag_remark`, `create_by`, `create_time`, `update_by`, `update_time`, `is_deleted`) VALUES (null, 1, '"
                        + str1 + "', '" +
                        str2
                        + "', NULL, '" + str3 + "', '" + str4 + "', NULL, NULL, 'FH0022.04', 1, 1, 1, 1, NULL, NULL, 1, NULL, NULL, 1, '2024-03-04 00:00:00', 1, '2024-03-04 00:00:00', 0);";

                System.out.println(sql);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
