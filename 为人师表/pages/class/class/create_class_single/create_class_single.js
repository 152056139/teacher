// pages/class/class/create_class_single/create_class_single.js
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		courseName:"请选择课程",
		date: "2018-12-21",
		time: "12:32",
		classroom: "Z2012"
	},
	bindCourseChange: function (e) {
		console.log("课堂表单改变，内容为", e.detail.value)
		this.setData({ courseName:e.detail.value })
	},
	bindDateChange: function (e) {
		console.log("日期表单改变，内容为", e.detail.value)
		this.setData({ date: e.detail.value })
	},
	bindTimeChange: function (e) {
		console.log("时间表单改变，内容为", e.detail.value)
		this.setData({ time: e.detail.value })
	}, 
	chooseCourse: function (e){
		wx.navigateTo({
			url: '/pages/class/class/create_class_single/choose_course/choose_course',
		})
	},
	/**
	 * 点击添加课堂
	 */
	submit: function(e) {
		console.log("点击添加课堂，表单内容为",this.data)
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
