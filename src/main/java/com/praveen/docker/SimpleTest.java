package com.praveen.docker;


import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class SimpleTest{

    HashMap<Integer,Integer> map = new HashMap<>();
    public void fabonnaci(int n){
        System.out.println(fab(n));
    }

    public int fab(int n){
        if(n<=1) return n;
        if(map.containsKey(n)) return map.get(n);
        int result = fab(n-1)+fab(n-2);
        map.put(n,result);
        return result;
    }

    public static int knapsack(int[] weights, int[] values, int capacity) {
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < weights.length; i++) {
            for (int w = capacity; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }

        return dp[capacity];
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[0]; // if no solution found
    }


    public static void main(String[] args) {
        SimpleTest tst =new SimpleTest();
        System.out.println("praveen");
        tst.fabonnaci(10);
        int[] weights = {1, 3, 4, 5};
        int[] values = {1, 4, 5, 7};
        int capacity = 7;
        System.out.println(knapsack(weights, values, capacity)); // Outp
        System.out.println(Arrays.toString(twoSum(new int[]{3,2,4},6)));
    }
}