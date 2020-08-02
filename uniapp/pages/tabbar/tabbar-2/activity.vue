<template>
	<view class="notice">
		<u-toast ref="uToast" />
		<view style="text-align: center;font-size: 25px;padding-top: 20px;margin-bottom: 20px;">
			社区活动
		</view>
		<view class="new">
			<u-form>
				<u-form-item :label-position="labelPosition" label=" " prop="text" label-width="0">
					<view style="background-color: #FFFFFF;">
								<u-input type="textarea" :border="true" placeholder="请输入活动文字内容" v-model="text" :height="150" :auto-height="true"></u-input>
								<view class="send">
									<u-button type="primary" @click="send">新增</u-button>
								</view>
					</view>
				</u-form-item>
			</u-form>
		</view>
		<view class="hei">
			<view class="num" v-for="(activity, index) in activityList" :key="activity.activityId">
				<u-row>
					<u-col span="4">
						<u-image width="80px" height="80px" :src="activity.picture"></u-image>
					</u-col>
					<u-col span="8" style="padding-left: 0px;">
						{{activity.text}}
					</u-col>
				</u-row>
				<view class="three">
					<u-modal ref="uModal" v-model="show_del" :show-cancel-button="true" :show-title="showTitle" :async-close="asyncClose"
					 @confirm="confirm" :content="content">
					</u-modal>
					<u-button @click="change(activity)" type="primary" class="bubu" size="mini" style="margin-left: 5px;">修改</u-button>
					<u-button @click="deleteQue(activity.activityId)" type="primary" class="bubu" size="mini" style="margin-left: 5px;">删除</u-button>
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
				//新增
				avatar: '/static/activity.jpg',
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
			this.getAll()
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
				});
			},
			deleteQue(id) {
				//提示
				this.show_del = true
				this.id = id
			},
			//确定删除
			confirm() {
				this.show_del = false
				uni.request({
					url: this.apiServer + '/community/activity?activityId=' + this.id,
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
							url: '/pages/tabbar/tabbar-2/activity'
						})
					}
				});
			},
			//修改
			change(activity) {
				uni.navigateTo({
					url: '/pages/tabbar/tabbar-2/activityChange?activityId=' + activity.activityId + '&text=' + activity.text,
				})
			},
			//新增
			send() {
				uni.request({
					url: this.apiServer + '/community/activity' ,
					method: 'POST',
					data: {
						text: this.text,
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
							url: '/pages/tabbar/tabbar-2/activity'
						})
					}
				});
			},
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
