var app = getApp()
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		// 性别
		arraySex: ['男', '女'],
		indexSex: 0,
		// 生日
		birthday: '1997-05-14',
		// 学号
		id: "",
		email: "",
		phone: ""
	},
	/**
	 * 改动性别
	 */
	bindSexChange: function (e) {
		console.log(e.detail.value)
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
			url: '/pages/index/register/other_info/other_detail/other_detail?setting=' + e.currentTarget.dataset.setting,
		})
	},
	/**
	 * 点击提交按钮
	 */
	submit: function (e) {
		wx.request({
			url: app.globalData.requestUrl + '/teacher/Register', 
			data: {
				sex: this.data.indexSex,
				birthday: this.data.birthday,
				schoolid: this.data.id,
				email: this.data.email,
				phone: this.data.phone
			},
			header: {
				'content-type': 'application/json' // 默认值
			},
			success: function (res) {
				console.log(res.data)
			}
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		wx.setNavigationBarTitle({
			title: '详细信息',
		})
		// 删掉缓存
		wx.removeStorage({
			key: '学号',
			success: function(res) {},
		})
		wx.removeStorage({
			key: '邮箱',
			success: function (res) { },
		})
		wx.removeStorage({
			key: '手机号',
			success: function (res) { },
		})
		// 性别默认男
		wx.setStorage({
			key: 'sex',
			data: '0',
		})
		// 生日默认
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
				console.log('other_info显示：学号为', res)
				that.setData({
					id: res.data
				})
			},
		})
		wx.getStorage({
			key: '邮箱',
			success: function (res) {
				console.log('other_info显示：邮箱为', res)
				that.setData({
					email: res.data
				})
			},
		})
		wx.getStorage({
			key: '手机号',
			success: function (res) {
				console.log('other_info显示：手机号为', res)
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