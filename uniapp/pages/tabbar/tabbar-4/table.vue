<template>
	<view class="table">
		<u-toast ref="uToast" />
		<u-modal ref="uModal" v-model="show1" :show-cancel-button="true" :show-title="showTitle1" :async-close="asyncClose1"
		 @confirm="confirm1" :content="content1">
		</u-modal>
		<u-modal ref="uModal" v-model="show2" :show-cancel-button="true" :show-title="showTitle2" :async-close="asyncClose2"
		 @confirm="confirm2" :content="content2">
		</u-modal>
		<view class="u-tabs-box">
			<u-tabs-swiper activeColor="#4ac4d7" ref="tabs" :list="headList" :current="current" @change="change" :is-scroll="false"
			 swiperWidth="750"></u-tabs-swiper>
		</view>
		<swiper class="swiper-box" :current="swiperCurrent" @transition="transition" @animationfinish="animationfinish">

			<swiper-item class="swiper-item">
				<view style="margin-left: 300px;margin-top: 10px;margin-bottom: 10px;margin-right: 10px;">
					<u-button type="primary" @click="add1" >增加</u-button>
				</view>
				<view class="onetable">
					<u-table class="one-table">
							<u-tr>
								<u-th>序号</u-th>
								<u-th>姓名</u-th>
								<u-th>手机号</u-th>
								<u-th>社区</u-th>
								<u-th>住址</u-th>
								<u-th>修改</u-th>
								<u-th>删除</u-th>
							</u-tr>
							<u-tr v-for="(user, index) in userList" :key="user.userId">
								<u-td>{{user.userId}}</u-td>
								<u-td>{{user.username}}</u-td>
								<u-td>{{user.phoneNumber}}</u-td>
								<u-td>{{user.community}}</u-td>
								<u-td>{{user.address}}</u-td>
								<u-td>
									<u-button type="primary" size="mini" @click="change1(user)">修改</u-button>
					
								</u-td>
								<u-td>
					
									<u-button type="primary" size="mini" @click="delete1(user.userId)">删除</u-button>
								</u-td>
							</u-tr>
						</u-table>
					
				</view>
			</swiper-item>

			<swiper-item class="swiper-item">
				<view style="margin-left: 300px;margin-top: 10px;margin-bottom: 10px;margin-right: 10px;">
					<u-button type="primary" @click="add2" >增加</u-button>
				</view>
				<u-table>
					<u-tr>
						<u-th>名称</u-th>
						<u-th>来源</u-th>
						<u-th>去向</u-th>
						<u-th>储存</u-th>
						<u-th>修改</u-th>
						<u-th>删除</u-th>
					</u-tr>
					<u-tr v-for="(user, index) in materialList" :key="user.materialId">
						<u-td>{{user.materialName}}</u-td>
						<u-td>{{user.origin}}</u-td>
						<u-td>{{user.destination}}</u-td>
						<u-td>{{user.transferStation}}</u-td>
						<u-td>
							<u-button type="primary" size="mini" @click="change2(user)">修改</u-button>
						</u-td>
						<u-td>
							<u-button type="primary" size="mini" @click="delete2(user.materialId)">删除</u-button>
						</u-td>
					</u-tr>
				</u-table>
			</swiper-item>
		</swiper>
	</view>
</template>

<script>
	export default {

		data() {
			return {
				//大标签
				headList: [{
						name: '用户信息'
					},
					{
						name: '物资信息'
					}
				],
				current: 0,
				swiperCurrent: 0,
				tabsHeight: 0,
				dx: 0,
				//获取数据
				userList: [{

					userId: '1',
					username: '1',
				}],
				materialList: [{
					materialId: '1',
					materialName: '1',
				}],
				//删除
				id1: '',
				show1: false,
				content1: '你确定要删除这个用户吗',
				showTitle1: true,
				asyncClose1: false,
				id2: '',
				show2: false,
				content2: '你确定要删除这个物资吗',
				showTitle2: true,
				asyncClose2: false,
			}
		},
		onShow() {
			this.getAll();
		},
		onLoad() {
			this.getAll();
		},
		computed: {

		},
		methods: {
			// 大tab栏切换
			change(index) {
				this.swiperCurrent = index;
			},
			transition({
				detail: {
					dx
				}
			}) {
				this.$refs.tabs.setDx(dx);
			},
			animationfinish({
				detail: {
					current
				}
			}) {
				this.$refs.tabs.setFinishCurrent(current);
				this.swiperCurrent = current;
				this.current = current;
			},
			//获取
			getAll() {
				uni.request({
					url: this.apiServer + '/users/userList',
					method: 'GET',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.userList = res.data.data.userList
					}
				});
				uni.request({
					url: this.apiServer + '/material/materialList',
					method: 'GET',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.materialList = res.data.data.materialList
					}
				});
			},
			//增加
			add1(){
				uni.navigateTo({
					url: '/pages/tabbar/tabbar-4/userAdd'
				})
			},
			add2(){
				uni.navigateTo({
					url: '/pages/tabbar/tabbar-4/goodsAdd'
				})
			},
	
			change1(user) {
				uni.navigateTo({
					url: '/pages/tabbar/tabbar-4/userChange?username=' + user.username +'&community=' +user.community+ '&address=' +user.address+ '&phoneNumber=' +user.phoneNumber
				})
			},	
			change2(user) {
				uni.navigateTo({
					url: '/pages/tabbar/tabbar-4/goodsChange?materialName=' + user.materialName +'&origin=' + user.origin +'&destination=' +user.destination +'&transferStation='+ user.transferStation,
				})
			},
			//删除
			delete1(id) {
				//提示
				this.show1 = true
				this.id1 = id
			},
			//确定删除
			confirm1() {
				this.show1 = false
				uni.request({
					url: this.apiServer + '/users/user?userId=' + this.id1,
					method: 'DELETE',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.$refs.uToast.show({
							position: 'top',
							title: '删除用户成功',
							type: 'success',
							url: '/pages/tabbar/tabbar-4/table'
						})
					}
				});
			},
			//删除
			delete2(id) {
				//提示
				this.show2 = true
				this.id2 = id
			},
			//确定删除
			confirm2() {
				this.show2 = false
				uni.request({
					url: this.apiServer + '/material/material?materialId=' + this.id2,
					method: 'DELETE',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.$refs.uToast.show({
							position: 'top',
							title: '删除物资成功',
							type: 'success',
							url: '/pages/tabbar/tabbar-4/table'
						})
					}
				});
			},
		
		}
	}
</script>

<style>
	.table{
			height: 623px;
	
	/* 	background-color: #dadada; */
	}
	
	.swiper-box {
		flex: 1;
				height: 100%;
	}
	
	.swiper-item {
		height: 100%;
	}
	.onetable{
		height: 600px;

		
	}
	.one-table{
			height: 600px;
	}
</style>
