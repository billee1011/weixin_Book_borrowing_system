// pages/qrCode/qrCode.js
function Base64() {

  // private property 
  var _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

  // public method for encoding 
  this.encode = function (input) {
    var output = "";
    var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
    var i = 0;
    input = _utf8_encode(input);
    while (i < input.length) {
      chr1 = input.charCodeAt(i++);
      chr2 = input.charCodeAt(i++);
      chr3 = input.charCodeAt(i++);
      enc1 = chr1 >> 2;
      enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
      enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
      enc4 = chr3 & 63;
      if (isNaN(chr2)) {
        enc3 = enc4 = 64;
      } else if (isNaN(chr3)) {
        enc4 = 64;
      }
      output = output +
        _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
        _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
    }
    return output;
  }

  // public method for decoding 
  this.decode = function (input) {
    var output = "";
    var chr1, chr2, chr3;
    var enc1, enc2, enc3, enc4;
    var i = 0;
    input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
    while (i < input.length) {
      enc1 = _keyStr.indexOf(input.charAt(i++));
      enc2 = _keyStr.indexOf(input.charAt(i++));
      enc3 = _keyStr.indexOf(input.charAt(i++));
      enc4 = _keyStr.indexOf(input.charAt(i++));
      chr1 = (enc1 << 2) | (enc2 >> 4);
      chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
      chr3 = ((enc3 & 3) << 6) | enc4;
      output = output + String.fromCharCode(chr1);
      if (enc3 != 64) {
        output = output + String.fromCharCode(chr2);
      }
      if (enc4 != 64) {
        output = output + String.fromCharCode(chr3);
      }
    }
    output = _utf8_decode(output);
    return output;
  }

  // private method for UTF-8 encoding 
  var _utf8_encode = function (string) {
    string = string.replace(/\r\n/g, "\n");
    var utftext = "";
    for (var n = 0; n < string.length; n++) {
      var c = string.charCodeAt(n);
      if (c < 128) {
        utftext += String.fromCharCode(c);
      } else if ((c > 127) && (c < 2048)) {
        utftext += String.fromCharCode((c >> 6) | 192);
        utftext += String.fromCharCode((c & 63) | 128);
      } else {
        utftext += String.fromCharCode((c >> 12) | 224);
        utftext += String.fromCharCode(((c >> 6) & 63) | 128);
        utftext += String.fromCharCode((c & 63) | 128);
      }

    }
    return utftext;
  }

}

Page({

  /**
   * 页面的初始数据
   */
  data: {
    src: "http://qr.topscan.com/api.php?text=",
    info: "",
    title: "",
    a:'',
    timer:'',
    //选中的书的ID串   "110,111,112"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    console.log(e.title);
    console.log(e.bookIds);
    wx.setNavigationBarTitle({
      title: e.title,
    })

    this.setData({ info: e.bookIds, title: e.title });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function (e) {
    var that =  this;
    var timestamp;
    //借书要封装的连接
    var url = "https://www.kyssky.party:8443/weixin/SelectISInTime";
    //还书要封装的连接
    var url2 = "https://www.kyssky.party:8443/weixin/huanshu";
    var newSrc = "";
    var info = this.data.info;//串
    var phonenumber = getApp().data.phonenumber;
    var infoArr = info.split(',');
    console.log(infoArr);
    var s = "";
    for (var i = 0; i < infoArr.length; i++) {
      if (i == infoArr.length - 1) {
        s += infoArr[i];
      } else {
        s += infoArr[i] + ',';
      }
    }
    timestamp = Date.parse(new Date());
    info = "phonenumber:" + phonenumber + ";book:" + s + ";time:" + timestamp;
    console.log(info);
    var base = new Base64();
    var result = base.encode(info);
    getApp().data.result = result;
    console.log(result);
    if (this.data.title == "我的借书二维码"){
      newSrc = this.data.src + url + "?text=" + result;
      console.log(newSrc);
      this.setData({
        src: newSrc
      });
    }else{
      newSrc = this.data.src + url2 + "?text=" + result;
      console.log(newSrc);
      this.setData({
        src: newSrc
      });
    }
    //轮询
    var flag = 0;
       if (this.data.title == "我的借书二维码") {
        var x = 0;
  that.data.a = setInterval(function () { 
          x++;
          if(flag==1){
            console.log('服务器未返回信息');
          }

          if(flag==0){
            flag =1;

            wx.request({
              url: "https://www.kyssky.party:8443/weixin/I_weixin_canpay",
              data: {
                // phonenumber: phonenumber
                text:result
              },
              
              success: function (res) {
                console.log(res.data);
                flag = 0;
                if (res.data.statusCode == '0') { //对方扫描成功
                  //跳转到支付界面
                  wx.navigateTo({
                    url: '../pay/pay?infoArr='+infoArr,
                  })

                  clearInterval(that.data.a);
                  console.log('扫描成功,跳转到支付界面');
                } else if (res.data.statusCode == '1') {
                  //对方未扫描成功
                  console.log('对方未点击确定');
                  if (x % 60 == 0) {
                    //更新界面
                    
                    // var newtimestamp = Date.parse(new Date());
                    // info = "phonenumber:" + phonenumber + ";book:" + s + ";time:" + newtimestamp;
                    // result = base.encode(info);

                    // newSrc = this.data.src + url + "?text=" + result;
                    // this.setData({
                    //   src: newSrc
                    // });
                   
                          that.onLoad(e);
                          that.onReady(e);
                      
                    
                   

                  }
                }
                
              },
              fail: function () {
                console.log('调用request失败');
                flag2=0;

              }
            })
          }
  }, 1000);

      } else {

        //还书二维码

         var flag2 = 0;
         var y = 0;
  that.data.timer = setInterval(function(){
          y++;
          if(flag2==1){
            console.log('服务器未返回信息');
          }

          if(flag2==0){
            flag2=1;
            wx.request({
              url: 'https://www.kyssky.party:8443/weixin/huanshulunxuforUser',
              data: {
                
                text:result
              },
              success: function(res){
                console.log('还书返回的res.statusCode:'+res.data.statusCode);
                if(res.data.statusCode=='0'){
                  wx.showToast({
                    title: '还书成功',
                  })
                  clearInterval(that.data.timer);


                  wx.redirectTo({
                    url: '../repayBook/repayBook'
                  })


                } else if (res.data.statusCode == '1') {

                  //对方未扫描成功
                  console.log('未扫描成功');
                  if (y % 60 == 0) {
                    //更新界面

                    // var newtimestamp = Date.parse(new Date());
                    // info = "phonenumber:" + phonenumber + ";book:" + s + ";time:" + newtimestamp;
                    // result = base.encode(info);

                    // newSrc = this.data.src + url2 + "?text=" + result;
                    // this.setData({
                    //   src: newSrc
                    // });


                    that.onLoad(e);
                    that.onReady(e);
                  }
                }
                flag2=0;
              }
            })
          }

         },1000);

      }
    
  },

  /**rnunr 
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    this.onUnload();
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    if (this.data.timer==''){

    }else{
      clearInterval(this.data.timer);
    }
    if (this.data.a==''){

    }else{
      clearInterval(this.data.a);
    }
   
   
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