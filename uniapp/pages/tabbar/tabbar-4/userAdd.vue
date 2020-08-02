<template>
	<view>
		<u-toast ref="uToast" />
		<view class="form">
			
			<view style="font-size: 16px;" v-if="form.admin==='Y'">您好，尊敬的管理员</view>
			<view style="font-size: 16px;" v-if="form.admin==='N'">您好，尊敬的用户</view>
			<br>
			<u-form :model="form" ref="uForm">
				<u-form-item label="姓名" prop="username" label-width="150">
					<u-input :border="true" v-model="form.username" />
				</u-form-item>
				<u-form-item label="社区名称" prop="community" label-width="150">
					<u-input :border="true" v-model="form.community" />
				</u-form-item>
				<u-form-item label="住址" prop="address" label-width="150">
					<u-input :border="true" v-model="form.address" />
				</u-form-item>
				<!-- :leftIconStyle="{color: '#888', fontSize: '32rpx'}" left-icon="kefu-ermai" -->
				<u-form-item :label-position="labelPosition" label="手机号码" prop="phoneNumber" label-width="150">
					<u-input :border="true" placeholder="请输入手机号" v-model="form.phoneNumber" type="number"></u-input>
				</u-form-item>
			</u-form>
			<br>
			<u-toast ref="uToast" />
			<u-button @click="setUser" type="primary">确定增加</u-button>
		</view>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				userId: 1,
				form: {
					username: '',
					community: '',
					address: '',
					phoneNumber: '',
				},
				rules: {
					username: [{
						required: true,
						message: '请输入姓名',
						// 可以单个或者同时写两个触发验证方式
						trigger: 'blur,change'
					}],
					community: {
						required: true,
						message: '请输入社区',
						trigger: 'blur,change'
					},
					address: {
						required: true,
						message: '请输入住址',
						trigger: 'blur,change'
					},
					phoneNumber: [{
							required: true,
							message: '请输入手机号',
							trigger: ['change', 'blur'],
						},
						{
							validator: (rule, value, callback) => {
								// 调用uView自带的js验证规则，详见：https://www.uviewui.com/js/test.html
								return this.$u.test.mobile(value);
							},
							message: '手机号码不正确',
							// 触发器可以同时用blur和change，二者之间用英文逗号隔开
							trigger: ['change', 'blur'],
						}
					],

				}
			};
		},
		onShow(option) {
			console.log(option.id)
			this.userId = option.id
					
			this.getUser();
		},
		onLoad(option) {
			console.log(option.id)
			this.userId = option.id
		
			this.getUser();
		},
		// 必须要在onReady生命周期，因为onLoad生命周期组件可能尚未创建完毕
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
		computed: {},
		methods: {
			//得到原有用户信息
			getUser() {
				uni.request({
					url: this.apiServer + '/user?userId=' + this.userId,
					method: 'GET',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.form = res.data.data.user
					},
				})
			},

			//修改信息
			setUser() {
				this.$refs.uForm.validate(valid => {
					if (valid) {
						uni.request({
							url: this.apiServer + '/users/user',
							method: 'PUT',
							data: this.form,
							header: {
								'content-type': 'application/json'
							},
							success: res => {
								this.$refs.uToast.show({
									position: 'top',
									title: '修改成功',
									type: 'success',
									// url: '/pages/tabbar/tabbar-4/table'
								})
							uni.redirectTo({
								url:'/pages/tabbar/tabbar-4/table'
							})
							}

						})
					}
				})
			},


		}
	};
</script>

<style>
	.form {
		margin-left: 20px;
		margin-right: 20px;
		margin-top: 20px;
	}
</style>
