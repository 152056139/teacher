// pages/create_course/create_course.js
var app = getApp()
Page({
	/**
	 * 页面的初始数据
	 */
	data: {

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

	},
	/**
	 * 创建班科
	 */
	createCourse: function (e) {
		console.log(e.detail.value)

		var courseName = e.detail.value.course_name

		wx.request({
			url: app.globalData.requestUrl+ '/teacher/CreateCourse', 
			data: {
				course_name: courseName,
				user_id: app.globalData.userId
			},
			header: {
				'content-type': 'application/json' // 默认值
			},
			success: function (res) {
				console.log(res.data)
				if (res.data.status== "success"){
					wx.navigateBack({
						
					})
				} else {
					wx.showToast({
						title: '创建课程失败',
						icon: 'none'
					})
				}
			},
			fail: function (err) {
				console.log("创建课程失败" + err)
			}
		})
	}
})