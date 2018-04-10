// pages/index/register/other_info/other_detail/other_detail.js
var app = getApp()
Page({
	title: "",
	value: "",
	/**
	 * 页面的初始数据
	 */
	data: {

	},

	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		console.log(options)
		this.title = options.setting
		wx.setNavigationBarTitle({
			title: options.setting,
		})

	},
	cancel: function () {
		wx.navigateBack({

		})
	},
	ok: function (e) {
		console.log(e.detail.value)
		// 获取输入的文本
		this.value = e.detail.value.value
		
		wx.navigateBack({
			url: '/pages/index/register/other_info/other_info',
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
		// 存入缓存中
		wx.setStorage({
			key: this.title,
			data: this.value,
		})
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