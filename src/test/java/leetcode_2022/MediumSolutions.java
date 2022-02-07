package leetcode_2022;

import java.util.*;

public class MediumSolutions


{
    class Node {
        int val;
        Node next;

        public Node(int val,Node next){
            this.val = val;
            this.next = next;
        }
    }
    public static void main(String[] args){

       // int[] nums = new int[] {2,3,1,2,4,3};
      //  System.out.println(minimumSizeSubArraySum(nums,7));

       // System.out.println(isValidParenthesisString("(*)"));

        //int[] nums = new int[]{0,0,1,1,1,2,2,3,3};
        //System.out.println(removeDuplicateFromSortedArray(nums));
        int[] nums = new int[]{1,0,2,0,0,7};
        moveZeros(nums);
        System.out.println(Arrays.toString(nums));


    }

    public void  findAllAnagramsInAString(String original,String check){

    }

    public static int longestSubWithRepeatingChar(String s){
       // Input: abccabcabcc  ====>  3
        int windowStart= 0;
        Map<Character,Integer> map = new HashMap<>();
        int winlen = Integer.MIN_VALUE;
        int index =0;
       while(index < s.length()){
           if(!map.containsKey(s.charAt(index))){
               map.put(s.charAt(index),index);
           }else{
               map.remove(s.charAt(windowStart));
               windowStart +=1;
           }

           winlen  = Math.max(winlen,index-windowStart+1);
       }
       return winlen;

    }

    public static boolean isPalindrome(String s){
        //two pointers in opposite direction
        int left =0;
        int right = s.length()-1;
        while(left< right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left +=1;
            right -=1;
        }
        return true;

    }

    public static int[] twoSortedSortedArrayUsingTwoPointers(int[] nums,int target){
        // two pointers in opposite direction
        int left=0;
        int[] res = new int[2];
        int right = nums.length-1;
        while(left <right){
            int temp = nums[left]+ nums[right];
            if(temp== target){
                res[0] =left;
                res[1] = right;
            }
            left +=1;
            right -=1;
        }
        return res;
    }

    public static void moveZeros(int[] nums){
        //[1, 0, 2, 0, 0, 7]  ==== >  [1, 2, 7, 0, 0, 0]  [1,2,7]

        int index=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                nums[index] = nums[i];
                index++;
            }
        }
        for(int i=index;i<nums.length;i++){
            nums[i] =0;
        }
    }
    public static int middleOfLinkedList(Node head){
        Node slow = head;
        Node fast = head;
        while(fast !=null && fast.next !=null){
            slow  = slow.next;
            fast = fast.next.next;
        }
        return slow.val;
    }
    public static int removeDuplicateFromSortedArray(int[] nums){
        // [0,0,1,1,1,2,2]  ====>  [0,1,2]
        int count=0;
        int windowStart = 0;
        for(int i=0;i<nums.length;i++){
           if(nums[windowStart] == nums[i]){
               continue;
           }else {
               windowStart = i;
               count +=1;
           }
        }
        return count+1;
    }

    public static int minimumSizeSubArraySum(int[] nums,int target){
        /*
            Input: target = 7, nums = [2,3,1,2,4,3]
            Output: 2
            Explanation: The subarray [4,3] has the minimal length under the problem constraint.
         */
        int windowStart =0;
        int minLen = Integer.MAX_VALUE;
        int sum =0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
            while(sum >=target){
                minLen = Math.min(minLen,i-windowStart+1);
                sum -= nums[windowStart];
                windowStart +=1;
            }
        }
        if(minLen ==Integer.MAX_VALUE) return 0;
        else{
            return minLen;
        }
    }

    public static Boolean isValidParenthesisString(String s){

        Stack<Character> stack = new Stack<>();
        for(Character c:s.toCharArray()){
            if(c== '('){
                stack.push(c);
            }else if(c == ')' && !stack.isEmpty() && stack.peek() == '('){
                stack.pop();
            }

        }
        return stack.isEmpty() ;

    }

    public static List<List<String>> groupAnagram(String[] strs){
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> str =new HashMap<>();
        for(String sh:strs){
            String hashed = sortString(sh);
            if(!str.containsKey(hashed)){
                str.put(sh,new ArrayList<>());
            }
            str.get(hashed).add(sh);
        }

        res.addAll(str.values());
        return res ;

    }
    public static String sortString(String s){
        Arrays.sort(s.toCharArray());
        return s;
    }


}
