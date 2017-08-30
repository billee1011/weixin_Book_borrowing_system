// my.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    zhanghao: '账号：',
    username: '',
    phone: '',
    flag: true,
    exit: '退出登陆'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("onload");
    var app = getApp();
    var that = this;
    if (app.data.phonenumber == '') {
      if (app.data.weixin == '') {
        wx.navigateTo({
          url: '../accountlogin/accountlogin'
        })
      } else {
        wx.navigateTo({
          url: '../loginbyweixinafter/loginbyweixinafter'
        })
      }
    } else {

    }

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    console.log("ready");
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var app = getApp();
    var that = this;
    if (app.data.phonenumber == '') {
      if (app.data.weixin == '') {
        this.setData({ exit: '登陆', zhanghao: '未登陆' });
      } else {
        this.setData({ exit: '绑定账号', zhanghao: '未绑定账号' });
      }

    } else {
      that.setData({ phone: app.data.phonenumber });

      wx.request({
        // url: 'http://localhost:8080/weixin/FindUserName',
        url: 'https://www.kyssky.party:8443/weixin/FindUserName',
        data: {
          phone: that.data.phone
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
          that.setData({ flag: true });
          that.setData({ username: res.data.username, exit: '退出登陆', zhanghao: '账号：' })
        },
        fail: function () {
          that.setData({ flag: false });
        }
      })
    }

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    console.log("hide");
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    console.log("unload");
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
  setfunction: function () {
    //点击设置按钮
    if (this.data.username != '' && this.data.phone != '') {
      wx.navigateTo({
        url: '../../pages/setting/setting'
      })
    } else {

      wx.showToast({
        title: '请先登录',
        image: "../../images/pleaseLogin.png"
      })
    }
  },
  exit: function () {
    //点击退出登陆按钮
    var app = getApp();
    var that = this;
    if (this.data.exit == '退出登陆') {
      wx.showModal({
        title: '提示',
        content: '确定退出登陆?',
        success: function (res) {
          if (res.confirm) {
            var app = getApp();
            app.data.phonenumber = '',
              app.data.weixin = '',
              app.data.username = '',
              that.setData({ phone: app.data.phonenumber, username: app.data.username })
            that.onShow();
          } else if (res.cancel) {
            return false;
          }
        }
      })
    } else {
      if (app.data.weixin == '') {
        wx.navigateTo({
          url: '../accountlogin/accountlogin'
        })
      } else {
        wx.navigateTo({
          url: '../loginbyweixinafter/loginbyweixinafter'
        })
      }
    }
  },
  reserveBook: function () {
    //点击预定按钮
    //把当前用户的phonenumber传递给后台
    // app.data.phonenumber
    if (this.data.username != '' && this.data.phone != '') {
      wx.navigateTo({
        url: '../Reservation_list/Reservation_list'
      })
    } else {
      wx.showToast({
        title: '请先登录',
        image: "../../images/pleaseLogin.png"
      })
    }

  },
  returnBook: function () {
    //点击还书按钮

    if (this.data.username != '' && this.data.phone != '') {
      wx.navigateTo({
        url: '../repayBook/repayBook'
      })
    } else {
      wx.showToast({
        title: '请先登录',
        image: "../../images/pleaseLogin.png"
      })
    }

  },
  libraryColumn: function () {
    //点击借书栏按钮
    if (this.data.username != '' && this.data.phone != '') {
      wx.switchTab({
        url: '../myBorrow/myBorrow',
      })
    } else {
      wx.showToast({
        title: '请先登录',
        image: "../../images/pleaseLogin.png"
      })
    }

  },
  borrowHistory: function () {
    //点击借阅历史按钮
    if (this.data.username != '' && this.data.phone != '') {
      wx.navigateTo({
        url: '../borrowHistory/borrowHistory'
      })
    } else {
      wx.showToast({
        title: '请先登录',
        image: "../../images/pleaseLogin.png"
      })
    }
  },
  loading: function () {
    this.onLoad();
  }
})