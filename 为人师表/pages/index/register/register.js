// pages/register/register.js
var app = getApp()
Page({
	/**
	 * 页面的初始数据
	 */
	data: {
		userName: "",
		user_icon: "/image/user-icon.png",
	},
	/**
	 * 点击选择头像
	 */
	chooseImage: function (e) {
		var that = this
		wx.chooseImage({
			success: function (res) {
				that.tempFilePaths = res.tempFilePaths
				that.setData({
					user_icon: res.tempFilePaths[0]
				})
			}
		})
	},
	/**
	 * 点击注册
	 */
	register: function (e) {
		// 显示loading
		wx.showLoading({
			title: '正在注册...',
		})
		// 获取表单信息
		var username = e.detail.value.username
		var password = e.detail.value.password
		var repeatPassword = e.detail.value.repeadPassword
		// 检测表单信息是否为空
		if (username == "" || password == "" || repeatPassword == "") {
			wx.showToast({
				title: '用户名或密码不能为空',
				icon: 'none'
			})
		} else {
			// 判断两次输入密码是否一致
			if (password != repeatPassword) {
				wx.showToast({
					title: '两次输入密码不一致',
					icon: 'none'
				})
			} else {
				// 提交表单
				wx.uploadFile({
					url: app.globalData.requestUrl + '/teacher/Register',
					filePath: this.tempFilePaths[0],
					header: { "Content-Type": "multipart/form-data" },
					name: 'file',
					formData: {
						username: username,
						password: password,
					},
					success: function (res) {
						console.log(res)
						// 成功后隐藏loading
						wx.hideLoading();

						// 解析res的data
						var dataStr = res.data.slice(0, res.data.length - 1)
						var data = JSON.parse(dataStr)




						// 获得用户id,存入全局变量，存入缓存
						var id = data.id
						app.globalData.userId = id
						wx.setStorage({
							key: 'USERID',
							data: id,
						})
						wx.setStorage({
							key: 'STATUS',
							data: 'login',
						})
						// 获得服务器返回的状态码
						var status = data.status
						// 用户名被占用
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
						}
						// 注册成功
						else if (status == 'RegisterSuccess') {
							wx.reLaunch({
								url: '/pages/index/register/choose_identity/choose_identity'
							})
						}
						// 服务器返回注册失败
						else {
							wx.showModal({
								title: '注册失败',
								content: '注册失败，请检查用户名或密码中是否包含非法字符，或联系管理人员检查系统是否崩溃。',
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