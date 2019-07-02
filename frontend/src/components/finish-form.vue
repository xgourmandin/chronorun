<script>
  import store from '../store'

  export default {
    data() {
      return {
        bib: '',
        raceTime: '',
      }
    },
    computed: {
      form() {
        return {
          bib: this.name,
        }
      }
    },
    methods: {
      cleanForm() {
        this.bib = '';
        this.raceTime = '';
      },
      saveResult: function () {
        if (this.editMode) {
          this.editMode = false;
          let editedRace = this.form;
          editedRace.id = this.editedId;
          store.dispatch('editRace', editedRace)
            .then( () => this.cleanForm())
        } else {
          store.dispatch('addRace', this.form).then( () => this.cleanForm())
        }
      }
    },
  }
</script>

<template>
  <form @submit.prevent="saveResult">
    {{raceTime}}
    <v-spacer/>
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