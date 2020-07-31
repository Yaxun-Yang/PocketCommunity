<template>
	<view class="content">
		<view class="pad">
			<u-search placeholder="请输入搜索内容" v-model="keyword" bg-color="#ffffff"></u-search>
		</view>
		<view>
			<view class="pad">
				<u-image width="100%" height="400rpx" src="../../../static/u.jpg"></u-image>
			</view>
			<view class="padd">
				<u-row>
					<u-col span="6">
						<view style="text-align: left;font-size: 17px;">
							社区活动
						</view>
						<view class="border hei">
							<!-- v-for="(res, index) in commentList" :key="res.id" -->
							<view style="font-size: 13px;" >
								<u-row  >
									<u-col span="5">
										<u-image width="50px" height="50px" src="../../../static/u1.jpg" @click="acti"></u-image>
									</u-col>
									<u-col span="7" style="padding-left: 5px;">
										这是dsdcs
									</u-col>
								</u-row>
								<u-line color="#bfbfbf" length="100%" margin="10rpx" :hair-line="false" />
							</view>

						</view>
					</u-col>

					<u-col span="6">
						<view style="text-align: left;font-size: 17px;">
							社区头条
						</view>
						<view class="border">
							<!-- v-for="(res, index) in commentList" :key="res.id" -->
							<view class="con2">
								幸福超市今日做活动啦<br>
								健身器材将于8.23被拆
							</view>

						</view>
						<view style="margin-top: 30px;text-align: left;font-size: 17px;">
							通知板
						</view>
						<view class="border2" @click="notice">
							<view class="con2">
								1.zhehis sdbc
								2.cscsuicbdskbc
								3.cscsuicbdskbc
							</view>
						</view>
					</u-col>
				</u-row>
			</view>

			<u-row>
				<u-image width="100%" height="140rpx" src="../../../static/4.png"></u-image>
				<u-col span="3">
					ETC缴费
				</u-col>
				<u-col span="3">
					水费
				</u-col>
				<u-col span="3">
					电费
				</u-col>
				<u-col span="3">
					<view @click="que">
						调查问卷
					</view>
				</u-col>
			</u-row>
			<br>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				keyword: '',
				//社区活动
				activity: [{
					text: '',
					picture: '',
				}],
				notification: [{
					text: '',
				}]

			}
		},
		onLoad() {

		},
		methods: {
			que(){
				uni.navigateTo({
					url: '/pages/tabbar/tabbar-2/question'
				});
			},
			acti(){
				uni.navigateTo({
					url: '/pages/tabbar/tabbar-2/activity'
				});
			},
			notice(){
				uni.navigateTo({
					url: '/pages/tabbar/tabbar-2/notice'
				});
			}

		}
	}
</script>

<style>
	.content {
		text-align: center;
		/* height: 573px; */
		background-color: #dedede;
	}

	.pad {
		margin-left: 20px;
		margin-right: 20px;
		padding-top: 20px;

	}

	.padd {
		margin-left: 15px;
		margin-right: 20px;
		padding-top: 20px;
	}

	.border {
		background-color: #FFFFFF;
		text-align: left;
		border: 2px solid #bfbfbf;
	}

	.border2 {
		background-color: #FFFFFF;
		text-align: left;
		border: 2px solid #bfbfbf;
	}

	.hei {
		height: 193px;
		width: 150px;
		background-color: #FFFFFF;
		padding: 10px;
	}

	.con {

		background-color: #FFFFFF;

	}

	.con2 {
		padding-top: 10px;
		padding-bottom: 10px;
		padding-left: 3px;
		font-size: 13px;
		background-color: #FFFFFF;
	}
</style>
