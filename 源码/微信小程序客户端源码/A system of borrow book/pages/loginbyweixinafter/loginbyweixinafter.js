// loginbyweixinafter.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
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
  gotoregister:function(){
   //跳转到注册界面
    wx.navigateTo({
      url: '../register/register'
    })
  },
  gotobind:function(){
    //跳转到绑定账号页面
    wx.navigateTo({
      url: '../bindaccount/bindaccount'
    })
  },
  gotoaccountlogin:function(){
    //跳转到登陆界面
    wx.navigateTo({
      url: '../accountlogin/accountlogin'
    })
  },
  gotofirstpage:function(){
    wx.switchTab({
      url: '../firstpage/firstpage'
    })
  }
})