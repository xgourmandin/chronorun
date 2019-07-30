<script>
  import store from '../store'

  export default {
    data() {
      return {
        bib: '',
        errorMessage: '',
        alert: false
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
        this.bib = ''
        this.alert = false
      },
      saveResult: function () {
        store.dispatch('saveFinish', this.form.bib).then(() => this.cleanForm())
          .catch(error => {
            this.alert = true
            this.errorMessage = error.message
          })
      }
    },
    mounted(){
      store.dispatch('loadResults')
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
    <v-alert v-model="alert" dismissible type="error">
      {{ errorMessage }}
    </v-alert>
  </form>
</template>

<style scoped>

</style>