// pages/register/register.js
var app = getApp()
const requestUrl = require('../../config').requestUrl

Page({

	/**
	 * 页面的初始数据
	 */
	data: {

	},
	register: function (e) {
		console.log(e.detail.value);
		var username = e.detail.value.username
		var password = e.detail.value.password
		var repeatPassword = e.detail.value.repeadPassword

		if(username=="" || password=="" || repeatPassword ==""){
			wx.showToast({
				title: '用户名或密码不能为空',
				icon: 'none'
			})
		} else {
			if(password!=repeatPassword){
				wx.showToast({
					title: '两次输入密码不一致',
					icon: 'none'
				})
			} else {
				wx.request({
					url: requestUrl + '/teacher/Register',
					data: {
						username: username,
						password: password
					},
					header: {
						'content-type': 'application/json'
					},
					success: function (res) {
						console.log(res.data)
					},
					fail: function (err) {
						console.log('请求失败' + err)
					}
				})
			}
		}
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