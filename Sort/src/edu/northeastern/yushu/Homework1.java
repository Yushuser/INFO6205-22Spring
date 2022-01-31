package edu.northeastern.yushu;

import java.util.*;

public class Homework1 {
    //75
    public void sortColors(int[] nums){
        if (nums==null||nums.length==0){
            return;
        }
        int zero=-1;
        int two=nums.length;
        int i=0;
        while (i<two){
            if (nums[i]==1){
                i++;
            }else if (nums[i]==2){
                two--;
                swap(nums, i, two);
            }else {
                zero++;
                swap(nums, i, zero);
                i++;
            }
        }
    }
    private void swap(int[] nums, int a, int b){
        int temp=nums[a];
        nums[a]=nums[b];
        nums[b]=temp;
    }
    //229
    public List<Integer> majorityElement(int[] nums){
        HashMap<Integer, Integer> cnt=new HashMap<Integer, Integer>();
        for (int i=0;i<nums.length;i++){
            if (cnt.containsKey(nums[i])){
                cnt.put(nums[i], cnt.get(nums[i])+1);
            }else {
                cnt.put(nums[i], 1);
            }
        }
        List<Integer> ans=new ArrayList<>();
        for (int x:cnt.keySet()){
            if (cnt.get(x)>nums.length/3){
                ans.add(x);
            }
        }
        return ans;
    }
    //274
    public int hIndex(int[] citations){
        Arrays.sort(citations);
        int h=0, i=citations.length-1;
        while ((i>=0 && citations[i]>h)){
            h++;
            i--;
        }
        return h;
    }
    //349
    public int[] intersection(int[] nums1, int[] nums2){
        Set<Integer> set1=new HashSet<Integer>();
        Set<Integer> set2=new HashSet<Integer>();
        for (int num: nums1){
            set1.add(num);
        }
        for (int num: nums2){
            set2.add(num);
        }
        return getIntersection(set1, set2);
    }
    public int[] getIntersection(Set<Integer> set1, Set<Integer> set2){
        if (set1.size()>set2.size()){
            return getIntersection(set2, set1);
        }
        Set<Integer> intersectionSet=new HashSet<Integer>();
        for (int num: set1){
            if (set2.contains(num)){
                intersectionSet.add(num);
            }
        }
        int[] intersection=new int[intersectionSet.size()];
        int index=0;
        for (int num:intersectionSet){
            intersection[index++]=num;
        }
        return intersection;
    }
    //658
    public List<Integer> findCloseElements(int[] arr, int k, int x){
        int size =arr.length;
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
    //767
    public String reorganizeString(String s){
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
            char c=(char)('a'+i);
            while (counts[i]>0 && counts[i]<=halfLength && oddIndex<length){
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
    //791
    public String customSortString(String S, String T){
        int[] count=new int[26];
        for (char c: T.toCharArray()){
            count[c-'a']++;
        }
        StringBuilder ans =new StringBuilder();
        for (char c: S.toCharArray()){
            while (count[c - 'a']-- > 0) {
                ans.append(c);
            }
        }
            for (char c='a';c<='z';++c){
                while (count[c - 'a']-- > 0) {
                    ans.append(c);
            }
        }
        return ans.toString();
    }
    //969
    public List<Integer> pancakeSort(int[] A){
        List<Integer> ans = new ArrayList();
        int N=A.length;
        Integer[] B=new Integer[N];
        for (int i=0;i<N;++i)
            B[i]=i+1;
        Arrays.sort(B, (i, j)->A[j-1]-A[i-1]);
        for (int i: B){
            for (int f: ans)
                if (i<=f)
                    i=f+1-i;
                ans.add(i);
                ans.add(N--);
        }
        return ans;
    }
    //1636
    public int[] frequencySort(int[] nums){
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
}
