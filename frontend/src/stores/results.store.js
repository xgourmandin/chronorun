import {getSavedState, saveState} from './utils'

export default {
  state: {
    markedTimes: getSavedState('race.markedTimes'),
  },
  getters: {

  },
  mutations: {
    MARK(state, time) {
      state.markedTimes.push({
        id: state.markedTimes.length + 1,
        time: time.getTime(),
      })
      saveState('race.markedTimes', state.markedTimes)
    },
  },
  actions: {
    mark({ commit }) {
      commit('MARK', new Date())
    },
  }
}