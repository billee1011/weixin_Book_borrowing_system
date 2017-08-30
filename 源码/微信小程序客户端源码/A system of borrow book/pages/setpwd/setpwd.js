// setpwd.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    phone: '',
    setpwd: '',
    confirmpwd: '',
    show1: false,
    show2: false,
    hinttext1: '密码不能为空',
    hinttext2: '确认密码与设置的新密码不符'
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
   * 设置新密码框失去聚焦时执行
   */
  setpwdlur: function () {
    if (this.data.setpwd == '') {
      this.setData({ hinttext1: '密码不能为空', show1: true })
    } else if (this.data.setpwd.length < 6) {
      this.setData({ hinttext1: '密码长度不能低于6位', show1: true })
    } else {
      return false;
    }
  },
  /**
   * 设置新密码框输入时执行
   */
  setpwdinput: function (e) {
    this.setData({ setpwd: e.detail.value, show1: false })

  },
  /**
   * 确认密码框失去聚焦时执行
   */
  confirmpwdlur: function () {
    // console.log("sads");
    if (this.data.show1 == true) {
      this.setData({ show2: false })
    } else if (this.data.confirmpwd != this.data.setpwd) {
      this.setData({ show2: true })
    }
  },
  /**
   * 确认密码框输入时执行
   */
  confirmpwdinput: function (e) {
    this.setData({ confirmpwd: e.detail.value, show2: false })
  },
  /*
  *   点击确定按钮执行
  */
  formSubmit: function () {
    var app = getApp();
    var that = this;
    if (this.data.setpwd == '') {
      this.setData({ hinttext1: '密码不能为空', show1: true })
      return false;
    } else if (this.data.setpwd.length < 6) {
      this.setData({ hinttext1: '密码长度不能低于6位', show1: true })
    }
    else if (this.data.confirmpwd != this.data.setpwd) {
      this.setData({ show2: true })
    } else {
      wx.showLoading({
        title: '重新设置中...',
      })
      that.data.phone = app.data.phonenumber;
      wx.request({
        url: 'https://www.kyssky.party:8443/weixin/UpdateUser',
        //url: 'http://localhost:8080/weixin/UpdateUser',
        data: {
          phonenumber: that.data.phone,
          password: this.data.confirmpwd
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
          wx.hideLoading();
          if (res.data.statuscode == 6001) {
            wx.showModal({
              title: '提示',
              content: '修改密码成功！',
              showCancel: false,
              success: function (res) {
                if (res.confirm) {
                  wx.reLaunch({
                    url: '../accountlogin/accountlogin'
                  })
                }
              }
            })

          } else {
            wx.showToast({
              title: '重新设置密码失败',
              image: '../../images/timg.png',
              duration: 3000
            })
          }
        },
        fail: function (res) {
          wx.showToast({
            title: '网络异常',
            image: '../../images/timg.png',
            duration: 3000
          })
        }
      })
    }
  }
})