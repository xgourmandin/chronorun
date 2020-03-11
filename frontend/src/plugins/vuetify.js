import '@mdi/font/css/materialdesignicons.css'
import Vue from 'vue'
import Vuetify from 'vuetify/lib'

Vue.use(Vuetify, {
  theme: {
    dark: true,
  },
})

const opts = {}

export default new Vuetify(opts)