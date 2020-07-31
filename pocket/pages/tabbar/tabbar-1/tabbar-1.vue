<template>
	<view class="bb">

		<view class="comment">
			<view class="left">
				<image :src="url" mode="aspectFill"></image>
			</view>
			<view class="right">
				<view class="top">
					<view class="name">{{username }}</view>
				</view>
				<view>
					<u-form :model="model">
						<u-form-item :label-position="labelPosition" label=" " prop="text" label-width="0">
							<u-input type="textarea" :border="true" placeholder="请输入你的留言" v-model="model.text"></u-input>
							<u-button slot="right" type="success" size="mini" @click="send">发送</u-button>
						</u-form-item>
					</u-form>
				</view>

			</view>
		</view>
		<view class="comment" v-for="(res, index) in commentList" :key="res.id">
			<view class="left">
				<image :src="res.url" mode="aspectFill"></image>
			</view>
			<view class="right">
				<view class="top">
					<view class="name">{{ res.name }}</view>
				</view>
				<view class="content">{{ res.contentText }}</view>
				<view class="bottom">{{ res.date }}</view>
			</view>
		</view>
		<!-- 		<view class="circle">
			<span class="add">+</span>
		</view> -->



	</view>
</template>

<script>
	export default {

		data() {
			return {
				//
				url: 'https://cdn.uviewui.com/uview/template/SmilingDog.jpg',
				username: '用户2',
				//
				model: {
					text: '',
				},
				//
				commentList: [{
					id: 1,
					userId: '111',
					name: '用户1',
					date: '12-25 18:58',
					contentText: '今天天气真好，这里是留言，你好，这里是留言，这里是留言',
					url: 'https://cdn.uviewui.com/uview/template/SmilingDog.jpg',
				}]
			};
		},
		onLoad() {
			this.getComment();
		},
		methods: {
			// 评论列表
			getComment() {
				uni.request({
					url: this.apiServer + '/',
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.commentList = res.data.data.commentList;
					}
				});
			},

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
