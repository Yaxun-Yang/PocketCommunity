import Vue from 'vue'
import App from './App'
import uView from "uview-ui";
Vue.use(uView);
Vue.config.productionTip = false
Vue.prototype.apiServer = 'http://localhost:8099/'
App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
