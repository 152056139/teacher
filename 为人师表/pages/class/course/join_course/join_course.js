// pages/add_course/add_course.js
var app = getApp()
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		course: "",
	},
	join: function (e) {
		console.log(e)
		wx.request({
			url: app.globalData.requestUrl + '/teacher/JoinCourse',
			data: {
				courseid: e.currentTarget.dataset.courseid,
				userid: wx.getStorageSync("USERID")
			},
			header: {
				'content-type': 'application/json;charset=utf-8'
			},
			success: function (res) {
				if (res.data != '') {
					wx.navigateBack({

					})
				}
			}
		})
	},
	search: function (e) {
		console.log(e)
		var that = this
		if (e.detail.value != "") {
			wx.request({
				url: app.globalData.requestUrl + '/teacher/SearchCourse',
				data: {
					conditions: e.detail.value,
					userid: wx.getStorageSync("USERID")
				},
				method: 'POST',
				header: {
					'content-type': 'application/x-www-form-urlencoded;charset=utf-8'
				},
				success: function (res) {
					console.log(res)
					that.setData({
						course: res.data
					})
				}
			})
		} else {
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