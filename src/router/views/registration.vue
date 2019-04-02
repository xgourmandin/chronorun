<script>
  import Layout from '@layouts/main'
  import ContestantForm from '@components/contestant-form'
  import ContestantList from '@components/contestant-list'

  export default {
    components: {Layout, ContestantForm, ContestantList},
    page: {
      title: 'Inscription',
      meta: [{name: 'description', content: 'Inscrption des participants'}],
    },
  }
</script>

<template>
  <Layout>
    <ContestantForm :class="$style.container"></ContestantForm>
    <ContestantList></ContestantList>
  </Layout>
</template>
<style lang="scss" module>
  @import '@design';

.container {
  margin-bottom: 1em;
}
</style>
