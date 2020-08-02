<template>
	<view class="notice">
		<u-toast ref="uToast" />
		<u-modal ref="uModal" v-model="show_del" :show-cancel-button="true" :show-title="showTitle" :async-close="asyncClose"
		 @confirm="confirm" :content="content">
		</u-modal>
		<view style="text-align: center;font-size: 25px;padding-top: 20px;margin-bottom: 20px;">
			留言管理
		</view>
		<!-- v-for="(notice, index) in notification" :key="notice.id" -->
		<view class="hei">
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
				<view class="three">
					<u-button @click="detele(res.messageId)" type="primary" class="bubu" size="mini" style="margin-left: 5px;">删除</u-button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 删除
				id: '',
				show_del: false,
				content: '你确定要删除吗',
				showTitle: true,
				asyncClose: false,
				//列表
				commentList: [{
						messageId: 1,
						userId: '111',
						username: '用户1',
						messageTimestamp: '12-25 18:58',
						text: '今天天气真好，这里是留言，你好，这里是留言，这里是留言',
						avatar: 'https://cdn.uviewui.com/uview/template/SmilingDog.jpg',
					},
					{
						messageId: 2,
						userId: '111',
						username: '用户1',
						messageTimestamp: '12-25 18:58',
						text: '今天天气真好，这里是留言，你好，这里是留言，这里是留言',
						avatar: 'https://cdn.uviewui.com/uview/template/SmilingDog.jpg',
					}
				]

			}
		},
		onShow() {
			this.getComment();
		},
		onLoad() {
			this.getComment();
		},
		created() {

		},
		methods: {
			// 评论列表
			getComment() {
				uni.request({
					url: this.apiServer + '/message/messageList',
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.commentList = res.data.data;
					}
				})
			},
			//删除
			detele(id) {
				//提示
				this.show_del = true
				this.id = id
			},
			//确定删除
			confirm() {
				this.show_del = false
				uni.request({
					url: this.apiServer + '/message/message?messageId=' + this.id,
					method: 'DELETE',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						//上方提示并刷新
						this.$refs.uToast.show({
							position: 'top',
							title: '删除成功',
							type: 'success',
							// url: '/pages/tabbar/tabbar-4/message'
						})
						uni.redirectTo({
							url:'/pages/tabbar/tabbar-4/message'
						})
					}
				});
			},
		}
	}
</script>

<style lang="scss">
	.notice {
		background-color: #dedede;
		height: 1000px;
	}

	.hei {
		margin-left: 10px;
		margin-right: 10px;

	}

	.three {
		padding-top: 70px;
	}

	.comment {
		display: flex;
		padding: 30rpx;
		background-color: #FFFFFF;
		margin-top: 20px;

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
</style>
