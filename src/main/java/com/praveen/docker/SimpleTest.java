package com.praveen.docker;

import java.util.*;

public class SimpleTest {
    public static void main(String[] args) {
        SimpleTest s = new SimpleTest();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
//        HashMap<Integer,Integer> map = new HashMap<>();
//        map.put(3,2);
//        System.out.println(map.getOrDefault(4,0));
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(s.groupAnagrams(strs));
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] array = anagram(s);
            String key = new String(array);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);

//            if(map.containsKey(key))
//            {
//                List<String> list = new ArrayList<>(map.get(key));
//                list.add(s);
//                map.put(key,list);
//            }else {
//                map.put(key, List.of(s));
//            }
        }
        return map.values().stream().toList();
    }

    public char[] anagram(String s) {
        char[] ar = s.toCharArray();
        Arrays.sort(ar);
        return ar;
    }

    class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            char[] ch = s.toCharArray();
            for (char c : ch) {
                if (c == '(' || c == '[' || c == '{') stack.push(c);
                else {
                    if (stack.isEmpty()) return false;
                    char top = stack.pop();
                    if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) return false;

                }
            }
            return stack.isEmpty();
        }
    }


    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] ar = new int[26];
        for (int i = 0; i < s.length(); i++) {
            ar[s.charAt(i) - 'a']++;
            ar[t.charAt(i) - 'a']--;
        }

        for (int num : ar) {
            if (num != 0) return false;
        }
        return true;
    }

}
