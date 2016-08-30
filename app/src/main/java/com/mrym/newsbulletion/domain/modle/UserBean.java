package com.mrym.newsbulletion.domain.modle;

import java.util.List;
import java.util.Objects;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class UserBean {
   public UserBean() {
      super();
   }

   private Integer id;
   private String customerName; // 用户名-手机号
   private String passWord; // 密码
   private Objects registerTime;//注册时间
   private String messageCode;//下发短信验证码
   private String nickName; //昵称
   private String signature; //签名
   private String sex; //性别
   private String hobby; //爱好
   private String isShow; //是否可见
   private String headImg; //头像路径
   private String age; //年龄
   private String birthDate; //出生日期
   private Objects updateTime; //修改时间

   private String tag;//个性标签，逗号隔开
   private String job; //职业
   private String hometown;//家乡
   private String qqopenid;//qq互联Id
   private String qqinfo;//qq账号相关信息
   private String sinaopenid;//新浪微博登录id
   private String sinainfo;//新浪账号相关序列化值
   private String weixinopenid;//新浪微博登录id
   private String weixininfo;//新浪账号相关序列化值

   private Integer points;//金币/会员积分
   private double advanceDeposit;//账户余额
   private int joinTrueadventureState;//用户游戏状态标记
   private String deskName;//已加入店铺桌号
   private Double latitude;    //用户定位维度
   private Double longitude;    //用户定位经度

   public List<String> customerImg; //用户相册路径数组

   public int getJoinTrueadventureState() {
      return joinTrueadventureState;
   }

   public void setJoinTrueadventureState(int joinTrueadventureState) {
      this.joinTrueadventureState = joinTrueadventureState;
   }

   public String getDeskName() {
      return deskName;
   }

   public void setDeskName(String deskName) {
      this.deskName = deskName;
   }

   public Double getLatitude() {
      return latitude;
   }

   public void setLatitude(Double latitude) {
      this.latitude = latitude;
   }

   public Double getLongitude() {
      return longitude;
   }

   public void setLongitude(Double longitude) {
      this.longitude = longitude;
   }

   public String getWeixinopenid() {
      return weixinopenid;
   }

   public void setWeixinopenid(String weixinopenid) {
      this.weixinopenid = weixinopenid;
   }

   public String getWeixininfo() {
      return weixininfo;
   }

   public void setWeixininfo(String weixininfo) {
      this.weixininfo = weixininfo;
   }

   public Objects getUpdateTime() {
      return updateTime;
   }

   public void setUpdateTime(Objects updateTime) {
      this.updateTime = updateTime;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getCustomerName() {
      return customerName;
   }

   public void setCustomerName(String customerName) {
      this.customerName = customerName;
   }

   public String getPassWord() {
      return passWord;
   }

   public void setPassWord(String passWord) {
      this.passWord = passWord;
   }

   public Objects getRegisterTime() {
      return registerTime;
   }

   public void setRegisterTime(Objects registerTime) {
      this.registerTime = registerTime;
   }

   public String getMessageCode() {
      return messageCode;
   }

   public void setMessageCode(String messageCode) {
      this.messageCode = messageCode;
   }

   public String getNickName() {
      return nickName;
   }

   public void setNickName(String nickName) {
      this.nickName = nickName;
   }

   public String getSignature() {
      return signature;
   }

   public void setSignature(String signature) {
      this.signature = signature;
   }

   public String getSex() {
      return sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getHobby() {
      return hobby;
   }

   public void setHobby(String hobby) {
      this.hobby = hobby;
   }

   public String getIsShow() {
      return isShow;
   }

   public void setIsShow(String isShow) {
      this.isShow = isShow;
   }

   public String getHeadImg() {
      return headImg;
   }

   public void setHeadImg(String headImg) {
      this.headImg = headImg;
   }

   public String getBirthDate() {
      return birthDate;
   }

   public void setBirthDate(String birthDate) {
      this.birthDate = birthDate;
   }

   public String getTag() {
      return tag;
   }

   public void setTag(String tag) {
      this.tag = tag;
   }

   public String getJob() {
      return job;
   }

   public void setJob(String job) {
      this.job = job;
   }

   public String getHometown() {
      return hometown;
   }

   public void setHometown(String hometown) {
      this.hometown = hometown;
   }

   public String getQqopenid() {
      return qqopenid;
   }

   public void setQqopenid(String qqopenid) {
      this.qqopenid = qqopenid;
   }

   public String getQqinfo() {
      return qqinfo;
   }

   public void setQqinfo(String qqinfo) {
      this.qqinfo = qqinfo;
   }

   public String getSinaopenid() {
      return sinaopenid;
   }

   public void setSinaopenid(String sinaopenid) {
      this.sinaopenid = sinaopenid;
   }

   public String getSinainfo() {
      return sinainfo;
   }

   public void setSinainfo(String sinainfo) {
      this.sinainfo = sinainfo;
   }

   public Integer getPoints() {
      return points;
   }

   public void setPoints(Integer points) {
      this.points = points;
   }

   public double getAdvanceDeposit() {
      return advanceDeposit;
   }

   public void setAdvanceDeposit(double advanceDeposit) {
      this.advanceDeposit = advanceDeposit;
   }

   public List<String> getCustomerImg() {
      return customerImg;
   }

   public void setCustomerImg(List<String> customerImg) {
      this.customerImg = customerImg;
   }

   public String getAge() {
      return age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   @Override
   public String toString() {
      return "CustomerInfo [id=" + id + ", customerName=" + customerName
              + ", passWord=" + passWord + ", registerTime=" + registerTime
              + ", messageCode=" + messageCode + ", nickName=" + nickName
              + ", signature=" + signature + ", sex=" + sex + ", hobby="
              + hobby + ", isShow=" + isShow + ", headImg=" + headImg
              + ", updateTime=" + updateTime
              + ", tag=" + tag + ", hometown=" + hometown + ", qqopenid="
              + qqopenid + ", qqinfo=" + qqinfo + ", sinaopenid="
              + sinaopenid + ", sinainfo=" + sinainfo + ", points=" + points
              + ", advanceDeposit=" + advanceDeposit + ", customerImg="
              + customerImg + "]";
   }
}
