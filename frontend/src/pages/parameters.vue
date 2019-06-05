<script>
import { mapState } from 'vuex'
import RaceParams from '../components/race-params'

export default {
  page: {
    title: 'Parameters',
    meta: [{ name: 'description', content: 'Parameters' }],
  },
  components: { RaceParams },
  computed: {
    ...mapState('race', {
      raceStarted: (state) => state.raceStarted,
    }),
  },
}
</script>

<template>
  <v-flex>
    <div v-if="raceStarted">
      La course &agrave; d&eacute;marr&eacute;. Vous ne pouvez plus modifier les param&egrave;tres.
    </div>
    <div v-else>
      <RaceParams></RaceParams>
    </div>
  </v-flex>
</template>

<style scoped>
</style>

