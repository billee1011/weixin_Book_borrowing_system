// pages/Reservation_list/Reservation_list.js

Page({
  data: {
    //toast默认不显示  
    toastDisplay: "none",
    toastText: '你的预定列表以达到最大容量5本',
    display: 'none',
    goBorrowTxt: "去借阅",
    cancelTxt: '取消预定',
    books: [],//存放预定列表中所有的书籍
    phoneNumber: "",
    isConnected:true

  },
  onLoad: function () {
    // 页面初始化 options为页面跳转所带来的参数
    var app = getApp();
    var that = this;
    this.setData({
      phoneNumber: app.data.phonenumber
    });
    console.log("onLoad");


  },
  onReady: function () {
    // 页面渲染完成
    var app = getApp();
    var that = this;
    console.log("onReady");
  },
  onShow: function () {
    // 页面显示
    var app = getApp();
    var that = this;

    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/GetBookBook',
      data: {
        phonenumber: app.data.phonenumber
      },
      success: function (res) {
        console.log(res.data.simplebookinfo);
        if (res.data.simplebookinfo.length == 0) {
          that.setData({
            display: "block",
            books: res.data.simplebookinfo
          });
        } else {
          for (var i = 0; i < res.data.simplebookinfo.length; i++) {

            console.log(i);
            console.log(res.data.simplebookinfo[i].bookname);
            if (res.data.simplebookinfo[i].bookname.length > 28) {
              res.data.simplebookinfo[i].bookname = res.data.simplebookinfo[i].bookname.slice(0, 25) + '...';
              that.setData({
                books : res.data.simplebookinfo
              });
            } else {
              that.setData({
                books: res.data.simplebookinfo
              });
            }
          }
        }
        console.log(that.data.books);
      },
      fail: function () {
        wx.showToast({
          title: '网络异常，请稍后重试',
          image: '../../images/timg.png',
          duration: 3000
        })
      }
    })
    console.log('onShow');


  },
  onHide: function () {
    // 页面隐藏
    console.log("onHide");

  },
  onUnload: function () {
    // 页面关闭
  },
  onShareAppMessage: function () {
    return {
      title: '这是我的预定列表',
      path: ''
    }
  },
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
  },
  goBookSimple: function (e) {
    console.log('11111');
    var that = this;
    var isbn = e.currentTarget.dataset.isbn;
    var id = e.currentTarget.dataset.id;
    wx.onNetworkStatusChange(function (res) {
      console.log(res.isConnected)
      that.setData({ isConnected: res.isConnected })
    })
    console.log(that.data.isConnected)
    if (!that.data.isConnected) {
      wx.showToast({
        title: '网路出错',
        image: '../../images/timg.png',
        duration: 3000
      })
    } else {
      wx.navigateTo({
        url: '../booksimple/booksimple?isbn=' + isbn + '&id=' + id + '&s=B',
      })
    }
  },
  goBorrow: function (e) {
    var app = getApp();
    var Isbn = e.currentTarget.dataset.isbn;
    var id = e.currentTarget.dataset.id;
    var phoneNumber = getApp().data.phonenumber;
    var src = e.currentTarget.dataset.src;
    var name = e.currentTarget.dataset.name;
    var author = e.currentTarget.dataset.author;

    var that = this;
    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/SelectBookCanBorrowORBook',
      data: {
        phonenumber: phoneNumber,
        ISBN: Isbn,
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
          // console.log(that.data.book);
          wx.showModal({
            title: '确认加入借书栏?',
            content: '',
            success: function (res) {
              if (res.confirm) {
                //用户点击了确定
                wx.request({
                  url: 'https://www.kyssky.party:8443/weixin/BookFromBookToBorrow',
                  data: {
                    ISBN: Isbn,
                    phonenumber: phoneNumber,
                    ID: id
                  },
                  success: function (res) {
                    if (res.data.statusCode == '0') {
                      wx.navigateTo({
                        url: '../willReservation/willReservation?isbn=' + Isbn + '&sign=' + sign + '&src=' + src + '&author=' + author + '&name=' + name,
                      })

                      that.onShow();

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
          })
        } else if (returnC.statusCode == '2') {
          wx.showToast({
            title: '您的借书栏已满',
          })
        } else {
          wx.showToast({
            title: '该书不可借阅',
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

  },
  cancel: function (e) {
    var status = 'B';
    // console.log(e.currentTarget.dataset);
    var phoneNumber = this.data.phoneNumber;
    var cancelId = e.currentTarget.dataset.id;
    var cancelIsbn = e.currentTarget.dataset.isbn;
    var that = this;
    wx.showModal({
      title: '',
      content: '取消订阅?',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定');
          console.log(cancelId);
          console.log(cancelIsbn);
          wx.request({
            url: 'https://www.kyssky.party:8443/weixin/BookQUxiao',
            data: {
              ISBN: cancelIsbn,
              phonenumber: phoneNumber,
              ID: cancelId,
              statue: status
            },
            success: function (res) {
              console.log(res.data);
              if (res.data.statusCode == '0') {
                wx.showToast({
                  title: '取消预定成功',
                })

                var newBooks = that.data.books;
                that.onShow();

                if (that.data.books.length == 0) {
                  //显示您目前没有订阅
                  that.setData({
                    display: 'block'
                  });
                }
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
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  }
})