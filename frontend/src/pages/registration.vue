<script>
  import ContestantForm from '../components/contestant-form'
  import ContestantList from '../components/contestant-list'

  export default {
    components: {ContestantForm, ContestantList},
    page: {
      title: 'Inscription',
      meta: [{name: 'description', content: 'Inscrption des participants'}],
    },
  }
</script>

<template>
  <v-flex>
    <ContestantForm class="container"></ContestantForm>
    <ContestantList></ContestantList>
  </v-flex>
</template>
<style scoped>

.container {
  margin-bottom: 1em;
}
</style>
