<template>
	<view class="notice">	
		<u-toast ref="uToast" />	
		<view style="text-align: center;font-size: 25px;padding-top: 20px;margin-bottom: 20px;">
			社区通知
		</view>
		<view class="new">
			<u-form >
				<u-form-item :label-position="labelPosition" label=" " prop="text" label-width="0">
					<view style="background-color: #FFFFFF;">
						<u-input type="textarea" :border="true" placeholder="请输入通知内容" v-model="text"></u-input>
					</view>
					<u-button slot="right" type="primary" size="mini" @click="send">新增</u-button>
				</u-form-item>
			</u-form>
		</view>
		<view class="hei" v-for="(notice, index) in notificationList" :key="notice.notificationId">
			<view class="num">
				{{notice.text}}
				<view class="three">
					<u-modal ref="uModal" v-model="show_del" :show-cancel-button="true" :show-title="showTitle" :async-close="asyncClose"
					 @confirm="confirm" :content="content">
					</u-modal>
					<u-button @click="change(notice)" type="primary" class="bubu" size="mini" style="margin-left: 5px;">修改</u-button>
					<u-button @click="deleteQue(notice.notificationId)" type="primary" class="bubu" size="mini" style="margin-left: 5px;">删除</u-button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//删除
				id: '',
				show_del: false,
				content: '你确定要删除吗',
				showTitle: true,
				asyncClose: false,
				//新增
				text: '',
				//通知
				notificationList: [{
						notificationId: 1,
						text: '',
					}
				]

			}
		},
		onLoad() {
			this.getAll()
		},
		onShow() {
			this.getAll()
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
						this.notificationList = res.data.data.notificationList
					}
				});
			},
			//新增
			send() {
				uni.request({
					url: this.apiServer + '/community/notification' ,
					method: 'POST',
					data: {
						text: this.text
					},
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						//上方提示并刷新
						this.$refs.uToast.show({
							position: 'top',
							title: '发送成功',
							type: 'success',
							url: '/pages/tabbar/tabbar-2/notice'
						})
					}
				});
			},
			//删除
			deleteQue(noid) {
				//提示
				this.show_del = true
				this.id = noid
			},
			//修改
			change(notice) {
				uni.navigateTo({
					url: '/pages/tabbar/tabbar-2/noticeChange?notificationId='+ notice.notificationId + '&text=' + notice.text
				})
			},
			//确定删除
			confirm() {
				this.show_del = false;
				uni.request({
					url: this.apiServer + '/community/notification?notificationId=' + this.id ,
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
							// url: '/pages/tabbar/tabbar-2/notice'
						})
						uni.redirectTo({
							url:'/pages/tabbar/tabbar-2/notice'
						})
					}
				});
			},


		}
	}
</script>

<style lang="scss" scoped>
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
		height: 100px;
		margin-top: 10px;
		background-color: #FFFFFF;
		padding: 10px;
		padding-bottom: 10px;
	}

	.num {
		padding-left: 10px;
		margin-top: 20px;
		font-size: 18px;
	}

	.three {
		padding-left: 200px;
	}

	/* 修改 */
	.slot-content {
		font-size: 28rpx;
		color: $u-content-color;
		padding-left: 30rpx;
	}

	.input {
		padding-top: 100px;
	}
</style>
