<template>
	<view class="login">
		<view style="font-size: 30px;text-align: center;">
			欢迎光临 口袋社区
		</view>
		<view style="margin-top: 30px">
			<u-form :model="form" ref="uForm">
				<u-form-item label="用户名" prop="name" label-width="150">
					<u-input :border="true" v-model="form.name" placeholder="请输入用户名" />
				</u-form-item>
				<u-form-item label="密码" label-width="150" prop="password">
					<u-input :password-icon="true" :border="true" type="password" v-model="form.password" placeholder="请输入密码"></u-input>
				</u-form-item>
			</u-form>
		</view>
		<view style="margin-top: 30px;">
			<u-button type="primary">
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
					<u-image src="../../../static/qq.png" width="50px" height="50px"></u-image>
				</u-col>
				<u-col :span="6">
					<u-image src="../../../static/wx.png" width="50px" height="50px"></u-image>
				</u-col>
			</u-row>
		</view>

	</view>
</template>


<script>
	export default {

		data() {
			return {
				form: {
					name: '',
					password: '',
				}
			}
		},

		methods: {
			sign(){
				uni.navigateTo({
					url: '/pages/tabbar/tabbar-1/sign'
				});
			}
		},
	}
</script>

<style>
	.login {
		margin-top: 50px;
		padding-left: 30px;
		padding-right: 30px;
	}
</style>
