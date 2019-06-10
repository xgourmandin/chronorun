import Vue from 'vue'
import Vuex from "vuex";
import racestore from './stores/race.store'
import contestantstore from './stores/contestant.store'
import resultstore from './stores/results.store'

Vue.use(Vuex)

const store = new Vuex.Store({
 modules:{
   race: racestore,
   contestant: contestantstore,
   results: resultstore
 }
})

export default store
