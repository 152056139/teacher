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
	login: function (e) {
		var username
		var password
		var jscode

		// show loading
		wx.showLoading({
			title: '正在登陆...',
		})

		var that = this
		// 微信登录
		wx.login({
			success: function (res) {
				if (res.code) {
					// 获取表单数据并检测
					username = e.detail.value.username
					password = e.detail.value.password

					if (username == "" || password == "") {
						wx.showToast({
							title: '用户名或密码不能为空',
							icon: 'none',
							duration: 2000
						})
					} else {
						wx.request({
							url: app.globalData.requestUrl + '/teacher/Login',
							data: {
								username: username,
								password: password,
								jscode: res.code
							},
							header: {
								'content-type': 'application/json'
							},
							success: function (res) {
								console.log(res.data)

								// 获取服务器的返回信息
								var status = res.data['status']
								var id = res.data['id']

								if (status == "success") {
									console.log("login success")

									// 讲个人信息加载如全局变量
									app.globalData.userId = id

									wx.hideLoading()

									//跳转到主页
									wx.redirectTo({
										url: '/pages/index/index'
									})
								} else if (status == "not found user") {
									console.log("not found user")
									wx.showToast({
										title: '没有该用户',
										icon: 'none',
										duration: 2000
									})
								} else if (status == "wrong password") {
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
								wx.hideLoading()
								wx.showModal({
									title: '注册失败',
									content: '注册失败，请检查网络后重试。',
								})
								console.log('请求失败' + err)
							}
						})
					}
				} else {
					console.log('微信登录失败！' + res.errMsg)
				}
			}
		})
	},
	/**
	 * 跳转到注册页面
	 */
	redirectToRegister: function () {
		wx.navigateTo({
			url: '/pages/index/register/register'
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		wx.setNavigationBarTitle({
			title: '登陆',
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
