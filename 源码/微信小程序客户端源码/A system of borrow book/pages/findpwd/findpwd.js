// pages/findpwd/findpwd.js
function countdown(that) {
  //获取验证码时验证码计时器
  var second = that.data.second
  if (second == 0) {
    that.setData({
      second: 30
    });
    that.setData({ btntext: '获取验证码' });
    that.setData({ flag3: false });
    return;
  }
  var time = setTimeout(function () {
    that.setData({ flag3: true });
    that.setData({
      second: second - 1
    });
    that.setData({ btntext: that.data.second + 's' })
    countdown(that);
  }
    , 1000)
}
function countdown1(that) {
  //验证码有效期计时器
  var second = that.data.second_canCode
  if (second == 0) {
    that.setData({
      second_canCode: 600
    });
    that.data.return_code = -1;
    return;
  }
  var time = setTimeout(function () {
    that.data.second_canCode = second - 1;
    countdown1(that);
  }
    , 1000)
}
Page({

  /**
   * 页面的初始数据
   */
  data: {
    flag3: false,
    btntext:'获取验证码',
    second:30,
    second_canCode:600,
    phone:'',
    backup_phone:'',
    code:'',
    return_code:-1,
    flag:false,
    flag1:false
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

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },
  /**
   * 当手机号输入框输入的时候执行
   */
  phoneinput:function(e){
   this.setData({phone:e.detail.value,flag:false})
   
  },
  /**
   * 当验证码框输入的时候执行
   */
  codeinput:function(e){
    this.setData({ code: e.detail.value,flag1:false})
  },
  getcode: function () {
    //点击获取验证码按钮时执行
    var that=this;
    if (this.data.phone == '') {
      this.setData({ flag: true })
      return false;
    }
    else if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(this.data.phone))) {
      wx.showModal({
        title: '提示',
        content: '输入的手机号格式错误',
        showCancel: false,
      })

    } else {
      wx.request({
       url: 'https://www.kyssky.party:8443/weixin/FindPhone', 
        // url: 'http://localhost:8080/weixin/FindPhone', 
        data: {
          phonenumber: this.data.phone
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
          console.log(res.data)
          if (res.data.statuscode == 5001) {
            countdown(that);
            that.data.second_canCode = 600;
            countdown1(that);
            that.data.return_code = res.data.code;
            that.data.backup_phone=that.data.phone;
          } else if (res.data.statuscode == 5002) {
            wx.showToast({
              title: '该用户未注册',
              image: '../../images/timg.png',
              duration: 2000
            })
          } else if (res.data.statuscode == 5003) {
            wx.showToast({
              title: '服务器异常',
              image: '../../images/timg.png',
              duration: 2000
            })
          } else {
            wx.showToast({
              title: '发生未知错误',
              image: '../../images/timg.png',
              duration: 2000
            })
          }
        },
        fail: function () {
          wx.showToast({
            title: '网络连接错误',
            image: '../../images/timg.png',
            duration: 2000
          })
        }
      })
    }
  },
  /**
   * 点击设置新密码
   */
  formSubmit:function(){
    var app=getApp();
    var that=this;
    if (this.data.phone == '') {
      this.setData({ flag: true })
      return false;
    }
else if(this.data.code==''){
  this.setData({flag1:true})
  return false;
}else{
      if (this.data.code == this.data.return_code) {
        app.data.phonenumber = that.data.backup_phone;
        wx.redirectTo({
          url:'../setpwd/setpwd'
        })
      } else {
        wx.showToast({
          title: '验证码错误',
          image: '../../images/timg.png',
          duration: 2000
        })
      }
}
  }
})