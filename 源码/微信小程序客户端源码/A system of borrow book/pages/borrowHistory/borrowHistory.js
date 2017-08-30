// pages/borrowHistory/borrowHistory.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    flag: false,
    display:'none',
    borrowHistory: [
      {
        bookName: '第一本书',
        borrowTime: '2014-3-4',
        repayTime: '2017-4-5',
        'pos': '逸夫图书馆3楼东'
      },
      {
        bookName: '第二本书',
        borrowTime: '2014-3-4',
        repayTime: '2017-4-5',
        'pos': '逸夫图书馆3楼东'
      },
      {
        bookName: '第三本书',
        borrowTime: '2014-3-4',
        repayTime: '2017-4-5',
        'pos': '逸夫图书馆3楼东'
      },
      {
        bookName: '第四本书',
        borrowTime: '2014-3-4',
        repayTime: '2017-4-5',
        'pos': '逸夫图书馆3楼东'
      }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/SelectBookForHistory',
      data: {
        phonenumber: getApp().data.phonenumber
      },
      success: function (res) {
        if (res.data.simplebookinfo.length==0){
          that.setData({
            display:'block'

          });
        }else{
          that.setData({
            borrowHistory: res.data.simplebookinfo,
            flag: true,
            display:'none'
          });
        }

       
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

  }
})