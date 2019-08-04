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
    },
    ADD_RESULT(state, result) {
      state.raceResults.push(result)
    },
    UPDATE_RESULT(state, result) {
      state.raceResults.splice(state.raceResults.findIndex(r => r.id === result.id), 1)
      state.raceResults.push(result)
    }
  },
  actions: {
    mark() {
      const raceTime = new Date()
      api().post('/mark', {markTime: raceTime})
    },
    saveFinish({commit, state}, bib) {
      if (findResultByBib(state.raceResults, bib)) {
        return Promise.reject(Error("Ce dossard a déjà été enregistré"))
      } else {
        if (state.markedTimes.length === 0) {
          commit('MARK', new Date())
        }
        const raceTime = state.markedTimes[0]
        api().post('/finish', {bib: bib, raceTime: new Date(raceTime)})
        return Promise.resolve(true)
      }
    },
    loadResults({commit}) {
      api().get('/raceresult?sort=raceTime').then(r => {
        let results = r.data._embedded.result
        return results.map(c => {
          delete c._links
          return c
        })
      })
        .then(results => commit('SET_RESULTS', results))
    },
    deleteResult(store, id) {
      api().delete('/raceresult/'+id)
    },
    updateResult(store, result) {
      api().patch('/result', result)
    },
    recordGiveUp(store, bib) {
      api().post('/finish/giveup', bib)
    },
    webSocketCreateResult({commit}, result) {
      commit('FINISH_SAVED')
      commit('ADD_RESULT', result)
    },
    webSocketDeleteResult({commit}, result) {
      commit('DELETE_RESULT', result.id)
    },
    webSocketUpdateResult({commit}, result) {
      commit('UPDATE_RESULT', result)
    },
    webSocketMarkTime({commit}, raceTime) {
      commit('MARK', raceTime)
    }
  }
}

function findResultByBib(raceResults, bib) {
  return raceResults.find(r => r.contestant.bib === Number(bib))
}