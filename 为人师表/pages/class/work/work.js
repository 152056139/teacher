// pages/work/work.js
var app = getApp()
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		display: "none",
		btnDisplay: "none", 
		works: "",
	},
	toDetail: function(e) {
		wx.setStorage({
			key: 'WORKID',
			data: e.currentTarget.dataset.id,
		})
		wx.navigateTo({
			url: '/pages/class/work/work_detail/work_detail',
		})
	},
	createWork: function(e) {
		this.setData({ display: "none" })
		wx.navigateTo({
			url: '/pages/class/work/new_work/new_work',
		})
	},
	toggerMenu: function (e) {
		if(this.data["display"] == "none"){
			this.setData({display: "flex"})
		} else {
			this.setData({display: "none" })
		}
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		if (wx.getStorageSync("USERIDENTITY") == 1){
			this.setData({ btnDisplay: "flex"})
		}
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
		var that = this
		if (wx.getStorageSync("USERIDENTITY")==1){
			this.setData({isTeacher : true})
		}else {
			this.setData({ isTeacher : false })
		}
		wx.request({
			url: app.globalData.requestUrl + '/teacher/GetWorkList',
			data: {
				userId: wx.getStorageSync("USERID"),
				classId: wx.getStorageSync("CLASSID"),
			},
			header: {
				'content-type': 'application/x-www-form-urlencoded'
			},
			method: "POST",
			success: function (res) {
				console.log(res)
				that.setData({
					works: res.data
				})
			}
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

	}
})