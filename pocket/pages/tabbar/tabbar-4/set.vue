<template>
	<view >
		<view class="form">
			<view style="font-size: 16px;" v-if="form.admin==='Y'">您好，尊敬的管理员</view>
			<view style="font-size: 16px;" v-if="form.admin==='N'">您好，尊敬的用户</view>
			<br>
			<u-form :model="form" ref="uForm">
				<u-form-item label="姓名" prop="name" label-width="150">
					<u-input :border="true" v-model="form.name" />
				</u-form-item>
				<u-form-item label="身份证" prop="idCartNum" label-width="150">
					<u-input :border="true" v-model="form.idCartNum" />
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
				<u-form-item label="验证码" prop="code" label-width="150">
					<u-input :border="true" placeholder="请输入验证码" v-model="form.code" type="text"></u-input>
					<u-button slot="right" type="success" size="mini" @click="getCode">{{codeTips}}</u-button>
				</u-form-item>
				<u-form-item label="密码" prop="password" label-width="150">
					<u-input :password-icon="true" :border="true" type="password" v-model="form.password" placeholder="请输入密码"></u-input>
				</u-form-item>
				<u-form-item label="确认密码" label-width="150" prop="rePassword">
					<u-input :border="true" type="password" v-model="form.rePassword" placeholder="请确认密码"></u-input>
				</u-form-item>
			</u-form>
			<br>
			<u-button @click="setUser" type="primary">提交</u-button>
		</view>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				codeTips: '获取验证码',
				form: {
					name: '',
					idCartNum: '',
					community: '',
					address: '',
					phoneNumber: '',
					code: '',
					password: '',
					rePassword: '',
					admin: 'N',
				},
				rules: {
					name: [{
						required: true,
						message: '请输入姓名',
						// 可以单个或者同时写两个触发验证方式
						trigger: 'blur,change'
					}],
					idCartNum: [{
							required: true,
							message: '请输入身份证',
							trigger: 'blur,change'
						},
						{

							min: 18,
							message: '身份证不能少于18个数',
							trigger: 'change'
						}
					],
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
					code: [{
							required: true,
							message: '请输入验证码',
							trigger: ['change', 'blur'],
						},
						{
							type: 'number',
							message: '验证码只能为数字',
							trigger: ['change', 'blur'],
						}
					],
					password: [{
							required: true,
							message: '请输入密码',
							trigger: ['change', 'blur'],
						},
						{
							// 正则不能含有两边的引号
							pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+\S{5,12}$/,
							message: '需同时含有字母和数字，长度在6-12之间',
							trigger: ['change', 'blur'],
						}
					],
					rePassword: [{
							required: true,
							message: '请重新输入密码',
							trigger: ['change', 'blur'],
						},
						{
							validator: (rule, value, callback) => {
								return value === this.form.password;
							},
							message: '两次输入的密码不相等',
							trigger: ['change', 'blur'],
						}
					],
				}
			};
		},
		onLoad() {
			this.getUser();
		},
		// 必须要在onReady生命周期，因为onLoad生命周期组件可能尚未创建完毕
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
		computed: {

		},
		methods: {
			getUser() {
				uni.request({
					url: this.apiServer + '/',
					method: 'GET',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.form = res.data.data.user
					},
				})
			},
			setUser() {
				this.$refs.uForm.validate(valid => {
					if (valid) {
						uni.request({
							url: this.apiServer + '/',
							method: 'POST',
							data: this.form,
							header: {
								'content-type': 'application/json'
							},
							success: res => {},
						})
					}
				})
			}
		},


	};
</script>

<style>
	.form {
		margin-left: 20px;
		margin-right: 20px;
		margin-top: 20px;
	}
</style>
