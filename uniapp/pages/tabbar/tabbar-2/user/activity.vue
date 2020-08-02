<template>
	<view class="notice">
		<view style="text-align: center;font-size: 25px;padding-top: 20px;">
			社区活动
		</view>

		<view class="hei" >
			<view class="num" v-for="(activity, index) in activityList" :key="activity.activityId">
				<u-row>
					<u-col span="4">
						<u-image width="80px" height="80px" :src="activity.picture"></u-image>
					</u-col>
					<u-col span="8" style="padding-left: 0px;font-size: 20px;">
						{{activity.text}}
					</u-col>
				</u-row>
		
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				text: '',
				//社区活动
				activityList: [{
						activityId: 1,
						text: '',
						picture: '',
					}
				],


			}
		},
		onLoad() {
			this.getAll();
		},
		methods: {
			//得到全部
			getAll() {
				uni.request({
					url: this.apiServer + '/community/activityList',
					method: 'GET',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.activityList = res.data.data.activityList
					}
				})
			}
		}
	}
</script>

<style>
	.notice {
		background-color: #dedede;

	}

	.new {
		margin-left: 10px;
		margin-right: 10px;
		background-color: #FFFFFF;
	}

	.hei {
		margin-left: 10px;
		margin-right: 10px;
		margin-bottom: 20px;
		padding: 10px;
	}

	.num {
		padding: 20px;
		background-color: #FFFFFF;
		margin-top: 20px;
	}

	.three {
		padding-left: 200px;
	}

	/* 上传图片 */
	.wrap {

		padding-top: 1px;
		margin-left: 30px;
		margin-right: 30px;
		background-color: #FFFFFF;

	}

	.u-avatar-wrap {

		overflow: hidden;
		margin-bottom: 10rpx;
		text-align: center;
	}

	.u-avatar-demo {
		width: 150rpx;
		height: 150rpx;
		border-radius: 100rpx;
	}

	.wrap-button {

		margin-left: 30px;
		margin-right: 30px;
	}

	.send {
		margin-top: 30px;
	}
</style>
