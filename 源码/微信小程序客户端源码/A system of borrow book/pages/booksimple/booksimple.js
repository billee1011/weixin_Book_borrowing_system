// pages/booksimple/booksimple.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  flag:false,
  book:[],
  isbn:"",
  id:"",
  s:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {

    if (e.bookisbnandid) {
      this.setData({
        isbn: e.bookisbnandid.substring(0, e.bookisbnandid.length - 1),
        id: e.bookisbnandid[e.bookisbnandid.length - 1],
        s:e.s
      });
    }else{
      this.setData({
        isbn: e.isbn,
        id: e.id,
        s: e.s
      });
    }

    var that = this;

    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/SelectBook',

      data: {
        info: that.data.isbn
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function( res ){
       
        if (res.data.book.bookname.length > 19) {
          res.data.book.bookname = res.data.book.bookname.slice(0, 19) + "...";
          that.setData({
            book: res.data.book,
            flag: true
          });
        }else{
          that.setData({
            book: res.data.book,
            flag: true
          });
        }
        console.log(that.data.book.bookurl);
      }
    })
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
  goBorrow: function () {
    var phone = getApp().data.phonenumber;
    var username = getApp().data.username;
    var that = this;

      wx.request({
        url: 'https://www.kyssky.party:8443/weixin/SelectBookCanBorrowORBook',
        data: {
          phonenumber: phone,
          ISBN: that.data.isbn,
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
                    url: 'https://www.kyssky.party:8443/weixin/BookFromBookToBorrow',
                    data: {
                      ISBN: that.data.isbn,
                      phonenumber: phone,
                      ID: that.data.id 
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

                      //把该书从预定列表中删除

                    }
                  })
                }
              }
            })
          }  else {
            wx.showToast({
              title: '该书不可借阅',
            })
          }
        }
      })
    },
  cancel: function (e) {
    var status = 'B';
    // console.log(e.currentTarget.dataset);
    var phoneNumber = getApp().data.phonenumber;
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
              }
            }
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },
  cancelFromBorrow: function(e){
    var that = this;
    var status = 'C';
    wx.showModal({
      title: '提示',
      content: '确定从借书栏中移除此书?',
      success: function( res ){
        if(res.confirm){
          console.log('用户点击了确定');

          wx.request({
            url: 'https://www.kyssky.party:8443/weixin/BookQUxiao',
            data: {
              ISBN: that.data.isbn,
              phonenumber: getApp().data.phonenumber,
              ID: that.data.id,
              statue: status
            },
            success: function (res) {
              if (res.data.statusCode == '0') {
                wx.showToast({
                  title: '删除成功',
                })
              }
            }
          })
        }
      }
    })
  }
})