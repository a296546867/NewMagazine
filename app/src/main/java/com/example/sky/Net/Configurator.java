package com.example.sky.Net;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/18 9:06
 * 修改人：meigong
 * 修改时间：2016/5/18 9:06
 * 修改备注：
 */
public class Configurator {

    // 服务器地址
    public static String URL = "http://api.51camel.com";
    // AppKey
    public static final String APP_KEY = "10000003";
    //SecretKEY
    public static final String SECRET_KEY = "895C3B0B-86F9-499D-A7C0-FD3BB6F12313";
    //AesKey
    public static final String AES_KEY = "1234567890123456";


    /***************************************** 【接口】 *************************************************/
    // 杂志封面列表对应的Json数据保存目录
    public static final String Jpath = "json";
    // 注册
    public static final String Register = URL + "/jour/register.json";
    // 登录
    public static final String Login = URL + "/oauth/login.json";
    //密码修改
    public static final String ChangePassWord = URL + "/jour/ChangePassWord.json";
    //是否有新杂志
    public static final String UPDATE_LIST = URL + "/jour/isget.json?dt=";
    // 根据年份访问杂志
    public static final String MAGZINE_BY_YEAR = URL + "/jour/year_list.json?year=";
    // 访问杂志封面列表
    public static final String USERRENZHENG = URL + "/jour/yg_user.json";
    // 访问单个杂志基本信息
    public static final String SingleMagazineBaseInfo = URL + "/jour/show.json?jid=";
    // 访问单篇文章信息
    public static final String SingleArticleInfo = URL + "/jour/show_article.json?aid=";

    public static final String DOWN_ONE_JOURNAL = URL + "/jour/down.json?jid=";
    // 下载单个杂志所有信息
    public static final String DownloadSingleMagazineAllInfo = URL + "/jour/down.json?jid=";
    // 获取短信验证码
    public static final String getAuthCode = URL + "/oauth/send.json";
    // 添加收藏
    public static final String ADDCOLLECTION = URL + "/collect/add.json";
    // 查看收藏
    public static final String CollectionShow = URL + "/jour/collect.json";
    // 删除收藏
    public static final String DeleteCollection = URL + "/collect/delete.json";
    // 查看评论
    public static final String showCommentList = URL + "/comment/show.json";
    // 添加评论
    public static final String creatComment = URL + "/comment/create.json";
    // 搜索文章
    public static final String SEARCH_ARTICLE = URL + "/jour/search.json?key=";
    // 产品
    public static final String PRODUCT = URL + "/jour/show_product.json?productid=";
    // 提交申请表，普通会员，vip会员都是用该接口
    public static final String MEMBERAPPLYFORM = URL + "/jour/apply.json";
    // 分页获取我的申请信息列表
    public static final String GETAPPLYFORMINPAGE = URL + "/jour/my_apply.json?";
    // 删除申请信息
    public static final String DELETEAPPLYINFO = URL + "/jour/delete_applyform.json";
    // 编辑申请信息
    public static final String EDITAPPLYINFO = URL + "/jour/update_applyform.json";
    // 檢查版本更新
    public static final String VERSIONCODE = URL + "/jour/version.json?type=";
    // 用户认证信息
    public static final String APPROVEINFO = URL + "/jour/yg_user.json?token=";
    // 编辑用户认证信息
    public static final String USERAPPROVEINFO = URL + "/jour/update_ygmember.json?token=";
    //判断搜索到的文章是否是最新两期杂志中的文章
    public static final String ISINTHETWONEWMAGAZINE = URL + "/jour/CheckIsMostNew.json?aid=";
}
