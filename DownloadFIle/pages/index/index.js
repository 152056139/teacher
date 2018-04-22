//index.js
//获取应用实例
const app = getApp()

Page({
	data: {
		motto: 'Hello World',
		userInfo: {},
		hasUserInfo: false,
		canIUse: wx.canIUse('button.open-type.getUserInfo')
	},
	upload: function(e) {
		wx.chooseImage({
			success: function(res) {
				var tempFilePaths = res.tempFilePaths
				for(var i = 0; i < tempFilePaths.length; i++){
					wx.uploadFile({
						url: 'http://localhost:8081/DownloadFile/UploadFile',
						filePath: tempFilePaths[i],
						name: 'file',
						formData: {
							username: 'gray',
							index: i
						},
						success: function (res) {
							console.log(res.data)
						}
					})
				}
				
			},
		})
	},
	download: function(e) {
		wx.downloadFile({
			url: 'http://localhost:8081/DownloadFile/DownloadFile', //仅为示例，并非真实的资源
			data: {
				name: "touristappid.o6zAJs2S6O67LEVVZS8yIcCXMuaM.VGO7UA9EyVCT871a19d30e097c4d9f7b729a91fa1fe6.jpg"
			},
			success: function (res) {
				if (res.statusCode === 200) {
					console.log(res.tempFilePath)
					wx.previewImage({
						urls: [res.tempFilePath],
					})
				}
			}
		})
	},
	//事件处理函数
	bindViewTap: function () {
		wx.navigateTo({
			url: '../logs/logs'
		})
	},
	onLoad: function () {
		if (app.globalData.userInfo) {
			this.setData({
				userInfo: app.globalData.userInfo,
				hasUserInfo: true
			})
		} else if (this.data.canIUse) {
			// 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
			// 所以此处加入 callback 以防止这种情况
			app.userInfoReadyCallback = res => {
				this.setData({
					userInfo: res.userInfo,
					hasUserInfo: true
				})
			}
		} else {
			// 在没有 open-type=getUserInfo 版本的兼容处理
			wx.getUserInfo({
				success: res => {
					app.globalData.userInfo = res.userInfo
					this.setData({
						userInfo: res.userInfo,
						hasUserInfo: true
					})
				}
			})
		}
	},
	getUserInfo: function (e) {
		console.log(e)
		app.globalData.userInfo = e.detail.userInfo
		this.setData({
			userInfo: e.detail.userInfo,
			hasUserInfo: true
		})
	}
})
