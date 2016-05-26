package com.example.sky.Utils;

import android.util.Log;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/26 15:16
 * 修改人：meigong
 * 修改时间：2016/5/26 15:16
 * 修改备注：
 */
public class VIPCoast {

    // 会员类型
    final static int VIP = 1;
    final static int ZunXiang_GuaHaoXin = 2;
    final static int ZunXiang_KuaiDi = 3;


    /***
     * 判断调用那个方法
     *
     * @param continuteMember
     *            是否续会
     * @param years
     *            年限
     * @param selectType
     *            会员类型
     * @param M
     *            入会续会年数
     * @param N
     *            会员资料选择件数
     * @return
     */
    public static int FilterAndLaunch(boolean continuteMember, int years, int selectType, int M, int N) {

//        Log.i(TAG, "--入会续会标记-- = " + continuteMember);
//        Log.i(TAG, "--入会续会年限-- = " + years);
//        Log.i(TAG, "--会员类型-- = " + selectType);
//        Log.i(TAG, "--入会续会年数-- = " + M);
//        Log.i(TAG, "--会员资料选择件数-- = " + N);

        int cost = 0;
        boolean select_one_year = true;

        switch (selectType) {
            case VIP:

                if (years == 1) {
                    select_one_year = true;
                }
                else if (years > 1) {
                    select_one_year = false;
                }

                if (continuteMember) {
                    cost = cost_continuetobeVip(select_one_year, M, N);
                }
                else {
                    cost = cost_applytobeVIP(select_one_year, M, N);
                }

                break;
            case ZunXiang_GuaHaoXin:
                if (years == 1) {
                    select_one_year = true;
                }
                else if (years > 1) {
                    select_one_year = false;
                }

                if (continuteMember) {
                    cost = cost_continuetobePrivilege_tag_guahaoxing(select_one_year, M, N);
                }
                else {
                    cost = cost_applytobePrivilege_tag_guahaoxing(select_one_year, M, N);
                }

                break;
            case ZunXiang_KuaiDi:

                if (years == 1) {
                    select_one_year = true;
                }
                else if (years > 1) {
                    select_one_year = false;
                }

                if (continuteMember) {
                    cost = cost_continuetobePrivilege_tag_kuaidi(select_one_year, M, N);
                }
                else {
                    cost = cost_applytobePrivilege_tag_kuaidi(select_one_year, M, N);
                }

                break;

        }
        return cost;

    }

    /***
     * 申请“入会”的“VIP会员”： (1)“入会年限”选1年的，按100+60*（N-1）计算（N为会员资料选择件数）。
     * (2)“入会年限”不是选1年的，按100+90*（M-1）+60*（N-M）计算（N为会员资料选择件数，M为所选择的入会年数）
     *
     * @param select_one_year
     *            表示入会年限，true表1年，false表不是一年
     * @param M
     *            M为所选择的入会年数
     * @param N
     *            N为会员资料选择件数
     * @return 费用
     */
    public static int cost_applytobeVIP(boolean select_one_year, int M, int N) {

        if (select_one_year) {
            return (100 + 60 * (N - 1));
        }
        else {
            return (100 + 90 * (M - 1) + 60 * (N - M));
        }
    }

    /***
     * 申请“入会”的“尊享会员、挂号信”： (1)“入会年限”选1年的，按112+60*（N-1）计算（N为会员资料选择件数）。
     * (2)“入会年限”不是选1年的，按112+102*（M-1）+60*（N-M）计算（N为会员资料选择件数，M为所选择的入会年数）
     *
     * @param select_one_year
     *            表示入会年限，true表1年，false表不是一年
     * @param M
     *            M为所选择的入会年数
     * @param N
     *            N为会员资料选择件数
     * @return 费用
     */

    public static int cost_applytobePrivilege_tag_guahaoxing(boolean select_one_year, int M, int N) {

        if (select_one_year) {

            return (112 + 60 * (N - 1));
        }
        else {
            return (112 + 102 * (M - 1) + 60 * (N - M));
        }
    }

    /***
     * 申请“入会”的“尊享会员、快递”： (1)“入会年限”选1年的，按150+60*（N-1）计算（N为会员资料选择件数）。
     * (2)“入会年限”不是选1年的，按150+140*（M-1）+60*（N-M）计算（N为会员资料选择件数，M为所选择的入会年数）
     *
     * @param select_one_year
     *            表示入会年限，true表1年，false表不是一年
     * @param M
     *            M为所选择的入会年数
     * @param N
     *            N为会员资料选择件数
     * @return 费用
     */
    public static int cost_applytobePrivilege_tag_kuaidi(boolean select_one_year, int M, int N) {

        if (select_one_year) {
            return (150 + 60 * (N - 1));
        }
        else {
            return (150 + 140 * (M - 1) + 60 * (N - M));
        }
    }

    /***
     * 申请“续会”的“VIP会员”： (1)“续会年限”选1年的，按90+60*（N-1）计算（N为会员资料选择件数）。
     * (2)“入会年限”不是选1年的，按90M+60*（N-M）计算（N为会员资料选择件数，M为所选择的入会年数）
     *
     * @param select_one_year
     *            表示入会年限，true表1年，false表不是一年
     * @param M
     *            M为所选择的入会年数
     * @param N
     *            N为会员资料选择件数
     * @return 费用
     */

    public static int cost_continuetobeVip(boolean select_one_year, int M, int N) {

        if (select_one_year) {
            return (90 + 60 * (N - 1));
        }
        else {
            return (90 * M + 60 * (N - M));
        }
    }

    /***
     * 申请“续会”的“尊享会员、挂号信” ： (1)“续会年限”选1年的，按102+60*（N-1）计算（N为会员资料选择件数）。
     * (2)“入会年限”不是选1年的，按102M+60*（N-M）计算（N为会员资料选择件数，M为所选择的入会年数）
     *
     * @param select_one_year
     *            表示入会年限，true表1年，false表不是一年
     * @param M
     *            M为所选择的入会年数
     * @param N
     *            N为会员资料选择件数
     * @return 费用
     */
    public static int cost_continuetobePrivilege_tag_guahaoxing(boolean select_one_year, int M, int N) {

        if (select_one_year) {
            return (102 + 60 * (N - 1));
        }
        else {
            return (102 * M + 60 * (N - M));
        }
    }

    /***
     * 申请“续会”的“尊享会员、快递” ： (1)“续会年限”选1年的，按140+60*（N-1）计算（N为会员资料选择件数）。
     * (2)“入会年限”不是选1年的，按140M+60*（N-M）计算（N为会员资料选择件数，M为所选择的入会年数）
     *
     * @param select_one_year
     *            表示入会年限，true表1年，false表不是一年
     * @param M
     *            M为所选择的入会年数
     * @param N
     *            N为会员资料选择件数
     * @return 费用
     */
    public static int cost_continuetobePrivilege_tag_kuaidi(boolean select_one_year, int M, int N) {

        if (select_one_year) {
            return (140 + 60 * (N - 1));
        }
        else {
            return (140 * M + 60 * (N - M));
        }
    }

}
