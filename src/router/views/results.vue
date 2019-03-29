<script>
import Layout from '@layouts/main'
import RaceResultTable from '@components/race-result-table'
export default {
  page: {
    title: 'Resultats',
    meta: [
      { name: 'description', content: 'Saisie des résultats de la course' },
    ],
  },
  components: { Layout, RaceResultTable },
}
</script>

<template>
  <Layout>
    <RaceResultTable></RaceResultTable>
  </Layout>
</template>

<style lang="scss" module>
@import '@design';
</style>
