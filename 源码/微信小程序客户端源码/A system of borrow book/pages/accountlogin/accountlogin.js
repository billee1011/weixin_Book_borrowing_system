// pages/accountlogin/accountlogin.js
function countdown(that) {
  //获取验证码时验证码计时器
  var second = that.data.second
  if (second == 0) {
    that.setData({
      second: 30
    });
    that.setData({ btntext: '获取验证码' });
    that.setData({ flag4: false });
    return;
  }
  var time = setTimeout(function () {
    that.setData({ flag4: true });
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
  data: {
    second_canCode: 600,
    btntext: '获取验证码',
    second: 30,
    flag4: false,
    currentTab: 0,
    src: "../../images/weixin.png",
    password: '',
    flag: 1,
    flag1: 1,
    flag2: 1,
    flag3: 3,
    phone: '',
    code: '',
    return_code: -1,
    phone_sms: '',
    backup_phone: '',
    isConnected: true
  },

  onLoad: function (options) {
    // 生命周期函数--监听页面加载
  },
  onReady: function () {
    // 生命周期函数--监听页面初次渲染完成

  },
  onShow: function () {
    // 生命周期函数--监听页面显示
    // console.log(this);
  },
  onHide: function () {
    // 生命周期函数--监听页面隐藏

  },
  onUnload: function () {
    // 生命周期函数--监听页面卸载

  },
  onPullDownRefresh: function () {
    // 页面相关事件处理函数--监听用户下拉动作

  },
  onReachBottom: function () {
    // 页面上拉触底事件的处理函数

  },

  onShareAppMessage: function () {
    // 用户点击右上角分享
    wx.showShareMenu({
      withShareTicket: true
    })
  },
  clicktab: function (e) {
    //点击切换
    var that = this;
    if (this.data.currentTab == e.target.dataset.current) {
      return false;

    } else {
      that.setData({ currentTab: e.target.dataset.current });
    }
  },
  bindChange: function (e) {
    //滑动切换
    var that = this;
    that.setData({ currentTab: e.detail.current });

  },
  phoneinput: function (e) {
    //短信登陆时 手机号输入框输入时执行
    this.setData({ flag2: 1, phone_sms: e.detail.value })
  },
  codeinput: function (e) {
    //短信登陆时 验证码输入框输入时执行
    this.setData({ flag3: 1, code: e.detail.value })
  },
  idinput: function (e) {
    //账号登陆时 手机号输入框输入时执行
    this.setData({ flag: 1, phone: e.detail.value })
    //console.log(e);
  },
  pwdinput: function (e) {
    //账号登陆时 密码输入框输入时执行
    this.setData({ flag1: 1, password: e.detail.value })
    // console.log(e);
  },
  forSubmit: function () {
    //账号登陆时执行
    var app = getApp();
    var that = this;
    if (this.data.phone == '') {
      this.setData({ flag: 0 })
      return false;
    } else if (this.data.password == '') {
      this.setData({ flag1: 0 })
      return false;
    }
    else {
      wx.onNetworkStatusChange(function (res) {
        that.setData({ isConnected: res.isConnected })
      })
      if (!this.data.isConnected) {
        //检查网络情况
        wx.showToast({
          title: '网路出错',
          image: '../../images/timg.png',
          duration: 3000
        })
      } else {
        wx.showLoading({
          title: '正在登陆中'
        }),
          wx.request({
            url: 'https://www.kyssky.party:8443/weixin/LoginByPhone',
        
            data: {
              phonenumber: that.data.phone,
              password: that.data.password
            },
            header: {
              'content-type': 'application/x-www-form-urlencoded'
            },
            success: function (res) {
              wx.hideLoading();
              if (res.data.statuscode == 3001) {
                app.data.phonenumber = that.data.phone;
                wx.switchTab({
                  url: '../my/my'
                })
              } else if (res.data.statuscode == 3002) {
                wx.showToast({
                  title: '密码或账号错误！',
                  image: '../../images/timg.png',
                  duration: 3000
                })
              } else if (res.data.statuscode == 3003) {
                wx.showToast({
                  title: '服务器异常，请稍后重试',
                  image: '../../images/timg.png',
                  duration: 3000
                })
              } else {
                wx.showToast({
                  title: '服务器异常，请稍后重试',
                  image: '../../images/timg.png',
                  duration: 3000
                })
              }
            },
            fail: function () {
              wx.showToast({
                title: '网络异常，请稍后重试',
                image: '../../images/timg.png',
                duration: 3000
              })
            }
          })
      }

    }
  },
  forSubmit1: function () {
    //短信登陆时执行
    var app = getApp();
    if (this.data.phone_sms == '') {
      this.setData({ flag2: 0 })
      return false;
    } else if (this.data.code == '') {
      this.setData({ flag3: 0 })
      return false;
    }
    else {
      if (this.data.code == this.data.return_code && this.data.phone_sms == this.data.backup_phone) {
        app.data.phonenumber = this.data.backup_phone;
        wx.switchTab({
          url: '../my/my'
        })
      } else {
        wx.showToast({
          title: '验证码错误',
          image: '../../images/timg.png',
          duration: 2000
        })
      }
    }
  },
  getcode: function () {
    //点击获取验证码按钮时执行
    var that = this;
    if (this.data.phone_sms == '') {
      this.setData({ flag2: 0 })
      return false;
    }
    else if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(this.data.phone_sms))) {
      wx.showModal({
        title: '提示',
        content: '输入的手机号格式错误',
        showCancel: false,
      })

    } else {
      that.data.backup_phone = that.data.phone_sms;
      wx.request({
        url: 'https://www.kyssky.party:8443/weixin/LoginBySMS',
  
        data: {
          phonenumber: that.data.backup_phone
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
          console.log(res.data)
          if (res.data.statuscode == 4001) {
            countdown(that);
            that.data.second_canCode = 600;
            countdown1(that);
            that.data.return_code = res.data.code;
          } else if (res.data.statuscode == 4002) {
            wx.showToast({
              title: '该手机号未注册',
              image: '../../images/timg.png',
              duration: 2000
            })
          } else if (res.data.statuscode == 4003) {
            wx.showToast({
              title: '服务器异常',
              image: '../../images/timg.png',
              duration: 2000
            })
          } else {
            wx.showToast({
              title: '服务器异常',
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
  btnregister: function () {
    wx.navigateTo({
      url: '../register/register'
    })
  },
  findpwd: function () {
    //找回密码
    wx.navigateTo({
      url: '../findpwd/findpwd'
    })
  },
  weixinlogin: function () {
    //微信登陆
    var app = getApp();
    wx.showLoading({
      title: '正在登录中'
    }),
      wx.login({
        success: function (res) {
          if (res.code) {
            //发起网络请求
            wx.request({
              url: 'https://www.kyssky.party:8443/weixin/LoginServlet',
              data: {
                code: res.code
              },
              success: function (res) {
                wx.hideLoading();
                if (res.data.statuscode == 7001) {
                  app.data.phonenumber = res.data.phone;
                  wx.switchTab({
                    url: '../my/my'
                  })
                } else if (res.data.statuscode == 7002) {
                  app.data.weixin = res.data.weixin;
                  wx.redirectTo({
                    url: '../loginbyweixinafter/loginbyweixinafter'
                  })
                } else if (res.data.statuscode == 7003) {
                  wx.showToast({
                    title: '登录失败',
                    image: '../../images/timg.png',
                    duration: 3000
                  })
                } else {
                  wx.showToast({
                    title: '登录失败',
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
          } else {
            wx.showToast({
              title: '网络异常',
              image: '../../images/timg.png',
              duration: 3000
            })
          }
        }
      });
  }
})
