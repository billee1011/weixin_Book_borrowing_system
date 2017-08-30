// bindemail.js
function countdown(that) {
  //获取验证码时验证码计时器
  var second = that.data.second
  if (second == 0) {
    that.setData({
      second: 30
    });
    that.setData({ btntext: '获取验证码' });
    that.setData({ flag: false });
    return;
  }
  var time = setTimeout(function () {
    that.setData({ flag: true });
    that.setData({
      second: second - 1
    });
    that.setData({ btntext: that.data.second + 's' })
    countdown(that);
  }
    , 1000)
}

Page({

  /**
   * 页面的初始数据
   */
  data: {
    email:'',
    btntext:'获取验证码',
    second:30,
    second_canCode:600,
    flag:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },
  input_email:function(e){
   this.setData({email:e.detail.value})
  },
  getcode:function(){
    var app=getApp();
    var that=this;
    if (/(^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$)/.test(this.data.email)){
      wx.request({
        url: 'https://www.kyssky.party:8443/weixin/BindEmail',
        data: {
          userEmail: that.data.email
        },

        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
           if(res.data.statuscode==3101){
             countdown(that);
               app.temp.backcode=res.data.code;
               app.temp.email=that.data.email;
               wx.navigateTo({
                 url: '../checkemail/checkemail',
               })
           }else{
             wx.showToast({
               title: '获取验证码失败',
               image: '../../images/timg.png',
               duration: 3000
             })
           }
        },

        fail: function () {
          wx.showToast({
            title: '网络异常',
            image: '../../images/timg.png',
            duration: 3000
          })

        }

      }) 
    }else{
      wx.showToast({
        title: '邮箱格式错误',
        image: '../../images/timg.png',
        duration: 3000
      })
    }
}


})