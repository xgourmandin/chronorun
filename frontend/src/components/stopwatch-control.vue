<script>
import store from '../store'
import { mapState } from 'vuex'
import ValidatingButton from "./validating-button";

export default {
  components: {ValidatingButton},
  data() {
    return {
      raceSelected: {name: '', distance: ''}
    }
  },
  computed: {
    ...mapState({
      raceStarted: (state) => state.race.raceStarted,
      races: (state) => state.race.races
    }),
  },
  mounted() {
    store.dispatch('loadRaces')
  },
  created() {
    window.addEventListener('keydown', this.onkey)
  },
  beforeDestroy() {
    window.removeEventListener('keydown', this.onkey)
  },
  watch: {
    races: function(newRaces){
      store.dispatch('selectRace', newRaces[0].id)
      this.raceSelected = newRaces[0]
    }
  },
  methods: {
    startRace: function() {
      store.dispatch('startRace', this.raceSelected.id)
    },
    endRace: function() {
      store.dispatch('stopRace', this.raceSelected)
    },
    raceSelect: function() {
      store.dispatch('selectRace', this.raceSelected.id)
    },
    mark: function() {
      store.dispatch('mark')
    },
    getRaceName(race) {
      return race.name + ', '+race.distance+'Km'
    },
    onkey: function(e) {
      if (e.code == 'Space') {
        this.mark()
      }
    }
  },
}
</script>

<template>
  <div class="container">
    <v-select
      v-model="raceSelected"
      :items="races"
      :item-text="getRaceName"
      item-value="id"
      label="Sélectionner une course"
      return-object
      @change="raceSelect"
    ></v-select>&nbsp;
    <v-btn color="success" :disabled="raceStarted" @click="startRace" >Top d&eacute;part</v-btn>
    <v-divider
      class="mx-4"
      vertical
      light
    ></v-divider>
    <validating-button color="warning" :disabled="!raceStarted" @click-validated="endRace" confirm-count="2" action-text="Stopper la course"/>
    <br />
    <br />
    <v-divider></v-divider>
    <br />
    <v-btn color="error" class="bigbutton" :disabled="!raceStarted" @click="mark" v-on:keypress.space="mark"
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
  height: 10rem !important;
}
</style>
