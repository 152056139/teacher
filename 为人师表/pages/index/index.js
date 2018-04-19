// pages/course/course.js
var app = getApp()

Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		dateStr: "",
		display: "none",
		classes: "",
	},
	bindDateChange: function (e) {
		console.log(e)
		this.setData({
			dateStr: e.detail.value
		})
		var that = this
		wx.request({
			url: app.globalData.requestUrl + '/teacher/GetClass',
			data: {
				userid: wx.getStorageSync("USERID"),
				useridentity: wx.getStorageSync("USERIDENTITY"),
				date: this.data.dateStr
			},
			header: {
				'content-type': 'application/x-www-form-urlencoded'
			},
			method: "POST",
			success: function (res) {
				console.log(res)
				that.setData({
					classes: res.data
				})
			}
		})

	},
	/**
	 * 点击菜单
	 */
	menu: function () {
		wx.getStorage({
			key: 'USERIDENTITY',
			success: function (res) {
				if (res.data == 1) {
					wx.showActionSheet({
						itemList: ["创建课堂", "创建班课", "系统设置"],
						itemColor: '',
						success: function (res) {
							console.log(res.tapIndex)
							if (res.tapIndex == 0) {
								wx.navigateTo({
									url: '/pages/class/class/create_class_single/create_class_single',
								})
							} else if (res.tapIndex == 1) {
								wx.navigateTo({
									url: '/pages/class/course/create_course/create_course',
								})
							} else if (res.tapIndex == 2) {
								wx.navigateTo({
									url: 'system_setting/system_setting',
								})
							}
						}
					})
				} else if (res.data == 0) {
					wx.showActionSheet({
						itemList: ["加入班课", "系统设置"],
						itemColor: '',
						success: function (res) {
							console.log(res.tapIndex)
							if (res.tapIndex == 0) {
								wx.navigateTo({
									url: '/pages/class/course/join_course/join_course',
								})
							} else if (res.tapIndex == 1) {
								wx.navigateTo({
									url: 'system_setting/system_setting',
								})
							}
						}
					})
				}
			},
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		// 设置标题
		var date = new Date();
		this.setData({
			dateStr: this.printDate(date),
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
		var that = this
		wx.request({
			url: app.globalData.requestUrl + '/teacher/GetClass',
			data: {
				userid: wx.getStorageSync("USERID"),
				useridentity: wx.getStorageSync("USERIDENTITY"),
				date: this.data.dateStr
			},
			header: {
				'content-type': 'application/x-www-form-urlencoded'
			},
			method: "POST",
			success: function (res) {
				console.log(res)
				that.setData({
					classes: res.data
				})
			}
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

	},

	toMine: function () {
		wx.navigateTo({
			url: '/pages/index/mine/mine',
		})
	},
	toClass: function () {
		wx.switchTab({
			url: '/pages/class/class/class',
		})
	},
	printDate: function (date) {
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var date = date.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (date < 10) {
			date = "0" + date;
		}
		return year + "-" + month + "-" + date;
	},
})