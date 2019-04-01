<script>
  import {mapState} from 'vuex'
  export default {
    data() {
      return {
        headers: [
          {text: 'Dossard', value: 'bib'},
          {text: 'Nom', value: 'name'},
          {text: 'Naissance', value: 'birthYear'},
          {text: 'Catégorie', value: 'category'},
          {text: 'Sexe', value: 'sex'}
        ]
      }
    },
    computed: {
      ...mapState('race', {
        contestants: state => state.contestants
      })
    }

  }
</script>

<template>
  <v-data-table
    :headers="headers"
    :items="contestants"
  >
    <template v-slot:items="props">
      <td>{{ props.item.bib }}</td>
      <td class="text-xs-right">{{ props.item.name}}</td>
      <td class="text-xs-right">{{ props.item.birthYear }}</td>
      <td class="text-xs-right">{{ props.item.category }}</td>
      <td class="text-xs-right">{{ props.item.sex }}</td>
    </template>
  </v-data-table>
</template>

<style lang="scss" module>
  @import '@design';
</style>
