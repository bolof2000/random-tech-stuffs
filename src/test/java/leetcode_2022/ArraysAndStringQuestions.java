package leetcode_2022;

import java.util.*;

public class ArraysAndStringQuestions {

 public static int longestSubStringWithoutRepeatingCharacter(String s){
  Map<Character,Integer> map = new HashMap<>();
  int maxLen = 0;
  int window_start = 0;
  for(int i=0;i<s.length();i++){
   if(map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) >= window_start){
   window_start = map.get(s.charAt(i))+1;
    }
   map.put(s.charAt(i),i);

   maxLen = Math.max(maxLen,i-window_start+1);
  }
  return maxLen;
 }
 public static List<List<String>> groupAnagram(String[] strs) {

  Map<String, List<String>> map = new HashMap<>();
  List<List<String>> results = new ArrayList<>();
  for(String s:strs){

   String key = sortString(s);
   if(!map.containsKey(key)){
    map.put(key,new ArrayList<>());
   }else{
    map.get(key).add(s);
   }
  }

   results.addAll(map.values());
  return results;

 }

 public static String sortString(String s){
       Arrays.sort(s.toCharArray());
       return s;
 }

 public static int searchInRotatedArray(int[] nums,int target){
  int left = 0;
  int right = nums.length-1;

  while(left <= right){
   int mid = (left+right)/2;

   if(nums[mid] == target){
    return mid;
   }else{

    if(nums[mid] >= nums[left]){
     if(target <=nums[mid] && target >=nums[left]){
      right = mid-1;
     }else{
      left = mid+1;
     }
    }else{
     if(nums[mid] <= nums[right]){
      if(target >=nums[mid] && target <= nums[right]){
       left = mid+1;
      }else{
       right = mid-1;
      }
     }
    }
   }
  }

  return -1;

 }

 public static int[] productArraysExceptSelf(int[] nums){
  int[] results = new int[nums.length];
  Arrays.fill(results,1);

  int mul = 1;

  for(int i=0;i<nums.length;i++){
   results[i] = results[i]*mul;
   mul = mul*nums[i];
  }
  mul = 1;
  for(int i=nums.length-1;i>=0;i--){
   results[i] = results[i]*mul;
   mul = mul*nums[i];
  }
  return results;
 }

 public static void main(String[] args){
  System.out.println(longestSubStringWithoutRepeatingCharacter("bbbbb"));
 }
}

 class LRUCacheSolution {

 public Map<Integer,Integer> map;
 Deque<Integer> deque;
 private final int capacity;

 public LRUCacheSolution(int capacity){
  this.capacity = capacity;

 }
 public int getValue(int key){
  if(map.containsKey(key)){

   int value = map.get(key);

   this.deque.addFirst(key);
   return value;
  }else{
   return -1;
  }
 }
 public void put(int key, int val){

  if(!map.containsKey(key)){
   if(this.capacity == map.size()){
    int oldest = this.deque.removeLast();
    map.remove(oldest);
    this.deque.addFirst(key);
    this.map.put(key,val);
   }{
    this.deque.addFirst(key);
    this.map.put(key,val);
   }
  }
 }
}
