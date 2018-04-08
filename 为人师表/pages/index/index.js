// pages/course/course.js
var app = getApp()
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		array: ['第一周', '第二周', '第三周', '第四周'],
		objectArray: [
			{
				id: 0,
				name: '第一周'
			},
			{
				id: 1,
				name: '第二周'
			},
			{
				id: 2,
				name: '第三周'
			},
			{
				id: 3,
				name: '第四周'
			}
		],
		index: 0,
		display: "none"
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
	menu: function(){
		if(this.data["display"]=="none"){
			this.setData({
				display: "inline",
			})
		}else {
			this.setData({
				display: "none",
			})
		}
	},
	toCreateCourse: function() {
		console.log("跳转到创建班科")
		wx.navigateTo({
			url: '/pages/class/course/create_course/create_course',
		})
	}
})