<template>
	<view class="login">
		<u-toast ref="uToast" />
		<view style="font-size: 30px;text-align: center;">
			欢迎光临 口袋社区
		</view>
		<view style="margin-top: 30px">
			<u-form :model="form" ref="uForm">
				<u-form-item label="用户名" prop="username" label-width="150">
					<u-input :border="true" v-model="form.username" placeholder="请输入用户名" />
				</u-form-item>
				<u-form-item label="密码" label-width="150" prop="password">
					<u-input :password-icon="true" :border="true" type="password" v-model="form.password" placeholder="请输入密码"></u-input>
				</u-form-item>
			</u-form>
		</view>
		<view style="margin-top: 30px;">

			<u-button type="primary" :loading="loading" @click="login">
				登录
			</u-button>
		</view>
		<br>
		<view style="margin-top: 30px;">
			<u-button @click="sign">
				注册
			</u-button>
		</view>

		<view style="margin-top: 30px;margin-bottom: 30px;">
			<u-divider color="#bfbfbf">使用其他方式登录</u-divider>
		</view>
		<view style="padding-left: 60px;">
			<u-row>
				<u-col :span="6">
					<u-image src="/static/qq.png" width="50px" height="50px"></u-image>
				</u-col>
				<u-col :span="6">
					<u-image src="/static/wx.png" width="50px" height="50px"></u-image>
				</u-col>
			</u-row>
		</view>

	</view>
</template>


<script>
	export default {
		data() {
			return {
				loading: false,
				form: {
					username: '',
					password: '',
				}
			}
		},

		methods: {
			sign() {
				uni.navigateTo({
					url: '/pages/tabbar/tabbar-1/sign'
				});
			},
			login() {
				// 按钮开始转
				this.loading = true
				uni.request({
					url: this.apiServer + '/users/user?username=' + this.form.username + '&password=' + this.form.password,
					method: 'GET',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						console.log(res);
						if (res.data.data.user !== null) {
							// 登录按钮不转了
							this.loading = false;
							// 将返回的数据注入内存,同步接口
							uni.setStorageSync('username', res.data.data.user.username)
							uni.setStorageSync('userId', res.data.data.user.userId)
							uni.setStorageSync('admin', res.data.data.user.admin)
							//上方提示并跳转
							if (res.data.data.user.admin === 'N') {
								this.$refs.uToast.show({
									position: 'top',
									title: '欢迎你，用户',
									type: 'success',
									url: '/pages/tabbar/tabbar-2/tabbar-2',
									isTab: true,
								})
							} else if (res.data.data.user.admin === 'Y') {
								this.$refs.uToast.show({
									position: 'top',
									title: '欢迎你，管理员',
									type: 'success',
									url: '/pages/tabbar/tabbar-2/tabbar-2',
									isTab: true,
								})
							}
						} else if (res.data.data.user === null) {
							// 登录按钮不转了
							this.loading = false;
							//上方提示
							this.$refs.uToast.show({
								position: 'top',
								title: '用户名或密码错误',
								type: 'error'
							})
						}

					}
				});
			},

		}
	}
</script>

<style>
	.login {
		margin-top: 50px;
		padding-left: 30px;
		padding-right: 30px;
	}
</style>
