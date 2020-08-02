<template>
	<view class="bb">
		<u-toast ref="uToast" />
		<view class="comment">
			<view class="left">
				<image :src="avatar" mode="aspectFill"></image>
			</view>
			<view class="right">
				<view class="top">
					<view class="name">{{username}}</view>
				</view>
				<view>
					<u-form>
						<u-form-item :label-position="labelPosition" label=" " prop="text" label-width="0">
							<u-input type="textarea" :border="true" placeholder="请输入你的留言" v-model="text"></u-input>
							<u-button slot="right" type="success" size="mini" @click="send" :loading="loading">发送</u-button>
						</u-form-item>
					</u-form>
				</view>
			</view>
		</view>
		<view class="comment" v-for="(res, index) in commentList" :key="res.messageId">
			<view class="left">
				<image :src="res.avatar" mode="aspectFill"></image>
			</view>
			<view class="right">
				<view class="top">
					<view class="name">{{ res.username }}</view>
				</view>
				<view class="content">{{ res.text }}</view>
				<view class="bottom">{{ res.messageTimestamp }}</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {

		data() {
			return {
				dd: 2,
				//按钮
				loading: false,
				//本人
				id: '',
				admin: '',
				avatar: '/static/avatar.png',
				username: '游客',
				userId: '',
				text: '',
				//列表
				commentList: [{
					messageId: 1,
					messageTimestamp: '12-25 18:58',
					text: '今天天气真好，这里是留言，你好，这里是留言，这里是留言',
					username: '用户1',
					avatar: 'https://cdn.uviewui.com/uview/template/SmilingDog.jpg',
				}],

			};
		},
		onLoad() {
			
			this.id = this.getStorageId()
			this.admin = this.getStorageAdmin()
			this.getComment();
		},
		onShow() {
			
			this.id = this.getStorageId()
			this.admin = this.getStorageAdmin()
			this.getComment();
		},
		computed: {
			// id() {
			// 	const value = uni.getStorageSync('userId');
			// 	return value
			// },
			// admin() {
			// 	const value = uni.getStorageSync('admin');
			// 	return value
			// }
		},
		methods: {
			//
			getStorageId() {
				const id = uni.getStorageSync('userId')
				//console.log(id)
				if(id!==null)
				return id;
				else
				return this.id
			},
			getStorageAdmin() {
				const admin = uni.getStorageSync('admin')
				//console.log(admin)
				if(admin!==null)
				return admin;
				else
				return this.admin
			},
			// 用户和留言
			getComment() {
				console.log("userId:"+this.id)
				uni.request({
					url: this.apiServer + '/users/userInfo?userId=' + this.id,
					method: 'GET',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.username = res.data.data.user.username
						this.avatar = res.data.data.user.avatar
					}
				});

				uni.request({
					url: this.apiServer + '/message/messageList',
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.commentList = res.data.data;
					}
				});
			},
			// 发送评论
			send() {
				this.loading = true
				console.log(this.id)
				if (this.admin === 'N' || this.admin === 'Y') {
					console.log('你登录了')
					// 登录了
					uni.request({
						url: this.apiServer + '/message/message',
						method: 'POST',
						data: {
							userId: this.id,
							text: this.text
						},
						header: {
							'content-type': 'application/json'
						},
						success: res => {
							this.loading = false
							if (res.data.data !== null) {
								//上方提示
								this.$refs.uToast.show({
									position: 'top',
									title: '留言成功',
									type: 'success',
								})
								uni.switchTab({
									url: '/pages/tabbar/tabbar-1/tabbar-1',
									success: function(e) {
										var page = getCurrentPages().pop();
										if (page == undefined || page == null) return;
										page.onLoad();
									}
								});
							}
						}
					});
				} else {
					console.log('你没登录')
					this.loading = false
					this.$refs.uToast.show({
						position: 'top',
						title: '请先登录',
						type: 'error',
					})

				}

			}
		}
	};
</script>

<style lang="scss" scoped>
	.bb {
		height: 1200px;
		background-color: #e8ebef;
	}

	.comment {
		display: flex;
		padding: 30rpx;
		background-color: #FFFFFF;

		.left {
			image {
				width: 64rpx;
				height: 64rpx;
				border-radius: 50%;
				background-color: #f2f2f2;
			}
		}

		.right {
			flex: 1;
			padding-left: 20rpx;
			font-size: 30rpx;

			.top {
				display: flex;
				justify-content: space-between;
				align-items: center;
				margin-bottom: 10rpx;

				.name {
					color: #5677fc;
				}

			}

			.content {
				margin-bottom: 10rpx;
			}

			.bottom {
				margin-top: 20rpx;
				display: flex;
				font-size: 24rpx;
				color: #9a9a9a;

				.reply {
					color: #5677fc;
					margin-left: 10rpx;
				}
			}
		}
	}

	/* 	.circle {
		width: 50px;
		height: 50px;
		background-color: #2674c2;
		border-radius: 25px;


	}


	.add {
		// height: 50px;
		// line-height: 50px;
		display: block;
		color: #FFF;
		text-align: center;
		font-size: 42px;
		font-weight: bolder;
	} */
</style>
