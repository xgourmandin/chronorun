<script>
  import store from '../store'
  import { mapState } from 'vuex'
  import EventBus from "../event-bus";

  export default {

    data() {
      return {
        name: '',
        birthYear: 19,
        bib: '',
        category: '',
        sex: false,
        race: null,
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
          race: this.race,
          club: this.club
        }
      },
      ...mapState({
        races: (state) => state.race.races
      }),
    },
    mounted() {
      EventBus.$on('edit_contestant', function (contestant) {
        this.editMode = true;
        this.editedId = contestant.id;
        this.name = contestant.name;
        this.birthYear = contestant.birthYear;
        this.bib = contestant.bib;
        this.category = contestant.category;
        this.sex = contestant.sex === 'F';
        this.race = contestant.race
        this.club = contestant.club
      }.bind(this))
      store.dispatch('loadRaces')
    },
    methods: {
      cleanForm() {
        this.alert = false;
        this.name = '';
        this.birthYear = 19;
        this.bib = '';
        this.category = '';
        this.sex = false;
        this.race = null
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
      },
      getRaceName(race) {
        return race.name + ', '+race.distance+'Km'
      },
      cancelEdit() {
        this.editMode = false
        this.cleanForm()
      },
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
    <div class="halfpage">
      <v-select
        v-model="race"
        :items="races"
        :item-text="getRaceName"
        item-value="id"
        label="Sélectionner une course"
        class="smallinput"
      ></v-select>&nbsp;
    </div>
    <div class="halfpage">
    <v-text-field
      id="club"
      v-model="club"
      label="Club"
      name="club"
      class="smallinput"
    />
    </div>
    <v-btn color="success" type="submit">{{editMode ? 'Editer' : 'Inscrire'}}</v-btn>
    <v-btn color="warning" @click="cancelEdit" v-if="editMode">Annuler</v-btn>
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
