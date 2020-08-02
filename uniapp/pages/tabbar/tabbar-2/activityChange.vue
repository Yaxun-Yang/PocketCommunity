<template>
	<view class="notice">
		<u-toast ref="uToast" />
		<view style="text-align: center;font-size: 25px;padding-top: 20px;">
			社区活动 
		</view>
		<view class="new">
			<u-form>
				<u-form-item :label-position="labelPosition" label=" " prop="text" label-width="0">
					<view style="background-color: #FFFFFF;">
						<u-row>
							<u-col :span="6">
								<view class="wrap">
									<view class="u-avatar-wrap">
										<image class="u-avatar-demo" :src="avatar" mode="aspectFill"></image>
									</view>
								</view>
								<view class="wrap-button">
									<u-button type="primary" @click="chooseAvatar" style="font-size: 20px;">添加图片</u-button>
								</view>
							</u-col>
							<u-col :span="6">
								<u-input type="textarea" :border="true" placeholder="请输入活动文字内容" v-model="text" :height="150" :auto-height="true"></u-input>
							</u-col>
						</u-row>
					</view>
				</u-form-item>
			</u-form>
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
				//社区活动
				avatar:  '/static/activity.jpg',
				activityId: 1,
				text: '',
				picture: ''
			}
		},
		onLoad(option) {
			this.activityId = option.activityId
			this.text = option.text
		},
		onShow(option) {
			this.activityId = option.activityId
			this.text = option.text
		},
		created() {
			// 监听从裁剪页发布的事件，获得裁剪结果
			uni.$on('uAvatarCropper', path => {
				this.avatar = path;
				// 可以在此上传到服务端
				uni.uploadFile({
					url: this.apiServer+'/community/picture',
					filePath: path,
					name: 'file',
					formData:{
						activityId:this.activityId
					},
					complete: (res) => {
						console.log(res);
					}
				});
			})
		},
		methods: {
			chooseAvatar() {
				// 此为uView的跳转方法，详见"文档-JS"部分，也可以用uni的uni.navigateTo
				this.$u.route({
					// 关于此路径，请见下方"注意事项"
					url: '/uview-ui/components/u-avatar-cropper/u-avatar-cropper',
					// 内部已设置以下默认参数值，可不传这些参数
					params: {
						// 输出图片宽度，高等于宽，单位px
						destWidth: 300,
						// 裁剪框宽度，高等于宽，单位px
						rectWidth: 200,
						// 输出的图片类型，如果'png'类型发现裁剪的图片太大，改成"jpg"即可
						fileType: 'jpg',
					}
				})
			},
			//确认修改
			change() {
				uni.request({
					url: this.apiServer + '/community/activity',
					method: 'PUT',
					data: {
						activityId: this.activityId,
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
							url: '/pages/tabbar/tabbar-2/activity'
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
		height: 623px;
	}

	.new {
		margin-top: 20px;
		margin-left: 10px;
		margin-right: 10px;
		background-color: #FFFFFF;
		padding-bottom: 20px;
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
