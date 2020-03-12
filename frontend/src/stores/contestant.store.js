import {getSavedState, saveState} from './utils'
import api from "../utils/api";

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
        contestant = fillSex(contestant)
        api().post("/contestant", contestant)
        return Promise.resolve(true)
      } else {
        return Promise.reject(Error("Le dossard " + contestant.bib + " existe déjà"))
      }
    },
    editContestant(store, contestant) {
      contestant = fillSex(contestant)
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
    },
    uploadCsv(store, {file, race}) {
      let formData = new FormData();
      formData.append('file', file);
      formData.append('raceId', race);
      api().post('/contestant/import', formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        })
        .then(function() {
          store.dispatch('loadContestants')
        })
    }
  }
}

function findContestantByBib(contestants, bib) {
  return contestants.find(c => c.bib === bib)
}

function fillSex(contestant) {
  let sex = 'M'
  if (contestant.sex) {
    sex = 'F'
  }
  contestant.sex = sex
  return contestant
}