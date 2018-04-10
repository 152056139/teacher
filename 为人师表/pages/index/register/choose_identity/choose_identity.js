// pages/index/register/choose_identity/choose_identity.js
Page({
	/**
	 * 页面的初始数据
	 */
	data: {
		student: "inline",
		teacher: "none"
	},
	/**
	 * 点击下一步
	 */
	next: function () {
		// 判断时学生还是老师
		wx.getStorage({
			key: 'user_identity',
			success: function (res) {
				// 跳转到完善信息页面
				wx.navigateTo({
					url: '/pages/index/register/other_info/other_info?identity=' + res.data,
				})
			},
		})
	},
	chooseStudent: function () {
		if (this.data.student == "none") {
			// 改变界面的对号
			this.setData({
				student: "inline",
				teacher: "none"
			})
			// 将数据放入缓存中
			wx.setStorage({
				key: 'user_identity',
				data: '0'
			})
		}
	},
	chooseTeacher: function () {
		if (this.data.teacher == "none") {
			this.setData({
				teacher: "inline",
				student: "none"
			})
			// 将数据放入缓存中
			wx.setStorage({
				key: 'user_identity',
				data: '1'
			})
		}
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		wx.setNavigationBarTitle({
			title: '选择身份',
		})
		// 给缓存中放入默认用户身份
		wx.setStorage({
			key: 'user_identity',
			data: '0'
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