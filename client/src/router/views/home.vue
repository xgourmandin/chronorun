<script>
import appConfig from '@src/app.config'
import Layout from '@layouts/main'
import RaceStopwatch from '../../components/race-stopwatch'
import StopwatchControl from '../../components/stopwatch-control'

export default {
  page: {
    title: 'Chrono',
    meta: [{ name: 'description', content: appConfig.description }],
  },
  components: { RaceStopwatch, StopwatchControl, Layout },
}
</script>

<template>
  <Layout>
    <RaceStopwatch></RaceStopwatch>
    <StopwatchControl></StopwatchControl>
  </Layout>
</template>
