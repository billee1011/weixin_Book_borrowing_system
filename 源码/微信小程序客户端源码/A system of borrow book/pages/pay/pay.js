// pages/pay/pay.js


//倒计时2分钟



Page({

  /**
   * 页面的初始数据
   */
  data: {
    phonenumber: "",
    display: 'block',
    infoArr: [],
    display2:'none',
    result:"",
    delay:'block',
    sec:120,
    txt:'确认支付',
    disabled: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    console.log(e.infoArr);
    var that = this;
    this.setData({
      phonenumber: getApp().data.phonenumber,
      infoArr: e.infoArr.split(','),
      result: getApp().data.result
    });
    
    var timer = setInterval(function(){
      var newsec = that.data.sec-1;

      that.setData({
        sec: newsec
      });

      if(newsec==0){
        clearInterval(timer);
        that.setData({
          txt:'支付超时!请返回借书栏重新生成二维码',
          disabled: true
        });
      }
    },1000);
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
    this.setData({
      phonenumber: getApp().data.phonenumber
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

  confirm: function () {
    var that = this;    
    this.setData({
      delay:'block'
    });
    console.log('确认支付');
    wx.request({
      url: 'https://www.kyssky.party:8443/weixin/I_weixin_ISpay',
      data: {
        // phonenumber: getApp().data.phonenumber
        text: that.data.result
      },
      success: function () {
        console.log('支付完成');
        wx.showToast({
          title: '支付成功',
        })
        that.setData({
          display: 'none',
          display2:'block',
          delay:'block'
        })
        
      }
    })
  }
})