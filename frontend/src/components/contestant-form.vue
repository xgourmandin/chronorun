<script>
  import store from '../store'
  import EventBus from "../event-bus";

  export default {

    data() {
      return {
        name: '',
        birthYear: 19,
        bib: '',
        category: '',
        sex: false,
        club: '',
        errorMessage: '',
        alert: false,
        editMode: false,
        editedId: null
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
    mounted() {
      EventBus.$on('edit_contestant', function (contestant) {
        this.editMode = true;
        this.editedId = contestant.id;
        this.name = contestant.name;
        this.birthYear = contestant.birthYear;
        this.bib = contestant.bib;
        this.category = contestant.category;
        this.sex = contestant.sex == 'F';
        this.club = contestant.club
      }.bind(this))
    },
    methods: {
      cleanForm() {
        this.alert = false;
        this.name = '';
        this.birthYear = 19;
        this.bib = '';
        this.category = '';
        this.sex = false;
        this.club = ''
      },
      saveContestant: function () {
        if (this.editMode) {
          this.editMode = false;
          let editedContestant = this.form;
          editedContestant.id = this.editedId;
          store.dispatch('editContestant', editedContestant)
            .then( () => this.cleanForm())
        } else {
          store.dispatch('addContestant', this.form).then( () => this.cleanForm()).catch(error => {
            this.alert = true;
            this.errorMessage = error.message
          })
        }
      }
    }
  }
</script>

<template>
  <form
    @submit.prevent="saveContestant">
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
    <v-btn color="success" type="submit">{{editMode ? 'Editer' : 'Inscrire'}}</v-btn>
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
