<script>
import Layout from '@layouts/main'

export default {
  page: {
    title: 'Inscription',
    meta: [{ name: 'description', content: 'Inscrption des participants' }],
  },
  components: { Layout },
  data() {
    return {
      name: '',
      bib: '',
      category: ''
    }

  },
  methods: {
    addContestant: function () {

    }
  }
}
</script>

<template>
  <Layout>
    <form
      @submit.prevent="addContestant">
      <label for="contestant">Nom et prénom</label>
      <BaseInputText
        id="contestant"
        v-model="name"
        name="username"
        :placeholder="'Nom du participant'"
      />
      <label for="bib">Numéro de dossard</label>
      <BaseInputText
        id="bib"
        v-model="bib"
        name="bib"
        :placeholder="'Dossard'"
      />
    </form>
  </Layout>
</template>
<style lang="scss" module>
@import '@design';
</style>
