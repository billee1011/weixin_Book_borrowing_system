// pages/register/register.js
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
function countdown1(that) {
  //验证码有效期计时器
  var second = that.data.second_canCode
  if (second == 0) {
    that.setData({
      second_canCode: 600
    });
    that.data.backcode = -1;
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
    Y: true,
    N: false,
    phone: '',
    backups_phone: '',
    show1: false,
    hinttext: "输入的手机号格式不正确",
    hinttext1: "验证码不能为空",
    show2: false,
    idnumber: '',
    show3: false,
    hinttext2: "输入的身份证号格式不正确",
    password: '',
    second: 30,
    btntext: '获取验证码',
    flag: false,
    show4: false,
    show5: false,
    hinttext3: "姓名不能为空",
    hinttext4: "密码长度不能低于6位",
    code: '',
    backcode: -1,
    name: '',
    second_canCode: 600,
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },

  phonelur: function () {
    //手机号输入框失去焦点时执行
    if ((/^1[3|4|5|7|8][0-9]{9}$/.test(this.data.phone))) {
      this.setData({ show1: false })
      if (this.data.code == '') {
        this.setData({ show2: true })
      }
    } else {
      if (this.data.phone == '') {
        this.setData({ hinttext: "手机号不能为空" });
        this.setData({ show1: true });
      } else {
        this.setData({ hinttext: "输入的手机号格式不正确" });
        this.setData({ show1: true });

      }
    }
  },
  inputphone: function (e) {
    //手机号输入框输入时执行
    this.setData({ phone: e.detail.value });
  },
  phonefocus: function () {
    this.setData({ show1: false })
  },
  idlur: function () {
    //身份证号输入框失去焦点时执行
    if (this.data.show2 == true || this.data.show1 == true) {
      this.setData({ show3: false });
    } else {
      if ((/(^\d{15}$)|(^\d{6}[1|2]\d{10}(\d|X|x)$)/.test(this.data.idnumber))) {
        this.setData({ show3: false })
        if (this.data.name == '') {
          this.setData({ show4: true })
        } else {
          this.setData({ show4: false })
        }
      } else {
        if (this.data.idnumber == '') {
          this.setData({ hinttext2: "身份证号不能为空" });
          this.setData({ show3: true });
        } else {
          this.setData({ hinttext2: "输入的身份证号格式不正确" });
          this.setData({ show3: true });

        }
      }
    }
  },
  inputid: function (e) {
    //身份证号输入框输入时执行
    this.setData({ idnumber: e.detail.value });
  },
  idfocus: function () {
    this.setData({ show3: false })
  },
  codelur: function () {
    //输入验证码失去交调点时执行
    if (this.data.show1 == true) {
      this.setData({ show2: false })
    } else {
      if (this.data.code == '') {
        this.setData({ show2: true })
      } else {
        this.setData({ show2: false })
        if (this.data.idnumber == '') {
          this.setData({ hinttext2: "身份证号不能为空" });
          this.setData({ show3: true });
        } else if (!(/(^\d{15}$)|(^\d{6}[1|2]\d{10}(\d|X|x)$)/.test(this.data.idnumber))) {
          this.setData({ hinttext2: "输入的身份证号格式不正确" });
          this.setData({ show3: true });

        }
      }
    }
  },
  codefocus: function () {
    this.setData({ show2: false });
  },
  inputcode: function (e) {
    //验证码输入时执行
    this.setData({ code: e.detail.value });
  },
  namelur: function () {
    //输入姓名失去交调点时执行
    if (this.data.show1 == true || this.data.show2 == true || this.data.show3 == true) {
      this.setData({ show4: false })
    } else {
      if (this.data.name == '') {
        this.setData({ show4: true })
      } else {
        this.setData({ show4: false })
        if (this.data.password.length > 5) {
          this.setData({ show5: false })
        } else {
          this.setData({ show5: true })
        }
      }
    }
  },
  namefocus: function () {
    this.setData({ show4: false })
  },
  inputname: function (e) {
    //姓名输入时执行
    this.setData({ name: e.detail.value });
    this.setData({ show4: false })
  },
  pwdlur: function () {
    //密码框失去焦点时执行
    if (this.data.show1 == true || this.data.show2 == true || this.data.show3 == true || this.data.show4 == true) {
      this.setData({ show5: false })
    } else {
      if (this.data.password.length > 5) {
        this.setData({ show5: false })
      } else {
        this.setData({ show5: true })
      }
    }
  },
  inputpwd: function (e) {
    //密码框输入时执行
    this.setData({ password: e.detail.value });
    this.setData({ show5: false })
  },
  obtaincode: function () {
    //点击获取验证码
    var that = this;
    if (this.data.show1 == true) {
      return false;
    } else {
      this.setData({ show2: false });
      wx.request({
        url: 'https://www.kyssky.party:8443/weixin/PhoneRegister',
       // url: 'http://localhost:8080/weixin/PhoneRegister',
        data: {
          phonenumber: that.data.phone
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
          console.log(res.statusCode);
          console.log(res.data);
          if (res.statusCode != 200) {
            wx.showToast({
              title: '网路连接超时',
              image: '../../images/timg.png',
              duration: 3000
            })
          } else if (res.data.statuscode == 2002) {
            wx.showModal({
              title: '提示',
              content: '该用户已注册',
              showCancel: false
            })
          } else if (res.data.statuscode == 2001) {
            countdown(that);
            that.data.second_canCode = 600;
            countdown1(that);
            that.setData({ backups_phone: that.data.phone, backcode: res.data.code });
            // console.log(res.data.code);
            console.log(res.data);
          }
        },
        fail: function (res) {
          wx.showToast({
            title: '网路连接超时',
            image: '../../images/timg.png',
            duration: 3000
          })
        }
      })
    }

  },
  formSubmit: function () {
    var app = getApp();
    var that = this;
    if (this.data.show1 == true || this.data.show2 == true || this.data.show3 == true || this.data.show4 == true || this.data.show5 == true) {
      return false;
    } else if (this.data.code != this.data.backcode) {
      wx.showModal({
        title: '提示',
        content: '验证码错误',
        showCancel: false
      })
    }

    else {
      wx.showLoading({
        title: '正在注册中'
      })
      that.data.backcode = -1;
      wx.request({
        url: 'https://www.kyssky.party:8443/weixin/Register', 
        //url: 'http://localhost:8080/weixin/Register',
        method: 'POST',
        data: {
          phonenumber: that.data.backups_phone,
          idcard: that.data.idnumber,
          name: that.data.name,
          password: that.data.password,
          weixin: ''
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'

        },
        success: function (res) {
          console.log(res.data);
          if (res.data.statuscode == 1001) {
            //注册成功
            if (app.data.weixin != '') {
              wx.request({
                url:'https://www.kyssky.party:8443/weixin/BindWeiXin',
                data: {
                  weixin: app.data.weixin,
                  phone: that.data.backups_phone
                },
                header: {
                  'content-type': 'application/x-www-form-urlencoded'
                },
                success: function (res) {
                  wx.hideLoading();
                  if (res.data.statuscode == 8001) {
                    app.data.phonenumber = that.data.backups_phone;
                    wx.switchTab({
                      url: '../my/my'
                    })
                  } else {
                    wx.showModal({
                      title: '提示',
                      content: '注册成功，但是由于某些原因，导致绑定微信账号失败，请稍后直接绑定账号',
                      showCancel: false
                    })

                  }
                },
                fail: function () {
                  wx.showModal({
                    title: '提示',
                    content: '注册成功，但是由于某些原因，导致绑定微信账号失败，请稍后直接绑定账号',
                    showCancel: false
                  })
                }
              })
            } else {
              wx.hideLoading();
              app.data.phonenumber = that.data.backups_phone;
              wx.switchTab({
                url: '../my/my'
              })
            }
          } else if (res.data.statuscode == 1002) {
            wx.showModal({
              title: '提示',
              content: '服务器异常，请稍后重试',
              showCancel: false
            })
          } else if (res.data.statuscode == 1003) {
            wx.showModal({
              title: '提示',
              content: '身份认证错误',
              showCancel: false
            })
          } else {
            wx.showModal({
              title: '提示',
              content: '发生未知错误',
              showCancel: false
            })
          }
        },
        fail: function () {
          wx.showToast({
            title: '网路连接超时',
            image: '../../images/timg.png',
            duration: 3000
          })
        }
      })
    }

  }

})