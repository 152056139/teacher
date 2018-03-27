// pages/login/login.js
var app = getApp()
Page({

	/**
	 * 页面的初始数据
	 */
	data: {

	},
	/**
	 * 点击按钮登陆
	 */
	login: function(e) {
		console.log('form发生了submit事件，携带数据为：', e.detail.value)

		var username = e.detail.value.username
		var password = e.detail.value.password

		wx.request({
			url: 'http://192.168.1.107:8080/teacher/Login',
			data: {
				username: username,
				password: password
			},
			header: {
				'content-type': 'application/json' // 默认值
			},
			success: function (res) {
				console.log(res.data)
				
			},
			fail: function (err) {
				console.log('请求失败' + err)
			}
		})
	},
	redirectToRegister: function () {
		wx.navigateTo({
			url: '/pages/register/register'
		})
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

	}
})