// pages/class/class/create_class_single/choose_course/choose_course.js
var app = getApp()
Page({

	/**
	 * 页面的初始数据
	 */
	data: {

	},
	/**
	 * 点击确定选中某个课程
	 */
	submit: function (e) {
		console.log(e.currentTarget.dataset)
		wx.setStorage({
			key: 'COURSENAME',
			data: e.currentTarget.dataset.coursename,
		})
		wx.setStorage({
			key: 'COURSEID',
			data: e.currentTarget.dataset.courseid,
		})
		wx.navigateBack({})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		var that = this

		wx.getStorage({
			key: 'USERID',
			success: function (res) {
				wx.request({
					url: app.globalData.requestUrl + '/teacher/GetCourseList',
					data: {
						teacherid: res.data
					},
					header: {
						'content-type': 'application/json'
					},
					success: function (res) {
						console.log(res)
					}
				})
			},
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