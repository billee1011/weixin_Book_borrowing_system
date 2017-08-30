// setting.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    flag0: false,
    flag1: true,
    flag2: true,
    flag3: true,
    flag4: true,
    flag5: true,
    flag6: false,
    email: 'null',
    setting1: '',
    setting2: '',
    setting3: ''
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
    var that = this;
    var app = getApp();
    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/FindSetting', 
      data: {
        phone: app.data.phonenumber
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        //  console.log(res.data);
        //console.log(res.data.statuscode);

        if (res.data.statuscode == 1101) {
          //  console.log(res.data)
          that.data.email = res.data.email;
          app.data.email = that.data.email;
          //  console.log(app.data.email);

          if (res.data.setting1 == '1') {
            that.setData({ flag1: true })
          } else {
            that.setData({ flag1: false })
          }
          if (res.data.setting2 == '1') {
            that.setData({ flag2: true })
          } else {
            that.setData({ flag2: false })
          }
          if (res.data.setting3 == 'week') {
            that.setData({ flag3: true })
          } else {
            that.setData({ flag3: false })
          }
          that.setData({ flag0: true, flag5: false, flag6: false });

        } else {
          that.setData({ flag0: false, flag5: false, flag6: true });
          //console.log("dasdas111");
        }


      },
      fail: function () {
        that.setData({ flag0: false, flag5: false, flag6: true });

      }
    })
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
  switch1Change: function (e) {
    //接收提醒按钮点击触发

    var app = getApp();
    var that = this;
    if (app.data.email == 'null' || app.data.email == '') {
      this.setData({ flag1: false });
      wx.showModal({
        title: '提示',
        content: '您还未绑定邮箱，绑定邮箱后才可以接收推送，是否立即绑定？',
        success: function (res) {
          if (res.confirm) {
            //绑定邮箱
            wx.navigateTo({
              url: '../bindemail/bindemail'
            })
          } else if (res.cancel) {

          }
        }
      })

    } else {
      //修改设置
      wx.showLoading({
        title: '修改中',
      })
      if (e.detail.value == true) {
        that.data.setting1 = '1';
      } else {
        that.data.setting1 = '0';
      }
      wx.request({
        url: 'https://www.kyssky.party:8443/weixin/UpdateSetting', //仅为示例，并非真实的接口地址

        data: {
          phone: app.data.phonenumber,
          setting1: that.data.setting1
        },

        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },

        success: function (res) {
          wx.hideLoading();
          if (res.data.statuscode == 2101) {
            that.setData({ flag1: e.detail.value })
          } else {
            wx.showToast({
              title: '修改设置失败',
              image: '../../images/timg.png',
              duration: 3000
            })
          }
        },

        fail: function () {
          wx.hideLoading();
          wx.showToast({
            title: '修改设置失败',
            image: '../../images/timg.png',
            duration: 3000
          })

        }

      })


    }

  },
  switch2Change: function (e) {
    //推荐图书按钮点击触发
    var app = getApp();
    var that=this;
    if (app.data.email == 'null' || app.data.email == '') {
      this.setData({ flag2: false });
      wx.showModal({
        title: '提示',
        content: '您还未绑定邮箱，绑定邮箱后才可以接收推送，是否立即绑定？',
        success: function (res) {
          if (res.confirm) {
            //绑定邮箱
            wx.navigateTo({
              url: '../bindemail/bindemail'
            })
          } else if (res.cancel) {

          }
        }
      })

    } else {
      //修改设置
      wx.showLoading({
        title: '修改中',
      })
      if (e.detail.value == true) {
        that.data.setting2 = '1';
      } else {
        that.data.setting2 = '0';
      }
      wx.request({
        url: 'https://www.kyssky.party:8443/weixin/UpdateSetting', 

        data: {
          phone: app.data.phonenumber,
          setting2: that.data.setting2
        },

        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },

        success: function (res) {
          wx.hideLoading();
          if (res.data.statuscode == 2101) {
            that.setData({ flag2: e.detail.value })
          } else {
            wx.showToast({
              title: '修改设置失败',
              image: '../../images/timg.png',
              duration: 3000
            })
          }
        },

        fail: function () {
          wx.hideLoading();
          wx.showToast({
            title: '修改设置失败',
            image: '../../images/timg.png',
            duration: 3000
          })

        }

      })
    }
  },
  changitem3body: function () {
    if (this.data.flag3 == true) {
      this.setData({ flag3: false })
    } else {
      this.setData({ flag3: true })
    }

  },
  radioChange: function (e) {
    var app=getApp();
    var that=this;
    wx.showLoading({
      title: '修改中',
    })
    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/UpdateSetting', 

      data: {
        phone: app.data.phonenumber,
        setting3: e.detail.value
      },

      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },

      success: function (res) {
        wx.hideLoading();
        if (res.data.statuscode == 2101) {
          if (e.detail.value == 'week') {
            that.setData({ flag4: true })

          } else {
            that.setData({ flag4: false })
          }

        } else {
          wx.showToast({
            title: '修改设置失败',
            image: '../../images/timg.png',
            duration: 3000
          })
        }
      },

      fail: function () {
        wx.hideLoading();
        wx.showToast({
          title: '修改设置失败',
          image: '../../images/timg.png',
          duration: 3000
        })

      }

    })


  },
  loading: function () {
    this.onShow();
  }
})