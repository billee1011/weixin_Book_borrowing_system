// pages/myBorrow/myBorrow.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    display: 'none',
    readyBorrow: [],
    isChecked: 'false',
    bookInfo: [],
    src: "",
    disabled:true,
    touch_start: 0,
    touch_end: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    var that = this;
    var phoneNumber = getApp().data.phonenumber;
    console.log(phoneNumber);

    var username = getApp().data.username;

    if (phoneNumber == '' && username == '') {
      wx.navigateTo({
        url: '../accountlogin/accountlogin',
      })
    }
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
    var phoneNumber = getApp().data.phonenumber;
    var username = getApp().data.username;
    console.log(phoneNumber);
    if (phoneNumber == '' && username == '') {
      wx.navigateTo({
        url: '../accountlogin/accountlogin',
      })
    } else {
      wx.request({
        url: 'https://www.kyssky.party:8443/weixin/GetBookJIYUEZHONG',
        data: {
          phonenumber: phoneNumber
        },
        success: function (res) {
          console.log(res.data);
          console.log("dfsdfdsf");
          if(res.data.simplebookinfo.length == 0) {
            that.setData({
              display: "block",
              disabled:true,
              readyBorrow: res.data.simplebookinfo
            });
            console.log("sd");
          } else {
            that.setData({
              readyBorrow: res.data.simplebookinfo,
              display: "none"
            });
          }
          console.log(that.data.readyBorrow);
        },
        fail: function () {
          wx.showToast({
            title: '网路出错',
            image: '../../images/timg.png',
            duration: 3000
          })
        }
      })
    }


  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    this.onUnload();
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
   console.log("xiezaile")
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
  checkboxChange: function (e) {
    if (e.detail.value.length == 1 || e.detail.value.length == 2) {
      this.setData({
        bookInfo: e.detail.value,
        disabled: false
      });
    } else {
      console.log("check")
      this.setData({
        disabled: true
      });
    }
  },
  createQrCode: function () {
    //传递选中的书籍
    var title = "我的借书二维码";
    var bookIdStr = this.data.bookInfo.join(',');
    wx.navigateTo({
      url: '../qrCode/qrCode?title=' + title + "&bookIds=" + bookIdStr,
    })
  },
  goBookSimple: function (e) {
    var isbn = e.currentTarget.dataset.isbn;
    var id = e.currentTarget.dataset.id;

    var that = this;

    var phoneNumber = getApp().data.phonenumber;
    var status = 'C'
    var newBooks = this.data.readyBorrow;
    //触摸时间距离页面打开的毫秒数  
    var touchTime = that.data.touch_end - that.data.touch_start;

    if (touchTime > 350) {
      //长按 删除
      wx.showModal({
        title: '提示',
        content: '从借书栏中删除此书',
        success: function (res) {
          if (res.confirm) {
            console.log('用户点击了确定');
            wx.request({
              url: 'https://www.kyssky.party:8443/weixin/BookQUxiao',
              data: {
                ISBN: isbn,
                phonenumber: phoneNumber,
                ID: id,
                statue: status
              },
              success: function (res) {
                if (res.data.statusCode == '0') {
                  wx.showToast({
                    title: '删除成功',
                  })

                  //重新渲染
                  that.onShow();

                }
              }
            })
          }
        }
      })

    } else {
      wx.navigateTo({
        url: '../booksimple/booksimple?isbn=' + isbn + '&id=' + id + '&s=C',
      })
    }
  },
  mytouchstart: function (e) {
    let that = this;
    that.setData({
      touch_start: e.timeStamp
    })
    console.log(e.timeStamp + '- touch-start')
  },
  //按下事件结束  
  mytouchend: function (e) {
    let that = this;
    that.setData({
      touch_end: e.timeStamp
    })
    console.log(e.timeStamp + '- touch-end')
  }
})