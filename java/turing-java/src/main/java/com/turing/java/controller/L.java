package com.turing.java.controller;

import java.util.*;
import java.util.stream.Collectors;

/**
 * L
 *
 * @Description
 * @date 2024-06-05 12:19 星期三 summary
 */
public class L {
    public static void main(String[] args) {

        int x = -121;
        boolean palindrome = isPalindrome(x);
    }

    public static boolean isPalindrome(int x) {

        List<Integer> list = new ArrayList<>();
        while (true) {
            int e = x % 10;
            list.add(e);
            x = x / 10;
            if (x == 0) {
                break;
            }
        }


        int length = list.size();
        int i = length / 2;

        for (int j = 0; j < i; j++) {
            if (list.get(j) != list.get(length - j-1)) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> result = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] a = strs[i].toCharArray();
            Arrays.sort(a);
            String s = new String(a);
            List<String> orDefault = result.getOrDefault(s, new ArrayList<>());
            orDefault.add(strs[i]);
            result.put(s, orDefault);
        }
        return result.values().stream().collect(Collectors.toList());
    }
}
