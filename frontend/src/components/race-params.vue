<script>
  import {mapMutations} from 'vuex'
  import formatDate from "../utils/format-date";

  export default {
    data() {
      return {
      name: 'toto',
      distance: 0,
      raceDate: formatDate(new Date())
      }
    },
    computed: {

    },
    methods: {
      ...mapMutations(['updateRaceName', 'updateRaceDistance', 'updateRaceDate']),
    },
  }
</script>

<template>
    <form>
    <v-text-field
      id="raceName"
      label="Nom de la course"
      :value="name"
      name="raceName"
      :placeholder="'Nom de course'"
    />
    <v-text-field
      id="racedist"
      :value="distance"
      label="Distance"
      type="number"
      name="racedist"
      step="0.1"
    />
    <v-text-field
      id="racedate"
      label="Date de la course"
      type="date"
      :value="raceDate"
      name="racedate"
    />
      <v-btn color="success">Créer</v-btn>
    </form>
</template>

<style scoped>

</style>
