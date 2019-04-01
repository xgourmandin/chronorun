const CATEGORY_MAP = [
  { minAge: 0, maxAge: 6, code: 'BB' },
  { minAge: 7, maxAge: 9, code: 'EA' },
  { minAge: 10,maxAge: 11, code: 'PO' },
  { minAge: 12,maxAge: 13, code: 'BE' },
  { minAge: 14,maxAge: 15, code: 'MA' },
  { minAge: 16,maxAge: 17, code: 'CA' },
  { minAge: 18,maxAge: 19, code: 'JU' },
  { minAge: 20,maxAge: 22, code: 'ES' },
  { minAge: 23,maxAge: 39, code: 'SE' },
  { minAge: 40,maxAge: 49, code: 'V1' },
  { minAge: 50,maxAge: 59, code: 'V2' },
  { minAge: 60,maxAge: 69, code: 'V3' },
  { minAge: 70,maxAge: 79, code: 'V4' },
  { minAge: 80,maxAge: 99, code: 'V5' },
]




export const state = {
  raceParams: getSavedState('race.params') || {name: '', distance: 0, raceDate: new Date()},
  raceStarted: getSavedState('race.started'),
  raceStartDate: new Date(getSavedState('race.startDate')),
  markedTimes: getSavedState('race.markedTimes'),
  contestants: getSavedState('race.contestants') || [],
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
  updateRaceName(state, value) {
    state.raceParams.name = value
    saveState('race.params', state.raceParams)
  },
  updateRaceDistance(state, value) {
    state.raceParams.distance = Number(value)
    saveState('race.params', state.raceParams)
  },
  updateRaceDate(state, value) {
    state.raceParams.raceDate = value
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
  ADD_CONTESTANT(state, contestant) {
    state.contestants.push(contestant)
  },
  DELETE_CONTESTANT(state, contestant) {
    state.contestants.splice(state.contestants.findIndex(c => c.bib === contestant.bib), 1)
  }
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
  addContestant({commit}, contestant) {
    contestant = fillCategoryAndSex(contestant)
    commit('ADD_CONTESTANT', contestant)
  },
  deleteContestant({commit}, contestant) {
    commit('DELETE_CONTESTANT', contestant)
  }
}

function getSavedState(key) {
  return JSON.parse(window.localStorage.getItem(key))
}

function saveState(key, state) {
  window.localStorage.setItem(key, JSON.stringify(state))
}

function fillCategoryAndSex(contestant) {
  let sex = 'M'
  if(contestant.sex) {
    sex='F'
  }
  contestant.sex = sex

  let age = new Date().getFullYear() - contestant.birthYear
  let cat = CATEGORY_MAP.find( e => age >= e.minAge && age <= e.maxAge )
  contestant.category = cat.code+sex
  return contestant
}
