// firstpage.js
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
          console.log(res.data);                  // 开发者传回的数据   
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
    onload2: false,
    onload1: true,
    onload3: false,
    footer_img: '../../images/load-midc03.gif',
    footer_text: '努力加载中...',
    num: 0,
    second: 1,
    request: true,
    search_display: "none",
    bookNameList: [],
    search_content: "",
    isConnected: true,
    footer_condition: false,
    indicatorDots: true,
    autoplay: true,
    imgUrls: [
      '../../images/banner01.jpg',
      '../../images/banner02.jpg',
      '../../images/banner03.jpg'
    ],
    book: [
    ],
    kind1: [
      { kind1_text: '医学', kind1_images: 'cate/11.png' },
      { kind1_text: '历史', kind1_images: 'cate/13.png' },
      { kind1_text: '小说', kind1_images: 'cate/19.png' },
      { kind1_text: '生活', kind1_images: 'cate/39.png' },
      { kind1_text: '法律', kind1_images: 'cate/37.png' }
    ],
    kind2: [
      { kind2_text: '文学', kind2_images: 'cate/34.png' },
      { kind2_text: '教材', kind2_images: 'cate/30.png' },
      { kind2_text: '艺术', kind2_images: 'cate/49.png' },
      { kind2_text: '经济', kind2_images: 'cate/44.png' },
      { kind2_text: '全部', kind2_images: 'cate/12.png' }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var book = this.data.book;
    var that = this;
    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/SelectBookForHome',
      data: {
        number: this.data.num
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        book = book.concat(res.data.simplebookinfo);

        for (var i = that.data.book.length; i < book.length; i++) {
          if (book[i].bookname.length > 10) {
            book[i] = { bookurl: book[i].bookurl, bookname: book[i].bookname.slice(0, 13) + '...', author: book[i].author, id: i, isbn: book[i].isbn };
          } else {
            book[i] = { bookurl: book[i].bookurl, bookname: book[i].bookname, author: book[i].author, id: i, isbn: book[i].isbn };
          }
        }
        that.setData({ footer_condition: false });
        that.setData({ book, book, onload1: false, onload2: true, onload3: false });
        // console.log(that.data.book);
      },
      fail: function (e) {

        that.setData({ onload1: false, onload2: false, onload3: true })
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
  scan: function () {
    //扫一扫功能
    console.log('22222');
    wx.scanCode({
      onlyFromCamera: true,
      success: (res) => {
        console.log('11111111');
        console.log(res.result);
        var patrn = /^[0-9]{1,20}$/; 
        if(!patrn.exec(res.result)){
          wx.showModal({
            title: '提示',
            content: '您出示的二维码不是本馆书籍二维码',
          })
        }else{
          var bookisbnandid = res.result;
          wx.navigateTo({
            url: '../bookinfo/bookinfo?bookisbnandid=' + bookisbnandid,
          })
        }
       
      }
    })

 
  },
  search_focus: function () {
    if (this.data.search_content == '') {
      this.setData({ search_display: "none" });
    } else {
      this.setData({ search_display: "block" });
    }

  },
  loading: function () {
    this.onLoad();
  },
  find: function () {
    //查找书籍功能
    var app = getApp();
    app.temp.search_content = this.data.search_content
    if (this.data.search_content == '') {
      return false;
    } else {
      wx.navigateTo({
        url: '../searchlist/searchlist'
      })
    }

  },
  scrolltolower: function (e) {
    //上拉刷新
    var that = this;

    if (this.data.footer_condition == true || this.data.book.length > 100) {
      if (this.data.book.length > 100) {
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
        url: 'https://www.kyssky.party:8443/weixin/SelectBookForHome',
        data: {
          number: that.data.num
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
          book = book.concat(res.data.simplebookinfo);

          for (var i = that.data.book.length; i < book.length; i++) {
            if (book[i].bookname.length > 10) {
              book[i] = { bookurl: book[i].bookurl, bookname: book[i].bookname.slice(0, 13) + '...', author: book[i].author, id: i, isbn: book[i].isbn };
            } else {
              book[i] = { bookurl: book[i].bookurl, bookname: book[i].bookname, author: book[i].author, id: i, isbn: book[i].isbn };
            }
          }
          // console.log(book);
          that.setData({ footer_condition: false });
          that.setData({ book, book });
          // console.log(that.data.book);
        },
        fail: function (e) {
          console.log(e);
        }
      })


    }

  },
  iptBook: function (e) {
    this.setData({ search_content: e.detail.value });
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
  addSearCon: function (e) {
    console.log(e);
    //点击相关搜索面板，搜索框中显示所点击的内容
    this.setData({ search_content: e.currentTarget.id });
    this.find();
  },
  search_blur: function () {
    this.setData({ search_display: "none" });
  },




  kindtap: function (e) {
    //类别1点击事件
    var kindName = e.currentTarget.dataset.kindname;
    console.log(kindName);
    var that = this;
    wx.onNetworkStatusChange(function (res) {
      that.setData({ isConnected: res.isConnected });
    })
    if (that.data.isConnected) {
      // wx.request({
      //   url: 'https://localhost:8080/log/LoginServlet', //仅为示例，并非真实的接口地址
      //   data: {
      //     kindName: kindName,
      //   },
      //   header: {
      //     'content-type': 'application/json'
      //   },
      //   success: function (res) {
      //     //跳转界面，并在界面显示服务器返回的数据
      //      var url = "";
      //      if(kindName=="全部"){
      //         url = "../all/all";
      //      }else{
      //         url="../second_class/second_class";
      //     }
      //     wx.navigateTo({
      //       url: url
      //     })


      //   },
      //   fail: function () {
      //     wx.showToast({
      //       title: '连接服务器失败',
      //       image: '../../images/timg.png',
      //       duration: 3000
      //     })
      //   }
      // })

      var url = "";
      if (kindName == "全部") {
        url = "../all/all";
        wx.switchTab({
          url: url,
        })

      } else {
        url = "../second_class/second_class?secondTitle=" + kindName;
        wx.navigateTo({
          url: url
        })
      }

    } else {
      wx.showToast({
        title: '连接服务器失败',
        image: '../../images/timg.png',
        duration: 3000
      })
    }

  },
  booktap: function (e) {
    var that = this;
    var app = getApp();
    app.temp.isbn = e.currentTarget.id;
    wx.navigateTo({
      url: '../bookinfo/bookinfo'
    })
  }
})