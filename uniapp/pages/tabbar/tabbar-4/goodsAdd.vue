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
				<u-form-item  label="储存" :label-position="labelPosition"  prop="transferStation" label-width="150">
					<u-input :border="true"  v-model="form.transferStation"  />
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
		onLoad(option) {
			this.userId = option.id
			this.getUser();
		},
		onShow() {
			this.userId = option.id
			this.getUser();
		},
		// 必须要在onReady生命周期，因为onLoad生命周期组件可能尚未创建完毕
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
		computed: {},
		methods: {
			//修改信息
			setUser() {
				this.$refs.uForm.validate(valid => {
					if (valid) {
						uni.request({
							url: this.apiServer + '/material/material',
							method: 'POST',
							data: this.form,
							header: {
								'content-type': 'application/json'
							},
							success: res => {
								this.$refs.uToast.show({
									position: 'top',
									title: '增加物资成功',
									type: 'success',
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
