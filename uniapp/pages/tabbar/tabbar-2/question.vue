<template>
	<view class="que">
		<u-toast ref="uToast" />
		<view class="new">
			<u-form>
				<u-form-item :label-position="labelPosition" label=" " prop="text" label-width="0">
					<view style="background-color: #FFFFFF;">
						<u-input type="textarea" :border="true" placeholder="请输入问卷星问卷ID" v-model="text"></u-input>
					</view>
					<u-button slot="right" type="primary" size="mini" @click="send">新增</u-button>
				</u-form-item>
			</u-form>
		</view>
		<view class="one" v-for="(que, index) in questionnaireList" :key="que.questionnaireId">
			<u-row :gutter="4">
				<u-col :span="3">
					<u-image width="100px" height="100px" src="../../../static/que.png"></u-image>
				</u-col>
				<u-col :span="9">

					<view class="num">
						调查问卷{{que.questionnaireId}}
						<view class="three">
							<u-modal ref="uModal" v-model="show" :show-cancel-button="true" :show-title="showTitle" :async-close="asyncClose"
							 @confirm="confirm" :content="content">
							</u-modal>
							<u-button @click="change(que.url)" type="primary" class="bubu" size="mini" style="margin-left: 5px;">修改</u-button>
							<u-button @click="deleteQue(que.questionnaireId)" type="primary" class="bubu" size="mini" style="margin-left: 5px;">删除</u-button>

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
		onShow() {
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
			deleteQue(id) {
				//提示
				this.show = true
				this.id = id
			},
			//确定删除
			confirm() {
				this.show = false
				uni.request({
					url: this.apiServer + '/questionnaire/questionnaire?questionnaireId=' + this.id,
					method: 'DELETE',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						//上方提示并刷新
						this.$refs.uToast.show({
							position: 'top',
							title: '删除成功',
							type: 'success',
							// url: '/pages/tabbar/tabbar-2/question'
						})
						uni.redirectTo({
							url:'/pages/tabbar/tabbar-2/question'
						})
					}
				});

			},
			//打开问卷星
			change(url) {
				uni.navigateToMiniProgram({ //这里用uniapp的跳转方法，原生应该是wx.navigateToMiniProgram
						  appId: 'wxd947200f82267e58',//这里的appid为目标小程序的appid，此处为问卷星小程序
						  path:'pages/wjxqList/wjxqList?activityId='+url,//此处填入需要跳转的问卷ID（从库中取得）
						  envVersion: 'release',
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

	.new {
		margin-left: 10px;
		margin-right: 10px;

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
		padding-left: 90px;
	}

	.bubu {
		width: 20px;
		height: 20px;
		font-size: 10px;
	}
</style>
