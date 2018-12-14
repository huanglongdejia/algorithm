package com.hl.string;

import java.util.*;

/**
 * @author huanglong
 * @Description: 最大子序列
 * @date 2018/12/1310:37
 */
public class MaxSequence {
    /* 比如{5,-3,4,2}的最大子序列就是 {5,-3,4,2}，它的和是8,达到最大；而 {5,-6,4,2}的最大子序列是{4,2}，它的和是6 */
    /* ，只要前i项的和还没有小于0那么子序列就一直向后扩展，否则丢弃之前的子序列开始新的子序列 */
    public int[] maxSequence(int[] arr){
        int begin = 0;
        int end = 0;
        int sum = 0;
        int max = arr[0];
        int newbegin=0; //记录下新的开始下标
        for(int i=0;i<arr.length;i++){
            sum += arr[i];
            if(sum>max){
                max = sum;
                begin=newbegin;
                end=i;
            }
            /* 和小于0,丢弃之前的子序列开始新的子序列 */
            if(sum<0){
                newbegin = i+1; // 新的开始下标
                sum = 0;
            }
        }
        int[] des = new int[(end-begin+1)];
        System.arraycopy(arr,begin,des,0,(end-begin+1));
        return des;
    }

    /* 最长连续相同的子串 */
    public static String getMaxSiminarSequence(String src){
        int begin = 0;
        int end = 0;
        int newbegin = 0; //新的起点
        int count = 0; //记录没一段的长度
        int max = 0; //记录最大的长度
        for(int i=0;i<src.length()-1;i++){
            char head = src.charAt(i);
            char next = src.charAt(i+1);
            count++;
            if(count>max){
                max = count;
                begin = newbegin;
                end = i;
            }
            if(head!=next){
                newbegin = i+1;
                count=0;
            }
        }
        return src.substring(begin,end+1);

    }

    /* 最长连续的子串 */
    public static String getMaxContinueSequence(String src){
        int begin = 0;
        int end = 0;
        int max = 0;
        int count = 0;
        int newBegin = 0;
        for(int i=0;i<src.length()-1;i++){
            char head = src.charAt(i);
            char next = src.charAt(i+1);
            count++;
            if(count>max){
                max = count;
                begin = newBegin;
                end = i;
            }
            if((next-head)!=1){
                count=0;
                newBegin=i+1;
            }
        }
        return src.substring(begin,end+1);
    }

    /* 最长递增子序列(非连续) */
    /* f(i)表示L中以ai为末元素的最长递增子序列的长度 */
    /* 求出每一个节点的  最长递增子序列 */
    /* 使用之前的结果推导后面的结果 */
    public static List<Integer>[] getMaxIncSequence(int[] arr){
        int[] counts = new int[arr.length];
        List<Integer>[] data = new List[arr.length];
        //Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            counts[i] = 1; // 至少长度为1
            //数据
            List<Integer> li = new ArrayList<>();
            li.add(arr[i]);
            data[i] = li;
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j]&&counts[i]-1<counts[j]){
                    counts[i]+=1;
                    //数据
                    List<Integer> pre = new ArrayList<>(data[j]);
                    pre.addAll(li);
                    data[i] = pre;
                }
            }
        }
        return data;
    }

    /* 考虑最长公共子序列问题如何分解成子问题，设A=“a0，a1，…，am-1”，B=“b0，b1，…，bn-1”，并Z=“z0，z1，…，zk-1”为它们的最长公共子序列。不难证明有以下性质：
    （1） 如果am-1=bn-1，则zk-1=am-1=bn-1，且“z0，z1，…，zk-2”是“a0，a1，…，am-2”和“b0，b1，…，bn-2”的一个最长公共子序列；

    （2） 如果am-1!=bn-1，则若zk-1!=am-1，蕴涵“z0，z1，…，zk-1”是“a0，a1，…，am-2”和“b0，b1，…，bn-1”的一个最长公共子序列；

    （3） 如果am-1!=bn-1，则若zk-1!=bn-1，蕴涵“z0，z1，…，zk-1”是“a0，a1，…，am-1”和“b0，b1，…，bn-2”的一个最长公共子序列。
     */
    /* 思想核心从后开始递归 */
    public static String commonSequence(String str1,String str2){
        int str1Len = str1.length();
        int str2Len = str2.length();
        if(str1Len==0||str2Len==0){
            return "";
        }
        char lastChar1 = str1.charAt(str1Len-1);
        char lastChar2 = str2.charAt(str2Len-1);
        if(lastChar1==lastChar2){
            return commonSequence(str1.substring(0,str1Len-1),str2.substring(0,str2Len-1))+lastChar1;
        }else {
            String left = commonSequence(str1.substring(0,str1Len-1),str2);
            String right =commonSequence(str1,str2.substring(0,str2Len-1));
            return left.length()>right.length()?left:right;
        }
    }
    /* 最长公共子串 */
    /* 如果xi == yj， 则 c[i][j] = c[i-1][j-1]+1
       如果xi ! = yj,  那么c[i][j] = 0
       最后求Longest Common Substring的长度等于
       max{  c[i][j],  1<=i<=n， 1<=j<=m}
    */
    /* 使用之前的结果推导后面的结果 */
    public static String commonSubString(String str1,String str2){
        int strLen1 = str1.length();
        int strLen2 = str2.length();
        int[][] arr = new int[strLen1][strLen2];
        for(int i=0;i<strLen1;i++){
            char chari = str1.charAt(i);
            for(int j=0;j<strLen2;j++){
                char charj = str2.charAt(j);
                if(chari==charj){
                    if(i==0||j==0){
                        arr[i][j] = 1;
                    }else {
                        arr[i][j] = arr[i-1][j-1]+1;
                    }
                }else {
                    arr[i][j] = 0;
                }
            }
        }
        int maxIndexi = 0;
        int max = 0;
        for(int i=0;i<strLen1;i++){
            for(int j=0;j<strLen2;j++){
                if(arr[i][j]>max){
                    maxIndexi =i;
                    max=arr[i][j];
                }
            }
        }
        return str1.substring(maxIndexi-max+1,maxIndexi+1);
    }

    /* 比较两个字符串A和B，确定A中是否包含B中所有的字符。字符串A和B中的字符都是大写字母
    样例
    给出 A = "ABCD" B = "ACD"，返回 true
    给出 A = "ABCD" B = "AABC"， 返回 false
     */
    public static boolean isContaintSequence(String strA,String strB){
        int lenB = strB.length();
        int start = -1;
        for(int i=0;i<lenB;i++){
            start = findChar(strA,start+1,strB.charAt(i));
            if(start==-1){
                return false;
            }
        }
        return true;
    }
    public static int findChar(String str,int start,char c){
        for(int i=start;i<str.length();i++){
            if(str.charAt(i)==c){
                return i;
            }
        }
        return -1;
    }




    public static void main(String[] args){
        MaxSequence maxSequence = new MaxSequence();
        int[] arr = {5,-6,4,2};
        int[] res = maxSequence.maxSequence(arr);
        for(int j:res){
            System.out.println(j);
        }

        System.out.println(getMaxSiminarSequence("hjdvvvddcd"));

        System.out.println(getMaxContinueSequence("hjdvvcddcd"));

        int[] src = {5,-6,3,4,2};
        List<Integer>[] counts = getMaxIncSequence(src);
        for(List<Integer> j:counts){
            for(Integer in:j){
                System.out.print(in+",");
            }
            System.out.println();
        }

        System.out.println(commonSequence("ABCBDAB","BDCB"));

        System.out.println(commonSubString("ABDCBDAB","BDCB"));

        System.out.println(isContaintSequence("ABCD","ACD"));
        System.out.println(isContaintSequence("ABCD","AABC"));

    }
}
