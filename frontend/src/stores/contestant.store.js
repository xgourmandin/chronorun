import {getSavedState, saveState} from './utils'
import api from "../utils/api";

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
    addContestant({commit, state}, contestant) {
      if (!findContestantByBib(state.contestants, Number(contestant.bib))) {
        contestant = fillCategoryAndSex(contestant)
        api().post("/contestant", contestant).then(c => {
          delete c.data._links
          commit('ADD_CONTESTANT', c.data)
        })
        return Promise.resolve(true)
      } else {
        return Promise.reject(Error("Le dossard " + contestant.bib + " existe déjà"))
      }
    },
    editContestant({commit}, contestant) {
      contestant = fillCategoryAndSex(contestant)
      api().patch("/contestant/" + contestant.id, contestant).then(c => {
          let result = c.data
          delete result._links
          commit('REPLACE_CONTESTANT', result)
        }
      )
    },
    deleteContestant({commit}, contestant) {
      api().delete("/contestant/" + contestant.id).then(() => {
        commit('DELETE_CONTESTANT', contestant)
      })
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