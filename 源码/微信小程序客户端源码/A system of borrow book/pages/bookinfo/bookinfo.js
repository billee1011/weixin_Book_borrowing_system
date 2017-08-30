// bookinfo.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    onload2: false,
    onload1: false,
    isConnected: true,
    book: [],
    recommend_book: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    var that = this;
    var app = getApp();
    var bookinfo = "";
    if (e.bookisbnandid) {
      if (e.bookisbnandid.length == 14) {
        bookinfo = e.bookisbnandid.substring(0, e.bookisbnandid.length - 1);
      } else if (e.bookisbnandid.length == 13) {
        bookinfo = e.bookisbnandid;
      }

    } else {
      bookinfo = app.temp.isbn;
    }

    console.log(bookinfo);


    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/SelectBook',

      data: {
        // info: app.temp.isbn
        info: bookinfo
      },

      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },

      success: function (res) {
        console.log(res.data.book);
        that.setData({
          book: res.data.book
        });
        if (res.data.book.bookname.length > 19) {
          res.data.book.bookname = res.data.book.bookname.slice(0, 19) + "..."
        }
        that.setData({ book: res.data.book })
        wx.request({
          url: 'https://www.kyssky.party:8443/weixin/SelectBookForPush',
          data: {
            info: that.data.book.category,
            ISBN: app.temp.isbn
          },

          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },

          success: function (res) {
            console.log(res.data);
            for (var i = 0; i < res.data.simplebookinfo.length; i++) {
              if (res.data.simplebookinfo[i].bookname.length > 13) {
                res.data.simplebookinfo[i].bookname = res.data.simplebookinfo[i].bookname.slice(0, 13) + '...'
              }
            }
            that.setData({ recommend_book: res.data.simplebookinfo, onload1: true, onload2: false })

          },

          fail: function () {

            // that.setData({ onload1: false, onload2: true });
            wx.showToast({
              title: '请求错误',
              image: '../../images/nofound.png'
            })
          }

        })
      },

      fail: function () {
        wx.showToast({
          title: '请求错误',
          image: '../../images/nofound.png'
        })
        that.setData({ onload1: false, onload2: true });

      }

    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  loading: function () {
    this.onLoad();
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
  reonload: function (e) {
    var app = getApp();
    app.temp.isbn = e.currentTarget.id;
    wx.redirectTo({
      url: '../bookinfo/bookinfo'
    })

  },
  goReservation: function () {
    var that = this;
    var phone = getApp().data.phonenumber;
    var username = getApp().data.username;
    if (phone == '' && username == '') {
      wx.showToast({
        title: '你还未登录',
        image: '../../images/pleaseLogin.png',
        duration: 2000,
        mask: true,
        success: function (res) { },
        fail: function (res) { },
        complete: function (res) { },
      })
    } else {
      // console.log(getApp().temp.isbn);

      wx.request({
        url: 'https://www.kyssky.party:8443/weixin/SelectBookCanBorrowORBook',
        data: {
          phonenumber: phone,
          ISBN: getApp().temp.isbn,
          status: "B"
        },
        header: {
          'content-type': 'application/json'
        },
        success: function (res) {
          console.log(res.data);
          var returnC = res.data;

          if (returnC.statusCode == '0') {
            var sign = 'B';
            console.log(that.data.book);
            var src = that.data.book.bookurl;
            var author = that.data.book.author;
            var name = that.data.book.bookname;
            wx.showModal({
              title: '确认预定?',
              content: '',
              success: function (res) {
                if (res.confirm) {
                  //用户点击了确定

                  wx.request({
                    url: 'https://www.kyssky.party:8443/weixin/BookBook',
                    data: {
                      ISBN: getApp().temp.isbn,
                      phonenumber: phone
                    },
                    success: function (res) {
                      console.log(res.data);
                      if (res.data.statusCode == '0') {
                        wx.redirectTo({
                          url: '../willReservation/willReservation?isbn=' + getApp().temp.isbn + '&sign=' + sign + '&src=' + src + '&author=' + author + '&name=' + name,
                        })
                      }
                    }
                  })
                }
              }
            })
          } else if (returnC.statusCode == '2') {
            wx.showToast({
              title: '您的预定列表已满',
            })
          } else {
            wx.showToast({
              title: '该书不可预定',
            })
          }
        }
      })
    }
  },
  goBorrow: function () {
    var phone = getApp().data.phonenumber;
    var username = getApp().data.username;
    var that = this;
    if (phone == '' && username == '') {
      wx.showToast({
        title: '你还未登录',

        image: '../../images/pleaseLogin.png',
        duration: 2000,
        mask: true,
        success: function (res) { },
        fail: function (res) { },
        complete: function (res) { },
      })
    } else {
      wx.request({
        url: 'https://www.kyssky.party:8443/weixin/SelectBookCanBorrowORBook',
        data: {
          phonenumber: phone,
          ISBN: getApp().temp.isbn,
          status: "C"
        },
        header: {
          'content-type': 'application/json'
        },
        success: function (res) {
          console.log(res.data);
          var returnC = res.data;
          if (returnC.statusCode == '0') {
            var sign = 'C';
            console.log(that.data.book);
            var src = that.data.book.bookurl;
            var author = that.data.book.author;
            var name = that.data.book.bookname;
            wx.showModal({
              title: '确认加入借书栏?',
              content: '',
              success: function (res) {
                if (res.confirm) {
                  //用户点击了确定
                  wx.request({
                    url: 'https://www.kyssky.party:8443/weixin/BorrowBook',
                    data: {
                      ISBN: getApp().temp.isbn,
                      phonenumber: phone
                    },
                    success: function (res) {
                      if (res.data.statusCode == '0') {
                        wx.redirectTo({
                          url: '../willReservation/willReservation?isbn=' + getApp().temp.isbn + '&sign=' + sign + '&src=' + src + '&author=' + author + '&name=' + name,
                        })
                      } else if (res.data.statusCode == '2') {
                        wx.showToast({
                          title: '您的借书栏已满',
                        })
                      }
                    }
                  })



                }
              }
            })
          } else {
            wx.showToast({
              title: '该书不可借阅',
            })
          }
        }
      })
    }
  }
})