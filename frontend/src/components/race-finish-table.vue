<script>
  import {mapState} from 'vuex'
  import store from '../store'
  import ValidatingButton from "./validating-button"

  export default {
    components: {ValidatingButton},
    data: function () {
      return {
        search: '',
        headers: [
          {text: 'Heure d\'arrivée', value: 'mark'},
          {text: 'Actions', value: 'actions', sortable: false}
        ],
        pagination: {
          itemsPerPage: 15
        },
      }
    },
    computed: {
      ...mapState( {
        raceTimes: (state) => state.results.markedTimes,
      }),
    },
    methods: {
      deleteItem: function(item) {
        store.dispatch("deleteMark", item)
      }
    }
  }
</script>

<template>
  <v-card>
    <v-card-title primary-title><b>Temps marqués disponibles : {{raceTimes.length}}</b></v-card-title>
    <v-data-table
      :headers="headers"
      :items="raceTimes"
      :search="search"
      :options="pagination"
    >
      <template v-slot:no-data>
        Pas de temps disponible
      </template>
      <template v-slot:item.actions="{ item }">
        <validating-button color="error" confirm-count="0" action-text="Supr" @click-validated="deleteItem(item)"></validating-button>
      </template>
    </v-data-table>
  </v-card>
</template>

<style scoped>

</style>
