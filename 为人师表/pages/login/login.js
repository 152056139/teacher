// pages/login/login.js
var app = getApp()

const requestUrl = require('../../config').requestUrl
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
		// get info from page
		var username = e.detail.value.username
		var password = e.detail.value.password
		// check info
		if(username=="" || password==""){
			wx.showToast({
				title: '用户名或密码不能为空',
				icon: 'none',
				duration: 2000
			})
		} else {
			wx.request({
				url: requestUrl + '/teacher/Login',
				data: {
					username: username,
					password: password
				},
				header: {
					'content-type': 'application/json'
				},
				success: function (res) {
					console.log(res.data)
					var status = res.data['status']
					if (status == "success") {
						console.log("login success")
						wx.switchTab({
							url: '/pages/course/course'
						})
					} else if (status == "not found user") {
						console.log("not found user")
						wx.showToast({
							title: '没有该用户',
							icon: 'none',
							duration: 2000
						})
					} else if(status == "wrong password") {
						console.log("wrong password")
						wx.showToast({
							title: '密码错误',
							icon: 'none',
							duration: 2000
						})
					} else {
						console.log("login fail")
						wx.showToast({
							title: '登陆失败',
							icon: 'none',
							duration: 2000
						})
					}
				},
				fail: function (err) {
					console.log('请求失败' + err)
				}
			})
		}	
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
