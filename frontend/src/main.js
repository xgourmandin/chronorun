import Vue from 'vue'
import vuetify from './plugins/vuetify'
import router from './plugins/router'
import store from './store'
import App from './App.vue'

Vue.config.productionTip = false

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App),
}).$mount('#app')

