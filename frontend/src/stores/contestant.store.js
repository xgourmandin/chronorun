import {getSavedState, saveState} from './utils'
import api from "../utils/api";

const CATEGORY_MAP = [
  {minAge: 0, maxAge: 6, code: 'BB'},
  {minAge: 7, maxAge: 9, code: 'EA'},
  {minAge: 10, maxAge: 11, code: 'PO'},
  {minAge: 12, maxAge: 13, code: 'BE'},
  {minAge: 14, maxAge: 15, code: 'MA'},
  {minAge: 16, maxAge: 17, code: 'CA'},
  {minAge: 18, maxAge: 19, code: 'JU'},
  {minAge: 20, maxAge: 22, code: 'ES'},
  {minAge: 23, maxAge: 34, code: 'SE'},
  {minAge: 35, maxAge: 39, code: 'M0'},
  {minAge: 40, maxAge: 44, code: 'M1'},
  {minAge: 45, maxAge: 49, code: 'M2'},
  {minAge: 50, maxAge: 54, code: 'M3'},
  {minAge: 55, maxAge: 59, code: 'M4'},
  {minAge: 60, maxAge: 64, code: 'M5'},
  {minAge: 65, maxAge: 69, code: 'M6'},
  {minAge: 70, maxAge: 74, code: 'M7'},
  {minAge: 75, maxAge: 79, code: 'M8'},
  {minAge: 80, maxAge: 84, code: 'M9'},
  {minAge: 85, maxAge: 99, code: 'M10'},
]

export default {
  state: {
    contestants: getSavedState('race.contestants') || [],
  },
  getters: {},
  mutations: {
    ADD_CONTESTANT(state, contestant) {
      state.contestants.push(contestant)
    },
    DELETE_CONTESTANT(state, contestant) {
      state.contestants.splice(state.contestants.findIndex(c => c.bib === contestant.bib), 1)
    },
    REPLACE_CONTESTANT(state, contestant) {
      state.contestants.splice(state.contestants.findIndex(c => c.bib === contestant.bib), 1)
      state.contestants.push(contestant)
    },
    SET_CONTESTANTS(state, contestants) {
      state.contestants = contestants
      saveState('race.contestants', contestants)
    },
  },
  actions: {
    addContestant({state}, contestant) {
      if (!findContestantByBib(state.contestants, Number(contestant.bib))) {
        contestant = fillCategoryAndSex(contestant)
        api().post("/contestant", contestant)
        return Promise.resolve(true)
      } else {
        return Promise.reject(Error("Le dossard " + contestant.bib + " existe déjà"))
      }
    },
    editContestant(store, contestant) {
      contestant = fillCategoryAndSex(contestant)
      api().patch("/contestant/" + contestant.id, contestant)
    },
    deleteContestant(store, contestant) {
      api().delete("/contestant/" + contestant.id)
    },
    loadContestants({commit}) {
      api().get("/contestant").then(r => {
        let contestants = r.data._embedded.contestant
        return contestants.map(c => {
          delete c._links
          return c
        })
      })
        .then(contestants => commit('SET_CONTESTANTS', contestants))
    },
    webSocketCreateContestant({commit}, contestant) {
      commit('ADD_CONTESTANT', contestant)
    },
    webSocketUpdateContestant({commit}, contestant) {
      commit('REPLACE_CONTESTANT', contestant)
    },
    webSocketDeleteContestant({commit}, contestant) {
      commit('DELETE_CONTESTANT', contestant)
    }
  }
}

function findContestantByBib(contestants, bib) {
  return contestants.find(c => c.bib === bib)
}

function fillCategoryAndSex(contestant) {
  let sex = 'M'
  if (contestant.sex) {
    sex = 'F'
  }
  contestant.sex = sex

  let age = new Date().getFullYear() - contestant.birthYear
  let cat = CATEGORY_MAP.find(e => age >= e.minAge && age <= e.maxAge)
  contestant.category = cat.code + sex
  return contestant
}