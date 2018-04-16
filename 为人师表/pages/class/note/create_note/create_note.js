// pages/class/note/create_note/create_note.js
var app = getApp()
Page({
	/**
	 * 页面的初始数据
	 */
	data: {
		userId: "",
		tempFilePaths: "",
	},
	/**
	 * 点击提交表单按钮
	 */
	formSubmit: function (e) {
		var that = this
		console.log(e)
		wx.request({
			url: app.globalData.requestUrl + '/teacher/TakeNotes', 
			data: {
				notetext: e.detail.value.note,
				classid: 3,
				userid: e.detail.value.userId
			},
			success: function(res){
				console.log(res.data)
				console.log(that.data.tempFilePaths)
				for (var i = 0; i < that.data.tempFilePaths.length; i++){
					
					wx.uploadFile({
						url: app.globalData.requestUrl + '/teacher/TakeNotes',
						header: { "Content-Type": "multipart/form-data" },
						filePath: that.data.tempFilePaths[i],
						formData: {
							noteid: res.data.noteId
						},
						name: 'file',
						success: function (res) {
							console.log(res)
						}
					})
					
				}
			}
		})
		
	},
	chooseImage: function (e){
		var that = this
		wx.chooseImage({
			success: function(res) {
				that.setData({
					tempFilePaths: res.tempFilePaths
				})
			},
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		var that = this
		wx.getStorage({
			key: 'USERID',
			success: function (res) {
				that.setData({
					userId: res.data
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