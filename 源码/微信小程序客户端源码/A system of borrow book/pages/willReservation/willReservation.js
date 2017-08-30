// pages/willReservation/willReservation.js
Page({
  data: {
    src: "",
    bookName: "",
    author: "",
    bookId: "",
    isbn: "",
    title: "",
    explain: "",
    sign: "",
    type: "",
    sucTxt: "",
    url: "",
    btnTxt: "",
    background: "#8E7055",
    openType: "navigate"
  },
  onLoad: function (e) {
    // 页面初始化 options为页面跳转所带来的参数
    console.log(e.sign);
    var isbn = e.isbn;
    var phone = getApp().data.phonenumber;
    this.setData({
      isbn: e.isbn,
      src: "https://www.kyssky.party:8443/weixin/" + e.src,
      author: e.author,
      bookName: e.name
    });
    if (e.sign == 'B') {
      this.setData({
        title: "预定",
        explain: "即日起,预定期限为7天",
        sign: e.sign,
        url: "../Reservation_list/Reservation_list",
        btnTxt: "查看我的预定列表"
      });
    } else if (e.sign == 'C') {
      this.setData({
        title: " 添加借书栏",
        explain: "即日起,借书栏存放时间为7天",
        sign: e.sign,
        url: "../myBorrow/myBorrow",
        btnTxt: "查看我的借书栏",
        openType: "switchTab"
      });
    } else {

    }

    var that = this;
    wx.setNavigationBarTitle({
      title: that.data.title,
    })

    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/SelecctBookOrBorrowBook',
      data: {
        phonenumber: phone,
        ISBN: isbn,
        statue: that.data.sign
      },
      success: function (res) {
        //把  src  bookName   author  isbn id 添加至js
        console.log(res.data);
        that.setData({
          bookId: res.data.bookid,
          type: 'success',
          sucTxt: that.data.title + "成功",
        });
      }
    })


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
  }
})