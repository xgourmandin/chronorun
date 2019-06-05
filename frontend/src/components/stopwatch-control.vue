<script>
import store from '../store'
import { mapState } from 'vuex'

export default {
  computed: {
    ...mapState({
      raceStarted: (state) => state.raceStarted,
    }),
  },
  methods: {
    startRace: function() {
      store.dispatch('startRace')
    },
    endRace: function() {
      store.dispatch('stopRace')
    },
    mark: function() {
      store.dispatch('mark')
    },
  },
}
</script>

<template>
  <div class="container">
    <v-btn color="success" :disabled="raceStarted" @click="startRace"
      >Top d&eacute;part</v-btn>
    -
    <v-btn color="warning" :disabled="!raceStarted" @click="endRace"
      >Stopper la course</v-btn
    >
    <br />
    <br />
    <v-btn color="error" class="bigbutton" :disabled="!raceStarted" @click="mark"
      >Mark !</v-btn
    >
  </div>
</template>

<style scoped>

.container {
  width: 100%;
  text-align: center;
}

.bigbutton {
  width: 50%;
  height: 10rem;
}
</style>
