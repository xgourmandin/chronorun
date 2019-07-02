<script>
  import RaceFinishForm from '../components/finish-form'
  import RaceFinishTable from '../components/race-finish-table'
  export default {
    page: {
      title: 'Arrivée',
      meta: [
        { name: 'description', content: 'Saisie des dossards à l\'arrivée de la course' },
      ],
    },
    components: { RaceFinishForm, RaceFinishTable },
  }
</script>

<template>
  <v-flex>
    <RaceFinishForm class="container"></RaceFinishForm>
    <RaceFinishTable></RaceFinishTable>
  </v-flex>
</template>
