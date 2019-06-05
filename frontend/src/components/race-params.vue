<script>
  import {mapState, mapMutations} from 'vuex'
  import _ from 'lodash'
  import formatDate from '../utils/format-date'

  export default {
    computed: {
      ...mapState('race', {
        raceName: state => state.raceParams.name,
        distance: state => state.raceParams.distance,
        raceDate: state => formatDate( state.raceParams.raceDate),
      })
    },
    methods: {
      ...mapMutations('race', ['updateRaceName', 'updateRaceDistance', 'updateRaceDate']),
      debounceUpdate: _.debounce(function (mutator, e) {
          mutator(e.target.value)
        }, 500)
    },
  }
</script>

<template>
  <div class="paramsContainer">
    <v-text-field
      id="raceName"
      label="Nom de la course"
      :value="raceName"
      name="raceName"
      :placeholder="'Nom de course'"
      @input="debounceUpdate(updateRaceName, $event)"
    />
    <v-text-field
      id="racedist"
      :value="distance"
      label="Distance"
      type="number"
      name="racedist"
      step="0.1"
      @input="debounceUpdate(updateRaceDistance, $event)"
    />
    <v-text-field
      id="racedate"
      label="Date de la course"
      type="date"
      :value="raceDate"
      name="racedate"
      @input="debounceUpdate(updateRaceDate,$event)"
    />
  </div>
</template>

<style scoped>

  .paramsContainer > label {
    display: block;
    padding-bottom: 0.25em;
    color: #35495e;
  }

</style>
