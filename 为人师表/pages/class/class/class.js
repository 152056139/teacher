// pages/course/course.js
var app = getApp()
var userIdentity = ""
Page({
	/**
	 * 页面的初始数据
	 */
	data: {
		imagePath: app.globalData.requestUrl+"/teacher/upload/",
		noteList:"",	// 笔记列表
		userIdentity: '',
		stars: ["grayStar", "grayStar", "grayStar", "grayStar", "grayStar"],	
		display: "none",	// 悬浮菜单是否显示
		blackboardDisabled: "true",	//黑板是否可编辑
		blackboardContent: "",	// 黑板的内容
		editDisplay: "flex", // 编辑按钮是否显示
		editBackground: "#f8f8f8",	// 编辑按钮的背景色
		editColor: "#000000", 	// 编辑按钮的前景色
		editText: "编辑",	// 编辑按钮上的字
		isFocus:"false",	//textarea是否获取焦点

		scoreDisabled: "none",

		commentDisabled: "none",
		commentButtonText: "取消",
	},
	toggleComment: function(e){
		if (e.currentTarget.dataset.commentbuttontext=="取消"){
			this.setData({
				commentDisabled: "none",
			})
		} else if (e.currentTarget.dataset.commentbuttontext == "发送"){

		}
	},
	/**
	 * 监听评论框中的文本输入
	 */
	judgeChange: function(e) {
		if(e.detail.value==""){
			this.setData({commentButtonText: "取消"})
		} else {
			this.setData({ commentButtonText: "发送" })
		}
	},
	/**
	 * 点击评论跳出评论
	 */
	bind: function(e) {
		console.log(e)
		if(e.target.id=="comment"){
			this.setData({
				commentDisabled: "flex",
				commentFocus: "true"
			})
		} else {
			this.setData({
				commentDisabled: "none",
			})
		}
	},
	/**
	 * 点击编辑按钮
	 */
	// 修改edit按钮样式
	changeEdit: function (e) {
		console.log(e)
		if(e.currentTarget.dataset.text == "编辑"){
			this.setData({
				editText:"保存",
				editBackground:"#3f99f8",
				editColor:"#ffffff",
				blackboardDisabled:"",
				isFocus:"true"
			})
		} else if (e.currentTarget.dataset.text == "保存"){
			this.setData({
				editText: "编辑",
				editBackground: "#f8f8f8",
				editColor: "#000000",
				blackboardDisabled: "true",
				isFocus:'false'
			})
		}
	},
	// 给服务器提交数据
	edit:function(e) {
		this.setData({
			blackboardContent: e.detail.value.textarea
		})
		wx.request({
			url: app.globalData.requestUrl + "/teacher/SetTeasay",
			data: {
				classid: 3,
				teachersay: this.data.blackboardContent
			}
		})
	},
	/**
	 * 点击菜单按钮
	 */
	toggerMenu: function (e) {
		if (this.data["display"] == "none") {
			this.setData({ display: "flex" })
		} else {
			this.setData({ display: "none" })
		}
	},
	/**
	 * 分数发生改变
	 */
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
	// 点击创建笔记
	toCreateNote:function(e) {
		var that = this
		wx.navigateTo({
			url: '/pages/class/note/create_note/create_note',
			success: function(res) {
				that.setData({
					display:"none"
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
			key: 'USERIDENTITY',
			success: function (res) {
				// 获取用户身份
				if (res.data == 1) {
					console.log("I am a teacher")
					that.setData({
						editDisplay: "inline",
						scoreDisabled: "none",
					})
				} else if (res.data == 0) {
					console.log("I am a student")
					that.setData({
						editDisplay: "none",
						scoreDisabled: "flex"
					})
				}
			},
		})
		// 获得笔记列表
		wx.request({
			url: app.globalData.requestUrl + "/teacher/GetNotes",
			method: "POST",
			data: {
				classid: "3"
			},
			header: {
				'content-type': 'application/x-www-form-urlencoded' // 默认值
			},
			success: function(res){
				console.log(res.data)
				that.setData({
					noteList: res.data
				})
				wx.downloadFile({
					url:"",
					success:function(res) {
						
					}
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