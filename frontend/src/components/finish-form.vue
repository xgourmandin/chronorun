<script>
  import store from '../store'
  import ValidatingButton from "./validating-button";

  export default {
    components: {ValidatingButton},
    data() {
      return {
        bib: '',
        errorMessage: '',
        alert: false,
        giveUpDialog: false,
        giveUpBib: null
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
      },
      recordGiveUp() {
        store.dispatch('recordGiveUp', this.giveUpBib)
        this.giveUpDialog = false
        this.giveUpBib = null
      }
    },
    mounted(){
      store.dispatch('loadMarks')
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
      type="number"
      :placeholder="'Numéro de dossard'"
    />
    <v-btn type="submit" color="success" :disabled="bib == ''">Enregistrer</v-btn>
    <v-btn absolute right @click.stop="giveUpDialog = true">
      <v-icon>mdi-flag</v-icon> Abandon
    </v-btn>
    <v-alert v-model="alert" dismissible type="error">
      {{ errorMessage }}
    </v-alert>
    <v-dialog
      v-model="giveUpDialog" max-width="50%">
      <v-card>
        <v-card-title>
          <span class="headline">Abandon d'un participant</span>
        </v-card-title>
        <v-card-text>
          Attention : Vous vous apprétez à enregistrer un abandon. Vérifiez bien le numéro de dossard entré ici.
          <v-text-field type="number" class="smallinput" required label="Numéro de dossard" v-model="giveUpBib"></v-text-field>
          <validating-button color="warning" action-text="Enregistrer l'abandon" confirm-count="0" @click-validated="recordGiveUp"></validating-button>
        </v-card-text>
      </v-card>
    </v-dialog>
  </form>
</template>

<style scoped>
  .smallinput {
    width: 50%;
  }
</style>