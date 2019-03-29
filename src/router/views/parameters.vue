<script>
import Layout from '@layouts/main'
import { mapState } from 'vuex'
import RaceParams from '@components/race-params'

export default {
  page: {
    title: 'Parameters',
    meta: [{ name: 'description', content: 'Parameters' }],
  },
  components: { Layout, RaceParams },
  computed: {
    ...mapState('race', {
      raceStarted: (state) => state.raceStarted,
    }),
  },
}
</script>

<template>
  <Layout>
    <div v-if="raceStarted">
      La course &agrave; d&eacute;marr&eacute;. Vous ne pouvez plus modifier les param&egrave;tres.
    </div>
    <div v-else>
      <RaceParams></RaceParams>
    </div>
  </Layout>
</template>

<style lang="scss" module>
@import '@design';
</style>

