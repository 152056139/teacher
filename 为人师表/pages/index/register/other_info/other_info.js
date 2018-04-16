var app = getApp()
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		textIdentity:"学号",
		arraySex: ['男', '女'],	// 性别
		indexSex: 0,	// 性别数组角标
		birthday: '1997-05-14',
		id: "",	// 学号
		email: "",
		phone: "",
		identity: "",
	},
	/**
	 * 改动性别
	 */
	bindSexChange: function (e) {
		// 0是男，1是女
		if (e.detail.value == 0) {
			this.setData({
				indexSex: 0
			})
			wx.setStorage({
				key: 'sex',
				data: '0',
			})
		} else {
			this.setData({
				indexSex: 1
			})
			wx.setStorage({
				key: 'sex',
				data: '1',
			})
		}
	},
	/**
	 * 改动时间
	 */
	bindDateChange: function (e) {
		this.setData({
			birthday: e.detail.value
		})
		wx.setStorage({
			key: 'birthday',
			data: e.detail.value,
		})
	},
	/**
	 * 点击详细设置页面
	 */
	toDetail: function (e) {
		wx.navigateTo({
			url: '/pages/index/register/other_info/other_detail/other_detail?setting=' + e.currentTarget.dataset.setting + '&type=' + e.currentTarget.dataset.type,
		})
	},
	/**
	 * 点击提交按钮
	 */
	submit: function (e) {
		// 判断信息的填入情况

		// 将其他信息发送给服务器
		var that = this
		console.log(that.data)
		wx.getStorage({
			key: 'user_identity',
			success: function (res) {
				that.setData({
					identity: res.data
				})
				wx.request({
					url: app.globalData.requestUrl + '/teacher/Register',
					data: {
						flag: "other",
						sex: that.data.indexSex,
						birthday: that.data.birthday,
						schoolid: that.data.id,
						email: that.data.email,
						phone: that.data.phone,
						identity: that.data.identity,
						id: app.globalData.userId
					},
					header: {
						'content-type': 'application/json' 
					},
					success: function (res) {
						console.log(res.data)
						wx.setStorage({
							key: 'USERIDENTITY',
							data: that.data.identity,
						})
					}
				})
			},
		})
		wx.reLaunch({
			url: '/pages/index/index',
		})
	},
	/**
	 * 点击跳过
	 */
	skip: function () {
		var that = this
		wx.getStorage({
			key: 'user_inentity',
			success: function (res) {
				wx.request({
					url: app.globalData.requestUrl + '/teacher/Register',
					data: {
						flag: "onlyIdentity",
						identity: res.data,
						id: app.globalData.userId
					},
					header: {
						'content-type': 'application/json' // 默认值
					},
					success: function (res) {
						console.log(res.data)
						wx.setStorage({
							key: 'USERIDENTITY',
							data: that.data.identity,
						})
					}
				})
			},
		})
		wx.reLaunch({
			url: '/pages/index/index',
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		// 设置显示学号还是工号
		if(options.identity=='0'){
			this.setData({
				textIdentity:"学号"
			})
		}else if(options.identity=='1'){
			this.setData({
				textIdentity: "工号"
			})
		}
		// 设置标题信息
		wx.setNavigationBarTitle({
			title: '详细信息',
		})

		// 加载页面时默认填充性别和生日
		wx.setStorage({
			key: 'sex',
			data: '0',
		})
		wx.setStorage({
			key: 'birthday',
			data: this.data.birthday,
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
	onShow: function (options) {
		var that = this
		wx.getStorage({
			key: '学号',
			success: function (res) {
				that.setData({
					id: res.data
				})
			},
		})
		wx.getStorage({
			key: '邮箱',
			success: function (res) {
				that.setData({
					email: res.data
				})
			},
		})
		wx.getStorage({
			key: '手机号',
			success: function (res) {
				that.setData({
					phone: res.data
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
		//页面卸载后删除缓存中的值
		wx.removeStorage({
			key: 'user_identity',
			success: function (res) { },
		})
		wx.removeStorage({
			key: 'sex',
			success: function (res) { },
		})
		wx.removeStorage({
			key: 'birthday',
			success: function (res) { },
		})
		wx.removeStorage({
			key: '学号',
			success: function (res) { },
		})
		wx.removeStorage({
			key: '邮箱',
			success: function (res) { },
		})
		wx.removeStorage({
			key: '手机号',
			success: function (res) { },
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