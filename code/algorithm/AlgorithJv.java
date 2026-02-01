import java.util.Map;
import java.util.Set;

import javax.swing.GroupLayout.Group;
import javax.swing.tree.TreeNode;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;    
import java.util.HashSet;



public class AlgorithJv {
// This is a sample Java class for algorithm implementations.
/**
 * ## 1. Two Sum
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.
 * @param args
 */
   public int[] twoSum(int[] nums, int target){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1}; // If no solution found
   }

   //triplets
   public List<List<Integer>> threeSum(int [] nums){
     List<List<Integer>> result = new ArrayList<>();
     Arrays.sort(nums);
     int n = nums.length;
     for(int i = 0; i<n-2;i++){
        int currentNum = nums[i];
        if(currentNum > 0) break;
        if(i > 0 && currentNum == nums[i-1]) continue;
        int left = i +1;
        int right = n-1;
        while (left < right){
            int currentSum = currentNum + nums[left] + nums[right];
        if(currentSum < 0){
            left++;
        } else if (currentSum > 0){
            right--;
        }else{
            result.add(Arrays.asList(currentNum, nums[left], nums[right]));
            while ((left < right) && (nums[left] == nums[left + 1])) left++;
            while ((left < right) && (nums[right] == nums[right - 1])) right--;
            left++;
            right--;
        }
    }
    
}
return result;
   }

/**
 * LC 121. Best Time to Buy and Sell Stock
 * You are given an array prices where prices[i] is the price of a given stock on the i-th day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0
 * @param args
 */
public int maxProfit(int [] prices){
    int minP = Integer.MAX_VALUE;
    int maxProfit = 0;
    for (int i = 0; i< prices.length;i++){
        int currentprice = prices[i];
        if(currentprice < minP){
            minP = currentprice;
        }
        else if(currentprice - minP > maxProfit){
            maxProfit = currentprice - minP;
        }
    }
    return maxProfit;
}

/**
 * LC 217. Contains Duplicate
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
 * @param args
 */
public boolean containsDup(int[] nums){
    Set<Integer> set = new HashSet<>();
    for(int num:nums){
        if(set.contains(num)){
            return true;
        }
        set.add(num);
    }
    return false;
}

/**
 * Maximum subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number
 * @param args
 */
public int maxSubArray(int[] nums){
    int maxSum = nums[0];
    int currentSum = nums[0];
    for(int i = 1;i<nums.length;i++){
        int num = nums[i];
        currentSum = Math.max(num, currentSum + num);
        System.out.println("Current Sum: " + currentSum);
        if(currentSum > maxSum){
            maxSum = currentSum;
        }
    }
    return maxSum;
}

/**
 * LC 3. Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without repeating characters. 
 * @param args
 */

 public int lengthOfLongestSubstring(String s){
    int n = s.length();
    int left = 0; int right = 0;

    Set<Character> set = new HashSet<>();
    int maxLength = 0;
    while(right < n){
        char c = s.charAt(right);
        System.out.println("Character at right pointer: " + c);
        if(!set.contains(c)){
            set.add(c);
            right++;
            maxLength = Math.max(maxLength, set.size());// right - left
        
        }else{
            char leftChar = s.charAt(left);
            System.out.println("Character at left pointer: " + leftChar);
            left++;
            set.remove(leftChar);
        }
    }
    return maxLength;
}

    public int lengthOfLongestSubstring2(String s) {
        // 处理边界情况，如果字符串为空或为null，直接返回0
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 创建一个HashMap，用于存储字符及其最新出现的索引
        // Key: 字符, Value: 该字符最后一次出现的索引
        Map<Character, Integer> map = new HashMap<>();

        int maxLength = 0; // 用于记录找到的最大长度
        int left = 0;      // 滑动窗口的左边界

        // right是滑动窗口的右边界，我们遍历整个字符串
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right); // 获取当前右指针指向的字符

            // 检查当前字符是否在哈希表中已存在
            // 如果存在，说明在 [left, right] 窗口内可能遇到了重复字符
            if (map.containsKey(currentChar)) {
                // 当我们遇到重复字符时，需要收缩窗口的左边界 left。
                // 新的 left 必须在重复字符上一次出现位置的右边。
                // 例如 s = "abba"，当 right=3 指向 'a' 时，'a' 上次在 0。
                // 此时 left 应该更新为 max(当前left, 0 + 1)。
                // 使用 Math.max 是为了确保 left 指针不会向左回退。
                // 比如 s = "abba"，当 right=2 指向 'b' 时，left 更新为 2。
                // 当 right=3 指向 'a' 时，'a' 的旧索引是 0, 0+1=1。
                // 但此时 left 已经是 2 了，我们不能让它退回到 1，所以 left 保持 2。
                left = Math.max(left, map.get(currentChar) + 1);
            }

            // 无论如何，都要更新当前字符在哈希表中的位置为最新的索引 right
            map.put(currentChar, right);

            // 计算当前窗口的长度 (right - left + 1)，并更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }


    //linked list reverse

    public ListNode reverseListNode(ListNode head){
        ListNode prevListNode = null;
        ListNode curListNode = head;
        while(curListNode != null){
            ListNode tempNext = curListNode.next;
            curListNode.next = prevListNode;
            prevListNode = curListNode;
            curListNode = tempNext;
            
        }
        return prevListNode;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    //linkedlist cycle
    public boolean hasCycle(ListNode head){
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null ){
            
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    // get max depth of binary tree
    public int maxDepth(TreeNode root){
        if ((root == null)){
            return 0; 
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }

    //the same tree
    public boolean isSameTree(TreeNode p, TreeNode q){
        if(p == null && q == null){
            return true;
        }
        if(p == null || q == null){
            return false;
        }
        if(p.val != q.val){
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 主函数，启动递归检查  有效的二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        // 调用辅助函数，初始范围是 (-∞, +∞)
        // 我们用 null 来代表无穷大和无穷小
        return isValid(root, null, null);
    }

    /**
     * 辅助递归函数
     * @param node   当前节点
     * @param lower  值的下界 (必须 > lower)
     * @param upper  值的上界 (必须 < upper)
     * @return       是否是有效的BST
     */
    private boolean isValid(TreeNode node, Long lower, Long upper) {
        // Base Case: 空树是有效的BST
        if (node == null) {
            return true;
        }

        // 检查当前节点的值是否在有效范围内
        // 如果 lower 不为 null 且当前节点值小于等于下界，则无效
        if (lower != null && node.val <= lower) {
            return false;
        }
        // 如果 upper 不为 null 且当前节点值大于等于上界，则无效
        if (upper != null && node.val >= upper) {
            return false;
        }

        // 递归检查子树
        // 1. 检查左子树：
        //    - 左子树的下界继承父节点的下界 (lower)
        //    - 左子树的上界更新为当前节点的值 (node.val)
        // 2. 检查右子树：
        //    - 右子树的下界更新为当前节点的值 (node.val)
        //    - 右子树的上界继承父节点的上界 (upper)
        // 必须左右子树都有效，整棵树才有效
        return isValid(node.left, lower, (long)node.val) && 
               isValid(node.right, (long)node.val, upper);
    }

    //first unique character in a string
    public int firstUniqChar(String s) {
        // 1. 创建一个长度为 26 的数组，因为只有小写字母
        int[] counts = new int[26];

        // 2. 第一次遍历：统计频率
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counts[c - 'a']++; // 'a'->0, 'b'->1, ...
        }

        // 3. 第二次遍历：按顺序查找
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 4. 如果该字符的计数为1，返回索引
            if (counts[c - 'a'] == 1) {
                return i;
            }
        }

        // 5. 未找到，返回 -1
        return -1;
    }

    /**
     * Two Strings（判断两个字符串是否存在公共字符）考点： 集合交集 / 布尔桶。
     * @param args
     */
    public static String twoStrings(String s1, String s2) {
        // 1. Create a boolean bucket for all 26 lowercase letters.
        boolean[] charBucket = new boolean[26];

        // 2. Populate the bucket based on characters in s1.
        for (char c : s1.toCharArray()) {
            // Map character 'a' to index 0, 'b' to 1, etc.
            charBucket[c - 'a'] = true;
        }

        // 3. Iterate through the second string.
        for (char c : s2.toCharArray()) {
            // 4. Check if this character's flag is set in the bucket.
            if (charBucket[c - 'a']) {
                // 5. If true, a common character is found.
                return "YES";
            }
        }

        // 6. No common characters found.
        return "NO";
    }

    // Group Anagrams（将异位词分组，返回列表大小或示例分组）
    public static List<List<String>> groupAnagrams(List<String> strs) {
        Map<String, List<String>> anagramMap = new HashMap<>();
        for (String s : strs) {
            // 1. 将字符串转换为字符数组并排序
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sorted = new String(charArray);

            // 2. 将原始字符串放入对应的分组中
            anagramMap.computeIfAbsent(sorted, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(anagramMap.values());
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> anagramMap = new HashMap<>();

        for (String s : strs) {
            // 3a. 创建频率数组
            int[] counts = new int[26];

            // 3b. 统计频率
            for (char c : s.toCharArray()) {
                counts[c - 'a']++;
            }

            // 3c. 生成唯一的 Key
            StringBuilder keyBuilder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                keyBuilder.append(counts[i]);
                keyBuilder.append('#'); // 使用分隔符
            }
            String key = keyBuilder.toString();

            // 3d. 将字符串放入对应的分组
            anagramMap.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(anagramMap.values());
    }


    public static void main(String[] args) {
        System.out.println("Algorithm Java Class");
        // Example usage of the twoSum method
        AlgorithJv algo = new AlgorithJv();
        // int[] nums = {2, 7, 11, 15};
        // int target = 17;
        // int[] result = algo.twoSum(nums, target);
        // System.out.println("Two Sum Result: " + Arrays.toString(result));


        // int[] prices = {7,1,5,3,6,15};
        // int maxProfit = algo.maxProfit(prices);
        // System.out.println("Max Profit: " + maxProfit);

        // int[] nums = {1,2,3,1};
        // boolean hasDuplicate = algo.containsDup(nums);
        // System.out.println("Contains Duplicate: " + hasDuplicate);

        // int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        // int maxSubArraySum = algo.maxSubArray(nums);
        // System.out.println("Max Subarray Sum: " + maxSubArraySum);

        // String s = "abcDjcbbcdfkjh";
        // int length = algo.lengthOfLongestSubstring(s);
        // System.out.println("Length of Longest Substring: " + length);

        // ListNode head = new ListNode(1);
        // head.next = new ListNode(2);
        // head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);

        // System.out.print("Original List: ");
        // printList(head);

        // // 2. 调用反转方法

        // ListNode reversedHead = algo.reverseListNode(head);

        // // 3. 打印反转后的链表
        // System.out.print("Reversed List: ");
        // printList(reversedHead);

        // List<List<Integer>> triplets = algo.threeSum(new int[]{-1,0,1,2,-1,-4});
        // System.out.println("Triplets: " + triplets);

  

    }



    // 辅助方法：打印链表
    public static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}


