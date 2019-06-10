<script>
import RaceParams from '../components/race-form'
import RaceList from '../components/race-list'

export default {
  page: {
    title: 'Parameters',
    meta: [{ name: 'description', content: 'Parameters' }],
  },
  components: { RaceParams, RaceList },
}
</script>

<template>
  <v-flex>
      <RaceParams class="container"></RaceParams>
      <RaceList></RaceList>
  </v-flex>
</template>

<style scoped>
</style>

