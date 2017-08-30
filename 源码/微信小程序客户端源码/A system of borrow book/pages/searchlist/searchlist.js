// searchlist.js
function countdown(that) {
  //计时器
  var second = that.data.second
  if (second == 0) {
    wx.request({
      // url: 'http://localhost:8080/weixin/selectBookNameList',
      url: 'https://www.kyssky.party:8443/weixin/selectBookNameList',
      data: {
        info: that.data.search_content
      },
      success: function (res) {
        if (that.data.search_content == '') {
          that.setData({ search_display: "none" });
          that.data.request = true;
        } else {
          // console.log(res.data);                  // 开发者传回的数据   
          var temp = res.data.bookNameList;
          that.setData({
            bookNameList: temp,
          });
          that.setData({ search_display: "block" });
          that.data.request = true;
        }

      },
      fail: function () {
        that.data.request = true;
      }
    })
    return;
  }
  var time = setTimeout(function () {
    that.setData({
      second: second - 1
    });
    countdown(that);
  }
    , 1000)
}
Page({

  /**
   * 页面的初始数据
   */
  data: {
    onload3:true,
    onload2: false,
    onload1: false,
    num:0,
    footer_condition:false,
    footer_img: '../../images/load-midc03.gif',
    footer_text: '努力加载中...',
    second: 1,
    request: true,
    search_content:'',
    search_display:'block',
    bookNameList:'',
    isConnected:true,
    book: [
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this
    var app=getApp()
    var book = that.data.book;
    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/selectSimpleBookInfo',
      data: {
        number: '0',
        info: app.temp.search_content
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        // console.log(res.data)
        if (res.data.simplebookinfo.length == 0){
          that.setData({ onload3: false, onload1:true})
        }else{
          // console.log(res.data.simplebookinfo);
          book = book.concat(res.data.simplebookinfo);
          // console.log(book);
          for (var i = that.data.book.length; i < book.length; i++) {
            if (book[i].bookname.length > 10) {
              book[i] = { bookurl: book[i].bookurl, bookname: book[i].bookname.slice(0, 13) + '...', author: book[i].author, id: i, press: book[i].press, counterpart: book[i].counterpart ,isbn:book[i].isbn};
            } else {
              book[i] = { bookurl: book[i].bookurl, bookname: book[i].bookname, author: book[i].author, id: i, press: book[i].press, counterpart: book[i].counterpart, isbn: book[i].isbn };
            }
          }
          // console.log(book);
          //that.setData({ footer_condition: false });
          that.setData({ book:book, search_content:app.temp.search_content, onload1:true, onload2:false,onload3:true});
        // console.log(that.data.book);
        }
      },
      fail: function (e) {
             that.setData({onload1:false,onload2:true})
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
  loading: function () {
    this.onLoad();
  },
  scan: function () {
    //扫一扫功能
    console.log('22222');
    wx.scanCode({
      onlyFromCamera: true,
      success: (res) => {
        console.log('11111111');
        console.log(res.result);
        var patrn = /^[0-9]{1,20}$/;
        if (!patrn.exec(res.result)) {
          wx.showModal({
            title: '提示',
            content: '您出示的二维码有错误',
          })
        } else {
          var bookisbnandid = res.result;
          wx.navigateTo({
            url: '../bookinfo/bookinfo?bookisbnandid=' + bookisbnandid,
          })
        }

      }
    })
  },
  find: function () {
    //查找书籍功能
    var that = this
    var app = getApp()
    this.setData({book:[]})
    var book = that.data.book;
    app.temp.search_content = this.data.search_content;
    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/selectSimpleBookInfo',
      data: {
        number: '0',
        info: app.temp.search_content
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        if (res.data.simplebookinfo.length == 0) {
          that.setData({ onload3: false })
        } else {
          // console.log(res.data.simplebookinfo);
          book = book.concat(res.data.simplebookinfo);
          // console.log(book);
          for (var i = that.data.book.length; i < book.length; i++) {
            if (book[i].bookname.length > 10) {
              book[i] = { bookurl: book[i].bookurl, bookname: book[i].bookname.slice(0, 13) + '...', author: book[i].author, id: i, press: book[i].press, counterpart: book[i].counterpart, isbn: book[i].isbn };
            } else {
              book[i] = { bookurl: book[i].bookurl, bookname: book[i].bookname, author: book[i].author, id: i, press: book[i].press, counterpart: book[i].counterpart, isbn: book[i].isbn};
            }
          }
          // console.log(book);
          //that.setData({ footer_condition: false });
          that.setData({ book, book,onload3:true, onload1: true, onload2: false  });
          // console.log(that.data.book);
        }
      },
      fail: function (e) {
        that.setData({ onload1: false, onload2: true })
      }
    })
  },
  booktap: function (e) {
    var that = this;
    var app = getApp();
    app.temp.isbn = e.currentTarget.id;
    wx.navigateTo({
      url: '../bookinfo/bookinfo'
    })

  },
  addSearCon: function (e) {
    // console.log(e);
    //点击相关搜索面板，搜索框中显示所点击的内容
    this.setData({ search_content: e.currentTarget.id });
    this.find();
  },
   search_focus: function () {
    if (this.data.search_content == '') {
      this.setData({ search_display: "none" });
    } else {
      this.setData({ search_display: "block" });
    }

  },
   search_blur: function () {
     this.setData({ search_display: "none" });
   },
   iptBook: function (e) {
     this.data.search_content= e.detail.value;
     //输入事件的发生
     //从服务器获取书籍名字
     var value = e.detail.value;
     var that = this;
     if (that.data.search_content == '') {
       that.setData({ search_display: "none" });
     }
     else {
       if (that.data.request == true) {
         that.data.request = false;
         wx.request({
           // url: 'http://localhost:8080/weixin/selectBookNameList',
           url: 'https://www.kyssky.party:8443/weixin/selectBookNameList',
           data: {
             info: that.data.search_content
           },
           success: function (res) {
             if (that.data.search_content == '') {
               that.setData({ search_display: "none" });
               that.data.request = true;
             } else {
               console.log(res.data);
               var temp = res.data.bookNameList;
               that.setData({
                 bookNameList: temp,
               });
               that.setData({ search_display: "block" });
               that.data.request = true;
             }
           },
           fail: function () {
             that.data.request = true;
           }
         })
       } else {
         that.setData({
           second: 1
         });
         countdown(that);
       }
     }
   },
   scrolltolower: function (e) {
     //上拉刷新
     var that = this;
     var app=getApp()
     if (this.data.footer_condition == true || this.data.footer_img=='') {
       if (this.data.footer_img == '') {
         this.setData({ footer_img: '', footer_text: '已经到底啦！', footer_condition: true })
       } else {
         this.setData({ footer_img: '../../images/load-midc03.gif', footer_text: '努力加载中...' })
       }
       return false;
     } else {
       var book = that.data.book;
       that.setData({ footer_condition: true });
       that.data.num++;
       wx.request({
         url: 'https://www.kyssky.party:8443/weixin/selectSimpleBookInfo',
         data: {
           number: that.data.num,
           info: app.temp.search_content
         },
         header: {
           'content-type': 'application/x-www-form-urlencoded'
         },
         success: function (res) {
           console.log(res.data.simplebookinfo);
           if (res.data.simplebookinfo.length<10){
             that.setData({ footer_img: '', footer_text: '已经到底啦！'})
           }
           book = book.concat(res.data.simplebookinfo);
          // console.log(book);
           for (var i = that.data.book.length; i < book.length; i++) {
             if (book[i].bookname.length > 10) {
               book[i] = { bookurl: book[i].bookurl, bookname: book[i].bookname.slice(0, 13) + '...', author: book[i].author, id: i, press: book[i].press, counterpart: book[i].counterpart};
             } else {
               book[i] = { bookurl: book[i].bookurl, bookname: book[i].bookname, author: book[i].author, id: i, press: book[i].press, counterpart: book[i].counterpart};
             }
           }
          //  console.log(book);
           that.setData({ footer_condition: false });
           that.setData({ book, book });
           // console.log(that.data.book);
         },
         fail: function (e) {
           console.log(e);
         }
       })


     }

   }
})