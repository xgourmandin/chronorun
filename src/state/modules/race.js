export const state = {
  raceParams: getSavedState('race.params') || {categories: []},
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
  ADD_CATEGORY(state, catToAdd) {
    state.raceParams.categories.push(catToAdd);
    saveState('race.params', state.raceParams)
  },
  DELETE_CATEGORY(state, catToDelete) {
    state.raceParams.categories.splice(state.raceParams.categories.indexOf(catToDelete), 1);
    saveState('race.params', state.raceParams)
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
  addCategory({commit}, {category}) {
    commit('ADD_CATEGORY', category)
  },
  deleteCategory({commit}, {category}) {
    commit('DELETE_CATEGORY', category)
  },
}

function getSavedState(key) {
  return JSON.parse(window.localStorage.getItem(key))
}

function saveState(key, state) {
  window.localStorage.setItem(key, JSON.stringify(state))
}
