// pages/class/class/create_class_single/create_class_single.js
var app = getApp()
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		courseName: "请选择课程",
		courseId: "",
		date: "2018-12-21",
		time: "12:32",
		classroom: ""
	},
	bindCourseChange: function (e) {
		console.log("课堂表单改变，内容为", e.detail.value)
		this.setData({ courseName: e.detail.value })
	},
	bindDateChange: function (e) {
		console.log("日期表单改变，内容为", e.detail.value)
		this.setData({ date: e.detail.value })
	},
	bindTimeChange: function (e) {
		console.log("时间表单改变，内容为", e.detail.value)
		this.setData({ time: e.detail.value })
	},
	chooseCourse: function (e) {
		wx.navigateTo({
			url: '/pages/class/class/create_class_single/choose_course/choose_course',
		})
	},
	/**
	 * 点击添加课堂
	 */
	formSubmit: function (e) {
		// 将表单中的classroom赋值给this
		this.setData({
			classroom: e.detail.value.classroom
		})
		// 检测表单是否完善
		var flag = true
		var title = ""
		// 判断课程名是否填写
		if (this.data.courseName == "请选择课程") {
			flag = false
			var title = "请完善课程名"
		}
		if (this.data.classroom == "") {
			flag = false
			var title = "请指定上课教室"
		}
		// 发送请求
		if(flag){
			wx.request({
				url: app.globalData.requestUrl + '/teacher/CreateClass',
				data: {
					courseid: this.data.courseId,
					classroom: this.data.classroom,
					classtime: this.data.date + " " + this.data.time
				},
				header: {
					'content-type': 'application/json'
				},
				success: function (res) {

				}
			})
		} else {
			wx.showToast({
				title: title,
			})
		}
		wx.removeStorage({
			key: 'COURSENAME',
			success: function(res) {},
		})
		wx.removeStorage({
			key: 'COURSEID',
			success: function(res) {},
		})
		

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
		this.setData({
			courseName: "请选择课程"
		})
	},

	/**
	 * 生命周期函数--监听页面显示
	 */
	onShow: function () {
		var that = this
		wx.getStorage({
			key: 'COURSENAME',
			success: function (res) {
				that.setData({
					courseName: res.data
				})
			},
		})
		wx.getStorage({
			key: 'COURSEID',
			success: function (res) {
				that.setData({
					courseId: res.data
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
