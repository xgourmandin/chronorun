import {saveState} from './utils'
import api from '../utils/api'

export default {
  state: {
    races: [],
    raceStarted: false,
    raceStartDate: null,
  },
  getters: {
    raceStarted(state) {
      return state.raceStarted
    },
    raceStartDate(state) {
      return new Date(state.raceStartDate)
    },
  },
  mutations: {
    ADD_RACE(state, race) {
      state.races.push(race)
    },
    UPDATE_RACE(state, race) {
      state.races.splice(state.races.findIndex(c => c.id === race.id), 1)
      state.races.push(race)
    },
    DELETE_RACE(state, contestant) {
      state.races.splice(state.races.findIndex(c => c.id === contestant.id), 1)
    },
    SET_RACE_START(state, newValue) {
      state.raceStarted = newValue
    },
    SET_RACE_START_DATE(state, raceStartDate) {
      state.raceStartDate = raceStartDate
    },
    SET_RACES(state, races) {
      state.races = races
      saveState('races', races)
    },
  },
  actions: {
    addRace({commit}, race) {
      api().post('/race', race).then(r => {
        delete r.data._links
        commit('ADD_RACE', r.data)
      })
    },
    editRace({commit}, race) {
      api().patch("/race/" + race.id, race).then(r => {
          let result = r.data
          delete result._links
          commit('UPDATE_RACE', result)
        }
      )
    },
    deleteRace({commit}, race) {
      api().delete("/race/" + race.id).then(() => {
        commit('DELETE_RACE', race)
      })
    },
    loadRaces({commit}) {
      api().get("/race").then(r => {
        let races = r.data._embedded.race
        return races.map(r => {
          delete r._links
          return r
        })
      })
        .then(races => commit('SET_RACES', races))
    },
    startRace({ commit, state }, raceId) {
      let race = findRaceInState(state.races, raceId);
      api().put('/start', race).then((r) => {
        let result = r.data
        delete result._links
        commit('UPDATE_RACE', result)
        commit('SET_RACE_START', true)
        commit('SET_RACE_START_DATE', new Date(Date.parse(result.raceStartDate)))
      })
    },
    stopRace({ commit }, race) {
      race.raceStartDate = null
      api().patch('/race/'+race.id, race)
      commit('SET_RACE_START', false)
    },
    selectRace({commit, state}, raceId) {
      let race = findRaceInState(state.races, raceId);
      if (race) {
        commit('SET_RACE_START', race.raceStartDate != null)
        commit('SET_RACE_START_DATE', new Date(Date.parse(race.raceStartDate)))
      }
    },
  }
}

function findRaceInState(races, raceID) {
  return races.find(r => r.id === raceID)
}
