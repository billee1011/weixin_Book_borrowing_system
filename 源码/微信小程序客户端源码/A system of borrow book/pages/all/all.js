// pages/all/all.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    Imgsrc:"https://www.kyssky.party:8443/weixin/",   
      classes: [{
        "classSrc": "cate/00.png",
        "className": "两性关系"
      }, {
        "classSrc": "cate/01.png",
        "className": "中小学教辅"
      }, {
        "classSrc": "cate/03.png",
        "className": "亲子/家教"
      }, {
        "classSrc": "cate/04.png",
        "className": "休闲/爱好"
      }, {
        "classSrc": "cate/05.png",
        "className": "传记"
      }, {
        "classSrc": "cate/06.png",
        "className": "体育/运动"
      }, {
        "classSrc": "cate/07.png",
        "className": "保健/养生"
      }, {
        "classSrc": "cate/09.png",
        "className": "农业/林业"
      }, {
        "classSrc": "cate/10.png",
        "className": "动漫/幽默"
      }, {
        "classSrc": "cate/11.png",
        "className": "医学"
      }, {
        "classSrc": "cate/13.png",
        "className": "历史"
      }, {
        "classSrc": "cate/14.png",
        "className": "古籍"
      }, {
        "classSrc": "cate/15.png",
        "className": "哲学/宗教"
      }, {
        "classSrc": "cate/16.png",
        "className": "外语"
      }, {
        "classSrc": "cate/17.png",
        "className": "孕产/胎教"
      }, {
        "classSrc": "cate/18.png",
        "className": "家庭/家居"
      }, {
        "classSrc": "cate/19.png",
        "className": "小说"
      }, {
        "classSrc": "cate/22.png",
        "className": "工业技术"
      }, {
        "classSrc": "cate/23.png",
        "className": "工具书"
      }, {
        "classSrc": "cate/24.png",
        "className": "建筑"
      }, {
        "classSrc": "cate/25.png",
        "className": "心理学"
      }, {
        "classSrc": "cate/26.png",
        "className": "成功/励志"
      }, {
        "classSrc": "cate/27.png",
        "className": "手工/DIY"
      }, {
        "classSrc": "cate/28.png",
        "className": "投资理财"
      }, {
        "classSrc": "cate/29.png",
        "className": "政治/军事"
      }, {
        "classSrc": "cate/30.png",
        "className": "教材"
      }, {
        "classSrc": "cate/31.png",
        "className": "教材教辅"
      }, {
        "classSrc": "cate/32.png",
        "className": "教育"
      }, {
        "classSrc": "cate/33.png",
        "className": "文化"
      }, {
        "classSrc": "cate/34.png",
        "className": "文学"
      }, {
        "classSrc": "cate/35.png",
        "className": "旅游/地图"
      }, {
        "classSrc": "cate/36.png",
        "className": "时尚/美妆"
      }, {
        "classSrc": "cate/37.png",
        "className": "法律"
      }, {
        "classSrc": "cate/38.png",
        "className": "烹饪/美食"
      }, {
        "classSrc": "cate/40.png",
        "className": "社会科学"
      }, {
        "classSrc": "cate/41.png",
        "className": "科普读物"
      }, {
        "classSrc": "cate/42.png",
        "className": "童书"
      }, {
        "classSrc": "cate/43.png",
        "className": "管理"
      }, {
        "classSrc": "cate/44.png",
        "className": "经济"
      }, {
        "classSrc": "cate/45.png",
        "className": "网络课程"
      }, {
        "classSrc": "cate/46.png",
        "className": "考试"
      }, {
        "classSrc": "cate/47.png",
        "className": "育儿/早教"
      }, {
        "classSrc": "cate/48.png",
        "className": "自然科学"
      }, {
        "classSrc": "cate/49.png",
        "className": "艺术"
      }, {
        "classSrc": "cate/50.png",
        "className": "英文原版书"
      }, {
        "classSrc": "cate/51.png",
        "className": "计算机/网络"
      }, {
        "classSrc": "cate/52.png",
        "className": "青春文学"
      }, {
        "classSrc": "cate/33.png",
        "className": "文化"
      }]
    

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
    return {
      title: '图书分类',
      path: '/page/user?id=123',
      title: '我觉得很好，转发给你'
    }
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
  },
  goSecond:function(e){
     var title = e.currentTarget.dataset.title;
     wx.navigateTo({
    url: '../second_class/second_class?secondTitle='+title
    })
  }
})