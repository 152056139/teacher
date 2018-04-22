// pages/class/work/new_work/new_work.js
var app = getApp()
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		dateStart: '',
		dateEnd: '',
		timeStart: '12:00',
		timeEnd: '12:00',
		isSame: true,
	},
	toDetail: function (e) {
		wx.navigateTo({
			url: '/pages/class/work/new_work/new_work_detail/new_work_detail',
		})
	},
	submit: function (e) {
		wx.request({
			url: app.globalData.requestUrl + '/teacher/CreateWorks',
			header: {
				'content-type': 'application/x-www-form-urlencoded' // 默认值
			},
			method: 'POST',
			data: {
				classId: wx.getStorageSync("CLASSID"),
				userId: wx.getStorageSync("USERID"),
				workTitle: e.detail.value.title,
				workDescription: e.detail.value.detail,
				workBeginTime: this.data.dateStart + ' ' + this.data.timeStart,
				workEndTime: this.data.dateEnd + ' ' + this.data.timeEnd
			},
			success: function (res) {
				if(res.statusCode == 200) {
					wx.navigateBack({
						delta: 1,
					})
				}
			}
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		// set date as system date
		var date = new Date();
		var month = date.getMonth() + 1;
		var day = date.getDate();
		if (month < 10) {
			month = '0' + month;
		}
		if (day < 10) {
			day = '0' + day;
		}
		this.setData({
			dateStart: date.getFullYear() + "-" + month + "-" + day,
			dateEnd: date.getFullYear() + "-" + month + "-" + day
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
		this.setData({
			detail: wx.getStorageSync("NEW_WORK_DETAIL")
		})
		wx.removeStorageSync("NEW_WORK_DETAIL")
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
	bindDateChangeStart: function (e) {
		this.setData({ dateStart: e.detail.value })
		if (this.data.dateStart == this.data.dateEnd) {
			this.setData({ isSame: true })
		}
	},
	bindDateChangeEnd: function (e) {
		this.setData({ dateEnd: e.detail.value })
		if (this.data.dateStart == this.data.dateEnd) {
			this.setData({ isSame: true })
		}
	},
	bindTimeChangeStart: function (e) {
		this.setData({ timeStart: e.detail.value })
	},
	bindTimeChangeEnd: function (e) {
		this.setData({ timeEnd: e.detail.value })
	}
})