<template>
	<view class="notice">
		<view style="text-align: center;font-size: 25px;padding-top: 20px;margin-bottom: 20px;">
			社区通知
		</view>

	
		<view class="hei" v-for="(notice, index) in notification" :key="notice.notificationId">
			<view class="id">
				通知{{notice.notificationId}}
			</view>
			<view class="num">
				{{notice.text}}
		
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				notification: [{
						notificationId: 1,
						text: '',
					}
				]

			}
		},
		onLoad() {
			this.getAll();
		},
		methods: {
			//得到全部
			getAll(){
				uni.request({
					url: this.apiServer + '/community/notificationList' ,
					method: 'GET',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.notification = res.data.data.notificationList
					}
				});
			},

		}
	}
</script>

<style>
		
	.notice{
		background-color: #dedede;
		height: 1000px;

	}
	.new{
		margin-left: 10px;
		margin-right: 10px;
	
	}
	.hei {
		margin-left: 10px;
		margin-right: 10px;

		margin-top: 20px;
		background-color: #FFFFFF;
		padding: 10px;
	}
	.id{
				padding-left: 10px;
					font-size: 14px;
					color: #333333;
	}
	.num {
		padding-left: 10px;
		margin-top: 5px;
		font-size: 22px;
	}
	
	.three {
		padding-left: 200px;
	}
</style>
