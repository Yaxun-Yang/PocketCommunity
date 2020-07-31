<template>
	<view>
		<view class="u-tabs-box">
			<u-tabs-swiper activeColor="#4ac4d7" ref="tabs" :list="headList" :current="current" @change="change" :is-scroll="false"
			 swiperWidth="750"></u-tabs-swiper>
		</view>
		<swiper class="swiper-box" :current="swiperCurrent" @transition="transition" @animationfinish="animationfinish">

			<swiper-item class="swiper-item">
				<u-table>
					<u-tr>
						<u-th>序号</u-th>
						<u-th>姓名</u-th>
						<u-th>手机号</u-th>
						<u-th>社区</u-th>
						<u-th>住址</u-th>
						<u-th>修改</u-th>
						<u-th>删除</u-th>
					</u-tr>
					<u-tr>
						<u-td>1</u-td>
						<u-td>张三</u-td>
						<u-td>110</u-td>
						<u-td>玫瑰</u-td>
						<u-td>东门路</u-td>
						<u-td>
							<u-button type="primary" size="mini" @click="">修改</u-button>

						</u-td>
						<u-td>
							<u-button type="primary" size="mini" @click="">删除</u-button>
						</u-td>
					</u-tr>
				</u-table>
			</swiper-item>

			<swiper-item class="swiper-item">
				<u-table>
					<u-tr>
						<u-th>名称</u-th>
						<u-th>来源</u-th>
						<u-th>去向</u-th>
						<u-th>储存</u-th>
						<u-th>修改</u-th>
						<u-th>删除</u-th>
					</u-tr>
					<u-tr>
						<u-td>1</u-td>
						<u-td>四川</u-td>
						<u-td>西门路</u-td>
						<u-td>东门路</u-td>
						<u-td>
							<u-button type="primary" size="mini" @click="">修改</u-button>

						</u-td>
						<u-td>
							<u-button type="primary" size="mini" @click="">删除</u-button>
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

			}
		},

		onLoad() {

		},
		computed: {
			// userPhone() {
			// 	// return sessionStorage.getItem('phoneNumber');
			// },
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
			//跳转

		}
	}
</script>

<style>
</style>
