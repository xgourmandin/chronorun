<script>
  import store from '../store'

  export default {

    data() {
      return {
        name: '',
        birthYear: 1950,
        bib: '',
        category: '',
        sex: false,
        club: '',
        errorMessage: '',
        alert: false
      }

    },
    computed: {
      form() {
        return {
          name: this.name,
          birthYear: this.birthYear,
          bib: this.bib,
          category: this.category,
          sex: this.sex,
          club: this.club
        }
      }
    },
    methods: {
      addContestant: function () {
        store.dispatch('addContestant', this.form).catch(error => {
          this.alert = true
          this.errorMessage = error.message
        })
      }
    }
  }
</script>

<template>
  <form
    @submit.prevent="addContestant">
    <div class="halfpage">
      <v-text-field
        id="contestant"
        v-model="name"
        label="Nom et prénom"
        name="username"
        class="smallinput"
      />
    </div>
    <div class="halfpage">
      <v-text-field
        id="birth"
        v-model="birthYear"
        label="Année de naissance"
        type="number"
        name="birth"
        class="smallinput"
      />
    </div>
    <div class="halfpage">
      <v-text-field
        id="bib"
        v-model="bib"
        label="Dossard"
        type="number"
        name="bib"
        class="smallinput"
      />
    </div>
    <div class="halfpage">
      <v-switch
        id="sex"
        v-model="sex"
        name="sex"
      >
        <template v-slot:prepend>
          Homme &nbsp;
        </template>
        <template v-slot:append>
          Femme
        </template>
      </v-switch>
    </div>
    <v-text-field
      id="club"
      v-model="club"
      label="Club"
      name="club"
    />
    <v-btn color="success" type="submit">Inscrire</v-btn>
    <v-alert
      v-model="alert"
      dismissible
      type="error"
    >
      {{ errorMessage }}
    </v-alert>
  </form>
</template>

<style scoped>

  .halfpage {
    display: inline-table;
    width: 50%;
  }

  .halfpage > label {
    display: block;
    padding-bottom: 0.25em;
    color: #35495e;
  }

  .smallinput {
    width: 80%;
  }
</style>
