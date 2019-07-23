import {getSavedState, saveState} from './utils'
import api from '../utils/api'

export default {
  state: {
    markedTimes: getSavedState('race.markedTimes') || [],
    raceResults: getSavedState('race.results') || [],
  },
  getters: {

  },
  mutations: {
    MARK(state, time) {
      state.markedTimes.push( time.getTime())
      saveState('race.markedTimes', state.markedTimes)
    },
    FINISH_SAVED(state) {
      state.markedTimes.shift()
      saveState('race.markedTimes', state.markedTimes);
    },
    SET_RESULTS(state, results) {
      state.raceResults = results
    }
  },
  actions: {
    mark({ commit }) {
      commit('MARK', new Date())
    },
    saveFinish({commit, state}, bib) {
      if (state.markedTimes.length == 0) {
        commit('MARK', new Date())
      }
      const raceTime = state.markedTimes[0]
      api().post('/finish', {bib: bib, raceTime: new Date(raceTime)}).then(() => {
        commit('FINISH_SAVED')
      })
    },
    loadResults({commit}) {
      api().get('/raceresult').then( r => {
        let results = r.data._embedded.result
        return results.map(c => {
          delete c._links
          return c
        })
      })
        .then(results => commit('SET_RESULTS', results))
    }
  }
}