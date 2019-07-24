import {getSavedState, saveState} from './utils'
import api from '../utils/api'

export default {
  state: {
    markedTimes: getSavedState('race.markedTimes') || [],
    raceResults: getSavedState('race.results') || [],
  },
  getters: {},
  mutations: {
    MARK(state, time) {
      state.markedTimes.push(time.getTime())
      saveState('race.markedTimes', state.markedTimes)
    },
    FINISH_SAVED(state) {
      state.markedTimes.shift()
      saveState('race.markedTimes', state.markedTimes);
    },
    SET_RESULTS(state, results) {
      state.raceResults = results
    },
    DELETE_RESULT(state, id) {
      state.raceResults.splice(state.raceResults.findIndex(r => r.id === id), 1)
    }
  },
  actions: {
    mark({commit}) {
      commit('MARK', new Date())
    },
    saveFinish({commit, state}, bib) {
      if (findResultByBib(state.raceResults, bib)) {
        if (state.markedTimes.length == 0) {
          commit('MARK', new Date())
        }
        const raceTime = state.markedTimes[0]
        api().post('/finish', {bib: bib, raceTime: new Date(raceTime)}).then(() => {
          commit('FINISH_SAVED')
        })
        return Promise.resolve(true)
      } else {
        return Promise.reject(Error("Ce dossard a déjà été enregistré"))
      }
    },
    loadResults({commit}) {
      api().get('/raceresult').then(r => {
        let results = r.data._embedded.result
        return results.map(c => {
          delete c._links
          return c
        })
      })
        .then(results => commit('SET_RESULTS', results))
    },
    deleteResult({commit}, id) {
      api().delete('/raceresult/'+id).then(() => commit('DELETE_RESULT', id))
    }
  }
}

function findResultByBib(raceResults, bib) {
  return raceResults.find(r => r.contestant.bib === bib)
}