// pages/register/register.js
var app = getApp()
Page({
	/**
	 * 页面的初始数据
	 */
	data: {
		userName: ""
	},
	register: function (e) {
		wx.showLoading({
			title: '正在注册...',
		})
		console.log(e.detail.value);
		var username = e.detail.value.username
		var password = e.detail.value.password
		var repeatPassword = e.detail.value.repeadPassword

		if (username == "" || password == "" || repeatPassword == "") {
			wx.showToast({
				title: '用户名或密码不能为空',
				icon: 'none'
			})
		} else {
			if (password != repeatPassword) {
				wx.showToast({
					title: '两次输入密码不一致',
					icon: 'none'
				})
			} else {
				wx.request({
					url: app.globalData.requestUrl + '/teacher/Register',
					data: {
						username: username,
						password: password,
						flag:"base"
					},
					header: {
						'content-type': 'application/json'
					},
					success: function (res) {
						wx.hideLoading();

						console.log(res.data)
						var status = res.data.status

						if (status == 'userNamExist') {
							wx.showModal({
								title: '注册失败',
								content: '该用户名已经被使用，请更换用户名后重试。',
								success: function (res) {
									if (res.confirm) {
										console.log('用户点击确定')
									} else if (res.cancel) {
										console.log('用户点击取消')
									}
								}
							})
						} else if (status == 'RegisterSuccess') {
							wx.reLaunch({
								url: '/pages/index/register/choose_identity/choose_identity'
							})
						} else {
							wx.showModal({
								title: '注册失败',
								content: '注册失败，请检查用户名或密码中是否包含非法字符后重试。',
							})
						}
					},
					fail: function (err) {
						wx.hideLoading()
						wx.showModal({
							title: '注册失败',
							content: '请求失败，请检查网络后重试。',
						})
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
		wx.setNavigationBarTitle({
			title: '注册'
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