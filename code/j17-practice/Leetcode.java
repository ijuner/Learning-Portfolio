import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class Leetcode {

   
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        // In case no solution is found
        throw new IllegalArgumentException("No two sum solution");
}

 public boolean isAnagram(String s, String t) {
        // Step 1: Handle the edge case
        if (s.length() != t.length()) {
            return false;
        }

        // Step 2: Build the frequency map for string s
        Map<Character, Integer> charCounts = new HashMap<>();
        for (char c : s.toCharArray()) {
            System.out.println("Processing character: " + c);
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        // Step 3: Decrement counts using string t
        for (char c : t.toCharArray()) {
            System.out.println("Checking character: " + c);
            int count = charCounts.getOrDefault(c, 0);
            if (count == 0) {
                // t has a character that s doesn't have, or has too many
                return false;
            }
            charCounts.put(c, count - 1);
            System.out.println("Updated count for character " + c + ": " + (count - 1));
        }

        // If we reach here, it means they are anagrams
        return true;
    }
 public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] counts = new int[26]; // for 26 lowercase letters

        // Increment count for chars in s
        for (int i = 0; i < s.length(); i++) {
            System.out.println("Processing character: " + s.charAt(i));
            counts[s.charAt(i) - 'a']++;
            // Debugging output to show the count array
            System.out.println("Count for character " + s.charAt(i) + ": " + counts[s.charAt(i) - 'a']);
        }

        // Decrement count for chars in t
        for (int i = 0; i < t.length(); i++) {
            System.out.println("Processing character: " + t.charAt(i)); 
            counts[t.charAt(i) - 'a']--;
            // If at any point the count becomes negative, it means t has an extra char not in s
            if (counts[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }


    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            System.out.println(left + " " + right);
            // Move left pointer if not a letter or digit
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                System.out.println(left + " " + left + " " + right);
                System.err.println("left-Skipping non-letter/digit at left: " + left);
                System.err.println(s.charAt(left));
            }

            // Move right pointer if not a letter or digit
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                System.out.println(right + " " + left + " " + right);
                right--;
                System.err.println("right-Skipping non-letter/digit at right: " + right);
                 System.err.println(s.charAt(right));
            }  

            // Compare the two characters
            System.out.println("Comparing: " + s.charAt(left) + " and " + s.charAt(right));
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            // Move pointers inward
            left++;
            right--;
        }

        return true;
    }

        public boolean isPalindrome2(String s) {

StringBuilder sb = new StringBuilder();
for (char c : s.toCharArray()) {
  if (Character.isLetterOrDigit(c)) {
    sb.append(Character.toLowerCase(c));
  }
}
String cleaned = sb.toString();
return cleaned.equals(sb.reverse().toString());
        }


         public int maxArea(int[] height) {
        int left = 0;                      // Left pointer at start
        int right = height.length - 1;     // Right pointer at end
        int max = 0;                       // To keep track of max area

        while (left < right) {
            // Calculate width between the two pointers
            int width = right - left;

            // Find the shorter of the two lines
            int h = Math.min(height[left], height[right]);
            // Debugging output
            System.out.println("Left pointer: " + left + ", Right pointer: " + right);
            System.out.println("Height at left: " + height[left] + ", Height at right: " + height[right]);
            System.out.println("Current height: " + h);

            // Calculate current area
            int area = width * h;
            System.out.println("Current area: " + area);

            // Update max if current area is greater
            max = Math.max(max, area);
            System.out.println("Max area so far: " + max);

            // Move the shorter pointer inward
            if (height[left] < height[right]) {
                left++;  // Move left pointer right
                System.out.println("Moving left pointer to: " + left);
            } else {
                right--; // Move right pointer left
                System.out.println("Moving right pointer to: " + right);
            }
        }

        return max;
    }


     public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0, right = 0;
        int maxLen = 0;

        while(right < s.length()){
            System.out.println("Current window: " + s.substring(left, right) + ", Left: " + left + ", Right: " + right);
            char c = s.charAt(right);
            while(window.contains(c)){
                System.out.println("Character " + c + " already in window, removing " + s.charAt(left) + " from left");
                // If the character is already in the window, remove the leftmost character
                window.remove(s.charAt(left));
                left++;
            }
            window.add(c);
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
        
    }


public static void main(String[] args) {
        Leetcode solution = new Leetcode();

        // Example for twoSum
        // int[] nums = {2, 7, 11, 15};
        // int target = 9;
        // int[] result = solution.twoSum(nums, target);
        // System.out.println("Two Sum Result: " + result[0] + ", " + result[1]);

        // Example for isAnagram
        // String s = "anagram";
        // String t = "nagaram";
        // boolean isAnagramResult = solution.isAnagram(s, t);
        // System.out.println("Is Anagram: " + isAnagramResult);

        // Example for isAnagram2
        // String s2 = "rat";      
        // String t2 = "car";
        // boolean isAnagram2Result = solution.isAnagram2(s2, t2);
        // System.out.println("Is Anagram2: " + isAnagram2Result);

        // Example for isPalindrome
        // String s3 = "A man, a plan, a canal: Panama";
        // boolean isPalindromeResult = solution.isPalindrome(s3);
        // System.out.println("Is Palindrome: " + isPalindromeResult);


        // Example for maxArea
        // int[] height = {1,8,6,2,5,4,8,3,7};
        // int maxAreaResult = solution.maxArea(height);
        // System.out.println("Max Area: " + maxAreaResult);

        // Example for lengthOfLongestSubstring
        String s4 = "abcabcbb";
        int lengthOfLongestSubstringResult = solution.lengthOfLongestSubstring(s4);
        System.out.println("Length of Longest Substring: " + lengthOfLongestSubstringResult);   

    }


}
