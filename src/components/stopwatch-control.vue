<script>
import store from '@state/store'
import { mapState } from 'vuex'

export default {
  computed: {
    ...mapState('race', {
      raceStarted: (state) => state.raceStarted,
    }),
  },
  methods: {
    startRace: function() {
      store.dispatch('race/startRace')
    },
    endRace: function() {
      store.dispatch('race/stopRace')
    },
  },
}
</script>

<template>
  <div>
    <BaseButton :disabled="raceStarted" @click="startRace"
      >Top d&eacute;part</BaseButton
    >
    -
    <BaseButton :disabled="!raceStarted" @click="endRace"
      >Stopper la course</BaseButton
    >
  </div>
</template>

<style lang="scss" module>
@import '@design';
</style>
