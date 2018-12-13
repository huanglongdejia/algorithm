package com.hl.string;

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
    public String getMaxSiminarSequence(String src){

        int begin = 0;
        int end = 0;
        int newbegin = 0;
        int count = 0;
        int max = 0; //记录最大的长度
        for(int i=0;i<src.length();i++){
            count++;
            char head = src.charAt(i);
            char next = src.charAt(i+1);
            if(head!=next){
                newbegin = i+1;

            }else {

            }
            /*if(){

            }*/
        }
        return "";

    }


    /* 最长连续的子串 */

    public static void main(String[] args){
        MaxSequence maxSequence = new MaxSequence();
        int[] arr = {5,-6,4,2};
        int[] res = maxSequence.maxSequence(arr);
        for(int j:res){
            System.out.println(j);
        }
    }
}
