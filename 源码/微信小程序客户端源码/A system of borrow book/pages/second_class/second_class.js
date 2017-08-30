// pages/second_class/second_class.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    display:'block',
    header: "",
    title: "",
    navisTxt: [],
    books: [],
    pagenum:0,
    scrollTop:0,
    ziro:0,
    max:30
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    var pagenum = this.data.pagenum;
    if (e.secondTitle) {
      wx.setNavigationBarTitle({
        title: e.secondTitle
      })
      this.setData({
        title: e.secondTitle
      });
    }
    var title = this.data.title;
    var that = this;
    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/SelectByICON',
      data: {
        category: title
      },
      success: function (res) {
        console.log(res.data);
        if (res.data.listTitle) {
          that.setData({
            navisTxt: res.data.listTitle,
            header: res.data.listTitle[0],
            pagenum:0
          });

          wx.request({
            url: 'https://www.kyssky.party:8443/weixin/SelectBookByCategory',
            data: {
              info: that.data.header,
              number: pagenum
            },
            success: function (res) {
             console.log(res.data.simplebookinfo);

             for (var i = 0; i < res.data.simplebookinfo.length;i++){
               console.log(i);
               console.log(res.data.simplebookinfo[i].bookname);
               if (res.data.simplebookinfo[i].bookname.length>35){
                 res.data.simplebookinfo[i].bookname = res.data.simplebookinfo[i].bookname.slice(0,34)+'...';
                 console.log(res.data.simplebookinfo[i].bookname);
                 that.setData({
                   books: res.data.simplebookinfo
                 });           
               }else{
                 that.setData({
                   books: res.data.simplebookinfo
                 });
               }
             }
            }
          })
          pagenum++;
        } else {
          console.log("没有二级分类");
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    var that = this;
      wx.request({
        url: 'https://www.kyssky.party:8443/weixin/SelectBookByCategory',
        data: {
          info:that.data.header,
          number : that.data.pagenum
        },
        success: function( res ){
          for (var i = 0; i < res.data.simplebookinfo.length; i++) {
            console.log(i);
            console.log(res.data.simplebookinfo[i].bookname);
            if (res.data.simplebookinfo[i].bookname.length > 35) {
              res.data.simplebookinfo[i].bookname = res.data.simplebookinfo[i].bookname.slice(0, 34) + '...';
              console.log(res.data.simplebookinfo[i].bookname);
              that.setData({
                books: res.data.simplebookinfo
              });
            } else {
              that.setData({
                books: res.data.simplebookinfo
              });
            }
          }



        //  that.setData({
        //    books: res.data.simplebookinfo
        //  });
        }
      })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function (e) {

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
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  

  },
  changeHeader: function (e) {
    this.setData({
      header: e.target.dataset.header,  //动态改变小标题
      pagenum:0,
      scrollTop:0
    })
    this.onReady();
  },
  pullDownRefresh: function (e) {
    // console.log("下拉刷新....")
    // this.onLoad(e);
  },

  pullUpLoad: function (e) {
    this.setData({
      pagenum: this.data.pagenum + 1
    })
    console.log("上拉拉加载更多....");
    var that = this;
    wx.request({
          url: 'https://www.kyssky.party:8443/weixin/SelectBookByCategory',
          data: {
            info: that.data.header,
            number: that.data.pagenum
          },
        success: function(res){

          for (var i = 0; i < res.data.simplebookinfo.length; i++) {
           
            console.log(res.data.simplebookinfo[i].bookname);
            if (res.data.simplebookinfo[i].bookname.length > 35) {
              res.data.simplebookinfo[i].bookname = res.data.simplebookinfo[i].bookname.slice(0, 34) + '...';
              console.log(res.data.simplebookinfo[i].bookname);
             
            } 
          }

          that.setData({ books: that.data.books.concat(res.data.simplebookinfo)});
        }
    })
  },
  goBookInfo: function(e){
    var isbn  = e.currentTarget.dataset.isbn;
    getApp().temp.isbn = isbn;

    wx.navigateTo({
      url: '../bookinfo/bookinfo',
      success: function(){
        console.log("跳转到详情页成功");
      }
    })
  }
})