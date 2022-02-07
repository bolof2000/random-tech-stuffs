import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leetcode75 {

    public static void main(String[] args){
        int[] nums = new int[]{2,7,11,15};
       // System.out.println(Arrays.toString(twoSum(nums,9)));

        int[] prices = new int[]{7,1,5,3,6,4};

      //  System.out.println(bestTimeToBuyAndSellStock(prices));

        int[] nums2 = new int[]{1,2,3,4};
        //System.out.println(Arrays.toString(productArrayExceptSelf(nums2)));

        int[] nums3 = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxiumSubArray(nums3));


    }

    public static int[] twoSum(int[] nums,int target){
        int[] results = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int num = nums[i];
            int temp = target-num;
            if(map.containsKey(temp)){
                results[0] = i;
                results[1] = map.get(temp);
            }
            map.put(num,i);
        }
        return results;
    }

    public static int bestTimeToBuyAndSellStock(int[] prices){
        int max = Integer.MIN_VALUE;
        int minPrice = Integer.MAX_VALUE;

        for(int price:prices){
            minPrice = Math.min(minPrice,price);
            int currentProfit = price-minPrice;
            max = Math.max(max,currentProfit);
        }

        return max;
    }

    public static int[] productArrayExceptSelf(int[] nums){
        int[] results = new int[nums.length];
        Arrays.fill(results,1);
        int mul = 1;
        for(int i=0;i<nums.length;i++){
            results[i] = results[i]*mul;
            mul = nums[i]*mul;
        }
        mul = 1;
        for(int i=nums.length-1;i>=0;i--){
            results[i] = results[i]*mul;
            mul = nums[i]*mul;
        }
        return results;
    }

    public static int maxiumSubArray(int[] nums){
        int max = Integer.MIN_VALUE;
        int currentSum = Integer.MIN_VALUE;
        for(int i=1;i<nums.length;i++){
             currentSum = Math.max(currentSum+nums[i],nums[i]);
            max = Math.max(max,currentSum);
        }
        return max;
    }

    public static void moveZeros(int[] nums){
        int index =0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        for(int i=index;i<nums.length;i++){
            nums[i] = 0;
        }
    }



}
