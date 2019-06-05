import Vue from 'vue'
import './plugins/vuetify'
import router from './plugins/router'
import store from './store'
import App from './App.vue'

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')

