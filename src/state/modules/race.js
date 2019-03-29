export const state = {
  raceParams: getSavedState('race.params'),
  raceStarted: getSavedState('race.started'),
  raceStartDate: new Date(getSavedState('race.startDate')),
  markedTimes: getSavedState('race.markedTimes'),
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
  SET_RACE_PARAMS(state, newValue) {
    state.raceParams = newValue;
  },
  SET_RACE_START(state, newValue) {
    state.raceStarted = newValue
    state.raceStartDate = new Date()
    state.markedTimes = []
    saveState('race.started', newValue)
    saveState('race.startDate', state.raceStartDate)
    saveState('race.markedTimes', state.markedTimes)
  },
  MARK(state, time) {
    state.markedTimes.push({
      id: state.markedTimes.length + 1,
      temps: time.getTime() - state.raceStartDate.getTime(),
    })
    saveState('race.markedTimes', state.markedTimes)
  },
}

export const actions = {
  startRace({ commit }) {
    commit('SET_RACE_START', true)
  },
  stopRace({ commit }) {
    commit('SET_RACE_START', false)
  },
  mark({ commit }) {
    commit('MARK', new Date())
  },
  setParams({commit}, params) {
    commit('SET_RACE_PARAMS', params)
  }
}

function getSavedState(key) {
  return JSON.parse(window.localStorage.getItem(key))
}

function saveState(key, state) {
  window.localStorage.setItem(key, JSON.stringify(state))
}
