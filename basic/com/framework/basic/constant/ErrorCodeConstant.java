package com.framework.basic.constant;

public interface ErrorCodeConstant 
{
   //操作成功
   String OPERATOR_SUCCESS = "0";
   
   //数据库异常
   String DB_ERROR = "-1000";
   
   //系统异常
   String SYSTEM_ERROR = "-1001";
   
   //验证码错码
   String VALIDATECODE_ERROR = "1001";
   
   //用户名为空
   String USERNAME_EMPTY_ERROR = "1002";
   
   //密码为空
   String PASSWORD_EMPTY_ERROR = "1003";
   
   //登陆失败
   String LOGIN_ERROR =  "1004";
   
   //没有登陆错码
   String NO_LOGIN_ERROR =  "1005";
   
   //非法访问
   String ILLEGAL_ACCESS = "1006";
   
   //登陆用户已经存在
   String USER_EXIST_ERROR = "1008";
   
   //登陆用户已经删除
   String USER_DELETE_ERROR = "1009";
   
   //上传文件失败
   String UPLOAD_FILE_ERROR = "1010";
   
   //用户更新密码失败
   String USER_UPDATE_PASSWORD_ERROR = "1021";

   //用户旧密码错误
   String INIT_USER_UPDATE_PASSWORD_ERROR = "1022";
   
   //名称为空
   String NAME_EMPTY_ERROR = "1023";
   
   //名称已经存在
   String NAME_EXIST_ERROR = "1024";
   
   //产品介绍内容为空
   String INTRODUCTION_EMPTY_ERROR = "1025";
   
   //产品类型为空
   String STYLE_EMPTY_ERROR = "1026";
   
   //图片为空
   String IMAGE_EMPTY_ERROR = "1027";
   
   //标识为空
   String ID_EMPTY_ERROR = "1028";
   
   //数据不存在
   String ID_NO_EXIST_ERROR = "1029";
   
   //产品操作方式为空
   String OPERATOR_EMPTY_ERROR = "1030"; 
   
   //产品游戏类型为空
   String GAME_STYLE_EMPTY_ERROR = "1031";
   
   //支持的平台为空
   String PLATFORM_EMPTY_ERROR = "1032";
   
   //媒体类型为空
   String MEDIA_STYLE_EMPTY_ERROR = "1033";
   
   //媒体内容为空
   String MEDIA_CONTENT_EMPTY_ERROR = "1034";
   
   //产品ID为空
   String PRODUCT_ID_EMPTY_ERROR = "1035";
   
   //产品评论内容为空
   String COMMENT_EMPTY_ERROR = "1036";
   
   //产品评论：人物email为空
   String COMMENT_EMAIL_EMPTY_ERROR = "1037";
   
   //产品评论：人物名称为空
   String COMMENT_NAME_EMPTY_ERROR = "1038";
   
   //产品评论：人物图片为空
   String COMMENT_IMAGE_EMPTY_ERROR = "1039";
   
   //产品下载地址为空
   String PRODUCT_DOWNLOAD_URL_EMPTY_ERROR = "1040";
   
   //下载平台ID为空
   String DOWNLOAD_PLATFROM_ID_EMPTY_ERROR = "1041";
   
   //产品推荐ID为空
   String PRODUCT_RECOMMENT_ID_EMPTY_ERROR = "1042";
   
   //产品排行ID为空
   String PRODUCT_RANKING_ID_EMPTY_ERROR = "1043";
   
   //用户留言内容为空
   String USER_MESSAGE_CONTENT_EMPTY_ERROR = "1044";
   
   //用户留言:email为空
   String USER_MESSAGE_EMAIL_EMPTY_ERROR = "1045";
   
   //用户留言:公司为空
   String USER_MESSAGE_COMPANY_EMPTY_ERROR = "1046";
   
   //用户留言:电话号码为空
   String USER_MESSAGE_TELLPHONE_EMPTY_ERROR = "1047";
   
   //公司事件:标题为空
   String COMPANY_THING_TITLE_EMPTY_ERROR = "1048";
   
   //公司事件:链接地址为空
   String COMPANY_THING_URL_EMPTY_ERROR = "1049";
   
   //公司事件:信息为空
   String COMPANY_THING_INFO_EMPTY_ERROR = "1050";
}
