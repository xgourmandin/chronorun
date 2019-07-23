<script>
  import store from '../store'

  export default {
    data() {
      return {
        bib: ''
      }
    },
    computed: {
      form() {
        return {
          bib: this.bib,
        }
      }
    },
    methods: {
      cleanForm() {
        this.bib = '';
      },
      saveResult: function () {
          store.dispatch('saveFinish', this.form.bib).then( () => this.cleanForm())
      }
    },
  }
</script>

<template>
  <form @submit.prevent="saveResult">
    <v-text-field
      id="bib"
      label="Dossard"
      v-model="bib"
      name="bib"
      :placeholder="'Numéro de dossard'"
    />
    <v-btn type="submit" color="success">Enregistrer</v-btn>
  </form>
</template>

<style scoped>

</style>