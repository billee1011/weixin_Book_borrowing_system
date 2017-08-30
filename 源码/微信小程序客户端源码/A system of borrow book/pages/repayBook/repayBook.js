// pages/repayBook/repayBook.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    display: 'none',
    repayBooks: [],
    repayBookIdArr: [],
    disabled: true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {

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
    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/GetJOSNJIEYUELE',
      data: {
        phonenumber: getApp().data.phonenumber
      },
      success: function (res) {
        if (res.data.simplebookinfo.length == 0) {
          console.log('0000');
          that.setData({
            display: 'block'
          })
        } else{
          console.log('1111');
          for (var i = 0; i < res.data.simplebookinfo.length;i++){

            console.log(i);
            console.log(res.data.simplebookinfo[i].bookname);
            if (res.data.simplebookinfo[i].bookname.length>30){
              res.data.simplebookinfo[i].bookname = res.data.simplebookinfo[i].bookname.slice(0, 29) + '...';
              that.setData({
                repayBooks: res.data.simplebookinfo
              });
            } else{
              that.setData({
                repayBooks: res.data.simplebookinfo
              });
            }
          }



          // that.setData({
          //   repayBooks: res.data.simplebookinfo
          // });
        }

       
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
  checkboxChange: function (e) {
    if (e.detail.value.length < 1) {
      this.setData({
        disabled: true
      });
    } else {
      this.setData({
        disabled: false,
        repayBookIdArr: e.detail.value
      });
    }
    console.log(e.detail.value);
  },
  createRepayQrCode: function () {
    var books = this.data.repayBookIdArr.join(',');
    var title = "我的还书二维码";
    wx.navigateTo({
      url: '../qrCode/qrCode?title=' + title + '&bookIds=' + books,
    })
  },
  borrowAgainBtn: function (e) {
    var that = this;
    var isbn = e.currentTarget.dataset.isbn;
    var id = e.currentTarget.dataset.id;
    console.log(e.currentTarget.dataset.isbn + e.currentTarget.dataset.id);
    var phonenumber = getApp().data.phonenumber;
    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/BookForXujie',
      data: {
        ISBN: isbn,
        ID: id,
        phonenumber: phonenumber
      },
      success: function (res) {
        if (res.data.statusCode == '0') {
          wx.showToast({
            title: '续借成功',
          })
          that.onLoad();
        } else {
          wx.showToast({
            image: '../../images/timg.png',
            title: '续借失败 只能续借一次',
          })
          that.onLoad();
        }
      }
    })

  },
  gotoInfo: function(e){
    var isbn = e.currentTarget.dataset.isbn;
    var id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '../bookinfo/bookinfo?bookisbnandid='+isbn+id,
    })
    console.log('跳转到书籍详情页');
  }
})