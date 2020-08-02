<template>
	<view class="notice">
		<u-toast ref="uToast" />
		<view style="text-align: center;font-size: 25px;padding-top: 20px;margin-bottom: 20px;">
			社区通知 ID = {{notificationId}}
		</view>
		<view class="hei">
			<view class="num">
				<u-input type="textarea" :border="true" placeholder="请输入活动文字内容" v-model="text" :height="150" :auto-height="true"></u-input>
			</view>
			<view style="margin-top: 20px;margin-bottom: 20px;">
				<u-button type="success" @click="change" style="font-size: 20px;">确认修改</u-button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				notificationId: 1,
				text: '',
			}
		},
		onLoad(option) {
			console.log(option.text)
			this.notificationId = option.notificationId
			this.text = option.text

		},
		onShow(option){
			console.log(option.text)
			this.notificationId = option.notificationId
			this.text = option.text
		},
		methods: {
			//确认修改
			change() {
				uni.request({
					url: this.apiServer + '/community/notification',
					method: 'PUT',
					data: {
						notificationId: this.notificationId,
						text: this.text
					},
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						//上方提示并刷新
						this.$refs.uToast.show({
							position: 'top',
							title: '修改成功',
							type: 'success',
							url: '/pages/tabbar/tabbar-2/notice'
						})
					}
				});
			}
		}
	}
</script>

<style>
	.notice {
		background-color: #dedede;
		height: 1000px;

	}

	.new {
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

	.id {
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
