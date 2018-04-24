// pages/index/mine/mine.js
var app = getApp()
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		arraySex: ['男', '女'],
		indexSex: 0,
		arrayIdentity: ['学生', '老师'],
		indexIdentity: 0,
		date: '2018-04-01',
		isMine: false,
	},
	bindDateChange: function (e) {
		console.log(e.detail.value)
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		this.setData({ url: app.globalData.requestUrl + "/teacher/upload/" })
		console.log(options)
		if(options.isMine == "true"){
			this.setData({ isMine: true})
		}
		var that = this
		wx.request({
			url: app.globalData.requestUrl + '/teacher/GetMyResume',
			data: {
				userId: wx.getStorageSync("USERID")
			},
			header: {
				'content-type': 'application/json' // 默认值
			},
			success: function (res) {
				console.log(res)
				that.setData({
					userBirthday: res.data.userBirthday,
					userImage: res.data.userImage,
					userName: res.data.userName,
					userSchoolId: res.data.userSchoolId,
					userSex: res.data.userSex
				})
			}
		})
	},
	update: function(e) {
		wx.request({
			url: app.globalData.requestUrl + '/teacher/UpdateMyResume',
			data: {
				userId: wx.getStorageSync("USERID"),
				filepath: this.data.userImage,
				userSex: this.data.userSex,
				birthDay: this.data.userBirthday,
				userMail: "222222", 
				userPhone: "222222"
			},
			header: {
				'content-type': 'application/json' // 默认值
			},
			success: function (res) {
				console.log(res.data)
				if (res.data.STATU == "success"){
					wx.navigateBack({
						
					})
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