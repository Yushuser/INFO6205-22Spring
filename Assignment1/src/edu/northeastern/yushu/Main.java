package edu.northeastern.yushu;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] nums = {2, 0, 1, 1, 0, 2};
        System.out.println(Arrays.toString(sortColors(nums)));
        int[] majority = {3, 2, 3, 3, 1};
        System.out.println(majorityElement(majority));
        int[] citation={3, 0,6, 1, 5};
        System.out.println(hIndex(citation));
        int[] arr1 = {1,2,2,1,4}, arr2 = {2,4,2};
        System.out.println(Arrays.toString(intersection(arr1, arr2)));
        int[] arr={1,2,3,4,5};
        int x=3, k=4;
        System.out.println(findClosestElement(arr, x, k));
        String s="aab";
        System.out.println(reorganizeString(s));
        String order = "cba";
        String t = "abcd";
        System.out.println(customSort(order, t));
        int[] pancake = {3,2,4,1};
        System.out.println(pancakeSort(pancake));
        int[] freqNums = {1,1,2,2,2,3};
        System.out.println(Arrays.toString(frequencySort(freqNums)));
        String[] words = {"i","love","leetcode","i","love","coding"};
        int topK = 2;
        System.out.println(topKFrequent(words, topK));
    }

    //75.Sort Colors
    private static int[] sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int zero = -1;
        int two = nums.length;
        int i = 0;
        while (i < two) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                two--;
                swap(nums, i, two);
            } else {
                zero++;
                swap(nums, i, zero);
                i++;
            }
        }
        return nums;
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    //229. Majority Element II
    private static List<Integer> majorityElement(int[] majority) {
        HashMap<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int i = 0; i < majority.length; i++) {
            if (cnt.containsKey(majority[i])) {
                cnt.put(majority[i], cnt.get(majority[i]) + 1);
            } else {
                cnt.put(majority[i], 1);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int x : cnt.keySet()) {
            if (cnt.get(x) > majority.length / 3) {
                ans.add(x);
            }
        }
        return ans;
    }
    //274. H-Index
    private static int hIndex(int[] citation){
        Arrays.sort(citation);
        int h=0, i=citation.length-1;
        while (i>=0&&citation[i]>h){
            h++;
            i--;
        }
        return h;
    }
    //349. Intersection of Two Arrays
    private static int[] intersection(int[] arr1, int[] arr2){
        Set<Integer> set1=new HashSet<>();
        Set<Integer> set2=new HashSet<>();
        for (int num: arr1){
            set1.add(num);
        }
        for (int num: arr2){
            set2.add(num);
        }
        return getIntersection(set1, set2);
    }
    private static int[] getIntersection(Set<Integer> set1, Set<Integer> set2){
        if(set1.size()>set2.size()){
            return getIntersection(set2, set1);
        }
        Set<Integer> intersectionSet= new HashSet<Integer>();
        for (int num: set1){
            if(set2.contains(num)){
                intersectionSet.add(num);
            }
        }
        int[] intersection = new int[intersectionSet.size()];
        int index=0;
        for (int num: intersectionSet){
            intersection[index++]=num;
        }
        return intersection;
    }
    //658. Find K Closest Elements
    private static List<Integer> findClosestElement(int[] arr, int x, int k){
        int size=arr.length;
        int left=0;
        int right=size-1;
        int removeNums=size-k;
        while (removeNums>0){
            if (x-arr[left]<=arr[right]-x){
                right--;
            }else {
                left++;
            }
            removeNums--;
        }
        List<Integer> res=new ArrayList<>();
        for (int i=left;i<left+k;i++){
            res.add(arr[i]);
        }
        return res;
    }
    //767. Reorganize String
    private static String reorganizeString(String s){
        if (s.length()<2){
            return s;
        }
        int[] counts=new int[26];
        int maxCount=0;
        int length=s.length();
        for (int i=0;i<length;i++){
            char c=s.charAt(i);
            counts[c-'a']++;
            maxCount=Math.max(maxCount, counts[c-'a']);
        }
        if (maxCount>(length+1)/2){
            return "";
        }
        char[] reorganizeArray=new char[length];
        int evenIndex=0, oddIndex=1;
        int halfLength=length/2;
        for (int i=0;i<26;i++){
            char c=(char) ('a'+i);
            while (counts[i]>0 && counts[i]<=halfLength&&oddIndex<length){
                reorganizeArray[oddIndex]=c;
                counts[i]--;
                oddIndex+=2;
            }
            while (counts[i]>0){
                reorganizeArray[evenIndex]=c;
                counts[i]--;
                evenIndex+=2;
            }
        }
        return new String(reorganizeArray);
    }
    //791. Custom Sort String
    private static String customSort(String S, String T){
        int[] count =new int[26];
        for (char c: T.toCharArray()){
            count[c-'a']++;
        }
        StringBuilder ans=new StringBuilder();
        for (char c:S.toCharArray()){
            while (count[c-'a']-->0){
                ans.append(c);
            }
        }
        for (char c='a';c<='z';++c){
            while (count[c-'a']-->0){
                ans.append(c);
            }
        }
        return ans.toString();
    }
    //969. Pancake Sorting
    private static List<Integer> pancakeSort(int[] A){
        List<Integer> ans=new ArrayList();
        int N=A.length;
        Integer[] B=new Integer[N];
        for (int i=0;i<N;++i)
            B[i]=i+1;
        Arrays.sort(B, (i, j) -> A[j-1]-A[i-1]);

        for (int i: B){
            for (int f: ans)
                if (i<=f)
                    i=f+1-i;
                ans.add(i);
                ans.add(N--);
        }
        return ans;
    }
    //1636. Sort Array by Increasing Frequency
    private static int[] frequencySort(int[] nums){
        int[] cnts=new int[201];
        for (int n: nums){
            cnts[n+100]++;
        }
        for (int i=0;i<nums.length;i++){
            nums[i]=201*cnts[nums[i]+100]-nums[i]+100;
        }
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){
            nums[i]=100-nums[i]%201;
        }
        return nums;
    }
    //692. Top K Frequent Words
    private static List<String> topKFrequent(String[] words, int k){
        Map<String, Integer> tf=new HashMap<String, Integer>();
        for (String word: words){
            tf.put(word, tf.getOrDefault(word, 0)+1);
        }
        List<String> rec= new ArrayList<String>();
        for (Map.Entry<String, Integer> entry: tf.entrySet()){
            rec.add(entry.getKey());
        }
        Collections.sort(rec, new Comparator<String>() {
            @Override
            public int compare(String word1, String word2) {
                return tf.get(word1)==tf.get(word2)?word1.compareTo(word2):tf.get(word2)-tf.get(word1);
            }
        });
        return rec.subList(0,k);
    }
}
