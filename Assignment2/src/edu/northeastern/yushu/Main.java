package edu.northeastern.yushu;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //1. Search Insert Position
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        System.out.println("1. Search Insert Position : " + searchInsert(nums, 5));
        //2. Single Element in a Sorted Array
        int[] num = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println("2. Single Element in a Sorted Array : " + singleNonDuplicate(num));
        //3. Find Minimum in Rotated Sorted Array
        int[] nu = {3, 4, 5, 1, 2};
        System.out.println("3. Find Minimum in Rotated Sorted Array : " + findMin(nu));
        //4. Meeting Rooms II
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("4. Meeting Rooms II : " + minMeetingRooms(intervals));
        //5. Top K Frequent Elements
        int[] kfe = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println("5. Top K Frequent Elements : " + Arrays.toString(topKFrequent(kfe, k)));
        //6. 3Sum Closest
        int[] sum = {-1, 2, 1, -4};
        int tar = 1;
        System.out.println("6. 3Sum Closest : " + threeSumClosest(sum, tar));
        //7. Insert Interval
        int[][] interval = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        System.out.println("7. Insert Interval : " + Arrays.deepToString(insert(interval, newInterval)));
        //8. Non-overlapping Intervals
        int[][] interva = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println("8. Non-overlapping Intervals : " + eraseOverlapIntervals(interva));
        //9. Interval List Intersections
        int[][] firstList = {{0, 2}, {5, 10}, {13, 23}, {24, 25}}, secondList = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        System.out.println("9. Interval List Intersections : " + Arrays.deepToString(intervalIntersection(firstList, secondList)));
        //10. 4Sum
        int[] su = {1, 0, -1, 0, -2, 2};
        int t = 0;
        System.out.println("10. 4Sum : " + fourSum(su, t));
    }

    //1. Search Insert Position
    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    //2. Single Element in a Sorted Array
    public static int singleNonDuplicate(int[] nums) {
        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    //3. Find Minimum in Rotated Sorted Array
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while ((left < right)) {

            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    //4. Meeting Rooms II
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        Arrays.sort(intervals, (m1, m2) -> m1[0] - m2[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for (int[] interval : intervals) {
            if (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) {
                minHeap.poll();
            }
            minHeap.add(interval[1]);
        }

        return minHeap.size();
    }

    //5. Top K Frequent Elements
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occ = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occ.put(num, occ.getOrDefault(num, 0) + 1);
        }
        List<int[]> values = new ArrayList<int[]>();
        for (Map.Entry<Integer, Integer> entry : occ.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        int[] ret = new int[k];
        qsort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    public static void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);
        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        Collections.swap(values, start, index);
        if (k <= index - start) {
            qsort(values, start, index - 1, ret, retIndex, k);
        } else {
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            if (k > index - start + 1) {
                qsort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }

    //6. 3Sum Closest
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int a = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - a)) {
                    a = sum;
                }
                if (sum > target) {
                    end--;
                } else if (sum < target) {
                    start++;
                } else {
                    return a;
                }
            }
        }
        return a;
    }

    //7. Insert Interval
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval.length == 0)
            return intervals;
        if (intervals.length == 0)
            return new int[][]{newInterval};
        List<int[]> res = new ArrayList<>();
        int L = newInterval[0], R = newInterval[1], len = intervals.length, j = 0;
        while (j < len && intervals[j][1] < L)
            res.add(intervals[j++]);
        while (j < len && intervals[j][0] <= R) {
            L = Math.min(L, intervals[j][0]);
            R = Math.max(R, intervals[j++][1]);
        }
        res.add(new int[]{L, R});
        while (j < len) res.add(intervals[j++]);
        return res.toArray(new int[res.size()][]);
    }

    //8. Non-overlapping Intervals
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int n = intervals.length;
        int r = intervals[0][1];
        int a = 1;
        for (int i = 1; i < n; ++i) {
            if (intervals[i][0] >= r) {
                ++a;
                r = intervals[i][1];
            }
        }
        return n - a;
    }

    //9. Interval List Intersection
    public static int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> an = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            int l = Math.max(A[i][0], B[j][0]);
            int h = Math.min(A[i][1], B[j][1]);
            if (l <= h)
                an.add(new int[]{l, h});
            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return an.toArray(new int[an.size()][]);
    }

    //10. 4Sum
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }
}
