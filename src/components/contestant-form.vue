<script>
  // import store from '@state/store'
  import { mapState } from 'vuex'

export default {

  data() {
    return {
      name: '',
      bib: '',
      category: ''
    }

  },
  computed: {
    ...mapState('race', {
      choices: (state) => state.raceParams.categories.map(cat => { return {value: cat.toLowerCase(), label: cat} })
    })
  },
  methods: {
    addContestant: function () {

    }
  }
}
</script>

<template>
  <form
    @submit.prevent="addContestant">
    <label for="contestant">Nom et prénom</label>
    <BaseInputText
      id="contestant"
      v-model="name"
      name="username"
      :placeholder="'Nom du participant'"
    />
    <label for="bib">Numéro de dossard</label>
    <BaseInputText
      id="bib"
      v-model="bib"
      name="bib"
      :placeholder="'Dossard'"
    />
    <label for="category">Catégorie</label>
    <BaseSelect id="category" :choices="choices"></BaseSelect>
  </form>
</template>

<style lang="scss" module>
@import '@design';
</style>
