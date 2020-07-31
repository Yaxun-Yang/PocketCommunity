<template>
	<view style="background-color: #dedede;">
		<view style="text-align: center;font-size: 25px;margin-top: 20px;margin-bottom: 20px;">
			社区活动
		</view>
		<view class="hei">
			<!-- v-for="(res, index) in commentList" :key="res.id" -->
			<view style="font-size: 13px;">
				<u-row>
					<u-col span="5">
						<u-image width="50px" height="50px" src="../../../static/u1.jpg"></u-image>
					</u-col>
					<u-col span="7" style="padding-left: 5px;">
						这是社区活动的文字
					</u-col>
				</u-row>
				<u-line color="#bfbfbf" length="100%" margin="10rpx" :hair-line="false" />
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
	
				//社区活动
				activity: [{
					text: '',
					picture: '',
				}],
			
	
			}
		},
		onLoad() {
	
		},
		methods: {
	
	
		}
	}
</script>

<style>

	.hei {
		height: 193px;
		background-color: #FFFFFF;
		padding: 10px;
	}
</style>
