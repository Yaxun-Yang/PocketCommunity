<template>
	<view>
		<u-toast ref="uToast" />

		<view class="form">
			<u-form :model="form" ref="uForm">
				<u-form-item label="名称" prop="materialName" label-width="150">
					<u-input :border="true" v-model="form.materialName" />
				</u-form-item>
				<u-form-item label="来源" prop="origin" label-width="150">
					<u-input :border="true" v-model="form.origin" />
				</u-form-item>
				<u-form-item label="去向" prop="destination" label-width="150">
					<u-input :border="true" v-model="form.destination" />
				</u-form-item>
				<u-form-item label="储存" :label-position="labelPosition" prop="transferStation" label-width="150">
					<u-input :border="true" v-model="form.transferStation" />
				</u-form-item>
			</u-form>
			<br>
			<u-toast ref="uToast" />
			<u-button @click="setUser" type="primary">提交修改</u-button>
		</view>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				userId: 1,
				form: {
					materialName: '',
					origin: '',
					destination: '',
					transferStation: '',
				},
				rules: {
					materialName: [{
						required: true,
						message: '请输入名称',
						// 可以单个或者同时写两个触发验证方式
						trigger: 'blur,change'
					}],
					origin: {
						required: true,
						message: '请输入来源',
						trigger: 'blur,change'
					},
					destination: {
						required: true,
						message: '请输入去向',
						trigger: 'blur,change'
					},
					transferStation: {
						required: true,
						message: '请输入储存',
						trigger: 'blur,change'
					},


				}
			};
		},
		onShow(option) {
			this.form.materialName = option.materialName
			this.form.origin = option.origin
			this.form.destination = option.destination
			this.form.transferStation = option.transferStation
			this.getUser();
		},
		onLoad(option) {
			this.form.materialName = option.materialName
			this.form.origin = option.origin
			this.form.destination = option.destination
			this.form.transferStation = option.transferStation
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
					url: this.apiServer + '/material/material?materialId=' + this.userId,
					method: 'GET',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.form = res.data.data.material
					},
				})
			},

			//修改信息
			setUser() {
				this.$refs.uForm.validate(valid => {
					if (valid) {
						uni.request({
							url: this.apiServer + '/material/material',
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
									url: '/pages/tabbar/tabbar-4/table'
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
