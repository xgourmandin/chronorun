<script>
  import formatDate from "../utils/format-date"
  import store from '../store'
  import EventBus from "../event-bus"

  export default {
    data() {
      return {
        name: '',
        distance: 0,
        raceDate: formatDate(new Date()),
        editMode: false,
        editedId: null
      }
    },
    computed: {
      form() {
        return {
          name: this.name,
          distance: this.distance,
          raceDate: this.raceDate,
        }
      }
    },
    mounted() {
      EventBus.$on('edit_race', function (race) {
        this.editMode = true;
        this.editedId = race.id;
        this.name = race.name;
        this.distance = race.distance;
        this.raceDate = race.raceDate;
      }.bind(this))
    },
    methods: {
      cleanForm() {
        this.name = '';
        this.raceDate = formatDate(new Date());
        this.distance = 0;
      },
      saveRace: function () {
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
  <form @submit.prevent="saveRace">
    <v-text-field
      id="raceName"
      label="Nom de la course"
      v-model="name"
      name="raceName"
      :placeholder="'Nom de course'"
    />
    <v-text-field
      id="racedist"
      v-model="distance"
      label="Distance"
      type="number"
      name="racedist"
      step="0.1"
    />
    <v-text-field
      id="racedate"
      label="Date de la course"
      type="date"
      v-model="raceDate"
      name="racedate"
    />
    <v-btn type="submit" color="success">{{editMode ? 'Editer' : 'Créer'}}</v-btn>
  </form>
</template>

<style scoped>

</style>
