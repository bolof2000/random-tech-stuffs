package leetcode_2022;

import java.util.ArrayList;
import java.util.List;

public class DynamicProgrammingSolutions {

    public static int climbStairs(int n){

        if(n==1){
            return 1;
        }
       int[] ways = new int[n+1];
        ways[1] = 1;
        ways[2] = 2;
        for(int i=3;i<n+1;i++){
            ways[i] = ways[i-1]+ways[i-2];
        }

        return ways[n];
    }

    //o(n) O(1)-space since no array
    public static int climbStairs2(int n){
        if(n==1){
            return 1;
        }

        int first = 1;
        int second = 2;

        for(int i=3;i<=n;i++){
            int third = first+second;
            first = second;
            second = third;
        }

        return second;
    }

    public static int maxSubSArray(int[] nums){
        int maxSum =0;
        int currentSum = nums[0];
        for(int i=1;i<nums.length;i++) {
            currentSum = Math.max(currentSum + nums[i], nums[i]);
            maxSum = Math.max(currentSum, maxSum);
        }
        return maxSum;
    }
    public static int fib(int n){
        if(n <=1){
            return n;
        }
        else{
            return fib(n-1)+fib(n-2);
        }
    }

    public static int fib2(int n){

        return 1;
    }

    public static boolean isSubsequence(String s,String t){
       // Input: s = "abc", t = "ahbgdc"

        if(s.length() == 0) return true;

        int pos = 0;
        for(int i=0;i<t.length();i++){

            if(s.charAt(pos) == t.charAt(i)){
                pos +=1;

                if(pos == s.length()) return true;
            }
        }
        return false;
    }

    public static int maxProfit(int[] prices){
        int minPrice = Integer.MAX_VALUE;
        int profit =Integer.MIN_VALUE;

        for(int price:prices){
            minPrice = Math.min(minPrice,price);
         int currentProfit = price-minPrice;
         profit = Math.max(profit,currentProfit);
        }
        return profit;
    }

    public static int houseRobber(int[] nums){
       if(nums.length ==0){
           return 0;
        }
       if(nums.length==1){
           return nums[0];
       }
       if(nums.length ==2){
           return Math.max(nums[0],nums[1]);
       }
       List<Integer> dp = new ArrayList<>();
       dp.add(nums[0]);
       int num = Math.max(nums[0],nums[1]);
       dp.add(num);

       for(int i=2;i<nums.length;i++){
           dp.add(Math.max(nums[i]+ dp.get(i - 2),dp.get(i-1)));
       }

       return dp.get(nums.length-1);
    }

    public static boolean jumpGame(int[] nums){

        /*
        Input: nums = [2,3,1,1,4]
        Output: true
        Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
         */


        return false;
    }


}
