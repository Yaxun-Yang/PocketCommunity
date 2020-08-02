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

							<view style="font-size: 13px;" v-for="(res, index) in activity" :key="res.activityId">
								<u-row>
									<u-col span="5">
										<u-image width="50px" height="50px" :src="res.picture" @click="acti"></u-image>
									</u-col>
									<u-col span="7" style="padding-left: 5px;">
										{{res.text}}
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
							<view class="con2">
								幸福超市今日做活动啦
							</view>
							<view class="con2">
								健身器材将于8.5被拆
							</view>
						</view>

						<view style="margin-top: 10px;text-align: left;font-size: 17px;">
							通知板
						</view>
						<view class="border2" @click="notice">
							<view class="con2" v-for="(res, index) in notification" :key="res.notificationId">
								{{res.notificationId}}.{{res.text}}
							</view>
						</view>
					</u-col>
				</u-row>
			</view>
			<view class="four">
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
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				id:'',
				admin:'',
				keyword: '',
				//社区活动
				activity: [{
					activityId: 1,
					text: '',
					picture: '',
				}],
				//通知
				notification: [{
					notificationId: 1,
					text: '',
				}]
			}
		},
		onShow(){
			this.id = this.getStorageId()
			this.admin = this.getStorageAdmin()
			this.getAll();
		},
		onLoad() {
			this.id = this.getStorageId()
			this.admin = this.getStorageAdmin()
			this.getAll();
		},
		computed: {
		
		},
		methods: {
			//
			getStorageId() {
					
				const id = uni.getStorageSync('userId')
				
				return id;
			},
			getStorageAdmin() {
					
				const admin = uni.getStorageSync('admin')
				return admin;
			},
			getAll() {
			
				uni.request({
					url: this.apiServer + '/community/threeActivityList',
					method: 'GET',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.activity = res.data.data.threeActivityList
					}
				});
				uni.request({
					url: this.apiServer + '/community/notificationList',
					method: 'GET',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.notification = res.data.data.notificationList
					}
				});
			},
			que() {
				if (this.admin === 'Y') {
					uni.navigateTo({
						url: '/pages/tabbar/tabbar-2/question'
					});
				} else {
					uni.navigateTo({
						url: '/pages/tabbar/tabbar-2/user/question'
					});
				}
			},
			acti() {
				if (this.admin === 'Y') {
					uni.navigateTo({
						url: '/pages/tabbar/tabbar-2/activity'
					});
				} else {
					uni.navigateTo({
						url: '/pages/tabbar/tabbar-2/user/activity'
					});
				}
			},
			notice() {
				if (this.admin === 'Y') {
					uni.navigateTo({
						url: '/pages/tabbar/tabbar-2/notice'
					});
				} else {
					uni.navigateTo({
						url: '/pages/tabbar/tabbar-2/user/notice'
					});
				}
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
		padding-top: 3px;
		padding-bottom: 3px;
		padding-left: 3px;
		font-size: 13px;
		background-color: #FFFFFF;
	}

	.four {
		padding-bottom: 20px;
	}
</style>
