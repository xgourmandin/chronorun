<template>
  <ul id="race-results">
    <li v-for="item in raceResults" :key="item.id">
      {{ item.contestant.name }} - {{durationFormat(item.raceDuration)}}
    </li>
  </ul>
</template>

<script>

  import {mapState} from 'vuex'
  import {formatDuration} from '../utils/duration'

  export default {
    name: "result-display",
    computed: {
      ...mapState({
        raceResults: (state) => state.results.raceResults.reverse().slice(0, 6),
      }),
    },
    methods: {
      durationFormat: function(duration) {
        let formatted = formatDuration(duration);
        const milliRegExp = /\.\d\d\dS/g
        return formatted.replace("M", " Minutes ").replace("H", " Heure ").replace(milliRegExp, " sec")
      }
    }
  }
</script>

<style scoped>

  ul {
    list-style-type: none;
  }

  li {
    font-size: 5em;
    text-align: center;
  }
</style>