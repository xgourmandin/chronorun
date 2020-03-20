import '@mdi/font/css/materialdesignicons.css'
import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify, {
  theme: {
    dark: true,
  },
})

const opts = {}

export default new Vuetify(opts)