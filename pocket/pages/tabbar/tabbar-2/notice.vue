<template>
	<view class="notice">
		<view style="text-align: center;font-size: 25px;padding-top: 20px;margin-bottom: 20px;">
			社区通知
		</view>
		<view class="new">
			<u-form :model="model">
				<u-form-item :label-position="labelPosition" label=" " prop="text" label-width="0">
					<u-input type="textarea" :border="true" placeholder="请输入你的留言" v-model="model.text"></u-input>
					<u-button slot="right" type="success" size="mini" @click="send">发送</u-button>
				</u-form-item>
			</u-form>
		</view>
		<view class="hei" v-for="(notice, index) in notification" :key="notice.id">
			<view class="num">
				{{notice.text}}
				<view class="three">
					<u-button type="primary" class="bubu" size="mini" style="margin-left: 5px;">修改</u-button>
					<u-button type="primary" class="bubu" size="mini" style="margin-left: 5px;">删除</u-button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				model:[{
					text:'',
				}],
				notification: [{
						id: 1,
						text: '这是通知1',
					},
					{
						id: 2,
						text: '这是通知22222222',
					}
				]

			}
		},
		onLoad() {

		},
		methods: {


		}
	}
</script>

<style>
		
	.notice{
		background-color: #dedede;
		height: 1000px;

	}

	.hei {
		margin-left: 10px;
		margin-right: 10px;
		height: 100px;
		margin-top: 20px;
		background-color: #FFFFFF;
		padding: 10px;
	}
	
	.num {
		padding-left: 10px;
		margin-top: 40px;
	}
	
	.three {
		padding-left: 200px;
	}
</style>
