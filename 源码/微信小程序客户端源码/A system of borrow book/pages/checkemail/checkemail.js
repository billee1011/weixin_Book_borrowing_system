// checkemail.js
function countdown1(that) {
  //验证码有效期计时器
  var second = that.data.second_canCode
  if (second == 0) {
    that.setData({
      second_canCode: 600
    });
    that.data.backcode = -1;
    return;
  }
  var time = setTimeout(function () {
    that.data.second_canCode = second - 1;
    countdown1(that);
  }
    , 1000)
}
Page({

  /**
   * 页面的初始数据
   */
  data: {
    backcode:-1,
    second_canCode:600,
    email:'',
    code:'',
    flag:false
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
      var app=getApp();
       this.setData({email:app.temp.email})
       this.data.backcode = app.temp.backcode;
       countdown1(this);
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
  inputcode:function(e){
    this.setData({ flag: false });
    this.setData({code:e.detail.value})
  },
  checkcode:function(){
    var app=getApp();
    var that=this;
         if(this.data.code==''){
           this.setData({flag:true});
         }else{
            if(this.data.code==this.data.backcode){
              wx.request({
                url:'https://www.kyssky.party:8443/weixin/UpdateEmail',

                data: {
                  phone: app.data.phonenumber,
                  email: that.data.email
                },

                header: {
                  'content-type': 'application/x-www-form-urlencoded'
                },

                success: function (res) {
                    if(res.data.statuscode==4101){
                    //绑定成功
                      wx.showModal({
                        showCancel:false,
                        title: '提示',
                        content: '绑定成功',
                        success: function (res) {
                          if (res.confirm) {
                            wx.navigateBack({
                              delta: 2
                            })
                          } 
                        }
                      })
                    }else{
   //绑定失败       
                      wx.showToast({
                        title: '绑定邮箱失败',
                        image: '../../images/timg.png',
                        duration: 3000
                      })
                    }
                },

                fail: function () {
                  wx.showToast({
                    title: '网络异常',
                    image: '../../images/timg.png',
                    duration: 3000
                  })
                }

              })
            }
         }
  }
})