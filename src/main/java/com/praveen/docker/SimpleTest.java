package com.praveen.docker;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimpleTest {
    public static void main(String[] args) {
        SimpleTest s = new SimpleTest();
        int[] nums = {100,4,200,1,3,2};
//        s.longestCon2(nums);
//        topK();
        String pattern = "abba", ss = "dog cat cat fish";
        System.out.println(wordPattern(pattern,ss));
       String sa = "egg", t = "add";
        System.out.println(s.isIsomorphic(sa,t));
    }

    public boolean isIsomorphic(String s, String t) {

        HashMap<Character, Character> char1 = new HashMap<>();
        HashMap<Character, Character> char2 = new HashMap<>();
        if (s.length() != t.length()) return false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            char word = t.charAt(i);
            if (char1.containsKey(ch)) {
                if (!char1.get(ch).equals(word)) return false;
            } else {
                if (char2.containsKey(word)) return false;
                char1.put(ch, word);
                char2.put(word, ch);
            }
        }
        return true;
    }


    public static boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> charToWord = new HashMap<>();
        HashMap<String, Character> wordToChar = new HashMap<>();
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String word = words[i];
            if (charToWord.containsKey(ch)) {
                if (!charToWord.get(ch).equals(word)) return false;
            } else {
                if (wordToChar.containsKey(word)) return false;
                charToWord.put(ch, word);
                wordToChar.put(word, ch);
            }
        }
        return true;
    }


    public static void topK(){
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        List<Integer> list = Arrays.stream(nums).boxed().toList();
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int num : set){
            hm.put(num,Collections.frequency(list,num));
        }
        int size= hm.size()-k;
        System.out.println(hm.entrySet().stream().sorted(Map.Entry.comparingByValue()).skip(size).map(Map.Entry::getKey).toArray(Integer[]::new));
        int[] aarr = hm.entrySet().stream().sorted(Map.Entry.comparingByValue()).skip(size).map(Map.Entry::getKey).mapToInt(Integer::intValue).toArray();
    }

    public void longestCon2(int[] nums){
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        //100,4,200,1,3,2
        int curr = 0,max=0;
        for(int num : nums){
            curr=0;
            if(!set.contains(num-1)){
                curr++;
                while(set.contains(num+1)){
                    curr++;
                    num++;
                }
                max = Math.max(curr,max);
            }
        }
        System.out.println(max);
    }

    public void longestCon(int[] nums){
        // 1,2,3,4,100,200
        HashMap<Integer,Boolean> map = Arrays.stream(nums).boxed().collect(Collectors.toMap(t->t,t->false,(a,b)->a,HashMap::new));
        System.out.println(map);
        int next = 0,maxLong=0,curr=0;
        for(int i=0;i<nums.length;i++){
            next = nums[i]+1;
            curr=1;
            while(map.containsKey(next) && map.get(next)==false){
                map.put(next,true);
                curr++;
                next++;
            }

            next = nums[i]-1;
            while(map.containsKey(next) && map.get(next)==false){
                map.put(next,true);
                curr++;
                next--;
            }
            maxLong = Math.max(curr,maxLong);
        }
        System.out.println("MaxLong - "+maxLong);
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
