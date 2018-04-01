// pages/course/course.js
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		stars: [
			"grayStar", "grayStar", "grayStar", "grayStar", "grayStar"
		]
	},
	score: function (e) {
		console.log(e);
		var targetId = e.target.id

		for(var i = targetId; i >= 0; i--){
			this.data.stars[i] = "yellowStar"
		}
		for(var i = targetId; i <= 4; i++){
			this.data.stars[i] = "grayStar"
		}
		this.data.stars[targetId] = "yellowStar"
		console.log(this.data.stars)
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