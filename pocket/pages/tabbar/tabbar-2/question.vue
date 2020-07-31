<template>
	<view class="que">
		<view class="one">
			<u-row :gutter="4">
				<u-col :span="3">
					<u-image width="100px" height="100px" src="../../../static/que.png"></u-image>
				</u-col>
				<u-col :span="9">
					<view class="num">
						调查问卷1
						<view class="three">
								<u-button @click="goWJX" type="primary" class="bubu" size="mini" style="margin-left: 5px;">填写</u-button>
								<u-button type="primary" class="bubu" size="mini" style="margin-left: 5px;">修改</u-button>
								<u-button type="primary" class="bubu" size="mini" style="margin-left: 5px;">删除</u-button>
						</view>
					</view>
				</u-col>
			</u-row>
		</view>
	</view>
</template>

<script>
	export default
	{
		data() 
		{
			return
			{
				avatar: '/static/avatar.png',
			}
		},
		
		methods:{
			goWJX(){
				uni.navigateToMiniProgram({ //这里用uniapp的跳转方法，原生应该是wx.navigateToMiniProgram
						  appId: 'wxd947200f82267e58',//这里的appid为目标小程序的appid，此处为问卷星小程序
						  path:'pages/wjxqList/wjxqList?activityId='+'',//此处填入需要跳转的问卷ID（从库中取得）
						  success(res) {
						  		// 打开成功
							   console.log(res)
						  },
						  fail(err){
						  		// 打开失败	
							  console.log(err)
						  }
						})
			}
		}
		
	}
</script>

<style>
	.que {
		background-color: #dedede;
		padding-left: 20px;
		padding-right: 20px;
		padding-top: 20px;
		height: 1000px;
	}

	.one {
		background-color: #FFFFFF;

	}

	.num {
		padding-left: 6px;
		margin-top: 40px;
	}

	.three {
		padding-left: 90px;
	}

	.bubu {

		width: 20px;
		height: 20px;
		font-size: 10px;
	}
</style>
