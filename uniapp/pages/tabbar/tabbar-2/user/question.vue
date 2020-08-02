<template>
	<view class="que">
		<view class="newque">
		</view>
		<view class="one" v-for="(que, index) in questionnaireList" :key="que.questionnaireId">
			<u-row :gutter="4">
				<u-col :span="3">
					<u-image width="100px" height="100px" src="/static/que.png"></u-image>
				</u-col>
				<u-col :span="9">
					<view class="num">
						调查问卷{{que.questionnaireId}}
						<view class="three">
							<u-button @click="change(que.url)" type="primary" class="bubu" size="mini" style="margin-left: 15px;">填写</u-button>
						</view>
					</view>
				</u-col>
			</u-row>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//新增
				text: '',
				//删除
				id: '',
				show: false,
				content: '你确定要删除吗',
				showTitle: true,
				asyncClose: false,
				//列表
				questionnaireList: [{
					questionnaireId: 1,
					url: ''
				}]
			}
		},
		onLoad() {
			this.getAll()
		},
		methods: {
			//得到
			getAll() {
				uni.request({
					url: this.apiServer + '/questionnaire/questionnaireList',
					method: 'GET',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.questionnaireList = res.data.data.questionnaireList
					}
				});
			},
			//删除
			deleteQue() {
				//提示
				this.show = true
				this.id = id
			},
			//打开问卷星
			change(url) {
				uni.navigateToMiniProgram({ //这里用uniapp的跳转方法，原生应该是wx.navigateToMiniProgram
						  appId: 'wxd947200f82267e58',//这里的appid为目标小程序的appid，此处为问卷星小程序
						  path:'pages/wjxqList/wjxqList?activityId='+url,//此处填入需要跳转的问卷ID（从库中取得）
						  success(res) {
						  		// 打开成功
							   console.log(res)
						  },
						  fail(err){
						  		// 打开失败	
							  console.log(err)
						  }
						})
			},
			send(){
				uni.request({
					url: this.apiServer + '/questionnaire/questionnaire',
					method: 'POST',
					data: {
						url:this.text
					},
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						//上方提示并刷新
						this.$refs.uToast.show({
							position: 'top',
							title: '添加成功',
							type: 'success',
						})
						uni.redirectTo({
							url:'/pages/tabbar/tabbar-2/question'
						})
					}
				});
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

	.newque {
		padding-left: 290px;
	}

	.one {
		background-color: #FFFFFF;
		margin-top: 10px;
	}

	.num {
		padding-left: 6px;
		margin-top: 40px;
	}

	.three {
		padding-left: 150px;
	}

	.bubu {
		width: 20px;
		height: 20px;
		font-size: 10px;
	}
</style>
