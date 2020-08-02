<template>
	<view class="ava">
		<view style="font-size: 30px;padding-top: 40px;color: #3f4041;">
			设置你的头像吧
		</view>
		<view style="font-size: 16px;padding-top: 10px;color: #5c5c5e;">
			记得放一张美美的照片哦～
		</view>
		<view class="wrap">
			<view class="u-avatar-wrap">
				<image class="u-avatar-demo" :src="avatar" mode="aspectFill"></image>
			</view>
			<view style="font-size: 26px;padding-bottom: 20px;padding-top: 20px;">
				关关子
			</view>

		</view>
		<view class="wrap-button">
			<u-button type="primary" @click="chooseAvatar" style="font-size: 20px;">添加图片</u-button>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				avatar:  '/static/avatar.png',
			}
		},
		created() {
			// 监听从裁剪页发布的事件，获得裁剪结果
			uni.$on('uAvatarCropper', path => {
				this.avatar = path;
				// 可以在此上传到服务端
				uni.uploadFile({
					url: this.apiServer+'/users/avatar',
					filePath: path,
					name: 'file',
					formData:{
						userId: this.userId,
					},
					complete: (res) => {
						console.log(res);
					}
				});
			})
		},
		computed: {
			//获取权限
			admin() {
				return sessionStorage.getItem('admin');
			},
			//获取用户id
			userId() {
				return sessionStorage.getItem('userId');
			},
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
		}
	}
</script>

<style lang="scss" scoped>
	.ava {
		text-align: center;
		height: 623px;
		background-color: #e8ebef;
	}

	.wrap {
		margin-top: 50px;
		padding-top: 1px;
		margin-left: 30px;
		margin-right: 30px;
		background-color: #FFFFFF;
		
	}

	.u-avatar-wrap {
		margin-top: 80rpx;
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
		margin-top: 40px;
		margin-left: 30px;
		margin-right: 30px;
	}
</style>
