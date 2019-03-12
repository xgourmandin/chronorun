export const state = {
  raceStarted: getSavedState('race.started'),
  raceStartDate: getSavedState('race.startDate'),
}

export const getters = {
  raceStarted(state) {
    return state.raceStarted
  },
  raceStartDate(state) {
    return new Date(state.raceStartDate)
  },
}

export const mutations = {
  SET_RACE_START(state, newValue) {
    state.raceStarted = newValue
    state.raceStartDate = new Date()
    saveState('race.started', newValue)
    saveState('race.startDate', state.raceStartDate)
  },
}

export const actions = {
  startRace({ commit }) {
    commit('SET_RACE_START', true)
  },
  stopRace({ commit }) {
    commit('SET_RACE_START', false)
  },
}

function getSavedState(key) {
  return JSON.parse(window.localStorage.getItem(key))
}

function saveState(key, state) {
  window.localStorage.setItem(key, JSON.stringify(state))
}
