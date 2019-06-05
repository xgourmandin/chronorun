<script>
import RaceStopwatch from '../components/race-stopwatch'
import StopwatchControl from '../components/stopwatch-control'

export default {
  page: {
    title: 'Chrono',
  },
  components: { RaceStopwatch, StopwatchControl },
}
</script>

<template>
  <v-flex>
    <RaceStopwatch></RaceStopwatch>
    <StopwatchControl></StopwatchControl>
  </v-flex>
</template>
