<script>
import RaceResultTable from '../components/race-result-table'
export default {
  page: {
    title: 'Resultats',
    meta: [
      { name: 'description', content: 'Saisie des résultats de la course' },
    ],
  },
  components: { RaceResultTable },
}
</script>

<template>
  <v-flex>
    <RaceResultTable></RaceResultTable>
  </v-flex>
</template>

