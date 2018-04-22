// pages/class/work/give_score/give_score.js
var app = getApp()
Page({
	/**
	 * 页面的初始数据
	 */
	data: {
		stars: ["grayStar", "grayStar", "grayStar", "grayStar", "grayStar"],
		score: "",
	},
	scoreSubmit: function(e) {
		var score = 0;
		for(var i = 0; i< 5; i++){
			if (this.data.stars[i] == 'yellowStar'){
				score = i;
			}
		}
		wx.request({
			url: app.globalData.requestUrl + '/teacher/GradeWorkAnswer',
			data: {
				studentName: this.data.username,
				workId: wx.getStorageSync("WORKID"),
				workScore: score
			},
			header: {
				'content-type': 'application/json'
			},
			success: function (res) {
				if(res.data.STATU=='success'){
					wx.navigateBack({
						delta: 1,
					})
				}
				
			}
		})
	},
	/**
	 * 分数发生改变
	 */
	score: function (e) {
		var targetId = e.target.id

		for (var i = targetId; i >= 0; i--) {
			this.data.stars[i] = "yellowStar"
		}
		for (var i = targetId; i <= 4; i++) {
			this.data.stars[i] = "grayStar"
		}
		this.data.stars[targetId] = "yellowStar"
		this.setData({
			'stars[0]': this.data.stars[0],
			'stars[1]': this.data.stars[1],
			'stars[2]': this.data.stars[2],
			'stars[3]': this.data.stars[3],
			'stars[4]': this.data.stars[4],

		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		this.setData({
			username: options.username
		})
		var that = this
		wx.request({
			url: app.globalData.requestUrl + '/teacher/EnterWorkAnswer',
			data: {
				studentName: options.username,
				workId: wx.getStorageSync("WORKID")
			},
			header: {
				'content-type': 'application/json'
			},
			success: function (res) {
				console.log(res)
				that.setData({
					answer: res.data.answerContent,
					score: res.data.answerScore
				})
				var targetId = res.data.answerScore
				for (var i = targetId; i >= 0; i--) {
					that.data.stars[i] = "yellowStar"
				}
				for (var i = targetId; i <= 4; i++) {
					that.data.stars[i] = "grayStar"
				}
				that.data.stars[targetId] = "yellowStar"
				that.setData({
					'stars[0]': that.data.stars[0],
					'stars[1]': that.data.stars[1],
					'stars[2]': that.data.stars[2],
					'stars[3]': that.data.stars[3],
					'stars[4]': that.data.stars[4],

				})
			}
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