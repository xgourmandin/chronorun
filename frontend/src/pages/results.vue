<script>
import RaceResultTable from '../components/race-result-table'
import RaceResultForm from "../components/race-result-form";
export default {
  page: {
    title: 'Resultats',
    meta: [
      { name: 'description', content: 'Saisie des résultats de la course' },
    ],
  },
  components: {RaceResultForm, RaceResultTable },
}
</script>

<template>
  <v-flex>
    <RaceResultForm class="container"></RaceResultForm>
    <RaceResultTable></RaceResultTable>
  </v-flex>
</template>

