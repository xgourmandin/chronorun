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
    mark: function() {
      store.dispatch('race/mark')
    },
  },
}
</script>

<template>
  <div :class="$style.container">
    <BaseButton :disabled="raceStarted" @click="startRace"
      >Top d&eacute;part</BaseButton
    >
    -
    <BaseButton :disabled="!raceStarted" @click="endRace"
      >Stopper la course</BaseButton
    >
    <br />
    <br />
    <BaseButton :class="$style.bigbutton" :disabled="!raceStarted" @click="mark"
      >Mark !</BaseButton
    >
  </div>
</template>

<style lang="scss" module>
@import '@design';

.container {
  width: 100%;
  text-align: center;
}

.bigbutton {
  width: 50%;
  height: 10rem;
}
</style>
