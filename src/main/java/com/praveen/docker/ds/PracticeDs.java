package ds;


import java.util.*;

//sealed class in java
public class PracticeDs {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4};
        int target = 6;
        System.out.println(Arrays.toString(twoSum(nums, target)));

    }

// [3,2,4] , 6
//    Problem: Find two indices such that their values sum up to the target.
//    Approach: Use a HashMap to store value → index.
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


//     2. Move Zeroes [0,1,0,3,12] , [1,3,12,0,0]
//    Problem: Move all 0s to the end of the array while keeping the order of non-zero elements.
//    Approach: Two pointers.
    public void moveZeroes(int[] nums) {
        int insertPos = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

//    Kadane’s Algorithm (Maximum Subarray Sum)
//    Problem: Find the contiguous subarray with the largest sum.
//    Approach: Keep track of currentSum and update maxSum.
//    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//    Output: 6
//    Explanation: The subarray [4,-1,2,1] has the largest sum 6.
    public int maxSubArray(int[] nums) {
        int cu = 0, mx = nums[0];
        for (int num : nums) {
            cu = cu + num;
            mx = Math.max(cu, mx);
            if (cu < 0) cu = 0;
        }
        return mx;
    }

//    Merge Intervals
//    Problem: Merge all overlapping intervals.
//    Approach: Sort by start time, then merge overlapping intervals.
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(interval[1], merged.getLast()[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


//    Search in Rotated Sorted Array
//    Problem: Find target in rotated sorted array.
//    Approach: Binary search with conditions for rotation.
//    Input: nums = [4,5,6,7,0,1,2], target = 0
//    Output: 4
    public static int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int mid = (start+end) / 2;
            if (nums[mid] == target) return mid;

            //left half is sorted
            if (nums[start] <= nums[mid]) {
                if (nums[start] <= target && target < nums[mid])
                    end = mid - 1;
                else start = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[end]) start = mid + 1;
                else end = mid - 1;
            }
        }
        return -1;
    }

//    Dutch National Flag (Sort 0s, 1s, 2s)
//    Problem: Sort an array of 0s, 1s, and 2s in-place.
//            Approach: 3 pointers: low, mid, high.
//    Input: nums = [2,0,2,1,1,0]
//    Output: [0,0,1,1,2,2]
    public void sortColors(int[] nums) {
        int start = 0, mid = 0, end = nums.length - 1;

        while (mid <= end) {
            switch (nums[mid]) {
                case 0:
                    swap(nums, start, mid);
                    start++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(nums, mid, end);
                    end--;
                    break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

//  Subarray Sum Equals K
//    Problem: Count the number of subarrays whose sum equals k.
//            Approach: Prefix sum with HashMap.
//    Input: nums = [1,2,3], k = 3
//    Output: 2
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // One way to make sum 0 before starting

        int sum = 0,answer=0;

        for (int num : nums) {
            sum += num;

            // If (sum - k) seen before, then there exists a subarray ending here with sum k
            if (map.containsKey(sum - k)) {
                answer += map.get(sum - k);
            }

            // Add current sum to map
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return answer;
    }

//    Maximum Average Subarray I
//    Problem: Find the maximum average of a contiguous subarray of length k.
//            Approach: Sliding window.
//    Input: nums = [1,12,-5,-6,50,3], k = 4
//    Output: 12.75000
//    Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
    public double findMaxAverage(int[] nums, int k) {
        double sum = 0;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        double maxSum = sum;

        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum / k;
    }


//    . Minimum Size Subarray Sum
//    Problem: Find the minimal length of a contiguous subarray of which the sum ≥ target.
//            Approach: Sliding window.
//    Input: target = 7, nums = [2,3,1,2,4,3]
//    Output: 2
//    Explanation: The subarray [4,3] has the minimal length under the problem
    public int minSubArrayLen(int target, int[] nums) {
        int start = 0, sum = 0, minLen = Integer.MAX_VALUE;
        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];
            while (sum >= target) {
                minLen = Math.min(minLen, end - start + 1);
                sum -= nums[start];
                start++;
            }
        }

        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }

//    Longest Palindromic Substring
//    Problem: Return the longest palindromic substring in s.
//            Approach: Expand around center.
//    Input: s = "babad"
//    Output: "bab"
//    Explanation: "aba" is also a valid answer.
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;  // Track start and end of longest palindrome

        for (int i = 0; i < s.length(); i++) {
            // Try odd-length palindrome centered at i
            int len1 = expandFromCenter(s, i, i);

            // Try even-length palindrome centered between i and i+1
            int len2 = expandFromCenter(s, i, i + 1);

            int len = Math.max(len1, len2);  // Take the longer one

            // If new palindrome is longer than previous
            if (len > end - start) {
                // Update start and end index
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);  // Return the longest palindrome
    }
    private int expandFromCenter(String s, int left, int right) {
        // Expand as long as characters match and are in bounds
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Length of the palindrome = right - left - 1
        return right - left - 1;
    }

//    Valid Anagram
//    Problem: Check if t is an anagram of s.
//    Input: s = "anagram", t = "nagaram"
//    Output: true
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

//    Longest Palindrome by Rearranging
//    need solution

//    Given a string s, return the length of the longest palindrome that can be built with those letters.
//    Input: s = "abccccdd"
//Output: 7
//Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
    public int longestPalindrome2(String s) {
        Set<Character> set = new HashSet<>();
        int maxLen =0;
        for(char c : s.toCharArray()){
            if(set.contains(c)){
                maxLen+=2;
                set.remove(c);
            }
            else{
                set.add(c);
            }
        }
        return set.isEmpty()? maxLen:maxLen+1;
    }

// Longest Substring Without Repeating Characters
//    Input: s = "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3.
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left=0,maxLen = 0;
        for(int right=0;right<s.length();right++){
            while(!set.add(s.charAt(right)))
                set.remove(s.charAt(left++));
            maxLen = Math.max(maxLen,right-left+1);
        }
        return maxLen;
    }

//    Group Anagrams
//    Problem: Group strings that are anagrams.
//            Approach: Sort characters and use them as key in a HashMap.
//    Input: strs = ["eat","tea","tan","ate","nat","bat"]
//    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] array = anagram(s);
            String key = new String(array);
            //both work
            map.computeIfAbsent(key,k-> new ArrayList<>()).add(s);
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

    public char[] anagram(String s){
        char[] ar = s.toCharArray();
        Arrays.sort(ar);
        return ar;
    }

//🔹 KMP Algorithm
    // need to look

 public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
     ListNode(int val){
          this.val = val;
     }
  }

  //reverse linked list
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while(curr!=null)
        {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    //cycle in linkedlist
    public boolean hasCycle(ListNode head) {
        if(head==null || head.next==null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast!=null && fast.next!=null)
        {
            if(slow==fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

//merge two sorted list
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode list = new ListNode();
        ListNode newList = list;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                newList.next = list1;
                list1 = list1.next;
            } else {
                newList.next = list2;
                list2 = list2.next;
            }
            newList = newList.next;
        }
        if (list1 != null) newList.next = list1;
        if (list2 != null) newList.next = list2;
        return list.next;
    }

    //not good approach intersection of record
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while(headA!=null){
            set.add(headA);
            headA = headA.next;
        }
        while(headB!=null){
            if(set.contains(headB))
                return headB;
            else{
                set.add(headB);
                headB = headB.next;
            }
        }
        return null;
    }
//Best
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while(a !=b){
            a = ( a==null) ? headB : a.next;
            b = (b==null) ? headA : b.next;
        }
        return a;
    }

//    Copy List with Random Pointer
    // need to look

    //selfown answer code
//    Add Two Numbers
//    Problem: Add two numbers represented by linked lists (digits in reverse order).
//    Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//Output: [8,9,9,9,0,0,0,1]
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode list = head;
        int carry = 0,sum=0;
        while(l1!=null && l2!=null){
            sum = l1.val + l2.val + carry;
            list.next = new ListNode(sum%10);
            list = list.next;
            carry=sum/10;
            l1=l1.next;
            l2=l2.next;
        }
        while(l1!=null){
            sum = l1.val + carry;
            list.next = new ListNode(sum%10);
            list = list.next;
            carry=sum/10;
            l1=l1.next;
        }
        while(l2!=null){
            sum = l2.val + carry;
            list.next = new ListNode(sum%10);
            list = list.next;
            carry=sum/10;
            l2=l2.next;
        }
        if(carry!=0){
            list.next = new ListNode(carry);
        }
        return head.next;
    }

//    Valid Parentheses
//Problem: Check if input string has valid open-close brackets.
//Approach: Use a Stack to track open brackets.
//Input: s = "()[]{}"
//Output: true
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

    //min stack
//    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    class MinStack {
        Stack<Integer> stack =null;
        Stack<Integer> minHeap =null;


        public MinStack() {
            stack = new Stack<>();
            minHeap = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if(minHeap.isEmpty())
                minHeap.push(val);
            else
                minHeap.push(Math.min(val,minHeap.peek()));
        }

        public void pop() {
            stack.pop();
            minHeap.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {

            return minHeap.peek();
        }
    }

    // Next Greater Element
    //need to look
//    LRU Cache
//🔹 Circular Queue Implementation


    //Longest Consecutive Sequence
//    Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence
//    Input: nums = [100,4,200,1,3,2]
//Output: 4
//Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int maxLength = 0;

        for (int num : set) {
            // Only try to build from start of sequence
            if (!set.contains(num - 1)) {
                int curr = num;
                int streak = 1;

                while (set.contains(curr + 1)) {
                    curr++;
                    streak++;
                }
                maxLength = Math.max(maxLength, streak);
            }
        }
        return maxLength;
    }

//    Top K Frequent Elements
    //Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
//    Input: nums = [1,1,1,2,2,3], k = 2
    //Output: [1,2]
    public int[] topKFrequent(int[] nums, int k) {
        List<Integer> list = Arrays.stream(nums).boxed().toList();
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int num : list){
            if(!hm.containsKey(num))
                hm.put(num,Collections.frequency(list,num));
        }
        int size= hm.size()-k;
        return hm.entrySet().stream().sorted(Map.Entry.comparingByValue()).skip(size).map(Map.Entry::getKey).mapToInt(Integer::intValue).toArray();

    }


    //group anagram
//    Given an array of strings strs, group the anagrams together. You can return the answer in any order.
//    Input: strs = ["eat","tea","tan","ate","nat","bat"]
//Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
    public List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String,List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] array = s.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            map.computeIfAbsent(key,k-> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }


    // word pattern
//Given a pattern and a string s, find if s follows the same pattern.
//    Input: pattern = "abba", s = "dog cat cat dog"
//Output: true
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


        //isomorphic string

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
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }
    //inorder traversal tree
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(list,root);
        return list;
    }

    void inOrder(List<Integer> list,TreeNode root){
        if(root!=null){
            inOrder(list,root.left);
            list.add(root.val);
            inOrder(list,root.right);
        }
    }

//Preorder
void preOrder(List<Integer> list,TreeNode root){
    if(root!=null){
        list.add(root.val);
        preOrder(list,root.left);
        preOrder(list,root.right);
    }
}

// level order traversal - BSF
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0;i< size;i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            result.add(list);
        }
        return result;
    }

//  Inorder traversal using stack
//     stack iterative
public List<Integer> inOrder(TreeNode treeNode) {
    List<Integer> list = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = treeNode;
    while (!stack.isEmpty() || curr != null) {
        if (curr != null) {
            stack.push(curr);
            curr = curr.left;
        } else {
            curr = stack.pop();
            list.add(curr.val);
            curr = curr.right;
        }
    }
    return list;
}

// pre order iterative using stack
    public List<Integer> preOrder(TreeNode treeNode) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        TreeNode curr = stack.peek();
        while (!stack.isEmpty()) {
            curr = stack.pop();
            list.add(curr.val);
            if(curr.right!=null) stack.push(curr.right);
            if(curr.left!=null) stack.push(curr.left);
        }
        return list;
    }

    //postorder using stack/deque iterative
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root==null) return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode curr = null;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            list.addFirst(curr.val);
            if(curr.left!=null) stack.push(curr.left);
            if(curr.right!=null) stack.push(curr.right);
        }
        return list;
    }

    //Diameter of binary tree
//    The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
    int maxDiameter  = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxDiameter;
    }
    public int depth(TreeNode root){
        if(root==null) return 0;
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        maxDiameter = Math.max(maxDiameter,(leftDepth+rightDepth));
        return Math.max(leftDepth,rightDepth)+1;
    }

    //lowest common ancestor

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        else if (right == null) return left;
        else return root;
    }

}
