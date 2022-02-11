package com.spring.cloud.oauth2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * JWT 测试
 *
 * @author xuweizhi
 * @since 2022/02/11 15:45
 */
public class JsonWordToken {


    /**
     * %s
     * %s
     */
    private String string;

    @Test
    public void test() {
        //创建一个JwtBuilder对象
        JwtBuilder jwtBuilder = Jwts.builder()
                //声明的标识{"jti":"666"}
                .setId("666")
                //主体，用户{"sub":"Nice"}
                .setSubject("Fox")
                //创建日期{"ita":"xxxxxx"}
                .setIssuedAt(new Date())
                //签名手段，参数1：算法，参数2：盐
                .signWith(SignatureAlgorithm.HS256, "123123");
        //获取token
        String token = jwtBuilder.compact();
        System.out.println(token);

        //三部分的base64解密
        System.out.println("=========");
        String[] split = token.split("\\.");
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
        //无法解密
        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));
    }

    @Test
    public void testParseToken() {
        //token
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiJGb3giLCJpYXQiOjE2MDgyNzI1NDh9" +
                ".Hz7tk6pJaest_jxFrJ4BWiMg3HQxjwY9cGmJ4GQwfuU";
        //解析token获取载荷中的声明对象
        Claims claims = Jwts.parser()
                .setSigningKey("123123")
                .parseClaimsJws(token)
                .getBody();

        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        System.out.println("issuedAt:" + claims.getIssuedAt());
    }

    @Test
    public void stringMethod() {
        String test = "Hospital_Code\n" +
                "医院代码\n" +
                "varchar(32)\n" +
                "N\n" +
                "对接接口约定的医院机构编码，多院区则为院区编码\n" +
                "Recipe_Item_Id\n" +
                "处方明细编号\n" +
                "varchar(32)\n" +
                "N\n" +
                "医疗机构内部记录的处方项目明细编号，主键\n" +
                "Recipe_Id\n" +
                "处方ID\n" +
                "varchar(32)\n" +
                "N\n" +
                "医疗机构内部记录的处方唯一编号，外键，关联门诊处方信息表\n" +
                "Group_No\n" +
                "组号\n" +
                "varchar(32)\n" +
                "N\n" +
                "药物分组使用时的组号\n" +
                "Drug_Id\n" +
                "药品编号\n" +
                "varchar(32)\n" +
                "N\n" +
                "医院药品id，要求能够唯一标识 “通用名，剂型，规格，厂家”的编码\n" +
                "Drug_Name\n" +
                "药品名称\n" +
                "varchar(64)\n" +
                "N\n" +
                "药品的通用名称\n" +
                "Specification\n" +
                "规格  \n" +
                "varchar(64)\n" +
                "N\n" +
                "药品规格的描述\n";

        String[] split = test.split("\n");
        for (int i = 0; i < split.length / 5; i++) {
            int index = i * 5;
            // todo 类型需转换
            String field = "/**\n* %s\n* %s\n*/\nprivate String %s;";
            String s = split[index];
            String replace = s.replace("_", "");

            int i1 = (int)replace.charAt(0) + 32;
            replace= (char) i1 + replace.substring(1);
            System.out.println(String.format(field, split[index + 3] == "N"?"必填":"非必填", split[index + 1].trim() + ":" + split[index + 4], replace));
        }
    }

}
