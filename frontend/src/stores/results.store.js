import {getSavedState, saveState} from './utils'
import api from '../utils/api'

const uuidv4 = require('uuid/v4');

export default {
  state: {
    markedTimes: getSavedState('race.markedTimes') || [],
  },
  getters: {

  },
  mutations: {
    MARK(state, time) {
      state.markedTimes.push({
        id: uuidv4(),
        time: time.getTime(),
      })
      saveState('race.markedTimes', state.markedTimes)
    },
    BIB_SAVED(state, id) {
      state.markedTimes = state.markedTimes.filter(times => times.id === id);
      saveState('race.markedTimes', state.markedTimes);
    }
  },
  actions: {
    mark({ commit }) {
      commit('MARK', new Date())
    },
    saveBib({commit}, bib, raceTime) {
      api().post('/finish', {bib: bib, raceTime: raceTime}).then(() => {
        commit('BIB_SAVED', bib)
      })
    }
  }
}