package com.hwx.daydayup;

import java.util.Arrays;
import java.util.List;

/**
 * created by houwanxing1 on 2019/2/17
 */
public class DP {

    /**
     * 只能做一笔交易
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices){
        int maxPro = 0;
        int minPrice = Integer.MIN_VALUE;

        for(int i = 1;i < prices.length;i++){
            if(prices[i] < minPrice){
                minPrice = prices[i];
            }

            if(maxPro < prices[i] - minPrice){
                maxPro = prices[i] - minPrice;
            }
        }

        return maxPro;
    }

    /**
     * 不限制交易次数[贪心，只要有收益就交易]
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices){
        int max = 0;
        for(int i = 1;i < prices.length;i++){
            if(prices[i] > prices[i - 1]){
                max += prices[i] - prices[i - 1];
            }
        }

        return max;
    }

    /**
     * leetcode 322 零钱兑换
     * https://leetcode-cn.com/problems/coin-change/submissions/
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        //包含0-amount金额的数组
        int[] res = new int[amount + 1];
        Arrays.fill(res, amount + 1);
        res[0] = 0;

        for(int i = 1;i <= amount;i++){
            for(int coin : coins){
                if(coin <= i){
                    res[i] = Math.min(res[i], res[i - coin] + 1);
                }
            }
        }

        return res[amount] > amount ? -1 : res[amount];
    }

    /**
     * leetcode120 三角形最小路径和
     * https://leetcode-cn.com/problems/triangle/
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        //比三角形数组多一层全0的值
        int[][] dp = new int[triangle.size() + 1][triangle.size() + 1];

        for (int i = triangle.size() - 1;i >= 0;i--) {
            List<Integer> curLevel = triangle.get(i);
            for(int j = 0;j < curLevel.size();j++){
                dp[i][j] = curLevel.get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }

        return dp[0][0];
    }

    /**
     * leetcode152 乘积最大子序列
     * https://leetcode-cn.com/problems/maximum-product-subarray/
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        if (nums.length == 1){
            return nums[0];
        }

        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];
        int maxVal = nums[0];
        for (int i = 1;i < nums.length;i++) {
            max[i] = Math.max(Math.max(max[i - 1] * nums[i], min[i - 1] * nums[i]), nums[i]);
            min[i] = Math.min(Math.min(max[i - 1] * nums[i], min[i - 1] * nums[i]), nums[i]);
            maxVal = Math.max(Math.max(max[i], min[i]), maxVal);
        }

        return maxVal;
    }

    /**
     * leetcode300 最长上升子序列
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        int[] dp = new int[nums.length + 1];
        for (int i = 0;i < nums.length;i++) {
            dp[i] = 1;
        }
        int max = 1;
        for (int i = 1;i < nums.length;i++){
            for (int j = 0;j < i;j++){
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /**
     * leetcode279 完全平方数
     * https://leetcode-cn.com/problems/perfect-squares/
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1;i <= n;i++){
            int j = 1;
            while(j * j <= i){
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
                j++;
            }
        }

        return dp[n];
    }

    /**
     * leetcode5 最长回文子串
     * https://leetcode-cn.com/problems/longest-palindromic-substring/
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 2){
            return s;
        }

        int len = s.length();

        String res = "";
        int max = 0;
        //创建一个行列均为字符串长度的二维数组，创建时默认初始化为false
        boolean[][] dp = new boolean[len][len];
        for(int j = 0; j < len; j++){
            for(int i = 0; i <= j; i++){//这里只考虑了i<=j的情况，因为i>j时均为false
            //当i==j,j-i==1,j-i==2时，只要满足s.charAt(i) == s.charAt(j)就是回文字符串
            //如果不是这样，还要判断当前回文字符串的子串是不是回文字符串，即dp[i + 1][j - 1])，这就是动态规划思想
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                if(dp[i][j]){//如果是回文字符串
                    if(j - i + 1 > max){//并且比之前的回文字符串要长，更新字符串长度，记录字符串
                        max = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }

}
