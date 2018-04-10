// pages/index/register/other_info/other_detail/other_detail.js
var app = getApp()
Page({
	title: "",	// 页面标题
	value: "",	// 
	/**
	 * 页面的初始数据
	 */
	data: {
		keyboardType: "text",
		inputValue: "",
	},
	/**
	 * 清除输入框
	 */
	clearInput: function () {
		this.setData({
			inputValue: ""
		})
	},
	/**
	 * 点击取消
	 */
	cancel: function () {
		// 直接返回上个页面
		wx.navigateBack({
			url: '/pages/index/register/other_info/other_info',
		})
	},
	/**
	 * 点击完成
	 */
	ok: function (e) {

		// 页面退出时，将表单的内容写入内存
		wx.setStorage({
			key: this.title,
			data: e.detail.value.value,
		})
		wx.navigateBack({
			url: '/pages/index/register/other_info/other_info',
		})


	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		// 获取页面标题并且设置
		this.title = options.setting
		wx.setNavigationBarTitle({
			title: options.setting,
		})
		// 获取输入类型，并且弹出相应的键盘
		this.setData({
			keyboardType: options.type
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
		// 根据标题获取缓存中的值，直接写到表单中
		var that = this
		wx.getStorage({
			key: this.title,
			success: function (res) {
				that.setData({
					inputValue: res.data
				})
			},
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