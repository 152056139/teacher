// pages/work_detail/work_detail.js
var app = getApp()
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		isTeacher: "",
		isFinish: true,
	},
	toAnswer: function (e) {
		console.log(e)
		wx.navigateTo({
			url: '/pages/class/work/give_score/give_score?username=' + e.currentTarget.dataset.name,
		})
	},
	toFinish: function (e) {
		this.setData({
			isFinish: true,
			index: 0
		})
	},
	toUnFinish: function (e) {
		this.setData({
			isFinish: false,
			index: 1
		})
	},
	change: function (e) {
		console.log(e)
		if (e.detail.current == 0) {
			this.setData({ isFinish: true })
		} else {
			this.setData({ isFinish: false })
		}
	},
	submit: function (e) {
		console.log(e)
		wx.request({
			url: app.globalData.requestUrl + '/teacher/SumbitWorks',
			data: {
				userId: wx.getStorageSync("USERID"),
				workId: wx.getStorageSync("WORKID"),
				answer: e.detail.value.answer
			},
			header: {
				'content-type': 'application/json'
			},
			success: function (res) {
				if (res.data.STATU == 'success') {
					wx.navigateBack({

					})
				}
			}
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		var that = this
		if (wx.getStorageSync("USERIDENTITY") == 1) {
			this.setData({ isTeacher: true })
			wx.request({
				url: app.globalData.requestUrl + '/teacher/GetWhoWork',
				data: {
					classId: wx.getStorageSync("CLASSID"),
					workId: wx.getStorageSync("WORKID")
				},
				header: {
					'content-type': 'application/json'
				},
				success: function (res) {
					console.log(res.data[0])
					var data = res.data[0]
					that.setData({
						question: data.workDescription,
						title: data.workTitle,
						studentFinish: data.已完成,
						studentUnFinish: data.未完成
					})
				}
			})
		} else {
			this.setData({ isTeacher: false })
			wx.request({
				url: app.globalData.requestUrl + '/teacher/MakeMyWork',
				data: {
					workId: wx.getStorageSync("WORKID"),
					studentId: wx.getStorageSync("USERID")
				},
				header: {
					'content-type': 'application/json'
				},
				success: function (res) {
					console.log(res)
					that.setData({
						question: res.data.workDescription,
						title: res.data.workTitle,
						answer: res.data.answerContent
					})
				}
			})
		}

		
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